package game.engine;

import game.collectibles.*;
import game.creatures.*;
import game.dice.*;
import game.gui.GUIGameController;
import game.gui.Guider;
import game.realms.*;
import game.utilities.GameColor;

import java.util.Arrays;
import java.util.LinkedList;

public class MoveEvaluation {

    private final YellowRealm yellowRealm;
    private final RedRealm redRealm;
    private final Move[][] redRealmMoveGrid;
    private final GreenRealm greenRealm;
    private final Move[][] greenRealmMoveGrid;
    private final LinkedList<Move> pastMoves;
    private final MagentaRealm magentaRealm;
    private Realm[] realms;
    private AIPlayer player;
    private final BlueRealm blueRealm;
    private final GUIGameController guiGameController;

    public MoveEvaluation(AIPlayer player, LinkedList<Move> pastMoves, GUIGameController guiGameController) {
        Realm[] realms = player.getRealms();
        applyPastMoves(pastMoves,realms);
        this.guiGameController=guiGameController;
        this.pastMoves = pastMoves;
        this.magentaRealm = (MagentaRealm) realms[3];
        this.yellowRealm = (YellowRealm) realms[4];
        this.redRealm = (RedRealm) realms[0];
        Move[] redMoves = redRealm.getRealmMoves();
        Object[] rewards = redRealm.getCollectibles();
        redRealmMoveGrid = new Move[4][4];
        greenRealmMoveGrid = new Move[3][4];
        blueRealm = (BlueRealm) realms[2];
        for (int c = 0; c < redRealm.getRealmMoves().length; c++) {
            Move move = redMoves[c];
            int row = 0;
            for (int i = 0; i < 4; i++) {
                Object temp = ((Dragon) move.getCreature()).getHealth()[i];
                if (temp instanceof Integer) {
                    if (move.getDice().getValue() == (Integer) temp) {
                        row = i;
                        break;
                    }
                }
            }
            int col = ((Dragon) move.getCreature()).getDragonNumber() - 1;
            redRealmMoveGrid[row][col] = move;
        }
        greenRealm = (GreenRealm) realms[1];
        Move[] greenMoves = greenRealm.getRealmMoves();
        for (int i = 0; i < greenMoves.length; i++) {
            Move move = greenMoves[i];
            int row = (move.getDice().getValue() - 1) / 4;
            int col = (move.getDice().getValue() - 1) % 4;
            greenRealmMoveGrid[row][col] = move;

        }
    }

    public void applyPastMoves(LinkedList<Move> pastMoves,Realm[] realms){
        for (Move m : pastMoves) {
            Realm realm = realms[m.getDice().getRealm().ordinal()];
            Move[] realmMoves = realm.getRealmMoves();
            for (Move realmMove : realmMoves) {
                if (realmMove.equals(m)) {
                    realm.attack(realmMove);
                    realm.checkReward();
                    realm.getReward();
                }
            }

        }
    }
    public double evaluateYellowBonusHelper(int i,Collectibles[] rewards){
        if(i==11){
            return 0;
        }
        pastMoves.add(new Move(new YellowDice(6),new Lion()));
        AIPlayer helperPlayer=new AIPlayer("Helper Player");
        MoveEvaluation moveEvaluation=new MoveEvaluation(helperPlayer,pastMoves,guiGameController);
        return ((double) 1 /i)*moveEvaluation.getRewardEvaluation(rewards[i])+evaluateYellowBonusHelper(++i,rewards);
    }

    public Move[][] getRedRealmMoveGrid() {
        return redRealmMoveGrid;
    }
    public double evaluateRedMove(Move move) {
        int row = 0;
        for (int i = 0; i < 4; i++) {
            Object temp = ((Dragon) move.getCreature()).getHealth()[i];
            if (temp instanceof Integer) {
                if (move.getDice().getValue() == (Integer) temp) {
                    row = i;
                    break;
                }
            }
        }
        int col = ((Dragon) move.getCreature()).getDragonNumber() - 1;
        Object[] redRealmRewards = redRealm.getCollectibles();
        int noRemainingMovesRow = 0;
        for (int i = 0; i < 4; i++) {
            if (redRealmMoveGrid[row][i] != null && !redRealmMoveGrid[row][i].isExecuted()) {
                noRemainingMovesRow++;
            }
        }
        int noRemainingMovesCol = 0;
        for (int i = 0; i < 4; i++) {
            if (redRealmMoveGrid[i][col] != null && !redRealmMoveGrid[i][col].isExecuted()) {
                noRemainingMovesCol++;
            }
        }
        int noRemainingMovesDiagonal = 0;
        Collectibles diagonalReward = null;
        if (row == col) {
            //Diagonal Reward exists
            if (redRealmRewards[4] != null) {
                diagonalReward = (Collectibles) redRealmRewards[4];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (i == j) {
                            if (redRealmMoveGrid[i][j] != null && !redRealmMoveGrid[i][j].isExecuted()) {
                                noRemainingMovesDiagonal++;

                            }
                        }
                    }
                }
            }
        }
        LinkedList<Move> newPastMoves = new LinkedList<>(pastMoves);
        AIPlayer newHelperPlayer1=new AIPlayer("HelperPlayer1");
        newPastMoves.add(move);
        MoveEvaluation moveEvaluation2=new MoveEvaluation(newHelperPlayer1,newPastMoves,guiGameController);
        Collectibles rowReward = null;
        if (redRealmRewards[row] != null && redRealmRewards[row] instanceof Collectibles) {
            rowReward = (Collectibles) redRealmRewards[row];
        }
        int[] dragonsScore = redRealm.getDragonsScore();
        int score = dragonsScore[col];
        double rewardWeight = 0;
        if (rowReward != null) {
            rewardWeight = getRewardEvaluation(rowReward,newPastMoves);
        }
        double scoreWeight = ((double) 1 / noRemainingMovesCol) * score;
        double moveWeight = scoreWeight + rewardWeight * ((double) 1 / noRemainingMovesRow);
        if (diagonalReward != null) {
            double diagonalRewardWeight = getRewardEvaluation(diagonalReward,newPastMoves);
            moveWeight += diagonalRewardWeight * ((double) 1 / noRemainingMovesDiagonal);
        }
        //diagonalRewardWeight*((double) 1 /noRemainingMovesDiagonal)
        return moveWeight;
    }
//    public LinkedList<Move> cloneMoves(LinkedList<Move> pastMoves){
//        LinkedList<Move> newMoves=new LinkedList<>();
//        for(int i=0;i<pastMoves.size();i++){
//            Dice die;
//            if(pastMoves.get(i).getDice() instanceof RedDice){
//                die=new RedDice(pastMoves.get(i).getDice().getValue());
//                Dragon dragon= (Dragon) redRealm.getCreature(die);
//                newMoves.add(new Move(die,dragon));
//            }
//            else{
//                if(pastMoves.get(i).getDice() instanceof GreenDice){
//                    die=new GreenDice(pastMoves.get(i).getDice().getValue());
//                    Guardian gaia= (Guardian) greenRealm.getCreature(die);
//                    newMoves.add(new Move(die,gaia));
//                }
//                else{
//                    if(pastMoves.get(i).getDice() instanceof BlueDice){
//                        die=new BlueDice(pastMoves.get(i).getDice().getValue());
//                        Serpent serpent= (Serpent) blueRealm.getCreature(die);
//                        newMoves.add(new Move(die,serpent));
//                    }
//                    else{
//                        if(pastMoves.get(i).getDice() instanceof MagentaDice){
//                            die=new MagentaDice(pastMoves.get(i).getDice().getValue());
//                            Phoenix phoenix = (Phoenix) magentaRealm.getCreature(die);
//                            newMoves.add(new Move(die,phoenix));
//                        }
//                        else{
//                            if(pastMoves.get(i).getDice() instanceof YellowDice){
//                                die=new YellowDice(pastMoves.get(i).getDice().getValue());
//                                Lion dragon= (Dragon) redRealm.getCreature(die);
//                                newMoves.add(new Move(die,dragon));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }
    public Move[][] getGreenRealmMoveGrid() {
        return greenRealmMoveGrid;
    }

    public double evaluateGreenMove(Move move) {
        int row = (move.getDice().getValue() - 1) / 4;
        int col = (move.getDice().getValue() - 1) % 4;
        Object[] rowRewards = greenRealm.getRowRewards();
        Object[] colRewards = greenRealm.getColRewards();
        int noRemainingMovesRow = 0;
        for (int i = 0; i < colRewards.length; i++) {
            if (greenRealmMoveGrid[row][i] != null && !greenRealmMoveGrid[row][i].isExecuted()) {
                noRemainingMovesRow++;
            }
        }
        int noRemainingMovesCol = 0;
        for (int i = 0; i < rowRewards.length; i++) {
            if (greenRealmMoveGrid[i][col] != null && !greenRealmMoveGrid[i][col].isExecuted()) {
                noRemainingMovesCol++;
            }
        }
        Collectibles rowReward = null;
        if (rowRewards[row] != null && rowRewards[row] instanceof Collectibles) {
            rowReward = (Collectibles) rowRewards[row];
        }
        Collectibles colReward = null;
        if (colRewards[col] != null && colRewards[col] instanceof Collectibles) {
            colReward = (Collectibles) colRewards[col];
        }
        double rowRewardWeight = 0;
        if (rowReward != null) {
            rowRewardWeight = getRewardEvaluation(rowReward);
        }
        double colRewardWeight = 0;
        if (colReward != null) {
            colRewardWeight = getRewardEvaluation(colReward);
        }
        double scoreWeight = greenRealm.getFakeScore(move);
        double moveWeight = scoreWeight + rowRewardWeight * ((double) 1 / noRemainingMovesRow) + colRewardWeight * ((double) 1 / noRemainingMovesCol);
        return moveWeight;

    }

    public double evaluateBlueMove(Move move) {
        Collectibles[] rewards = blueRealm.getRewardsProperties();
        int hitCount = blueRealm.getHitcount();
        double rewardWeight = 0;
        for (int i = hitCount; i < rewards.length; i++) {
            if (rewards[i] != null) {
                Collectibles reward = rewards[i];
                rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward);
            }
        }
        int scoreWeight = blueRealm.getFakeScore(move);
        double moveWeight = scoreWeight + rewardWeight;
        return moveWeight;
    }

    public double evaluateMagentaMove(Move move) {
        Collectibles[] rewards = magentaRealm.getRewardsProperties();
        int hitCount = magentaRealm.getCounterHits();
        double rewardWeight = 0;
        for (int i = hitCount; i < rewards.length; i++) {
            if (rewards[i] != null) {
                Collectibles reward = rewards[i];
                rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward);
            }
        }
        int scoreWeight = magentaRealm.getFakeScore(move);
        double moveWeight = scoreWeight + rewardWeight;
        return moveWeight;
    }

    public double evaluateYellowMove(Move move) {
        if(yellowRealm.isRealmAvailable()){
            Collectibles[] rewards = yellowRealm.getRewardsProperties();
            int hitCount = yellowRealm.getCountHits();
            LinkedList<Move> newPastMoves=new LinkedList<>(pastMoves);
            double rewardWeight = 0;
            for (int i = hitCount; i < rewards.length; i++) {
                newPastMoves.add(move)
                if (rewards[i] != null) {
                    Collectibles reward = rewards[i];
                    rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward,newPastMoves);
                }
            }
            int scoreWeight = yellowRealm.getFakeScore(move);
            double moveWeight = scoreWeight + rewardWeight;
            return moveWeight;
        }

    }

    public double getRewardEvaluation(Collectibles collectible,LinkedList<Move> currentMoves) {

        if (collectible instanceof ArcaneBoost) {
            // return a value specific to ArcaneBoost
            return 15;
        } else if (collectible instanceof TimeWarp) {
            // return a value specific to TimeWarp
            return 0;
        } else if (collectible instanceof ElementalCrest) {
            // return a value specific to ElementalCrest
            return 30;
        } else if (collectible instanceof ColorBonus) {
            // return a value specific to colorBonus
            GameColor colorBonusColor = ((ColorBonus) collectible).getColor();
            switch (colorBonusColor) {
                case RED:
                    return evaluateRedBonusWeight(currentMoves);
                case BLUE:
                    return evaluateBlueBonusWeight(currentMoves);
                case GREEN:
                    return evaluateGreenBonusWeight(currentMoves);
                case YELLOW:
                    return evaluateYellowBonusWeight(currentMoves);
                case MAGENTA:
                    return evaluateMagentaBonusWeight(currentMoves);
                default:
                    return 0;
            }
        }
        return 0;
    }
    public double evaluateRedBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        noWorlds++;
        if(noWorlds>limit){
            return 0;
        }
        Move[] possibleMoves=moveEvaluation2.redRealm.getRealmMoves();
        double maxWeight=0;
        double tempWeight=0;
        for(int i=0;i<possibleMoves.length;i++){
            tempWeight=moveEvaluation2.evaluateRedMove(possibleMoves[i]);
            if(tempWeight>maxWeight){
                maxWeight=tempWeight;
            }
        }
        return maxWeight;
    }


    public double evaluateYellowBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        noWorlds++;
        if(noWorlds>limit){
            return 0;
        }
        return moveEvaluation2.evaluateYellowMove(new Move(new YellowDice(6),new Lion()));
    }
    public double evaluateMagentaBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        noWorlds++;
        if(noWorlds>limit){
            return 0;
        }
        return moveEvaluation2.evaluateMagentaMove(new Move(new MagentaDice(6),new Phoenix()));
    }
    public double evaluateBlueBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        noWorlds++;
        if(noWorlds>limit){
            return 0;
        }
        return moveEvaluation2.evaluateBlueMove(new Move(new BlueDice(6),blueRealm.getCreature(new BlueDice(6))));
    }
    public double evaluateGreenBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        noWorlds++;
        if(noWorlds>limit){
            return 0;
        }
        Move[] possibleMoves=moveEvaluation2.greenRealm.getRealmMoves();
        double maxWeight=0;
        double tempWeight=0;
        for(int i=0;i<possibleMoves.length;i++){
            tempWeight=moveEvaluation2.evaluateGreenMove(possibleMoves[i]);
            if(tempWeight>maxWeight){
                maxWeight=tempWeight;
            }
        }
        return maxWeight;
    }
    public static int noWorlds;
    public static final int limit=6;

}

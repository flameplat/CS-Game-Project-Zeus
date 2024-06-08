package game.engine;

import game.collectibles.*;
import game.creatures.Dragon;
import game.dice.YellowDice;
import game.realms.*;
import game.utilities.GameColor;

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

    public MoveEvaluation(AIPlayer player, LinkedList<Move> pastMoves) {
        Realm[] realms = player.getRealms();
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
        Collectibles rowReward = null;
        if (redRealmRewards[row] != null && redRealmRewards[row] instanceof Collectibles) {
            rowReward = (Collectibles) redRealmRewards[row];
        }
        int[] dragonsScore = redRealm.getDragonsScore();
        int score = dragonsScore[col];
        double rewardWeight = 0;
        if (rowReward != null) {
            rewardWeight = getRewardEvaluation(rowReward);
        }
        double scoreWeight = ((double) 1 / noRemainingMovesCol) * score;
        double moveWeight = scoreWeight + rewardWeight * ((double) 1 / noRemainingMovesRow);
        if (diagonalReward != null) {
            double diagonalRewardWeight = getRewardEvaluation(diagonalReward);
            moveWeight += diagonalRewardWeight * ((double) 1 / noRemainingMovesDiagonal);
        }
        //diagonalRewardWeight*((double) 1 /noRemainingMovesDiagonal)
        return moveWeight;
    }

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
        Collectibles[] rewards = yellowRealm.getRewardsProperties();
        int hitCount = yellowRealm.getCountHits();
        double rewardWeight = 0;
        for (int i = hitCount; i < rewards.length; i++) {
            if (rewards[i] != null) {
                Collectibles reward = rewards[i];
                rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward);
            }
        }
        int scoreWeight = yellowRealm.getFakeScore(move);
        double moveWeight = scoreWeight + rewardWeight;
        return moveWeight;
    }

    public double getRewardEvaluation(Collectibles collectible) {
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
                    return 20;
                case BLUE:
                    return 11;
                case GREEN:
                    return 10;
                case YELLOW:
                    return 15;
                case MAGENTA:
                    return 12;
                default:
                    return 0;
            }
        }
        return 0;
    }
    public double evaluateColorBonusWeight(GameColor color){
        evaluateColorBonusWeightHelper(color,0);
    }
    public double evaluateColorBonusWeightHelper(GameColor color,int i){

    }
    //Gets updated on the first call of evaluation
    private LinkedList<Move> redMoves;
    private LinkedList<Move> greenMoves;
    private int blueI;
    private int magentaI;
    private int yellowI;
    public double evaluateYellowBonusWeight(){
        if(yellowI==11){
            return 0;
        }
        yellowI++;
        return evaluateYellowMove(new Move(new YellowDice(6),yellowRealm.getCreature((new YellowDice(6)))));
    }
    public double evaluateMagentaBonusWeight(){

    }


}

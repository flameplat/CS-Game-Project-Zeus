package game.engine;

import game.collectibles.*;
import game.creatures.*;
import game.dice.*;
import game.gui.GUIGameController;
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

    //This limit represents the hardness level of the AI, ranging from 0 to Integer.MAX_VALUE. The higher the number, the harder the AI.
    public static final int limit=500;
    public static int MAX_NO_WORLDS_INITIALIZED=0;
    public static int redWorlds=0;
    public static int greenWorlds = 0;
    public static int blueWorlds = 0;
    public static int yellowWorlds = 0;
    public static int magentaWorlds=0;
    private final Realm[] realms;
    private final AIPlayer player;
    private final BlueRealm blueRealm;
    private  GUIGameController guiGameController;

    /**
     * Represents the evaluation of possible moves for an AI player.
     *
     * @param player            The AI player making the move.
     * @param pastMoves         The list of past moves made by the player.
     * @param guiGameController The GUI game controller.
     */
    public MoveEvaluation(AIPlayer player, LinkedList<Move> pastMoves, GUIGameController guiGameController) {
        this.player=player;
        this.realms = player.getRealms();
        applyPastMoves(pastMoves,realms);
        this.guiGameController=guiGameController;
        this.pastMoves = pastMoves;
        this.magentaRealm = (MagentaRealm) realms[3];
        this.yellowRealm = (YellowRealm) realms[4];
        this.redRealm = (RedRealm) realms[0];
        Move[] redMoves = redRealm.getRealmMoves();
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
        for (Move move : greenMoves) {
            int row = (move.getDice().getValue() - 1) / 4;
            int col = (move.getDice().getValue() - 1) % 4;
            greenRealmMoveGrid[row][col] = move;

        }
    }
    /**
     * Adds a move to the pastMoves list.
     *
     * @param move the move to be added
     */
    public void addMove(Move move){
        pastMoves.add(move);
    }
    /**
     * Sets the GUI game controller for the MoveEvaluation class.
     *
     * @param guiGameController The GUI game controller object to be set.
     */
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }

    /**
     * Resets the count of different worlds in MoveEvaluation class to zero.
     */
    public static void resetNoWorlds(){
        MoveEvaluation.blueWorlds=0;
        MoveEvaluation.redWorlds = 0;
        MoveEvaluation.magentaWorlds=0;
        MoveEvaluation.yellowWorlds = 0;
        MoveEvaluation.greenWorlds = 0;
    }
    /**
     * Applies the past moves to the given realms.
     *
     * @param pastMoves the list of past moves to be applied
     * @param realms    the array of realms to apply the moves to
     */
    public void applyPastMoves(LinkedList<Move> pastMoves,Realm[] realms){
        for (Move m : pastMoves) {
            Realm realm = realms[m.getDice().getRealm().ordinal()];
            realm.attack(new Move(m));
            realm.checkReward();
            realm.getReward();
        }
    }

    /**
     * Evaluates the move for the red realm.
     *
     * @param move The move to be evaluated.
     * @return The evaluation value.
     */
    public double evaluateRedMove(Move move) {
        if(redRealm.isRealmAvailable()){
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
            newPastMoves.add(new Move(move));
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
        return 0;
    }


    /**
     * Calculates the evaluation score for a green move.
     *
     * @param move The move to be evaluated.
     * @return The evaluation score for the move.
     */
    public double evaluateGreenMove(Move move) {
        if(greenRealm.isRealmAvailable()){
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
            LinkedList<Move> newPastMoves=new LinkedList<>(pastMoves);
            newPastMoves.add(new Move(move));
            double rowRewardWeight = 0;
            if (rowReward != null) {
                rowRewardWeight = getRewardEvaluation(rowReward,newPastMoves);
            }
            double colRewardWeight = 0;
            if (colReward != null) {
                colRewardWeight = getRewardEvaluation(colReward,newPastMoves);
            }
            double scoreWeight = greenRealm.getFakeScore(move);
            return scoreWeight + rowRewardWeight * ((double) 1 / noRemainingMovesRow) + colRewardWeight * ((double) 1 / noRemainingMovesCol);
        }
        return 0;
    }

    /**
     * Evaluates the value of a blue move based on the current state of the blue realm.
     *
     * @param move The move to be evaluated
     * @return The evaluation value of the move
     */
    public double evaluateBlueMove(Move move) {
        if(blueRealm.isRealmAvailable()){
            Collectibles[] rewards = blueRealm.getCollectibles();
            int hitCount = blueRealm.getHitcount();
            double rewardWeight = 0;
            LinkedList<Move> newPastMoves=new LinkedList<>(pastMoves);
            for (int i = hitCount; i < rewards.length; i++) {
                newPastMoves.add(new Move(blueRealm.getRealmMoves()[blueRealm.getRealmMoves().length-1]));
                if (rewards[i] != null) {
                    Collectibles reward = rewards[i];
                    rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward,newPastMoves);
                }
            }
            int scoreWeight = blueRealm.getFakeScore(move);
            return scoreWeight + rewardWeight;
        }
        return 0;
    }

    /**
     * Evaluates the move for the Magenta realm.
     *
     * @param move The move to evaluate.
     * @return The evaluation score for the move.
     */
    public double evaluateMagentaMove(Move move) {
        if(magentaRealm.isRealmAvailable()){
            Collectibles[] rewards = magentaRealm.getCollectibles();
            int hitCount = magentaRealm.getCounterHits();
            double rewardWeight = 0;
            LinkedList<Move> newPastMoves=new LinkedList<>(pastMoves);
            for (int i = hitCount; i < rewards.length; i++) {
                newPastMoves.add(new Move(move));
                if (rewards[i] != null) {
                    Collectibles reward = rewards[i];
                    rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward,newPastMoves);
                }
            }
            int scoreWeight = magentaRealm.getFakeScore(move);
            return scoreWeight + rewardWeight;
        }
        return 0;

    }

    /**
     * Evaluates the yellow move based on the current state of the yellow realm.
     * Adds the move to the list of past moves and calculates the reward weight based on collected rewards.
     * Calculates the score weight based on the fake score of the move.
     *
     * @param move The move to be evaluated.
     * @return The evaluation of the yellow move.
     */
    public double evaluateYellowMove(Move move) {
        if(yellowRealm.isRealmAvailable()){
            Collectibles[] rewards = yellowRealm.getCollectibles();
            int hitCount = yellowRealm.getCountHits();
            LinkedList<Move> newPastMoves=new LinkedList<>(pastMoves);
            double rewardWeight = 0;
            for (int i = hitCount; i < rewards.length; i++) {
                newPastMoves.add(new Move(move));
                if (rewards[i] != null) {
                    Collectibles reward = rewards[i];
                    rewardWeight += ((double) 1 / (i - hitCount + 1)) * getRewardEvaluation(reward,newPastMoves);
                }
            }
            int scoreWeight = yellowRealm.getFakeScore(move);
            return scoreWeight + rewardWeight;
        }
        return 0;
    }

    /**
     * Evaluates the reward value for a given collectible and current moves.
     *
     * @param collectible   the collectible to evaluate the reward value for
     * @param currentMoves  the list of current moves
     * @return the reward value for the collectible and current moves
     */
    public double getRewardEvaluation(Collectibles collectible,LinkedList<Move> currentMoves) {
        if (collectible instanceof ArcaneBoost) {
            // return a value specific to ArcaneBoost
            return 10;
        } else if (collectible instanceof TimeWarp) {
            // return a value specific to TimeWarp
            return 0;
        } else if (collectible instanceof ElementalCrest) {
            int minScore = realms[0].getTotalScore();
            for (int i = 0; i < 5; i++) {
                if (realms[i].getTotalScore() < minScore) {
                    minScore = realms[i].getTotalScore();
                }
            }
            return (player.gameScore.getTotalElementalCrests() + 1) *(minScore)
                    * (7- guiGameController.gameStatus.getRound());

        }
        else if (collectible instanceof ColorBonus) {
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
    /**
     * Evaluates the weight of the red bonus for a given list of moves.
     *
     * @param currentMoves A linked list of moves.
     * @return The weight of the red bonus as a double value.
     */
    public double evaluateRedBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        redWorlds++;
        if(redWorlds>limit || !moveEvaluation2.redRealm.isRealmAvailable()){
            return 0;
        }
        if(MAX_NO_WORLDS_INITIALIZED<redWorlds){
            MAX_NO_WORLDS_INITIALIZED=redWorlds;
        }
        Move[] possibleMoves=helperPlayer.getRealm(GameColor.RED).getRealmMoves();
        double maxWeight=-1;
        double tempWeight;
        for (Move move : possibleMoves) {
            tempWeight = moveEvaluation2.evaluateRedMove(new Move(move));
            if (tempWeight > maxWeight) {
                maxWeight = tempWeight;
            }
        }
        return maxWeight;
    }

    /**
     * Evaluates the weight of the yellow bonus in the given list of moves.
     * @param currentMoves The list of moves to evaluate.
     * @return The weight of the yellow bonus.
     */
    public double evaluateYellowBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        yellowWorlds++;
        if(yellowWorlds>limit || !moveEvaluation2.yellowRealm.isRealmAvailable()){
            return 0;
        }
        if(MAX_NO_WORLDS_INITIALIZED<yellowWorlds){
            MAX_NO_WORLDS_INITIALIZED=yellowWorlds;
        }
        Dice yellowDice = new YellowDice(6);
        Move move = new Move(yellowDice, new Lion());
        return moveEvaluation2.evaluateYellowMove(move);
    }
    /**
     *
     */
    public double evaluateMagentaBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        magentaWorlds++;

        if(magentaWorlds>limit || !moveEvaluation2.magentaRealm.isRealmAvailable() ){
            return 0;
        }
        if(MAX_NO_WORLDS_INITIALIZED<magentaWorlds){
            MAX_NO_WORLDS_INITIALIZED=magentaWorlds;
        }
        return moveEvaluation2.evaluateMagentaMove(new Move(new MagentaDice(6),new Phoenix()));
    }
    /**
     * Resets the value of MAX_NO_WORLDS to 0, indicating that it has not been initialized.
     * This method sets the value of the static variable MAX_NO_WORLDS, which is used for move evaluation,
     * to 0. This indicates that the variable has not been initialized and should be set to a proper value
     * before it is used.
     */
    public static void resetMAX_NO_WORLDS(){
        MoveEvaluation.MAX_NO_WORLDS_INITIALIZED=0;
    }
    /**
     * Evaluates the blue bonus weight for a given list of moves.
     *
     * @param currentMoves the list of moves to evaluate
     * @return the blue bonus weight
     */
    public double evaluateBlueBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        blueWorlds++;

        if(blueWorlds>limit || !moveEvaluation2.blueRealm.isRealmAvailable()){
            return 0;
        }
        if(MAX_NO_WORLDS_INITIALIZED<blueWorlds){
            MAX_NO_WORLDS_INITIALIZED=blueWorlds;
        }
        return moveEvaluation2.evaluateBlueMove(new Move(new BlueDice(6),blueRealm.getCreature(new BlueDice(6))));
    }
    /**
     * Evaluates the weight of the green bonus in the given list of moves.
     *
     * @param currentMoves the list of moves to evaluate
     * @return the weight of the green bonus
     */
    public double evaluateGreenBonusWeight(LinkedList<Move> currentMoves){
        AIPlayer helperPlayer=new AIPlayer("HelperPlayer");
        MoveEvaluation moveEvaluation2=new MoveEvaluation(helperPlayer,currentMoves,guiGameController);
        greenWorlds++;

        if(greenWorlds>limit || !greenRealm.isRealmAvailable()){
            return 0;
        }
        if(MAX_NO_WORLDS_INITIALIZED<greenWorlds){
            MAX_NO_WORLDS_INITIALIZED=greenWorlds;
        }
        Move[] possibleMoves=moveEvaluation2.greenRealm.getRealmMoves();
        double maxWeight=-1;
        double tempWeight;
        for (Move move : possibleMoves) {
            tempWeight = moveEvaluation2.evaluateGreenMove(move);
            if (tempWeight > maxWeight) {
                maxWeight = tempWeight;
            }
        }

        return maxWeight;
    }


    /**
     * Calculates the weight of a given move.
     *
     * @param move The move to calculate the weight for.
     * @return The weight of the move.
     */
    public double getWeightOfMove(Move move){
        double weight = 0;
        GameColor realm = move.getDice().getRealm();
        if(realm == GameColor.RED ){
            weight = evaluateRedMove(move);
        }
        if(realm == GameColor.GREEN){
            weight = evaluateGreenMove(move);
        }
        if(realm == GameColor.BLUE){
            weight = evaluateBlueMove(move);
        }
        if(realm == GameColor.MAGENTA){
            weight = evaluateMagentaMove(move);
        }
        if(realm == GameColor.YELLOW){
            weight = evaluateYellowMove(move);
        }
        return weight;
    }
    /**
     * Retrieves the move with the highest weight based on a given dice.
     *
     * @param die The dice value to be considered when searching for the move.
     * @return The move with the highest weight according to the given dice. If no moves are
     *         available, returns null.
     */
    public Move getMoveOfHighestWeight(Dice die){
        double selectedWeight=-1;
        double tempWeight;
        Move selectedMove=null;
        Move[] possibleMoves = guiGameController.getPossibleMovesForADie(player, die);
        for (Move move : possibleMoves) {
            tempWeight = getWeightOfMove(move);
            if (selectedWeight < tempWeight) {
                selectedWeight = tempWeight;
                selectedMove = move;
            }
        }
        return selectedMove;
    }
    /**
     * Calculates the weight of a given dice.
     *
     * @param die The dice for which the weight needs to be calculated
     * @return The weight of the dice
     */
    public double getWeightOfDice(Dice die){
        double selectedWeight=-1;
        double tempWeight;
        Move[] possibleMoves = guiGameController.getPossibleMovesForADie(player, die);
        for (Move move : possibleMoves) {
            tempWeight = getWeightOfMove(move);
            if (selectedWeight < tempWeight) {
                selectedWeight = tempWeight;

            }
        }
        return selectedWeight*getTurnWeight(die);
    }
    /**
     * Calculates the turn weight for a given dice.
     *
     * @param selectedDice The dice for which to calculate the turn weight.
     * @return The turn weight for the given dice.
     */
    private double getTurnWeight(Dice selectedDice) {
        int[] arrayOfAvailableDice = new int[guiGameController.getAvailableDice().length];
        for(int i=0; i<guiGameController.getAvailableDice().length;i++){
            arrayOfAvailableDice[i] = guiGameController.getAvailableDice()[i].getValue();
        }
        Arrays.sort(arrayOfAvailableDice);
        double value;
        int Turn= guiGameController.gameStatus.getTurn();
        CurrentStatus status = guiGameController.gameStatus.getGameStatus();
        if(status == CurrentStatus.ARCANE_BOOST || status == CurrentStatus.PASSIVE_TURN || Turn == 3){
            value = 1;
        }
        else{
            if(arrayOfAvailableDice.length == 6){
                value = 1-(0.15*(findIndex(arrayOfAvailableDice,selectedDice.getValue())));
            }
            else if(arrayOfAvailableDice.length == 5){
                value = 1-(0.2*(findIndex(arrayOfAvailableDice,selectedDice.getValue())));
            }
            else if(arrayOfAvailableDice.length == 4){
                value = 1-(0.25*(findIndex(arrayOfAvailableDice,selectedDice.getValue())));
            }
            else if(arrayOfAvailableDice.length == 3){
                value = 1-(0.35*(findIndex(arrayOfAvailableDice,selectedDice.getValue())));
            }
            else if(arrayOfAvailableDice.length == 2){
                value = 1-(0.5*(findIndex(arrayOfAvailableDice,selectedDice.getValue())));
            }
            else
                value = 1;
        }
        return value;
    }

    /**
     * Finds the index of a specified value in an array.
     *
     * @param array The array to search in.
     * @param value The value to find the index of.
     * @return The index of the specified value in the array, or -1 if the value is not found.
     */
    //helper method used in getTurnWeight method
    private static int findIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns an array of weights corresponding to each dice in the given dice array.
     *
     * @param diceArray an array of Dice objects
     * @return an array of weights, where each weight corresponds to a die in the dice array
     */
    public double[] getWeightOfAllDice(Dice [] diceArray){
        double [] weights = new double[diceArray.length];
        //System.out.println("Move Evaluation");
        for(int i=0;i<weights.length;i++){
            weights[i] = getWeightOfDice(diceArray[i]);
            //System.out.println(diceArray[i]+": "+weights[i]);
        }
        return weights;
    }
    /**
     */
    public double getWeightOfBestMove(Dice[] diceArray){
        double[] weights = getWeightOfAllDice(diceArray);
        int bestWeight =-1;
        int tempWeight;
        for (double weight : weights) {
            tempWeight = (int) weight;
            if (bestWeight < tempWeight) {
                bestWeight = tempWeight;
            }
        }
        return bestWeight;

    }
    /**
     * Finds the best move based on the weight of the given dice array.
     *
     * @param diceArray an array of Dice objects
     * @return the best Move object with the highest weight
     */
    public Move bestMove(Dice [] diceArray){
        double[] weights = getWeightOfAllDice(diceArray);
        int bestWeight =-1;
        int tempWeight;
        Dice bestDice = null;
        Move bestMove;
        for(int i=0;i<weights.length;i++){
            tempWeight = (int) weights[i];
            if(bestWeight<tempWeight){
                bestWeight = tempWeight;
                bestDice = diceArray[i];
            }
        }
        bestMove = getMoveOfHighestWeight(bestDice);
        return bestMove;
    }
    /**
     * Finds the best dice in an array of dice, based on their weights.
     *
     * @param diceArray an array of Dice objects
     * @return the best Dice object with the highest weight
     */
    public Dice bestDice(Dice [] diceArray) {
        double[] weights = getWeightOfAllDice(diceArray);
        int bestWeight = -1;
        int tempWeight;
        Dice bestDice = null;

        for (int i = 0; i < weights.length; i++) {
            tempWeight = (int) weights[i];
            if (bestWeight < tempWeight) {
                bestWeight = tempWeight;
                bestDice = diceArray[i];
            }
        }
        return bestDice;
    }

    /**
     * Evaluates the color bonus weight based on the given game realm.
     *
     * @param realm the game color realm to evaluate the bonus weight
     * @return the evaluated color bonus weight
     */
    public double evaluateColorBonusWeight(GameColor realm) {
        double weight = 0;
        if(realm == GameColor.RED) {
            weight = evaluateRedBonusWeight(pastMoves);
            System.out.println("RedBonus: "+weight);
        } else if(realm == GameColor.GREEN) {
            weight = evaluateGreenBonusWeight(pastMoves);
            System.out.println("GreenBonus: "+weight);
        } else if(realm == GameColor.BLUE) {
            weight = evaluateBlueBonusWeight(pastMoves);
            System.out.println("BlueBonus: "+weight);
        } else if(realm == GameColor.MAGENTA) {
            weight = evaluateMagentaBonusWeight(pastMoves);
            System.out.println("MagentaBonus: "+weight);
        } else if(realm == GameColor.YELLOW) {
            weight = evaluateYellowBonusWeight(pastMoves);
            System.out.println("YellowBonus: "+weight);
        }
        return weight;
    }
}

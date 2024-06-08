package game.engine;

import game.dice.*;
import game.gui.GUIGameController;
import game.utilities.GameColor;

import java.util.LinkedList;
import java.util.Random;

public class AIPlayer extends Player{
    private Move selectedMove;
    private GUIGameController guiGameController;

    private LinkedList<Move> pastMoves;
    private MoveEvaluation moveEvaluation;

    public AIPlayer(String name){
        super();
        setName(name);
        isAI=true;
        pastMoves=new LinkedList<>();
        moveEvaluation =new MoveEvaluation(this,pastMoves);
    }
    // The AI player should call the methods of the GUI to select the die.
    public void selectDice(Dice[] diceArray){
        System.out.printf("During round %d in %s :%n",guiGameController.gameStatus.getRound(),guiGameController.gameStatus.getGameStatus());

        Dice selectedDie=diceArray[r.nextInt(diceArray.length)];
        Move[] possibleMoves=guiGameController.getPossibleMovesForADie(this,selectedDie);
        selectedMove=possibleMoves[r.nextInt(possibleMoves.length)];




        System.out.println("AI has chosen:  "+selectedDie);
        System.out.println("-".repeat(50));

        if (selectedDie instanceof RedDice) {
            guiGameController.redDiceButtonClick();
        } else if (selectedDie instanceof WhiteDice) {
            guiGameController.whiteDiceButtonClick();
        } else if (selectedDie instanceof YellowDice) {
            guiGameController.yellowDiceButtonClick();
        } else if (selectedDie instanceof GreenDice) {
            guiGameController.greenDiceButtonClick();
        } else if (selectedDie instanceof BlueDice) {
            guiGameController.blueDiceButtonClick();
        } else if (selectedDie instanceof MagentaDice) {
            guiGameController.magentaDiceButtonClick();
        } else {
            System.out.println("Unknown dice type selected.");
        }
    }
    public Move getSelectedMove() {
        return selectedMove;
    }
    public void playColorBonus(GameColor color,Move[] possibleMoves){
        double tempWeight;
        double selectedWeight = 0;
        for(int i=0; i<possibleMoves.length;i++){
            tempWeight = moveEvaluation.getWeightOfMove(possibleMoves[i]);
            if(selectedWeight<tempWeight){
                selectedWeight = tempWeight;
                selectedMove = possibleMoves[i];
            }
        }
        System.out.println("Color bonus: "+color+", selected move: "+selectedMove);
        guiGameController.makeMove(this,selectedMove);
    }
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController = guiGameController;
    }
    public boolean useTimeWarp(Dice[] dice){
        //Do your decision here
        if(moveEvaluation.getWeightOfAllDice(dice))
        return true;
    }
    public boolean useArcaneBoost(Dice[] dice){
        //Do your decision here
        return true;
    }
    public GameColor selectRealm(LinkedList<GameColor> availableRealms){
        GameColor [] remRealms = (GameColor[]) availableRealms.toArray();
        double chosenWeight =0;
        double tempWeight;
        GameColor chosenRealm = null;
        for(int i=0;i<remRealms.length;i++){
            tempWeight = moveEvaluation.evaluateColorBonusWeight(remRealms[i]);
            if(chosenWeight<tempWeight){
                chosenWeight = tempWeight;
                chosenRealm = remRealms[i];
            }
        }
        return chosenRealm;
    }
    /*Essence Bonus Player */
    void playEssenceBonus(){
        
    }

    public LinkedList<Move> getPastMoves() {
        return pastMoves;
    }

    public MoveEvaluation getRealmsDecision() {
        return moveEvaluation;
    }
}

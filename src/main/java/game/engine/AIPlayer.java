package game.engine;

import game.dice.*;
import game.gui.GUIGameController;
import game.utilities.GameColor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AIPlayer extends Player{
    private Move selectedMove;
    private GUIGameController guiGameController;
    private final Random r;
    public AIPlayer(String name){
        super();
        setName(name);
        r=new Random();
        isAI=true;
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
        selectedMove = possibleMoves[r.nextInt(possibleMoves.length)];
        System.out.println("Color bonus: "+color+", selected move: "+selectedMove);
        guiGameController.makeMove(this,selectedMove);
    }
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController = guiGameController;
    }
    public boolean useTimeWarp(Dice[] dice){
        //Do your decision here
        return true;
    }
    public boolean useArcaneBoost(Dice[] dice){
        //Do your decision here
        return true;
    }
    public GameColor selectRealm(LinkedList<GameColor> availableRealms){
        GameColor selectedRealm=availableRealms.get(r.nextInt(availableRealms.size()));
        System.out.println("Realm Selected: "+selectedRealm);
        return selectedRealm;
    }
}

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
        System.out.println(Arrays.toString(diceArray));
        Move[] possibleMoves;
        Dice selectedDie;
        int i=0;
        //Filter Available Dice
        do{
            selectedDie=diceArray[r.nextInt(diceArray.length)];
            possibleMoves=guiGameController.getPossibleMovesForADie(this,selectedDie);
            i++;
            if(i>500){
                System.out.println("AI stuck in loop");
            }
        }
        while (possibleMoves.length==0);

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
    public void playColorBonus(GameColor color){
        //You will only need to make a decision for red and green realm only
        Move[] moves=guiGameController.getAllPossibleMoves(this);
        List<Move> colorMoves = Arrays.stream(moves)
                .filter(move -> move.getDice().getRealm() == color)
                .collect(Collectors.toList());

        selectedMove = colorMoves.get(r.nextInt(colorMoves.size()));
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

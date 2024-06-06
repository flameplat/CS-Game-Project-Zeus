package game.engine;

import game.dice.*;
import game.gui.GUIGameController;
import game.gui.GreenBonusController;
import game.gui.RedRealmController;
import game.utilities.GameColor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AIPlayer extends Player{
    private Move selectedMove;
    private CLIGameController cliGameController;
    private GUIGameController guiGameController;
    private Random r;
    public AIPlayer(String name){
        super();
        setName(name);
        cliGameController=new CLIGameController();
        r=new Random();
        isAI=true;
    }
    // The AI player should call the methods of the GUI to select the die.
    // This will be much better for the other player to see what the AI is doing.
    public void selectDice(Dice[] diceArray){
        //Filter Available Dice
        List<Dice> availableDice = Arrays.stream(diceArray)
                .filter((dice -> cliGameController.getPossibleMovesForADie(this,dice).length!=0))
                .collect(Collectors.toList());
        if(availableDice.isEmpty()){
            return;
        }
        Dice selectedDie=availableDice.get(r.nextInt(availableDice.size()));
        Move[] possibleMoves=cliGameController.getPossibleMovesForADie(this,selectedDie);
        selectedMove=possibleMoves[r.nextInt(possibleMoves.length)];
        if (selectedDie instanceof RedDice) {
            guiGameController.redDiceButtonClick();
            //will call getSelected move after calling redDiceButtonClick()
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
        Move[] moves=cliGameController.getAllPossibleMoves(this);
        List<Move> colorMoves = Arrays.stream(moves)
                .filter(move -> move.getDice().getRealm() == color)
                .collect(Collectors.toList());
        selectedMove = colorMoves.get(r.nextInt(colorMoves.size()));
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
        return availableRealms.get(r.nextInt(availableRealms.size()));
    }
}

package game.engine;

import game.dice.*;
import game.gui.GUIGameController;
import game.utilities.GameColor;

import java.util.Arrays;
import java.util.LinkedList;

public class AIPlayer extends Player {
    private final MoveEvaluation moveEvaluation;
    private Move selectedMove;
    private GUIGameController guiGameController;

    /**
     * The AIPlayer class represents an AI LAMBDA λ model player in the game. It is a subclass of the Player class.
     *
     *   AAAAA   IIIII       L      AAAAA   M     M  BBBBB    DDDDD     AAAAA
     *  A     A    I         L     A     A  MM   MM  B     B  D    D   A     A
     *  AAAAAAA    I         L     AAAAAAA  M M M M  BBBBBB   D     D  AAAAAAA
     *  A     A    I         L     A     A  M  M  M  B     B  D    D   A     A
     *  A     A  IIIII       LLLLL A     A  M     M  BBBBB    DDDDD    A     A
     *
     */
    public AIPlayer(String name) {
        super();
        setName(name);
        // We could have used 'instanceof' to check if it is an AI or not but we don't like it cause it
        // violates the open-closed principle, if a new AI Model is added, we'll have to find all the 'instanceof' checks and update them
        isAI = true;
        LinkedList<Move> pastMoves = new LinkedList<>();
        moveEvaluation = new MoveEvaluation(this, pastMoves, guiGameController);
    }

    /**
     * Selects a dice from the given array based on the AI player's evaluation.
     *
     * @param diceArray an array of Dice objects
     */
    // The AI player should call the methods of the GUI to select the die.
    public void selectDice(Dice[] diceArray) {
        System.out.printf("During round %d in %s :%n", guiGameController.gameStatus.getRound(), guiGameController.gameStatus.getGameStatus());
        System.out.println(Arrays.toString(diceArray));
        selectedMove = moveEvaluation.bestMove(diceArray);
        Dice selectedDie = moveEvaluation.bestDice(diceArray);
        MoveEvaluation.resetNoWorlds();
        System.out.println(getName()+" has chosen:  " + selectedDie);
        System.out.println("-".repeat(50));
        System.out.println("Number of worlds initialized: " + MoveEvaluation.MAX_NO_WORLDS_INITIALIZED);
        MoveEvaluation.resetMAX_NO_WORLDS();
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
            System.err.println("Unknown dice type selected.");
        }

    }

    /**
     * Retrieves the selected move.
     *
     * @return the selected move
     */
    public Move getSelectedMove() {
        return selectedMove;
    }

    /**
     * Plays a color bonus by selecting the move with the highest weight from a given array of possible moves.
     * The weight of a move is determined by the color realm of the corresponding dice.
     *
     * @param color         The color bonus to play.
     * @param possibleMoves The array of possible moves to choose from.
     */
    public void playColorBonus(GameColor color, Move[] possibleMoves) {
        double tempWeight;
        double selectedWeight = -1;
        for (Move move : possibleMoves) {
            tempWeight = moveEvaluation.getWeightOfMove(move);
            if (selectedWeight < tempWeight) {
                selectedWeight = tempWeight;
                selectedMove = move;
            }
            MoveEvaluation.resetNoWorlds();
            MoveEvaluation.resetMAX_NO_WORLDS();
        }
        System.out.println("Color bonus: " + color + ", selected move: " + selectedMove);
        guiGameController.makeMove(this, selectedMove);
    }

    /**
     * Sets the GUI game controller for this AI player.
     *
     * @param guiGameController The GUI game controller to be set.
     */
    public void setGuiGameController(GUIGameController guiGameController) {
        this.guiGameController = guiGameController;
        moveEvaluation.setGuiGameController(guiGameController);
    }

    /**
     * Checks if the weight of the best move is less than 6.
     * If true, prints the dice array and the weight of the best move,
     * and returns true.
     * If false, returns false.
     *
     * @param dice an array of Dice objects representing the available dice
     * @return true if the weight of the best move is less than 6, false otherwise
     */
    public boolean useTimeWarp(Dice[] dice) {
        double weight = moveEvaluation.getWeightOfBestMove(dice);
        if (weight < 6) {
            System.out.println(Arrays.toString(dice));
            System.out.println("Use Time Warp, Weight of best move: " + weight);
            return true;
        } else
            return false;
    }

    /**
     * Determines if the AI player should use the Arcane Boost.
     *
     * @param dice The array of dice available to the AI player.
     * @return true if the AI player should use the Arcane Boost, false otherwise.
     */
    public boolean useArcaneBoost(Dice[] dice) {
        return moveEvaluation.getWeightOfBestMove(dice) > 10 || guiGameController.gameStatus.getRound() == 6;
    }

    /**
     * Selects a game realm from the given list of available realms based on the color bonus weight.
     *
     * @param availableRealms a linked list of available game realms
     * @return the selected game realm based on the highest color bonus weight
     */
    public GameColor selectRealm(LinkedList<GameColor> availableRealms) {
        GameColor[] remRealms = availableRealms.toArray(GameColor[]::new);
        double chosenWeight = -1;
        double tempWeight;
        GameColor chosenRealm = null;
        for (GameColor remRealm : remRealms) {
            tempWeight = moveEvaluation.evaluateColorBonusWeight(remRealm);
            if (chosenWeight < tempWeight) {
                chosenWeight = tempWeight;
                chosenRealm = remRealm;
            }
            MoveEvaluation.resetNoWorlds();
            MoveEvaluation.resetMAX_NO_WORLDS();
        }
        System.out.println("Chosen Realm: " + chosenRealm);
        return chosenRealm;
    }


    /**
     * Retrieves the MoveEvaluation object associated with the current move.
     * @return The MoveEvaluation object containing the evaluation of the move.
     */
    public MoveEvaluation getMoveEvaluation() {
        return moveEvaluation;
    }

}
package game.engine;

import game.dice.*;

public class GameBoard {
    private final Dice[] diceArray;
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;


    /**
     * Represents a game board for a two-player game.
     */
    // -----------------------Constructor-----------------------//
    public GameBoard() {
        player1 = new Player();
        player2 = new Player();
        player1.setPlayerStatus(PlayerStatus.ACTIVE);
        player2.setPlayerStatus(PlayerStatus.PASSIVE);
        diceArray = new Dice[]{new RedDice(), new GreenDice(), new BlueDice(), new MagentaDice(), new YellowDice(), new WhiteDice()};
    }

    /**
     *
     * Retrieves the player 1 object.
     *
     * @return the player 1 object.
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Sets the player 1 object in the GameBoard.
     *
     * @param player1 The player 1 object to be set.
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    // -----------------------Methods-----------------------//

    /**
     * Returns the player2 object.
     *
     * @return the player2 object
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets the player 2 for the game board.
     *
     * @param player2 the Player object representing player 2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Retrieves an array of dice objects from the game board.
     *
     * @return The array of dice objects.
     */
    public Dice[] getDice() {
        return diceArray;
    }
}

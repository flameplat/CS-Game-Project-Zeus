package game.engine;

public class GameStatus {
    /**
     * Gets the current game status, including round and turn information for the
     * current active player
     * return The current { GameStatus} object.
     */
    //--------------------------Attributes--------------------------//
    private CurrentStatus gameStatus;
    private int round;
    private int turn;

    //--------------------------Constructor--------------------------//
    public GameStatus() {
        round = 1;
        turn = 1;
    }

    //--------------------------Methods--------------------------//
    public CurrentStatus getGameStatus() {
        return gameStatus;

    }

    /**
     * Sets the game status to the specified status.
     *
     * @param status The new game status to be set.
     */
    public void setGameStatus(CurrentStatus status) {
        this.gameStatus = status;
    }

    /**
     * Increments the round number by 1.
     * This method is used to update the current round of the game.
     *
     * @return void
     */
    public void incrementRound() {
        round++;
    }

    /**
     * Resets the turn counter to 1.
     */
    public void resetTurn() {
        turn = 1;
    }

    /**
     * Increments the turn count by 1.
     */
    public void incrementTurn() {
        turn++;
    }

    /**
     * Returns the current round number.
     *
     * @return the current round number.
     */
    public int getRound() {
        return round;
    }

    /**
     * Returns the current turn number in the game.
     *
     * @return The current turn number.
     */
    public int getTurn() {
        return turn;
    }
    /**
     * Sets the round number for the game.
     *
     * @param round The round number to set.
     */
    public void setRound(int round){
        this.round=round;
    }

}

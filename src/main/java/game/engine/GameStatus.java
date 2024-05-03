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
        round=1;
        turn=1;
    }
    //--------------------------Methods--------------------------//
    public CurrentStatus getGameStatus() {
        return gameStatus;

    }


    public void incrementRound() {
        round++;
    }

    public void resetTurn() {
        turn = 1;
    }

    public void incrementTurn() {
        turn++;
    }


    public void setGameStatus(CurrentStatus status) {
        this.gameStatus = status;
    }

}

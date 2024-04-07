package game.engine;

public class GameStatus {
    /**
     * Gets the current game status, including round and turn information for the
     * current active player
     *   return The current { GameStatus} object.
     */
    private CurrentStatus gameStatus;
    private Player currentActivePlayer;
    private int round;
    private int turn;
    public GameStatus(){
        //
    }

    public CurrentStatus getGameStatus() {
        return gameStatus;
    }

    public int getRound() {
        return round;
    }

    public int getTurn() {
        return turn;
    }
    public void incrementRound(){
        round++;
    }
    public void resetTurn(){
        turn=0;
    }
    public void incrementTurn(){
        turn++;
    }
    public Player getCurrentActivePlayer(){
        return currentActivePlayer;
    }
    public void setGameStatus(CurrentStatus status){
        this.gameStatus=status;
    }
}

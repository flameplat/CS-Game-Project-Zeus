package game.engine;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;
    private CurrentStatus currentStatus;

    // -----------------------Constructor-----------------------//
    public GameBoard(Player player1,Player player2){
        this.player1=player1;
        this.player2=player2;
        currentStatus=CurrentStatus.IN_PROGRESS;
    }
    // -----------------------Methods-----------------------//
    //Display realm for active player
    public void displayAllRealms(){

    }
    public void displayAllPossibleMoves(Move[] moves){

    }
    public void displayMainDiceDeck(){

    }
    //Sets the active player
    public void setActivePlayer(Player player){

    }
    //Sets the passive player
    public void setPassivePlayer(Player player){

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void display(){
        //Display game board
    }

}

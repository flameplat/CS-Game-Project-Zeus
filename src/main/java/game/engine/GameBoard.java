package game.engine;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    Player passivePlayer;
    Player activePlayer;

    // -----------------------Constructor-----------------------//
    //Initialized Players, Forgotten Realm, and set up the main board and contains the main game components
    public GameBoard(Player player1){
        //
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
        this.activePlayer=player;
    }
    //Sets the passive player
    public void setPassivePlayer(Player player){
        this.passivePlayer=player;
    }
}

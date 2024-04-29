package game.engine;

import game.dice.Dice;
import game.realms.ForgottenRealm;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;
    private CurrentStatus currentStatus;
    private ForgottenRealm forgottenRealm;
    private Dice[] diceArray;

    // -----------------------Constructor-----------------------//
    public GameBoard(){
        currentStatus=CurrentStatus.IN_PROGRESS;
        forgottenRealm=new ForgottenRealm();
    }
    public void setPlayers(Player player1,Player player2){
        this.player1=player1;
        this.player2=player2;
    }
    // -----------------------Methods-----------------------//
    //Display realm for active player
    public void displayAllRealms(){

    }
    public void displayAllPossibleMoves(Move[] moves){

    }
    public void displayMainDiceDeck() {

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

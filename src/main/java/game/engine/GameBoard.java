package game.engine;

import game.dice.*;
import game.exceptions.MissingGameFilesException;
import game.realms.ForgottenRealm;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;
    private CurrentStatus currentStatus;
    private ForgottenRealm forgottenRealm;
    private Dice[] diceArray;

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public ForgottenRealm getForgottenRealm() {
        return forgottenRealm;
    }

    // -----------------------Constructor-----------------------//
    public GameBoard(){
        currentStatus=CurrentStatus.IN_PROGRESS;
        player1=new Player();
        player1=new Player();
        forgottenRealm=new ForgottenRealm();
        diceArray= new Dice[]{new RedDice(), new GreenDice(), new BlueDice(), new MagentaDice(), new YellowDice(), new WhiteDice()};
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
    public Dice[] getDice(){
        return diceArray;
    }
}

package game.engine;

import game.dice.*;
import game.exceptions.MissingGameFilesException;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;
    private CurrentStatus currentStatus;
    private Dice[] diceArray;

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }



    // -----------------------Constructor-----------------------//
    public GameBoard(){
        currentStatus=CurrentStatus.IN_PROGRESS;
        player1=new Player();
        player1=new Player();
        diceArray= new Dice[]{new RedDice(), new GreenDice(), new BlueDice(), new MagentaDice(), new YellowDice(), new WhiteDice()};
    }
    public void setPlayer1(Player player1){
        this.player1=player1;
    }
    public void setPlayer2(Player player2){
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

package game.engine;

import game.dice.*;

public class GameBoard {
    // -----------------------Attributes-----------------------//
    private Player player1;
    private Player player2;
    private final Dice[] diceArray;




    // -----------------------Constructor-----------------------//
    public GameBoard(){
        player1=new Player();
        player2=new Player();
        player1.setPlayerStatus(PlayerStatus.ACTIVE);
        player2.setPlayerStatus(PlayerStatus.PASSIVE);
        diceArray= new Dice[]{new RedDice(), new GreenDice(), new BlueDice(), new MagentaDice(), new YellowDice(), new WhiteDice()};
    }
    public void setPlayer1(Player player1){
        this.player1=player1;
    }
    public void setPlayer2(Player player2){
        this.player2=player2;
    }
    // -----------------------Methods-----------------------//

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Dice[] getDice(){
        return diceArray;
    }
}

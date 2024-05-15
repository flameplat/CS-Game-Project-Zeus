package game;


import game.engine.*;
import game.realms.*;
import game.utilities.Color;
import java.util.*;



public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        controller.startGame();
        GreenRealm x= (GreenRealm) controller.getActivePlayer().getRealm(Color.GREEN);
        Move[] moves=x.getRealmMoves();
        for(Move m:moves){
            System.out.println(m);
            x.attack(m);
            if(x.checkReward()){
                System.out.println("Reward exists");
                System.out.println(Arrays.toString(x.getReward()));
            }
            else{
                System.out.println("No reward");
            }
            System.out.println(x);
        }
        System.out.println(x);


    }
}

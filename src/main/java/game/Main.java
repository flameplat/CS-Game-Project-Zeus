package game;


import game.engine.*;
import game.realms.*;
import game.utilities.Color;
import java.util.*;



public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        controller.startGame();
//        GreenRealm x= (GreenRealm) controller.getActivePlayer().getRealm(Color.GREEN);
//        Move[] moves=x.getRealmMoves();
//        for(Move m:moves){
//            System.out.println(m);
//            x.attack(m);
//            if(x.checkReward()){
//                System.out.println("Reward exists");
//                System.out.println(Arrays.toString(x.getReward()));
//            }
//            else{
//                System.out.println("No reward");
//            }
//            System.out.println(x);
//        }
//        System.out.println(x);

//        RedRealm redRealm = (RedRealm) controller.getActivePlayer().getRealm(Color.RED);
//        Move[] redMoves = redRealm.getRealmMoves();
//        for (Move move : redMoves) {
//            System.out.println(move);
//            redRealm.attack(move);
//            if (redRealm.checkReward()) {
//                System.out.println("Reward exists");
//                System.out.println(Arrays.toString(redRealm.getReward()));
//            } else {
//                System.out.println("No reward");
//            }
//            System.out.println(redRealm);
//        }
//        System.out.println(redRealm);


    }
}

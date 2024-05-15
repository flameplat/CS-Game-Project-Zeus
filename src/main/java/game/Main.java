package game;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.TimeWarp;
import game.creatures.Dragon;
import game.creatures.Guardian;
import game.creatures.Lion;
import game.creatures.Serpent;
import game.dice.*;
import game.engine.*;
import game.realms.*;
import game.utilities.CollectiblesComparator;
import game.utilities.Color;

import java.util.*;
import java.util.stream.Collectors;



public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        GreenRealm x= (GreenRealm) controller.getActivePlayer().getRealm(Color.GREEN);
        Move[] moves=x.getRealmMoves();
        for(Move m:moves){
            System.out.println(m);
            x.attack(m);
            if(x.checkReward()){
                System.out.println("Reward exists");
                System.out.println(x.getReward()[0]);
            }
            else{
                System.out.println("No reward");
            }
            System.out.println(x);
        }
        System.out.println(x);


    }
}

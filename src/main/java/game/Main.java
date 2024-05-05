package game;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.TimeWarp;
import game.engine.*;
import game.utilities.CollectiblesComparator;
import game.utilities.Color;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {
        CLIGameController cliGameController = new CLIGameController();
//        Move[] moves=new Move[]{
//                new Move(new WhiteDice(1),new Lion()),
//                new Move(new BlueDice(2),new Lion()),
//                new Move(new YellowDice(3),new Lion()),
//                new Move(new RedDice(4),new Lion()),
//                new Move(new GreenDice(5),new Lion()),
//                new Move(new MagentaDice(6),new Lion())};
        Collectibles[] x=new Collectibles[]{
                new ColorBonus(Color.GREEN),
                new ColorBonus(Color.RED),
                new ColorBonus(Color.YELLOW),
                new ColorBonus(Color.BLUE),
                new ColorBonus(Color.MAGENTA),
                new ArcaneBoost(),
                new TimeWarp()
        };
        PriorityQueue<Collectibles> priorityQueue=new PriorityQueue<>(new CollectiblesComparator());
        priorityQueue.addAll(Arrays.asList(x));
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.remove());
        }

//        YellowRealm yellowRealm=(YellowRealm) cliGameController.getActivePlayer().getRealm(Color.YELLOW);
//        Move[] moves=new Move[]{
//                new Move(new YellowDice(1),new Lion()),
//                new Move(new YellowDice(2),new Lion()),
//                new Move(new YellowDice(3),new Lion()),
//                new Move(new YellowDice(4),new Lion()),
//                new Move(new YellowDice(5),new Lion()),
//                new Move(new YellowDice(6),new Lion()),
//                new Move(new YellowDice(4),new Lion()),
//                new Move(new YellowDice(2),new Lion()),
//                new Move(new YellowDice(2),new Lion()),
//                new Move(new YellowDice(6),new Lion()),
//                new Move(new YellowDice(1),new Lion()),
//                new Move(new YellowDice(2),new Lion()),
//                new Move(new YellowDice(3),new Lion()),
//                new Move(new YellowDice(4),new Lion())
//
//        };
//        for(Move m:moves){
//            System.out.println(yellowRealm.attack(m));
//            yellowRealm.checkReward();
//            System.out.println(yellowRealm);
//        }


    }
}

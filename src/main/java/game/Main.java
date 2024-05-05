package game;

import game.creatures.Lion;
import game.creatures.Phoenix;
import game.creatures.Serpent;
import game.dice.BlueDice;
import game.dice.Dice;
import game.dice.MagentaDice;
import game.dice.YellowDice;
import game.engine.*;
import game.realms.BlueRealm;
import game.realms.MagentaRealm;
import game.realms.YellowRealm;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {
        CLIGameController cliGameController = new CLIGameController();
        cliGameController.startGame();
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

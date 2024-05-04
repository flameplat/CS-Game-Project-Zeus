package game;

import game.collectibles.TimeWarp;
import game.creatures.Lion;
import game.creatures.Phoenix;
import game.dice.*;
import game.engine.*;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.realms.GreenRealm;
import game.realms.MagentaRealm;
import game.realms.YellowRealm;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CLIGameController cliGameController = new CLIGameController();
//        YellowRealm yellowRealm =(YellowRealm)cliGameController.getActivePlayer().getRealm(Color.YELLOW);
//        System.out.println(Arrays.toString(yellowRealm.collectibles));
//        System.out.println(yellowRealm.attack(new Move(new YellowDice(2),new Lion())));
//        System.out.println(yellowRealm.attack(new Move(new YellowDice(3),new Lion())));
//        System.out.println(yellowRealm.attack(new Move(new YellowDice(4),new Lion())));
//        System.out.println(yellowRealm.checkReward());
//        System.out.println(Arrays.toString(yellowRealm.getRealmMoves()));
//        System.out.println(yellowRealm.getReward());
        cliGameController.startGame();

    }
}

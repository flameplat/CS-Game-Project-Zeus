package game;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.TimeWarp;
import game.creatures.Guardian;
import game.creatures.Lion;
import game.creatures.Serpent;
import game.dice.BlueDice;
import game.dice.GreenDice;
import game.dice.YellowDice;
import game.engine.*;
import game.realms.BlueRealm;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.YellowRealm;
import game.utilities.CollectiblesComparator;
import game.utilities.Color;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {
        CLIGameController cliGameController = new CLIGameController();
        cliGameController.startGame();

    }
}

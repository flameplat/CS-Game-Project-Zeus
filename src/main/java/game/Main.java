package game;

import game.creatures.Serpent;
import game.dice.BlueDice;
import game.dice.Dice;
import game.engine.*;
import game.realms.BlueRealm;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {
        CLIGameController cliGameController = new CLIGameController();
        cliGameController.startGame();


    }
}

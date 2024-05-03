package game;

import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.dice.GreenDice;
import game.dice.RedDice;
import game.engine.*;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.realms.GreenRealm;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
         CLIGameController cliGameController = new CLIGameController();
        cliGameController.startGame();



    }
}

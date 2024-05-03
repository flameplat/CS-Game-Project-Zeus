package game;

import game.collectibles.*;
import game.dice.BlueDice;
import game.dice.Dice;
import game.dice.GreenDice;
import game.dice.RedDice;
import game.engine.*;
import game.exceptions.InvalidPlayerNameException;
import game.exceptions.MissingGameFilesException;
import game.realms.GreenRealm;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
         CLIGameController cliGameController = new CLIGameController();
//        System.out.println(cliGameController.getGameScore(cliGameController.getActivePlayer()).toString());


    }


}

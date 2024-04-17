package game;

import game.engine.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Dice Realms: Quest for the Elemental Crests!");
         CLIGameController cliGameController = new CLIGameController();
         //cliGameController.startGame();
        GameGuide g=new GameGuide();
        System.out.println(g.getUserChoice(3,6));



    }
}

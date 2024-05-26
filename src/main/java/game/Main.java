package game;

import game.engine.CLIGameController;
import game.engine.StandardAntiCheatService;


public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        controller.startGame();

    }

}

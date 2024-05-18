package game;
import game.engine.CLIGameController;
import game.utilities.Color;


public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        controller.startGame();
    }
}

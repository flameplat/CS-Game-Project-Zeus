package game;
import game.engine.CLIGameController;
import game.engine.Move;
import game.engine.Player;
import game.realms.GreenRealm;
import game.realms.RedRealm;
import game.utilities.Color;


public class Main {

    public static void main(String[] args) {
        CLIGameController controller = new CLIGameController();
        controller.startGame();
    }
}

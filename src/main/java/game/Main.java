package game;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.CLIGameController;
import game.engine.GameBoard;
import game.engine.Move;
import game.engine.Player;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;

public class Main {

    public static void main(String[] args) {
        CLIGameController controller= new CLIGameController();
        controller.startGame();
    }

}

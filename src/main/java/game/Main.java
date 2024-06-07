package game;

import game.dice.YellowDice;
import game.engine.AIPlayer;
import game.engine.CLIGameController;
import game.engine.Move;
import game.utilities.GameColor;


public class Main {

    public static void main(String[] args) {
//        CLIGameController controller = new CLIGameController();
//        controller.startGame();
        AIPlayer x=new AIPlayer("MOSTAFA");
        int e=x.getRealmsDecision().evaluateYellowRealmMove(new Move(new YellowDice(6),x.getRealm(GameColor.YELLOW).getCreature(new YellowDice(6))));
        System.out.println(e);



    }

}

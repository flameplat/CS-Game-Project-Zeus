package game;

import game.dice.YellowDice;
import game.engine.AIPlayer;
import game.engine.CLIGameController;
import game.engine.Move;
import game.utilities.GameColor;

import java.lang.reflect.Array;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
//        CLIGameController controller = new CLIGameController();
//        controller.startGame();
        AIPlayer x=new AIPlayer("MOSTAFA");
//        Move m=x.getRealm(GameColor.RED).getRealmMoves()[1];
//        System.out.println(m);
////        x.getRealm(GameColor.RED).attack(m);
//        print2DArray(x.getMoveEvaluation().getRedRealmMoveGrid());
//        System.out.println(x.getMoveEvaluation().evaluateRedRealmMove(m));
//        System.out.println(11%4);
        Move m=x.getRealm(GameColor.BLUE).getRealmMoves()[0];
        System.out.println(x.getRealm(GameColor.BLUE));
        System.out.println(x);
        System.out.println(m);
        System.out.println(x.getMoveEvaluation().evaluateBlueMove(m));


    }
    public static void print2DArray(Move[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}

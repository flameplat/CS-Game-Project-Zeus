package game;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.CLIGameController;
import game.engine.GameBoard;
import game.engine.Move;
import game.engine.Player;

public class Main {

    public static void main(String[] args) {
        CLIGameController controller= new CLIGameController();
        GameBoard gameBoard = controller.getGameBoard();
        Player player = controller.getActivePlayer();

        int[] redDiceValues = { 3, 2, 1, 3, 4, 6 };
        int[] dragonNumber = { 1, 1, 1, 2, 3, 4 };
        int[] greenDiceValues = { 3, 4, 5, 6 };
        int[] whiteDiceValues = { 6, 6, 6, 6 };
        int[] blueDiceValues = { 1, 2, 3 };
        int[] magentaDiceValues = { 1, 3, 6 };
        int[] yellowDiceValues = { 1, 1, 1 };

        for (int i = 0; i < redDiceValues.length; i++) {
            Dice[] dice = gameBoard.getDice();
            dice[0].setValue(redDiceValues[i]);
            ((RedDice) dice[0]).selectsDragon(dragonNumber[i]);
            Move[] possibleMoves = controller.getPossibleMovesForADie(player, dice[0]);
            if (possibleMoves.length > 0) {
                controller.makeMove(player, possibleMoves[0]);
            }
        }

        for (int i = 0; i < greenDiceValues.length; i++) {
            Dice[] dice = gameBoard.getDice();
            dice[1].setValue(greenDiceValues[i]);
            dice[5].setValue(whiteDiceValues[i]);
            Move[] possibleMoves = controller.getPossibleMovesForADie(player, dice[1]);
            if (possibleMoves.length > 0) {
                controller.makeMove(player, possibleMoves[0]);
            }
        }

        for (int i = 0; i < blueDiceValues.length; i++) {
            Dice[] dice = gameBoard.getDice();
            dice[2].setValue(blueDiceValues[i]);
            Move[] possibleMoves = controller.getPossibleMovesForADie(player, dice[2]);
            if (possibleMoves.length > 0) {
                controller.makeMove(player, possibleMoves[0]);
            }
        }

        for (int i = 0; i < magentaDiceValues.length; i++) {
            Dice[] dice = gameBoard.getDice();
            dice[3].setValue(magentaDiceValues[i]);
            Move[] possibleMoves = controller.getPossibleMovesForADie(player, dice[3]);
            if (possibleMoves.length > 0) {
                controller.makeMove(player, possibleMoves[0]);
            }
        }

        for (int i = 0; i < yellowDiceValues.length; i++) {
            Dice[] dice = gameBoard.getDice();
            dice[4].setValue(yellowDiceValues[i]);
            Move[] possibleMoves = controller.getPossibleMovesForADie(player, dice[4]);
            if (possibleMoves.length > 0) {
                controller.makeMove(player, possibleMoves[0]);
            }
        }

        int expectedRedScore = 10;
        int expectedGreenScore = 7;
        int expectedBlueScore = 6;
        int expectedMagentaScore = 10;
        int expectedYellowScore = 3;
        int expectedElementalCrests = 2;
        int expectedTotalScore = 42;

        int actualRedScore = controller.getGameScore(player).getRedRealmScore();
        int actualGreenScore = controller.getGameScore(player).getGreenRealmScore();
        int actualBlueScore = controller.getGameScore(player).getBlueRealmScore();
        int actualMagentaScore = controller.getGameScore(player).getMagentaRealmScore();
        int actualYellowScore = controller.getGameScore(player).getYellowRealmScore();
        int actualElementalCrests = player.getGameScore().getTotalElementalCrests();
        int actualTotalScore = controller.getGameScore(player).getTotalScore();

        player.getScoreSheet().displayScoreSheet();
        System.out.println(player.getGameScore());
        System.out.println("Actual Red Score: " + actualRedScore);
        System.out.println("Actual Green Score: " + actualGreenScore);
        System.out.println("Actual Blue Score: " + actualBlueScore);
        System.out.println("Actual Magenta Score: " + actualMagentaScore);
        System.out.println("Actual Yellow Score: " + actualYellowScore);
        System.out.println("Actual Elemental Crests: " + actualElementalCrests);
        System.out.println("Actual Total Score: " + actualTotalScore);
    }

}

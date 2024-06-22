package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.dice.Dice;
import game.dice.RedDice;
import game.gui.GUIGameController;
import game.utilities.GameColor;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MoveEvaluationTest {
    @Test
    void resetNoWorldsTest() {
        MoveEvaluation.redWorlds = 1;
        MoveEvaluation.greenWorlds = 1;
        MoveEvaluation.blueWorlds = 1;
        MoveEvaluation.yellowWorlds = 1;
        MoveEvaluation.magentaWorlds = 1;
        MoveEvaluation.resetNoWorlds();

        assertEquals(0, MoveEvaluation.redWorlds);
        assertEquals(0, MoveEvaluation.greenWorlds);
        assertEquals(0, MoveEvaluation.blueWorlds);
        assertEquals(0, MoveEvaluation.yellowWorlds);
        assertEquals(0, MoveEvaluation.magentaWorlds);
    }

    @Test
    void getRewardEvaluationTest() {
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("mostafa");
        GUIGameController guiGameController = new GUIGameController();
        MoveEvaluation moveEvaluation = new MoveEvaluation(player, pastMoves, guiGameController);
        
        Collectibles arcaneBoost = new ArcaneBoost();
        assertEquals(10, moveEvaluation.getRewardEvaluation(arcaneBoost, pastMoves));

        Collectibles timeWarp = new TimeWarp();
        assertEquals(0, moveEvaluation.getRewardEvaluation(timeWarp, pastMoves));

        Collectibles elementalCrest = new ElementalCrest();
        assertEquals(0, moveEvaluation.getRewardEvaluation(elementalCrest, pastMoves));

        Collectibles colorBonus = new ColorBonus(GameColor.RED);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(colorBonus, pastMoves));
    }
    
    @Test
    void getWeightOfMoveTest() {
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("seniorX");
        GUIGameController guiGameController = new GUIGameController();
        
        MoveEvaluation moveEvaluation = new MoveEvaluation(player, pastMoves, guiGameController);
        
        Dice redDice = new RedDice(3);
        Move move = new Move(redDice, player.getRealm(redDice).getCreature(redDice));

        double weightOfMove = moveEvaluation.getWeightOfMove(move);
        assertTrue(weightOfMove >= 0);
    }
}
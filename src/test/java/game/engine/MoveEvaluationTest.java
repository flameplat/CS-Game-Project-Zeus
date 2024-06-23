package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.ElementalCrest;
import game.collectibles.TimeWarp;
import game.creatures.Lion;
import game.dice.Dice;
import game.dice.RedDice;
import game.dice.YellowDice;
import game.gui.GUIGameController;
import game.utilities.GameColor;
import org.junit.jupiter.api.Test;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
// This is not a full test we were just trying to make one :)
class MoveEvaluationTest {
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
        MoveEvaluation.resetNoWorlds();
        Collectibles redBonus = new ColorBonus(GameColor.RED);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(redBonus , pastMoves));
        MoveEvaluation.resetNoWorlds();
        Collectibles greenBonus = new ColorBonus(GameColor.GREEN);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(greenBonus , pastMoves));
        MoveEvaluation.resetNoWorlds();
        Collectibles blueBonus = new ColorBonus(GameColor.BLUE);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(blueBonus, pastMoves));
        MoveEvaluation.resetNoWorlds();
        Collectibles yellowBonus = new ColorBonus(GameColor.YELLOW);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(yellowBonus, pastMoves));
        MoveEvaluation.resetNoWorlds();
        Collectibles magentaBonus = new ColorBonus(GameColor.MAGENTA);
        assertNotEquals(0, moveEvaluation.getRewardEvaluation(magentaBonus, pastMoves));
        MoveEvaluation.resetNoWorlds();
    }
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
    void getWeightOfMoveTest() {
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("seniorX");
        GUIGameController guiGameController = new GUIGameController();
        
        MoveEvaluation moveEvaluation = new MoveEvaluation(player, pastMoves, guiGameController);
        
        Dice redDice = new RedDice(3);
        Move move = new Move(redDice, player.getRealm(redDice).getCreature(redDice));
        double weightOfMove = moveEvaluation.getWeightOfMove(move);
        assertTrue(weightOfMove > 0);
        MoveEvaluation.resetNoWorlds();
    }
    @Test
    void evaluateRedMoveTest() {
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("Mona Elshazly");
        GUIGameController guiGameController = new GUIGameController();
        MoveEvaluation moveEvaluation = new MoveEvaluation(player, pastMoves, guiGameController);

        Dice redDice = new RedDice(3);
        Move move = new Move(redDice, player.getRealm(redDice).getCreature(redDice));
        double weightOfMove = moveEvaluation.evaluateRedMove(move);
        assertTrue(weightOfMove > 0);
        MoveEvaluation.resetNoWorlds();
    }
    @Test
    void evaluateYellowBonusWeightTest() {
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("Elon Musk");
        GUIGameController guiGameController = new GUIGameController();
        MoveEvaluation moveEval = new MoveEvaluation(player, pastMoves, guiGameController);
        double yellowBonusWeight = moveEval.evaluateYellowBonusWeight(pastMoves);
       assertTrue(yellowBonusWeight > 0);
        MoveEvaluation.resetNoWorlds();
   }
    @Test
    public void testEvaluateYellowMove(){
        LinkedList<Move> pastMoves = new LinkedList<>();
        AIPlayer player = new AIPlayer("Reham Sa3eed");
        GUIGameController guiGameController = new GUIGameController();
        MoveEvaluation moveEvaluation = new MoveEvaluation(player, pastMoves, guiGameController);

        Dice yellowDice = new YellowDice(6);
        Move move = new Move(yellowDice, new Lion());
        double moveWeight=moveEvaluation.evaluateYellowMove(move);
        assertTrue(moveWeight>0);
    }


}

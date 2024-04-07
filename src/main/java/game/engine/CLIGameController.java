package game.engine;

import game.Realms.RedRealm;
import game.dice.*;
import game.creatures.*;
import game.collectibles.*;

public class CLIGameController extends GameController {

    @Override
    public void startGame() {

    }

    @Override
    public boolean switchPlayer() {
        return false;
    }

    @Override
    public Dice[] rollDice() {
        return new Dice[0];
    }

    @Override
    public Dice[] getAvailableDice() {
        return new Dice[0];
    }

    @Override
    public Dice[] getAllDice() {
        return new Dice[0];
    }

    @Override
    public Dice[] getForgottenRealmDice() {
        return new Dice[0];
    }

    @Override
    public Move[] getAllPossibleMoves() {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMoves(Dice dice) {
        return new Move[0];
    }

    @Override
    public GameBoard getGameBoard() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public ScoreSheet getScoreSheet() {
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        return null;
    }

    @Override
    public GameScore getGameScore() {
        return null;
    }

    @Override
    public TimeWarp[] getTimeWarpPowers() {
        return null;
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers() {
        return null;
    }

    @Override
    public boolean selectDice(Dice dice) {
        return false;
    }

    @Override
    public boolean makeMove(Dice dice, Creature creature) {
        return false;
    }
}

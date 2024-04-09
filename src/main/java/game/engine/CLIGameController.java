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
    public Move[] getAllPossibleMoves(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return new Move[0];
    }

    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        return new Move[0];
    }

    @Override
    public GameBoard getGameBoard() {
        return null;
    }

    @Override
    public Player getActivePlayer() {
        return null;
    }

    @Override
    public Player getPassivePlayer() {
        return null;
    }

    @Override
    public ScoreSheet getScoreSheet(Player player) {
        return null;
    }

    @Override
    public GameStatus getGameStatus() {
        return null;
    }

    @Override
    public GameScore getGameScore(Player player) {
        return null;
    }

    @Override
    public TimeWarp[] getTimeWarpPowers(Player player) {
        return new TimeWarp[0];
    }

    @Override
    public ArcaneBoost[] getArcaneBoostPowers(Player player) {
        return new ArcaneBoost[0];
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        return false;
    }

    @Override
    public boolean makeMove(Player player, Move move) {
        return false;
    }
}

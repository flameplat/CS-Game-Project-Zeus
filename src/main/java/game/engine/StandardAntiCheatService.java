package game.engine;

import game.dice.Dice;
import game.exceptions.CheatDetectedException;

public class StandardAntiCheatService implements AntiCheatService{
    @Override
    public void checkPlayerScore(Player player) {

    }

    @Override
    public void checkGameBoard(GameBoard gameBoard) {

    }

    @Override
    public void checkGameStatus(GameStatus gameStatus) {

    }

    @Override
    public void checkDice(Dice[] dice) {

    }

    @Override
    public void checkPlayerReward(Player player) throws CheatDetectedException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkPlayerReward'");
    }
}

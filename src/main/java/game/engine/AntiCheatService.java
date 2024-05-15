package game.engine;

import game.dice.Dice;
import game.exceptions.CheatDetectedException;

public interface AntiCheatService {
    void checkPlayerScore(Player player) throws CheatDetectedException;
    void checkPlayerReward(Player player) throws CheatDetectedException;
    void checkGameBoard(GameBoard gameBoard)throws CheatDetectedException;
    void checkGameStatus(GameStatus gameStatus)throws CheatDetectedException;
    void checkDice(Dice[] dice)throws CheatDetectedException;

}

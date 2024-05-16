package game.engine;

import game.dice.Dice;
import game.exceptions.CheatDetectedException;
import game.exceptions.DiceCheatException;
import game.exceptions.InvalidFinalScoreCheat;
import game.exceptions.RewardCheatException;

public interface AntiCheatService {
    void checkPlayerScore(Player player) throws CheatDetectedException;
    void checkPlayerReward(Player player) throws RewardCheatException;
    void checkGameStatus(GameStatus gameStatus)throws CheatDetectedException;
    void checkDice(Dice[] dice)throws DiceCheatException;
    void checkPlayerFinalScore(Player player) throws InvalidFinalScoreCheat;
    void handlePlayerScore(Player player);
    void handleDiceCheat(Dice[] dice);
    void handleRewardCheat(Player player);


}

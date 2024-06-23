package game.engine;

import game.dice.Dice;
import game.exceptions.CheatDetectedException;
import game.exceptions.DiceCheatException;
import game.exceptions.InvalidFinalScoreCheat;
import game.exceptions.RewardCheatException;

/**
 * Cheat can happen within the game if the user used one of the common ways to alter the current state of the
 * game using "Cheat Engine" or any other software that can track changes and sequences in the memory and modify the memory of a running program
 */
public interface AntiCheatService {
    /**
     * Checks the player's score for any signs of cheating.
     *
     * @param player the player whose score is being checked
     * @throws CheatDetectedException if cheating is detected
     */
    void checkPlayerScore(Player player) throws CheatDetectedException;

    /**
     * Checks if a player has cheated in receiving rewards.
     *
     * @param player The player to check for reward cheating.
     * @throws RewardCheatException if the player has cheated in receiving rewards.
     */
    void checkPlayerReward(Player player) throws RewardCheatException;

    /**
     * Checks the game status to detect any cheat.
     *
     * @param gameStatus the current game status.
     * @throws CheatDetectedException if a cheat is detected.
     */
    void checkGameStatus(GameStatus gameStatus) throws CheatDetectedException;

    /**
     * Checks if there is any cheat detected in the given array of Dice objects.
     *
     * @param dice The array of Dice objects to be checked.
     * @throws DiceCheatException If a dice cheat is detected.
     */
    void checkDice(Dice[] dice) throws DiceCheatException;

    /**
     * Checks the final score of a player to detect any possible cheating.
     *
     * @param player the player whose final score needs to be checked
     * @throws InvalidFinalScoreCheat if cheating is detected in the player's final score
     */
    void checkPlayerFinalScore(Player player) throws InvalidFinalScoreCheat;

    /**
     * Handles the player score by performing necessary actions or validations.
     * This method is called when the player's score needs to be handled.
     *
     * @param player The player whose score needs to be handled.
     */
    void handlePlayerScore(Player player);

    /**
     * Handles a dice cheat detected during the anti-cheat service checks.
     * This method is called when a cheat in the dice is detected, and it takes the necessary actions to handle the cheat.
     *
     * @param dice the array of dice involved in the cheat
     */
    void handleDiceCheat(Dice[] dice);

    /**
     * Handles the cheat where a player tries to alter their rewards unfairly.
     *
     * @param player The player who attempted the cheat.
     */
    void handleRewardCheat(Player player);


}

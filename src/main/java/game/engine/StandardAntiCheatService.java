package game.engine;

import game.collectibles.Collectibles;
import game.dice.Dice;
import game.exceptions.*;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.GameColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StandardAntiCheatService implements AntiCheatService {
    /**
     * Contains a mapping of players to their previous collectibles.
     * <p>
     * This variable is a private final Map<Player, Map<String, Integer>> that stores the previous collectibles
     * of each player in the game. The outer map associates each player with their respective map of collectibles,
     * where the keys are string identifiers and the values are integers representing the number of collectibles.
     * The previousCollectibles map is intended to keep track of the collectibles obtained by each player before a certain point in the game.
     * <p>
     * Examples of usage:
     * - Comparing a player's previous collectibles with their current ones to detect cheating.
     * <p>
     * Note: This variable is defined in the class StandardAntiCheatService, which implements the AntiCheatService interface and is used to detect cheating in a game.
     *
     * @see AntiCheatService
     * @see Player
     * @see Map
     * @see String
     * @see Integer
     */
    private final Map<Player, Map<String, Integer>> previousCollectibles;
    /**
     * Represents a map that stores the previous scores of players.
     * <p>
     * This map associates each player with their previous score.
     * It is used for detecting cheating by comparing the current score of a player
     * with their previous scores and if it is greater than @scoreLimit it will be considered a cheat
     */
    private final Map<Player, Integer> previousScores;
    /**
     * Represents an array of previous dice values.
     * <p>
     * This array stores the dice objects from the player's previous turns. It is used to detect cheating
     * if the player tries to manipulate the dice values by using the same values as before.
     */
    private final Dice[] previousDice;
    /**
     * The master player for the game.
     *
     * <p>
     * The master player is a private final instance of the Player class. It represents a player
     * who achieves the maximum score in the game and is responsible for making decisions, receiving rewards, and
     * keeping track of the score limit. This variable is used within the StandardAntiCheatService
     * class, which implements the AntiCheatService interface.
     * </p>
     *
     * @serial Not applicable
     * @serialField Not applicable
     * @see Player
     * @see StandardAntiCheatService
     * @see AntiCheatService
     * @since Unknown
     * @deprecated Not applicable
     */
    private final Player master;
    /**
     * The score limit for detecting cheating in a game. Players' scores will be compared to this limit to detect cheating.
     */
    private int scoreLimit;

    /**
     * StandardAntiCheatService class provides methods to detect cheating in a game.
     * It implements the AntiCheatService interface.
     */
    public StandardAntiCheatService() {
        master = new Player();
        this.previousScores = new HashMap<>();
        this.previousCollectibles = new HashMap<>();
        this.previousDice = new Dice[6];
    }

    /**
     * Initializes the master player and plays the game with maximum attacks.
     * The master player receives round rewards from the game controller, attacks
     * the realms with their respective moves, receives rewards from the realms,
     * and keeps track of the score limit.
     */
    public void initMasterPlayer() {
        //MASTER PLAYER plays the game with max attacks
        CLIGameController controller = new CLIGameController();
        Collectibles[] roundRewards = controller.getRoundRewards();
        for (Collectibles r : roundRewards) {
            master.receiveCollectible(r);
        }
        scoreLimit = 0;
        RedRealm redRealm = (RedRealm) master.getRealms()[0];
        GreenRealm greenRealm = (GreenRealm) master.getRealms()[1];
        Move[] redMoves = redRealm.getRealmMoves();
        for (Collectibles r : controller.getRoundRewards()) {
            master.receiveCollectible(r);
        }
        int previousScore = 0;
        int currentScore;
        for (Move move : redMoves) {

            redRealm.attack(move);
            if (redRealm.checkReward()) {
                Collectibles[] rewards = redRealm.getReward();
                for (Collectibles r : rewards) {
                    master.receiveCollectible(r);
                }
            }
            currentScore = redRealm.getTotalScore();
            if (scoreLimit < currentScore - previousScore) {
                scoreLimit = currentScore - previousScore;
            }
            previousScore = currentScore;
        }
        previousScore = 0;
        Move[] greenMoves = greenRealm.getRealmMoves();
        for (Move move : greenMoves) {
            greenRealm.attack(move);
            if (greenRealm.checkReward()) {
                Collectibles[] rewards = greenRealm.getReward();
                for (Collectibles r : rewards) {
                    master.receiveCollectible(r);
                }
            }
            currentScore = greenRealm.getTotalScore();
            if (scoreLimit < currentScore - previousScore) {
                scoreLimit = currentScore - previousScore;
            }
            previousScore = currentScore;
        }
        previousScore = 0;
        for (int i = 2; i < 5; i++) {
            Realm realm = master.getRealms()[i];
            while (realm.isRealmAvailable()) {
                Move m = realm.getRealmMoves()[realm.getRealmMoves().length - 1];
                realm.attack(m);
                if (realm.checkReward()) {
                    Collectibles[] rewards = realm.getReward();
                    for (Collectibles r : rewards) {
                        master.receiveCollectible(r);
                    }
                }
                currentScore = realm.getTotalScore();
                if (scoreLimit < currentScore - previousScore) {
                    scoreLimit = currentScore - previousScore;
                }
                previousScore = currentScore;
            }
        }
    }

    /**
     * Checks the current score of a player and compares it with their previous scores to detect cheating.
     *
     * @param player The player whose score needs to be checked.
     * @throws CheatDetectedException If cheating is detected based on the player's score when it is greater than the score limit.
     */
    @Override
    public void checkPlayerScore(Player player) throws CheatDetectedException {
        if (scoreLimit == 0) {
            initMasterPlayer();
        }
        int currentScore = player.getGameScore().getCurrentScore();
        if (previousScores.containsKey(player)) {
            if ((currentScore - previousScores.get(player)) < 0) {
                throw new NegativeScoreException();
            }
            if ((currentScore - previousScores.get(player)) > scoreLimit) {
                System.err.println("Current score: " + currentScore);
                System.err.println("Previous score: " + previousScores.get(player));
                System.err.println("Score limit: " + scoreLimit);
                throw new HighScoreException();
            }
        }
        previousScores.put(player, currentScore);
    }

    /**
     * This method is used to check the status of a game.
     *
     * @param gameStatus The status of the game.
     */
    @Override
    public void checkGameStatus(GameStatus gameStatus) {

    }

    /**
     * Checks the validity of the given dice array. Throws a DiceCheatException if any dice in the array
     * is invalid, indicating cheating has occurred.
     *
     * @param dice the array of Dice objects to be checked
     * @throws DiceCheatException if any dice is invalid
     */
    @Override
    public void checkDice(Dice[] dice) throws DiceCheatException {
        int c = 0;
        for (Dice die : dice) {
            if ((die.getRealm() != GameColor.values()[c++]) || !(die.getValue() > 0 && die.getValue() < 7)) {
                System.out.println(Arrays.toString(dice));
                throw new DiceCheatException();
            }
        }
        for (int i = 0; i < dice.length; i++) {
            this.previousDice[i] = Dice.getNewDice(dice[i].getRealm(), dice[i].getValue());
        }


    }

    /**
     * Checks if a player's reward is valid based on their collectibles and game scores.
     *
     * @param player The player to check for reward validity.
     * @throws RewardCheatException If a reward cheat is detected.
     */
    @Override
    public void checkPlayerReward(Player player) throws RewardCheatException {
        if (player.getTotalArcaneBoostPowersCollected() > Collectibles.getCounter("ArcaneBoost")
                || player.getTotalTimeWarpPowersCollected() > Collectibles.getCounter("TimeWarp")) {
            throw new RewardCheatException();
        }
        if (previousCollectibles.containsKey(player)) {
            if (previousCollectibles.get(player).containsKey("TimeWarp")) {
                int previousTimeWarp = previousCollectibles.get(player).get("TimeWarp");
                int currentTimeWarp = Collectibles.getCounter("TimeWarp");
                if (previousTimeWarp > currentTimeWarp) {
                    throw new RewardCheatException("Time Warp cheat detected!");
                }
            }
            if (previousCollectibles.get(player).containsKey("ArcaneBoost")) {
                int previousArcaneBoost = previousCollectibles.get(player).get("ArcaneBoost");
                int currentArcaneBoost = Collectibles.getCounter("ArcaneBoost");
                if (previousArcaneBoost > currentArcaneBoost) {
                    throw new RewardCheatException("Arcane Boost cheat detected!");
                }
            }
        }
        if (player.getGameScore().getTotalElementalCrests() > master.getGameScore().getTotalElementalCrests()) {
            throw new RewardCheatException();
        }
        previousCollectibles.put(player, player.getCollectiblesCounters());
    }

    /**
     * Checks the final score of a player to ensure there is no cheating.
     * Throws InvalidFinalScoreCheat if any realm's total score in the player's realms exceeds
     * the total score of the corresponding realm in the master player, or if the player's
     * game score exceeds the master's game score.
     *
     * @param player The player whose final score needs to be checked.
     * @throws InvalidFinalScoreCheat if any cheating is detected in the player's final score.
     */
    @Override
    public void checkPlayerFinalScore(Player player) throws InvalidFinalScoreCheat {
        if (scoreLimit == 0) {
            initMasterPlayer();
        }
        Realm[] playerRealms = player.getRealms();

        for (int i = 0; i < playerRealms.length; i++) {
            if (playerRealms[i].getTotalScore() > master.getRealms()[i].getTotalScore()) {
                System.out.println(playerRealms[i]);
                throw new InvalidFinalScoreCheat();
            }
        }
        if (player.getGameScore().getTotalScore() > master.getGameScore().getTotalScore()) {
            throw new InvalidFinalScoreCheat();
        }
    }

    /**
     * Calculates and handles the cheat penalty for a player's score.
     *
     * @param player The player whose score needs to be handled.
     */
    @Override
    public void handlePlayerScore(Player player) {
        player.getGameScore().setCheatPenalty(((player.getGameScore().getCurrentScore() - previousScores.get(player)) / scoreLimit) * 100 - 100);
    }

    /**
     * Handles the cheat functionality for the dice game.
     * <p>
     * This method copies the previous dice values into the current dice array,
     * allowing the player to cheat by using the same dice values as before.
     *
     * @param dice the array of dice to be manipulated
     * @throws NullPointerException if 'dice' is null
     */
    @Override
    public void handleDiceCheat(Dice[] dice) {
        System.arraycopy(previousDice, 0, dice, 0, dice.length);
    }

    /**
     * Resets the rewards for the specified player.
     *
     * @param player The player whose rewards should be reset.
     */
    @Override
    public void handleRewardCheat(Player player) {
        player.resetRewards();
    }
}

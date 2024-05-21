package game.engine;

import game.collectibles.Collectibles;
import game.dice.Dice;
import game.exceptions.*;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StandardAntiCheatService implements AntiCheatService {
    private final Map<Player, Map<String, Integer>> previousCollectibles;
    private final Map<Player, Integer> previousScores;
    private final Dice[] previousDice;
    private final Player master;
    private int scoreLimit;

    public StandardAntiCheatService() {
        master = new Player();
        this.previousScores = new HashMap<>();
        this.previousCollectibles = new HashMap<>();
        this.previousDice = new Dice[6];
    }

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
                throw new HighScoreException();
            }
        }
        previousScores.put(player, currentScore);
    }

    @Override
    public void checkGameStatus(GameStatus gameStatus) {

    }

    @Override
    public void checkDice(Dice[] dice) throws DiceCheatException {
        int c = 0;
        for (Dice die : dice) {
            if ((die.getRealm() != Color.values()[c++]) || !(die.getValue() > 0 && die.getValue() < 7)) {
                System.out.println(Arrays.toString(dice));
                throw new DiceCheatException();
            }
        }
        for (int i = 0; i < dice.length; i++) {
            this.previousDice[i] = Dice.getNewDice(dice[i].getRealm(), dice[i].getValue());
        }


    }

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

    @Override
    public void checkPlayerFinalScore(Player player) throws InvalidFinalScoreCheat {
        if (scoreLimit == 0) {
            initMasterPlayer();
        }
        Realm[] playerRealms = player.getRealms();

        for (int i = 0; i < playerRealms.length; i++) {
            if (playerRealms[i].getTotalScore() > master.getRealms()[i].getTotalScore()) {
                throw new InvalidFinalScoreCheat();
            }
        }
        if (player.getGameScore().getTotalScore() > master.getGameScore().getTotalScore()) {
            throw new InvalidFinalScoreCheat();
        }
    }

    @Override
    public void handlePlayerScore(Player player) {
        player.getGameScore().setCheatPenalty(((player.getGameScore().getCurrentScore() - previousScores.get(player)) / scoreLimit) * 100 - 100);
    }

    @Override
    public void handleDiceCheat(Dice[] dice) {
        System.arraycopy(previousDice, 0, dice, 0, dice.length);
    }

    @Override
    public void handleRewardCheat(Player player) {
        player.resetRewards();
    }
}

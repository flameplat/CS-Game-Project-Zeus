package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Lion;
import game.dice.Dice;
import game.dice.YellowDice;
import game.engine.Move;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class YellowRealm extends Realm {
    private static final GameColor REALM_GAME_COLOR = GameColor.YELLOW;
    private static final String name = "\u001B[33m" + "Yellow Realm" + "\u001B[0m";
    private final LinkedList<Move> realmMoves;
    private final Lion lion;
    private final int[] scoreMultiplier = {1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 3};
    private final Object[] score;
    private final String[] rewardValues;
    public Collectibles[] collectibles;
    // -----------------------Attributes-----------------------//
    private int totalRealmScore;
    private int countHits;
    private int noElementalCrests;

    /**
     * YellowRealm represents a realm with a yellow game color.
     * It is responsible for managing the realm moves, scores, rewards, and the lion creature.
     */
    // -----------------------Constructor-----------------------//
    public YellowRealm() {
        this.realmMoves = new LinkedList<>();
        this.rewardValues = new String[11];
        this.totalRealmScore = 0;
        this.lion = new Lion();
        this.countHits = 0;
        this.noElementalCrests = 0;
        this.collectibles = getRewardsProperties();
        for (int i = 1; i < 7; i++) {
            this.realmMoves.addLast(new Move(new YellowDice(i), lion));
        }
        this.score = new Object[11];
        for (int i = 0; i < 11; i++) {
            score[i] = 0 + " ";
        }
    }

    /**
     * Retrieves the properties of the rewards from the RadiantSvannaRewards.properties file.
     *
     * @return an array of Collectibles representing the reward properties.
     *         Returns null if there are no rewards or an error occurred while retrieving the properties.
     */
    // -----------------------Methods-----------------------//
    public Collectibles[] getRewardsProperties() {
        Properties properties = new Properties();
        Collectibles[] rewardProperties = new Collectibles[11];
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/RadiantSvannaRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        for (int i = 0; i < 11; i++) {
            String reward = properties.getProperty("hit" + (i + 1) + "Reward");
            rewardProperties[i] = Collectibles.getCollectibleFromString(reward);
            if (rewardProperties[i] == null) {
                rewardValues[i] = "  ";
            } else {
                rewardValues[i] = rewardProperties[i].toString();
            }

        }
        return rewardProperties;
    }
    /**
     * Represents the imaginary hit count for a realm.
     * This variable holds the number of imaginary hits that have been made in the realm.
     * A hit is considered imaginary when it does not have any effect or consequence in the game.
     *
     * The imaginaryHitCount variable is a private integer that can only be accessed within the YellowRealm class.
     *
     * Example usage:
     *
     * YellowRealm yellowRealm = new YellowRealm();
     * int imaginaryHits = yellowRealm.getImaginaryHitCount();
     *
     * imaginaryHits++;
     * yellowRealm.setImaginaryHitCount(imaginaryHits);
     *
     * System.out.println("Imaginary hit count in Yellow Realm: " + yellowRealm.getImaginaryHitCount());
     *
     */
    private int imaginaryHitCount;

    /**
     * Returns the name of the realm.
     *
     * @return the name of the realm.
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Retrieves the score values.
     *
     * @return an array of score values
     */
    public Object[] getScoreValues(){
        return score;
    }
    /**
     * Returns the reward values of the realm as an array of strings.
     *
     * @return the reward values of the realm
     */
    public String[] getRewardValues(){
        return rewardValues;
    }

    /**
     * Returns the count of hits in the Yellow Realm.
     *
     * @return The count of hits in the Yellow Realm.
     */
    public int getCountHits() {
        return countHits;
    }

    /**
     * Returns the color of the realm.
     *
     * @return the color of the realm
     */
    @Override
    public GameColor getColor() {
        return REALM_GAME_COLOR;
    }

    /**
     * This method returns the status of the realm.
     *
     * @return the status of the realm as an integer value
     */
    @Override
    public int getStatus() {
        return 0;
    }

    /**
     * Checks if the yellow realm is available.
     *
     * @return true if the yellow realm is available, false otherwise.
     */
    @Override
    public boolean isRealmAvailable() {
        return countHits < 11;
    }

    /**
     * Retrieves the reward for the YellowRealm.
     *
     * @return an array of Collectibles representing the reward for the YellowRealm.
     */
    @Override
    public Collectibles[] getReward() {
        Collectibles[] temp = new Collectibles[1];
        temp[0] = collectibles[countHits - 1];
        return temp;
    }

    /**
     * Checks if there is a reward for the current hit count in the YellowRealm.
     *
     * @return true if there is a reward, false otherwise.
     */
    @Override
    public boolean checkReward() {
        if (collectibles[countHits - 1] != null) {
            if (collectibles[countHits - 1] instanceof ElementalCrest) {
                noElementalCrests++;
            }
            rewardValues[countHits - 1] = "X ";
            return true;
        }
        return false;

    }
    /**
     * Returns the array of Collectibles associated with the Yellow Realm.
     *
     * @return the array of Collectibles associated with the Yellow Realm
     */
    public Collectibles[] getCollectibles(){
        return collectibles;
    }



    /**
     * Initiates an attack in the Yellow Realm.
     *
     * @param move the move used to perform the attack
     * @return true if the attack was successful, false otherwise
     */
    @Override
    public boolean attack(Move move) {
        if (isRealmAvailable()) {
            boolean flag = false;
            for (Move realmMove : realmMoves) {
                if (move != null && move.getDice().getValue() == realmMove.getDice().getValue() && move.getDice().getRealm() == realmMove.getDice().getRealm()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
            int attackScore = move.getDice().getValue() * scoreMultiplier[countHits];
            lion.attack();
            score[countHits] = (attackScore < 9) ? attackScore + " " : attackScore;
            totalRealmScore = totalRealmScore + attackScore;
            countHits++;
            return true;
        } else {
            return false;
        }

    }


    /**
     * Returns the total score of the realm.
     *
     * @return The total score of the realm as an integer.
     */
    @Override
    public int getTotalScore() {
        return totalRealmScore;
    }

    /**
     * Returns the number of elemental crests in the realm.
     *
     * @return The number of elemental crests in the realm.
     */
    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    /**
     * Returns a string representation of the YellowRealm instance.
     *
     * @return the string representation of the YellowRealm instance
     */
    @Override
    public String toString() {
        return String.format("Radiant Savanna: Solar Lion (YELLOW REALM):\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  H  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "|  M  |     |     |     |x2   |     |     |x2   |     |x2   |     |x3   |\n" +
                        "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n\n",
                score[0], score[1], score[2], score[3], score[4], score[5], score[6], score[7], score[8], score[9], score[10],
                rewardValues[0], rewardValues[1], rewardValues[2], rewardValues[3], rewardValues[4], rewardValues[5], rewardValues[6], rewardValues[7], rewardValues[8], rewardValues[9], rewardValues[10]);
    }

    /**
     * Returns an array of Move objects for the realm.
     *
     * @return an array of Move objects if the realm is available, otherwise an empty array.
     */
    @Override
    public Move[] getRealmMoves() {
        if (isRealmAvailable()) {
            return realmMoves.toArray(Move[]::new);
        }
        return new Move[0];

    }

    /**
     * This method returns the creature based on the given dice object.
     *
     * @param dice the dice object used to determine the creature
     * @return the creature based on the given dice, or null if conditions are not met
     */
    @Override
    public Creature getCreature(Dice dice) {
        if (dice.getRealm() == GameColor.YELLOW && (dice.getValue() <= 6 && dice.getValue() >= 1)) {
            return lion;
        }
        return null;
    }

    /**
     * Calculates the fake score of a move in the YellowRealm.
     *
     * @param move The move for which to calculate the fake score.
     * @return The fake score of the move.
     */
    @Override
    public int getFakeScore(Move move) {
       //Score
        int attackScore = move.getDice().getValue() * scoreMultiplier[countHits];
        return attackScore;
    }

    /**
     * Retrieves the score multiplier values.
     *
     * @return An array of integers representing the score multiplier values.
     */
    public int[] getScoreMultiplier(){
        return scoreMultiplier;
    }


}

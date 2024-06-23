package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Phoenix;
import game.dice.Dice;
import game.dice.MagentaDice;
import game.engine.Move;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;


public class MagentaRealm extends Realm {
    // -----------------------Attributes-----------------------//
    private static final GameColor REALM_GAME_COLOR = GameColor.MAGENTA;
    private static final String name = "\u001B[35m" + "Magenta Realm" + "\u001B[0m";
    private final Collectibles[] collectibles;
    private final Move[] realmMoves;
    private final Phoenix phoenix;
    private final int[] score;
    private final String[] attackValues;
    private final String[] rewardValues;
    private int totalRealmScore;
    private int noElementalCrests;
    private int counterHits;
    private Move[] realmPossibleMoves;

    /**
     * Represents the Magenta Realm.
     */
    // -----------------------Constructor-----------------------//
    public MagentaRealm() {
        this.rewardValues = new String[11];
        this.collectibles = getRewardsProperties();
        this.attackValues = new String[11];
        for (int i = 0; i < 11; i++) {
            attackValues[i] = " ";
        }
        this.phoenix = new Phoenix();
        totalRealmScore = 0;
        this.score = new int[11];
        this.noElementalCrests = 0;
        this.realmMoves = new Move[]{
                new Move(new MagentaDice(1), phoenix),
                new Move(new MagentaDice(2), phoenix),
                new Move(new MagentaDice(3), phoenix),
                new Move(new MagentaDice(4), phoenix),
                new Move(new MagentaDice(5), phoenix),
                new Move(new MagentaDice(6), phoenix)};
        this.realmPossibleMoves = realmMoves;
        this.counterHits = 0;
    }

    /**
     * Updates the possible moves based on the given move.
     *
     * @param move the Move object to update the possible moves with
     */
    // -----------------------Methods-----------------------//
    private void updatePossibleMoves(Move move) {
        LinkedList<Move> list = new LinkedList<>();
        for (Move realmMove : realmMoves) {
            if ((move.getDice().getValue() % realmMoves.length) < realmMove.getDice().getValue()) {
                list.addLast(realmMove);
            }
        }
        this.realmPossibleMoves = list.toArray(Move[]::new);
    }
    /**
     * Returns the value of the counterHits field.
     *
     * @return The current value of the counterHits field.
     */
    public int getCounterHits() {
        return counterHits;
    }

    /**
     * Retrieves the properties of the rewards.
     *
     * @return An array of Collectibles representing the reward properties.
     */
    public Collectibles[] getRewardsProperties() {
        Properties properties = new Properties();
        Collectibles[] rewardProperties = new Collectibles[11];
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/MysticalSkyRewards.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        for (int i = 0; i < 11; i++) {

            String reward = properties.getProperty("hit" + (i + 1) + "Reward");
            rewardProperties[i] = Collectibles.getCollectibleFromString(reward);
            if (rewardProperties[i] != null) {
                rewardValues[i] = rewardProperties[i].toString();
            } else {
                rewardValues[i] = "  ";
            }


        }
        return rewardProperties;
    }

    /**
     * Returns the name of the realm as a String.
     *
     * @return The name of the realm.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the color of the realm.
     *
     * @return The color of the realm.
     */
    @Override
    public GameColor getColor() {
        return REALM_GAME_COLOR;
    }

    /**
     * Retrieves the status of the realm.
     *
     * @return the status of the realm as an integer
     */
    @Override
    public int getStatus() {
        return 0;
    }

    /**
     * Checks if the realm is available based on the current number of hits.
     *
     * @return true if the realm is available, false otherwise.
     */
    @Override
    public boolean isRealmAvailable() {
        return counterHits < 11;
    }

    /**
     * This method retrieves the reward for the realm.
     *
     * @return An array of Collectibles representing the reward for the realm.
     */
    @Override
    public Collectibles[] getReward() {
        Collectibles[] temp = new Collectibles[1];
        temp[0] = collectibles[counterHits - 1];
        return temp;
    }

    /**
     * Checks whether there is a reward or not after attacking. If a reward is found,
     * it updates the rewardValues array and increments the noElementalCrests counter
     * if the reward is an instance of ElementalCrest.
     *
     * @return true if there is a reward, false otherwise.
     */
    @Override
    public boolean checkReward() {
        if (collectibles[counterHits - 1] != null) {
            if (collectibles[counterHits - 1] instanceof ElementalCrest) {
                noElementalCrests++;
            }
            rewardValues[counterHits - 1] = "X ";

            return true;
        }
        return false;

    }

    /**
     * Executes an attack move on the specified realm.
     *
     * @param move The move to be executed.
     * @return true if the attack was successful, false otherwise.
     */
    @Override
    public boolean attack(Move move) {
        if (isRealmAvailable()) {
            boolean flag = false;
            for (Move i : realmPossibleMoves) {
                if (move != null && i.getDice().getRealm() == move.getDice().getRealm() && i.getDice().getValue() == move.getDice().getValue()) {
                    flag = true;
                    break;
                }

            }
            if (!flag) {
                return false;
            }
            updatePossibleMoves(move);
            phoenix.attack();
            int attackScore = move.getDice().getValue();
            if (attackScore == 6) {
                attackValues[counterHits] = String.valueOf(0);
            } else {
                attackValues[counterHits] = String.valueOf(attackScore);
            }
            score[counterHits] = attackScore;
            totalRealmScore += attackScore;
            counterHits++;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Returns the total score for the realm.
     *
     * @return the total score for the realm as an integer
     */
    @Override
    public int getTotalScore() {
        return totalRealmScore;
    }

    /**
     * Returns the number of elemental crests for a realm.
     *
     * @return The number of elemental crests for the realm.
     */
    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    /**
     * Returns a string representation of the Mystical Sky: Majestic Phoenix realm.
     *
     * @return a string representation of the realm.
     */
    @Override
    public String toString() {
        return String.format("Mystical Sky: Majestic Phoenix (MAGENTA REALM):\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  #  |1    |2    |3    |4    |5    |6    |7    |8    |9    |10   |11   |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  H  |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |%d    |\n" +
                        "|  C  |<    |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |<%s   |\n" +
                        "|  R  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n\n\n",
                score[0], score[1], score[2], score[3], score[4], score[5], score[6], score[7], score[8], score[9], score[10],
                attackValues[0], attackValues[1], attackValues[2], attackValues[3], attackValues[4], attackValues[5], attackValues[6], attackValues[7], attackValues[8], attackValues[9],
                rewardValues[0], rewardValues[1], rewardValues[2], rewardValues[3], rewardValues[4], rewardValues[5], rewardValues[6], rewardValues[7], rewardValues[8], rewardValues[9], rewardValues[10]);
    }


    /**
     * This method returns an array of Moves representing the possible moves in the Magenta Realm.
     *
     * @return an array of Moves representing the possible moves in the Magenta Realm.
     */
    @Override
    public Move[] getRealmMoves() {
        if(isRealmAvailable()){
            return realmPossibleMoves;
        }
        return new Move[0];
    }

    /**
     * Retrieves a Creature based on a Dice.
     *
     * @param dice The Dice object to determine the realm.
     * @return A Creature object corresponding to the realm of the Dice.
     */
    @Override
    public Creature getCreature(Dice dice) {
        if (dice.getRealm() == GameColor.MAGENTA && (dice.getValue() <= 6 && dice.getValue() >= 1)) {
            return phoenix;
        }
        return null;
    }

    /**
     * Retrieves the fake score from the given move.
     *
     * @param move the move containing the dice value
     * @return the fake score from the move
     */
    @Override
    public int getFakeScore(Move move) {
        int attackScore = move.getDice().getValue();
        return attackScore;
    }

    /**
     * Returns an array of score values for the Magenta Realm.
     *
     * @return An array of integers representing the score values for the Magenta Realm.
     */
    public int[] getScoreValues(){
        return score;
    }
    /**
     * Retrieves the reward values associated with each hit score in the Magenta Realm.
     *
     * @return an array of Strings representing the reward values
     */
    public String[] getRewardValues(){
        return rewardValues;
    }
    /**
     * Retrieves an array of Collectibles.
     *
     * @return An array of Collectibles.
     */
    public Collectibles[] getCollectibles(){
        return collectibles;
    }

}

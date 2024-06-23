package game.realms;

import game.collectibles.Collectibles;
import game.collectibles.ElementalCrest;
import game.creatures.Creature;
import game.creatures.Guardian;
import game.dice.Dice;
import game.dice.GreenDice;
import game.engine.Move;
import game.utilities.GameColor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class GreenRealm extends Realm {

    private static final GameColor REALM_GAME_COLOR = GameColor.GREEN;
    // -----------------------Attributes-----------------------//
    private final Guardian[][] mainArray;
    private final Object[] rowRewards;
    private final Guardian[] gardians;
    private final Object[] colRewards;
    private final LinkedList<Move> possibleMoves;
    Collectibles[] currentRewards;
    private int[] score;
    private int count;
    private int noElementalCrests;

    /**
     * Initializes a GreenRealm object.
     *
     * The GreenRealm class represents a realm in the game. It contains a grid of Guardians,
     * each with their own score and status. The grid is initialized with deadGuardian and
     * gardians objects. The realm also has rowRewards and colRewards arrays for storing rewards.
     * The score array stores the attack scores of the Guardians. The possibleMoves list stores
     * the valid moves in the realm.
     *
     * This constructor initializes all the necessary objects and arrays in the class.
     * It sets the score array, initializes the Guardians, loads properties from files,
     * and initializes the mainArray grid with the deadGuardian and gardians objects.
     *
     * @see Guardian
     * @see DeadGuardian
     * @see Move
     */
    // -----------------------Constructor-----------------------//
    public GreenRealm() {

        this.score = new int[11];
        this.possibleMoves = new LinkedList<>();
        this.rowRewards = new Object[3];
        this.colRewards = new Object[4];
        count = 0;
        gardians = new Guardian[11];
        for (int i = 2; i < 13; i++) {
            gardians[i - 2] = new Guardian(i);
        }
        Guardian deadGuardian = new Guardian();
        deadGuardian.attack();
        loadProperties();
        this.mainArray = new Guardian[][]{
                {deadGuardian, gardians[0], gardians[1], gardians[2]},
                {gardians[3], gardians[4], gardians[5], gardians[6]},
                {gardians[7], gardians[8], gardians[9], gardians[10]}
        };
        for (Guardian gardian : gardians) {
            possibleMoves.add(new Move(new GreenDice(gardian.getScore()), gardian));
        }
    }

    /**
     * Returns the name of the realm as a String.
     *
     * @return The name of the realm.
     */
    // -----------------------Methods-----------------------//
    @Override
    public String getName() {
        return "\u001B[32m" + "Green Realm" + "\u001B[0m";
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
     * Returns the status of the realm.
     *
     * @return the status of the realm as an integer
     */
    @Override
    public int getStatus() {
        return 0;
    }

    /**
     * Checks if the realm is available.
     *
     * @return true if the realm is available, false otherwise.
     */
    @Override
    public boolean isRealmAvailable() {
        return count < 12;
    }

    /**
     * Retrieves the rewards obtained from the realm.
     *
     * @return An array of Collectibles representing the rewards obtained from the realm.
     */
    @Override
    public Collectibles[] getReward() {
        return currentRewards;
    }

    /**
     * Checks for rewards in the GreenRealm.
     *
     * @return {@code true} if there are rewards, otherwise {@code false}.
     */
    @Override
    public boolean checkReward() {
        LinkedList<Collectibles> rewards = new LinkedList<>();
        for (int i = 0; i < mainArray.length; i++) {
            int rowSum = 0;
            int j;
            for (j = 0; j < mainArray[i].length; j++) {
                if (!mainArray[i][j].isAlive()) {
                    rowSum += 1;
                }
            }
            if (rowSum == 4) {
                Object rowReward = rowRewards[i];
                if (rowReward != "X ") {
                    if (rowReward instanceof ElementalCrest) {
                        noElementalCrests++;
                    }
                    rewards.add((Collectibles) rowReward);
                    rowRewards[i] = "X ";
                }

            }
        }

        // Check columns
        for (int j = 0; j < mainArray[0].length; j++) {
            int columnSum = 0;
            int i;
            for (i = 0; i < mainArray.length; i++) {
                if (!mainArray[i][j].isAlive()) {
                    columnSum += 1;
                }
            }
            if (columnSum == 3) {
                Object colReward = colRewards[j];
                if (colReward != "X ") {
                    if (colReward instanceof ElementalCrest) {
                        noElementalCrests++;
                    }
                    rewards.add((Collectibles) colReward);
                    colRewards[j] = "X ";
                }

            }
        }
        this.currentRewards = rewards.toArray(Collectibles[]::new);
        return currentRewards.length != 0;
    }

    /**
     * Checks if an attack is possible and executes it.
     *
     * @param move the Move object representing the attack
     * @return true if the attack is successfully executed, false otherwise
     */
    @Override
    public boolean attack(Move move) {
        if (isRealmAvailable()) {
            if (possibleMoves.contains(move)) {
                possibleMoves.remove(move);
                for (Guardian[] guardians : mainArray) {
                    for (Guardian guardian : guardians) {
                        if (guardian.getScore() == move.getDice().getValue()) {
                            guardian.attack();
                            count++;
                            move.execute();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the total score of the GreenRealm.
     *
     * @return The total score, which is obtained by summing up the scores of all previous attacks. If no attacks have been made, returns 0.
     */
    @Override
    public int getTotalScore() {
        if (count == 0) {
            return 0;
        }
        return score[count - 1];
    }

    /**
     * Returns the number of elemental crests for the GreenRealm.
     *
     * @return The number of elemental crests for the GreenRealm.
     */
    @Override
    public int getNoElementalCrests() {
        return noElementalCrests;
    }

    /**
     * Returns a string representation of the GreenRealm object.
     *
     * The string representation includes the layout of the GreenRealm grid, the rewards
     * for each row and column, and the current score.
     *
     * @return a string representation of the GreenRealm object
     */
    @Override
    public String toString() {
        return String.format("Terra's Heartland: Gaia Guardians (GREEN REALM):\n" +
                        "+-----------------------------------+\n" +
                        "|  #  |1    |2    |3    |4    |R    |\n" +
                        "+-----------------------------------+\n" +
                        "|  1  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  2  |%s    |%s    |%s    |%s    |%s   |\n" +
                        "|  3  |%s    |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------+\n" +
                        "|  R  |%s   |%s   |%s   |%s   |     |\n" +
                        "+-----------------------------------------------------------------------+\n" +
                        "|  S  |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |%s   |\n" +
                        "+-----------------------------------------------------------------------+\n\n\n",
                mainArray[0][0], mainArray[0][1], mainArray[0][2], mainArray[0][3], rowRewards[0],
                mainArray[1][0], mainArray[1][1], mainArray[1][2], mainArray[1][3], rowRewards[1],
                mainArray[2][0], (mainArray[2][1].toString().length() == 1) ? mainArray[2][1] + " " : mainArray[2][1], (mainArray[2][2].toString().length() == 1) ? mainArray[2][2] + " " : mainArray[2][2], (mainArray[2][3].toString().length() == 1) ? mainArray[2][3] + " " : mainArray[2][3], rowRewards[2],
                colRewards[0], colRewards[1], colRewards[2], colRewards[3],
                score[0] < 10 ? score[0] + " " : score[0], score[1] < 10 ? score[1] + " " : score[1], score[2] < 10 ? score[2] + " " : score[2], score[3] < 10 ? score[3] + " " : score[3], score[4] < 10 ? score[4] + " " : score[4], score[5] < 10 ? score[5] + " " : score[5], score[6] < 10 ? score[6] + " " : score[6], score[7] < 10 ? score[7] + " " : score[7], score[8] < 10 ? score[8] + " " : score[8], score[9] < 10 ? score[9] + " " : score[9], score[10] < 10 ? score[10] + " " : score[10]
        );
    }
    /**
     * Retrieves the main array of Guardian objects.
     *
     * @return the 2D array of Guardian objects representing the main array
     */
    public Guardian[][] getMainArray(){
        return mainArray;
    }
    /**
     * Retrieves the column rewards of the GreenRealm object.
     *
     * @return An array of Objects representing the column rewards of the GreenRealm.
     */
    public Object[] getColRewards(){
        return colRewards;
    }
    /**
     * Returns the score for the realm as an array of integers.
     *
     * @return an array of integers representing the score for the realm.
     */
    public int[] getScore(){
        return score;
    }
    /**
     * Returns the row rewards for the GreenRealm.
     *
     * @return The row rewards as an array of objects.
     */
    public Object[] getRowRewards(){
        return rowRewards;
    }


    /**
     * Retrieves an array of possible Move objects for the realm.
     *
     * @return the array of possible Move objects
     */
    @Override
    public Move[] getRealmMoves() {
        return possibleMoves.toArray(Move[]::new);
    }

    /**
     * Retrieves a Creature based on a Dice.
     *
     * @param dice The Dice object to determine the realm.
     * @return A Creature object corresponding to the realm of the Dice, or null if no creature is found.
     */
    @Override
    public Creature getCreature(Dice dice) {
        for (Guardian[] guardians : mainArray) {
            for (Guardian guardian : guardians) {
                if (guardian.getScore() == dice.getValue()) {
                    return guardian;
                }
            }
        }
        return null;
    }

    /**
     * Calculates the fake score for a given Move object.
     *
     * @param move the Move object to calculate the fake score for
     * @return the fake score for the Move object
     */
    @Override
    public int getFakeScore(Move move) {
        int prev=(count==0)?0:score[count-1];
        return score[count]-prev;
    }

    /**
     * Returns a list of all living Guardian creatures in the GreenRealm.
     *
     * @return A LinkedList of living Guardian creatures.
     */
    public LinkedList<Guardian> getAliveCreatures() {
        LinkedList<Guardian> aliveGardians = new LinkedList<>();
        for (Guardian g : gardians) {
            if (g.isAlive()) {
                aliveGardians.add(g);
            }
        }
        return aliveGardians;
    }

    /**
     * Retrieves an array of all creatures in the GreenRealm.
     *
     * @return An array of Guardian objects representing all the creatures in the GreenRealm.
     */
    public Guardian[] getAllCreatures() {
        return gardians;
    }

    /**
     * Loads the properties from the config files and initializes the rewards and scores.
     * If the files are not found, an error message is printed.
     * The method uses the Collectibles.getCollectibleFromString method to convert string representations
     * of rewards into instances of the Collectibles class.
     */
    private void loadProperties() {
        Properties properties = new Properties();
        Properties gaiaScoreProperties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/TerrasHeartlandRewards.properties");
            FileInputStream gaiaScoreStream = new FileInputStream("src/main/resources/config/TerrasHeartlandScores.properties");
            properties.load(fileInputStream);
            gaiaScoreProperties.load(gaiaScoreStream);
            fileInputStream.close();
            gaiaScoreStream.close();
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
        //Row rewards
        for (int i = 0; i < 3; i++) {
            String reward = properties.getProperty("row" + (i + 1) + "Reward");
            Collectibles collectible = Collectibles.getCollectibleFromString(reward);
            rowRewards[i] = (collectible == null) ? "X " : collectible;
        }
        for (int i = 0; i < 4; i++) {
            String reward = properties.getProperty("column" + (i + 1) + "Reward");
            Collectibles collectible = Collectibles.getCollectibleFromString(reward);
            colRewards[i] = (collectible == null) ? "X " : collectible;
        }
        try {
            for (int i = 0; i < score.length; i++) {
                score[i] = Integer.parseInt(gaiaScoreProperties.getProperty("attack" + (i + 1)));
            }
        } catch (NumberFormatException e) {
            score = new int[]{1, 2, 4, 7, 11, 16, 22, 29, 37, 46, 56};
        }

    }
}

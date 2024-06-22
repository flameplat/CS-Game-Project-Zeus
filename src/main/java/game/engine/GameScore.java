package game.engine;

import game.realms.*;
import game.utilities.GameColor;


public class GameScore {
    /**
     * The playerName variable represents the name of the player.
     *
     * <p>
     * Example usage:
     * </p>
     * <pre>
     *     String playerName = gameScore.getPlayerName();
     * </pre>
     *
     * <p>
     * The playerName variable is a String and is declared as private final, meaning its value cannot be changed once initialized and can only be accessed within the class where it
     *  is declared.
     * </p>
     *
     * <p>
     * It is used in the GameScore class to store the name of the player. The player's name is used to identify the player and may be displayed during the game or in the final score
     * .
     * </p>
     */
    private final String playerName;
    /**
     * The realms variable is an array of Realm objects.
     *
     * Each element of the array represents a realm in the game. A realm is an abstract class that represents a specific
     * area or domain in the game. The Realm class provides various methods to interact with the realms in the game, such
     * as getting the name of the realm, checking its availability, attacking it, getting rewards, and more.
     *
     * The array of realms is used to store and manage all the realms in the game. It allows for easy access and
     * manipulation of each realm individually.
     *
     * Usage example:
     *   // Create an array of realms
     *   Realm[] realms = new Realm[3];
     *
     *   // Initialize each element of the array with specific realm objects
     *   realms[0] = new YellowRealm();
     *   realms[1] = new GreenRealm();
     *   realms[2] = new RedRealm();
     *
     *   // Access a specific realm and perform actions
     *   Realm realm = realms[0];
     *   String realmName = realm.getName();
     *   int realmStatus = realm.getStatus();
     *
     * Note:
     *   The Realm class is an abstract class, and you cannot create instances of it directly. Instead, you need to create
     *   instances of its subclasses (e.g., YellowRealm, GreenRealm, RedRealm, etc.) and add them to the array of realms.
     *
     * @see Realm
     * @see YellowRealm
     * @see GreenRealm
     * @see RedRealm
     * @see MagentaRealm
     * @see BlueRealm
     */
    private final Realm[] realms;
    /**
     * Represents the total number of Elemental Crests.
     */
    //--------------------------Attributes--------------------------//
    // all of the scores for the Realms
    private int totalElementalCrests;
    /**
     * The totalScore variable represents the total score of the game.
     * It is an integer value that keeps track of the cumulative score of the player.
     * Each successful action or achievement in the game adds to the totalScore.
     *
     * The totalScore variable is private and can only be accessed within the GameScore class.
     */
    private int totalScore;
    /**
     * The `cheatPenalty` variable is a private integer in the `GameScore` class.
     *
     * It represents the penalty points applied to a player's score if they are caught cheating.
     *
     * The value of `cheatPenalty` can be accessed through the getter and modified through the setter methods of the `GameScore` class.
     */
    private int cheatPenalty;
    /**
     * Represents a variable named "test" of type int.
     * This variable is declared as private, meaning it can only be accessed within the same class.
     *
     * The variable "test" is likely used to store a numerical value for testing purposes.
     * The purpose or meaning of this variable is not clear from the given information.
     *
     * It is recommended to review the containing class "GameScore" and its methods to understand
     * the context and usage of the "test" variable.
     */
    private int test;

    /**
     * The GameScore class represents the game score of a player.
     * It manages the player's name, realms, total elemental crests,
     * total score, and cheat penalty.
     */
    //--------------------------Constructor--------------------------//
    public GameScore(Realm[] realms, String playerName) {
        this.playerName = playerName;
        this.realms = realms;
        totalElementalCrests = 0;
        totalScore = 0;
        this.cheatPenalty = 0;
    }

    /**
     * Class representing the game scores.
     */
    public GameScore() {
        this.realms = new Realm[]{new RedRealm(), new GreenRealm(), new BlueRealm(), new MagentaRealm(), new YellowRealm()};
        this.playerName = "NULL";
        totalElementalCrests = 0;
        totalScore = 0;
        this.cheatPenalty = 0;

    }
    //--------------------------Methods--------------------------//


    /**
     * Update the game score by calculating the total score based on the scores of each realm.
     */
    public void updateGameScore() {
        totalScore = 0;
        for (Realm realm : realms) {
            totalScore += realm.getTotalScore();
        }
    }

    /**
     * Increases the count of total elemental crests received by the player.
     * This method is called when the player receives an elemental crest as a collectible.
     */
    public void receiveElementalCrest() {
        totalElementalCrests++;
    }

    /**
     * Returns the total score for the Yellow realm.
     *
     * @return the total score for the Yellow realm as an integer
     */
    public int getYellowRealmScore() {
        return realms[GameColor.YELLOW.ordinal()].getTotalScore();
    }

    /**
     * Returns the total score for the green realm.
     *
     * @return the total score for the green realm as an integer
     */
    public int getGreenRealmScore() {
        return realms[GameColor.GREEN.ordinal()].getTotalScore();
    }

    /**
     * Returns the total score of the Red realm.
     *
     * @return the total score of the Red realm as an integer
     */
    public int getRedRealmScore() {
        return realms[GameColor.RED.ordinal()].getTotalScore();
    }

    /**
     * Retrieves the total score for the Magenta realm.
     *
     * @return The total score for the Magenta realm as an integer.
     */
    public int getMagentaRealmScore() {
        return realms[GameColor.MAGENTA.ordinal()].getTotalScore();
    }

    /**
     * Retrieves the total score of the blue realm for the current game.
     *
     * @return the total score of the blue realm as an integer
     */
    public int getBlueRealmScore() {
        return realms[GameColor.BLUE.ordinal()].getTotalScore();
    }

    /**
     * Sets the cheat penalty for the game score.
     *
     * @param cheatPenalty The cheat penalty to be set.
     */
    public void setCheatPenalty(int cheatPenalty) {
        this.cheatPenalty = cheatPenalty;
    }

    /**
     * Retrieves the total number of elemental crests collected by the player.
     *
     * @return the total number of elemental crests collected by the player.
     */
    public int getTotalElementalCrests() {
        return totalElementalCrests;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return the current score of the game after applying the cheat penalty.
     */
    public int getCurrentScore() {
        updateGameScore();
        return totalScore - (cheatPenalty * totalScore) / 100;
    }

    /**
     * Returns the total score of the game. The method calculates the total score by updating the game score,
     * finding the minimum score among all realms, and then adding the product of the total elemental crests and the minimum score
     * to the total score. Finally, a cheat penalty is applied to the total score and the result is returned.
     *
     * @return the total score of the game as an integer.
     */
    public int getTotalScore() {
        updateGameScore();
        int minScore = realms[0].getTotalScore();
        for (int i = 0; i < 5; i++) {
            if (realms[i].getTotalScore() < minScore) {
                minScore = realms[i].getTotalScore();
            }
        }
        totalScore += totalElementalCrests * minScore;
        return totalScore - (cheatPenalty * totalScore) / 100;
    }

    /**
     * Returns the total score for a specific color of realm.
     *
     * @param gameColor the color of the realm
     * @return the total score for the specified color as an integer
     */
    private int getTotalScoreForColor(GameColor gameColor) {
        return realms[gameColor.ordinal()].getTotalScore();
    }

    /**
     * Returns a string representation of the GameScore object.
     * The string representation includes the playerName, realm information, total elemental crests,
     * and final score.
     *
     * @return a string representation of the GameScore object.
     */
    @Override
    public String toString() {
        // we tried to make the endgame scoresheet looks like a table
        StringBuilder sb = new StringBuilder();

        // player name, row
        sb.append(String.format("%-20s", "Player Name:")).append(playerName).append("\n");

        // realm header
        sb.append(String.format("%-20s", "Realm"));
        sb.append(String.format("%-20s", "Elemental Crests"));
        sb.append(String.format("%-20s", "Total Score")).append("\n");

        String[] colors = {"Red", "Green", "Blue", "Magenta", "Yellow"};
        for (int i = 0; i < 5; i++) {
            sb.append(String.format("%-20s", colors[i] + " Realm"));
            sb.append(String.format("%-20d", realms[i].getNoElementalCrests()));
            sb.append(String.format("%-20d", getTotalScoreForColor(GameColor.values()[i]))).append("\n");
        }
        sb.append("-".repeat(45)).append("\n");

        // elemental crest row
        sb.append(String.format("%-40s", "Total Elemental Crests")).append(getTotalElementalCrests()).append("\n");

        // final score row
        sb.append(String.format("%-40s", "Final Score")).append(getTotalScore()).append("\n");

        return sb.toString();
    }
}

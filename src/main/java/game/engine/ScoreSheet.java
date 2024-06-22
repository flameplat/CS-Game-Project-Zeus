package game.engine;


import game.creatures.Creature;
import game.dice.Dice;
import game.realms.Realm;
import game.utilities.GameColor;

public class ScoreSheet {
    private static final String RED_COLOR = "\u001B[31m";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String BLUE_COLOR = "\u001B[34m";
    private static final String MAGENTA_COLOR = "\u001B[35m";
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String RESET_COLOR = "\u001B[0m";
    //--------------------------Attributes--------------------------//
    private final Realm[] realms;
    /**
     * The formatted string representation of a ScoreSheet object.
     */
    private String string;

    /**
     * The ScoreSheet class represents a score sheet for a game.
     * It manages the display of different realms and their scores.
     *
     * @param realmsReference An array of Realm objects representing the realms to be displayed.
     */
    //--------------------------Constructor--------------------------//
    public ScoreSheet(Realm[] realmsReference) {
        this.realms = realmsReference;
    }

    /**
     * Updates the score sheet with the information from the realms.
     * The updated score sheet includes the name and score of each realm.
     * The method appends the updated score sheet to the existing string.
     */
    //--------------------------Methods--------------------------//
    private void updateScoreSheet() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nScoreSheet\n\n");
        for (Realm realm : realms) {
            stringBuilder.append(realm.toString());
        }
        string = stringBuilder.toString();
    }

    /**
     * Displays the score sheet by updating it and printing the contents of each realm in different colors.
     */
    public void displayScoreSheet() {
        updateScoreSheet();
        displayRedRealm();
        displayGreenRealm();
        displayBlueRealm();
        displayMagentaRealm();
        displayYellowRealm();
    }

    /**
     * Displays the red realm in the score sheet.
     */
    public void displayRedRealm() {
        System.out.println(RED_COLOR + realms[0] + RESET_COLOR);
    }

    /**
     * Display the green realm on the screen.
     */
    public void displayGreenRealm() {
        System.out.println(GREEN_COLOR + realms[1] + RESET_COLOR);
    }

    /**
     * Method to display the blue realm in the score sheet.
     */
    public void displayBlueRealm() {
        System.out.println(BLUE_COLOR + realms[2] + RESET_COLOR);
    }

    /**
     * This method displays the information of the Magenta Realm.
     * It prints the name and status of the Magenta Realm in magenta color.
     */
    public void displayMagentaRealm() {
        System.out.println(MAGENTA_COLOR + realms[3] + RESET_COLOR);
    }

    /**
     * Displays the Yellow Realm on the score sheet.
     */
    public void displayYellowRealm() {
        System.out.println(YELLOW_COLOR + realms[4] + RESET_COLOR);
    }

    /**
     * Returns a string representation of the ScoreSheet object.
     * The ScoreSheet is updated before converting it to a string.
     *
     * @return the string representation of the ScoreSheet object.
     */
    @Override
    public String toString() {
        updateScoreSheet();
        return string;
    }

    /**
     * Retrieves a Creature based on a Dice representing the realm.
     *
     * @param dice The Dice object to determine the realm.
     * @return A Creature object corresponding to the realm of the Dice. Returns null if the realm is not found or if the realm is white.
     */
    public Creature getCreatureByRealm(Dice dice) {
        if (dice.getRealm() == GameColor.WHITE) {
            System.err.println("There is no white realm");
            return null;
        }
        for (Realm i : realms) {
            if (dice.getRealm().equals(i.getColor())) {
                return i.getCreature(dice);
            }
        }
        System.err.println("Creature not found");
        return null;
    }


}



package game.engine;


import game.creatures.Creature;
import game.dice.Dice;
import game.realms.Realm;
import game.utilities.Color;

public class ScoreSheet {
    private static final String RED_COLOR = "\u001B[31m";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String BLUE_COLOR = "\u001B[34m";
    private static final String MAGENTA_COLOR = "\u001B[35m";
    private static final String YELLOW_COLOR = "\u001B[33m";
    private static final String RESET_COLOR = "\u001B[0m";
    //We will create linked list of type realm/action and everytime we will update the linked list
    //--------------------------Attributes--------------------------//
    private final Realm[] realms;
    private String string;

    //--------------------------Constructor--------------------------//
    public ScoreSheet(Realm[] realmsReference) {
        this.realms = realmsReference;
    }

    //--------------------------Methods--------------------------//
    private void updateScoreSheet() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nScoreSheet\n\n");
        for (Realm realm : realms) {
            stringBuilder.append(realm.toString());
        }
        string = stringBuilder.toString();
    }

    public void displayScoreSheet() {
        updateScoreSheet();
        displayRedRealm();
        displayGreenRealm();
        displayBlueRealm();
        displayMagentaRealm();
        displayYellowRealm();
    }

    public void displayRedRealm() {
        System.out.println(RED_COLOR + realms[0] + RESET_COLOR);
    }

    public void displayGreenRealm() {
        System.out.println(GREEN_COLOR + realms[1] + RESET_COLOR);
    }

    public void displayBlueRealm() {
        System.out.println(BLUE_COLOR + realms[2] + RESET_COLOR);
    }

    public void displayMagentaRealm() {
        System.out.println(MAGENTA_COLOR + realms[3] + RESET_COLOR);
    }

    public void displayYellowRealm() {
        System.out.println(YELLOW_COLOR + realms[4] + RESET_COLOR);
    }

    @Override
    public String toString() {
        updateScoreSheet();
        return string;
    }

    public Creature getCreatureByRealm(Dice dice) {
        if (dice.getRealm() == Color.WHITE) {
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



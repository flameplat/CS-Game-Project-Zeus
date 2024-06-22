package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.ColorBonus;
import game.collectibles.EssenceBonus;
import game.collectibles.TimeWarp;
import game.creatures.Creature;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameGuide {
    private static final String GAME = String.format("Welcome to Dice Realms: Quest for the Elemental Crests! " +
            "In this game, two players become wizards striving to conquer elemental realms " +
            "and collect Elemental Crests. Players encounter creatures like Pyroclast Dragons, Gaia Guardians," +
            "Hydra Serpents, Majestic Phoenixes, and Solar Lions. To win, employ magic, strategy, and" +
            "cunning to defeat creatures and claim crests. The goal: emerge as the most powerful mage in Eldoria.");
    private static final String ROUND = "You have maximum 3 rolls for the given dice, choose wisely";
    private static final String TURN = "Roll the available dice then choose one of them to spell";
    private static final String FORGOTTEN_REALM = "Choose a dice from the forgotten realm";
    private static final String PASSIVE_TURN = "Now you have to play your passive turn";
    private static final String COLOR_BONUS = ColorBonus.getInstruction();
    private static final String AB_POWER = ArcaneBoost.getInstruction();
    private static final String TW_POWER = TimeWarp.getInstruction();
    private static final String ESSENCE_BONUS = EssenceBonus.getInstruction();
    private static final String ROLL = "Press Enter to roll";
    private final Scanner sc;
    public GameGuide() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays the menu options for the game.
     */
    public void displayMenu() {
        System.out.println("Dice Realms: Quest for the Elemental Crests!");
        System.out.println("(1) Start Game\n(2) Exit Game");
    }

    /**
     * Displays a numbered choice for each element in the given array.
     *
     * @param array The array containing the choices to be displayed.
     */
    public void displayNumberedChoice(Object[] array) {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < array.length; i++) {
            result.append(i + 1).append("-");
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        System.out.println(result);
    }

    /**
     * Displays the corresponding instructions based on the given instruction type.
     *
     * @param instruction The instruction type to display.
     */
    public void displayInstructions(Instruction instruction) {
        String output;
        switch (instruction) {
            case GAME:
                output = GAME;
                break;
            case ROUND:
                output = ROUND;
                break;
            case TURN:
                output = TURN;
                break;
            case FORGOTTEN_REALM:
                output = FORGOTTEN_REALM;
                break;
            case AB_POWER:
                output = ArcaneBoost.getInstruction();
                break;
            case TW_POWER:
                output = TimeWarp.getInstruction();
                break;
            case PASSIVE_TURN:
                output = PASSIVE_TURN;
                break;
            case COLOR_BONUS:
                output = COLOR_BONUS;
                break;
            case AB_PROMPT:
                output = AB_POWER;
                break;
            case TW_PROMPT:
                output = TW_POWER;
                break;
            case ESSENCE_BONUS:
                output = ESSENCE_BONUS;
                break;
            case ROLL:
                output = ROLL;
                break;
            default:
                output = "";
        }
        System.out.println(output);
    }
    /**
     * Returns the instruction associated with the given Instruction enum.
     *
     * @param instruction The Instruction enum value to get the instruction for.
     * @return The instruction associated with the given Instruction enum.
     */
    public String getInstruction(Instruction instruction){
        String output;
        switch (instruction) {
            case GAME:
                output = GAME;
                break;
            case ROUND:
                output = ROUND;
                break;
            case TURN:
                output = TURN;
                break;
            case FORGOTTEN_REALM:
                output = FORGOTTEN_REALM;
                break;
            case AB_POWER:
                output = ArcaneBoost.getInstruction();
                break;
            case TW_POWER:
                output = TimeWarp.getInstruction();
                break;
            case PASSIVE_TURN:
                output = PASSIVE_TURN;
                break;
            case COLOR_BONUS:
                output = COLOR_BONUS;
                break;
            case AB_PROMPT:
                output = AB_POWER;
                break;
            case TW_PROMPT:
                output = TW_POWER;
                break;
            case ESSENCE_BONUS:
                output = ESSENCE_BONUS;
                break;
            case ROLL:
                output = ROLL;
                break;
            default:
                output = "";
        }
        return output;
    }

    /**
     * Gets the user's choice within a specified range.
     *
     * @param minBound The minimum value of the range (inclusive).
     * @param maxBound The maximum value of the range (inclusive).
     * @return The user's valid choice within the specified range.
     */
    public int getUserChoice(int minBound, int maxBound) {
        if (minBound > maxBound) {
            int temp = minBound;
            minBound = maxBound;
            maxBound = temp;
        }
        int validValue;
        while (true) {
            try {
                System.out.printf("Enter valid number from %d to %d%n", minBound, maxBound);
                validValue = sc.nextInt();
                if (validValue <= maxBound && validValue >= minBound) {
                    break;
                }

            } catch (InputMismatchException e) {
                sc.nextLine(); //Clears buffer
            }

        }
        return validValue;

    }

    /**
     * Displays the names of creatures in an array.
     *
     * @param creatures An array of Creature objects.
     */
    public void displayCreatures(Creature[] creatures) {
        System.out.print("[");
        for (int i = 0; i < creatures.length; i++) {
            System.out.print(creatures[i].getName());
            if (i < creatures.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Displays a menu to the user with two options (Yes and No) and prompts the user to select a choice.
     *
     * @return true if the user selects Yes, false if the user selects No
     */
    public boolean getUserBooleanChoice() {
        System.out.printf("(1) Yes%n(2) No%n");
        int choice = getUserChoice(1, 2);
        return choice == 1;
    }

    /**
     * Closes the scanner used by the game.
     */
    public void closeScanner() {
        sc.close();
    }
    /**
     * Displays the available game modes: Multiplayer and Singleplayer.
     */
    public void displayGameMode(){
        System.out.printf("(1) Multiplayer%n(2) Singleplayer%n");
    }
}

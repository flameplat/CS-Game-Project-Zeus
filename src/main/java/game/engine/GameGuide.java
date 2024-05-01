package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.TimeWarp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameGuide {
    private final Scanner sc;
    private static final String GAME = String.format("Welcome to Dice Realms: Quest for the Elemental Crests!%n " +
            "In this game, two players become wizards striving to conquer elemental realms %n" +
            "and collect Elemental Crests. Players encounter creatures like Pyroclast Dragons, Gaia Guardians, %n" +
            "Hydra Serpents, Majestic Phoenixes, and Solar Lions. To win, employ magic, strategy, and %n" +
            "cunning to defeat creatures and claim crests. The goal: emerge as the most powerful mage in Eldoria.%n");
    private static final String ROUND = "You have maximum 3 rolls for the given dice, choose wisely";
    private static final String TURN = "Roll the available dice then choose one of them to spell";
    private static final String FORGOTTEN_REALM = "Choose a dice from the forgotten realm";
    private static final String PASSIVE_TURN="Now you have to play your passive turn";
    private static final String COLOR_BONUS=String.format("Congratulations you have received a color bonus,%nyou should now choose a realm to play by choosing a die you have not selected before%n");
    private static final String AB_POWER = ArcaneBoost.getInstruction();
    private static final String TW_POWER = TimeWarp.getInstruction();
    private static final String ROLL="Press R to roll the available dice";
    private static final String SELECT_DICE="Enter the number corresponding to your choice:";


    public GameGuide() {
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Dice Realms: Quest for the Elemental Crests!");
        System.out.println("(1) Start Game\n(2) Exit Game");
    }

    public void displayInstructions(Instruction instruction) {
        String output;
        switch (instruction){
            case GAME: output=GAME;break;
            case ROUND:output=ROUND;break;
            case TURN:output=TURN;break;
            case FORGOTTEN_REALM:output=FORGOTTEN_REALM;break;
            case AB_POWER:output= ArcaneBoost.getInstruction();break;
            case TW_POWER:output= TimeWarp.getInstruction();break;
            case PASSIVE_TURN:output= PASSIVE_TURN ;break;
            case COLOR_BONUS:output= COLOR_BONUS;break;
            case AB_PROMPT:output= AB_POWER;break;
            case TW_PROMPT:output= TW_POWER;break;
            default:output="";
        }
        System.out.println(output);
    }

    public int getUserChoice(int minBound, int maxBound) {
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
    public boolean getUserBooleanChoice(){
        System.out.printf("(1) Yes%n(2) No%n");
        int choice=getUserChoice(1,2);
        return choice==1;
    }
    public int getUserIntChoice() {
        int validValue;
        while (true) {
            try {
                System.out.println("Enter valid number");
                validValue = sc.nextInt();
                break;

            } catch (InputMismatchException e) {
                sc.nextLine(); //Clears buffer
            }

        }
        return validValue;

    }
    public void waitForKeyPress(char character){
        while (true){
            String validchar = sc.nextLine();
            if(validchar.length()==1 && validchar.charAt(0)==character){
                break;
            }


        }
    }
    public void closeScanner(){
        sc.close();
    }
}

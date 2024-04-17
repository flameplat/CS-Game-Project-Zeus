package game.engine;

import game.collectibles.ArcaneBoost;
import game.collectibles.TimeWarp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameGuide {
    private final Scanner sc;

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
            case GAME: output="GAME INSTRUCTIONS";break;
            case ROUND:output="ROUND INSTRUCTIONS";break;
            case TURN:output="TURN INSTRUCTIONS";break;
            case FORGOTTEN_REALM:output="FORGOTTEN REALM INSTRUCTIONS";break;
            case AB_POWER:output= ArcaneBoost.getInstruction();break;
            case TW_POWER:output= TimeWarp.getInstruction();break;
            case PASSIVE_TURN:output= "PASSIVE TURN INSTRUCTIONS";break;
            case COLOR_BONUS:output= "COLOR BONUS INSTRUCTIONS";break;
            case AB_PROMPT:output= "Do you want to use Arcane Boost?";break;
            case TW_PROMPT:output= "Do you want to use Time Warp?";break;
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
    public void closeScanner(){
        sc.close();
    }
}

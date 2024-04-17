package game.engine;

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

    public void displayInstructions() {

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
        sc.close();
        return validValue;

    }
}

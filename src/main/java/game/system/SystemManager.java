package game.system;

import game.exceptions.MissingGameFilesException;

import java.io.File;

public class SystemManager {
    /**
     * The SystemManager class is responsible for performing system-related checks in a game.
     */
    public SystemManager() {

    }

    /**
     * Checks the system by calling the {@link #checkGameFiles()} method.
     * If any game file is missing, it exits the game with an error message.
     *
     * @throws MissingGameFilesException if any game file is not found
     */
    public void checkSystem() {
        try {
            checkGameFiles();
        } catch (MissingGameFilesException e) {
            exit(e.getMessage());
        }
    }

    /**
     * Checks if all the necessary game files exist in the specified file paths.
     * If any of the files are missing, it throws a MissingGameFilesException.
     *
     * @throws MissingGameFilesException if any of the required game files are missing
     */
    public void checkGameFiles() throws MissingGameFilesException {
        String[] filePaths = {"src/main/resources/config/Game.properties",
                "src/main/resources/config/EmberfallDominionRewards.properties",
                "src/main/resources/config/MysticalSkyRewards.properties",
                "src/main/resources/config/RadiantSvannaRewards.properties",
                "src/main/resources/config/RoundsRewards.properties",
                "src/main/resources/config/TerrasHeartlandRewards.properties",
                "src/main/resources/config/TideAbyssRewards.properties"


        };
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new MissingGameFilesException(String.format("File %s is not found", filePath));
            }
        }

    }

    /**
     * Performs system checks to ensure that all necessary files for the game are present.
     * If any required file is missing, the method exits the game.
     */
    public void performSystemChecks() {
        try {
            checkSystem();
        } catch (Exception e) {
            exit(e.getMessage());
        }
    }

    /**
     * Exits the game by terminating the program execution.
     * The method prints a message "Exiting Game..." and then calls
     * {@link System#exit(int)} with the exit code 0.
     */
    public void exit() {
        System.out.println("Exiting Game...");
        System.exit(0);
    }

    /**
     * Prints the error message, displays "Exiting Game..." message, and exits the program.
     *
     * @param error the error message to be displayed before exiting
     */
    public void exit(String error) {
        System.err.println("System Error: " + error);
        System.out.println("Exiting Game...");
        System.exit(1);
    }
}

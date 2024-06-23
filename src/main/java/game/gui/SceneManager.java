package game.gui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SceneManager {
    private final Stage primaryStage;
    private Scene scene;
    private Parent root;
    private static GUIGameController guiGameController;
    public SceneManager(Stage stage) {
        this.primaryStage=stage;
    }

    /**
     * Sets the GUI game controller for the SceneManager.
     *
     * @param guiGameController The GUI game controller to be set.
     */
    public static void setGuiGameController(GUIGameController guiGameController) {
        SceneManager.guiGameController=guiGameController;
    }

    /**
     * This method is used to switch the scene in the application.
     *
     * @param resourceFileName the name of the FXML resource file for the new scene
     * @param isResizable      specifies whether the new scene should be resizable or not
     */
    public void switchScene(String resourceFileName, boolean isResizable){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            root = loader.load();
            GameController controller = loader.getController();
            controller.setSceneManager(this);
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(isResizable);
            if (isResizable || resourceFileName=="Wizards.fxml") {
                Platform.runLater(() -> {
                    Screen screen = Screen.getPrimary();
                    CalculatePositionToCenterStage(screen,primaryStage);
                });

            }
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Calculates the position to center a given Stage on the Screen.
     *
     * @param screen The Screen on which the Stage will be centered.
     * @param currentStage The Stage to be centered.
     */
    private void CalculatePositionToCenterStage(Screen screen,Stage currentStage) {
        double centerX = screen.getBounds().getWidth() / 2 - currentStage.getWidth() / 2;
        //+15 to make window tab appear
        double centerY = screen.getBounds().getHeight() / 2 -currentStage.getHeight() / 2+15;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
    }

    /**
     * Switches the scene to the main menu scene.
     */
    public void switchMainMenuScene(){
        switchScene("MainMenu.fxml", false);
    }

    /**
     *
     */
    public void switchPlayerDataScene(){
        switchScene("PlayerData.fxml", false);
    }

    /**
     *
     */
    public void switchGamePlayScene(){
        switchScene("GamePlay.fxml", true);
    }

    /**
     * Represents a map of stages in the SceneManager class.
     * Each stage is associated with a unique string key.
     */
    private final HashMap<String, Stage> stageMap = new HashMap<>();
    /**
     * Map of scene resource file names to corresponding Scene objects.
     * The keys are the resource file names and the values are Scene objects.
     * This map is used to store and retrieve Scene objects for different scenes in the application.
     */
    private final HashMap<String, Scene> sceneMap = new HashMap<>();

    /**
     * Shows the stage for a given realm.
     *
     * @param resourceFileName the filename of the resource used to create the stage
     * @param enableWindowTab whether to enable the window tab in the stage
     * @param disableExitButton whether to disable the exit button in the stage
     */
    public void showRealmStage(String resourceFileName, boolean enableWindowTab, boolean disableExitButton) {
        try {
            Stage stage = getOrCreateStage(resourceFileName, enableWindowTab);
            if (disableExitButton) {
                stage.setOnCloseRequest(Event::consume);
            }
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an existing stage from the stage map based on the resource file name,
     * or creates a new stage if it doesn't exist. The method loads the FXML file, sets up
     * the controller, sets the position and properties of the stage, and adds the stage
     * to the stage map and scene map if it is a new stage.
     *
     * @param resourceFileName   The name of the FXML resource file for the stage.
     * @param enableWindowTab    Whether to enable the window tab in the stage.
     * @return The existing or newly created Stage object.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    private Stage getOrCreateStage(String resourceFileName, boolean enableWindowTab) throws IOException {
        Stage stage = stageMap.get(resourceFileName);
        if (stage != null) {
            // Retrieve the controller
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            Parent root = loader.load();
            RealmController realmController = loader.getController();
            realmController.setSceneManager(this);
            realmController.resetLabels();
            double shiftForPlayer1 = 600;
            double shiftForPlayer2 = 0;
            if(realmController instanceof Guider){
                Screen screen = Screen.getPrimary();
                CalculatePositionToCenterStage(screen,stage);
            }
            else {
                if (GUIGameController.isPlayer1Playing()) {
                    stage.setX(shiftForPlayer1);
                } else {
                    stage.setX(shiftForPlayer2);
                }
                realmController.setGuiGameController(guiGameController);
                if (realmController instanceof EndGame) {
                    ((EndGame) realmController).setPlayers(guiGameController.getActivePlayer(), guiGameController.getPassivePlayer());
                }
                // Center vertically
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                double centerY = (bounds.getHeight() - stage.getHeight()) / 2;
                stage.setY(100);
            }

        }
        else {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(null);
            RealmController realmController = loader.getController();
            if(realmController instanceof EndGame){
                this.endGame= (EndGame) realmController;
            }
            realmController.setSceneManager(this);
            realmController.setGuiGameController(guiGameController);
            if(realmController instanceof EndGame){
                ((EndGame)realmController).setPlayers(guiGameController.getActivePlayer(),guiGameController.getPassivePlayer());
            }

            stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            if (!enableWindowTab) {
                stage.initStyle(StageStyle.UNDECORATED);
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            double shiftForPlayer1 = 600;
            double shiftForPlayer2 = 0;
            if(realmController instanceof Guider){
                Screen screen = Screen.getPrimary();
                CalculatePositionToCenterStage(screen,stage);
            }
            else {
                if (GUIGameController.isPlayer1Playing()) {
                    stage.setX(shiftForPlayer1);
                } else {
                    stage.setX(shiftForPlayer2);
                }
                realmController.setGuiGameController(guiGameController);
                if (realmController instanceof EndGame) {
                    ((EndGame) realmController).setPlayers(guiGameController.getActivePlayer(), guiGameController.getPassivePlayer());
                }
                // Center vertically
                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getVisualBounds();
                double centerY = (bounds.getHeight() - stage.getHeight()) / 2;
                stage.setY(100);
            }
            stageMap.put(resourceFileName, stage);
            sceneMap.put(resourceFileName, scene);
        }
        return stage;
    }

    /**
     * Shows the Red Realm stage.
     *
     * This method is responsible for displaying the stage related to the
     * Red Realm. It internally calls the {@link #showRealmStage(String, boolean, boolean)} method
     * with the necessary parameters to show the Red Realm stage.
     *
     * @see #showRealmStage(String, boolean, boolean)
     */
    // Methods for showing specific realm stages
    public void showRedRealmStage() {
        showRealmStage("RedRealm.fxml", true, true);
    }

    /**
     * Displays the Green Realm bonus stage.
     *
     * This method calls the {@link #showRealmStage(String, boolean, boolean)} method with the resource file name
     * "GreenBonus.fxml", enabling the window tab and disabling the exit button. It displays the stage and
     * waits for it to be closed.
     */
    public void showGreenRealmStage() {
        showRealmStage("GreenBonus.fxml", true, true);
    }

    /**
     * Displays the Yellow Realm stage.
     * This method calls the showRealmStage method with the resource file name "YellowBonus.fxml",
     * enableWindowTab set to true, and disableExitButton set to true.
     * If an IOException occurs while creating or showing the stage, it is printed to the standard error stream.
     */
    public void showYellowRealmStage() {
        showRealmStage("YellowBonus.fxml", true, true);
    }

    /**
     * Displays the Magenta Realm stage.
     * It calls the {@link #showRealmStage(String, boolean, boolean)} method
     * with the parameters "MagentaBonus.fxml", true, true.
     */
    public void showMagentaRealmStage() {
        showRealmStage("MagentaBonus.fxml", true, true);
    }

    /**
     *
     */
    public void showBlueRealmStage() {
        showRealmStage("BlueBonus.fxml", true, true);
    }

    /**
     * Displays the realm picker stage.
     * This method calls the {showRealmStage} method with the given parameters.
     * The realm picker stage allows the user to choose a realm from a predefined list.
     */
    public void showRealmPickerStage() {
        showRealmStage("RealmPicker.fxml", true, true);
    }

    /**
     * Displays the end game screen to the user.
     * Uses the "EndGame.fxml" file to build the UI stage.
     *
     * The UI stage is shown with the following properties:
     *  - It is resizable
     *  - It is not modal (does not block interaction with other UI elements)
     *
     * Note: No parameters are passed to this method.
     * To make changes to the method behavior, modify the fxml file or
     * update the method implementation.
     */
    public void showEndGame() {
        showRealmStage("EndGame.fxml", true, false);
    }
    /**
     * Represents the end game state in the game.
     */
    private EndGame endGame;
    /**
     * Updates the end game stage.
     * If {@code endGame} is not null, calls the {@code updateEndGame} method on it.
     */
    public void updateEndGameStage(){
        if(endGame!=null){
            endGame.updateEndGame();
        }

    }
    /**
     * Switches the current scene to the Wizards scene.
     * This method uses the switchScene method to switch to the specified
     * FXML file "Wizards.fxml" with the parameter "false".
     */
    public void switchWizardsScene(){
        switchScene("Wizards.fxml",false);
    }


}
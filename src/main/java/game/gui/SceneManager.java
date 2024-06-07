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

    public static void setGuiGameController(GUIGameController guiGameController) {
        SceneManager.guiGameController=guiGameController;
    }

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

    private void CalculatePositionToCenterStage(Screen screen,Stage currentStage) {
        double centerX = screen.getBounds().getWidth() / 2 - currentStage.getWidth() / 2;
        //+15 to make window tab appear
        double centerY = screen.getBounds().getHeight() / 2 -currentStage.getHeight() / 2+15;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
    }

    public void switchMainMenuScene(){
        switchScene("MainMenu.fxml", false);
    }

    public void switchPlayerDataScene(){
        switchScene("PlayerData.fxml", false);
    }

    public void switchGamePlayScene(){
        switchScene("GamePlay.fxml", true);
    }

    private HashMap<String, Stage> stageMap = new HashMap<>();
    private HashMap<String, Scene> sceneMap = new HashMap<>();

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

    private Stage getOrCreateStage(String resourceFileName, boolean enableWindowTab) throws IOException {
        Stage stage = stageMap.get(resourceFileName);
        if (stage == null) {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(null);
            RealmController realmController = loader.getController();
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
            stageMap.put(resourceFileName, stage);
            sceneMap.put(resourceFileName, scene);
        }
        return stage;
    }

    // Methods for showing specific realm stages
    public void showRedRealmStage() {
        showRealmStage("RedRealm.fxml", true, true);
    }

    public void showGreenRealmStage() {
        showRealmStage("GreenBonus.fxml", true, true);
    }

    public void showYellowRealmStage() {
        showRealmStage("YellowBonus.fxml", true, true);
    }

    public void showMagentaRealmStage() {
        showRealmStage("MagentaBonus.fxml", true, true);
    }

    public void showBlueRealmStage() {
        showRealmStage("BlueBonus.fxml", true, true);
    }

    public void showRealmPickerStage() {
        showRealmStage("RealmPicker.fxml", true, true);
    }

    public void showEndGame() {
        showRealmStage("EndGame.fxml", true, false);
    }


    public void switchWizardsScene(){
        switchScene("Wizards.fxml",false);
    }


}
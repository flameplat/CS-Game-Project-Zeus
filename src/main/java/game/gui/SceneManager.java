package game.gui;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class SceneManager {
    private final Stage stage;
    private Scene scene;
    private Parent root;
    private static GUIGameController guiGameController;
    public SceneManager(Stage stage) {
        this.stage=stage;
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
            stage.setScene(scene);
            stage.setResizable(isResizable);
            if (isResizable) {
                Screen screen = Screen.getPrimary();
                CalculatePositionToCenterStage(screen);
            }
            stage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void CalculatePositionToCenterStage(Screen screen) {
        double centerX = screen.getBounds().getWidth() / 2 - stage.getWidth() / 2;
        //+15 to make window tab appear
        double centerY = screen.getBounds().getHeight() / 2 - stage.getHeight() / 2+15;
        stage.setX(centerX);
        stage.setY(centerY);
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

    private Stage realmStage;


    public void showRealmStage(String resourceFileName) {


        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            Parent root = loader.load();

            // Initialize the controller
            RealmController realmController = loader.getController();
            realmController.setSceneManager(this);
            realmController.setGuiGameController(guiGameController);

            // Create the scene and stage
            Scene realmScene = new Scene(root);
            realmStage = new Stage();
            realmStage.setScene(realmScene);
            realmStage.initModality(Modality.APPLICATION_MODAL);
            realmStage.initOwner(stage);
            realmStage.setResizable(false);

            // Handle stage close request
            realmStage.setOnCloseRequest(Event::consume);

            realmStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void closeRealmStage() {
        if (realmStage != null) {
            realmStage.close();
        }
    }

    public void showRedRealmStage(){
        showRealmStage("RedRealm.fxml");
    }

    public void showGreenRealmStage(){
        showRealmStage("GreenBonus.fxml");
    }

    public void showYellowRealmStage(){
        showRealmStage("YellowBonus.fxml");
    }

    public void showMagentaRealmStage(){
        showRealmStage("MagentaBonus.fxml");
    }

    public void showBlueRealmStage(){
        showRealmStage("BlueBonus.fxml");
    }
    public void showRealmPickerStage(){
        showRealmStage("RealmPicker.fxml");
    }
}
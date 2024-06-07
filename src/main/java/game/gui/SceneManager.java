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
import java.util.Map;
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
            if (isResizable || resourceFileName=="Wizards.fxml") {
                Platform.runLater(() -> {
                    Screen screen = Screen.getPrimary();
                    CalculatePositionToCenterStage(screen);
                });

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


    public void showRealmStage(String resourceFileName,boolean enableWindowTab,boolean disableExitButton) {

        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(resourceFileName)));
            Parent root = loader.load();
            // Initialize the controller
            RealmController realmController = loader.getController();
            realmController.setSceneManager(this);
            realmController.setGuiGameController(guiGameController);
            if(realmController instanceof EndGame){
                ((EndGame)realmController).setPlayers(guiGameController.getActivePlayer(),guiGameController.getPassivePlayer());
            }
            root.setStyle("-fx-background-color: transparent;");
            // Create the scene and stage
            Scene realmScene = new Scene(root);
            scene.setFill(null);
            Stage newStage = new Stage();
            newStage.setScene(realmScene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initOwner(stage);
            newStage.setResizable(false);
            if(!enableWindowTab){
                newStage.initStyle(StageStyle.UNDECORATED);
            }
            newStage.setOnShown(event -> {
                double shiftForPlayer1 = 600;
                double shiftForPlayer2 = 0;
                if(realmController instanceof Guider){
                    Screen screen = Screen.getPrimary();
                    CalculatePositionToCenterStage(screen);
                }
                else{
                    if (GUIGameController.isPlayer1Playing()) {
                        newStage.setX(shiftForPlayer1);
                    } else {
                        newStage.setX(shiftForPlayer2);
                    }
                    // Center vertically
                    Screen screen = Screen.getPrimary();
                    Rectangle2D bounds = screen.getVisualBounds();
                    double centerY = (bounds.getHeight() - newStage.getHeight()) / 2;
                    newStage.setY(centerY);
                }
            });
            if(disableExitButton){
                newStage.setOnCloseRequest(Event::consume);
            }
            newStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showRedRealmStage(){
        showRealmStage("RedRealm.fxml",true,true);
    }

    public void showGreenRealmStage(){
        showRealmStage("GreenBonus.fxml",true,true);
    }

    public void showYellowRealmStage(){
        showRealmStage("YellowBonus.fxml",true,true);
    }

    public void showMagentaRealmStage(){
        showRealmStage("MagentaBonus.fxml",true,true);
    }

    public void showBlueRealmStage(){
        showRealmStage("BlueBonus.fxml",true,true);
    }

    public void showRealmPickerStage(){
        showRealmStage("RealmPicker.fxml",true,true);
    }

    public void switchWizardsScene(){
        switchScene("Wizards.fxml",false);
    }

    public void showEndGame(){
        showRealmStage("EndGame.fxml",true,false);
    }


}
package game.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneManager {
    private final Stage stage;
    private Scene scene;
    private Parent root;
    private Map<String,Scene> scenes;
    private static GUIGameController guiGameController;

    public SceneManager(Stage stage) {
        this.stage=stage;
        scenes=new HashMap<>();
    }
    public static void setGuiGameController(GUIGameController guiGameController) {
        SceneManager.guiGameController=guiGameController;
    }
    public void switchMainMenuScene(){
        try{
            FXMLLoader loader= new FXMLLoader(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
            root= loader.load();
            MainMenuController mainMenuController=loader.getController();
            mainMenuController.setSceneManager(this);
            scene=new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void switchPlayerDataScene(){
        try{
            FXMLLoader loader= new FXMLLoader(Objects.requireNonNull(getClass().getResource("PlayerData.fxml")));
            root= loader.load();
            PlayerDataController playerDataController=loader.getController();
            playerDataController.setSceneManager(this);
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public void switchGamePlayScene(){
        if(scenes.containsKey("GamePlayScene")){
            stage.setScene(scenes.get("GamePlayScene"));
            stage.setResizable(true);
            stage.show();
        }
        else{
            try{
                FXMLLoader loader= new FXMLLoader(Objects.requireNonNull(getClass().getResource("GamePlay.fxml")));
                root= loader.load();
                GamePlayController gamePlayController=loader.getController();
                GamePlayController.setSceneManager(this);
                scene=new Scene(root);
                scenes.put("GamePlayScene",scene);
                stage.setScene(scene);
                stage.setResizable(true);
                // Calculate the position to center the stage on the screen
                Screen screen = Screen.getPrimary();
                double centerX = screen.getBounds().getWidth() / 2 - (double) 1920 / 2;
                double centerY = screen.getBounds().getHeight() / 2 - (double) 1080 / 2;
                stage.setX(centerX);
                stage.setY(centerY);
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private Stage redRealmStage;
    private Stage realmPickerStage;
    private Stage greenRealmStage;
    private Stage yellowRealmStage;
    private Stage magentaRealmStage;
    private Stage blueRealmStage;
    public void showRealmPickerStage(){
        if(scenes.containsKey("RealmPickerScene")){
            realmPickerStage = new Stage();
            realmPickerStage.setScene(scenes.get("RealmPickerScene"));
            realmPickerStage.initModality(Modality.APPLICATION_MODAL);
            realmPickerStage.initOwner(stage);
            RealmPickerController.setGuiGameController(guiGameController);
            realmPickerStage.setResizable(false);
            realmPickerStage.setOnCloseRequest(Event::consume);
            realmPickerStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("RealmPicker.fxml")));
                root = loader.load();
                RealmPickerController.setSceneManager(this);
                Scene realmPickerScene = new Scene(root);
                realmPickerStage = new Stage();
                realmPickerStage.setScene(realmPickerScene);
                realmPickerStage.initModality(Modality.APPLICATION_MODAL);
                realmPickerStage.initOwner(stage);
                RealmPickerController.setGuiGameController(guiGameController);
                realmPickerStage.setResizable(false);
                realmPickerStage.setOnCloseRequest(Event::consume);
                realmPickerStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void closeRealmPickerStage(){
        realmPickerStage.close();
    }
    public void showRedRealmStage(){
        if(scenes.containsKey("RedRealmScene")){
            redRealmStage = new Stage();
            redRealmStage.setScene(scenes.get("RedRealmScene"));
            redRealmStage.initModality(Modality.APPLICATION_MODAL);
            redRealmStage.initOwner(stage);
            RedRealmController.setGuiGameController(guiGameController);
            redRealmStage.setResizable(false);
            redRealmStage.setOnCloseRequest(Event::consume);
            redRealmStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("RedRealm.fxml")));
                root = loader.load();
                RedRealmController.setSceneManager(this);
                Scene redRealmScene = new Scene(root);
                redRealmStage = new Stage();
                redRealmStage.setScene(redRealmScene);
                redRealmStage.initModality(Modality.APPLICATION_MODAL);
                redRealmStage.initOwner(stage);
                RedRealmController.setGuiGameController(guiGameController);
                redRealmStage.setResizable(false);
                redRealmStage.setOnCloseRequest(Event::consume);
                redRealmStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void closeRedRealmStage(){
        redRealmStage.close();
    }

    public void showGreenRealmStage(){
        if(scenes.containsKey("GreenRealmScene")){
            greenRealmStage = new Stage();
            greenRealmStage.setScene(scenes.get("GreenRealmScene"));
            greenRealmStage.initModality(Modality.APPLICATION_MODAL);
            greenRealmStage.initOwner(stage);
            GreenBonusController.setGuiGameController(guiGameController);
            greenRealmStage.setResizable(false);
            greenRealmStage.setOnCloseRequest(Event::consume);
            greenRealmStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("GreenBonus.fxml")));
                root = loader.load();
                GreenBonusController.setSceneManager(this);
                Scene greenRealmScene = new Scene(root);
                greenRealmStage = new Stage();
                greenRealmStage.setScene(greenRealmScene);
                greenRealmStage.initModality(Modality.APPLICATION_MODAL);
                greenRealmStage.initOwner(stage);
                GreenBonusController.setGuiGameController(guiGameController);
                greenRealmStage.setResizable(false);
                greenRealmStage.setOnCloseRequest(Event::consume);
                greenRealmStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void closeGreenRealmStage(){
        greenRealmStage.close();
    }



    public void showYellowRealmStage(){
        if(scenes.containsKey("YellowRealmScene")){
            yellowRealmStage = new Stage();
            yellowRealmStage.setScene(scenes.get("YellowRealmScene"));
            yellowRealmStage.initModality(Modality.APPLICATION_MODAL);
            yellowRealmStage.initOwner(stage);
            YellowBonusController.setGuiGameController(guiGameController);
            yellowRealmStage.setResizable(false);
            yellowRealmStage.setOnCloseRequest(Event::consume);
            yellowRealmStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("YellowBonus.fxml")));
                root = loader.load();
                YellowBonusController.setSceneManager(this);
                Scene yellowRealmScene = new Scene(root);
                yellowRealmStage = new Stage();
                yellowRealmStage.setScene(yellowRealmScene);
                yellowRealmStage.initModality(Modality.APPLICATION_MODAL);
                yellowRealmStage.initOwner(stage);
                YellowBonusController.setGuiGameController(guiGameController);
                yellowRealmStage.setResizable(false);
                yellowRealmStage.setOnCloseRequest(Event::consume);
                yellowRealmStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void closeYellowRealmStage(){
        yellowRealmStage.close();
    }

    public void showMagentaRealmStage(){
        if(scenes.containsKey("MagentaRealmScene")){
            magentaRealmStage = new Stage();
            magentaRealmStage.setScene(scenes.get("MagentaRealmScene"));
            magentaRealmStage.initModality(Modality.APPLICATION_MODAL);
            magentaRealmStage.initOwner(stage);
            MagentaBonusController.setGuiGameController(guiGameController);
            magentaRealmStage.setResizable(false);
            magentaRealmStage.setOnCloseRequest(Event::consume);
            magentaRealmStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("MagentaBonus.fxml")));
                root = loader.load();
                MagentaBonusController.setSceneManager(this);
                Scene magentaRealmScene = new Scene(root);
                magentaRealmStage = new Stage();
                magentaRealmStage.setScene(magentaRealmScene);
                magentaRealmStage.initModality(Modality.APPLICATION_MODAL);
                magentaRealmStage.initOwner(stage);
                MagentaBonusController.setGuiGameController(guiGameController);
                magentaRealmStage.setResizable(false);
                magentaRealmStage.setOnCloseRequest(Event::consume);
                magentaRealmStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void closeMagentaRealmStage(){
        magentaRealmStage.close();
    }

    public void showBlueRealmStage(){
        if(scenes.containsKey("BlueRealmScene")){
            blueRealmStage = new Stage();
            blueRealmStage.setScene(scenes.get("BlueRealmScene"));
            blueRealmStage.initModality(Modality.APPLICATION_MODAL);
            blueRealmStage.initOwner(stage);
            BlueBonusController.setGuiGameController(guiGameController);
            blueRealmStage.setResizable(false);
            blueRealmStage.setOnCloseRequest(Event::consume);
            blueRealmStage.showAndWait();
            stage.toFront();
        } else {
            try{
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("BlueBonus.fxml")));
                root = loader.load();
                BlueBonusController.setSceneManager(this);
                Scene blueRealmScene = new Scene(root);
                blueRealmStage = new Stage();
                blueRealmStage.setScene(blueRealmScene);
                blueRealmStage.initModality(Modality.APPLICATION_MODAL);
                blueRealmStage.initOwner(stage);
                BlueBonusController.setGuiGameController(guiGameController);
                blueRealmStage.setResizable(false);
                blueRealmStage.setOnCloseRequest(Event::consume);
                blueRealmStage.showAndWait();
                stage.toFront();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void closeBlueRealmStage(){
        blueRealmStage.close();
    }

}



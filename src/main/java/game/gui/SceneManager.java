package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private GUIGameController gameController;
    public SceneManager(Stage stage,GUIGameController guiGameController) {
        this.stage=stage;
        this.gameController=guiGameController;
    }

    public void switchMainMenuScene(){
        try{
            FXMLLoader loader= new FXMLLoader(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
            root= loader.load();
            MainMenuController mainMenuController=loader.getController();
            mainMenuController.setGameController(gameController);
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
            playerDataController.setGameController(gameController);
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
        try{
            root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game/gui/GamePlay.fxml")));
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}

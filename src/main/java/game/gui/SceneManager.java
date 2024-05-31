package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneManager {
    private final Stage stage;
    private Scene scene;
    private Parent root;
    Map<String,Scene> scenes;
    public SceneManager(Stage stage) {
        this.stage=stage;
        scenes=new HashMap<>();
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
                stage.show();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}

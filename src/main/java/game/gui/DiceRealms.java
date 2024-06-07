package game.gui;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class DiceRealms extends Application {
    private static SceneManager sceneManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon=new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dice Realms");
        sceneManager=new SceneManager(primaryStage);
        sceneManager.switchMainMenuScene();
    }
    public static SceneManager getSceneManager(){
        return sceneManager;
    }
}

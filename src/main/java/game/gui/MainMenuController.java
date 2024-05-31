package game.gui;

import game.engine.GameMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    private Label hint;
    private SceneManager sceneManager;
    @FXML
    private ImageView bg;
    @FXML
    private ImageView button1;
    @FXML
    private ImageView button2;

    private static GameMode gameMode;

    public MainMenuController(){

    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    public void setGameModeSinglePlayer(){
        this.hint.setText("This option is WIP");
    }
    public void setGameModeMultiplayer(){
        gameMode=GameMode.MULTIPLAYER;
        sceneManager.switchPlayerDataScene();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/mainMenu.jpeg")).toExternalForm());
        Image multiPlayerButton=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm());
        Image singlePlayerButton=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm());
        bg.setImage(mainBG);
        button1.setImage(multiPlayerButton);
        button2.setImage(singlePlayerButton);
    }
    public static GameMode getGameMode(){
        return gameMode;
    }
}

package game.gui;

import game.engine.GameMode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable ,GameController{
    @FXML
    private SceneManager sceneManager;
    @FXML
    private ImageView bg;
    @FXML
    private ImageView button1;
    @FXML
    private ImageView button2;

    @FXML
    private ImageView logo;

    @FXML
    private Label multiplayerLabel;

    @FXML
    private Label singlePlayerLabel;


    private static GameMode gameMode;

    public MainMenuController(){

    }

    /**
     * Sets the SceneManager instance for the MainMenuController.
     *
     * @param sceneManager the SceneManager instance to set
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    /**
     * Sets the game mode to single player and switches the scene to the player data scene.
     */
    public void setGameModeSinglePlayer(){
        gameMode=GameMode.SINGLEPLAYER;
        sceneManager.switchPlayerDataScene();
    }
    /**
     * Sets the game mode to multiplayer.
     * Switches to the player data scene.
     */
    public void setGameModeMultiplayer(){
        gameMode=GameMode.MULTIPLAYER;
        sceneManager.switchPlayerDataScene();
    }
    /**
     * Adds a hover effect to the given ImageView by adding event handlers for MOUSE_ENTERED and MOUSE_EXITED events.
     *
     * @param imageView the ImageView to add the hover effect to
     */
    private void addHoverEffect(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(10);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }


    /**
     * Initializes the MainMenuController.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/mainMenu.jpeg")).toExternalForm());
        Image multiPlayerButton=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm());
        Image singlePlayerButton=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm());
        Image logoImage=new Image(Objects.requireNonNull(getClass().getResource("/images/Logo.png")).toExternalForm());
        logo.setImage(logoImage);
        bg.setImage(mainBG);
        button1.setImage(multiPlayerButton);
        button2.setImage(singlePlayerButton);
        addHoverEffect(button1);
        addHoverEffect(button2);
        multiplayerLabel.setMouseTransparent(true);
        singlePlayerLabel.setMouseTransparent(true);
    }
    /**
     * Retrieves the GameMode.
     *
     * @return The GameMode object representing the current game mode.
     */
    public static GameMode getGameMode(){
        return gameMode;
    }
}

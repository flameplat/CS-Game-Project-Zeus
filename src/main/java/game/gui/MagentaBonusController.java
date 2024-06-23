package game.gui;
import game.engine.Move;
import game.engine.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class MagentaBonusController implements Initializable,RealmController {
    @FXML private Button PhoenixButton;
    @FXML private ImageView PhoenixImageView;
    @FXML private ImageView BG;
    @FXML
    private Label label;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/MagentaRealmBG.jpg")).toExternalForm());
        Image creature =new Image(Objects.requireNonNull(getClass().getResource("/images/MagentaPhoneix.png")).toExternalForm());
    BG.setImage(mainBG);
    PhoenixImageView.setImage(creature);
    }
    /**
     * Closes the current stage and performs an attack on the Phoenix.
     * Makes a move for the current player and resets the labels.
     */
    public void AttackPhoenix() {

        Stage stage = (Stage) PhoenixImageView.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            guiGameController.makeMove(currentPlayer,possibleMove);
            resetLabels();
        });
    }
    /**
     * Represents a scene manager responsible for managing scenes in the application.
     */
    private SceneManager sceneManager;
    /**
     * This variable represents the GUI game controller for the MagentaBonusController class.
     *
     * The GUIGameController is responsible for handling the game logic and user interactions for the GUI.
     *
     * It provides methods such as makeMove() to make a move in the game, setLabel() to set the label text for the current player,
     * resetLabels() to reset the label text, and other setter methods to set the necessary dependencies for the controller.
     *
     * Example usage:
     *
     *     private GUIGameController guiGameController;
     *
     *     public void setGuiGameController(GUIGameController guiGameController){
     *         this.guiGameController=guiGameController;
     *     }
     */
    private GUIGameController guiGameController;
    /**
     * Sets the GUI game controller for the MagentaBonusController class.
     *
     * @param guiGameController the GUI game controller to be set
     */
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    /**
     * Sets the SceneManager for the MagentaBonusController.
     *
     * @param sceneManager the SceneManager to be set
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     * Represents the currently active player in the game.
     * The player can be set using the `setCurrentPlayer` method and retrieved using the getter.
     */
    private static Player currentPlayer;
    /**
     * Sets the current player for the MagentaBonusController class.
     *
     * @param currentPlayer the player object representing the current player.
     */
    public static void setCurrentPlayer(Player currentPlayer){
        MagentaBonusController.currentPlayer=currentPlayer;
    }
    /**
     * The possible move for the player.
     */
    private static Move possibleMove;
    /**
     * Sets the possible move for the MagentaBonusController.
     *
     * @param move the move object to set as the possible move
     */
    public static void setPossibleMove(Move move){
        MagentaBonusController.possibleMove=move;
    }
    /**
     * Sets the label text to display a message about encountering the Phoenix.
     * The text will be in the format "playerName, you have encountered the Phoenix! Click on it to attack it".
     */
    public void setLabel(){
        label.setText(currentPlayer.getName() + ", you have encountered the Phoenix! Click on it to attack it");
    }
    /**
     * Resets the label by setting its text to an empty string.
     */
    @Override
    public void resetLabels(){
        label.setText("");
    }

}

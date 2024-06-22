package game.gui;
import game.engine.Move;
import game.engine.Player;
import javafx.application.Platform;
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
public class BlueBonusController implements Initializable,RealmController {
    @FXML
    private Button serpantButton;
    @FXML
    private ImageView serpantImageView;
    @FXML
    private ImageView BG;
    @FXML
    private Label label;

    /**
     * Initializes the BlueBonusController.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueBG.jpg")).toExternalForm());
        Image creature = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueSerpant.png")).toExternalForm());
        BG.setImage(mainBG);
        serpantImageView.setImage(creature);
    }
    /**
     * Closes the current stage and performs an attack on the serpent.
     * This method is responsible for closing the current stage and attacking the serpent in the game.
     * It is intended to be called when the user clicks on the serpent button.
     * The attack is performed asynchronously using the Platform.runLater() method to ensure it is executed on the JavaFX application thread.
     * After the attack is performed, the labels are reset using the resetLabels() method.
     * It does not return any value.
     */
    public void attackSerpent() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            guiGameController.makeMove(currentPlayer,possibleMove);
            resetLabels();
        });
    }
    /**
     * The SceneManager class is responsible for managing the scenes and stages in the application.
     */
    private SceneManager sceneManager;
    /**
     * Resets the label in the current instance of {@link BlueBonusController}.
     */
    @Override
    public void resetLabels(){
        label.setText("");
    }
    /**
     * Sets the SceneManager for the BlueBonusController.
     *
     * @param sceneManager The SceneManager to be set.
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     * The GUI Game Controller for the BlueBonusController class.
     */
    private GUIGameController guiGameController;
    /**
     * Sets the GUI game controller for the BlueBonusController.
     *
     * @param guiGameController the GUI game controller to set
     */
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    /**
     *
     */
    private static Move possibleMove;
    /**
     * Sets the possible move for the BlueBonusController.
     *
     * @param move the move to set as the possible move
     */
    public static void setPossibleMove(Move move){
        BlueBonusController.possibleMove = move;
    }
    /**
     * The current player who is making a move.
     */
    private static Player currentPlayer;
    /**
     * Sets the current player for the BlueBonusController class.
     *
     * @param currentPlayer the Player object representing the current player
     */
    public static void setCurrentPlayer(Player currentPlayer){
        BlueBonusController.currentPlayer=currentPlayer;
    }
    /**
     * Sets the label text to display the current player's name and instruction to attack the serpent.
     */
    public void setLabel(){
        label.setText(currentPlayer.getName() + ", click on Serpent to attack it!:");
    }
}
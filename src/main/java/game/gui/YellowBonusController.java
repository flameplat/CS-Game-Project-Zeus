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
public class YellowBonusController implements Initializable ,RealmController {
    @FXML private Button LionButton;
    @FXML private ImageView LionImageView;
    @FXML private ImageView BG;
    @FXML
    private Label label;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/YellowRealmBG.jpg")).toExternalForm());
        Image creature =new Image(Objects.requireNonNull(getClass().getResource("/images/YellowLion.png")).toExternalForm());
        BG.setImage(mainBG);
        LionImageView.setImage(creature);
        resetLabels();
    }
    /**
     * This method is used to attack the lion by closing the current stage, and making a move using the GUI game controller
     */
    public void AttackLion() {
        Stage stage = (Stage) LionImageView.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            guiGameController.makeMove(currentPlayer,possibleMove);
        });

    }

    /**
     * Represents the SceneManager to manage scenes in the application.
     */
    private SceneManager sceneManager;
    /**
     * This method resets the label text to an empty string.
     */
    @Override
    public void resetLabels(){
        label.setText("");
    }

    /**
     * Sets the SceneManager for the YellowBonusController.
     *
     * @param sceneManager the SceneManager to be set
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     * GUI game controller for the YellowBonusController class.
     */
    private GUIGameController guiGameController;
    /**
     * Sets the GUI game controller for the YellowBonusController.
     *
     * @param guiGameController The GUI game controller to be set.
     */
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    /**
     * Stores the current player in the YellowBonusController class.
     */
    private static Player currentPlayer;
    /**
     * Sets the current player.
     *
     * @param currentPlayer the new current player
     */
    public static void setCurrentPlayer(Player currentPlayer){
        YellowBonusController.currentPlayer=currentPlayer;
    }
    /**
     * Represents a possible move in the game.
     */
    private static Move possibleMove;
    /**
     * Sets the possible move for the YellowBonusController class.
     *
     * @param move the Move object representing the possible move
     */
    public static void setPossibleMove(Move move){
        YellowBonusController.possibleMove=move;
    }
    /**
     * Sets the text of the label with a message indicating the current player's name and instructions to click on the lion to attack it.
     *
     * This method is typically called when the lion button is clicked in the Yellow Bonus Scene in the game. It retrieves the current player's name and constructs a message instruct
     * ing the player to click on the lion to attack it. The constructed message is then set as the text of the label.
     *
     * @throws NullPointerException if the currentPlayer or label is null
     */
    @FXML
    public void setLabel(){
        label.setText(currentPlayer.getName() + ", click on lion to attack it!");
    }
}
package game.gui;
import game.engine.Move;
import game.engine.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;
public class BlueBonusController implements Initializable {
    @FXML
    private Button serpantButton;
    @FXML
    private ImageView serpantImageView;
    @FXML
    private ImageView BG;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueBG.jpg")).toExternalForm());
        Image creature = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueSerpant.png")).toExternalForm());
        BG.setImage(mainBG);
        serpantImageView.setImage(creature);
    }
    public void AttcakSerpant () {
        sceneManager.closeBlueRealmStage();
        guiGameController.makeMove(currentPlayer,possibleMove);
    }
    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        BlueBonusController.sceneManager=sceneManager;
    }
    private static GUIGameController guiGameController;
    public static void setGuiGameController(GUIGameController guiGameController){
        BlueBonusController.guiGameController=guiGameController;
    }
    private static Move possibleMove;
    public static void setPossibleMove(Move move){
        BlueBonusController.possibleMove = move;
    }
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        BlueBonusController.currentPlayer=currentPlayer;
    }
}
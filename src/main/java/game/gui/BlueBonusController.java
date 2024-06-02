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
public class BlueBonusController implements Initializable,RealmController {
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
        sceneManager.closeRealmStage();
        guiGameController.makeMove(currentPlayer,possibleMove);
    }
    private SceneManager sceneManager;
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    private GUIGameController guiGameController;
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
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
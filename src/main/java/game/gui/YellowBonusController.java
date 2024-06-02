package game.gui;
import game.creatures.Lion;
import game.dice.YellowDice;
import game.engine.Move;
import game.engine.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class YellowBonusController implements Initializable ,RealmController {
    @FXML private Button LionButton;
    @FXML private ImageView LionImageView;
    @FXML private ImageView BG;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/YellowRealmBG.jpg")).toExternalForm());
        Image creature =new Image(Objects.requireNonNull(getClass().getResource("/images/YellowLion.png")).toExternalForm());
        BG.setImage(mainBG);
        LionImageView.setImage(creature);
    }
    public void AttackLion() {
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
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        YellowBonusController.currentPlayer=currentPlayer;
    }
    private static Move possibleMove;
    public static void setPossibleMove(Move move){
        YellowBonusController.possibleMove=move;
    }
}
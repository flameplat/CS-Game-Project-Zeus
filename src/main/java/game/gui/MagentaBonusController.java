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
import java.util.Objects;
import java.util.ResourceBundle;
public class MagentaBonusController implements Initializable,RealmController {
    @FXML private Button PhoenixButton;
    @FXML private ImageView PhoenixImageView;
    @FXML private ImageView BG;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/MagentaRealmBG.jpg")).toExternalForm());
        Image creature =new Image(Objects.requireNonNull(getClass().getResource("/images/MagentaPhoneix.png")).toExternalForm());
    BG.setImage(mainBG);
    PhoenixImageView.setImage(creature);
    }
    public void AttackPhoenix() {
        sceneManager.closeRealmStage();
        guiGameController.makeMove(currentPlayer,possibleMove);
    }
    private SceneManager sceneManager;
    private GUIGameController guiGameController;
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        MagentaBonusController.currentPlayer=currentPlayer;
    }
    private static Move possibleMove;
    public static void setPossibleMove(Move move){
        MagentaBonusController.possibleMove=move;
    }

}

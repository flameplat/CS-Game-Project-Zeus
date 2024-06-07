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
    public void AttackPhoenix() {

        Stage stage = (Stage) PhoenixImageView.getScene().getWindow();
        stage.close();
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
    public void setLabel(){
        label.setText(currentPlayer.getName() + ", you have encountered the Phoenix! Click on it to attack it");
    }

}

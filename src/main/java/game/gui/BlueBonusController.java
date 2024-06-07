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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueBG.jpg")).toExternalForm());
        Image creature = new Image(Objects.requireNonNull(getClass().getResource("/images/BlueSerpant.png")).toExternalForm());
        BG.setImage(mainBG);
        serpantImageView.setImage(creature);
    }
    public void attackSerpent() {
        Stage stage = (Stage) label.getScene().getWindow();
        stage.close();
        Platform.runLater(() -> {
            guiGameController.makeMove(currentPlayer,possibleMove);
            resetLabels();
        });
    }
    private SceneManager sceneManager;
    @Override
    public void resetLabels(){
        label.setText("");
    }
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
    public void setLabel(){
        label.setText(currentPlayer.getName() + ", click on Serpent to attack it!:");
    }
}
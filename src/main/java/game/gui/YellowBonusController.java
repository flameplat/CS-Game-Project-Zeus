package game.gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class YellowBonusController implements Initializable  {
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
    public void AttackLion(ActionEvent event) {

    }
    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        YellowBonusController.sceneManager=sceneManager;
    }
    private static GUIGameController guiGameController;
    public static void setGuiGameController(GUIGameController guiGameController){
        YellowBonusController.guiGameController=guiGameController;
    }
}
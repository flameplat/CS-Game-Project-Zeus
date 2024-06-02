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
public class MagentaBonusController implements Initializable  {
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
    public void AttackPhoenix(ActionEvent event) {

    }
    private static SceneManager sceneManager;
    private static GUIGameController guiGameController;
    public static void setGuiGameController(GUIGameController guiGameController){
        MagentaBonusController.guiGameController=guiGameController;
    }
    public static void setSceneManager(SceneManager sceneManager){
        MagentaBonusController.sceneManager=sceneManager;
    }
}

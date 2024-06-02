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
    public void AttcakSerpant (ActionEvent event) {

    }
}
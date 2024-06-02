package game.gui;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
public class GreenBonusController implements  Initializable  {
    @FXML private Button Gaia1;
    @FXML private Button Gaia2;
    @FXML private Button Gaia3;
    @FXML private Button Gaia4;
    @FXML private Button Gaia5;
    @FXML private Button Gaia6;
    @FXML private Button Gaia7;
    @FXML private Button Gaia8;
    @FXML private Button Gaia9;
    @FXML private Button Gaia10;
    @FXML private Button Gaia11;
    @FXML private ImageView BG;
    @FXML private ImageView ImageButton1;
    @FXML private ImageView ImageButton2;
    @FXML private ImageView ImageButton3;
    @FXML private ImageView ImageButton4;
    @FXML private ImageView ImageButton5;
    @FXML private ImageView ImageButton6;
    @FXML private ImageView ImageButton7;
    @FXML private ImageView ImageButton8;
    @FXML private ImageView ImageButton9;
    @FXML private ImageView ImageButton10;
    @FXML private ImageView ImageButton11;
    @FXML private ImageView GaiaCreature;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/GreenRealmBG.jpeg")).toExternalForm());
        Image buttons =new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/greenButtons.jpeg")).toExternalForm());
        Image creature=new Image(Objects.requireNonNull(getClass().getResource("/images/GaiaGurdian.png")).toExternalForm());
        BG.setImage(mainBG);
        GaiaCreature.setImage(creature);
        ImageButton1.setImage(buttons);
        ImageButton2.setImage(buttons);
        ImageButton3.setImage(buttons);
        ImageButton4.setImage(buttons);
        ImageButton5.setImage(buttons);
        ImageButton6.setImage(buttons);
        ImageButton7.setImage(buttons);
        ImageButton8.setImage(buttons);
        ImageButton9.setImage(buttons);
        ImageButton10.setImage(buttons);
        ImageButton11.setImage(buttons);
    }
    public void AttackGaia1(ActionEvent event) {

    }
    public void AttackGaia2(ActionEvent event) {

    }
    public void AttackGaia3(ActionEvent event) {

    }
    public void AttackGaia4(ActionEvent event) {

    }
    public void AttackGaia5(ActionEvent event) {

    }
    public void AttackGaia6(ActionEvent event) {

    }
    public void AttackGaia7(ActionEvent event) {

    }
    public void AttackGaia8(ActionEvent event) {

    }
    public void AttackGaia9(ActionEvent event) {

    }
    public void AttackGaia10(ActionEvent event) {

    }
    public void AttackGaia11(ActionEvent event) {

    }
}

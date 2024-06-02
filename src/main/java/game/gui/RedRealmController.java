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

public class RedRealmController implements Initializable {
    @FXML private Button FaceD1;
    @FXML private Button WingD1;
    @FXML private Button TailD1;
    @FXML private Button FaceD2;
    @FXML private Button WingD2;
    @FXML private Button HeartD2;
    @FXML private Button FaceD3;
    @FXML private Button TailD3;
    @FXML private Button HeartD3;
    @FXML private Button WingD4;
    @FXML private Button TailD4;
    @FXML private Button HeartD4;
    @FXML private ImageView BG;
    @FXML private ImageView dragon1;
    @FXML private ImageView dragon2;
    @FXML private ImageView dragon3;
    @FXML private ImageView dragon4;
    @FXML private Label text;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/redRealmBackground.jpg")).toExternalForm());
        Image dragons =new Image(Objects.requireNonNull(getClass().getResource("/images/RedDragon.png")).toExternalForm());
        BG.setImage(mainBG);
        dragon1.setImage(dragons);
        dragon2.setImage(dragons);
        dragon3.setImage(dragons);
        dragon4.setImage(dragons);
    }
    public void attckFaceD1(ActionEvent event) {

    }
    public void attckWingD1(ActionEvent event) {

    }
    public void attckTailD1(ActionEvent event) {

    }
    public void attckFaceD2(ActionEvent event) {

    }
    public void attckWingD2(ActionEvent event) {

    }
    public void attckHeartD2(ActionEvent event) {

    }
    public void attckFaceD3(ActionEvent event) {

    }
    public void attckTailD3(ActionEvent event) {

    }
    public void attckHeartD3(ActionEvent event) {

    }
    public void attckWingD4(ActionEvent event) {

    }
    public void attckTailD4(ActionEvent event) {

    }
    public void attckHeartD4(ActionEvent event) {

    }
    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        RedRealmController.sceneManager=sceneManager;
    }

}

package game.gui;

import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class RealmPickerController implements Initializable {

    @FXML
    private ImageView backGroundImageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/RealmsCreatures.png")).toExternalForm());
        backGroundImageView.setImage(mainBG);
    }

    @FXML
    public void chooseRedRealm(){
    }
    @FXML
    public void chooseGreenRealm(){

    }
    @FXML
    public void chooseBlueRealm(){

    }
    @FXML
    public void chooseMagentaRealm(){

    }
    @FXML
    public void chooseYellowRealm(){

    }
    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        RealmPickerController.sceneManager=sceneManager;
    }


}

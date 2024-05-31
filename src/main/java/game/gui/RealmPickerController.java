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
    private Consumer<GameColor> userInputHandler;
    @FXML
    private ImageView backGroundImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/RetroDice.png")).toExternalForm());
        backGroundImageView.setImage(mainBG);
    }
    public void setOnUserInput(Consumer<GameColor> handler){
        this.userInputHandler=handler;
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


}

package game.gui;

import game.engine.Player;
import game.utilities.GameColor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;


public class RealmPickerController implements Initializable,RealmController {

    @FXML
    private ImageView backGroundImageView;
    @FXML private Label label;

    @FXML private Rectangle redRealmRectangle;
    @FXML private Rectangle greenRealmRectangle;
    @FXML private Rectangle blueRealmRectangle;
    @FXML private Rectangle magentaRealmRectangle;
    @FXML private Rectangle yellowRealmRectangle;

    @FXML private ImageView mainImageView;
    @FXML private ImageView redImageView;
    @FXML private ImageView greenImageView;
    @FXML private ImageView blueImageView;
    @FXML private ImageView magentaImageView;
    @FXML private ImageView yellowImageView;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/RealmsCreatures.png")).toExternalForm());
        Image button=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm());
        backGroundImageView.setImage(mainBG);
        addHoverEffect(redRealmRectangle);
        addHoverEffect(greenRealmRectangle);
        addHoverEffect(blueRealmRectangle);
        addHoverEffect(magentaRealmRectangle);
        addHoverEffect(yellowRealmRectangle);
        redImageView.setImage(button);
        greenImageView.setImage(button);
        blueImageView.setImage(button);
        magentaImageView.setImage(button);
        yellowImageView.setImage(button);
        mainImageView.setImage(button);
    }
    private void addHoverEffect(Rectangle rectangle) {
        rectangle.setOnMouseEntered(event -> rectangle.setOpacity(0.3));

        rectangle.setOnMouseExited(event -> rectangle.setOpacity(0));
    }

    @FXML
    public void chooseRedRealm() {
        chooseRealm(GameColor.RED);
    }

    @FXML
    public void chooseGreenRealm() {
        chooseRealm(GameColor.GREEN);
    }

    @FXML
    public void chooseBlueRealm() {
        chooseRealm(GameColor.BLUE);
    }

    @FXML
    public void chooseMagentaRealm() {
        chooseRealm(GameColor.MAGENTA);
    }

    @FXML
    public void chooseYellowRealm() {
        chooseRealm(GameColor.YELLOW);
    }

    private void chooseRealm(GameColor realmColor) {
        if(possibleRealms.contains(realmColor)){
            Stage stage = (Stage) label.getScene().getWindow();
            stage.close();
            guiGameController.playColorBonus(currentPlayer, realmColor);
        }
        else {
            label.setText("Not Available");
        }
    }

    private  SceneManager sceneManager;
    public  void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        RealmPickerController.currentPlayer=currentPlayer;
    }
    private  GUIGameController guiGameController;
    public  void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    private static LinkedList<GameColor> possibleRealms;
    public static void setPossibleRealms(LinkedList<GameColor> possibleRealms){
        RealmPickerController.possibleRealms=possibleRealms;
    }


}

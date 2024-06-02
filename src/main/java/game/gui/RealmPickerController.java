package game.gui;

import game.engine.Player;
import game.realms.Realm;
import game.utilities.GameColor;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;


public class RealmPickerController implements Initializable {

    @FXML
    private ImageView backGroundImageView;
    @FXML private Label label;

    @FXML private Rectangle redRealmRectangle;
    @FXML private Rectangle greenRealmRectangle;
    @FXML private Rectangle blueRealmRectangle;
    @FXML private Rectangle magentaRealmRectangle;
    @FXML private Rectangle yellowRealmRectangle;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/RealmsCreatures.png")).toExternalForm());
        backGroundImageView.setImage(mainBG);
        addHoverEffect(redRealmRectangle);
        addHoverEffect(greenRealmRectangle);
        addHoverEffect(blueRealmRectangle);
        addHoverEffect(magentaRealmRectangle);
        addHoverEffect(yellowRealmRectangle);
    }
    private void addHoverEffect(Rectangle rectangle) {
        rectangle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setOpacity(0.3);
            }
        });

        rectangle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rectangle.setOpacity(0);
            }
        });
    }

    @FXML
    public void chooseRedRealm(){
        if(possibleRealms.contains(currentPlayer.getRealm(GameColor.RED))){
            sceneManager.showRealmPickerStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.RED);
        }
        else {
            label.setText("Not Available");
        }
    }
    @FXML
    public void chooseGreenRealm(){
        sceneManager.showRealmPickerStage();
        guiGameController.playColorBonus(currentPlayer,GameColor.GREEN);
    }
    @FXML
    public void chooseBlueRealm(){
        sceneManager.showRealmPickerStage();
        guiGameController.playColorBonus(currentPlayer,GameColor.BLUE);
    }
    @FXML
    public void chooseMagentaRealm(){
        sceneManager.showRealmPickerStage();
        guiGameController.playColorBonus(currentPlayer,GameColor.MAGENTA);
    }
    @FXML
    public void chooseYellowRealm(){
        sceneManager.showRealmPickerStage();
        guiGameController.playColorBonus(currentPlayer,GameColor.YELLOW);
    }

    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        RealmPickerController.sceneManager=sceneManager;
    }
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        RealmPickerController.currentPlayer=currentPlayer;
    }
    private static GUIGameController guiGameController;
    public static void setGuiGameController(GUIGameController guiGameController){
        RealmPickerController.guiGameController=guiGameController;
    }
    private static LinkedList<Realm> possibleRealms;
    public static void setPossibleRealms(LinkedList<Realm> possibleRealms){
        RealmPickerController.possibleRealms=possibleRealms;
    }


}

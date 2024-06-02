package game.gui;

import game.engine.Player;
import game.realms.Realm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

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
    public void chooseRedRealm(){
        if(possibleRealms.contains(GameColor.RED)){
            sceneManager.closeRealmStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.RED);
        }
        else {
            label.setText("Not Available");
        }
    }
    @FXML
    public void chooseGreenRealm(){
        if(possibleRealms.contains(GameColor.GREEN)){
            sceneManager.closeRealmStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.GREEN);
        }
        else {
            label.setText("Not Available");
        }
    }
    @FXML
    public void chooseBlueRealm(){
        if(possibleRealms.contains(GameColor.BLUE)){
            sceneManager.closeRealmStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.BLUE);
        }
        else {
            label.setText("Not Available");
        }
    }
    @FXML
    public void chooseMagentaRealm(){
        if(possibleRealms.contains(GameColor.MAGENTA)){
            sceneManager.closeRealmStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.MAGENTA);
        }
        else {
            label.setText("Not Available");
        }
    }
    @FXML
    public void chooseYellowRealm(){
        if(possibleRealms.contains(GameColor.YELLOW)){
            sceneManager.closeRealmStage();
            guiGameController.playColorBonus(currentPlayer,GameColor.YELLOW);
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

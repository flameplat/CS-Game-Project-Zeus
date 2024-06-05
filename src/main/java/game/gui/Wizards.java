package game.gui;

import game.engine.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Wizards implements Initializable, GameController{
    private static Player player1;
    private static Player player2;
    @FXML
    private Label mainLabel;
    @FXML
    private Rectangle redWizardRectangle;
    @FXML private Rectangle greenWizardRectangle;
    @FXML private Rectangle blueWizardRectangle;
    @FXML private Rectangle magentaWizardRectangle;
    @FXML private Rectangle yellowWizardRectangle;

    @FXML private ImageView mainImageView;
    @FXML private ImageView buttonImageView;
    private Image[] wizardImages;
    private Player currentPlayer;
    private SceneManager sceneManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wizardImages=new Image[5];
        Image button=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm());
        buttonImageView.setImage(button);
        Image redWizardImage=new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/redWizard.png")).toExternalForm());
        Image greenWizardImage=new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/greenWizard.png")).toExternalForm());
        Image blueWizardImage=new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/blueWizard.png")).toExternalForm());
        Image magentaWizardImage=new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/magentaWizard.png")).toExternalForm());
        Image yellowWizardImage=new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/yellowWizard.png")).toExternalForm());
        mainImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/AllWizards.png")).toExternalForm()));
        wizardImages[0]=redWizardImage;
        wizardImages[1]=greenWizardImage;
        wizardImages[2]=blueWizardImage;
        wizardImages[3]=magentaWizardImage;
        wizardImages[4]=yellowWizardImage;
        addHoverEffect(redWizardRectangle);
        addHoverEffect(greenWizardRectangle);
        addHoverEffect(blueWizardRectangle);
        addHoverEffect(magentaWizardRectangle);
        addHoverEffect(yellowWizardRectangle);
        player1=PlayerDataController.getPlayer1();
        player2=PlayerDataController.getPlayer2();
        currentPlayer=player1;
        mainLabel.setText(player1.getName()+", choose your wizard");

    }
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    public void chooseRedWizard(){
        chooseWizard(currentPlayer,wizardImages[0]);
    }
    public void chooseGreenWizard(){
        chooseWizard(currentPlayer,wizardImages[1]);
    }
    public void chooseBlueWizard(){
        chooseWizard(currentPlayer,wizardImages[2]);
    }
    public void chooseMagentaWizard(){
        chooseWizard(currentPlayer,wizardImages[3]);
    }
    public void chooseYellowWizard(){
        chooseWizard(currentPlayer,wizardImages[4]);
    }
    private void addHoverEffect(Rectangle rectangle) {
        rectangle.setOnMouseEntered(event -> rectangle.setOpacity(0.3));

        rectangle.setOnMouseExited(event -> rectangle.setOpacity(0));
    }


    public void chooseWizard(Player player,Image wizard){
        player.setWizardImage(wizard);
        if(player==player2){
            sceneManager.switchGamePlayScene();
            return;
        }
        mainLabel.setText(player2.getName()+", choose your wizard");
        currentPlayer=player2;
    }
}

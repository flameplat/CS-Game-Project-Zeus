package game.gui;

import game.engine.AIPlayer;
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
    @FXML
    private Rectangle greenWizardRectangle;
    @FXML
    private Rectangle blueWizardRectangle;
    @FXML
    private Rectangle magentaWizardRectangle;
    @FXML
    private Rectangle yellowWizardRectangle;

    @FXML private ImageView mainImageView;
    @FXML private ImageView buttonImageView;
    private Image[] wizardImages;
    private Player currentPlayer;
    private SceneManager sceneManager;

    /**
     * Initializes the Wizards class.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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
    /**
     * Sets the SceneManager for the Wizards class.
     *
     * @param sceneManager the SceneManager object to set
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     * Chooses the red wizard for the current player.
     * The method calls the chooseWizard method with the current player and the red wizard image.
     *
     * @see Wizards#chooseWizard(Player, Image)
     */
    public void chooseRedWizard(){
        chooseWizard(currentPlayer,wizardImages[0]);
    }
    /**
     * Allows the player to choose the green wizard for the current player
     */
    public void chooseGreenWizard(){
        chooseWizard(currentPlayer,wizardImages[1]);
    }
    /**
     * Choose the blue wizard for the current player.
     */
    public void chooseBlueWizard(){
        chooseWizard(currentPlayer,wizardImages[2]);
    }
    /**
     * Method to choose the Magenta Wizard for the current player.
     * Sets the wizard image for the player and performs necessary actions based on the game logic.
     *
     * @see Wizards#chooseWizard(Player, Image)
     */
    public void chooseMagentaWizard(){
        chooseWizard(currentPlayer,wizardImages[3]);
    }
    /**
     * Choose the yellow wizard for the current player.
     */
    public void chooseYellowWizard(){
        chooseWizard(currentPlayer,wizardImages[4]);
    }
    /**
     * Adds hover effect to a given rectangle.
     * When the mouse pointer enters the rectangle, its opacity is set to 0.3.
     * When the mouse pointer exits the rectangle, its opacity is set to 0.
     *
     * @param rectangle The rectangle to which the hover effect will be added.
     */
    private void addHoverEffect(Rectangle rectangle) {
        rectangle.setOnMouseEntered(event -> rectangle.setOpacity(0.3));
        rectangle.setOnMouseExited(event -> rectangle.setOpacity(0));
    }


    /**
     * Sets the wizard image for the given player and handles the logic for choosing wizards.
     *
     * @param player The player for whom the wizard image needs to be set.
     * @param wizard The wizard image to be set for the player.
     */
    public void chooseWizard(Player player,Image wizard){
        player.setWizardImage(wizard);
        if(player==player2){
            sceneManager.switchGamePlayScene();
            return;
        }
        if(player2 instanceof AIPlayer){
            for(int i=0;i<wizardImages.length;i++){
                if(player1.getWizardImage()!=wizardImages[i]){
                    player2.setWizardImage(wizardImages[i]);
                    break;
                }
            }
            sceneManager.switchGamePlayScene();
            return;
        }
        mainLabel.setText(player2.getName()+", choose your wizard");
        currentPlayer=player2;
    }
}

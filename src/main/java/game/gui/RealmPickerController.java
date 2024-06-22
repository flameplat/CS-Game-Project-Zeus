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



    /**
     * Initializes the RealmPickerController by setting the background image and initializing button images.
     *
     * @param url             the URL of the FXML file used to initialize the controller
     * @param resourceBundle the ResourceBundle used to initialize the controller
     */
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
    /**
     * Adds a hover effect to the given rectangle.
     *
     * @param rectangle the rectangle to which the hover effect is added
     */
    private void addHoverEffect(Rectangle rectangle) {
        rectangle.setOnMouseEntered(event -> rectangle.setOpacity(0.3));

        rectangle.setOnMouseExited(event -> rectangle.setOpacity(0));
    }

    /**
     * Chooses the red realm for the game.
     *
     * The method calls the {@link #chooseRealm(GameColor)} method and passes the color {@link GameColor#RED} as a parameter.
     * If the red realm is available, the current stage is closed and the color bonus is played for the current player.
     * If the player is an AI, the AI algorithm is executed, otherwise the UI is updated with the appropriate controller.
     * After the color bonus is played, the labels are reset.
     *
     * @see GameColor
     * @see RealmController
     * @see GUIGameController
     * @see SceneManager
     */
    @FXML
    public void chooseRedRealm() {
        chooseRealm(GameColor.RED);
    }

    /**
     * Choose the green realm in the game.
     *
     * This method is triggered when the user selects the green realm option. It will update the
     * game's selected realm to be the green realm.
     *
     * @FXML
     *
     * @see GameColor
     * @see chooseRealm
     */
    @FXML
    public void chooseGreenRealm() {
        chooseRealm(GameColor.GREEN);
    }

    /**
     * Selects the blue realm in the game.
     *
     * This method is an event handler that is triggered when the blue realm is chosen by the player. It calls the chooseRealm(GameColor) method
     * passing the GameColor.BLUE enum value as the parameter.
     */
    @FXML
    public void chooseBlueRealm() {
        chooseRealm(GameColor.BLUE);
    }

    /**
     * Chooses the Magenta realm.
     * This method is called when the user selects the Magenta realm option. It triggers the 'chooseRealm' method passing the Magenta color as a parameter.
     */
    @FXML
    public void chooseMagentaRealm() {
        chooseRealm(GameColor.MAGENTA);
    }

    /**
     * This method is triggered when the user chooses the yellow realm.
     * It calls the chooseRealm method with the GameColor.YELLOW parameter.
     */
    @FXML
    public void chooseYellowRealm() {
        chooseRealm(GameColor.YELLOW);
    }

    /**
     * This method is used to choose a realm color.
     *
     * @param realmColor The chosen realm color.
     */
    private void chooseRealm(GameColor realmColor) {
        if(possibleRealms.contains(realmColor)){
            Stage stage = (Stage) label.getScene().getWindow();
            stage.close();
            Platform.runLater(()->{
                guiGameController.playColorBonus(currentPlayer, realmColor);
                resetLabels();
            });

        }
        else {
            label.setText("Not Available");
        }
    }

    /**
     * Represents the SceneManager used in the RealmPickerController class.
     */
    private  SceneManager sceneManager;
    /**
     * Sets the scene manager for the RealmPickerController.
     * The scene manager is responsible for managing the scenes and navigation between them.
     *
     * @param sceneManager the SceneManager instance to set
     */
    public  void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }

    /**
     * Resets the labels in the RealmPickerController.
     * Sets the text of the label to "Choose a realm".
     */
    @Override
    public void resetLabels() {
        label.setText("Choose a realm");
    }

    /**
     * Represents the current player in the game.
     *
     * This variable is used to keep track of the current player
     * playing the game. It is set and updated as the game progresses.
     *
     * The type of the currentPlayer variable is Player, which is an
     * instance of the Player class.
     *
     * The currentPlayer variable is declared as private and static,
     * meaning it is accessible only within the containing class
     * (RealmPickerController) and it is shared among all instances of
     * the class.
     */
    private static Player currentPlayer;
    /**
     * Sets the current player for the realm picker.
     *
     * @param currentPlayer the player to be set as the current player
     */
    public static void setCurrentPlayer(Player currentPlayer){
        RealmPickerController.currentPlayer=currentPlayer;
    }
    /**
     * Represents the GUI game controller for managing the game interface.
     * This variable is intended to be used internally within the class.
     *
     * <p>
     * The GUI game controller is responsible for handling user interactions and controlling
     * the flow of the game on the graphical user interface (GUI). It provides methods
     * for managing game logic, updating the game state, and displaying information to
     * the user.
     * </p>
     *
     * <p>
     * This variable should be accessed and modified only within the class where it is declared.
     * </p>
     *
     * @see GUIGameController
     */
    private  GUIGameController guiGameController;
    /**
     * Sets the GUIGameController object for this instance.
     *
     * @param guiGameController the GUIGameController object to set
     */
    public  void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    /**
     * A private static LinkedList of GameColor representing the possible realms in a game.
     *
     * This variable stores the list of game colors that are possible realms in the game.
     */
    private static LinkedList<GameColor> possibleRealms;
    /**
     * Sets the possible realms.
     *
     * @param possibleRealms the list of possible game colors representing realms
     */
    public static void setPossibleRealms(LinkedList<GameColor> possibleRealms){
        RealmPickerController.possibleRealms=possibleRealms;
    }


}

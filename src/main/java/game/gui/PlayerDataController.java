package game.gui;

import game.engine.AIPlayer;
import game.engine.GameMode;
import game.engine.Player;
import game.exceptions.InvalidPlayerNameException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerDataController implements Initializable,GameController {
    @FXML
    private ImageView bg;
    @FXML
    private ImageView button1;
    @FXML
    private Label mainLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField textField;
    private static int playersSubmitted=0;
    private SceneManager sceneManager;
    private static Player player1;
    private static Player player2;
    @FXML
    private Label submitLabel;
    @FXML
    private Label AIvsAI;
    @FXML
    private ImageView AIvsAIImageView;


    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller after its root element has been completely processed.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/Wizards.jpeg")).toExternalForm());
        Image button=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm());
        bg.setImage(mainBG);
        button1.setImage(button);
        mainLabel.setText("Enter Player 1 Name");
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
        addHoverEffect(button1);
        submitLabel.setMouseTransparent(true);
        if(MainMenuController.getGameMode()==GameMode.SINGLEPLAYER){
            AIvsAI.setText("AI VS AI");
            AIvsAIImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
            AIvsAI.setMouseTransparent(true);
            addHoverEffect(AIvsAIImageView);
        }
        else{
            AIvsAI.setText("AI VS AI");
            AIvsAI.setOpacity(0);
            AIvsAIImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
            AIvsAI.setMouseTransparent(true);
            AIvsAIImageView.setOpacity(0);
            AIvsAIImageView.setMouseTransparent(true);
        }

    }
    /**
     * Adds a hover effect to the given ImageView by setting a DropShadow effect when the mouse enters the image view
     * and removing the effect when the mouse exits the image view.
     *
     * @param imageView The ImageView to apply the hover effect to.
     */
    private void addHoverEffect(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(10);
        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    /**
     * Sets the SceneManager for the PlayerDataController's scene management.
     *
     * @param sceneManager The SceneManager object used for scene management.
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     * Sets the name for the player.
     * If playersSubmitted is less than 1, it sets the name for player1.
     * If the game mode is SINGLEPLAYER, it creates an AIPlayer for player2 with the name "Zeus".
     * If playersSubmitted is greater than or equal to 1, it sets the name for player2.
     *
     * @throws InvalidPlayerNameException if the name is empty or contains special characters
     */
    public void setPlayerName() {
        try {
            if (playersSubmitted < 1) {
                int maxLen=15;
                if(textField.getText().length()>maxLen){
                    throw new InvalidPlayerNameException("Name is too long. Max: "+maxLen+" characters");
                }
                player1= new Player(textField.getText());
                errorLabel.setText("");
                if(MainMenuController.getGameMode()== GameMode.SINGLEPLAYER){
                    player2=new AIPlayer("Zeus");
                    sceneManager.switchWizardsScene();
                    return;
                }
                mainLabel.setText("Enter Player 2 Name");
                textField.setText("");
                playersSubmitted++;
            } else {

                if (player1 != null && textField.getText().equals(player1.getName())) {
                    throw new InvalidPlayerNameException("Name already in use!");
                }
                int maxLen=10;
                if(textField.getText().length()>maxLen){
                    throw new InvalidPlayerNameException("Name is too long. Max: "+maxLen+" characters");
                }
                player2= new Player(textField.getText());
                errorLabel.setText("");
                playersSubmitted++;
                sceneManager.switchWizardsScene();
            }
        } catch (InvalidPlayerNameException e) {
            errorLabel.setText(e.getMessage());
        }

    }
    public void AIvsAI(){
        player1= new AIPlayer("Athena");
        player2=new AIPlayer("Zeus");
        player1.setWizardImage(new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/redWizard.png")).toExternalForm()));
        player2.setWizardImage(new Image(Objects.requireNonNull(getClass().getResource("/images/wizards/magentaWizard.png")).toExternalForm()));
        sceneManager.switchGamePlayScene();
    }
    /**
     * Retrieves the instance of the player 1.
     *
     * @return the Player instance representing player 1
     */
    public static Player getPlayer1(){
        return player1;
    }
    /**
     * Retrieves the player object for Player 2.
     *
     * @return The Player object for Player 2.
     */
    public static Player getPlayer2(){
        return player2;
    }

}

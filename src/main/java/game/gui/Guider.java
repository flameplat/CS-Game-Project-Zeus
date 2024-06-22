package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Guider implements Initializable,RealmController {
    @FXML
    private Label messageLabel;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label title;
    @FXML
    private ImageView bg;
    @FXML
    private ImageView button;
    @FXML
    private Label buttonLabel;

    private String[] messages;
    private String[] titles;
    private int currentMessageIndex = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bg.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/textBoard.png")).toExternalForm()));
        button.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm()));
        String gameDescription="Welcome to Dice Realms: In Eldoria, wizards seek to conquer realms and collect Elemental Crests, symbols of supreme power over elements. These lands teem with ancient magic and wild creatures like Pyroclast Dragons, Gaia Guardians, Hydra Serpents, Majestic Phoenixes, and Solar Lions. As wizards, players must use magic and strategy to overcome these beings, seize the crests, and become Eldoria's mightiest mage.";
        String gameComponents="• Grimoire: Players use a grimoire to record scores and progress.\n" +
                "• Magical Dice: Each wizard gets a set of 6 magical dice for spells and elemental attacks.\n" +
                "• Tokens: Represent Elemental Crests and earned powers during the game.";
        String roundFlow=
                "• The game unfolds over 6 rounds, each comprising both active and passive wizardry. \n" +
                "• Wizards alternate between being the active spellcaster and the passive observer. \n" +
                "• At the start of each round, active spellcasters receive either a mystical power or an immediate-use bonus, as shown in the below table: \n" +
                "• Active spellcasters roll the magical dice up to 3 times per round.";
        String diceRolling=
                "• Active spellcasters roll all 6 dice, they choose one die to direct their magical attacks against creatures in the corresponding realm. \n" +
                "• Lower-value dice are banished to the Forgotten Realm, becoming available for the passive observer. \n" +
                "• This is repeated up to 3 times, then the mantle of active spellcaster passes to their opponent, to which he first must cast a spell from the ones in the Forgotten Realm.";
        String endRound=
                "• After both players exhausts their up 3 turns and each used the Forgotten Realm, this would signify the end of the current round. \n" +
                        "• The cycle repeats with a total of 6 rounds and then the game ends. \n"+
                        "Note:\n"+
                        "• If there are no possible moves for current dice you will have the option to use Time warp if available during your active turn, other than this the turn will be lost and the game continues automatically without interruption";

        String scoring=
                "• Both players tally their scores based on the creatures subdued and Elemental Crests collected in each realm. \n" +
                        "• The wizard with the highest score is declared the Protector of Eldoria. ";
        messages = new String[]{
                gameDescription,
                gameComponents,
                roundFlow,
                diceRolling,
                endRound,
                scoring
        };
        titles=new String[]{
                "1-Game Description",
                "2-Game Components",
                "3-Game Flow"
        };
        messageLabel.setText(messages[currentMessageIndex++]);
        title.setText(titles[currentTitleIndex++]);
        addHoverEffect(button);
        buttonLabel.setMouseTransparent(true);
    }
    /**
     * This variable represents the index of the current title.
     * It is a private integer and is used within the Guider class.
     * The value of currentTitleIndex determines the position of the current title in the title list.
     */
    private int currentTitleIndex;

    /**
     * Handles the action when the "Next" button is clicked.
     * If there are messages remaining to be displayed, it updates the message label with the next message
     * and the title with the next title.
     * If there are no more messages, it performs certain actions such as removing the anchor pane from its parent,
     * performing a reward action for the active player, highlighting the current game round, and enabling the roll button.
     * If the active player is an AI player, it automatically triggers the roll button.
     */
    @FXML
    private void handleNext() {
        if (currentMessageIndex < messages.length) {
            messageLabel.setText(messages[currentMessageIndex++]);
            if(currentTitleIndex<3){
                title.setText(titles[currentTitleIndex++]);
            }
        } else {
            AnchorPane parentAnchorPane = (AnchorPane) anchorPane.getParent();
            parentAnchorPane.getChildren().remove(anchorPane);
            parentAnchorPane.setMouseTransparent(true);
            guiGameController.performReward(guiGameController.getActivePlayer(), guiGameController.getRoundRewards()[0]);
            guiGameController.highlightCurrentRound();
            if(guiGameController.getActivePlayer().isAI()){
                guiGameController.rollButtonClick();
            }
            else{
                guiGameController.enableRollButton();
            }


        }
    }
    /**
     * Adds hover effect to an ImageView.
     *
     * @param imageView The ImageView to apply the hover effect to.
     */
    private void addHoverEffect(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.WHITE);
        shadow.setRadius(20);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    /**
     * Represents the SceneManager class responsible for managing scene switching and stage manipulation.
     * This class is used to switch between different scenes and stages in the application.
     */
    private SceneManager sceneManager;
    /**
     * An instance of the GUIGameController class that controls the GUI of the game.
     */
    private GUIGameController guiGameController;
    /**
     * Sets the GUI game controller for the Guider class.
     *
     * @param guiGameController the GUI game controller to set
     */
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    /**
     * Sets the SceneManager for the Guider class.
     *
     * @param sceneManager The SceneManager to set.
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }

    /**
     * Resets the labels in the Guider class to their default values.
     */
    @Override
    public void resetLabels() {

    }
//    public void setMessageLabel(String message,String title){
//        messageLabel.setText(message);
//        this.title.setText(title);
//    }
}

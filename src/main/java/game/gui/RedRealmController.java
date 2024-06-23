package game.gui;
import game.creatures.Dragon;
import game.dice.RedDice;
import game.engine.Move;
import game.engine.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RedRealmController implements Initializable ,RealmController{
    @FXML private Label label;
    @FXML private ImageView BG;
    @FXML private ImageView dragon1;
    @FXML private ImageView dragon2;
    @FXML private ImageView dragon3;
    @FXML private ImageView dragon4;
    @FXML private Label possibleAttackLabel;
    @FXML private Label playerLabel;

    /**
     * Initializes the RedRealmController with the specified URL and ResourceBundle. Sets the background image
     * and dragon images.
     *
     * @param url             The location used to resolve relative paths for the root object, or {@code null}
     * @param resourceBundle The resources used to localize the root object, or {@code null}
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/redRealmBackground.jpg")).toExternalForm());
        Image dragons =new Image(Objects.requireNonNull(getClass().getResource("/images/RedDragon.png")).toExternalForm());
        BG.setImage(mainBG);
        dragon1.setImage(dragons);
        dragon2.setImage(dragons);
        dragon3.setImage(dragons);
        dragon4.setImage(dragons);
    }
    /**
     * The attack method is used to perform an attack action in the game.
     *
     * @param dieValue     the value of the die used for the attack
     * @param dragonNumber the number of the dragon being attacked
     */
    public void attack(int dieValue, int dragonNumber) {
        RedDice die = new RedDice(dieValue);
        die.selectsDragon(dragonNumber);
        Move move = new Move(die, currentPlayer.getRealm(die).getCreature(die));

        if (possibleMoves.contains(move)) {

            Stage stage = (Stage) dragon1.getScene().getWindow();
            stage.close();
            Platform.runLater(() -> {
                guiGameController.makeMove(currentPlayer,move);
                resetLabels();
            });

        } else {
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: " + possibleMoves.get(0).getDice().getName());
        }
    }

    /**
     * Attacks the face of a dragon using the specified die value and dragon number.
     */
    public void attackFaceD1() {
        attack(3, 1);
    }

    /**
     * Attacks a specific dragon's wing in the RedRealm.
     * It selects the dragon with the specified dragonNumber and rolls the red die with dieValue.
     * If the attack is valid, it makes the move and closes the stage.
     * Otherwise, it displays an error message.
     */
    public void attackWingD1() {
        attack(2, 1);
    }

    /**
     * Method: attackTailD1
     * Description: This method is used to perform an attack on the tail of Dragon1 using a RedDice with a value of 1.
     *
     * Usage: attackTailD1();
     *
     * Example:
     *   RedRealmController redRealmController = new RedRealmController();
     *   redRealmController.attackTailD1();
     *
     * Dependencies:
     *   - Class: RedDice
     *     - Method: selectsDragon(int dragonNumber)
     *   - Interface: RealmController
     *     - Method: resetLabels()
     *   - Class: GUIGameController
     *     - Method: makeMove(Player player, Move move)
     *
     * @see RedRealmController
     * @see RedDice
     * @see RealmController
     * @see GUIGameController
     */
    public void attackTailD1() {
        attack(1, 1);
    }

    /**
     *
     */
    public void attackFaceD2() {
        attack(6, 2);
    }

    /**
     * Executes an attack with the specified die value and dragon number.
     *
     * @param dieValue     The value of the die used for the attack.
     * @param dragonNumber The number of the dragon being attacked.
     */
    public void attackWingD2() {
        attack(1, 2);
    }

    /**
     * Attacks the heart of dragon D2.
     * Calls the attack method with a die value of 3 and dragon number 2.
     */
    public void attackHeartD2() {
        attack(3, 2);
    }

    /**
     * Attacks the face of a dragon with a RedDice of value 3.
     * This method selects the specified dragon and creates a Move object.
     * If the Move is a possible move, the current stage is closed and the move is executed on the GUIGameController.
     * After the move is made, the labels are reset.
     * If the Move is not possible, an error message is displayed.
     */
    public void attackFaceD3() {
        attack(5, 3);
    }

    /**
     * This method initiates an attack on a specific dragon's tail with a given die value.
     * It calls the attack method with the specified die value and dragon number.
     */
    public void attackTailD3() {
        attack(2, 3);
    }

    /**
     * Attacks the heart of a dragon using a red dice and the selected dragon number.
     * If the attack is valid, it makes the move, updates the labels, and closes the stage.
     * If the attack is not valid, it sets the label text to indicate that the attack is not possible.
     *
     * @param dieValue     the value of the red dice used for the attack
     * @param dragonNumber the number of the dragon being attacked
     */
    public void attackHeartD3() {
        attack(4, 3);
    }

    /**
     * Attacks a specific dragon in the wing area using a specified die value.
     * The attack is performed by creating a move object with the selected die and the creature in the current player's realm corresponding to that die.
     * If the created move is one of the possible moves, the move is executed by calling makeMove method on the GUI game controller.
     * After the move is executed, all labels are reset.
     * If the created move is not one of the possible moves, a message is displayed on the label indicating that the selected part cannot be attacked.
     *
     * @param dieValue     the value of the die to be used for the attack
     * @param dragonNumber the number of the dragon to be attacked in the wing area
     */
    public void attackWingD4() {
        attack(5, 4);
    }

    /**
     * Attacks the tail of dragon D4.
     * @throws NullPointerException if currentPlayer or guiGameController is null
     */
    public void attackTailD4() {
        attack(4, 4);
    }

    /**
     * Attacks the heart of Dragon 4 using a selected die value.
     */
    public void attckHeartD4() {
        attack(6, 4);
    }

    /**
     * The hoverFaceD1 method is used to hover over the face of dragon 1.
     * It calls the hover method with attackValue set to 3 and dragonNumber set to 1.
     * If the move is found in the list of possible moves, it highlights the move on the score sheet.
     *
     * @see RedRealmController
     * @see RedDice
     * @see Move
     * @see GUIGameController
     * @see SceneManager
     */
    public void hoverFaceD1() {
        hover(3, 1);
    }

    /**
     * Hover the specified dragon with the given attack value.
     *
     * @param attackValue The attack value.
     * @param dragonNumber The dragon number.
     */
    public void hoverWingD1() {
        hover(2, 1);
    }

    /**
     * Performs a hover action on a specified dragon with a given attack value.
     *
     * @param attackValue The attack value to use.
     * @param dragonNumber The number of the dragon to hover.
     */
    public void hoverTailD1() {
        hover(1, 1);
    }

    /**
     *
     */
    public void hoverFaceD2() {
        hover(6, 2);
    }

    /**
     * Hover over the specified dragon on Wing D2.
     * Calls the hover method with the attack value as 1 and the dragon number as 2.
     * If the move is a possible move, it highlights the possible move on the score sheet.
     *
     * @see RedRealmController#hover(int, int)
     */
    public void hoverWingD2() {
        hover(1, 2);
    }

    /**
     *
     */
    public void hoverHeartD2() {
        hover(3, 2);
    }

    /**
     * Hover the face of the dragon with the given attack value and dragon number.
     *
     * @param attackValue The value of the attack
     * @param dragonNumber The number of the dragon
     */
    public void hoverFaceD3() {
        hover(5, 3);
    }

    /**
     *  Hover the dice over a specific dragon if the move is possible.
     *  Highlights the possible move if it exists.
     *
     *  @param attackValue the attack value of the hover action
     *  @param dragonNumber the number of the dragon to hover over
     */
    public void hoverTailD3() {
        hover(2, 3);
    }

    /**
     * This method is used to perform the hover action on a dragon in the RedRealmController.
     * It highlights the possible moves for the current player based on the attack value and the dragon number.
     *
     * @param attackValue   the attack value to determine the available moves
     * @param dragonNumber  the number of the dragon to perform the hover action on
     */
    public void hoverHeartD3() {
        hover(4, 3);
    }

    /**
     * Selects a dragon on the red dice and highlights the possible move if it exists.
     *
     * @param attackValue   the attack value
     * @param dragonNumber  the dragon number to select
     */
    public void hoverWingD4() {
        hover(5, 4);
    }

    /**
     *
     */
    public void hoverTailD4() {
        hover(4, 4);
    }

    /**
     * Highlights a specific dragon on the game board based on the attack value and dragon number.
     * If the move is a possible move, the method will highlight the dragon.
     *
     * @param attackValue  the attack value used to select the red dice
     * @param dragonNumber the number of the dragon to be highlighted
     */
    public void hoverHeartD4() {
        hover(6, 4);
    }
    /**
     * Selects a dragon for the current red dice and performs a hover action.
     *
     * @param attackValue the attack value of the red dice
     * @param dragonNumber the number of the dragon to select
     */
    public void hover(int attackValue,int dragonNumber){
        RedDice redDice=new RedDice(attackValue);
        redDice.selectsDragon(dragonNumber);
        Move move=new Move(redDice,new Dragon(dragonNumber));
        if(possibleMoves.contains(move)){
            currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
        }

    }
    /**
     * Removes the highlighting from all cells in the score sheets of the realms.
     * This method should be called to remove the highlight after highlighting possible moves.
     */
    public void removeHighlight(){
        currentPlayer.getScoreSheetController().removeHighlight();
    }


    /**
     * SceneManager manages the scenes in the RedRealmController class.
     */
    private SceneManager sceneManager;
    /**
     *
     */
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    /**
     *
     */
    private GUIGameController guiGameController;
    /**
     * Sets the GUI game controller.
     *
     * @param guiGameController The GUI game controller to set.
     */
    public void setGuiGameController(GUIGameController guiGameController){
       this.guiGameController=guiGameController;
    }
    /**
     * Represents the current player in the game.
     */
    private static Player currentPlayer;
    /**
     * The possibleMoves variable is a private static LinkedList of type Move. It represents a list
     * of possible moves that can be made in the game. Each move is represented by an instance of the Move class.
     *
     * The Move class is a custom class that represents a move in the game. It contains the information about the
     * dice used for determining the movement distance and the creature to be moved. The Move class implements the
     * Comparable interface to allow for comparison between moves. It also overrides the equals() method for checking
     * equality and provides methods for retrieving the dice and creature associated with the move. The isExecuted flag
     * indicates whether the move has been executed.
     *
     * The RealmController interface is an interface that provides methods for setting the GUIGameController and SceneManager
     * objects. It also provides a resetLabels() method for resetting the labels in the GUI.
     */
    private static LinkedList<Move> possibleMoves;
    /**
     * Sets the list of possible moves to the given array of Move objects.
     *
     * @param moves the array of Move objects representing the possible moves
     */
    public static void setPossibleMoves(Move[] moves){
        RedRealmController.possibleMoves = new LinkedList<>(Arrays.asList(moves));
    }
    /**
     * Sets the current player in the RedRealmController class.
     *
     * @param currentPlayer the player to set as the current player
     */
    public static void setCurrentPlayer(Player currentPlayer){
        RedRealmController.currentPlayer=currentPlayer;
    }
    /**
     * Sets the label for the player.
     * Concatenates the name of the current player with a message reminding them to choose a region to attack.
     */
    public void setLabel(){
        playerLabel.setText(currentPlayer.getName() + ", choose a region to attack!");
    }
    /**
     * Resets the labels in the RedRealmController.
     * Sets the text of playerLabel, label, and possibleAttackLabel to an empty string.
     */
    @Override
    public void resetLabels(){
        playerLabel.setText("");
        label.setText("");
        possibleAttackLabel.setText("");
    }
}

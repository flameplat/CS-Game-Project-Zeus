package game.gui;

import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.EssenceBonus;
import game.creatures.Dragon;
import game.dice.*;
import game.engine.*;
import game.exceptions.InvalidMoveException;
import game.exceptions.NoAvailableMovesException;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.CollectiblesComparator;
import game.utilities.GameColor;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The GUIGameController class controls the GUI version of the game.
 */
public class GUIGameController extends CLIGameController implements Initializable, GameController {
    //This player points to the current player whether passive or active or arcaneBoost enabled
    private static Player currentPlayer;
    private static Player player1;
    private static Player player2;
    private final Image transparentImage;
    private final double lowOpacity = 0.5;
    private final double highOpacity = 1;

    @FXML
    private ImageView border;
    @FXML
    private ImageView border2;
    private StackPane[] diceGUI;
    @FXML
    private Label gameText;
    @FXML
    private ImageView gameTextBox;
    @FXML
    private ImageView rollButtonImage;
    @FXML
    private ImageView round1Reward;
    @FXML
    private ImageView round2Reward;
    @FXML
    private ImageView round3Reward;
    @FXML
    private ImageView round4Reward;
    @FXML
    private ImageView round5Reward;
    @FXML
    private ImageView round6Reward;
    @FXML
    private Label redDiceNumber;
    @FXML
    private Label greenDiceNumber;
    @FXML
    private Label blueDiceNumber;
    @FXML
    private Label magentaDiceNumber;
    @FXML
    private Label yellowDiceNumber;
    @FXML
    private Label whiteDiceNumber;
    @FXML
    private Label redDiceNumber1;
    @FXML
    private Label greenDiceNumber1;
    @FXML
    private Label blueDiceNumber1;
    @FXML
    private Label magentaDiceNumber1;
    @FXML
    private Label yellowDiceNumber1;
    @FXML
    private GridPane diceGridArcanePrism1;
    @FXML
    private Label redDiceNumber2;
    @FXML
    private Label greenDiceNumber2;
    @FXML
    private Label blueDiceNumber2;
    @FXML
    private Label magentaDiceNumber2;
    //------------------------------------------------------------------------------------------------//
    @FXML
    private Label yellowDiceNumber2;
    @FXML
    private GridPane diceGridArcanePrism2;
    @FXML
    private GridPane diceGrid;
    @FXML
    private GridPane forgottenRealmGrid;
    @FXML
    private GridPane roundsTable;
    private SceneManager sceneManager;
    @FXML
    private ImageView forgottenRealmImageView;
    @FXML
    private ImageView timeWarpImageView;
    @FXML
    private ImageView arcaneBoostImageView;
    @FXML
    private ImageView skipButtonImageView;
    @FXML
    private StackPane redDice;
    @FXML
    private StackPane greenDice;
    @FXML
    private StackPane blueDice;
    @FXML
    private StackPane magentaDice;
    @FXML
    private StackPane yellowDice;
    @FXML
    private StackPane whiteDice;
    @FXML
    private StackPane timeWarpStackPane;
    @FXML
    private StackPane arcaneBoostStackPane;
    @FXML
    private StackPane skipButtonStackPane;
    @FXML
    private Label currentPlayerLabel;
    @FXML
    private Label currentGameStatusLabel;
    @FXML
    private ImageView currentGameStatusImageView;
    @FXML
    private ImageView currentPlayerImageView;
    @FXML
    private Label turnLabel;
    @FXML
    private ImageView turnImageView;
    @FXML
    private StackPane rollButtonStackPane;
    private Dice[] refDiceArray;
    @FXML
    private Button arcaneBoostButtonClicker;
    @FXML
    private Button timeWarpButtonClicker;
    @FXML
    private Button skipButtonClicker;
    //------------------------------------------------------BUTTONS FUNCTIONS-------------------------------------------------------//
    private int realRounds = 1;
    private boolean arcanePrismEnabled;
    private GridPane whiteDieParent;
    private GridPane whiteDieDestination;
    @FXML
    private ImageView currentPlayerImageViewMain;
    @FXML
    private Button rollButtonClicker;
    //------------------------------------------------------GAME LOGIC METHODS-------------------------------------------------------//
    @FXML
    private AnchorPane guiderAnchorPane;

    /**
     * Constructs a new GUIGameController object.
     * Initializes the transparentImage with a WritableImage of size 1x1 and sets the pixel color to transparent.
     * Sets the gameStatus to ACTIVE_TURN.
     */
    public GUIGameController() {
        super();
        WritableImage transparentImage = new WritableImage(1, 1);
        PixelWriter pixelWriter = transparentImage.getPixelWriter();
        pixelWriter.setColor(0, 0, Color.rgb(0, 0, 0, 0));
        this.transparentImage = transparentImage;
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);

    }

    /**
     * This method checks if player1 is currently playing the game.
     *
     * @return true if player1 is playing, false otherwise.
     */
    public static boolean isPlayer1Playing() {
        return currentPlayer == player1;
    }

    /**
     * Initializes the GUI components and sets their initial states.
     *
     * @param url            The URL of the FXML file to be loaded
     * @param resourceBundle The ResourceBundle for the FXML file
     */
    //------------------------------------------------------ONE TIME METHODS-------------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView[] roundRewardImageViews = new ImageView[]{
                round1Reward, round2Reward, round3Reward,
                round4Reward, round5Reward, round6Reward
        };
        for (int i = 0; i < CLIGameController.MAX_NUMBER_OF_ROUNDS; i++) {
            if (i < roundRewards.length) {
                roundRewardImageViews[i].setImage(getRewardIcon(i, roundRewards));
            } else {
                roundRewardImageViews[i].setImage(null); // or a default image
            }
        }
        diceGUI = new StackPane[6];
        diceGUI[0] = redDice;
        diceGUI[1] = greenDice;
        diceGUI[2] = blueDice;
        diceGUI[3] = magentaDice;
        diceGUI[4] = yellowDice;
        diceGUI[5] = whiteDice;
        rollButtonImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm()));
        gameTextBox.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/textBox.png")).toExternalForm()));
        forgottenRealmImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/ForgottenRealm.png")).toExternalForm()));
        timeWarpImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/TimeWarp.png")).toExternalForm()));
        arcaneBoostImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ArcaneBoost.png")).toExternalForm()));
        skipButtonImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
        currentGameStatusImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/2.png")).toExternalForm()));
        currentPlayerImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/2.png")).toExternalForm()));
        turnImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/2.png")).toExternalForm()));
        updateDiceValues();
        disableAllButtons();
        disableGrid(diceGridArcanePrism1);
        disableGrid(diceGridArcanePrism2);

        Rectangle clip = new Rectangle(
                currentPlayerImageViewMain.getFitWidth(), currentPlayerImageViewMain.getFitHeight()
        );
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        currentPlayerImageViewMain.setClip(clip);
        border.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/4.png")).toExternalForm()));
        border2.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/4.png")).toExternalForm()));
        addHoverEffect(rollButtonImage, rollButtonClicker);
        addHoverEffect(arcaneBoostImageView, arcaneBoostButtonClicker);
        addHoverEffect(timeWarpImageView, timeWarpButtonClicker);
        addHoverEffect(skipButtonImageView, skipButtonClicker);

    }

    /**
     * Adds a hover effect to the provided ImageView when the corresponding button is hovered over.
     *
     * @param imageView The ImageView to apply the hover effect to.
     * @param button    The button that triggers the hover effect.
     */
    private void addHoverEffect(ImageView imageView, Button button) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(20);

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    //------------------------------------------------------DICE RELATED METHODS & UPDATERS-------------------------------------------------------//

    /**
     * Sets the SceneManager for the GUIGameController.
     *
     * @param sceneManager The SceneManager to set for the GUIGameController.
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Sets the Player 1 for the game.
     *
     * @param player the Player 1 object to set
     */
    public void setPlayer1(Player player) {
        gameBoard.setPlayer1(player);
        activePlayer = player;
        player.setPlayerStatus(PlayerStatus.ACTIVE);
        currentPlayer = activePlayer;
        updateSceneStatus();
        GUIGameController.player1 = player;
        if (player.isAI()) {
            ((AIPlayer) player).setGuiGameController(this);
            System.out.println(player.getName() + " is AI");
        } else {
            System.out.println(player.getName() + " is not AI");
        }

    }

    /**
     * Sets the player2 of the game.
     *
     * @param player the player to set as player2
     */
    public void setPlayer2(Player player) {
        gameBoard.setPlayer2(player);
        passivePlayer = player;
        player.setPlayerStatus(PlayerStatus.PASSIVE);
        GUIGameController.player2 = player;
        if (player.isAI()) {
            ((AIPlayer) player).setGuiGameController(this);
            System.out.println(player.getName() + " is AI");
        } else {
            System.out.println(player.getName() + " is not AI");
        }
    }

    /**
     * Sets the score sheet for player 1.
     *
     * @param scoreSheet The composite score sheet controller for player 1.
     */
    public void setPlayer1ScoreSheet(CompositeScoreSheetController scoreSheet) {
        activePlayer.setGUIScoreSheet(scoreSheet);
    }

    /**
     * Sets the score sheet for player 2.
     *
     * @param scoreSheet The CompositeScoreSheetController representing the score sheet for player 2.
     */
    public void setPlayer2ScoreSheet(CompositeScoreSheetController scoreSheet) {
        passivePlayer.setGUIScoreSheet(scoreSheet);
    }

    /**
     * Retrieves the reward icon image based on the index and the rewards array.
     *
     * @param i       the index of the reward in the rewards array
     * @param rewards the array of collectibles rewards
     * @return the reward icon image
     */
    public Image getRewardIcon(int i, Collectibles[] rewards) {
        Image result;
        if (rewards[i] == null) {
            return transparentImage;
        }
        switch (rewards[i].toString()) {
            case "TW":
                result = new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/TimeWarp.png")).toExternalForm());
                break;
            case "AB":
                result = new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ArcaneBoost.png")).toExternalForm());
                break;
            case "EB":
                result = new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/EssenceBonus.png")).toExternalForm());
                break;
            case "EC":
                result = new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ElementalCrest.png")).toExternalForm());
                break;
            default:
                result = new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toExternalForm());


        }
        return result;
    }

    /**
     * Sets the game mode for the game controller.
     *
     * @param gameMode The game mode to be set.
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     *
     */
    private void moveDice(StackPane dice, GridPane grid) {
        GridPane parent = (GridPane) dice.getParent();

        Integer rowIndex = GridPane.getRowIndex(dice);
        Integer columnIndex = GridPane.getColumnIndex(dice);

        if (rowIndex == null) rowIndex = 0;
        if (columnIndex == null) columnIndex = 0;

        parent.getChildren().remove(dice);
        grid.add(dice, columnIndex, rowIndex);
    }

    /**
     * Rolls the dice, setting their values to randomly generated values between 1 and 6.
     * Only rolls the dice that are in the AVAILABLE status.
     * Updates the dice values and returns the array of dice.
     *
     * @return an array of Dice objects with updated values
     */
    @FXML
    public Dice[] rollDice() {
        //Rolling only rolls available dice
        Random random = new Random();
        int diceValue;
        //Dice values are from 1 to 6
        int diceMaxBound = 6;
        int diceMinBound = 1;
        //Use this if you want to test max scores
//        for (int i = 0; i < diceArray.length; i++) {
//            if (diceArray[i].getDiceStatus() == DiceStatus.AVAILABLE) {
//                if (i < 2) {
//                    diceArray[i].setValue(random.nextInt(diceMaxBound - diceMinBound + 1) + diceMinBound);
//                } else {
//                    diceArray[i].setValue(6);
//                }
//            }
//        }
//        Original Dice Rolling
        for (Dice dice : diceArray) {
            if (dice != null && dice.getDiceStatus() == DiceStatus.AVAILABLE) {
                diceValue = random.nextInt(diceMaxBound - diceMinBound + 1) + diceMinBound;
                dice.setValue(diceValue);
            }
        }
        updateDiceValues();
        return diceArray;
    }

    /**
     * Resets all dice to the main deck and sets their status to AVAILABLE. Additionally, it disables the buttons of the dice in the main board and the forgotten realm grid.
     */
    //Returns all dice to main deck and sets them to available and disable their buttons
    private void resetDice() {
        for (int i = 0; i < diceArray.length; i++) {
            diceArray[i].setDiceStatus(DiceStatus.AVAILABLE);
            moveDice(diceGUI[i], diceGrid);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();
        }
    }

    /**
     * Selects a dice for a player.
     *
     * @param dice   The dice to be selected.
     * @param player The player who is selecting the dice.
     * @return true if the dice was successfully selected, false otherwise.
     */
    @Override
    public boolean selectDice(Dice dice, Player player) {
        if (diceArray != refDiceArray) {
            return false;
        }
        if (dice == null || player == null) {
            System.err.println("Error: Dice or Player cannot be null.");
            return false;
        }
        try {
            dice.setDiceStatus(DiceStatus.TURN_SELECTED);
            diceGUI[dice.getRealm().ordinal()].setDisable(true);
            diceGUI[dice.getRealm().ordinal()].setOpacity(lowOpacity);
            if (gameStatus.getGameStatus() == CurrentStatus.ACTIVE_TURN) {
                for (Dice diceFromArray : diceArray) {
                    if (diceFromArray.getDiceStatus() == DiceStatus.AVAILABLE && diceFromArray.getValue() < dice.getValue()) {
                        diceFromArray.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
                        moveDice(diceGUI[diceFromArray.getRealm().ordinal()], forgottenRealmGrid);
                    }
                }
                disableForgottenRealmButtons();
            }
            return true;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    /**
     * Moves the rest of the dice unselected by active player to forgotten realm.
     */
    private void moveDiceToForgottenRealm() {
        //Moves the rest of the dice unselected by active player to forgotten realm
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                i.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
                moveDice(diceGUI[i.getRealm().ordinal()], forgottenRealmGrid);
            }
        }
        disableForgottenRealmButtons();
    }

    /**
     * Disables the arcane boost button by setting the stack pane to be disabled and with low opacity.
     */
    //------------------------------------------------------BUTTONS AVAILABILITY METHODS-------------------------------------------------------//
    private void disableArcaneBoostButton() {
        arcaneBoostStackPane.setDisable(true);
        arcaneBoostStackPane.setOpacity(lowOpacity);
    }

    /**
     *
     */
    private void disableTimeWarpButton() {
        timeWarpStackPane.setDisable(true);
        timeWarpStackPane.setOpacity(lowOpacity);
    }

    /**
     * Disables the skip button by setting its disable property to true
     * and reducing its opacity to a low value.
     * <p>
     * This method is private and cannot be accessed outside of the class that contains it.
     * It does not return any value.
     */
    private void disableSkipButton() {
        skipButtonStackPane.setDisable(true);
        skipButtonStackPane.setOpacity(lowOpacity);
    }

    /**
     *
     */
    private void enableArcaneBoostButton() {
        arcaneBoostStackPane.setDisable(false);
        arcaneBoostStackPane.setOpacity(highOpacity);
    }

    /**
     *
     */
    private void enableTimeWarpButton() {
        timeWarpStackPane.setDisable(false);
        timeWarpStackPane.setOpacity(highOpacity);
    }

    /**
     *
     */
    private void enableSkipButton() {
        skipButtonStackPane.setDisable(false);
        skipButtonStackPane.setOpacity(highOpacity);
    }

    /**
     *
     */
    private void disableForgottenRealmButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == forgottenRealmGrid) {
                die.setDisable(true);
                die.setOpacity(lowOpacity);
            }
        }
    }

    /**
     *
     */
    private void enableForgottenRealmButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == forgottenRealmGrid) {
                die.setDisable(false);
                die.setOpacity(highOpacity);
            }
        }
    }

    /**
     * This method is used to disable the main board dice buttons.
     * It iterates through each dice in the diceGUI array and checks if it is a child of the diceGrid.
     * If it is, it sets the dice to be disabled and reduces its opacity.
     */
    private void disableMainBoardDiceButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == diceGrid) {
                die.setDisable(true);
                die.setOpacity(lowOpacity);
            }
        }
    }

    /**
     *
     */
    private void enableMainBoardDiceButtons() {
        for (Dice die : diceArray) {
            if (die.getDiceStatus() == DiceStatus.AVAILABLE) {
                if (diceGUI[die.getRealm().ordinal()].getParent() == diceGrid) {
                    diceGUI[die.getRealm().ordinal()].setDisable(false);
                    diceGUI[die.getRealm().ordinal()].setOpacity(highOpacity);
                }
            }
        }
    }

    /**
     *
     */
    private void disableRollButton() {
        rollButtonStackPane.setDisable(true);
        rollButtonStackPane.setOpacity(lowOpacity);
    }

    /**
     * Enables the roll button by removing the disable attribute and setting a high opacity.
     * Once enabled, the roll button can be clicked by the user.
     */
    protected void enableRollButton() {
        rollButtonStackPane.setDisable(false);
        rollButtonStackPane.setOpacity(highOpacity);
    }

    /**
     *
     */
    private void disableAllButtons() {
        disableRollButton();
        disableMainBoardDiceButtons();
        disableArcaneBoostButton();
        disableTimeWarpButton();
        disableSkipButton();
        disableForgottenRealmButtons();
    }

    /**
     * Highlights the possible moves for a red dice when the mouse hovers over the red dice button.
     * <p>
     * This method retrieves the possible moves for a red dice based on the current player and the reference
     * dice array. It then calls the highlightPossibleMoves method to highlight these possible moves.
     */
    //------------------------------------------------------HOVER METHODS-------------------------------------------------------//
    @FXML
    public void redDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[0]));
    }

    /**
     * Highlights the possible moves for the green dice button when it is being hovered on.
     * It calls the highlightPossibleMoves method with the possible moves for the current player and the second dice on the reference dice array.
     */
    @FXML
    public void greenDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[1]));
    }

    /**
     * Highlights the possible moves for the current player when the blue dice button is hovered on.
     */
    @FXML
    public void blueDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[2]));
    }

    /**
     * Highlights possible moves for the magenta dice button when hovered on.
     */
    @FXML
    public void magentaDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[3]));
    }

    /**
     * Method to handle the event when the yellow dice button is hovered upon.
     * It highlights the possible moves for a die of the current player.
     */
    @FXML
    public void yellowDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[4]));
    }

    /**
     * Highlights the possible moves for a white dice when the button is hovered on.
     */
    @FXML
    public void whiteDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[5]));
    }

    /**
     * This method is used to remove the highlight from the score sheet when the dice button is no longer being hovered over.
     */
    public void diceButtonHoverOff() {
        removeScoreSheetHighlight();
    }

    /**
     * Highlights the possible moves on the score sheet for the current player.
     *
     * @param moves an array of Move objects representing the possible moves
     */
    public void highlightPossibleMoves(Move[] moves) {
        currentPlayer.getScoreSheetController().highlightPossibleMoves(moves);
    }

    /**
     * Removes the highlight from the score sheet of the current player.
     */
    @FXML
    public void removeScoreSheetHighlight() {
        currentPlayer.getScoreSheetController().removeHighlight();
    }

    /**
     * Highlights the current round in the game.
     * This method removes any previous highlights from the round table and then highlights the cell
     * corresponding to the current round with the color white.
     */
    //------------------------------------------------------UPDATERS-------------------------------------------------------//
    protected void highlightCurrentRound() {
        removeRoundTableHighlight();
        int round = gameStatus.getRound();
        highlightCell(round, "white");
    }

    /**
     * Removes the highlighting from the cells in the round table.
     * This method iterates over each column in the round table and calls the `highlightCell` method with a `null` value as the highlight color
     */
    private void removeRoundTableHighlight() {
        for (int i = 0; i < roundsTable.getColumnCount(); i++) {
            highlightCell(i, null);
        }
    }

    /**
     * Highlights the cell in the specified column with the specified color.
     *
     * @param column the index of the column to highlight
     * @param color  the color to apply to the cell (in CSS format, e.g. "#FF0000" for red)
     */
    private void highlightCell(int column, String color) {
        for (Node node : roundsTable.getChildren()) {
            if (node instanceof Label) {
                Integer colIndex = GridPane.getColumnIndex(node);
                if (colIndex != null && colIndex == column) {
                    if (color == null) {
                        node.setStyle("");
                        break;
                    }
                    node.setStyle(String.format("-fx-background-color: %s;", color));
                    break;
                }
            }
        }
    }

    /**
     * Updates the score sheets for both the active and passive players.
     * <p>
     * This method is responsible for updating the score sheets of both the active and passive players.
     * It calls the updateScoreSheet() method on the score sheet controllers of both players, to ensure
     * that the score sheets are updated with the latest scores.
     * <p>
     * Note: This method does not return any value.
     */
    private void updateScoreSheets() {
        activePlayer.getScoreSheetController().updateScoreSheet();
        passivePlayer.getScoreSheetController().updateScoreSheet();

    }

    /**
     * Updates the values of the dice in the graphical user interface.
     * Uses the diceArray to set the text value of the corresponding dice labels.
     * Each dice value is converted to a string and assigned to the text of the respective label.
     *
     * @return void
     */
    private void updateDiceValues() {
        redDiceNumber.setText(String.valueOf(diceArray[0].getValue()));
        greenDiceNumber.setText(String.valueOf(diceArray[1].getValue()));
        blueDiceNumber.setText(String.valueOf(diceArray[2].getValue()));
        magentaDiceNumber.setText(String.valueOf(diceArray[3].getValue()));
        yellowDiceNumber.setText(String.valueOf(diceArray[4].getValue()));
        whiteDiceNumber.setText(String.valueOf(diceArray[5].getValue()));
    }

    /**
     * Updates the scene status by setting the text and image properties of various labels and image views.
     * The currentPlayerLabel shows the name of the current player.
     * The currentGameStatusLabel shows the current game status as a string.
     * The currentPlayerImageViewMain displays the wizard image of the current player.
     * The turnLabel displays the turn number if the game status is ACTIVE_TURN, otherwise it is cleared.
     */
    private void updateSceneStatus() {
        currentPlayerLabel.setText(currentPlayer.getName());
        currentGameStatusLabel.setText(gameStatus.getGameStatus().toString());
        currentPlayerImageViewMain.setImage(currentPlayer.getWizardImage());
        if (gameStatus.getGameStatus() == CurrentStatus.ACTIVE_TURN) {
            turnLabel.setText("Turn " + gameStatus.getTurn());
        } else {
            turnLabel.setText("");
        }

    }

    /**
     * Handles the action when the roll button is clicked.
     * Updates the scene status, rolls the dice, resets the game text, and disables the roll button.
     * Sets the current player to the active player and disables the forgotten realm buttons.
     * If the active player is an AI player, disables the main board dice buttons.
     * Checks if there are no possible moves for the active player with the available dice.
     * If time warp is available, prompts the player to use time warp or skip and handles the action accordingly.
     * If time warp is not available, sets the rewards label to indicate that the turn is lost and manages the turn cycle.
     * Checks if time warp is available and handles the action accordingly if the active player is an AI player.
     * If time warp is not available, selects the dice for the active player if they are an AI player.
     */
    private void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void rollButtonClick() {
        playSound("src/main/resources/sound/Playing Dice 1.wav");
        updateSceneStatus();
        rollDice();
        gameText.setText("");
        disableRollButton();
        currentPlayer = activePlayer;
        disableForgottenRealmButtons();
        if (activePlayer.isAI()) {
            disableMainBoardDiceButtons();
        } else {
            enableMainBoardDiceButtons();
        }
        if (getPossibleMovesForDice(activePlayer, getAvailableDice()).length == 0) {
            System.out.println(activePlayer + ", NO Possible Moves: availableDice: " + Arrays.toString(getAvailableDice()) + "\n" + Arrays.toString(getPossibleMovesForDice(activePlayer, getAvailableDice())));
            if (activePlayer.isTimeWarpAvailable()) {
                gameText.setText("No available moves for current dice. Use time warp?");
                if (activePlayer.isAI()) {
                    boolean useTimeWarp = ((AIPlayer) activePlayer).useTimeWarp(getAvailableDice());
                    if (useTimeWarp) {
                        timeWarpButtonClick();
                    } else {
                        skipButtonClick();
                    }
                    return;
                } else {
                    enableTimeWarpButton();
                    enableSkipButton();
                }
            } else {
                activePlayer.getScoreSheetController().setRewardsLabel("No available moves for current dice. Turn Lost");
                manageTurnCycle();
            }
            return;
        }
        if (activePlayer.isTimeWarpAvailable()) {
            if (activePlayer.isAI()) {
                boolean useTimeWarp = ((AIPlayer) activePlayer).useTimeWarp(getAvailableDice());
                if (useTimeWarp) {
                    timeWarpButtonClick();
                } else {
                    ((AIPlayer) activePlayer).selectDice(filterDiceWithPossibleMoves(activePlayer, getAvailableDice()).toArray(Dice[]::new));
                }
            } else {
                enableTimeWarpButton();
            }
            return;
        }
        if (activePlayer.isAI()) {
            ((AIPlayer) activePlayer).selectDice(filterDiceWithPossibleMoves(activePlayer, getAvailableDice()).toArray(Dice[]::new));
        }
    }

    /**
     * Disables the skip button and other related buttons when skip button is clicked.
     * If the game status is in "Arcane Boost", sets the current player's arcane boost skipped to true.
     * Manages the turn cycle
     */
    @FXML
    public void skipButtonClick() {
        playSound("src/main/resources/sound/Negative click.wav");
        disableSkipButton();
        disableTimeWarpButton();
        disableArcaneBoostButton();
        if (gameStatus.getGameStatus() == CurrentStatus.ARCANE_BOOST) {
            currentPlayer.setArcaneBoostSkipped(true);
        }
        manageTurnCycle();
    }

    /**
     * This method is called after each active turn and at the end of the passive turn or arcane boost.
     * It only gets called when a phase is partially or completely finished.
     */
    //Gets called after each active turn and at the end of the passive turn or arcane boost
    //It only gets called when a phase is partially or completely finished
    public void manageTurnCycle() {
        if (arcanePrismEnabled) {
            arcanePrismEnabled = false;
            enableGrid(whiteDieParent);
            disableGrid(whiteDieDestination);
            refDiceArray = diceArray;
        }
        removeScoreSheetHighlight();
        updateSceneStatus();
        updateScoreSheets();
        gameText.setText("");
        if (gameStatus.getGameStatus() == CurrentStatus.ACTIVE_TURN) {
            handleActiveTurn();
            return;
        }
        if (gameStatus.getGameStatus() == CurrentStatus.PASSIVE_TURN) {
            endPassiveTurn();
        }
        if (gameStatus.getGameStatus() == CurrentStatus.ARCANE_BOOST) {
            handleArcaneBoost();
        }
    }

    /**
     * Handles the active turn in the game.
     */
    private void handleActiveTurn() {
        if (gameStatus.getTurn() < CLIGameController.MAX_NUMBER_OF_TURNS && containsAvailableDie()) {
            advanceActiveTurn();
        } else if (gameStatus.getTurn() == CLIGameController.MAX_NUMBER_OF_TURNS || !containsAvailableDie()) {
            endActiveTurn();
        }
    }

    /**
     * Advances the active turn in the game.
     * This method increments the turn count, updates the active player,
     * and performs necessary actions based on the player type.
     * If the active player is an AI player, it triggers the roll button click event.
     * If the active player is a human player, it enables the roll button.
     * Additionally, it disables the main board dice buttons and time warp button.
     * Finally, it updates the scene status.
     */
    private void advanceActiveTurn() {
        gameStatus.incrementTurn();
        currentPlayer = activePlayer;
        if (activePlayer.isAI()) {
            rollButtonClick();
        } else {
            enableRollButton();
        }
        disableMainBoardDiceButtons();
        disableTimeWarpButton();
        updateSceneStatus();

    }

    /**
     * Ends the active turn of the current player.
     * Resets the turn status, disables the time warp button,
     * moves the dice to the Forgotten Realm, updates the current player,
     * updates the game status to passive turn, and checks if there are any possible moves.
     * If there are no possible moves, ends the passive turn.
     * If the current player is an AI player, selects dice with possible moves and updates the scene status.
     * Otherwise, enables the Forgotten Realm buttons and updates the scene status.
     */
    private void endActiveTurn() {
        gameStatus.resetTurn();
        disableTimeWarpButton();
        moveDiceToForgottenRealm();
        currentPlayer = passivePlayer;
        turnLabel.setText("");
        //Move to next phase
        gameStatus.setGameStatus(CurrentStatus.PASSIVE_TURN);
        if (passivePlayer.isAI()) {
            startDelayedAction(() -> {
            }, smallDelay);
        }
        if (getPossibleMovesForDice(passivePlayer, getForgottenRealmDice()).length == 0) {
            passivePlayer.getScoreSheetController().setRewardsLabel("No possible moves, passive turn lost");
            updateSceneStatus();
            endPassiveTurn();
            return;
        } else {
            if (passivePlayer.isAI()) {
                disableForgottenRealmButtons();
                System.out.println("Forgotten Realm Dice: " + Arrays.toString(getForgottenRealmDice()));
                ((AIPlayer) passivePlayer).selectDice(filterDiceWithPossibleMoves(passivePlayer, getForgottenRealmDice()).toArray(Dice[]::new));
            } else {
                enableForgottenRealmButtons();
            }
        }
        updateSceneStatus();
    }

    /**
     * Ends the passive player's turn and begins the next active player's turn.
     * This method performs the following actions:
     * 1. Sets the arcane boost skipped flag for both players to false.
     * 2. Resets the arcane boost usage count for both players.
     * 3. Sets the game status to ARCANE_BOOST.
     * 4. Updates the scene status.
     * 5. Resets the dice.
     * 6. Disables the arcane boost button.
     * 7. Manages the turn cycle by switching active and passive players.
     */
    private void endPassiveTurn() {
        activePlayer.setArcaneBoostSkipped(false);
        passivePlayer.setArcaneBoostSkipped(false);
        activePlayer.resetArcaneBoostUsage();
        passivePlayer.resetArcaneBoostUsage();
        gameStatus.setGameStatus(CurrentStatus.ARCANE_BOOST);
        updateSceneStatus();
        resetDice();
        disableForgottenRealmButtons();
        disableArcaneBoostButton();
        manageTurnCycle();
    }

    /**
     * Handles the Arcane Boost for the active and passive player.
     * <p>
     * If the active player has the Arcane Boost available and has not skipped it, the current player is set to the active player and a game text is displayed asking if they want
     * to use Arcane Boost. If the active player is an AI player, the useArcaneBoost method is called on the AIPlayer object and if it returns true, the arcaneBoostButtonClick method
     * is called; otherwise, the skipButtonClick method is called. If the active player is not an AI player, the enableArcaneBoostButton and enableSkipButton methods are called.
     * <p>
     * If the passive player has the Arcane Boost available and has not skipped it, checks are made on the active player. If the active player has used the Arcane Boost previously
     * , the dice are reset and the Arcane Boost usage is reset. Then, the current player is set to the passive player and a game text is displayed asking if they want to use Arcane
     * Boost. If the passive player is an AI player, the useArcaneBoost method is called on the AIPlayer object with a filtered array of dice with possible moves from the active player
     * and if it returns true, the arcaneBoostButtonClick method is called and the main board dice buttons are disabled; otherwise, the skipButtonClick method is called. If the passive
     * player is not an AI player, the enableArcaneBoostButton and enableSkipButton methods are called.
     * <p>
     * If neither the active nor the passive player have the Arcane Boost available or have skipped it, the endPhase method is called.
     */
    private void handleArcaneBoost() {
        if (activePlayer.isArcaneBoostAvailable() && !activePlayer.isArcaneBoostSkipped() && filterDiceWithPossibleMoves(activePlayer, getAvailableDice()).toArray(Dice[]::new).length!=0) {
            currentPlayer = activePlayer;
            gameText.setText(activePlayer.getName() + ", do you want to use Arcane Boost?");
            if (activePlayer.isAI()) {
                if (((AIPlayer) activePlayer).useArcaneBoost(getAvailableDice())) {
                    arcaneBoostButtonClick();
                } else {
                    skipButtonClick();
                }
            } else {
                enableArcaneBoostButton();
                enableSkipButton();
            }
            updateSceneStatus();
            return;
        }
        if (passivePlayer.isArcaneBoostAvailable() && !passivePlayer.isArcaneBoostSkipped() && filterDiceWithPossibleMoves(passivePlayer, getAvailableDice()).toArray(Dice[]::new).length!=0) {
            if (activePlayer.isArcaneBoostUsed()) {
                resetDice();
                activePlayer.resetArcaneBoostUsage();
            }
            currentPlayer = passivePlayer;
            if (passivePlayer.isAI()) {
                if (((AIPlayer) passivePlayer).useArcaneBoost(filterDiceWithPossibleMoves(activePlayer, getAvailableDice()).toArray(Dice[]::new))) {
                    arcaneBoostButtonClick();
                    disableMainBoardDiceButtons();
                } else {
                    skipButtonClick();
                }
            } else {
                enableArcaneBoostButton();
                enableSkipButton();
            }
            gameText.setText(passivePlayer.getName() + ", do you want to use Arcane Boost?");
            updateSceneStatus();
            return;
        }
        endPhase();
    }

    /**
     * Ends the current phase of the game.
     * Performs various actions necessary to transition to the next phase.
     * Switches to the next player, updates game status, increments round if necessary,
     * ends the game if maximum number of rounds is reached, highlights the current round,
     * resets the dice, sets the current player, disables buttons for AI players,
     * performs reward for the active player, updates scene status and score sheets,
     * disables main board dice buttons for all players, and either initiates rolling for AI player or enables roll button for human player.
     */
    private void endPhase() {
        switchPlayer();
        gameText.setText("");
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);
        if (realRounds % 2 == 0) {
            gameStatus.incrementRound();
        }
        if (gameStatus.getRound() == MAX_NUMBER_OF_ROUNDS + 1) {
            endGame();
            return;
        }
        realRounds++;
        highlightCurrentRound();
        resetDice();
        currentPlayer = activePlayer;
        if (currentPlayer.isAI()) {
            System.out.println(currentPlayer.getName() + " is AI");
            disableAllButtons();
            startDelayedAction(() -> {
            }, smallDelay);
        } else {
            System.out.println(currentPlayer.getName() + " is not AI");
        }
        if (gameStatus.getRound() < 7) {
            performReward(activePlayer, roundRewards[gameStatus.getRound() - 1]);
        }
        updateSceneStatus();
        updateScoreSheets();
        disableMainBoardDiceButtons();
        if (activePlayer.isAI()) {
            rollButtonClick();
        } else {
            enableRollButton();
        }
    }

    /**
     * Executes the necessary actions when the timeWarpButton is clicked.
     * It activates the time warp power for the active player, disables the skip button,
     * updates the score sheets, and checks if the time warp power is still available.
     * If the time warp power is no longer available, it disables the time warp button.
     * Finally, it calls the rollButtonClick method to simulate the roll of the dice.
     */
    @FXML
    public void timeWarpButtonClick() {
        playSound("src/main/resources/sound/Playing Dice 1.wav");
        activePlayer.useTimeWarpPower();
        disableSkipButton();
        updateScoreSheets();
        if (!activePlayer.isTimeWarpAvailable()) {
            disableTimeWarpButton();
        }
        rollButtonClick();
    }

    /**
     * This method is called when the "Arcane Boost" button is clicked.
     * It performs the following operations:
     * 1. Enables the main board dice buttons.
     * 2. Disables the "Skip" button.
     * 3. Disables the "Time Warp" button.
     * 4. Uses the Arcane Boost power of the current player.
     * 5. Checks if the Arcane Boost is no longer available for the current player, and disables the "Arcane Boost" button if necessary.
     * 6. If the current player is an AI player, selects dice with possible moves and updates the selected dice.
     * 7. Updates the score sheets.
     */
    @FXML
    public void arcaneBoostButtonClick() {
        playSound("src/main/resources/sound/Epic_Button_Click_1.wav");
        enableMainBoardDiceButtons();
        disableSkipButton();
        disableTimeWarpButton();
        currentPlayer.useArcaneBoostPower();
        if (!currentPlayer.isArcaneBoostAvailable()) {
            disableArcaneBoostButton();
        }
        if (currentPlayer.isAI()) {
            ((AIPlayer) currentPlayer).selectDice(filterDiceWithPossibleMoves(currentPlayer, getAvailableDice()).toArray(Dice[]::new));
        }
        updateScoreSheets();
    }

    /**
     * Executes a specified action after a delay.
     *
     * @param action         the action to be executed
     * @param delayInSeconds the delay before executing the action, in seconds
     */
    private final long largeDelay = 2;
    private final long smallDelay = 1;
    private void startDelayedAction(Runnable action, long delayInSeconds) {
        //TODO: We need to add a delay here inorder to make the AI decisions visible for the other player
        //Encountered Errors: -showAndWait is not allowed during animation or layout processing
        // -Not on FX application thread; currentThread = ForkJoinPool.commonPool-worker-3
        action.run();
//        CompletableFuture.delayedExecutor(delayInSeconds, TimeUnit.SECONDS)
//                .execute(() -> Platform.runLater(action));

        // All the commented code didn't fit within our game structure



//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delayInSeconds), event -> action.run()));
//        timeline.play();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(action);
//            }
//        }, delayInSeconds * 1000);

//        PauseTransition delay = new PauseTransition(Duration.seconds(delayInSeconds));
//        delay.setOnFinished(event -> Platform.runLater(action));
//        delay.play();


//        PauseTransition delay = new PauseTransition(Duration.seconds(delayInSeconds));
//        delay.setOnFinished(event -> action.run());
//        delay.play();

//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        disableAllButtons();
//        disableForgottenRealmButtons();
//        disableMainBoardDiceButtons();
//        executorService.schedule(() -> Platform.runLater(action), delayInSeconds, TimeUnit.SECONDS);
//        executorService.shutdown();
    }

    /**
     * Method called when the red dice button is clicked.
     * Executes the necessary actions to make a move using the red dice.
     * If there are no possible moves for the red dice, throws an InvalidMoveException.
     * Disables the main board dice buttons and the forgotten realm buttons.
     * Selects the red dice if it is the same as the reference dice array.
     * If the current player is an AI, makes the move using the selected AI move and starts a delayed action to manage the turn cycle.
     * Sets the current player and possible moves in the RedRealmController.
     * Shows the RedRealmStage.
     * Finally, manages the turn cycle.
     *
     * @throws InvalidMoveException if there are no possible moves for the red dice
     */
    @FXML
    public void redDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[0]).length == 0) {
                throw new InvalidMoveException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();
            if (diceArray == refDiceArray) {
                selectDice(diceArray[0], currentPlayer);
            }
            if (currentPlayer.isAI()) {
                makeMove(currentPlayer, ((AIPlayer) currentPlayer).getSelectedMove());
                startDelayedAction(this::manageTurnCycle, largeDelay);
                return;
            }
            RedRealmController.setCurrentPlayer(currentPlayer);
            RedRealmController.setPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[0]));
            //This will be done by RedRealm Stage
            //makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[0])[0]);
            sceneManager.showRedRealmStage();
            manageTurnCycle();

        } catch (InvalidMoveException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + refDiceArray[0].getName());
        }

    }

    /**
     * This method is called when the green dice button is clicked.
     * It handles the logic for making moves with the green dice.
     * If there are no possible moves for the green dice, it throws an InvalidMoveException.
     * It then selects the green dice if it is the same as the reference dice array.
     * It makes a move with the green dice to the first possible move.
     * It disables the main board dice buttons and the forgotten realm buttons.
     * If the current player is an AI player, it prints a message stating that and starts a delayed action to manage the turn cycle.
     * If the current player is not an AI player, it prints a message stating that.
     * Finally, it manages the turn cycle.
     * If an InvalidMoveException is caught, it updates the game text to display an error message.
     */
    @FXML
    public void greenDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[1]).length == 0) {
                throw new InvalidMoveException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            if (diceArray == refDiceArray) {
                selectDice(diceArray[1], currentPlayer);
            }


            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[1])[0]);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();
            if (currentPlayer.isAI()) {
                System.out.println(currentPlayer.getName() + " is AI");
                startDelayedAction(this::manageTurnCycle, largeDelay);
                return;
            } else {
                System.out.println(currentPlayer.getName() + " is not AI");
            }
            manageTurnCycle();
        } catch (InvalidMoveException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + refDiceArray[1].getName());
        }
    }

    /**
     * Handles the event when the blue dice button is clicked.
     * Checks if there are possible moves for the blue dice. If not, throws an InvalidMoveException.
     * Selects the blue dice if it is currently the reference dice.
     * Makes the move based on the possible moves for the blue dice.
     * Disables the main board dice buttons and the forgotten realm buttons.
     * If the current player is AI, starts a delayed action to manage the turn cycle.
     * Otherwise, continues with managing the turn cycle.
     * Displays an error message if there are no possible moves for the blue dice.
     */
    @FXML
    public void blueDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[2]).length == 0) {
                throw new InvalidMoveException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            if (diceArray == refDiceArray) {
                selectDice(diceArray[2], currentPlayer);
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[2])[0]);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();

            if (currentPlayer.isAI()) {
                System.out.println(currentPlayer.getName() + " is AI");
                startDelayedAction(this::manageTurnCycle, largeDelay);
                return;
            } else {
                System.out.println(currentPlayer.getName() + " is not AI");
            }
            manageTurnCycle();
        } catch (InvalidMoveException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + refDiceArray[2].getName());
        }
    }

    /**
     * Handles the click event when the magenta dice button is clicked.
     * <p>
     * This method checks if there are possible moves for the magenta dice of the current player.
     * If there are no possible moves, an InvalidMoveException is thrown and an error message is displayed.
     * If there are possible moves, the dice is selected and a move is made using the first possible move.
     * The main board dice buttons and forgotten realm buttons are disabled.
     * <p>
     * If the current player is an AI player, a delayed action is started to manage the turn cycle after a large delay.
     * If the current player is not an AI player, the turn cycle is managed immediately.
     *
     * @throws InvalidMoveException if there are no possible moves for the magenta dice
     */
    @FXML
    public void magentaDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[3]).length == 0) {
                throw new InvalidMoveException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            if (diceArray == refDiceArray) {
                selectDice(diceArray[3], currentPlayer);
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[3])[0]);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();

            if (currentPlayer.isAI()) {
                System.out.println(currentPlayer.getName() + " is AI");
                startDelayedAction(this::manageTurnCycle, largeDelay);
                return;
            } else {
                System.out.println(currentPlayer.getName() + " is not AI");
            }
            manageTurnCycle();

        } catch (InvalidMoveException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + refDiceArray[3].getName());
        }
    }

    /**
     * Method that is called when the yellow dice button is clicked.
     * It handles the logic for the yellow dice button click event.
     * If there are no possible moves for the fourth dice in the reference dice array,
     * it throws an InvalidMoveException.
     * If the dice array is the reference dice array, it selects the fourth dice for the current player.
     * It then makes a move for the current player using the possible moves for the fourth dice.
     * After that, it disables the main board dice buttons and the forgotten realm buttons.
     * If the current player is an AI player, it starts a delayed action to manage the turn cycle after a large delay.
     * If the current player is not an AI player, it simply logs that the player is not an AI.
     * Finally, it manages the turn cycle.
     * If an InvalidMoveException is caught, it sets the game text to display an appropriate message.
     */
    @FXML
    public void yellowDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[4]).length == 0) {
                throw new InvalidMoveException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            if (diceArray == refDiceArray) {
                selectDice(diceArray[4], currentPlayer);
            }

            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[4])[0]);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();
            if (currentPlayer.isAI()) {
                System.out.println(currentPlayer.getName() + " is AI");
                startDelayedAction(this::manageTurnCycle, largeDelay);
                return;
            } else {
                System.out.println(currentPlayer.getName() + " is not AI");
            }
            manageTurnCycle();
        } catch (InvalidMoveException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + refDiceArray[4].getName());
        }

    }

    /**
     * This method is invoked when the white dice button is clicked. It handles the logic for selecting and moving the white dice.
     * It checks if there are any available moves for the white dice, throws a NoAvailableMovesException if there are none.
     * It selects the white dice and disables the grid it is in.
     * If the current player is an AI player, it prints a message indicating that the player is an AI.
     * Sets the arcanePrismEnabled flag to true.
     * Creates a reference dice array with dice objects of different colors and the same value as the selected white dice.
     * Determines the destination grid for the white dice based on the current grid it is in.
     * Enables the destination grid and updates the dice numbers displayed in the destination grid.
     * If the current player is an AI player, it selects the dice objects from the reference dice array that have possible moves,
     * disables and sets the opacity of the destination grid, and starts a delayed action.
     * If the current player is not an AI player, it enables the destination grid and sets its opacity.
     * Displays an error message if there are no available moves for the white dice.
     *
     * @throws NoAvailableMovesException if there are no available moves for the white dice
     */
    @FXML
    public void whiteDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, diceArray[5]).length == 0) {
                throw new NoAvailableMovesException();
            }
            playSound("src/main/resources/sound/DiceDrop_BW.48117.wav");
            whiteDieParent = (GridPane) whiteDice.getParent();
            selectDice(diceArray[5], currentPlayer);
            disableGrid(whiteDieParent);
            disableTimeWarpButton();
            Dice selectedDie = diceArray[5];
            if (currentPlayer.isAI()) {
                System.out.println(currentPlayer.getName() + " is AI");

            } else {
                System.out.println(currentPlayer.getName() + " is not AI");
            }
            arcanePrismEnabled = true;
            refDiceArray = new Dice[]{
                    new RedDice(selectedDie.getValue()),
                    //If casting it to green die value is not desired then change this to value of white die
                    new GreenDice(diceArray[1].getValue()),
                    new BlueDice(selectedDie.getValue()),
                    new MagentaDice(selectedDie.getValue()),
                    new YellowDice(selectedDie.getValue())
            };
            whiteDieDestination = (whiteDieParent == diceGrid) ? diceGridArcanePrism1 : diceGridArcanePrism2;
            enableGrid(whiteDieDestination);
            if (whiteDieDestination == diceGridArcanePrism1) {
                redDiceNumber1.setText(String.valueOf(selectedDie.getValue()));
                greenDiceNumber1.setText(String.valueOf(diceArray[1].getValue()));
                blueDiceNumber1.setText(String.valueOf(selectedDie.getValue()));
                magentaDiceNumber1.setText(String.valueOf(selectedDie.getValue()));
                yellowDiceNumber1.setText(String.valueOf(selectedDie.getValue()));
            } else {
                redDiceNumber2.setText(String.valueOf(selectedDie.getValue()));
                greenDiceNumber2.setText(String.valueOf(diceArray[1].getValue()));
                blueDiceNumber2.setText(String.valueOf(selectedDie.getValue()));
                magentaDiceNumber2.setText(String.valueOf(selectedDie.getValue()));
                yellowDiceNumber2.setText(String.valueOf(selectedDie.getValue()));
            }
            if (currentPlayer.isAI()) {
                System.out.println("AI pressed White Dice");
                ((AIPlayer) currentPlayer).selectDice(filterDiceWithPossibleMoves(currentPlayer, refDiceArray).toArray(Dice[]::new));
                whiteDieDestination.setDisable(true);
                whiteDieDestination.setOpacity(lowOpacity);
                startDelayedAction(() -> {
                }, smallDelay);
            } else {
                whiteDieDestination.setDisable(false);
                whiteDieDestination.setOpacity(highOpacity);
            }
        } catch (NoAvailableMovesException e) {
            playSound("src/main/resources/sound/Ancient Game UI Click.wav");
            gameText.setText("There are no possible moves for " + diceArray[5].getName());
        }
    }

    /**
     * Disables the given grid by disabling its parent StackPane and making it invisible and mouse transparent.
     *
     * @param grid The grid to be disabled.
     */
    private void disableGrid(GridPane grid) {
        StackPane stackPane = (StackPane) grid.getParent();
        stackPane.setDisable(true);
        stackPane.setVisible(false);
        stackPane.setMouseTransparent(true);

    }

    /**
     * Enables the given GridPane and its parent StackPane to make them visible and interactive.
     *
     * @param grid The GridPane to enable.
     */
    private void enableGrid(GridPane grid) {
        StackPane stackPane = (StackPane) grid.getParent();
        stackPane.setDisable(false);
        stackPane.setVisible(true);
        stackPane.setMouseTransparent(false);

    }

    /**
     * Starts the game by performing necessary actions such as disabling main board dice buttons,
     * updating score sheets, updating scene status, setting the current player, setting the game status,
     * loading the Guider.fxml file, and performing additional actions for an AI player.
     */
    @Override
    public void startGame() {
        disableMainBoardDiceButtons();
        updateScoreSheets();
        updateSceneStatus();
        currentPlayer = activePlayer;
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);
        refDiceArray = diceArray;
        try {
            FXMLLoader guiderLoader = new FXMLLoader(getClass().getResource("Guider.fxml"));
            AnchorPane guiderAnchorPane = guiderLoader.load();
            Guider guiderController = guiderLoader.getController();
            guiderController.setGuiGameController(this);
            this.guiderAnchorPane.getChildren().add(guiderAnchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Makes a move for a given player.
     *
     * @param player the player making the move
     * @param move   the move to be made
     * @return true if the move was successfully made, false otherwise
     */
    @Override
    public boolean makeMove(Player player, Move move) {
        player.getScoreSheetController().setRewardsLabel("");
        try {
            if (move.getDice().getRealm() == GameColor.WHITE) {
                return false;
            }

            Realm realm = player.getRealm(move.getDice());
            realm.attack(move);
            performAntiCheatServiceChecks(player);
            if (realm.checkReward()) {
                processRewardQueue(player, realm.getReward());
            }
            if (player.isAI()) {
                ((AIPlayer) player).getMoveEvaluation().addMove(move);
            }
            updateScoreSheets();
            updateSceneStatus();
            sceneManager.updateEndGameStage();
            return true;


        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //------------------------------------------------------REWARD METHODS-------------------------------------------------------//

    /**
     * Process the reward queue for a given player.
     *
     * @param player  the player who will receive the rewards
     * @param rewards an array of Collectibles representing the rewards to be processed
     */
    private void processRewardQueue(Player player, Collectibles[] rewards) {
        LinkedList<Collectibles> list = new LinkedList<>();
        for (Collectibles r : rewards) {
            if (r != null) {
                list.add(r);
            }
        }
        PriorityQueue<Collectibles> priorityQueue = new PriorityQueue<>(new CollectiblesComparator());
        priorityQueue.addAll(list);
        while (!priorityQueue.isEmpty()) {
            performReward(player, priorityQueue.remove());
        }
    }

    /**
     * Performs the reward action for the given player and collectible reward.
     * If the reward is null, the method returns without performing any action.
     *
     * @param player The player who will receive the reward.
     * @param reward The collectible reward to be given to the player.
     */
    protected void performReward(Player player, Collectibles reward) {
        if (reward == null) {
            return;
        }
        player.getScoreSheetController().setRewardsLabel((player.getName() + ", you received " + reward.getName() + "!"));
        updateScoreSheets();


        if (reward instanceof EssenceBonus) {
            playEssenceBonus(player);
        } else {
            if (reward instanceof ColorBonus) {
                playColorBonus(player, ((ColorBonus) reward).getColor());
            } else {
                player.receiveCollectible(reward);
            }
        }

    }

    /**
     * Plays the color bonus for a player based on the given game color.
     * If the player is an AI, the appropriate AI algorithm is executed, otherwise the UI is updated.
     *
     * @param player    the player who is playing the color bonus
     * @param gameColor the game color for which the bonus is being played
     * @throws IllegalArgumentException if an invalid game color is provided
     */
    protected void playColorBonus(Player player, GameColor gameColor) {
        try {
            Move[] possibleMoves = player.getRealm(gameColor).getRealmMoves();
            if (possibleMoves.length == 0) {
                throw new NoAvailableMovesException();
            }

            switch (gameColor) {
                case RED:
                    if (player.isAI()) {
                        System.out.println(player + " is AI");
                        ((AIPlayer) (player)).playColorBonus(GameColor.RED, player.getRealm(GameColor.RED).getRealmMoves());
                    } else {
                        RedRealmController.setPossibleMoves(possibleMoves);
                        RedRealmController.setCurrentPlayer(player);
                        sceneManager.showRedRealmStage();
                    }
                    break;
                case GREEN:
                    if (player.isAI()) {
                        System.out.println(player + " is AI");
                        ((AIPlayer) (player)).playColorBonus(GameColor.GREEN, player.getRealm(GameColor.GREEN).getRealmMoves());
                    } else {
                        GreenBonusController.setPossibleMoves(possibleMoves);
                        GreenBonusController.setCurrentPlayer(player);
                        sceneManager.showGreenRealmStage();
                    }

                    break;
                case BLUE:
                    if (player.isAI()) {
                        System.out.println(player + " is AI");
                        System.out.println(player + " received Blue Bonus");
                        BlueDice blueDice = new BlueDice(6);
                        makeMove(player, new Move(blueDice, player.getRealm(blueDice).getCreature(blueDice)));
                    } else {
                        BlueBonusController.setPossibleMove(possibleMoves[possibleMoves.length - 1]);
                        BlueBonusController.setCurrentPlayer(player);
                        sceneManager.showBlueRealmStage();
                    }
                    break;
                case MAGENTA:
                    if (player.isAI()) {
                        System.out.println(player + " is AI");
                        System.out.println(player + " received Magenta Bonus");
                        makeMove(player, new Move(new MagentaDice(6), player.getRealm(gameColor).getCreature(new MagentaDice(6))));
                    } else {
                        MagentaBonusController.setCurrentPlayer(player);
                        MagentaBonusController.setPossibleMove(new Move(new MagentaDice(6), player.getRealm(gameColor).getCreature(new MagentaDice(6))));
                        sceneManager.showMagentaRealmStage();
                    }
                    break;
                case YELLOW:
                    if (player.isAI()) {
                        System.out.println(player + " is AI");
                        System.out.println(player + " received Yellow Bonus");
                        makeMove(player, new Move(new YellowDice(6), player.getRealm(gameColor).getCreature(new YellowDice(6))));
                    } else {
                        YellowBonusController.setCurrentPlayer(player);
                        YellowBonusController.setPossibleMove(new Move(new YellowDice(6), player.getRealm(gameColor).getCreature(new YellowDice(6))));
                        sceneManager.showYellowRealmStage();
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid color bonus: " + gameColor);
            }
        } catch (NoAvailableMovesException e) {
            player.getScoreSheetController().setRewardsLabel("Ohh bad luck...no possible moves, bonus lost!");
        }
    }

    /**
     * Plays the essence bonus for a given player. The essence bonus allows the player to choose a realm to gain a color bonus.
     *
     * @param player the player for whom the essence bonus is being played
     */
    protected void playEssenceBonus(Player player) {
        Realm[] realms = player.getRealms();
        LinkedList<GameColor> availableRealms = Stream.of(realms)
                .filter(Realm::isRealmAvailable).map(Realm::getColor)
                .collect(Collectors.toCollection(LinkedList::new));

        if (!availableRealms.isEmpty()) {
            if (player.isAI()) {
                playColorBonus(player, ((AIPlayer) player).selectRealm(availableRealms));
            } else {
                RealmPickerController.setPossibleRealms(availableRealms);
                RealmPickerController.setCurrentPlayer(player);
                sceneManager.showRealmPickerStage();
            }

        }

    }

    //------------------------------------------------------SECONDARY METHODS-------------------------------------------------------//


    /**
     * Checks if any dice in the diceArray has the status DiceStatus.AVAILABLE.
     *
     * @return true if there is at least one dice with status DiceStatus.AVAILABLE, false otherwise.
     */
    private boolean containsAvailableDie() {
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all possible moves for a given player.
     *
     * @param player the player for whom to retrieve possible moves
     * @return an array of possible moves for the player
     */
    @Override
    public Move[] getAllPossibleMoves(Player player) {
        if (player == null) {
            System.err.println("Player cannot be null.");
            return new Move[0];
        }
        LinkedList<Move> list = new LinkedList<>();
        for (Realm i : player.getRealms()) {
            Move[] moves = i.getRealmMoves();
            for (Move m : moves) {
                list.addLast(m);
            }
        }
        Move[] result = new Move[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    /**
     * Returns an array of possible moves based on the available dice for a given player.
     *
     * @param player The player for which to find the possible moves.
     * @return An array of possible moves for the given player and available dice.
     */
    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return getPossibleMovesForDice(player, getAvailableDice());
    }

    /**
     * Returns an array of possible moves for the given player and dice.
     *
     * @param player the player who wants to make the moves
     * @param dice   the dice values available for the player to use
     * @return an array of possible moves for the player and dice combination
     */
    private Move[] getPossibleMovesForDice(Player player, Dice[] dice) {
        try {
            LinkedList<Move> availableMoves = new LinkedList<>();
            for (Dice i : dice) {
                Move[] moves = getPossibleMovesForADie(player, i);
                for (Move m : moves) {
                    //To not include duplicated moves if white die has same value of any other die in the given dice array
                    //And to not duplicate moves for green realm
                    if (!availableMoves.contains(m)) {
                        availableMoves.addLast(m);
                    }
                }
            }
            return availableMoves.toArray(Move[]::new);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        }
        return new Move[0];
    }

    /**
     * Returns an array of all possible moves that a player can make for a given die.
     *
     * @param player The player making the move
     * @param dice   The dice for which we want to find the possible moves
     * @return An array of possible moves for the given die
     */
    @Override
    public Move[] getPossibleMovesForADie(Player player, Dice dice) {
        try {
            LinkedList<Move> possibleMoves = new LinkedList<>();
            int diceValue = dice.getValue();
            //We could have made it recursive but refused to do so for code simplicity
            // If the dice is white, iterate over all realms
            if (dice.getRealm() == GameColor.WHITE) {
                for (Realm realm : player.getRealms()) {
                    Move[] realmMoves = realm.getRealmMoves();
                    for (Move move : realmMoves) {
                        int targetValue = (realm instanceof GreenRealm) ? diceValue + diceArray[1].getValue() : diceValue;
                        if (move.getDice().getValue() == targetValue) {
                            possibleMoves.add(move);
                        }
                    }
                }
            } else {
                // If the dice is not white, find moves in the respective realm
                Realm realm = player.getRealm(dice);
                if (!realm.isRealmAvailable()) {
                    return new Move[0];
                }
                Move[] realmMoves = realm.getRealmMoves();
                for (Move move : realmMoves) {
                    int targetValue = (realm instanceof GreenRealm) ? diceValue + diceArray[5].getValue() : diceValue;
                    if ((realm instanceof RedRealm) && ((RedDice) dice).getDragonNumber() != 0) {
                        if (move.getDice().getValue() == diceValue && ((Dragon) move.getCreature()).getDragonNumber() == ((RedDice) dice).getDragonNumber()) {
                            possibleMoves.add(move);
                        }
                    } else if (move.getDice().getValue() == targetValue) {
                        possibleMoves.add(move);
                    }
                }
            }
            return possibleMoves.toArray(new Move[0]);
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return new Move[0];
        }

    }

    /**
     * Closes the game scanner, displays the score sheets of active and passive players,
     * prints the maximum number of initialized worlds constant, and shows the end game scene.
     */
    protected void endGame() {
        gameGuide.closeScanner();
        sc.close();
        activePlayer.getScoreSheet().displayScoreSheet();
        passivePlayer.getScoreSheet().displayScoreSheet();
        System.out.println(MoveEvaluation.MAX_NO_WORLDS_INITIALIZED);
        sceneManager.showEndGame();
    }
}
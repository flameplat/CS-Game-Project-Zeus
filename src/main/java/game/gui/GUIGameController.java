package game.gui;

import game.collectibles.Collectibles;
import game.collectibles.ColorBonus;
import game.collectibles.EssenceBonus;
import game.creatures.Dragon;
import game.creatures.Lion;
import game.dice.*;
import game.engine.*;
import game.exceptions.InvalidMoveException;
import game.exceptions.NoAvailableMovesException;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.CollectiblesComparator;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GUIGameController extends CLIGameController implements Initializable,GameController {
    private final Image transparentImage;
    private final double lowOpacity = 0.5;
    private final double highOpacity = 1;
    Map<Player, CompositeScoreSheetController> playerScoreSheet;
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

    //This player points to the current player whether passive or active or arcaneBoost enabled
    private Player currentPlayer;
    //------------------------------------------------------BUTTONS FUNCTIONS-------------------------------------------------------//
    private int realRounds = 1;
    private boolean arcanePrismEnabled;
    private GridPane whiteDieParent;
    private GridPane whiteDieDestination;

    public GUIGameController() {
        super();
        WritableImage transparentImage = new WritableImage(1, 1);
        PixelWriter pixelWriter = transparentImage.getPixelWriter();
        pixelWriter.setColor(0, 0, Color.rgb(0, 0, 0, 0));
        this.transparentImage = transparentImage;
        playerScoreSheet = new HashMap<>();
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);

    }

    //------------------------------------------------------ONE TIME METHODS-------------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        round1Reward.setImage(getRewardIcon(0, roundRewards));
        round2Reward.setImage(getRewardIcon(1, roundRewards));
        round3Reward.setImage(getRewardIcon(2, roundRewards));
        round4Reward.setImage(getRewardIcon(3, roundRewards));
        round5Reward.setImage(getRewardIcon(4, roundRewards));
        round6Reward.setImage(getRewardIcon(5, roundRewards));
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


    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public void setPlayer1(Player player) {
        gameBoard.setPlayer1(player);
        activePlayer = player;
        player.setPlayerStatus(PlayerStatus.ACTIVE);
        currentPlayer = activePlayer;
        updateSceneStatus();

    }

    public void setPlayer2(Player player) {
        gameBoard.setPlayer2(player);
        passivePlayer = player;
        player.setPlayerStatus(PlayerStatus.PASSIVE);
    }

    //------------------------------------------------------DICE RELATED METHODS & UPDATERS-------------------------------------------------------//

    public void setPlayer1ScoreSheet(CompositeScoreSheetController scoreSheet) {
        activePlayer.setGUIScoreSheet(scoreSheet);
    }

    public void setPlayer2ScoreSheet(CompositeScoreSheetController scoreSheet) {
        passivePlayer.setGUIScoreSheet(scoreSheet);
    }

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

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    private void moveDice(StackPane dice, GridPane grid) {
        GridPane parent = (GridPane) dice.getParent();

        Integer rowIndex = GridPane.getRowIndex(dice);
        Integer columnIndex = GridPane.getColumnIndex(dice);

        if (rowIndex == null) rowIndex = 0;
        if (columnIndex == null) columnIndex = 0;

        parent.getChildren().remove(dice);
        grid.add(dice, columnIndex, rowIndex);
    }

    @FXML
    public Dice[] rollDice() {
        //Rolling only rolls available dice
        Random random = new Random();
        int diceValue;
        //Dice values are from 1 to 6
        int diceMaxBound = 6;
        int diceMinBound = 1;
        for (Dice dice : diceArray) {
            if (dice != null && dice.getDiceStatus() == DiceStatus.AVAILABLE) {
                diceValue = random.nextInt(diceMaxBound - diceMinBound + 1) + diceMinBound;
                dice.setValue(diceValue);
            }
        }
        updateDiceValues();
        return diceArray;
    }

    //Returns all dice to main deck and sets them to available and disable their buttons
    private void resetDice() {
        for (int i = 0; i < diceArray.length; i++) {
            diceArray[i].setDiceStatus(DiceStatus.AVAILABLE);
            moveDice(diceGUI[i], diceGrid);
            disableMainBoardDiceButtons();
            disableForgottenRealmButtons();
        }
    }

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

    //------------------------------------------------------BUTTONS AVAILABILITY METHODS-------------------------------------------------------//
    private void disableArcaneBoostButton() {
        arcaneBoostStackPane.setDisable(true);
        arcaneBoostStackPane.setOpacity(lowOpacity);
    }

    private void disableTimeWarpButton() {
        timeWarpStackPane.setDisable(true);
        timeWarpStackPane.setOpacity(lowOpacity);
    }

    private void disableSkipButton() {
        skipButtonStackPane.setDisable(true);
        skipButtonStackPane.setOpacity(lowOpacity);
    }

    private void enableArcaneBoostButton() {
        arcaneBoostStackPane.setDisable(false);
        arcaneBoostStackPane.setOpacity(highOpacity);
    }

    private void enableTimeWarpButton() {
        timeWarpStackPane.setDisable(false);
        timeWarpStackPane.setOpacity(highOpacity);
    }

    private void enableSkipButton() {
        skipButtonStackPane.setDisable(false);
        skipButtonStackPane.setOpacity(highOpacity);
    }

    private void disableForgottenRealmButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == forgottenRealmGrid) {
                die.setDisable(true);
                die.setOpacity(lowOpacity);
            }
        }
    }

    private void enableForgottenRealmButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == forgottenRealmGrid) {
                die.setDisable(false);
                die.setOpacity(highOpacity);
            }
        }
    }

    private void disableMainBoardDiceButtons() {
        for (StackPane die : diceGUI) {
            if (die.getParent() == diceGrid) {
                die.setDisable(true);
                die.setOpacity(lowOpacity);
            }
        }
    }

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

    private void disableRollButton() {
        rollButtonStackPane.setDisable(true);
        rollButtonStackPane.setOpacity(lowOpacity);
    }

    private void enableRollButton() {
        rollButtonStackPane.setDisable(false);
        rollButtonStackPane.setOpacity(highOpacity);
    }

    private void disableAllButtons() {
        disableRollButton();
        disableMainBoardDiceButtons();
        disableArcaneBoostButton();
        disableTimeWarpButton();
        disableSkipButton();
        disableForgottenRealmButtons();
    }

    //------------------------------------------------------HOVER METHODS-------------------------------------------------------//
    @FXML
    public void redDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[0]));
    }

    @FXML
    public void greenDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[1]));
    }

    @FXML
    public void blueDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[2]));
    }

    @FXML
    public void magentaDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[3]));
    }

    @FXML
    public void yellowDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[4]));
    }

    @FXML
    public void whiteDiceButtonHoverOn() {
        highlightPossibleMoves(getPossibleMovesForADie(currentPlayer, diceArray[5]));
    }

    public void diceButtonHoverOff() {
        removeScoreSheetHighlight();
    }

    public void highlightPossibleMoves(Move[] moves) {
        currentPlayer.getScoreSheetController().highlightPossibleMoves(moves);
    }

    @FXML
    public void removeScoreSheetHighlight() {
        currentPlayer.getScoreSheetController().removeHighlight();
    }

    //------------------------------------------------------UPDATERS-------------------------------------------------------//
    private void highlightCurrentRound() {
        removeRoundTableHighlight();
        int round = gameStatus.getRound();
        highlightCell(round, "LawnGreen");
    }

    private void removeRoundTableHighlight() {
        for (int i = 0; i < roundsTable.getColumnCount(); i++) {
            highlightCell(i, null);
        }
    }

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

    private void updateScoreSheets() {
        activePlayer.getScoreSheetController().updateScoreSheet();
        passivePlayer.getScoreSheetController().updateScoreSheet();
    }

    private void updateDiceValues() {
        redDiceNumber.setText(String.valueOf(diceArray[0].getValue()));
        greenDiceNumber.setText(String.valueOf(diceArray[1].getValue()));
        blueDiceNumber.setText(String.valueOf(diceArray[2].getValue()));
        magentaDiceNumber.setText(String.valueOf(diceArray[3].getValue()));
        yellowDiceNumber.setText(String.valueOf(diceArray[4].getValue()));
        whiteDiceNumber.setText(String.valueOf(diceArray[5].getValue()));
    }

    private void updateSceneStatus() {
        currentPlayerLabel.setText(currentPlayer.getName());
        currentGameStatusLabel.setText(gameStatus.getGameStatus().toString());
        if (gameStatus.getGameStatus() == CurrentStatus.ACTIVE_TURN) {
            turnLabel.setText("Turn " + gameStatus.getTurn());
        } else {
            turnLabel.setText("");
        }

    }

    @FXML
    public void rollButtonClick() {
        rollDice();
        gameText.setText("");
        disableRollButton();
        disableForgottenRealmButtons();
        enableMainBoardDiceButtons();
        if (getPossibleMovesForDice(activePlayer, getAvailableDice()).length == 0) {
            if (activePlayer.isTimeWarpAvailable()) {
                enableTimeWarpButton();
                gameText.setText("No available moves for current dice. Use time warp?");
                enableSkipButton();
            } else {
                gameText.setText("No available moves for current dice. Turn Lost");
                manageTurnCycle();
            }
        }
        if (activePlayer.isTimeWarpAvailable()) {
            enableTimeWarpButton();
        }
    }
    @FXML
    public void skipButtonClick() {
        disableSkipButton();
        disableTimeWarpButton();
        disableArcaneBoostButton();
        if (gameStatus.getGameStatus() == CurrentStatus.ARCANE_BOOST) {
            currentPlayer.setArcaneBoostSkipped(true);
        }
        manageTurnCycle();
    }

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

    private void handleActiveTurn() {
        if (gameStatus.getTurn() < 3 && containsAvailableDie()) {
            advanceActiveTurn();
        } else if (gameStatus.getTurn() == 3 || !containsAvailableDie()) {
            endActiveTurn();
        }
    }

    private void advanceActiveTurn() {
        gameStatus.incrementTurn();
        currentPlayer = activePlayer;
        enableRollButton();
        disableMainBoardDiceButtons();
        disableTimeWarpButton();
        updateSceneStatus();
    }

    private void endActiveTurn() {
        gameStatus.resetTurn();
        disableTimeWarpButton();
        moveDiceToForgottenRealm();
        enableForgottenRealmButtons();
        currentPlayer = passivePlayer;
        turnLabel.setText("");
        //Move to next phase
        gameStatus.setGameStatus(CurrentStatus.PASSIVE_TURN);
        if (getPossibleMovesForDice(passivePlayer, getForgottenRealmDice()).length == 0) {
            gameText.setText("No possible moves, passive turn lost");
            endPassiveTurn();
        }
        updateSceneStatus();
    }

    private void endPassiveTurn() {
        activePlayer.setArcaneBoostSkipped(false);
        passivePlayer.setArcaneBoostSkipped(false);
        activePlayer.resetArcaneBoostUsage();
        passivePlayer.resetArcaneBoostUsage();
        gameStatus.setGameStatus(CurrentStatus.ARCANE_BOOST);
        updateSceneStatus();
        resetDice();
    }

    private void handleArcaneBoost() {
        if (activePlayer.isArcaneBoostAvailable() && !activePlayer.isArcaneBoostSkipped()) {
            currentPlayer = activePlayer;
            enableArcaneBoostButton();
            enableSkipButton();
            gameText.setText(activePlayer.getName() + ", do you want to use Arcane Boost?");
            updateSceneStatus();
            return;
        }
        if (passivePlayer.isArcaneBoostAvailable() && !passivePlayer.isArcaneBoostSkipped()) {
            if (activePlayer.isArcaneBoostUsed()) {
                resetDice();
                activePlayer.resetArcaneBoostUsage();
            }
            currentPlayer = passivePlayer;
            enableArcaneBoostButton();
            enableSkipButton();
            gameText.setText(passivePlayer.getName() + ", do you want to use Arcane Boost?");
            updateSceneStatus();
            return;
        }
        endPhase();
    }

    private void endPhase() {
        switchPlayer();
        gameText.setText("");
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);
        if (realRounds % 2 == 0) {
            gameStatus.incrementRound();
        }
        if (gameStatus.getRound() == 7) {
            endGame();
        }
        realRounds++;
        highlightCurrentRound();
        resetDice();
        currentPlayer = activePlayer;
        performReward(activePlayer, roundRewards[gameStatus.getRound() - 1]);
        updateSceneStatus();
        updateScoreSheets();
        enableRollButton();
        disableMainBoardDiceButtons();
    }

    @FXML
    public void timeWarpButtonClick() {
        activePlayer.useTimeWarpPower();
        disableSkipButton();
        if (!activePlayer.isTimeWarpAvailable()) {
            disableTimeWarpButton();
        }
        rollButtonClick();
        updateScoreSheets();
    }

    @FXML
    public void arcaneBoostButtonClick() {
        enableMainBoardDiceButtons();
        disableSkipButton();
        disableTimeWarpButton();
        currentPlayer.useArcaneBoostPower();
        if (!currentPlayer.isArcaneBoostAvailable()) {
            disableArcaneBoostButton();
        }
        updateScoreSheets();
    }

    @FXML
    public void redDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[0]).length == 0) {
                throw new InvalidMoveException();
            }

            selectDice(diceArray[0], currentPlayer);
            RedRealmController.setCurrentPlayer(currentPlayer);
            RedRealmController.setPossibleMoves(getPossibleMovesForADie(currentPlayer, refDiceArray[0]));
            //This will be done by RedRealm Stage
            //makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[0])[0]);
            sceneManager.showRedRealmStage();
            manageTurnCycle();
        } catch (InvalidMoveException e) {
            gameText.setText("There are no possible moves for " + refDiceArray[0].getName());
        }

    }

    @FXML
    public void greenDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[1]).length == 0) {
                throw new InvalidMoveException();
            }
            selectDice(diceArray[1], currentPlayer);
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[1])[0]);
            manageTurnCycle();
        } catch (InvalidMoveException e) {
            gameText.setText("There are no possible moves for " + refDiceArray[1].getName());
        }
    }

    @FXML
    public void blueDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[2]).length == 0) {
                throw new InvalidMoveException();
            }
            selectDice(diceArray[2], currentPlayer);
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[2])[0]);

            manageTurnCycle();
        } catch (InvalidMoveException e) {
            gameText.setText("There are no possible moves for " + refDiceArray[2].getName());
        }
    }

    @FXML
    public void magentaDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[3]).length == 0) {
                throw new InvalidMoveException();
            }
            selectDice(diceArray[3], currentPlayer);
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[3])[0]);
            manageTurnCycle();

        } catch (InvalidMoveException e) {
            gameText.setText("There are no possible moves for " + refDiceArray[3].getName());
        }
    }

    @FXML
    public void yellowDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, refDiceArray[4]).length == 0) {
                throw new InvalidMoveException();
            }
            selectDice(diceArray[4], currentPlayer);
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, refDiceArray[4])[0]);

            manageTurnCycle();
        } catch (InvalidMoveException e) {
            gameText.setText("There are no possible moves for " + refDiceArray[4].getName());
        }

    }


    @FXML
    public void whiteDiceButtonClick() {
        try {
            if (getPossibleMovesForADie(currentPlayer, diceArray[5]).length == 0) {
                throw new NoAvailableMovesException();
            }
            whiteDieParent = (GridPane) whiteDice.getParent();
            selectDice(diceArray[5], currentPlayer);
            disableGrid(whiteDieParent);
            Dice selectedDie = diceArray[5];
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
        } catch (NoAvailableMovesException e) {
            gameText.setText("There are no possible moves for " + diceArray[5].getName());
        }
    }

    private void disableGrid(GridPane grid) {
        StackPane stackPane = (StackPane) grid.getParent();
        stackPane.setDisable(true);
        stackPane.setVisible(false);
        stackPane.setMouseTransparent(true);

    }

    private void enableGrid(GridPane grid) {
        StackPane stackPane = (StackPane) grid.getParent();
        stackPane.setDisable(false);
        stackPane.setVisible(true);
        stackPane.setMouseTransparent(false);

    }

    //------------------------------------------------------GAME LOGIC METHODS-------------------------------------------------------//
    @Override
    public void startGame() {
        performReward(activePlayer, roundRewards[0]);
        enableRollButton();
        disableMainBoardDiceButtons();
        updateScoreSheets();
        updateSceneStatus();
        highlightCurrentRound();
        currentPlayer = activePlayer;
        gameStatus.setGameStatus(CurrentStatus.ACTIVE_TURN);
        refDiceArray = diceArray;
//
    }

    @Override
    public boolean makeMove(Player player, Move move) {
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
            updateScoreSheets();
            return true;

        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    //------------------------------------------------------REWARD METHODS-------------------------------------------------------//

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

    protected void performReward(Player player, Collectibles reward) {
        if (reward == null) {
            return;
        }
        gameText.setText(player.getName() + ", you received " + reward.getName() + "!");
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

    protected void playColorBonus(Player player, GameColor gameColor) {
        try {
            Move[] possibleMoves = player.getRealm(gameColor).getRealmMoves();
            if (possibleMoves.length == 0) {
                throw new NoAvailableMovesException();
            }

            switch (gameColor) {
                case RED:
                    RedRealmController.setPossibleMoves(possibleMoves);
                    RedRealmController.setCurrentPlayer(player);
                    sceneManager.showRedRealmStage();
                    break;
                case GREEN:
                    GreenBonusController.setPossibleMoves(possibleMoves);
                    GreenBonusController.setCurrentPlayer(player);
                    sceneManager.showGreenRealmStage();
                    break;
                case BLUE:
                    BlueBonusController.setPossibleMove(possibleMoves[possibleMoves.length - 1]);
                    BlueBonusController.setCurrentPlayer(player);
                    sceneManager.showBlueRealmStage();
                    break;
                case MAGENTA:
                    MagentaBonusController.setCurrentPlayer(player);
                    MagentaBonusController.setPossibleMove(new Move(new MagentaDice(6), player.getRealm(gameColor).getCreature(new MagentaDice(6))));
                    sceneManager.showMagentaRealmStage();
                    break;
                case YELLOW:
                    YellowBonusController.setCurrentPlayer(player);
                    YellowBonusController.setPossibleMove(new Move(new YellowDice(6), player.getRealm(gameColor).getCreature(new YellowDice(6))));
                    sceneManager.showYellowRealmStage();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid color bonus: " + gameColor);
            }
        } catch (NoAvailableMovesException e) {
            gameText.setText("Ohh bad luck...no possible moves, bonus lost!");
        }
    }

    protected void playEssenceBonus(Player player) {
        Realm[] realms = player.getRealms();
        LinkedList<GameColor> availableRealms = Stream.of(realms)
                .filter(Realm::isRealmAvailable).map(Realm::getColor)
                .collect(Collectors.toCollection(LinkedList::new));
        if(!availableRealms.isEmpty()){
            RealmPickerController.setPossibleRealms(availableRealms);
            RealmPickerController.setCurrentPlayer(player);
            sceneManager.showRealmPickerStage();
        }
    }

    //------------------------------------------------------SECONDARY METHODS-------------------------------------------------------//


    private boolean containsAvailableDie() {
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all six dice, providing their current state and value within the
     * game regardless of their location or status. The dice could be in various
     * states, such as currently rolled and awaiting selection by the active player,
     * in the Forgotten Realm awaiting selection by the passive player, or already
     * assigned to a specific turn by the active player.
     *
     * @return An array of all six {@code Dice}, with each die's state and value.
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
     * Gets possible moves for all currently rolled dice for a given player.
     *
     * @param player The player for whom to determine possible moves.
     * @return An array of all possible moves for all rolled dice.
     */
    @Override
    public Move[] getPossibleMovesForAvailableDice(Player player) {
        return getPossibleMovesForDice(player, getAvailableDice());
    }

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

    protected void endGame() {
        gameGuide.closeScanner();
        sc.close();
        System.out.println(activePlayer.getName());
        activePlayer.getScoreSheet().displayScoreSheet();
        System.out.println("*".repeat(100));
        System.out.println(passivePlayer.getName());
        passivePlayer.getScoreSheet().displayScoreSheet();
        System.out.println("*".repeat(100));
        System.out.println(activePlayer.getGameScore());
        System.out.println(passivePlayer.getGameScore());
        int diff = activePlayer.getGameScore().getTotalScore() - passivePlayer.getGameScore().getTotalScore();
        if (diff == 0) {
            System.out.println("Draw!");
        } else {
            if (diff > 0) {
                //Active player is the winner
                System.out.println(activePlayer + " is the winner!");
                gameStatus.setGameStatus(CurrentStatus.PLAYER_1_WINS);

            } else {
                System.out.println(passivePlayer + " is the winner!");
                gameStatus.setGameStatus(CurrentStatus.PLAYER_2_WINS);

            }
        }

        //Compares GameScore of each player and declares winner
        System.out.println("Game developed by Team: ");
        System.out.println("  ZZZZ  EEEEE  U   U  SSSS");
        System.out.println("     Z  E      U   U  S    ");
        System.out.println("    Z   EEEE   U   U   SSS ");
        System.out.println("   Z    E      U   U      S");
        System.out.println("  ZZZZ  EEEEE   UUU   SSSS");
        systemManager.exit();
    }
}
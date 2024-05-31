package game.gui;

import game.collectibles.*;
import game.creatures.Dragon;
import game.creatures.Guardian;
import game.creatures.Lion;
import game.dice.*;
import game.engine.*;
import game.exceptions.*;
import game.realms.GreenRealm;
import game.realms.Realm;
import game.realms.RedRealm;
import game.utilities.CollectiblesComparator;
import game.utilities.GameColor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GUIGameController extends CLIGameController implements Initializable {
    private final Image transparentImage;
    private StackPane[] diceGUI;
    Map<Player, CompositeScoreSheetController> playerScoreSheet;
    @FXML
    private Label gameText;
    @FXML
    private ImageView gameTextBox;
    @FXML
    private Button rollButton;
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
    private Button redDiceButton;
    @FXML
    private Button greenDiceButton;
    @FXML
    private Button blueDiceButton;
    @FXML
    private Button magentaDiceButton;
    @FXML
    private Button yellowDiceButton;
    @FXML
    private Button whiteDiceButton;
    //------------------------------------------BUTTONS FLAG------------------------------------------//
    private boolean redDiceButtonFlag;
    private boolean greenDiceButtonFlag;
    private boolean blueDiceButtonFlag;
    private boolean magentaDiceButtonFlag;
    private boolean yellowDiceButtonFlag;
    private boolean whiteDiceButtonFlag;
    private boolean timeWarpButtonFlag;
    private boolean arcaneBoostButtonFlag;
    private boolean skipButtonFlag;
    private boolean rollButtonFlag;
    //------------------------------------------------------------------------------------------------//

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
    private Button timeWarpButton;
    @FXML
    private Button arcaneBoostButton;
    @FXML
    private Button skipButton;
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
    private final double lowOpacity = 0.5;
    private final double highOpacity = 1;
    private final int longDelay = 0;
    private final int mediumDelay = 0;
    //This player points to the current player whether passive or active or arcaneBoost enabled
    private Player currentPlayer;

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
        gameText.setText(gameGuide.getInstruction(Instruction.TURN));
        updateDiceValues();
        disableAllButtons();
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

    //------------------------------------------------------DICE RELATED METHODS & UPDATERS-------------------------------------------------------//

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

    private void resetDice() {
        for (int i = 0; i < diceArray.length; i++) {
            diceArray[i].setDiceStatus(DiceStatus.AVAILABLE);
            moveDice(diceGUI[i], diceGrid);
        }
    }

    @Override
    public boolean selectDice(Dice dice, Player player) {
        if (dice == null || player == null) {
            System.err.println("Error: Dice or Player cannot be null.");
            return false;
        }
        try {
            dice.setDiceStatus(DiceStatus.TURN_SELECTED);
            diceGUI[dice.getRealm().ordinal()].getChildren().stream()
                    .filter(node -> node instanceof Button)
                    .forEach(node -> node.setDisable(true));
            diceGUI[dice.getRealm().ordinal()].setOpacity(0.3);
            for (Dice diceFromArray : diceArray) {
                if (diceFromArray.getDiceStatus() == DiceStatus.AVAILABLE && diceFromArray.getValue() < dice.getValue()) {
                    diceFromArray.setDiceStatus(DiceStatus.FORGOTTEN_REALM);
                    moveDice(diceGUI[dice.getRealm().ordinal()], forgottenRealmGrid);
                    diceGUI[dice.getRealm().ordinal()].setOpacity(0.5);
                }
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
    }

    //------------------------------------------------------BUTTONS AVAILABILITY METHODS-------------------------------------------------------//
    private void disableArcaneBoostButton() {
        arcaneBoostButton.setDisable(true);
        arcaneBoostStackPane.setOpacity(lowOpacity);
    }

    private void disableTimeWarpButton() {
        timeWarpButton.setDisable(true);
        timeWarpStackPane.setOpacity(lowOpacity);
    }

    private void disableSkipButton() {
        skipButton.setDisable(true);
        skipButtonStackPane.setOpacity(lowOpacity);
    }

    private void enableArcaneBoostButton() {
        arcaneBoostButton.setDisable(false);
        arcaneBoostStackPane.setOpacity(highOpacity);
    }

    private void enableTimeWarpButton() {
        timeWarpButton.setDisable(false);
        timeWarpStackPane.setOpacity(highOpacity);
    }

    private void enableSkipButton() {
        skipButton.setDisable(false);
        skipButtonStackPane.setOpacity(highOpacity);
    }

    private void disableForgottenRealmButtons() {
        for (int i = 0; i < diceArray.length; i++) {
            if (diceArray[i].getDiceStatus() == DiceStatus.FORGOTTEN_REALM) {
                diceGUI[i].getChildren().stream()
                        .filter(node -> node instanceof Button)
                        .forEach(node -> node.setDisable(true));
                diceGUI[i].setOpacity(lowOpacity);
            }
        }
        forgottenRealmGrid.setOpacity(lowOpacity);
    }

    private void enableForgottenRealmButtons() {
        for (int i = 0; i < diceArray.length; i++) {
            if (diceArray[i].getDiceStatus() == DiceStatus.FORGOTTEN_REALM) {
                diceGUI[i].getChildren().stream()
                        .filter(node -> node instanceof Button)
                        .forEach(node -> node.setDisable(false));
                diceGUI[i].setOpacity(highOpacity);
            }
        }
        forgottenRealmGrid.setOpacity(highOpacity);
    }

    private void disableMainBoardDiceButtons() {
        for (int i = 0; i < diceArray.length; i++) {
            if (diceArray[i].getDiceStatus() == DiceStatus.AVAILABLE) {
                diceGUI[i].getChildren().stream()
                        .filter(node -> node instanceof Button)
                        .forEach(node -> node.setDisable(true));
            }
        }
        diceGrid.setOpacity(lowOpacity);
    }

    private void disableDiceButton(StackPane dice) {
        dice.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> node.setDisable(false));
        dice.setOpacity(lowOpacity);
    }

    private void enableMainBoardDiceButtons() {
        for (int i = 0; i < diceArray.length; i++) {
            if (diceArray[i].getDiceStatus() == DiceStatus.AVAILABLE) {
                diceGUI[i].getChildren().stream()
                        .filter(node -> node instanceof Button)
                        .forEach(node -> node.setDisable(false));
            }
        }
        diceGrid.setOpacity(highOpacity);
    }

    private void enableDiceButton(StackPane dice) {
        dice.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> node.setDisable(false));
        dice.setOpacity(highOpacity);
    }

    private void disableRollButton() {
        rollButton.setDisable(true);
        rollButtonStackPane.setOpacity(lowOpacity);
    }

    private void enableRollButton() {
        rollButton.setDisable(false);
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
    private void clearFlags(){
        redDiceButtonFlag=false;
        greenDiceButtonFlag=false;
        blueDiceButtonFlag=false;
        magentaDiceButtonFlag=false;
        yellowDiceButtonFlag=false;
        whiteDiceButtonFlag=false;
        timeWarpButtonFlag=false;
        arcaneBoostButtonFlag=false;
        skipButtonFlag=false;
        rollButtonFlag=false;
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
    public void diceButtonHoverOff(){
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
        turnLabel.setText("Turn " + gameStatus.getTurn());
    }

    //------------------------------------------------------BUTTONS FUNCTIONS-------------------------------------------------------//
    public void redDiceButtonClick() {
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[0]).length == 0) {
                throw new InvalidMoveException();
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[0])[0]);
            redDiceButtonFlag=true;
        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[3]);
        }
    }

    public void greenDiceButtonClick() {
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[1]).length == 0) {
                throw new InvalidMoveException();
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[1])[0]);
            greenDiceButtonFlag=true;

        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[1]);
        }
    }

    public void blueDiceButtonClick(){
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[2]).length == 0) {
                throw new InvalidMoveException();
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[2])[0]);
            blueDiceButtonFlag=true;

        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[2]);
        }
    }

    public void magentaDiceButtonClick() {
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[3]).length == 0) {
                throw new InvalidMoveException();
            }
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[3])[0]);
            magentaDiceButtonFlag=true;

        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[3]);
        }
    }

    public void yellowDiceButtonClick() {
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[4]).length == 0) {
                throw new InvalidMoveException();
            }
                makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[4])[0]);
                yellowDiceButtonFlag=true;

        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[4]);
        }

    }

    public void whiteDiceButtonClick() {
        try{
            if (getPossibleMovesForADie(currentPlayer, diceArray[5]).length == 0) {
                throw new InvalidMoveException();
            }
            //TODO switch to realms picker scene
            makeMove(currentPlayer, getPossibleMovesForADie(currentPlayer, diceArray[5])[0]);
            whiteDiceButtonFlag=true;

        }
        catch(InvalidMoveException e){
            gameText.setText("There are no possible moves for "+diceArray[5]);
        }
    }
    //------------------------------------------------------GATHERING DATA FROM OTHER SCENES-------------------------------------------------------//
//    private Move getUserInput(String scene){
//        final Move userInput=null;
//        javafx.application.Platform.runLater(() -> {
//            sceneManager.switchRealmPickerScene("scene2", (input) -> {
//                userInput = input;
//                synchronized (userInput) {
//                    userInput.notify();
//                }
//            });
//        });
//        // Wait for user input to be collected
//        synchronized (userInput) {
//            while (userInput== null) {
//                try {
//                    userInput.wait();
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }

    //------------------------------------------------------GAME LOGIC METHODS-------------------------------------------------------//
    @Override
    public void startGame() {
        standardAntiCheatService.initMasterPlayer();
        performAntiCheatServiceChecks(activePlayer);
        performAntiCheatServiceChecks(passivePlayer);

        CompletableFuture<Void> gameFuture = CompletableFuture.completedFuture(null);

        for (int i = 0; i < MAX_NUMBER_OF_ROUNDS; i++) {
            final int roundIndex = i;
            gameFuture = gameFuture.thenCompose(v -> {
                gameStatus.resetTurn();
                updateSceneStatus();
                highlightCurrentRound();

                CompletableFuture<Void> roundFuture = CompletableFuture.completedFuture(null);

                if (roundRewards[roundIndex] != null) {
                    performReward(activePlayer, roundRewards[roundIndex]);
                }

                roundFuture = roundFuture.thenCompose(v2 -> playRoundGUI());
                roundFuture = roundFuture.thenCompose(v2 -> {
                    switchPlayer();
                    gameStatus.resetTurn();
                    updateSceneStatus();
                    if (roundRewards[roundIndex] != null) {
                        performReward(activePlayer, roundRewards[roundIndex]);
                    }
                    return playRoundGUI();
                });
                return roundFuture.thenRun(gameStatus::incrementRound);
            });
        }

        gameFuture.thenRun(this::endGame);
    }

    protected CompletableFuture<Void> playRoundGUI() {
        gameText.setText(gameGuide.getInstruction(Instruction.ROUND));
        delay(longDelay);
        resetDice();

        CompletableFuture<Void> roundFuture = CompletableFuture.completedFuture(null);

        for (int i = 0; (i < MAX_NUMBER_OF_TURNS) && containsAvailableDie(); i++) {
            roundFuture = roundFuture.thenCompose(v -> {
                CompletableFuture<Void> rollingFuture = new CompletableFuture<>();

                Platform.runLater(() -> {
                    gameText.setText(gameGuide.getInstruction(Instruction.TURN));
                    delay(mediumDelay);
                    updateSceneStatus();
                    enableRollButton();
                    updateScoreSheets();

                    rollButton.setOnAction(event -> {
                        rollDice();
                        disableRollButton();
                        rollingFuture.complete(null);
                    });
                });

                return rollingFuture.thenCompose(v2 -> playTurn());
            });
        }

        return roundFuture.thenRun(() -> {
            moveDiceToForgottenRealm();
            playPassiveTurn();
            checkArcaneBoost(activePlayer);
            checkArcaneBoost(passivePlayer);
        });
    }

    private CompletableFuture<Void> playTurn() {
        currentPlayer = activePlayer;
        CompletableFuture<Void> turnFuture = new CompletableFuture<>();
        Platform.runLater(() -> {
            Dice[] availableDice = getAvailableDice();
            try {
                if (availableDice.length == 0) {
                    throw new NoAvailableMovesException();
                }
                enableMainBoardDiceButtons();

                updateScoreSheets();
                gameStatus.incrementTurn();
                turnFuture.complete(null);
            } catch (NoAvailableMovesException e) {
                if (checkTimeWarp(activePlayer)) {
                    gameText.setText("No possible moves available. Use Time Warp?");
                    enableTimeWarpButton();
                    enableSkipButton();

                    timeWarpButton.setOnAction(timeWarpEvent -> {
                        disableTimeWarpButton();
                        disableSkipButton();
                        // Apply Time Warp logic
                        playTurn();
                        turnFuture.complete(null);
                    });

                    skipButton.setOnAction(skipEvent -> {
                        disableTimeWarpButton();
                        disableSkipButton();
                        disableMainBoardDiceButtons();
                        turnFuture.completeExceptionally(e);
                    });
                } else {
                    turnFuture.completeExceptionally(e);
                    disableMainBoardDiceButtons();
                }
            }
        });

        return turnFuture.handle((result, throwable) -> {
            if (throwable != null) {
                gameText.setText("Turn skipped");
            } else {
                gameText.setText("End of turn");
            }
            return null;
        });
    }

    private void playPassiveTurn() {
        System.out.println(passivePlayer.getName());
        gameGuide.displayInstructions(Instruction.PASSIVE_TURN);
        passivePlayer.getScoreSheet().displayScoreSheet();
        Dice[] temp = getForgottenRealmDice();
        Dice selectedDie;
        try {
            selectedDie = selectValidDie(passivePlayer, temp, false, DiceStatus.TURN_SELECTED);
        } catch (NoAvailableMovesException e) {
            System.out.println("Ohh bad luck...No possible moves!");
            System.out.println("Passive turn lost!");
            return;
        }
        Move selectedMove = selectValidMove(passivePlayer, selectedDie);
        makeMove(passivePlayer, selectedMove);
    }

    protected void playExtraTurn(Player player) {
        player.getScoreSheet().displayScoreSheet();
        LinkedList<Dice> notSelectedByPlayer = new LinkedList<>();
        DiceStatus filter = player.getPlayerStatus() == PlayerStatus.ACTIVE ? DiceStatus.ACTIVE_PLAYER_SELECTED : DiceStatus.PASSIVE_PLAYER_SELECTED;
        for (Dice i : diceArray) {
            if (i.getDiceStatus() != filter) {
                notSelectedByPlayer.add(i);
            }
        }
        Dice[] diceNotSelectedByPlayer = notSelectedByPlayer.toArray(Dice[]::new);
        try {
            Dice selectedDie = selectValidDie(player, diceNotSelectedByPlayer, false, filter);
            Move validMove = selectValidMove(player, selectedDie);
            makeMove(player, validMove);
        } catch (NoAvailableMovesException e) {
            System.out.println("Ohh bad luck...there are no possible moves, turn lost!");
        }
    }

    protected void checkArcaneBoost(Player player) {
        while (player.isArcaneBoostAvailable()) {
            displayArcaneBoostStatus(player);
            boolean choice = gameGuide.getUserBooleanChoice();
            if (choice) {
                try {
                    if (getPossibleMovesForDice(player, diceArray).length == 0) {
                        throw new NoAvailableMovesException();
                    }
                    player.useArcaneBoostPower();
                    playExtraTurn(player);
                } catch (NoAvailableMovesException e) {
                    System.out.println("No available moves for current dice");
                    System.out.println("You can't use Arcane Boost");
                    break;
                }
            } else {
                break;
            }
        }
    }

    protected boolean checkTimeWarp(Player player) {
        if (player.isTimeWarpAvailable()) {
            gameText.setText("You can use Time Warp!");
            enableTimeWarpButton();
            return true;
        }
        gameText.setText("");
        disableTimeWarpButton();
        return false;
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
            return true;

        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    protected void displayArcaneBoostStatus(Player player) {
        System.out.println(player.getName());
        gameGuide.displayInstructions(Instruction.AB_PROMPT);
        int count = player.getTotalArcaneBoostPowersCollected();
        System.out.printf("You have %d Arcane Boost%s%n", count, count > 1 ? "s" : "");
    }

    protected void displayTimeWarpStatus(Player player) {
        int count = player.getTotalTimeWarpPowersCollected();
        gameGuide.displayInstructions(Instruction.TW_PROMPT);
        System.out.printf("You have %d Time Warp%s%n", count, count > 1 ? "s" : "");
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
        delay(3000);
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
        gameGuide.displayInstructions(Instruction.COLOR_BONUS);
        switch (gameColor) {
            case RED: {
                Dice[] redDice = new Dice[]{
                        new RedDice(1),
                        new RedDice(2),
                        new RedDice(3),
                        new RedDice(4),
                        new RedDice(5),
                        new RedDice(6)};
                try {
                    Dice selectedDie = selectValidDie(player, redDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);

                } catch (NoAvailableMovesException e) {
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case GREEN: {
                // Define green dice
                GreenRealm greenRealm = (GreenRealm) player.getRealm(GameColor.GREEN);
                LinkedList<Guardian> aliveCreatures = greenRealm.getAliveCreatures();
                Guardian[] allCreatures = ((GreenRealm) player.getRealm(GameColor.GREEN)).getAllCreatures();
                try {
                    if (aliveCreatures.isEmpty() || !greenRealm.isRealmAvailable()) {
                        throw new NoAvailableMovesException();
                    }
                    gameGuide.displayCreatures(allCreatures);
                    System.out.println("Choose a Gaia to attack:");
                    while (true) {
                        int choice = gameGuide.getUserChoice(2, 12);
                        try {
                            if (!aliveCreatures.contains(allCreatures[choice - 2])) {
                                throw new InvalidMoveException();
                            }
                            System.out.println(allCreatures[choice - 2].getName());
                            greenRealm.attack(new Move(new GreenDice(allCreatures[choice - 2].getScore()), allCreatures[choice - 2]));
                            break;
                        } catch (InvalidMoveException e) {
                            System.out.println(allCreatures[choice - 2].getName() + " is dead, choose another Gaia");
                        }
                    }

                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case BLUE: {
                // Define blue dice
                Dice[] blueDice = new Dice[]{
                        new BlueDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, blueDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case MAGENTA: {
                // Define magenta dice
                Dice[] magentaDice = new Dice[]{
                        new MagentaDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, magentaDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            case YELLOW: {
                // Define yellow dice
                Dice[] yellowDice = new Dice[]{
                        new YellowDice(6)};
                try {
                    // Select a valid die and move
                    Dice selectedDie = selectValidDie(player, yellowDice, false, DiceStatus.TURN_SELECTED);
                    Move selectedMove = selectValidMove(player, selectedDie);
                    makeMove(player, selectedMove);
                } catch (NoAvailableMovesException e) {
                    // Handle case where no moves are available
                    System.out.println("Ohh bad luck...no possible moves, bonus lost!");
                }
                break;
            }
            default:
                System.err.println("Invalid color bonus: " + gameColor);
        }
    }

    protected void playEssenceBonus(Player player) {
        gameGuide.displayInstructions(Instruction.ESSENCE_BONUS);
        player.getScoreSheet().displayScoreSheet();
        Realm[] realms = player.getRealms();
        LinkedList<Realm> availableRealms = Stream.of(realms)
                .filter(Realm::isRealmAvailable)
                .collect(Collectors.toCollection(LinkedList::new));

        if (availableRealms.size() != realms.length) {
            while (true) {
                displayRealms(player);
                System.out.println("Possible realms to choose from:");
                for (int i = 0; i < availableRealms.size(); i++) {
                    System.out.print(availableRealms.get(i).getName());
                    if (i != availableRealms.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
                int choice = gameGuide.getUserChoice(1, realms.length);
                try {
                    if (!availableRealms.contains(realms[choice - 1])) {
                        throw new InvalidMoveException();
                    }
                    playColorBonus(player, realms[choice - 1].getColor());
                    break;
                } catch (InvalidMoveException e) {
                    System.out.printf("%s is not available%n", realms[choice - 1].getName());
                    System.out.println("Choose another realm");
                }
            }
        } else {
            displayRealms(player);
            playColorBonus(player, realms[gameGuide.getUserChoice(1, realms.length) - 1].getColor());
        }


    }

    //------------------------------------------------------SECONDARY METHODS-------------------------------------------------------//

    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {

        }
    }


    private boolean containsAvailableDie() {
        for (Dice i : diceArray) {
            if (i.getDiceStatus() == DiceStatus.AVAILABLE) {
                return true;
            }
        }
        return false;
    }

    //Selects a valid die (has an available move in player)
    //Prints given dice if no moves or prints filtered dice then the selected die
    private Dice selectValidDie(Player player, Dice[] dice, boolean checkTimeWarp, DiceStatus diceStatus) throws NoAvailableMovesException {
        Dice selectedDie;
        LinkedList<Dice> filteredDice;
        if (getPossibleMovesForDice(player, dice).length == 0) {
            gameGuide.displayNumberedChoice(dice);
            throw new NoAvailableMovesException();
        } else {
            gameGuide.displayNumberedChoice(dice);
            filteredDice = filterDiceWithPossibleMoves(player, dice);
            if (filteredDice.size() != dice.length) {
                System.out.println("Possible Dice to choose from:");
                System.out.println(filteredDice);
            }
            while (checkTimeWarp && checkTimeWarp(player)) {
                filteredDice = filterDiceWithPossibleMoves(player, dice);
                if (filteredDice.isEmpty()) {
                    throw new NoAvailableMovesException();
                }
                gameGuide.displayNumberedChoice(dice);
                if (filteredDice.size() != dice.length) {
                    System.out.println("Possible Dice to choose from:");
                    System.out.println(filteredDice);
                }
            }
            while (true) {
                int choice = gameGuide.getUserChoice(1, dice.length);
                selectedDie = dice[choice - 1];
                try {
                    if (!filteredDice.contains(dice[choice - 1])) {
                        throw new InvalidMoveException();
                    }
                    break;
                } catch (InvalidMoveException e) {
                    System.out.println("There are no possible moves for " + selectedDie);
                    System.out.println("Choose another die");
                }
            }

        }
        System.out.println(selectedDie);
        if (selectedDie instanceof WhiteDice) {
            System.out.println("Choose which realm to play with Arcane Prism");
            selectedDie.setDiceStatus(diceStatus);
            //RED, GREEN, BLUE, MAGENTA, YELLOW
            Dice[] possibleDice = {
                    new RedDice(selectedDie.getValue()),
                    new GreenDice(selectedDie.getValue()),
                    new BlueDice(selectedDie.getValue()),
                    new MagentaDice(selectedDie.getValue()),
                    new YellowDice(selectedDie.getValue())};
            return selectValidDie(player, possibleDice, false, diceStatus);
        }
        selectedDie.setDiceStatus(diceStatus);
        return selectedDie;
    }


    @Override
    public boolean switchPlayer() {
        boolean flag;
        try {
            if (activePlayer != passivePlayer && activePlayer.getPlayerStatus() == PlayerStatus.ACTIVE &&
                    passivePlayer.getPlayerStatus() == PlayerStatus.PASSIVE) {
                activePlayer.setPlayerStatus(PlayerStatus.PASSIVE);
                passivePlayer.setPlayerStatus(PlayerStatus.ACTIVE);
                Player temp = activePlayer;
                activePlayer = passivePlayer;
                passivePlayer = temp;
                flag = true;
            } else {
                flag = false;
            }
            currentPlayer = activePlayer;

        } catch (NullPointerException e) {
            System.err.println("Invalid Switch: " + e.getMessage());
            flag = false;
        }
        return flag;
    }


    /**
     * Gets the dice available for rolling or rerolling.
     *
     * @return An array of {@code Dice} available for the current turn.
     */

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


    protected void performAntiCheatServiceChecks(Player player) {
        try {
            standardAntiCheatService.checkPlayerScore(player);
            standardAntiCheatService.checkDice(diceArray);
            standardAntiCheatService.checkPlayerFinalScore(player);
            standardAntiCheatService.checkPlayerReward(player);
            standardAntiCheatService.checkGameStatus(gameStatus);
        } catch (DiceCheatException e) {
            System.err.println(e.getMessage());
            standardAntiCheatService.handleDiceCheat(diceArray);
        } catch (InvalidFinalScoreCheat e) {
            System.err.println("Cheat detected in final score of player: " + player.getName());
            systemManager.exit("Cheat detected!");
        } catch (RewardCheatException e) {
            System.err.println("Cheat detected in rewards of player: " + player.getName());
            standardAntiCheatService.handleRewardCheat(player);
        } catch (NegativeScoreException e) {
            System.err.println("Cheat detected in score of player: " + player.getName());
            System.err.println("Score is below zero!");
            standardAntiCheatService.handlePlayerScore(player);
        } catch (HighScoreException e) {
            System.err.println("Cheat detected in score of player: " + player.getName());
            System.err.println("Score is invalid: " + player.getGameScore().getTotalScore());
            standardAntiCheatService.handlePlayerScore(player);
        } catch (CheatDetectedException e) {
            systemManager.exit("Cheat detected!");
        }
    }

    public Collectibles[] getRoundRewards() {
        return roundRewards;
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
package game.gui;

import game.engine.AIPlayer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The GamePlayController class implements the Initializable and GameController interfaces.
 * It is responsible for controlling the gameplay scene of the game.
 */
public class GamePlayController implements Initializable,GameController {

    private SceneManager sceneManager;
    @FXML
    private AnchorPane player1ScoreSheetContainer;
    @FXML
    private AnchorPane gameBoardContainer;
    @FXML
    private AnchorPane player2ScoreSheetContainer;
    @FXML
    private ImageView backGround;

    /**
     * Sets the SceneManager for the GamePlayController.
     * The SceneManager is responsible for managing scene switching and providing access
     * to other controllers within the application.
     *
     * @param sceneManager The SceneManager to be set.
     */
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Initializes the game controller.
     *
     * @param url            The URL of the location used to resolve relative paths for the root object, or null if
     *                       the location is not known.
     * @param resourceBundle The resource bundle containing locale-specific objects, or null if not used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image mainBG = new Image(Objects.requireNonNull(getClass().getResource("/images/more clear backgroundgame2.jpeg")).toExternalForm());
            backGround.setImage(mainBG);
            // Load and configure Player 1 Composite ScoreSheet
            FXMLLoader player1Loader = new FXMLLoader(getClass().getResource("CompositeScoreSheet.fxml"));
            AnchorPane player1ScoreSheet = player1Loader.load();
            CompositeScoreSheetController player1Controller = player1Loader.getController();
            player1ScoreSheetContainer.getChildren().add(player1ScoreSheet);
            // Load GameBoard
            FXMLLoader gameBoardLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            AnchorPane gameBoard = gameBoardLoader.load();
            GUIGameController guiGameController = gameBoardLoader.getController();
            guiGameController.setSceneManager(DiceRealms.getSceneManager());
            guiGameController.setPlayer1(PlayerDataController.getPlayer1());
            guiGameController.setPlayer2(PlayerDataController.getPlayer2());
            guiGameController.setPlayer1ScoreSheet(player1Controller);
            guiGameController.setGameMode(MainMenuController.getGameMode());
            gameBoardContainer.getChildren().add(gameBoard);
            // Load and configure Player 2 Composite ScoreSheet
            FXMLLoader player2Loader = new FXMLLoader(getClass().getResource("CompositeScoreSheet.fxml"));
            AnchorPane player2ScoreSheet = player2Loader.load();
            CompositeScoreSheetController player2Controller = player2Loader.getController();
            player2ScoreSheetContainer.getChildren().add(player2ScoreSheet);
            guiGameController.setPlayer2ScoreSheet(player2Controller);
            SceneManager.setGuiGameController(guiGameController);
            Platform.runLater(guiGameController::startGame);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

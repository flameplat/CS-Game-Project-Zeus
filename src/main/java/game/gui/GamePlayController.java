package game.gui;

import game.engine.GameMode;
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

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Image mainBG = new Image(Objects.requireNonNull(getClass().getResource("/images/RetroDice.png")).toExternalForm());
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
            if (MainMenuController.getGameMode() == GameMode.MULTIPLAYER) {
                guiGameController.setPlayer1(PlayerDataController.getPlayer1());
                guiGameController.setPlayer2(PlayerDataController.getPlayer2());
                guiGameController.setPlayer1ScoreSheet(player1Controller);
                guiGameController.setGameMode(GameMode.MULTIPLAYER);
            }
            gameBoardContainer.getChildren().add(gameBoard);
            // Load and configure Player 2 Composite ScoreSheet
            FXMLLoader player2Loader = new FXMLLoader(getClass().getResource("CompositeScoreSheet.fxml"));
            AnchorPane player2ScoreSheet = player2Loader.load();
            CompositeScoreSheetController player2Controller = player2Loader.getController();
            player2ScoreSheetContainer.getChildren().add(player2ScoreSheet);
            guiGameController.setPlayer2ScoreSheet(player2Controller);
            SceneManager.setGuiGameController(guiGameController);
            guiGameController.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

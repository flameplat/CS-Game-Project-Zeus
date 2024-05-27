package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GamePlayController implements Initializable {

    @FXML
    private VBox player1ScoreSheetContainer;

    @FXML
    private VBox gameBoardContainer;

    @FXML
    private VBox player2ScoreSheetContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Load and configure Player 1 Composite ScoreSheet
            FXMLLoader player1Loader = new FXMLLoader(getClass().getResource("CompositeScoreSheet.fxml"));
            VBox player1ScoreSheet = player1Loader.load();
            CompositeScoreSheetController player1Controller = player1Loader.getController();
            ///TODO: add setters for scoresheet -> guiController
            player1ScoreSheetContainer.getChildren().add(player1ScoreSheet);

            // Load GameBoard
            FXMLLoader gameBoardLoader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
            VBox gameBoard = gameBoardLoader.load();
            gameBoardContainer.getChildren().add(gameBoard);
            // Load and configure Player 2 Composite ScoreSheet
            FXMLLoader player2Loader = new FXMLLoader(getClass().getResource("CompositeScoreSheet.fxml"));
            VBox player2ScoreSheet = player2Loader.load();
            CompositeScoreSheetController player2Controller = player2Loader.getController();
            player2ScoreSheetContainer.getChildren().add(player2ScoreSheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

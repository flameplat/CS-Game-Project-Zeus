package game.gui;

import game.engine.Player;
import game.engine.PlayerStatus;
import game.exceptions.InvalidPlayerNameException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerDataController implements Initializable {
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
    private GUIGameController gameController;
    private static int playersSubmitted=0;
    private SceneManager sceneManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/Wizards.jpeg")).toExternalForm());
        Image button=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm());
        bg.setImage(mainBG);
        button1.setImage(button);
        mainLabel.setText("Enter Player 1 Name");
    }
    public void setGameController(GUIGameController gameController){
        this.gameController=gameController;
    }
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    public void setPlayerName() {
        try {
            if (playersSubmitted < 1) {
                Player player = new Player(textField.getText());
                gameController.setPlayer1(player);
                errorLabel.setText("");
                mainLabel.setText("Enter Player 2 Name");
                playersSubmitted++;
            } else {
                if (gameController.getGameBoard().getPlayer1() != null && textField.getText().equals(gameController.getGameBoard().getPlayer1().getName())) {
                    throw new InvalidPlayerNameException("Name already in use!");
                }
                Player player = new Player(textField.getText());
                gameController.setPlayer2(player);
                errorLabel.setText("");
                playersSubmitted++;
                sceneManager.switchGamePlayScene();
            }
        } catch (InvalidPlayerNameException e) {
            errorLabel.setText(e.getMessage());
        }

    }
}

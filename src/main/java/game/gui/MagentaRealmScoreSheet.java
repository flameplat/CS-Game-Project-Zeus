package game.gui;

import game.realms.MagentaRealm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MagentaRealmScoreSheet implements Initializable {
    @FXML
    private Label hit1ScoreLabel;
    @FXML private Label hit2ScoreLabel;
    @FXML private Label hit3ScoreLabel;
    @FXML private Label hit4ScoreLabel;
    @FXML private Label hit5ScoreLabel;
    @FXML private Label hit6ScoreLabel;
    @FXML private Label hit7ScoreLabel;
    @FXML private Label hit8ScoreLabel;
    @FXML private Label hit9ScoreLabel;
    @FXML private Label hit10ScoreLabel;
    @FXML private Label hit11ScoreLabel;

    @FXML private Label hit1RewardLabel;
    @FXML private Label hit2RewardLabel;
    @FXML private Label hit3RewardLabel;
    @FXML private Label hit4RewardLabel;
    @FXML private Label hit5RewardLabel;
    @FXML private Label hit6RewardLabel;
    @FXML private Label hit7RewardLabel;
    @FXML private Label hit8RewardLabel;
    @FXML private Label hit9RewardLabel;
    @FXML private Label hit10RewardLabel;
    @FXML private Label hit11RewardLabel;
    private MagentaRealm magentaRealm;
    private Label[] scoreLabels;
    private Label[] rewardLabels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

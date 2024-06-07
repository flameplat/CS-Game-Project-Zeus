package game.gui;

import game.engine.Move;
import game.realms.MagentaRealm;
import game.realms.YellowRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MagentaRealmScoreSheet implements Initializable {
    @FXML private GridPane gridPane;
    @FXML private Label hit1ScoreLabel;
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
        scoreLabels = new Label[] {
                hit1ScoreLabel, hit2ScoreLabel, hit3ScoreLabel, hit4ScoreLabel, hit5ScoreLabel,
                hit6ScoreLabel, hit7ScoreLabel, hit8ScoreLabel, hit9ScoreLabel, hit10ScoreLabel, hit11ScoreLabel
        };

        rewardLabels = new Label[] {
                hit1RewardLabel, hit2RewardLabel, hit3RewardLabel, hit4RewardLabel, hit5RewardLabel,
                hit6RewardLabel, hit7RewardLabel, hit8RewardLabel, hit9RewardLabel, hit10RewardLabel, hit11RewardLabel
        };

    }
    public void updateScoreSheet(){
        int[] hitScore=magentaRealm.getScoreValues();
        for(int i=0;i< magentaRealm.getCounterHits();i++){
            scoreLabels[i].setText(String.valueOf(hitScore[i]));
        }
        String[] rewardValues=magentaRealm.getRewardValues();
        for(int i=0;i<rewardValues.length;i++){
            rewardLabels[i].setText(rewardValues[i]);
        }
    }
    public void setRealm(MagentaRealm yellowRealm){
        this.magentaRealm=yellowRealm;
    }
    public void highlightMoves(Move[] moves){
        for (Move move : moves) {
            if(move.getDice().getRealm()== GameColor.MAGENTA){
                if(magentaRealm.isRealmAvailable()){
                    if(magentaRealm.getRealmMoves()[0].getDice().getValue()<=move.getDice().getValue()){
                        int col=magentaRealm.getCounterHits();
                        highlightCell(0,col+1,"white");
                        highlightCell(1,col+1,"white");
                        highlightCell(2,col+1,"white");
                    }
                }
                break;
            }
        }

    }
    public void removeHighlight(){
        for(int i=0;i<4;i++){
            for(int j=0;j<12;j++){
                highlightCell(i,j,"null");
            }
        }
    }
    private void highlightCell(int row, int column,String color) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);

                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == column) {
                    if(color == "null"){
                        node.setStyle("");
                        break;
                    }
                    node.setStyle(String.format("-fx-background-color: %s;",color));
                    break;
                }
            }
        }
    }
}

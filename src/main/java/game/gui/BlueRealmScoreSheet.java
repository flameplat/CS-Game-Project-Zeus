package game.gui;

import game.engine.Move;
import game.realms.BlueRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BlueRealmScoreSheet implements Initializable {
    @FXML
    private GridPane gridPane;
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

    @FXML private Label score1Label;
    @FXML private Label score2Label;
    @FXML private Label score3Label;
    @FXML private Label score4Label;
    @FXML private Label score5Label;
    @FXML private Label score6Label;
    @FXML private Label score7Label;
    @FXML private Label score8Label;
    @FXML private Label score9Label;
    @FXML private Label score10Label;
    @FXML private Label score11Label;
    private Label[] fixedScoreLabels;

    private BlueRealm blueRealm;
    private Label[] scoreLabels;
    private Label[] rewardLabels;


    /**
     * Initializes the score sheet by assigning the labels for scores, rewards, and fixed scores.
     *
     * @param url           The URL of the FXML file.
     * @param resourceBundle The resource bundle of the FXML file.
     */
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
        fixedScoreLabels = new Label[] {
                score1Label, score2Label, score3Label, score4Label, score5Label,
                score6Label, score7Label, score8Label, score9Label, score10Label, score11Label
        };

    }
    /**
     * Updates the score sheet with the latest values from the BlueRealm.
     * It sets the hit score labels, reward labels, and fixed score labels
     * based on the values obtained from the BlueRealm.
     */
    public void updateScoreSheet(){
        String[] hitScore=blueRealm.getAttackValues();
        for(int i=0;i<blueRealm.getHitcount();i++){
            scoreLabels[i].setText(hitScore[i]);
        }
        String[] rewardValues=blueRealm.getRewardValues();
        for(int i=0;i<rewardValues.length;i++){
            rewardLabels[i].setText(rewardValues[i]);
        }
        int[] scoreSheetValues = blueRealm.getScoreSheetValues();
        for (int i = 0; i < scoreSheetValues.length; i++) {
            fixedScoreLabels[i].setText(String.valueOf(scoreSheetValues[i]));
        }
    }
    /**
     * Represents the score array for the BlueRealmScoreSheet class.
     *
     * The score array stores the scores for each move in the game. It is an
     * array of integers with a length of 11. Each element of the array
     * corresponds to a particular move in the game. The score values can
     * range from 1 to 6.
     */
    private final int[] score=new int[]{1,2,3,4,5,1,2,3,4,5,6};
    /**
     * Sets the BlueRealm for the BlueRealmScoreSheet.
     *
     * @param blueRealm the BlueRealm to set
     */
    public void setRealm(BlueRealm blueRealm){
        this.blueRealm=blueRealm;
    }
    /**
     * Highlights the cells on the blue realm score sheet grid based on the given array of moves.
     * Only cells with a blue die in the current realm and a value greater than or equal to the score of the current hit count will be highlighted in green color.
     * @param moves the array of moves
     */
    public void highlightMoves(Move[] moves){
        for (Move move : moves) {
            if(move.getDice().getRealm()== GameColor.BLUE
                    && blueRealm.isRealmAvailable()
                    && move.getDice().getValue()>=score[blueRealm.getHitcount()]) {
                int col=blueRealm.getHitcount();
                highlightCell(0,col+1,"blue");
                highlightCell(1,col+1,"blue");
                highlightCell(2,col+1,"blue");
                highlightCell(3,col+1,"blue");
                break;
            }
        }

    }
    /**
     * Removes the highlighting from all cells in the score sheet grid.
     */
    public void removeHighlight(){
        for(int i=0;i<4;i++){
            for(int j=0;j<12;j++){
                highlightCell(i,j,"null");
            }
        }
    }

    /**
     * Highlights a specified cell in the grid pane with a given color.
     *
     * @param row    the row index of the cell to highlight
     * @param column the column index of the cell to highlight
     * @param color  the color to apply to the cell, in the form of a CSS color string
     */
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

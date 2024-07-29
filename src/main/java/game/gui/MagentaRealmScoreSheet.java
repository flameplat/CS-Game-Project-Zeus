package game.gui;

import game.engine.Move;
import game.realms.MagentaRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
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


    /**
     * Initializes the score labels and reward labels.
     *
     * @param url The location used to resolve relative paths. Unused in this method.
     * @param resourceBundle The resources used by this controller. Unused in this method.
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

    }
    /**
     * Updates the score sheet with the latest values.
     * This method updates the score labels and reward labels of the Magenta Realm score sheet.
     * The score labels display the hit scores obtained from the Magenta Realm,
     * and the reward labels display the reward values associated with each hit score.
     */
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
    /**
     * Sets the realm of the MagentaRealmScoreSheet.
     *
     * @param yellowRealm The new realm to be set.
     */
    public void setRealm(MagentaRealm yellowRealm){
        this.magentaRealm=yellowRealm;
    }
    /**
     * Highlights the cells on the magentaRealm score sheet based on the given array of moves.
     *
     * @param moves the array of moves
     */
    public void highlightMoves(Move[] moves){
        for (Move move : moves) {
            if(move.getDice().getRealm()== GameColor.MAGENTA){
                if(magentaRealm.isRealmAvailable()){
                    if(magentaRealm.getRealmMoves()[0].getDice().getValue()<=move.getDice().getValue()){
                        int col=magentaRealm.getCounterHits();
                        highlightCell(0,col+1,"magenta");
                        highlightCell(1,col+1,"magenta");
                        highlightCell(2,col+1,"magenta");
                    }
                }
                break;
            }
        }

    }
    /**
     * Removes the highlighting from all cells in the score sheets of the realms.
     * This method should be called to remove the highlight after highlighting possible moves.
     */
    public void removeHighlight(){
        for(int i=0;i<4;i++){
            for(int j=0;j<12;j++){
                highlightCell(i,j,"null");
            }
        }
    }
    /**
     * Highlights a cell in the GridPane with the specified row and column index,
     * using the specified color. If the color is "null", removes the highlighting.
     *
     * @param row    the row index of the cell to highlight
     * @param column the column index of the cell to highlight
     * @param color  the color of the highlighting, or "null" to remove the highlighting
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

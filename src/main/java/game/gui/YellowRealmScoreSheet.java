package game.gui;

import game.engine.Move;
import game.realms.YellowRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class YellowRealmScoreSheet implements Initializable {
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
    private YellowRealm yellowRealm;
    private Label[] scoreLabels;
    private Label[] rewardLabels;

    /**
     * Initializes the YellowRealmScoreSheet controller.
     *
     * @param url            the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resource bundle that the root object was loaded from, or null if the root object was not loaded from a ResourceBundle
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
     * This method retrieves the score values from the Yellow Realm and updates the score labels accordingly.
     * It also retrieves the reward values from the Yellow Realm and updates the reward labels.
     */
    public void updateScoreSheet(){
        Object[] hitScore=yellowRealm.getScoreValues();
        for(int i=0;i<yellowRealm.getCountHits();i++){
            scoreLabels[i].setText(hitScore[i].toString());
        }
        String[] rewardValues=yellowRealm.getRewardValues();
        for(int i=0;i<rewardValues.length;i++){
            rewardLabels[i].setText(rewardValues[i]);
        }
    }
    /**
     * Sets the YellowRealm for the YellowRealmScoreSheet.
     *
     * @param yellowRealm The YellowRealm to set for the YellowRealmScoreSheet.
     */
    public void setRealm(YellowRealm yellowRealm){
        this.yellowRealm=yellowRealm;
    }
    /**
     * Highlights the cells on the yellow realm score sheet based on the given array of moves.
     *
     * @param moves the array of moves
     */
    public void highlightMoves(Move[] moves){
        for (Move move : moves) {
            if(move.getDice().getRealm()== GameColor.YELLOW){
                if(yellowRealm.isRealmAvailable()){
                    int col=yellowRealm.getCountHits();
                    highlightCell(0,col+1,"yellow");
                    highlightCell(1,col+1,"yellow");
                    highlightCell(2,col+1,"yellow");
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
     * Highlights a cell in the gridPane by applying a background color to the corresponding label.
     *
     * @param row   the row index of the cell to highlight
     * @param column   the column index of the cell to highlight
     * @param color   the background color to apply to the cell
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

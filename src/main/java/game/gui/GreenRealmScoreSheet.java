package game.gui;

import game.creatures.Guardian;
import game.engine.Move;
import game.realms.GreenRealm;
import game.realms.YellowRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GreenRealmScoreSheet implements Initializable {
    @FXML
    private GridPane grid;
    private GreenRealm greenRealm;

    // Guardian Values
    @FXML private Label G1;
    @FXML private Label G2;
    @FXML private Label G3;
    @FXML private Label G4;
    @FXML private Label G5;
    @FXML private Label G6;
    @FXML private Label G7;
    @FXML private Label G8;
    @FXML private Label G9;
    @FXML private Label G10;
    @FXML private Label G11;
    @FXML private Label G12;

    @FXML private Label colReward1;
    @FXML private Label colReward2;
    @FXML private Label colReward3;
    @FXML private Label colReward4;

    @FXML private Label rowReward1;
    @FXML private Label rowReward2;
    @FXML private Label rowReward3;
    @FXML private Label scoreLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void updateScoreSheet(){
        int[] score= greenRealm.getScore();
        Object[][] mainArray= greenRealm.getMainArray();
        Object[] colRewards= greenRealm.getColRewards();
        Object[] rowRewards= greenRealm.getRowRewards();
        G1.setText(mainArray[0][0].toString());
        G2.setText(mainArray[0][1].toString());
        G3.setText(mainArray[0][2].toString());
        G4.setText(mainArray[0][3].toString());
        G5.setText(mainArray[1][0].toString());
        G6.setText(mainArray[1][1].toString());
        G7.setText(mainArray[1][2].toString());
        G8.setText(mainArray[1][3].toString());
        G9.setText(mainArray[2][0].toString());
        G10.setText(mainArray[2][1].toString());
        G11.setText(mainArray[2][2].toString());
        G12.setText(mainArray[2][3].toString());
        colReward1.setText(colRewards[0].toString());
        colReward2.setText(colRewards[1].toString());
        colReward3.setText(colRewards[2].toString());
        colReward4.setText(colRewards[3].toString());
        rowReward1.setText(rowRewards[0].toString());
        rowReward2.setText(rowRewards[1].toString());
        rowReward3.setText(rowRewards[2].toString());
        String allScores="";
        for(int i=0;i<score.length-1;i++){
            allScores+=score[i]+", ";
        }
        allScores+=score[score.length-1];
        scoreLabel.setText(allScores);
    }
    public void setRealm(GreenRealm greenRealm){
        this.greenRealm=greenRealm;
    }
    public void highlightMoves(Move[] moves){
        for (Move move : moves) {
            if(move.getDice().getRealm() == GameColor.GREEN){
                switch (move.getDice().getValue()) {
                    case 1:
                        G1.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 2:
                        G2.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 3:
                        G3.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 4:
                        G4.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 5:
                        G5.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 6:
                        G6.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 7:
                        G7.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 8:
                        G8.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 9:
                        G9.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 10:
                        G10.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 11:
                        G11.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    case 12:
                        G12.setStyle(String.format("-fx-background-color: %s;","green"));
                        break;
                    default:
                        break;
                }
            }
        }
    }
    public void removeHighlight(){
        for(int i=0;i< grid.getRowCount();i++){
            for(int j=0;j< grid.getColumnCount();j++){
                highlightCell(i,j,"null");
            }
        }
    }
    private void highlightCell(int row, int column,String color) {
        for (Node node : grid.getChildren()) {
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

package game.gui;

import game.creatures.Dragon;
import game.dice.Dice;
import game.dice.RedDice;
import game.engine.Move;
import game.realms.RedRealm;
import game.utilities.GameColor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RedRealmScoreSheet implements Initializable {


    private RedRealm redRealm;
    @FXML
    private Label dragon1Face;
    @FXML
    private Label dragon1Wing;
    @FXML
    private Label dragon1Tail;
    @FXML
    private Label dragon1Heart;
    @FXML
    private Label dragon1Score;

    @FXML
    private Label dragon2Face;
    @FXML
    private Label dragon2Wing;
    @FXML
    private Label dragon2Tail;
    @FXML
    private Label dragon2Heart;
    @FXML
    private Label dragon2Score;

    @FXML
    private Label dragon3Face;
    @FXML
    private Label dragon3Wing;
    @FXML
    private Label dragon3Tail;
    @FXML
    private Label dragon3Heart;
    @FXML
    private Label dragon3Score;

    @FXML
    private Label dragon4Face;
    @FXML
    private Label dragon4Wing;
    @FXML
    private Label dragon4Tail;
    @FXML
    private Label dragon4Heart;
    @FXML
    private Label dragon4Score;
    @FXML
    private Label reward1;
    @FXML
    private Label reward2;
    @FXML
    private Label reward3;
    @FXML
    private Label reward4;
    @FXML
    private Label reward5;
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void updateLabels() {
        Dragon[] dragons = redRealm.getDragons();
        updateDragonLabels(dragons[0], dragon1Face, dragon1Wing, dragon1Tail, dragon1Heart);
        updateDragonLabels(dragons[1], dragon2Face, dragon2Wing, dragon2Tail, dragon2Heart);
        updateDragonLabels(dragons[2], dragon3Face, dragon3Wing, dragon3Tail, dragon3Heart);
        updateDragonLabels(dragons[3], dragon4Face, dragon4Wing, dragon4Tail, dragon4Heart);
        Object[] rewards = redRealm.getCollectibles();
        reward1.setText(rewards[0].toString());
        reward2.setText(rewards[1].toString());
        reward3.setText(rewards[2].toString());
        reward4.setText(rewards[3].toString());
        reward5.setText(rewards[4].toString());
        int[] score=redRealm.getDragonsScore();
        dragon1Score.setText(String.valueOf(score[0]));
        dragon2Score.setText(String.valueOf(score[1]));
        dragon3Score.setText(String.valueOf(score[2]));
        dragon4Score.setText(String.valueOf(score[3]));
    }

    private void updateDragonLabels(Dragon dragon, Label faceLabel, Label wingLabel, Label tailLabel, Label heartLabel) {
        Object[] health = dragon.getHealth();
        faceLabel.setText(health[0].toString());
        wingLabel.setText(health[1].toString());
        tailLabel.setText(health[2].toString());
        heartLabel.setText(health[3].toString());
    }

    public void updateScoreSheet() {
        updateLabels();
    }

    public void setRealm(RedRealm redRealm) {
        this.redRealm = redRealm;
    }

    public void highlightMoves(Move[] moves) {
        for (Move move : moves) {
            if (move.getDice().getRealm() == GameColor.RED) {
                highlightMove(move.getDice());
            }
        }
    }

    private void highlightMove(Dice die) {
        Dragon[] dragons = redRealm.getDragons();
        String style = "-fx-background-color: red;";
        if(((RedDice)die).getDragonNumber()!=0){
            Dragon dragon = dragons[((RedDice)die).getDragonNumber()-1];
            Object[] health = dragon.getHealth();
            for (int j = 0; j < health.length; j++) {
                if (health[j].equals(die.getValue())) {
                    switch (dragon.getDragonNumber()) {
                        case 1: // Dragon 1
                            highlightLabel(j, style, dragon1Face, dragon1Wing, dragon1Tail, dragon1Heart);
                            break;
                        case 2: // Dragon 2
                            highlightLabel(j, style, dragon2Face, dragon2Wing, dragon2Tail, dragon2Heart);
                            break;
                        case 3: // Dragon 3
                            highlightLabel(j, style, dragon3Face, dragon3Wing, dragon3Tail, dragon3Heart);
                            break;
                        case 4: // Dragon 4
                            highlightLabel(j, style, dragon4Face, dragon4Wing, dragon4Tail, dragon4Heart);
                            break;
                        default:
                            break;
                    }
                }
            }
            return;
        }
        for (int i = 0; i < dragons.length; i++) {
            Dragon dragon = dragons[i];
            Object[] health = dragon.getHealth();
            for (int j = 0; j < health.length; j++) {
                if (health[j].equals(die.getValue())) {
                    switch (i) {
                        case 0: // Dragon 1
                            highlightLabel(j, style, dragon1Face, dragon1Wing, dragon1Tail, dragon1Heart);
                            break;
                        case 1: // Dragon 2
                            highlightLabel(j, style, dragon2Face, dragon2Wing, dragon2Tail, dragon2Heart);
                            break;
                        case 2: // Dragon 3
                            highlightLabel(j, style, dragon3Face, dragon3Wing, dragon3Tail, dragon3Heart);
                            break;
                        case 3: // Dragon 4
                            highlightLabel(j, style, dragon4Face, dragon4Wing, dragon4Tail, dragon4Heart);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void highlightLabel(int healthIndex, String style, Label face, Label wing, Label tail, Label heart) {
        switch (healthIndex) {
            case 0:
                face.setStyle(style);
                break;
            case 1:
                wing.setStyle(style);
                break;
            case 2:
                tail.setStyle(style);
                break;
            case 3:
                heart.setStyle(style);
                break;
            default:
                break;
        }
    }

    public void removeHighlight() {
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                highlightCell(i, j, "null");
            }
        }
    }

    private void highlightCell(int row, int column, String color) {
        for (Node node : grid.getChildren()) {
            if (node instanceof Label) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer colIndex = GridPane.getColumnIndex(node);

                if (rowIndex != null && colIndex != null && rowIndex == row && colIndex == column) {
                    if (color == "null") {
                        node.setStyle("");
                        break;
                    }
                    node.setStyle(String.format("-fx-background-color: %s;", color));
                    break;
                }
            }
        }
    }
}



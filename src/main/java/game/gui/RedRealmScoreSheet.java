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

    /**
     * Initializes the RedRealmScoreSheet controller.
     *
     * @param url the location used to resolve relative paths
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Update the labels in the RedRealmScoreSheet based on the current state of the Red Realm.
     * This method retrieves the list of dragons from the Red Realm and updates the corresponding labels for each dragon.
     * It also updates the labels for the collectibles and the dragon scores.
     */
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

    /**
     * Updates the labels displaying the health of a dragon.
     *
     * @param dragon     the Dragon object for which the labels need to be updated
     * @param faceLabel  the Label that displays the face health of the dragon
     * @param wingLabel  the Label that displays the wing health of the dragon
     * @param tailLabel  the Label that displays the tail health of the dragon
     * @param heartLabel the Label that displays the heart health of the dragon
     */
    private void updateDragonLabels(Dragon dragon, Label faceLabel, Label wingLabel, Label tailLabel, Label heartLabel) {
        Object[] health = dragon.getHealth();
        faceLabel.setText(health[0].toString());
        wingLabel.setText(health[1].toString());
        tailLabel.setText(health[2].toString());
        heartLabel.setText(health[3].toString());
    }

    /**
     * Updates the score sheet with the latest values.
     * This method updates the score sheets for different realms and displays the player's total score,
     * total time warp powers collected, total elemental crests collected, and total arcane boost powers collected.
     * It also updates the labels indicating the number of time warps and arcane boosts used.
     */
    public void updateScoreSheet() {
        updateLabels();
    }

    /**
     * Sets the RedRealm for the RedRealmScoreSheet.
     *
     * @param redRealm the RedRealm to set
     */
    public void setRealm(RedRealm redRealm) {
        this.redRealm = redRealm;
    }

    /**
     * Highlights the moves in the specified array that have a dice realm of RED.
     *
     * @param moves an array of Move objects representing the moves to be highlighted
     */
    public void highlightMoves(Move[] moves) {
        for (Move move : moves) {
            if (move.getDice().getRealm() == GameColor.RED) {
                highlightMove(move.getDice());
            }
        }
    }

    /**
     * Highlights the move on the score sheet based on the given dice.
     *
     * @param die The dice representing the move to highlight.
     */
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

    /**
     * Highlights a specific label based on the health index and style.
     *
     * @param healthIndex The index of the label to highlight (0: face, 1: wing, 2: tail, 3: heart)
     * @param style The CSS style to apply to the label
     * @param face The label representing the dragon's face
     * @param wing The label representing the dragon's wing
     * @param tail The label representing the dragon's tail
     * @param heart The label representing the dragon's heart
     */
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

    /**
     * Removes the highlighting from all cells in the score sheets of the realms.
     * This method should be called to remove the highlight after highlighting possible moves.
     */
    public void removeHighlight() {
        for (int i = 0; i < grid.getRowCount(); i++) {
            for (int j = 0; j < grid.getColumnCount(); j++) {
                highlightCell(i, j, "null");
            }
        }
    }

    /**
     * Highlights a specific cell in a grid with the given row and column indices using the specified color.
     *
     * @param row    the row index of the cell to highlight
     * @param column the column index of the cell to highlight
     * @param color  the color to use for highlighting the cell
     */
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



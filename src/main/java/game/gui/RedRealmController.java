package game.gui;
import game.creatures.Dragon;
import game.dice.RedDice;
import game.engine.Move;
import game.engine.Player;
import game.utilities.GameColor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RedRealmController implements Initializable ,RealmController{
    @FXML private Label label;
    @FXML private ImageView BG;
    @FXML private ImageView dragon1;
    @FXML private ImageView dragon2;
    @FXML private ImageView dragon3;
    @FXML private ImageView dragon4;
    @FXML private Label possibleAttackLabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/redRealmBackground.jpg")).toExternalForm());
        Image dragons =new Image(Objects.requireNonNull(getClass().getResource("/images/RedDragon.png")).toExternalForm());
        BG.setImage(mainBG);
        dragon1.setImage(dragons);
        dragon2.setImage(dragons);
        dragon3.setImage(dragons);
        dragon4.setImage(dragons);
    }
    public void attack(int dieValue, int dragonNumber) {
        RedDice die = new RedDice(dieValue);
        die.selectsDragon(dragonNumber);
        Move move = new Move(die, currentPlayer.getRealm(die).getCreature(die));

        if (possibleMoves.contains(move)) {
            sceneManager.closeRealmStage();
            guiGameController.makeMove(currentPlayer, move);
        } else {
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: " + possibleMoves.get(0).getDice().getName());
        }
    }

    public void attckFaceD1() {
        attack(3, 1);
    }

    public void attckWingD1() {
        attack(2, 1);
    }

    public void attckTailD1() {
        attack(1, 1);
    }

    public void attckFaceD2() {
        attack(6, 2);
    }

    public void attckWingD2() {
        attack(1, 2);
    }

    public void attckHeartD2() {
        attack(3, 2);
    }

    public void attckFaceD3() {
        attack(5, 3);
    }

    public void attckTailD3() {
        attack(2, 3);
    }

    public void attckHeartD3() {
        attack(4, 3);
    }

    public void attckWingD4() {
        attack(5, 4);
    }

    public void attckTailD4() {
        attack(4, 4);
    }

    public void attckHeartD4() {
        attack(6, 4);
    }

    public void hoverFaceD1() {
        hover(3, 1);
    }

    public void hoverWingD1() {
        hover(2, 1);
    }

    public void hoverTailD1() {
        hover(1, 1);
    }

    public void hoverFaceD2() {
        hover(6, 2);
    }

    public void hoverWingD2() {
        hover(1, 2);
    }

    public void hoverHeartD2() {
        hover(3, 2);
    }

    public void hoverFaceD3() {
        hover(5, 3);
    }

    public void hoverTailD3() {
        hover(2, 3);
    }

    public void hoverHeartD3() {
        hover(4, 3);
    }

    public void hoverWingD4() {
        hover(5, 4);
    }

    public void hoverTailD4() {
        hover(4, 4);
    }

    public void hoverHeartD4() {
        hover(6, 4);
    }
    public void hover(int attackValue,int dragonNumber){
        RedDice redDice=new RedDice(attackValue);
        redDice.selectsDragon(dragonNumber);
        Move move=new Move(redDice,new Dragon(dragonNumber));
        if(possibleMoves.contains(move)){
            currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
        }

    }
    public void removeHighlight(){
        currentPlayer.getScoreSheetController().removeHighlight();
    }


    private SceneManager sceneManager;
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    private GUIGameController guiGameController;
    public void setGuiGameController(GUIGameController guiGameController){
       this.guiGameController=guiGameController;
    }
    private static Player currentPlayer;
    private static LinkedList<Move> possibleMoves;
    public static void setPossibleMoves(Move[] moves){
        RedRealmController.possibleMoves = new LinkedList<>(Arrays.asList(moves));
    }
    public static void setCurrentPlayer(Player currentPlayer){
        RedRealmController.currentPlayer=currentPlayer;
    }

}

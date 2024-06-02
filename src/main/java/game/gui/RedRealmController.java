package game.gui;
import game.dice.RedDice;
import game.engine.Move;
import game.engine.Player;
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

public class RedRealmController implements Initializable {
    @FXML private Button FaceD1;
    @FXML private Button WingD1;
    @FXML private Button TailD1;
    @FXML private Button FaceD2;
    @FXML private Button WingD2;
    @FXML private Button HeartD2;
    @FXML private Button FaceD3;
    @FXML private Button TailD3;
    @FXML private Button HeartD3;
    @FXML private Button WingD4;
    @FXML private Button TailD4;
    @FXML private Button HeartD4;
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
    public void attckFaceD1() {
        RedDice die=new RedDice(3);
        die.selectsDragon(1);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());
        }
    }
    public void attckWingD1() {
        RedDice die=new RedDice(2);
        die.selectsDragon(1);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckTailD1() {
        RedDice die=new RedDice(1);
        die.selectsDragon(1);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckFaceD2() {
        RedDice die=new RedDice(6);
        die.selectsDragon(2);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckWingD2() {
        RedDice die=new RedDice(1);
        die.selectsDragon(2);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckHeartD2() {
        RedDice die=new RedDice(3);
        die.selectsDragon(2);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckFaceD3() {
        RedDice die=new RedDice(5);
        die.selectsDragon(3);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckTailD3() {
        RedDice die=new RedDice(2);
        die.selectsDragon(3);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckHeartD3() {
        RedDice die=new RedDice(4);
        die.selectsDragon(3);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }
    }
    public void attckWingD4() {
        RedDice die=new RedDice(5);
        die.selectsDragon(4);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());

        }

    }
    public void attckTailD4() {
        RedDice die=new RedDice(4);
        die.selectsDragon(4);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());
        }
    }
    public void attckHeartD4() {
        RedDice die=new RedDice(6);
        die.selectsDragon(4);
        Move move=new Move(die,currentPlayer.getRealm(die).getCreature(die));
        if(possibleMoves.contains(move)){
            sceneManager.closeRedRealmStage();
            guiGameController.makeMove(currentPlayer,move);
        }
        else{
            label.setText("You can't attack this part");
            possibleAttackLabel.setText("Chosen Die: "+possibleMoves.get(0).getDice().getName());
        }
    }
    private static SceneManager sceneManager;
    public static void setSceneManager(SceneManager sceneManager){
        RedRealmController.sceneManager=sceneManager;
    }
    private static GUIGameController guiGameController;
    public static void setGuiGameController(GUIGameController guiGameController){
        RedRealmController.guiGameController=guiGameController;
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

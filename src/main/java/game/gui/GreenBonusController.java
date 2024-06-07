package game.gui;
import game.dice.GreenDice;
import game.engine.Move;
import game.engine.Player;
import game.utilities.GameColor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;
public class GreenBonusController implements  Initializable,RealmController  {
    @FXML private Button Gaia1;
    @FXML private Button Gaia2;
    @FXML private Button Gaia3;
    @FXML private Button Gaia4;
    @FXML private Button Gaia5;
    @FXML private Button Gaia6;
    @FXML private Button Gaia7;
    @FXML private Button Gaia8;
    @FXML private Button Gaia9;
    @FXML private Button Gaia10;
    @FXML private Button Gaia11;
    @FXML private Label label;
    @FXML private ImageView BG;
    @FXML private ImageView ImageButton1;
    @FXML private ImageView ImageButton2;
    @FXML private ImageView ImageButton3;
    @FXML private ImageView ImageButton4;
    @FXML private ImageView ImageButton5;
    @FXML private ImageView ImageButton6;
    @FXML private ImageView ImageButton7;
    @FXML private ImageView ImageButton8;
    @FXML private ImageView ImageButton9;
    @FXML private ImageView ImageButton10;
    @FXML private ImageView ImageButton11;
    @FXML private ImageView GaiaCreature;
    @FXML private Label playerLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/GreenRealmBG.jpeg")).toExternalForm());
        Image buttons =new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/greenButtons.jpeg")).toExternalForm());
        Image creature=new Image(Objects.requireNonNull(getClass().getResource("/images/GaiaGurdian.png")).toExternalForm());
        BG.setImage(mainBG);
        GaiaCreature.setImage(creature);
        ImageButton1.setImage(buttons);
        ImageButton2.setImage(buttons);
        ImageButton3.setImage(buttons);
        ImageButton4.setImage(buttons);
        ImageButton5.setImage(buttons);
        ImageButton6.setImage(buttons);
        ImageButton7.setImage(buttons);
        ImageButton8.setImage(buttons);
        ImageButton9.setImage(buttons);
        ImageButton10.setImage(buttons);
        ImageButton11.setImage(buttons);
        Gaia1.setOnMouseEntered(e -> hoverGaia1());
        Gaia2.setOnMouseEntered(e -> hoverGaia2());
        Gaia3.setOnMouseEntered(e -> hoverGaia3());
        Gaia4.setOnMouseEntered(e -> hoverGaia4());
        Gaia5.setOnMouseEntered(e -> hoverGaia5());
        Gaia6.setOnMouseEntered(e -> hoverGaia6());
        Gaia7.setOnMouseEntered(e -> hoverGaia7());
        Gaia8.setOnMouseEntered(e -> hoverGaia8());
        Gaia9.setOnMouseEntered(e -> hoverGaia9());
        Gaia10.setOnMouseEntered(e -> hoverGaia10());
        Gaia11.setOnMouseEntered(e -> hoverGaia11());
        Gaia1.setOnMouseExited(e -> removeHoverHighlight());
        Gaia2.setOnMouseExited(e -> removeHoverHighlight());
        Gaia3.setOnMouseExited(e -> removeHoverHighlight());
        Gaia4.setOnMouseExited(e -> removeHoverHighlight());
        Gaia5.setOnMouseExited(e -> removeHoverHighlight());
        Gaia6.setOnMouseExited(e -> removeHoverHighlight());
        Gaia7.setOnMouseExited(e -> removeHoverHighlight());
        Gaia8.setOnMouseExited(e -> removeHoverHighlight());
        Gaia9.setOnMouseExited(e -> removeHoverHighlight());
        Gaia10.setOnMouseExited(e -> removeHoverHighlight());
        Gaia11.setOnMouseExited(e -> removeHoverHighlight());

    }
    public void AttackGaia1() {
        Move move = new Move(new GreenDice(2), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(2)));
        performAttack(move, "Gaia 1");
    }
    public void AttackGaia2() {
        Move move = new Move(new GreenDice(3), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(3)));
        performAttack(move, "Gaia 2");
    }

    public void AttackGaia3() {
        Move move = new Move(new GreenDice(4), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(4)));
        performAttack(move, "Gaia 3");
    }

    public void AttackGaia4() {
        Move move = new Move(new GreenDice(5), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(5)));
        performAttack(move, "Gaia 4");
    }

    public void AttackGaia5() {
        Move move = new Move(new GreenDice(6), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(6)));
        performAttack(move, "Gaia 5");
    }

    public void AttackGaia6() {
        Move move = new Move(new GreenDice(7), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(7)));
        performAttack(move, "Gaia 6");
    }

    public void AttackGaia7() {
        Move move = new Move(new GreenDice(8), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(8)));
        performAttack(move, "Gaia 7");
    }

    public void AttackGaia8() {
        Move move = new Move(new GreenDice(9), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(9)));
        performAttack(move, "Gaia 8");
    }

    public void AttackGaia9() {
        Move move = new Move(new GreenDice(10), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(10)));
        performAttack(move, "Gaia 9");
    }

    public void AttackGaia10() {
        Move move = new Move(new GreenDice(11), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(11)));
        performAttack(move, "Gaia 10");
    }

    public void AttackGaia11() {
        Move move = new Move(new GreenDice(12), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(12)));
        performAttack(move, "Gaia 11");
    }

    public void hoverGaia1(){
        Move move = new Move(new GreenDice(2), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(2)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }
    public void hoverGaia2() {
        Move move = new Move(new GreenDice(3), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(3)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia3() {
        Move move = new Move(new GreenDice(4), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(4)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia4() {
        Move move = new Move(new GreenDice(5), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(5)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia5() {
        Move move = new Move(new GreenDice(6), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(6)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia6() {
        Move move = new Move(new GreenDice(7), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(7)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia7() {
        Move move = new Move(new GreenDice(8), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(8)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia8() {
        Move move = new Move(new GreenDice(9), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(9)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia9() {
        Move move = new Move(new GreenDice(10), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(10)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia10() {
        Move move = new Move(new GreenDice(11), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(11)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }

    public void hoverGaia11() {
        Move move = new Move(new GreenDice(12), currentPlayer.getRealm(GameColor.GREEN).getCreature(new GreenDice(12)));
        currentPlayer.getScoreSheetController().highlightPossibleMoves(new Move[]{move});
    }
    public void removeHoverHighlight(){
        currentPlayer.getScoreSheetController().removeHighlight();
    }

    // Common method to perform the attack
    private void performAttack(Move move, String gaiaNumber) {
        if (possibleMoves.contains(move)) {
            Stage stage = (Stage) ImageButton1.getScene().getWindow();
            // Close the stage
            stage.close();
            Platform.runLater(() -> {
                guiGameController.makeMove(currentPlayer,move);
                resetLabels();
            });
        } else {
            label.setText(gaiaNumber + " is Dead");
        }
    }
    private SceneManager sceneManager;
    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    private GUIGameController guiGameController;
    public void setGuiGameController(GUIGameController guiGameController){
        this.guiGameController=guiGameController;
    }
    private static LinkedList<Move> possibleMoves;
    public static void setPossibleMoves(Move[] moves){
        GreenBonusController.possibleMoves = new LinkedList<>(Arrays.asList(moves));
    }
    @Override
    public void resetLabels(){
        playerLabel.setText("");
        label.setText("");
    }
    private static Player currentPlayer;
    public static void setCurrentPlayer(Player currentPlayer){
        GreenBonusController.currentPlayer=currentPlayer;
    }
    public void setLabel(){
        playerLabel.setText(currentPlayer.getName() + ", choose a Gaia to attack!");
    }
}

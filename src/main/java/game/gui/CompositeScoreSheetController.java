package game.gui;

import game.engine.Move;
import game.engine.Player;
import game.realms.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class CompositeScoreSheetController implements Initializable {
    @FXML
    private ImageView bg;
    private YellowRealmScoreSheet yellowRealmScoreSheet;
    private MagentaRealmScoreSheet magentaRealmScoreSheet;
    private BlueRealmScoreSheet blueRealmScoreSheet;
    private GreenRealmScoreSheet greenRealmScoreSheet;
    private RedRealmScoreSheet redRealmScoreSheet;
    @FXML
    private AnchorPane yellowRealm;
    @FXML
    private AnchorPane magentaRealm;
    @FXML
    private AnchorPane redRealm;
    @FXML
    private AnchorPane blueRealm;
    @FXML
    private AnchorPane greenRealm;
    private Realm[] realms;
    @FXML
    private Label playerName;

    @FXML private ImageView playerNameImageView;
    @FXML private ImageView timeWarpImageView;
    @FXML private ImageView arcaneBoostImageView;
    @FXML private ImageView elementalCrestImageView;

    @FXML private Label timeWarpLabel;
    @FXML private Label arcaneBoostLabel;
    @FXML private Label elementalCrestLabel;
    @FXML private Label timeWarpLabelUsed;
    @FXML private Label arcaneBoostLabelUsed;
    @FXML private Label totalScoreLabel;
    private Player player;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            playerNameImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/2.png")).toExternalForm()));
            timeWarpImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/TimeWarp.png")).toExternalForm()));
            arcaneBoostImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ArcaneBoost.png")).toExternalForm()));
            elementalCrestImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ElementalCrest.png")).toExternalForm()));

            FXMLLoader yellowRealmLoader = new FXMLLoader(getClass().getResource("YellowRealmScoreSheet.fxml"));
            AnchorPane yellowRealmScene = yellowRealmLoader.load();
            yellowRealm.getChildren().add(yellowRealmScene);
            yellowRealmScoreSheet =yellowRealmLoader.getController();

            FXMLLoader magentaRealmLoader = new FXMLLoader(getClass().getResource("MagentaRealmScoreSheet.fxml"));
            AnchorPane magentaRealmScene = magentaRealmLoader.load();
            magentaRealm.getChildren().add(magentaRealmScene);
            magentaRealmScoreSheet=magentaRealmLoader.getController();

            FXMLLoader blueRealmLoader = new FXMLLoader(getClass().getResource("BlueRealmScoreSheet.fxml"));
            AnchorPane blueRealmScene = blueRealmLoader.load();
            blueRealm.getChildren().add(blueRealmScene);
            blueRealmScoreSheet =blueRealmLoader.getController();

            FXMLLoader greenRealmLoader = new FXMLLoader(getClass().getResource("GreenRealmScoreSheet.fxml"));
            AnchorPane greenRealmScene = greenRealmLoader.load();
            greenRealm.getChildren().add(greenRealmScene);
            greenRealmScoreSheet = greenRealmLoader.getController();

            FXMLLoader redRealmLoader = new FXMLLoader(getClass().getResource("RedRealmScoreSheet.fxml"));
            AnchorPane redRealmScene = redRealmLoader.load();
            redRealm.getChildren().add(redRealmScene);
            redRealmScoreSheet = redRealmLoader.getController();



        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    public void updateScoreSheet(){
        yellowRealmScoreSheet.updateScoreSheet();
        magentaRealmScoreSheet.updateScoreSheet();
        blueRealmScoreSheet.updateScoreSheet();
        timeWarpLabel.setText(String.valueOf(player.getTotalTimeWarpPowersCollected()));
        elementalCrestLabel.setText(String.valueOf(player.getGameScore().getTotalElementalCrests()));
        arcaneBoostLabel.setText(String.valueOf(player.getTotalArcaneBoostPowersCollected()));
        totalScoreLabel.setText(String.valueOf(player.getGameScore().getTotalScore()));
        timeWarpLabelUsed.setText(player.getTimeWarpsUsed() +"/7");
        arcaneBoostLabelUsed.setText(player.getArcaneBoostsUsed() +"/7");
        greenRealmScoreSheet.updateScoreSheet();
        redRealmScoreSheet.updateScoreSheet();
    }
    public void setPlayer(Player player){
        this.player=player;
        this.playerName.setText(player.toString());
        this.realms=player.getRealms();
        yellowRealmScoreSheet.setRealm((YellowRealm) realms[4]);
        magentaRealmScoreSheet.setRealm((MagentaRealm) realms[3]);
        blueRealmScoreSheet.setRealm((BlueRealm) realms[2]);
        greenRealmScoreSheet.setRealm((GreenRealm) realms[1]);
        redRealmScoreSheet.setRealm((RedRealm) realms[0]);
        updateScoreSheet();
    }
    public void highlightPossibleMoves(Move[] moves){
        yellowRealmScoreSheet.highlightMoves(moves);
        magentaRealmScoreSheet.highlightMoves(moves);
        blueRealmScoreSheet.highlightMoves(moves);
        greenRealmScoreSheet.highlightMoves(moves);
        redRealmScoreSheet.highlightMoves(moves);

    }
    public void removeHighlight(){
        yellowRealmScoreSheet.removeHighlight();
        magentaRealmScoreSheet.removeHighlight();
        blueRealmScoreSheet.removeHighlight();
        greenRealmScoreSheet.removeHighlight();
        redRealmScoreSheet.removeHighlight();
    }
}

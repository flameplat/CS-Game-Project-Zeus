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
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class CompositeScoreSheetController implements Initializable {
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
    @FXML private ImageView playerImageView;

    @FXML private Label timeWarpLabel;
    @FXML private Label arcaneBoostLabel;
    @FXML private Label elementalCrestLabel;
    @FXML private Label timeWarpLabelUsed;
    @FXML private Label arcaneBoostLabelUsed;
    @FXML private Label totalScoreLabel;
    private Player player;
    @FXML private Label rewardsLabel;
    @FXML private ImageView redRealmIcon;
    @FXML private ImageView greenRealmIcon;
    @FXML private ImageView blueRealmIcon;
    @FXML private ImageView magentaRealmIcon;
    @FXML private ImageView yellowRealmIcon;
    /**
     * Initializes the CompositeScoreSheetController.
     *
     * @param url the location of the FXML file that is being initialized
     * @param resourceBundle the resource bundle associated with the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            playerNameImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/2.png")).toExternalForm()));
            timeWarpImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/TimeWarp.png")).toExternalForm()));
            arcaneBoostImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ArcaneBoost.png")).toExternalForm()));
            elementalCrestImageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ElementalCrest.png")).toExternalForm()));
            Rectangle clip = new Rectangle(
                    playerImageView.getFitWidth(), playerImageView.getFitHeight()
            );
            clip.setArcWidth(40);
            clip.setArcHeight(40);
            playerImageView.setClip(clip);
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

            redRealmIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/RedRealm.jpeg")).toExternalForm()));
            greenRealmIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/GreenRealm.png")).toExternalForm()));
            blueRealmIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/BlueRealm.png")).toExternalForm()));
            magentaRealmIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/MagentaRealm.png")).toExternalForm()));
            yellowRealmIcon.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/YellowRealm.png")).toExternalForm()));

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    /**
     * Updates the score sheet with the latest values.
     * This method updates the score sheets for different realms and displays the player's total score,
     * total time warp powers collected, total elemental crests collected, and total arcane boost powers collected.
     * It also updates the labels indicating the number of time warps and arcane boosts used.
     */
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
    /**
     * Sets the player for the CompositeScoreSheetController.
     *
     * @param player the Player object to set
     */
    public void setPlayer(Player player){
        this.player=player;
        this.playerName.setText(player.toString());
        this.realms=player.getRealms();
        yellowRealmScoreSheet.setRealm((YellowRealm) realms[4]);
        magentaRealmScoreSheet.setRealm((MagentaRealm) realms[3]);
        blueRealmScoreSheet.setRealm((BlueRealm) realms[2]);
        greenRealmScoreSheet.setRealm((GreenRealm) realms[1]);
        redRealmScoreSheet.setRealm((RedRealm) realms[0]);
        playerImageView.setImage(player.getWizardImage());
        updateScoreSheet();
    }
    /**
     * Highlights the cells on the yellow, magenta, blue, green, and red realm score sheets
     * based on the given array of moves.
     *
     * @param moves the array of moves
     */
    public void highlightPossibleMoves(Move[] moves){
        yellowRealmScoreSheet.highlightMoves(moves);
        magentaRealmScoreSheet.highlightMoves(moves);
        blueRealmScoreSheet.highlightMoves(moves);
        greenRealmScoreSheet.highlightMoves(moves);
        redRealmScoreSheet.highlightMoves(moves);

    }
    /**
     * Removes the highlighting from all cells in the score sheets of the realms.
     * This method should be called to remove the highlight after highlighting possible moves.
     */
    public void removeHighlight(){
        yellowRealmScoreSheet.removeHighlight();
        magentaRealmScoreSheet.removeHighlight();
        blueRealmScoreSheet.removeHighlight();
        greenRealmScoreSheet.removeHighlight();
        redRealmScoreSheet.removeHighlight();
    }
    /**
     * Sets the text of the rewards label.
     *
     * @param text the text to be set on the rewards label
     */
    public void setRewardsLabel(String text){
        rewardsLabel.setText(text);
    }
}

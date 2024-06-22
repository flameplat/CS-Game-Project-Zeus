package game.gui;

import game.engine.GameScore;
import game.engine.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EndGame implements Initializable,RealmController {
    private SceneManager sceneManager;
    @FXML
    private ImageView background;


    @FXML
    private ImageView player1Wizard;

    @FXML
    private ImageView player2Wizard;

    @FXML
    private ImageView textBoard1;

    @FXML
    private Label player1Label;

    @FXML
    private ImageView player1RedRealm;

    @FXML
    private ImageView player1GreenRealm;

    @FXML
    private ImageView player1BlueRealm;

    @FXML
    private ImageView player1MagentaRealm;

    @FXML
    private ImageView player1YellowRealm;

    @FXML
    private ImageView player1ElementalCrest;

    @FXML
    private Label player1RedScore;

    @FXML
    private Label player1GreenScore;

    @FXML
    private Label player1BlueScore;

    @FXML
    private Label player1MagentaScore;

    @FXML
    private Label player1YellowScore;


    @FXML
    private Label player1TotalScoreLabel;


    @FXML
    private ImageView textBoard2;

    @FXML
    private Label player2Label;

    @FXML
    private ImageView player2RedRealm;

    @FXML
    private ImageView player2GreenRealm;

    @FXML
    private ImageView player2BlueRealm;

    @FXML
    private ImageView player2MagentaRealm;

    @FXML
    private ImageView player2YellowRealm;

    @FXML
    private ImageView player2ElementalCrest;

    @FXML
    private Label player2RedScore;

    @FXML
    private Label player2GreenScore;

    @FXML
    private Label player2BlueScore;

    @FXML
    private Label player2MagentaScore;

    @FXML
    private Label player2YellowScore;

    @FXML
    private Label player2TotalScoreLabel;

    @FXML
    private ImageView button1;

    @FXML
    private ImageView button2;
    @FXML
    private ImageView button3;

    @FXML
    private Label winnerLabel;
    @FXML
    private Label player1RedCrestLabel;

    @FXML
    private Label player1GreenCrestLabel;

    @FXML
    private Label player1BlueCrestLabel;

    @FXML
    private Label player1MagentaCrestLabel;

    @FXML
    private Label player1YellowCrestLabel;

    @FXML
    private Label player2RedCrestLabel;

    @FXML
    private Label player2GreenCrestLabel;

    @FXML
    private Label player2BlueCrestLabel;

    @FXML
    private Label player2MagentaCrestLabel;

    @FXML
    private Label player2YellowCrestLabel;
    @FXML
    private Label gamePhaseLabel;
    @FXML
    private ImageView winnerImageView;
    @FXML
    private Label player1TotalEC;
    @FXML
    private Label player2TotalEC;

    @Override
    public void setGuiGameController(GUIGameController guiGameController) {

    }

    @Override
    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager=sceneManager;
    }

    @Override
    public void resetLabels() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/endGameWizards.png")).toExternalForm()));
        textBoard1.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/textBoard.png")).toExternalForm()));
        textBoard2.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/textBoard.png")).toExternalForm()));
        player1RedRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/RedRealm.jpeg")).toExternalForm()));
        player1GreenRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/GreenRealm.png")).toExternalForm()));
        player1BlueRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/BlueRealm.png")).toExternalForm()));
        player1MagentaRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/MagentaRealm.png")).toExternalForm()));
        player1YellowRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/YellowRealm.png")).toExternalForm()));
        player1ElementalCrest.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ElementalCrest.png")).toExternalForm()));
        player2RedRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/RedRealm.jpeg")).toExternalForm()));
        player2GreenRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/GreenRealm.png")).toExternalForm()));
        player2BlueRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/BlueRealm.png")).toExternalForm()));
        player2MagentaRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/MagentaRealm.png")).toExternalForm()));
        player2YellowRealm.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/realms/YellowRealm.png")).toExternalForm()));
        player2ElementalCrest.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/rewards/ElementalCrest.png")).toExternalForm()));
        button1.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
        button2.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm()));
        button3.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm()));

        Rectangle clip = new Rectangle(
                player1Wizard.getFitWidth(), player1Wizard.getFitHeight()
        );
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        player1Wizard.setClip(clip);
        Rectangle clip2 = new Rectangle(
                player2Wizard.getFitWidth(), player2Wizard.getFitHeight()
        );
        clip2.setArcWidth(40);
        clip2.setArcHeight(40);
        player2Wizard.setClip(clip2);


    }
    /**
     * Updates the end game details based on the current player information.
     * If both player1 and player are not null, it sets the players and updates the corresponding labels for both players.
     */
    public void updateEndGame(){
        if(player1!=null && player2!=null){
            setPlayers(player1,player2);
        }
    }
    /**
     * Represents a player in the game.
     */
    private Player player1;
    /**
     * Represents a player in the game.
     */
    private Player player2;

    /**
     * Sets the players and updates the GUI with their information.
     *
     * @param player1 The first player.
     * @param player2 The second player.
     */
    public void setPlayers(Player player1,Player player2){
        this.player1=player1;
        this.player2=player2;
        GameScore gameScorePlayer1 = player1.getGameScore();
        player1Wizard.setImage(player1.getWizardImage());
        player1Label.setText(player1.getName());
        player1RedScore.setText(String.valueOf(gameScorePlayer1.getRedRealmScore()));
        player1GreenScore.setText(String.valueOf(gameScorePlayer1.getGreenRealmScore()));
        player1BlueScore.setText(String.valueOf(gameScorePlayer1.getBlueRealmScore()));
        player1MagentaScore.setText(String.valueOf(gameScorePlayer1.getMagentaRealmScore()));
        player1YellowScore.setText(String.valueOf(gameScorePlayer1.getYellowRealmScore()));
        player1TotalScoreLabel.setText(String.valueOf(gameScorePlayer1.getTotalScore()));

        player1RedCrestLabel.setText(String.valueOf(player1.getRealms()[0].getNoElementalCrests()));
        player1GreenCrestLabel.setText(String.valueOf(player1.getRealms()[1].getNoElementalCrests()));
        player1BlueCrestLabel.setText(String.valueOf(player1.getRealms()[2].getNoElementalCrests()));
        player1MagentaCrestLabel.setText(String.valueOf(player1.getRealms()[3].getNoElementalCrests()));
        player1YellowCrestLabel.setText(String.valueOf(player1.getRealms()[4].getNoElementalCrests()));

        GameScore gameScorePlayer2 = player2.getGameScore();
        player2Wizard.setImage(player2.getWizardImage());
        player2Label.setText(player2.getName());
        player2RedScore.setText(String.valueOf(gameScorePlayer2.getRedRealmScore()));
        player2GreenScore.setText(String.valueOf(gameScorePlayer2.getGreenRealmScore()));
        player2BlueScore.setText(String.valueOf(gameScorePlayer2.getBlueRealmScore()));
        player2MagentaScore.setText(String.valueOf(gameScorePlayer2.getMagentaRealmScore()));
        player2YellowScore.setText(String.valueOf(gameScorePlayer2.getYellowRealmScore()));
        player2TotalScoreLabel.setText(String.valueOf(gameScorePlayer2.getTotalScore()));
        player1TotalEC.setText(String.valueOf(gameScorePlayer1.getTotalElementalCrests()));

        player2RedCrestLabel.setText(String.valueOf(player2.getRealms()[0].getNoElementalCrests()));
        player2GreenCrestLabel.setText(String.valueOf(player2.getRealms()[1].getNoElementalCrests()));
        player2BlueCrestLabel.setText(String.valueOf(player2.getRealms()[2].getNoElementalCrests()));
        player2MagentaCrestLabel.setText(String.valueOf(player2.getRealms()[3].getNoElementalCrests()));
        player2YellowCrestLabel.setText(String.valueOf(player2.getRealms()[4].getNoElementalCrests()));
        player2TotalEC.setText(String.valueOf(gameScorePlayer2.getTotalElementalCrests()));
        if(player1.getGameScore().getTotalScore()==player2.getGameScore().getTotalScore()){
            gamePhaseLabel.setText("Draw!");
            return;
        }
        Player winnerPlayer=player1.getGameScore().getTotalScore()>player2.getGameScore().getTotalScore()? player1:player2;
        winnerLabel.setText(winnerPlayer.getName());
        winnerImageView.setImage(winnerPlayer.getWizardImage());
        Rectangle clip3 = new Rectangle(
                winnerImageView.getFitWidth(), winnerImageView.getFitHeight()
        );
        clip3.setArcWidth(40);
        clip3.setArcHeight(40);
        winnerImageView.setClip(clip3);

    }

}

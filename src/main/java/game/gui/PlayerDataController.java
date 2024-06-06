package game.gui;

import game.engine.AIPlayer;
import game.engine.GameMode;
import game.engine.Player;
import game.exceptions.InvalidPlayerNameException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerDataController implements Initializable,GameController {
    @FXML
    private ImageView bg;
    @FXML
    private ImageView button1;
    @FXML
    private Label mainLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField textField;
    private static int playersSubmitted=0;
    private SceneManager sceneManager;
    private static Player player1;
    private static Player player2;
    @FXML
    private Label submitLabel;



    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image mainBG=new Image(Objects.requireNonNull(getClass().getResource("/images/Wizards.jpeg")).toExternalForm());
        Image button=new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/1.png")).toExternalForm());
        bg.setImage(mainBG);
        button1.setImage(button);
        mainLabel.setText("Enter Player 1 Name");
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResource("/images/buttons/3.png")).toExternalForm()));
        addHoverEffect(button1);
        submitLabel.setMouseTransparent(true);

    }
    private void addHoverEffect(ImageView imageView) {
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.CYAN);
        shadow.setRadius(10);

        imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> imageView.setEffect(shadow));
        imageView.addEventHandler(MouseEvent.MOUSE_EXITED, e -> imageView.setEffect(null));
    }

    public void setSceneManager(SceneManager sceneManager){
        this.sceneManager=sceneManager;
    }
    public void setPlayerName() {
        try {
            if (playersSubmitted < 1) {
                player1= new Player(textField.getText());
                errorLabel.setText("");
                if(MainMenuController.getGameMode()== GameMode.SINGLEPLAYER){
                    player2=new AIPlayer("Zeus");
                    sceneManager.switchWizardsScene();
                    return;
                }
                mainLabel.setText("Enter Player 2 Name");
                textField.setText("");
                playersSubmitted++;
            } else {

                if (player1 != null && textField.getText().equals(player1.getName())) {
                    throw new InvalidPlayerNameException("Name already in use!");
                }
                int maxLen=20;
                if(textField.getText().length()>maxLen){
                    throw new InvalidPlayerNameException("Name is too long. Max: "+maxLen+" characters");
                }
                player2= new Player(textField.getText());
                errorLabel.setText("");
                playersSubmitted++;
                sceneManager.switchWizardsScene();
            }
        } catch (InvalidPlayerNameException e) {
            errorLabel.setText(e.getMessage());
        }

    }
    public static Player getPlayer1(){
        return player1;
    }
    public static Player getPlayer2(){
        return player2;
    }

}

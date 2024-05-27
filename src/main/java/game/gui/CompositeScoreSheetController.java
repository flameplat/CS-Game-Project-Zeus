package game.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CompositeScoreSheetController implements Initializable {
    @FXML
    private ImageView bg;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image sc=new Image(Objects.requireNonNull(getClass().getResource("/images/Wizards.jpeg")).toExternalForm());
        bg.setImage(sc);
    }
}

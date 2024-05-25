package game.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class DiceRealms extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scene1.fxml")));
        Scene scene=new Scene(root,800,600);
        Image icon=new Image(Objects.requireNonNull(getClass().getResource("/images/icon.png")).toExternalForm());
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Dice Realms");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

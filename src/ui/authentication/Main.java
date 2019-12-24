package ui.authentication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**
 *
 * To see beyond authentication ui, username: admin password: 123 role: receptionist.
 * Logout button works.
 * Add Patient button works.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
      /**  Media media = new Media("https://file-examples.com/wp-content/uploads/2017/11/file_example_MP3_700KB.mp3");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaPlayer.setAutoPlay(true);*/
        Parent root = FXMLLoader.load(getClass().getResource("authentication.fxml"));
        primaryStage.setTitle("P-Track: Patient Management System");
        Image icon = new Image("ui/icons/hospital.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 683, 390));


        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

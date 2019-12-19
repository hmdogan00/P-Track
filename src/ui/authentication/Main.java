package ui.authentication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("authentication.fxml"));
        primaryStage.setTitle("P-Track: Patient Management System");
        Image icon = new Image("ui/icons/hospital.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 665, 381));


        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

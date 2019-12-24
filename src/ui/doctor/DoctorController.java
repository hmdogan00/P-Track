package ui.doctor;

import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML
    Button logoutButton;
    @FXML
    private Label doctorUserName;
    @FXML
    private Label doctorRoom;
    @FXML
    private void logoutDoctor(ActionEvent e) throws IOException{
        System.out.println("Logged out from Doctor panel!");

        //back to auth scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/authentication/authentication.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.setResizable(false);
        app_stage.show();
    }

    private void getUpcomingPatient(){
//
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            doctorUserName.setText(Database.getUserName());
            doctorRoom.setText(Database.doctorDetails(Database.findDoctorKey(Database.getUserName())).get(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

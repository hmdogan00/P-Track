package ui.doctor;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ui.MasterController;
import ui.receptionist.DoctorTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorController extends MasterController implements Initializable {

    @FXML
    Button logoutButton;
    @FXML
    private Label doctorUserName;
    @FXML
    private Label doctorRoom;
    @FXML
    private Button filterButton;
    @FXML
    private TextField filterDoctorName;
    @FXML
    private TableView<DoctorTable> doctorTable;
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
    @FXML
    private void getFilteredData(){
        ObservableList<DoctorTable> listFiltered2 = FXCollections.observableArrayList();
        try{
            Connection con = Database.connection();
            String nameFilter = filterDoctorName.getText();
            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM doctor WHERE name LIKE '%" + nameFilter + "%' ");

            while (rs2.next()) {
                listFiltered2.add(new DoctorTable(rs2.getString("name"), rs2.getString("department"),
                        rs2.getString("room_number") ));
            }
        }catch (SQLException ex){}

        doctorTable.setItems(listFiltered2);
    }
    private void getUpcomingPatient(){

    }

    private void getPatientData(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            doctorUserName.setText(Database.getUserName());
            doctorRoom.setText("Room No:" + Database.doctorDetails(Database.findDoctorKey(Database.getUserName())).get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

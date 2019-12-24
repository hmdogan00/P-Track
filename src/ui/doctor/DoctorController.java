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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ui.receptionist.ModelTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML
    private Button logoutButton;

    //Upcoming Patient Table
    @FXML
    private TableView<UpcomingTable> upcomingPatientTable;

    @FXML
    private TableColumn<UpcomingTable, String> colUpName;

    @FXML
    private TableColumn<UpcomingTable, String> colLastApp;

    @FXML
    private TableColumn<UpcomingTable, String> colPhoneNo;

    @FXML
    private TableColumn<UpcomingTable, String> colAddPrescription;


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

    private void getUpcomingPatient() throws SQLException {
        ObservableList<UpcomingTable> obList = FXCollections.observableArrayList();
        int doctor_key = Database.findDoctorKey(Database.getUserName());
        try {
            Connection con = Database.connection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM appointment WHERE doctor_id = '" + doctor_key + "' ORDER BY time ASC");

            while (rs.next()) {
                obList.add(new UpcomingTable(rs.getString("name"), rs.getString("last_appointment"),
                        rs.getString("phone_no")));
            }
        }catch (SQLException ex){}

        colUpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastApp.setCellValueFactory(new PropertyValueFactory<>("lastAppointment"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        upcomingPatientTable.setItems(obList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

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
import javafx.util.Callback;
import ui.MasterController;
import ui.receptionist.DoctorTable;
import ui.receptionist.ModelTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private TableView<UpcomingTable> upcomingTable;

    @FXML
    private TableColumn<UpcomingTable, String> colName, colLastAppointment, colPhoneNo, colAddPrescription;


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

    }

    private void getPatientData() throws SQLException {
        /*int doctorId = Database.findDoctorKey(Database.getUserName());
        ArrayList doctorsAppointedPatients = Database.doctorsAppointedPatients(doctorId);
        ArrayList doctorAppointments = Database.doctorAppointments(doctorId);
        ObservableList<UpcomingTable> obList3 = FXCollections.observableArrayList();

        for(int i = 0; i < 5; i++) {
            obList3.add(new UpcomingTable("a","b","c"));
        }

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastAppointment.setCellValueFactory(new PropertyValueFactory<>("lastAppointment"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        upcomingTable.setItems(obList3);*/
        ObservableList<UpcomingTable> obList3 = FXCollections.observableArrayList();
        try {
            Connection con = Database.connection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient");

            while (rs.next()) {
                obList3.add(new UpcomingTable( rs.getString("name"), "b", "c"));
            }
        }catch (SQLException ex){}

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLastAppointment.setCellValueFactory(new PropertyValueFactory<>("lastAppointment"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        upcomingTable.setItems(obList3);


        Callback<TableColumn<UpcomingTable, String>,TableCell<UpcomingTable, String>> cellFactory = (param) -> {
            //make table cell with button
            final TableCell<UpcomingTable, String> cell = new TableCell<UpcomingTable, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button addAppointmentButton = new Button("Set Prescription");
                        addAppointmentButton.setOnAction(event -> {
                            loadWindow("ui/doctor/FXML/doctorPrescriptionPage.fxml", "Set Prescription");
                        });
                        setGraphic(addAppointmentButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        colAddPrescription.setCellFactory(cellFactory);
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

package ui.receptionist.controllers;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.MasterController;
import ui.receptionist.DocAppointmentTable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DoctorAppointmentsController extends MasterController implements Initializable {
    @FXML
    private Label doctorUserName,doctorRoom;

    @FXML
    private TextField filterDoctorName;

    @FXML
    private TableView<DocAppointmentTable> docAppointmentTable;

    @FXML
    private TableColumn<DocAppointmentTable, String> colName, colAppDate, colAppTime, colPhoneNo, colAddPrescription;

    private void getPatientData() throws SQLException {
        int doctorId = Database.findDoctorKey(Database.getUserName());
        ObservableList<DocAppointmentTable> obList4 = FXCollections.observableArrayList();
        try {
            ResultSet rs = myConn.createStatement().executeQuery("SELECT patient.`name`, appointment.`date`, appointment.`time`, patient.`patient_phoneNumber` FROM patient, appointment, doctor WHERE appointment.`doctor_id` = doctor.`doctor_id` AND appointment.`patient_id` = patient.`patient_id` AND doctor.`doctor_id`= '" + doctorId + "' AND appointment.`date` >= '"+ Database.date() +"' ORDER BY `date` DESC");

            while (rs.next()) {
                obList4.add(new DocAppointmentTable(rs.getString("name"), rs.getString("date"),
                        rs.getString("time"), rs.getString("patient_phoneNumber")));
            }
        }catch (SQLException ex){}

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAppDate.setCellValueFactory(new PropertyValueFactory<>("appDate"));
        colAppTime.setCellValueFactory(new PropertyValueFactory<>("appTime"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        docAppointmentTable.setItems(obList4);
    }

    private void getFilteredPatientDataByOrder() throws SQLException {
        int doctorId = Database.findDoctorKey(Database.getUserName());
        String patientNameFilter = filterDoctorName.getText();
        ObservableList<DocAppointmentTable> obList3 = FXCollections.observableArrayList();
        try {
            Connection con = Database.myConn;
            ResultSet rs = con.createStatement().executeQuery("SELECT patient.`name`, appointment.`date`, appointment.`time`, patient.`patient_phoneNumber` FROM patient, appointment, doctor WHERE appointment.`doctor_id` = doctor.`doctor_id` AND appointment.`patient_id` = patient.`patient_id` AND doctor.`doctor_id`= '" + doctorId + "' AND appointment.`date` >= '"+ Database.date() +"' AND patient.`name` LIKE '%" + patientNameFilter + "%' ORDER BY `date` DESC ");

            while (rs.next()) {
                obList3.add(new DocAppointmentTable(rs.getString("name"), rs.getString("date"),
                        rs.getString("time"), rs.getString("patient_phoneNumber")));
            }
        }catch (SQLException ex){}

        docAppointmentTable.setItems(obList3);
    }

    @FXML
    private void findUpcomingPatientFromList(ActionEvent e) throws SQLException {
        if (filterDoctorName.getText().equals("")) {
            getPatientData();
            System.out.println("Empty filter");
        }
        else
            getFilteredPatientDataByOrder();
        docAppointmentTable.refresh();
    }

    /**
     * initializes the page and setting labels information according to user
     * @param location the location of that page
     * @param resources sources for that page
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            doctorUserName.setText(Database.getUserName());
            doctorRoom.setText("Room No:" + Database.doctorDetails(Database.findDoctorKey(Database.getUserName())).get(2));
            getPatientData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

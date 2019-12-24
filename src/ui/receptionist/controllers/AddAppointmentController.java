package ui.receptionist.controllers;

import com.jfoenix.controls.JFXTimePicker;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddAppointmentController {

    ObservableList<String> hourList = FXCollections.observableArrayList("08","09","10","11","12","13","14","15","16","17");
    ObservableList<String> minuteList = FXCollections.observableArrayList("00","15","30","45");

    @FXML
    TextField patientName;
    @FXML
    TextField patientSurname;
    @FXML
    TextField doctorName;
    @FXML
    TextField doctorSurname;
    @FXML
    DatePicker appointmentDate;
    @FXML
    ChoiceBox hourChoice;
    @FXML
    ChoiceBox minuteChoice;
    @FXML
    Button saveAppointmentButton;

    public String dateValue(){
        String date = String.valueOf(appointmentDate.getValue());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        String result = year + "-" + month + "-" + day;
        System.out.println(result);
        return result;
    }

    @FXML
    private void initialize(){
        hourChoice.setItems(hourList);
        minuteChoice.setItems(minuteList);
    }
    @FXML public void saveAppointment(ActionEvent e) throws SQLException
    {
        String patName = patientName.getText() + " " + patientSurname.getText();
        int patientId = Database.findPatientKey( patName );
        String docName = doctorName.getText() + " " + doctorSurname.getText();
        int docId = Database.findDoctorKey( docName );
        if ( Database.doctorAvailability( docId ))
        {
            boolean trial = Database.addAppointment(patientId, docId, dateValue(), "12:45" );
            if (trial)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Appointment added successfully.");
                alert.show();
                Stage stage = (Stage) saveAppointmentButton.getScene().getWindow(); stage.close();
            }
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("A given info is wrong.");
                    alert.show();
                }
        }
        else
            { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Doctor is not available at given time. Please choose a different time.");
            alert.show();
            }
    }
}
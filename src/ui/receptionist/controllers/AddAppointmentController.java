package ui.receptionist.controllers;

import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;

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

        return day+"/"+ month+"/"+ year;
    }

   @FXML
   private void initialize(){
        hourChoice.setItems(hourList);
        minuteChoice.setItems(minuteList);
   }
    @FXML
    public void saveAppointment(ActionEvent e){
/*
        /// if doctor is available
        if ( classes.Receptionist.addAppointment(patient,doctor,cost,time) > 0 )
        {
 System.out.println

        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Doctor is unavailable\n Please select another doctor",ButtonType.OK);
            alert.showAndWait();
        }*/
    }
}

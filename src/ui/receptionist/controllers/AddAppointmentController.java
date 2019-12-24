package ui.receptionist.controllers;

import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;

public class AddAppointmentController {

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
    JFXTimePicker appointmentTime;
    @FXML
    Button saveAppointmentButton;

    public String dateValue(){
        String date = String.valueOf(appointmentDate.getValue());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);

        return day+"/"+ month+"/"+ year;
    }

    public String timeValue(){
        String time = String.valueOf(appointmentTime.getValue() + " ");
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);

        return hour + "/" + minute;
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

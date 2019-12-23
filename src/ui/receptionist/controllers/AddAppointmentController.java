package ui.receptionist.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.awt.event.*;

public class AddAppointmentController {

    public AddAppointmentController(){}

    @FXML
    TextField patientName;
    @FXML
    TextField patientSurname;
    @FXML
    TextField doctorName;
    @FXML
    TextField doctorSurname;
    @FXML
    TextField appointmentDate;
    @FXML
    TextField appointmentTime;
    @FXML
    Button saveAppointmentButton;

   /** private void saveAppointment(ActionEvent){

        /// if doctor is available
        if ( classes.Receptionist.addAppointment(patient,doctor,cost,time) > 0 )
        {

        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Doctor is unavailable\n Please select another doctor",ButtonType.OK);
            alert.showAndWait();
        }

    }**/
}

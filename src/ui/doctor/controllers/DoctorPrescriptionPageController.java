package ui.doctor.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;
import java.io.IOException;

public class DoctorPrescriptionPageController {

    @FXML
    Label doctorName;
    @FXML
    Label patientName;
    @FXML
    TextArea addPrescription;
    @FXML
    Button printPrescription;
    @FXML
    Button savePrescription;
    @FXML
    Button backToDoctorPage;

    public DoctorPrescriptionPageController(){}

    @FXML
    public void handleSavePrescription(ActionEvent e){

    }

    @FXML
    public void handleBackToDoctorPage(ActionEvent e){
        Stage stage = (Stage) backToDoctorPage.getScene().getWindow();
        stage.close();
    }

}

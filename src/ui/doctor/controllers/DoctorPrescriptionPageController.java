/*
 * class which controls the prescription page for doctors
 */
package ui.doctor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;

public class DoctorPrescriptionPageController {

    @FXML
    private Label doctorName,patientName;

    @FXML
    private TextArea addPrescription;

    @FXML
    private Button printPrescription, backToDoctorPage;

    /**
     * default constructor
     */
    public DoctorPrescriptionPageController(){}

    /**
     * goes back to the doctor page from prescription page
     * @param e gives action the back button
     */
    @FXML
    public void handleBackToDoctorPage(ActionEvent e){
        Stage stage = (Stage) backToDoctorPage.getScene().getWindow();
        stage.close();
    }

}

package ui.receptionist.controllers;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;

public class ChangePatientInfoController {

    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientId;
    @FXML
    private Label patientGender;
    @FXML
    private Label patientBirthdate;
    @FXML
    private Label bloodType;
    @FXML
    private TextField changeAddress;
    @FXML
    private TextField changeCity;
    @FXML
    private TextField changePatientPhone;
    @FXML
    private MenuButton changeInsurance;
    @FXML
    private TextField changeEmergencyName;
    @FXML
    private TextField changeEmergencySurname;
    @FXML
    private TextField changeEmergencyPhone;
    @FXML
    private Button saveButton;

    private int insuranceChooser = 0;

    public ChangePatientInfoController(String patientName) throws SQLException{
        int patientKey = database.Database.findPatientKey(patientName);
        ArrayList<String> infoList = database.Database.patientDetails(patientKey);
        patientNameLabel.setText(infoList.get(0));
        patientId.setText(infoList.get(1));
        patientGender.setText(infoList.get(2));
        patientBirthdate.setText(infoList.get(3));
        bloodType.setText(infoList.get(4));
    }
    @FXML
    private void sgkChoice(ActionEvent e) {
        changeInsurance.setText("SGK");
        insuranceChooser = 1;
    }

    @FXML
    private void bagkurChoice(ActionEvent e) {
        changeInsurance.setText("BAGKUR");
        insuranceChooser = 2;
    }

    @FXML
    private void privateChoice(ActionEvent e) {
        changeInsurance.setText("PRIVATE");
        insuranceChooser = 3;
    }

    @FXML private void savePatientInfo(ActionEvent e) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
        }
    }
}

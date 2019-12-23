package ui.receptionist.controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.awt.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import ui.receptionist.ModelTable;
import ui.receptionist.ReceptionistController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class PatientDetailsController implements Initializable {

    public PatientDetailsController() {


    }

    public void update( String patientName ) throws SQLException {
        int patientKey = database.Database.findPatientKey(patientName);
        ArrayList<String> infoList = database.Database.patientDetails(patientKey);
        System.out.println(infoList.toString());
        detailedNameLabel.setText(infoList.get(0));
        detailedIDLabel.setText(infoList.get(1));
        detailedGenderLabel.setText(infoList.get(2));
        detailedBirthDateLabel.setText(infoList.get(3));
        detailedBloodTypeLabel.setText(infoList.get(4));
        detailedAddressLabel.setText(infoList.get(5));
        detailedCityLabel.setText(infoList.get(6));
        detailedPhoneNumber.setText(infoList.get(7));
        detailedInsurance.setText(infoList.get(8));
        detailedEmergencyContactName.setText(infoList.get(9));
        detailedEmergencyContactPhoneNumber.setText(infoList.get(10));
    }

    public void setDetailedNameLabel(Label detailedNameLabel) {
        this.detailedNameLabel = detailedNameLabel;
    }

    public void setDetailedIDLabel(Label detailedIDLabel) {
        this.detailedIDLabel = detailedIDLabel;
    }

    public void setDetailedGenderLabel(Label detailedGenderLabel) {
        this.detailedGenderLabel = detailedGenderLabel;
    }

    public void setDetailedBloodTypeLabel(Label detailedBloodTypeLabel) {
        this.detailedBloodTypeLabel = detailedBloodTypeLabel;
    }

    public void setDetailedBirthDateLabel(Label detailedBirthDateLabel) {
        this.detailedBirthDateLabel = detailedBirthDateLabel;
    }

    public void setDetailedAddressLabel(Label detailedAddressLabel) {
        this.detailedAddressLabel = detailedAddressLabel;
    }

    public void setDetailedCityLabel(Label detailedCityLabel) {
        this.detailedCityLabel = detailedCityLabel;
    }

    public void setDetailedPhoneNumber(Label detailedPhoneNumber) {
        this.detailedPhoneNumber = detailedPhoneNumber;
    }

    public void setDetailedInsurance(Label detailedInsurance) {
        this.detailedInsurance = detailedInsurance;
    }

    public void setDetailedEmergencyContactName(Label detailedEmergencyContactName) {
        this.detailedEmergencyContactName = detailedEmergencyContactName;
    }

    public void setDetailedEmergencyContactPhoneNumber(Label detailedEmergencyContactPhoneNumber) {
        this.detailedEmergencyContactPhoneNumber = detailedEmergencyContactPhoneNumber;
    }

    @FXML
    private Label detailedNameLabel;
    @FXML
    private Label detailedIDLabel;
    @FXML
    private Label detailedGenderLabel;
    @FXML
    private Label detailedBloodTypeLabel;
    @FXML
    private Label detailedBirthDateLabel;
    @FXML
    private Label detailedAddressLabel;
    @FXML
    private Label detailedCityLabel;
    @FXML
    private Label detailedPhoneNumber;
    @FXML
    private Label detailedInsurance;
    @FXML
    private Label detailedEmergencyContactName;
    @FXML
    private Label detailedEmergencyContactPhoneNumber;
    @FXML
    private Button backButton;

    @FXML private void pageClose(ActionEvent c)
    {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        try {
            update("Hami Mert Doğan");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

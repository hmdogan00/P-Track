package ui.receptionist.controllers;
import com.jfoenix.transitions.template.JFXAnimationTemplateAction;
import database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;

public class ChangePatientInfoController implements Initializable {

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

    public ChangePatientInfoController(){}
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

    @FXML
    private void savePatientInfo(ActionEvent e) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            File file = null;
            Scanner scan = null;
            try {
                file = new File("outFile.txt");
                String path = file.getAbsolutePath();
                scan = new Scanner(file);
            } catch (FileNotFoundException f) {
                Alert fileAlert = new Alert(Alert.AlertType.INFORMATION);
                fileAlert.setContentText("Could not access file. Please try again.");
                fileAlert.show();
            }
            String id = scan.next();
            scan.close();

            int patientKey = database.Database.findPatientKey(id);
            //updating patient from database
            Database.updatePatient( patientKey, changeAddress.getText(), Integer.parseInt(changePatientPhone.getText()), changeInsurance.getText(), changeEmergencyName.getText(), Integer.parseInt(changeEmergencyPhone.getText()) );
        }
    }

    public void update( ) throws SQLException {
        File file = null;
        Scanner scan = null;
        try {
            file = new File("outFile.txt");
            String path = file.getAbsolutePath();
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Could not access file. Please try again.");
            alert.show();
        }
        String id = scan.next();
        scan.close();

        int patientKey = database.Database.findPatientKey(id);
        ArrayList<String> infoList = database.Database.patientDetails(patientKey);
        System.out.println(infoList.toString());
        patientNameLabel.setText(infoList.get(0));
        patientId.setText(infoList.get(1));
        patientGender.setText(infoList.get(2));
        patientBirthdate.setText(infoList.get(4));
        bloodType.setText(infoList.get(3));
        changeAddress.setText(infoList.get(5));
        changePatientPhone.setText(infoList.get(7));
        changeInsurance.setText(infoList.get(6));
        changeEmergencyName.setText(infoList.get(8));
        changeEmergencyPhone.setText(infoList.get(9));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

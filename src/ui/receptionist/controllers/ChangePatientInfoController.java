package ui.receptionist.controllers;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
public class ChangePatientInfoController {
    public ChangePatientInfoController(String patientName){

    }

    @FXML
    private Label patientName;
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
    private TextField changeInsurance;
    @FXML
    private TextField changeEmergencyName;
    @FXML
    private TextField changeEmergencyPhone;
    @FXML
    private Button backButton;

    @FXML private void pageClose(ActionEvent c)
    {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }
}

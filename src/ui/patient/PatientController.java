package ui.patient;

import com.jfoenix.transitions.template.JFXAnimationTemplateAction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import ui.MasterController;

public class PatientController extends MasterController implements Initializable {

    @FXML
    Button patientLogOutButton;
    @FXML
    Label patientNameLabel;
    @FXML
    Label patientID;
    @FXML
    Label patientGender;
    @FXML
    Label patientBloodType;
    @FXML
    Label patientAddress;
    @FXML
    Label patientPhoneNumber;
    @FXML
    Label topBarPatientName;
    @FXML
    TableView appointmentsView;

    public PatientController(){}

    @FXML
    private void patientLogOut(ActionEvent a) throws IOException {
        System.out.println("Logged out from Patient panel!");
        // System.out.println();

        //back to auth scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/authentication/authentication.fxml"));
        System.out.println( loader.getLocation() );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) a.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.setResizable(false);
        app_stage.show();
    }

    public void update() throws SQLException{
        int patientKey = database.Database.findPatientKey("0");
        ArrayList<String> infoList = database.Database.patientDetails(patientKey);
        topBarPatientName.setText(infoList.get(0));
        patientNameLabel.setText(infoList.get(0));
        patientID.setText(infoList.get(1));
        patientGender.setText(infoList.get(2));
        patientBloodType.setText(infoList.get(4));
        patientAddress.setText(infoList.get(5) + "/" + infoList.get(6));
        patientPhoneNumber.setText(infoList.get(7));
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

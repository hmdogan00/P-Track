/**
 * this control class shows patient scene
 */
package ui.patient;

import com.jfoenix.transitions.template.JFXAnimationTemplateAction;
import database.Database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import ui.MasterController;

public class PatientController extends MasterController implements Initializable {

    @FXML
    private Button patientLogOutButton;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label patientID;

    @FXML
    private Label patientGender;

    @FXML
    private Label patientBloodType;

    @FXML
    private TextArea patientAddress;

    @FXML
    private Label patientInsurance;

    @FXML
    private Label patientPhoneNumber;

    @FXML
    private Label topBarPatientName;

    @FXML
    private TableView appointmentsView;

    /**
     * a constructor creates new patient controller class
     */
    public PatientController(){}

    /**
     * a method which listens the log out button from patient scene
     * @param a the action which is pressing log out button
     * @throws IOException
     */
    @FXML
    private void patientLogOut(ActionEvent a) throws IOException {
        // controling the method
        System.out.println("Logged out from Patient panel!");

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

    /**
     * updates the labels of the patient scene according to that specific patient
     * @throws SQLException
     */
    private void update() throws SQLException{
        // finding patient key
        int patientKey = database.Database.findPatientKey(Database.getUserName());

        // adding the details of patient to a string infoList
        ArrayList<String> infoList = database.Database.patientDetails(patientKey);

        // setting these details to labels
        topBarPatientName.setText(infoList.get(0));
        patientNameLabel.setText(infoList.get(0));
        patientID.setText(infoList.get(1));
        patientGender.setText(infoList.get(2));
        patientBloodType.setText(infoList.get(3));
        patientAddress.setText(infoList.get(5));
        patientInsurance.setText(infoList.get(6));
        patientPhoneNumber.setText(infoList.get(7));
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

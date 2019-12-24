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
    public void handleDoctorLogout(ActionEvent e) throws IOException {
        System.out.println("Logged out from Doctor panel!");

        //back to auth scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/authentication/authentication.fxml"));
        System.out.println( loader.getLocation() );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.setResizable(false);
        app_stage.show();


    }

    @FXML
    public void handleSavePrescription(ActionEvent e){

    }

    @FXML
    public void handleBackToDoctorPage(ActionEvent e){
        Stage stage = (Stage) backToDoctorPage.getScene().getWindow();
        stage.close();
    }

}

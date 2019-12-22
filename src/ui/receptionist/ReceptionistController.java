package ui.receptionist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.authentication.Main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistController implements Initializable {
    //Dashboard
    @FXML
    private Label receptionistUsernameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Label numberOfAppointmentsLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextArea noteTextArea;

    //Recent Patient 1
    @FXML
    private Label recentPatientLabel1;

    @FXML
    private Label idNoLabel1;

    @FXML
    private Label passedTimeLabel1;

    @FXML
    private Label passedDateLabel1;

    @FXML
    private Label phoneNumberLabel1;

    @FXML
    private Label doctorName1;

    @FXML
    private Label departmentName1;

    @FXML
    private Button detailsButton1;

    @FXML
    private Button registerButton1;

    //Recent Patient 2
    @FXML
    private Label recentPatientLabel2;

    @FXML
    private Label idNoLabel2;

    @FXML
    private Label passedTimeLabel2;

    @FXML
    private Label passedDateLabel2;

    @FXML
    private Label phoneNumberLabel2;

    @FXML
    private Label doctorName2;

    @FXML
    private Label departmentName2;

    @FXML
    private Button detailsButton2;

    @FXML
    private Button registerButton2;

    //Recent Patient 3
    @FXML
    private Label recentPatientLabel3;

    @FXML
    private Label idNoLabel3;

    @FXML
    private Label passedTimeLabel3;

    @FXML
    private Label passedDateLabel3;

    @FXML
    private Label phoneNumberLabel3;

    @FXML
    private Label doctorName3;

    @FXML
    private Label departmentName3;

    @FXML
    private Button detailsButton3;

    @FXML
    private Button registerButton3;

    //Recent Patient 4
    @FXML
    private Label recentPatientLabel4;

    @FXML
    private Label idNoLabel4;

    @FXML
    private Label passedTimeLabel4;

    @FXML
    private Label passedDateLabel4;

    @FXML
    private Label phoneNumberLabel4;

    @FXML
    private Label doctorName4;

    @FXML
    private Label departmentName4;

    @FXML
    private Button detailsButton4;

    @FXML
    private Button registerButton4;

    //Recent Patient 5
    @FXML
    private Label recentPatientLabel5;

    @FXML
    private Label idNoLabel5;

    @FXML
    private Label passedTimeLabel5;

    @FXML
    private Label passedDateLabel5;

    @FXML
    private Label phoneNumberLabel5;

    @FXML
    private Label doctorName5;

    @FXML
    private Label departmentName5;

    @FXML
    private Button detailsButton5;

    @FXML
    private Button registerButton5;

    //add new patient
    @FXML
    private Button addNewPatientButton;

    //Table View Variables
    @FXML
    private TableView<ModelTable> patientTable;

    @FXML
    private TableColumn<?,?> colID;

    @FXML
    private TableColumn<ModelTable,String> colName;

    @FXML
    private TableColumn<ModelTable,String> colBirthdate;

    @FXML
    private TableColumn<ModelTable,String> colSex;

    @FXML
    private TableColumn<ModelTable,String> colBloodType;

    @FXML
    private TableColumn<?,?> colRoomNumber;

    @FXML
    private TableColumn<?,?> colLastApp;

    @FXML
    private TableColumn<ModelTable,String> colInsurance;

    //variables

    //constructor
    public ReceptionistController(){}

    @FXML
    private void openAddPatient(ActionEvent e) throws IOException{
        System.out.println("Add patient opened!");
        loadWindow("ui/receptionist/FXML/AddPatientScene.fxml", "Add New Patient");
    }

    @FXML
    private void openPatientDetails(ActionEvent e) throws IOException{
        System.out.println("Patient details opened!");
        loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details"); //title can be change to "Bora Fatih Kazancı's Details etc."
    }

    @FXML
    private void logoutReceptionist(ActionEvent e) throws IOException{
        System.out.println("Logged out from Receptionist panel!");

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

    private void loadWindow(String location, String title) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(location));
        Stage newStage = new Stage(StageStyle.DECORATED);
        newStage.setTitle(title);
        Image icon = new Image("ui/icons/hospital.png");
        newStage.getIcons().add(icon);
        newStage.setScene(new Scene(parent));
        newStage.setResizable(false);
        newStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colID.setCellValueFactory();
    }
}

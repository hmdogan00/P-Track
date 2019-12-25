package ui.receptionist;

import com.mysql.cj.result.SqlDateValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.Database;
import javafx.util.Callback;
import ui.MasterController;
import ui.receptionist.controllers.PatientDetailsController;

import javax.swing.text.StyledEditorKit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ReceptionistController extends MasterController implements Initializable {
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


    //add new patient
    @FXML
    private Button addNewPatientButton;

    //Patient Table View Variables
    @FXML
    private TableView<ModelTable> patientTable;

    @FXML
    private TableColumn<ModelTable,String> colName;

    @FXML
    private TableColumn<ModelTable,String> colBirthDate;

    @FXML
    private TableColumn<ModelTable,String> colID;


    @FXML
    private TableColumn<ModelTable,String> colSex;

    @FXML
    private TableColumn<ModelTable,String> colBloodType;

    @FXML
    private TableColumn<ModelTable,String> colAddAppointment;

    @FXML
    private TableColumn<ModelTable,String> colDetails;

    @FXML
    private TableColumn<ModelTable,String> colChangeInfo;

    @FXML
    private TextField filterPatientName;

    //Patient Table View Variables
    @FXML
    private TableView<DoctorTable> doctorTable;

    @FXML
    private TableColumn<DoctorTable,String> colDoctorName;

    @FXML
    private TableColumn<DoctorTable,String> colDoctorDepartment;

    @FXML
    private TableColumn<DoctorTable,String> colDoctorRoom;

    @FXML
    private TextField filterDoctorName;

    //variables
    ModelTable p;

    //constructor
    public ReceptionistController() {}
    @FXML
    private void openAddPatient(ActionEvent e) throws IOException{
        System.out.println("Add patient opened!");
        loadWindow("ui/receptionist/FXML/addPatientScene.fxml", "Add New Patient");
    }

    @FXML
    private void openPatientDetails(ActionEvent e) throws IOException{
        System.out.println("Patient details opened!");
        loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
    }

    @FXML
    private void logoutReceptionist(ActionEvent e) throws IOException{
        System.out.println("Logged out from Receptionist panel!");

        //back to auth scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/authentication/authentication.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.setResizable(false);
        app_stage.show();
    }

    //database for patients
    private void getPatientData(){
        ObservableList<ModelTable> obList = FXCollections.observableArrayList();
        try {
            Connection con = Database.connection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient");

            while (rs.next()) {
                obList.add(new ModelTable(rs.getString("name"), rs.getString("birth_date"),
                        rs.getString("citizenship_id"), rs.getString("insurance"),
                        rs.getString("gender"), rs.getString("blood_type")));
            }
        }catch (SQLException ex){}

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));

        //Adding Appointment Button
        Callback<TableColumn<ModelTable, String>,TableCell<ModelTable, String>> cellFactory = (param) -> {
            //make table cell with button
            final TableCell<ModelTable, String> cell = new TableCell<ModelTable, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button addAppointmentButton = new Button("Add Appointment");
                        addAppointmentButton.setOnAction(event -> {
                            p = getTableView().getItems().get(getIndex());
                            idCarry(p.getId());
                            loadWindow("ui/receptionist/FXML/addAppointment.fxml", "Add Appointment");
                        });
                        setGraphic(addAppointmentButton);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        colAddAppointment.setCellFactory(cellFactory);

        //adding patient details button
        Callback<TableColumn<ModelTable, String>,TableCell<ModelTable, String>> cellFactory1 = (param) -> {
            //make table cell with button
            final TableCell<ModelTable, String> cell1 = new TableCell<ModelTable, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                    }
                    else{
                        final Button addAppointmentButton = new Button("Details");
                        addAppointmentButton.setOnAction(event -> {
                            ModelTable p = getTableView().getItems().get(getIndex());
                            idCarry(p.getId());
                            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
                        });
                        setGraphic(addAppointmentButton);
                    }
                    setText(null);
                }
            };
            return cell1;
        };
        colDetails.setCellFactory(cellFactory1);

        //Adding change patient info button
        Callback<TableColumn<ModelTable, String>,TableCell<ModelTable, String>> cellFactory2 = (param) -> {
            //make table cell with button
            final TableCell<ModelTable, String> cell2 = new TableCell<ModelTable, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button addAppointmentButton = new Button("Change Info");
                        addAppointmentButton.setOnAction(event -> {
                            ModelTable p = getTableView().getItems().get(getIndex());
                            idCarry(p.getId());
                            loadWindow("ui/receptionist/FXML/changePatientInfo.fxml", "Change Patient Info");
                        });
                        setGraphic(addAppointmentButton);
                        setText(null);
                    }
                }
            };
            return cell2;
        };
        colChangeInfo.setCellFactory(cellFactory2);

        patientTable.setItems(obList);
    }

    private void getPatientsData(){
        ObservableList<ModelTable> obList = FXCollections.observableArrayList();
        try {
            Connection con = Database.connection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient");

            while (rs.next()) {
                obList.add(new ModelTable(rs.getString("name"), rs.getString("birth_date"),
                        rs.getString("citizenship_id"), rs.getString("insurance"),
                        rs.getString("gender"), rs.getString("blood_type")));
            }
        }catch (SQLException ex){}

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));

        patientTable.setItems(obList);
    }

    private void getFilteredPatientData(){
        ObservableList<ModelTable> listFiltered = FXCollections.observableArrayList();
        try{
            Connection con = Database.connection();
            String nameFilter = filterPatientName.getText();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient WHERE name LIKE '%" + nameFilter + "%' ");

            while (rs.next()) {
                listFiltered.add(new ModelTable(rs.getString("name"), rs.getString("birth_date"),
                        rs.getString("citizenship_id"), rs.getString("insurance"),
                        rs.getString("gender"), rs.getString("blood_type")));
            }
        }catch (SQLException ex){}

        patientTable.setItems(listFiltered);
    }

    //database for doctors
    private void getDoctorData(){
        ObservableList<DoctorTable> obList2 = FXCollections.observableArrayList();
        try {
            Connection con = Database.connection();
            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM doctor");

            while (rs2.next()) {
                obList2.add(new DoctorTable(rs2.getString("name"), rs2.getString("department"),
                        rs2.getString("room_number") ));
            }
        }catch (SQLException ex){}

        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoctorDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colDoctorRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        doctorTable.setItems(obList2);
    }

    private void getFilteredDoctorData(){
        ObservableList<DoctorTable> listFiltered2 = FXCollections.observableArrayList();
        try{
            Connection con = Database.connection();
            String nameFilter = filterDoctorName.getText();
            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM doctor WHERE name LIKE '%" + nameFilter + "%' ");

            while (rs2.next()) {
                listFiltered2.add(new DoctorTable(rs2.getString("name"), rs2.getString("department"),
                        rs2.getString("room_number") ));
            }
        }catch (SQLException ex){}

        doctorTable.setItems(listFiltered2);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("All data is uploaded");
        getPatientData();
        getDoctorData();
        try {
            timeLabel.setText(Database.time());
            dateLabel.setText(Database.date());
            receptionistUsernameLabel.setText(Database.getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        patientTable.refresh();
        doctorTable.refresh();
    }

    //Patient Name Filter
    @FXML
    private void findPatientNameFromList(ActionEvent e) throws InvocationTargetException {
        if (filterPatientName.getText().equals("")) {
            getPatientsData();
            System.out.println("ali baba");
        }
        else
            getFilteredPatientData();
        patientTable.refresh();
    }

    //Patient Name Filter
    @FXML
    private void findDoctorNameFromList(ActionEvent e) throws InvocationTargetException {
        if (filterDoctorName.getText().equals("")) {
            getDoctorData();
            System.out.println("Welcome 2019 2");
        }
        else
            getFilteredDoctorData();
        doctorTable.refresh();
    }

    private void idCarry(String printItem){
        PrintWriter outFile = null;
        File file = new File("outFile.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outFile = new PrintWriter(file);
        } catch (FileNotFoundException fileE) {}

        //write the patient id in a different txt file
        outFile.println(printItem);
        System.out.println(outFile);
        outFile.close();
    }

    /*private void recentDetails(){
        int appListSize = 0;
        try {
            appListSize = Database.appointmentOrder().size();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (appListSize != 0) {
            try {
                idNoLabel1.setText(Database.appointmentOrder().get(0));



                //accessing patient details

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }*/

    private void pushRecentApp(){
        //recent1 patient push to 2
        recentPatientLabel2.setText(recentPatientLabel1.getText());
        idNoLabel2.setText(idNoLabel1.getText());
        passedTimeLabel2.setText(passedTimeLabel1.getText());
        passedDateLabel2.setText(passedDateLabel1.getText());
        phoneNumberLabel2.setText(phoneNumberLabel1.getText());
        doctorName2.setText(doctorName1.getText());
        departmentName2.setText(departmentName1.getText());

        //recent2 patient push to 3
        recentPatientLabel3.setText(recentPatientLabel2.getText());
        idNoLabel3.setText(idNoLabel2.getText());
        passedTimeLabel3.setText(passedTimeLabel2.getText());
        passedDateLabel3.setText(passedDateLabel2.getText());
        phoneNumberLabel3.setText(phoneNumberLabel2.getText());
        doctorName3.setText(doctorName2.getText());
        departmentName3.setText(departmentName2.getText());

        //recent3 patient push to 4
        recentPatientLabel4.setText(recentPatientLabel3.getText());
        idNoLabel4.setText(idNoLabel3.getText());
        passedTimeLabel4.setText(passedTimeLabel3.getText());
        passedDateLabel4.setText(passedDateLabel3.getText());
        phoneNumberLabel4.setText(phoneNumberLabel3.getText());
        doctorName4.setText(doctorName3.getText());
        departmentName4.setText(departmentName3.getText());

        //recent4 patient push to 5
        recentPatientLabel5.setText(recentPatientLabel4.getText());
        idNoLabel5.setText(idNoLabel4.getText());
        passedTimeLabel5.setText(passedTimeLabel4.getText());
        passedDateLabel5.setText(passedDateLabel4.getText());
        phoneNumberLabel5.setText(phoneNumberLabel4.getText());
        doctorName5.setText(doctorName4.getText());
        departmentName5.setText(departmentName4.getText());
    }
}

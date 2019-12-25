package ui.receptionist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import database.Database;
import javafx.util.Callback;
import ui.MasterController;

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

public class ReceptionistController extends MasterController implements Initializable {
    //Dashboard
    @FXML
    private Button logoutButton;

    @FXML
    private Label receptionistUsernameLabel, numberOfAppointmentsLabel, timeLabel, dateLabel;

    @FXML
    private TextArea noteTextArea;

    //Recent patient in Dashboard
    //Recent Patient 1
    @FXML
    private Label recentPatientLabel1, idNoLabel1, passedTimeLabel1, passedDateLabel1, phoneNumberLabel1, doctorName1, departmentName1;

    @FXML
    private Button detailsButton1;

    //Recent Patient 2
    @FXML
    private Label recentPatientLabel2, idNoLabel2, passedTimeLabel2, passedDateLabel2, phoneNumberLabel2, doctorName2, departmentName2;

    @FXML
    private Button detailsButton2;

    //Recent Patient 3
    @FXML
    private Label recentPatientLabel3, idNoLabel3, passedTimeLabel3, passedDateLabel3, phoneNumberLabel3, doctorName3, departmentName3;

    @FXML
    private Button detailsButton3;

    //Recent Patient 4
    @FXML
    private Label recentPatientLabel4, idNoLabel4, passedTimeLabel4, passedDateLabel4, phoneNumberLabel4, doctorName4, departmentName4;

    @FXML
    private Button detailsButton4;

    //Recent Patient 5
    @FXML
    private Label recentPatientLabel5, idNoLabel5, passedTimeLabel5, passedDateLabel5, phoneNumberLabel5, doctorName5, departmentName5;

    @FXML
    private Button detailsButton5;

    //Recent Patient in Patient Board
    //Recent 11
    @FXML
    private Label recentPatientLabel11, idNoLabel11, passedTimeLabel11, passedDateLabel11, phoneNumberLabel11, doctorName11, departmentName11;

    @FXML
    private Button detailsButton11;

    //Recent21
    @FXML
    private Label recentPatientLabel21, idNoLabel21, passedTimeLabel21, passedDateLabel21, phoneNumberLabel21, doctorName21, departmentName21;

    @FXML
    private Button detailsButton21;

    //Recent 31
    @FXML
    private Label recentPatientLabel31, idNoLabel31, passedTimeLabel31, passedDateLabel31, phoneNumberLabel31, doctorName31, departmentName31;

    @FXML
    private Button detailsButton31;

    //Recent 41
    @FXML
    private Label recentPatientLabel41, idNoLabel41, passedTimeLabel41, passedDateLabel41, phoneNumberLabel41, doctorName41, departmentName41;

    @FXML
    private Button detailsButton41;

    //Recent 51
    @FXML
    private Label recentPatientLabel51, idNoLabel51, passedTimeLabel51, passedDateLabel51, phoneNumberLabel51, doctorName51, departmentName51;

    @FXML
    private Button detailsButton51;

    //add new patient
    @FXML
    private Button addNewPatientButton;

    //Patient Table View Variables
    @FXML
    private TableView<ModelTable> patientTable;

    @FXML
    private TableColumn<ModelTable,String> colName, colBirthDate, colID, colSex, colBloodType, colAddAppointment, colDetails, colChangeInfo;

    @FXML
    private TextField filterPatientName;

    //Patient Table View Variables
    @FXML
    private TableView<DoctorTable> doctorTable;

    @FXML
    private TableColumn<DoctorTable,String> colDoctorName, colDoctorDepartment, colDoctorRoom;

    @FXML
    private TableColumn<DoctorTable, String> colAvailability;

    @FXML
    private TableColumn<DoctorTable, Integer> colPhoneNo;

    @FXML
    private TextField filterDoctorName;

    //variables
    ModelTable p;

    /**
     * Default constructor
     */
    public ReceptionistController() {}

    /**
     * Opens add patient fxml file
     * @param e takes button as an action
     * @throws IOException
     */
    @FXML
    private void openAddPatient(ActionEvent e) throws IOException{
        System.out.println("Add patient opened!");
        loadWindow("ui/receptionist/FXML/addPatientScene.fxml", "Add New Patient");
    }

    /**
     * Open patient details fxml file
     * @param e takes button as an action
     * @throws IOException
     */
    @FXML
    private void openPatientDetails(ActionEvent e) throws IOException{
        System.out.println("Patient details opened!");
        loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
    }

    /**
     * Quits from receptionist page
     * @param e takes button as an action
     * @throws IOException
     */
    @FXML
    private void logoutReceptionist(ActionEvent e) throws IOException {
        System.out.println("Logged out from Receptionist panel!");

        //back to auth scene
        authLoader(e);
    }

    /**
     * Gets patient data from database and adds 3 dynamic table view button
     */
    private void getPatientData(){
        getPatientsData();

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
        //add button to the table
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
        //add button to the table
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
        //add button to the table
        colChangeInfo.setCellFactory(cellFactory2);
    }

    /**
     * Gets patient data from database and adds 3 dynamic table view button
     */
    private void getPatientsData(){
        ObservableList<ModelTable> obList = FXCollections.observableArrayList();
        try {
            Connection con = Database.myConn;
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

    /**
     * Gets filtered patient data from database and adds 3 dynamic table view button
     */
    private void getFilteredPatientData(){
        ObservableList<ModelTable> listFiltered = FXCollections.observableArrayList();
        try{
            Connection con = Database.myConn;
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

    /**
     * Gets doctor data from database and adds 1 dynamic table view button
     */
    private void getDoctorData(){
        ObservableList<DoctorTable> obList2 = FXCollections.observableArrayList();
        String text = "";
        try {
            Connection con = Database.myConn;
            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM doctor");

            while (rs2.next()) {
                int id = rs2.getInt("doctor_id");
                boolean availability = Database.doctorAvailability(id);
                if ( availability )
                    text = "Available";
                else
                    text = "Not Available";

                obList2.add(new DoctorTable(rs2.getString("name"), rs2.getString("department"),
                        rs2.getString("room_number"), text ,rs2.getString("phone_number") ));
            }
        }catch (SQLException ex){}

        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoctorDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colDoctorRoom.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        colAvailability.setCellValueFactory( new PropertyValueFactory<>("availability"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        doctorTable.setItems(obList2);
    }

    /**
     * Gets filtered doctor data from database and adds 1 dynamic table view button
     */
    private void getFilteredDoctorData(){
        ObservableList<DoctorTable> listFiltered2 = FXCollections.observableArrayList();
        try{
            Connection con = Database.myConn;
            String nameFilter = filterDoctorName.getText();
            ResultSet rs2 = con.createStatement().executeQuery("SELECT * FROM doctor WHERE name LIKE '%" + nameFilter + "%' ");

            while (rs2.next()) {
                //sets availability of doctor
                int id = rs2.getInt("doctor_id");
                boolean availability = Database.doctorAvailability(id);
                String text;
                if ( availability )
                {
                    text = "Available";
                }
                else
                {
                    text = "Not Available";
                }

                listFiltered2.add(new DoctorTable(rs2.getString("name"), rs2.getString("department"),
                        rs2.getString("room_number"), text ,rs2.getString("phone_number") ));
            }
        }catch (SQLException ex){}

        doctorTable.setItems(listFiltered2);
    }


    /**
     * Initialize the screen
     * @param location, resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("All data is uploaded");

        //loads the patient and doctor data to table
        getPatientData();
        getDoctorData();
        try {
            timeLabel.setText(Database.time());
            dateLabel.setText(Database.date());
            receptionistUsernameLabel.setText(Database.getUserName());
            numberOfAppointmentsLabel.setText("" + Database.totalAppointments());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        patientTable.refresh();
        doctorTable.refresh();
    }

    /**
     * finds patient name from list
     * @param e
     */
    @FXML
    private void findPatientNameFromList(ActionEvent e) throws InvocationTargetException {
        if (filterPatientName.getText().equals("")) {
            getPatientsData();
            System.out.println("Empty filter");
        }
        else
            getFilteredPatientData();
        patientTable.refresh();
    }

    /**
     * finds doctor name from list
     * @param e
     */
    @FXML
    private void findDoctorNameFromList(ActionEvent e) throws InvocationTargetException {
        if (filterDoctorName.getText().equals("")) {
            getDoctorData();
            System.out.println("Empty filter");
        }
        else
            getFilteredDoctorData();
        doctorTable.refresh();
    }

    /**
     * finds doctor name from list
     * @param e
     */
    @FXML
    private void recentInDashboard(ActionEvent e){
        /*recent1EqualsRecent2();

        int size = 0;
        try {
            size = Database.appointmentOrder().size();
            System.out.println(size);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        int finalSize = size;
        detailsButton1.setOnAction(event ->{
            int id1 = 0;
            int idDoctor1 = 0;
            try {
                id1 = (int) Database.appointmentOrder().get(finalSize);
                idDoctor1 = (int)Database.appointmentOrder().get(finalSize);

                ArrayList<String> infoList = database.Database.patientDetails(id1);

                //set recent patient's labels
                recentPatientLabel1.setText(infoList.get(0));
                idNoLabel1.setText(infoList.get(1));
                phoneNumberLabel1.setText(infoList.get(7));
                passedTimeLabel1.setText(infoList.get(0));
                passedDateLabel1.setText(infoList.get(0));
                doctorName1.setText(infoList.get(0));
                departmentName1.setText(infoList.get(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String newId1 = "" + id1;
            idCarry(newId1);
            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
        });

        detailsButton2.setOnAction(event ->{
            int id2 = 0;
            int idDoctor2 = 0;
            try {
                id2 = (int) Database.appointmentOrder().get(finalSize - 6);
                idDoctor2 = (int)Database.appointmentOrder().get(finalSize - 5);

                ArrayList<String> infoList = database.Database.patientDetails(id2);

                //set recent patient's labels
                recentPatientLabel2.setText(infoList.get(0));
                idNoLabel2.setText(infoList.get(1));
                phoneNumberLabel2.setText(infoList.get(7));
                passedTimeLabel2.setText(infoList.get(0));
                passedDateLabel2.setText(infoList.get(0));
                doctorName2.setText(infoList.get(0));
                departmentName2.setText(infoList.get(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String newId2 = "" + id2;
            idCarry(newId2);
            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
        });

        detailsButton3.setOnAction(event ->{
            int id3 = 0;
            int idDoctor3 = 0;
            try {
                id3 = (int) Database.appointmentOrder().get(finalSize - 9);
                idDoctor3 = (int)Database.appointmentOrder().get(finalSize - 8);

                ArrayList<String> infoList = database.Database.patientDetails(id3);

                //set recent patient's labels
                recentPatientLabel3.setText(infoList.get(0));
                idNoLabel3.setText(infoList.get(1));
                phoneNumberLabel3.setText(infoList.get(7));
                passedTimeLabel3.setText(infoList.get(0));
                passedDateLabel3.setText(infoList.get(0));
                doctorName3.setText(infoList.get(0));
                departmentName3.setText(infoList.get(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String newId3 = "" + id3;
            idCarry(newId3);
            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
        });

        detailsButton4.setOnAction(event ->{
            int id4 = 0;
            int idDoctor4 = 0;
            try {
                id4 = (int) Database.appointmentOrder().get(finalSize - 12);
                idDoctor4 = (int)Database.appointmentOrder().get(finalSize - 11);

                ArrayList<String> infoList = database.Database.patientDetails(id4);

                //set recent patient's labels
                recentPatientLabel4.setText(infoList.get(0));
                idNoLabel4.setText(infoList.get(1));
                phoneNumberLabel4.setText(infoList.get(7));
                passedTimeLabel4.setText(infoList.get(0));
                passedDateLabel4.setText(infoList.get(0));
                doctorName4.setText(infoList.get(0));
                departmentName4.setText(infoList.get(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String newId4 = "" + id4;
            idCarry(newId4);
            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
        });

        detailsButton5.setOnAction(event ->{
            int id5 = 0;
            int idDoctor5 = 0;
            try {
                id5 = (int) Database.appointmentOrder().get(finalSize - 15);
                idDoctor5 = (int)Database.appointmentOrder().get(finalSize - 14);

                ArrayList<String> infoList = database.Database.patientDetails(id5);

                //set recent patient's labels
                recentPatientLabel5.setText(infoList.get(0));
                idNoLabel5.setText(infoList.get(1));
                phoneNumberLabel5.setText(infoList.get(7));
                passedTimeLabel5.setText(infoList.get(0));
                passedDateLabel5.setText(infoList.get(0));
                doctorName5.setText(infoList.get(0));
                departmentName5.setText(infoList.get(0));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String newId5 = "" + id5;
            idCarry(newId5);
            loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details");
        });*/
    }

    /**
     * finds doctor name from list
     * @param e
     */
    private void recent1EqualsRecent2(){
        //names
        recentPatientLabel11 = recentPatientLabel1;
        recentPatientLabel21 = recentPatientLabel2;
        recentPatientLabel31 = recentPatientLabel3;
        recentPatientLabel41 = recentPatientLabel4;
        recentPatientLabel51 = recentPatientLabel5;

        //ids
        idNoLabel11 = idNoLabel1;
        idNoLabel21 = idNoLabel2;
        idNoLabel31 = idNoLabel3;
        idNoLabel41 = idNoLabel4;
        idNoLabel51 = idNoLabel5;

        //phone numbers
        phoneNumberLabel11 = phoneNumberLabel1;
        phoneNumberLabel21 = phoneNumberLabel2;
        phoneNumberLabel31 = phoneNumberLabel3;
        phoneNumberLabel41 = phoneNumberLabel4;
        phoneNumberLabel51 = phoneNumberLabel5;

        //passed times
        passedTimeLabel11 = passedTimeLabel1;
        passedTimeLabel21 = passedTimeLabel2;
        passedTimeLabel31 = passedTimeLabel3;
        passedTimeLabel41 = passedTimeLabel4;
        passedTimeLabel51 = passedTimeLabel5;

        //passed dates
        passedDateLabel11 = passedDateLabel1;
        passedDateLabel21 = passedDateLabel2;
        passedDateLabel31 = passedDateLabel3;
        passedDateLabel41 = passedDateLabel4;
        passedDateLabel51 = passedDateLabel5;

        //doctor names
        doctorName11 = doctorName1;
        doctorName21 = doctorName2;
        doctorName31 = doctorName3;
        doctorName41 = doctorName4;
        doctorName51 = doctorName5;

        //doctor departments
        departmentName11 = departmentName1;
        departmentName21 = departmentName2;
        departmentName31 = departmentName3;
        departmentName41 = departmentName4;
        departmentName51 = departmentName5;

        //button names
        detailsButton11 = detailsButton1;
        detailsButton21 = detailsButton2;
        detailsButton31 = detailsButton3;
        detailsButton41 = detailsButton4;
        detailsButton51 = detailsButton5;
    }

}

package ui.receptionist.controllers;

import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AddAppointmentController implements Initializable {

    ObservableList<String> hourList = FXCollections.observableArrayList("08","09","10","11","12","13","14","15","16","17");
    ObservableList<String> minuteList = FXCollections.observableArrayList("00","15","30","45");

    @FXML
    TextField patientName;

    @FXML
    TextField patientSurname;

    @FXML
    TextField doctorName;

    @FXML
    TextField doctorSurname;

    @FXML
    DatePicker appointmentDate;

    @FXML
    Button saveAppointmentButton;

    @FXML
    MenuButton hourButton, minuteButton;

    @FXML
    MenuItem hour08, hour09, hour10, hour11, hour12, hour13, hour14, hour15, hour16, minute00, minute15, minute30, minute45;

    @FXML public void hourChosen8( ActionEvent e){
        hourButton.setText("08");
    }
    @FXML public void hourChosen9( ActionEvent e){
        hourButton.setText("09");
    }
    @FXML public void hourChosen10( ActionEvent e){
        hourButton.setText("10");
    }
    @FXML public void hourChosen11( ActionEvent e){
        hourButton.setText("11");
    }
    @FXML public void hourChosen12( ActionEvent e){
        hourButton.setText("12");
    }
    @FXML public void hourChosen13( ActionEvent e){
        hourButton.setText("13");
    }
    @FXML public void hourChosen14( ActionEvent e){
        hourButton.setText("14");
    }
    @FXML public void hourChosen15( ActionEvent e){
        hourButton.setText("15");
    }
    @FXML public void hourChosen16( ActionEvent e){
        hourButton.setText("16");
    }
    @FXML public void minuteChosen00( ActionEvent e){
        minuteButton.setText("00");
    }
    @FXML public void minuteChosen15( ActionEvent e){
        minuteButton.setText("15");
    }
    @FXML public void minuteChosen30( ActionEvent e){
        minuteButton.setText("30");
    }
    @FXML public void minuteChosen45( ActionEvent e){
        minuteButton.setText("45");
    }

    public String dateValue(){
        String date = String.valueOf(appointmentDate.getValue());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        String result = year + "-" + month + "-" + day;
        System.out.println(result);
        return result;
    }

    public String timeValue(){
        String hour = hourButton.getText();
        String minute = minuteButton.getText();
        return hour + ":" + minute;
    }
    @FXML public void saveAppointment(ActionEvent e) throws SQLException
    {
        //blabal
        String patName = patientName.getText() + " " + patientSurname.getText();
        int patientId = Database.findPatientKey( patName );
        String docName = doctorName.getText() + " " + doctorSurname.getText();
        int docId = Database.findDoctorKey( docName );
        if ( Database.doctorAvailability( docId ))
        {
            boolean trial = Database.addAppointment(patientId, docId, dateValue(), timeValue() );
            if (trial)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Appointment added successfully.");
                alert.show();
                Stage stage = (Stage) saveAppointmentButton.getScene().getWindow(); stage.close();
            }
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("A given info is wrong.");
                    alert.show();
                }
        }
        else
            { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Doctor is not available at given time. Please choose a different time.");
            alert.show();
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

        String fullName = infoList.get(0);
        int index = fullName.lastIndexOf(' ');
        String firstName = fullName.substring(0, index);
        String lastName = fullName.substring( index + 1);

        patientName.setText(firstName);
        patientSurname.setText(lastName);
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
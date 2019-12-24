/**
 * this class shows add appointment scene in receptionist dashboard
 */
package ui.receptionist.controllers;

import database.Database;
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

    @FXML
    TextField patientName;

    @FXML
    TextField patientSurname;

    String patientdbId;

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

    /**
     * constructor creates add appointment class which initialises patientId to empty string
     */
    public AddAppointmentController(){
        patientdbId = "";
    }

    /**
     * setting menu hour item to 8
     * @param e listens the menu item
     */
    @FXML public void hourChosen8( ActionEvent e){
        hourButton.setText("08");
    }
    /**
     * setting menu hour item to 9
     * @param e listens the menu item
     */
    @FXML public void hourChosen9( ActionEvent e){
        hourButton.setText("09");
    }
    /**
     * setting menu hour item to 10
     * @param e listens the menu item
     */
    @FXML public void hourChosen10( ActionEvent e){
        hourButton.setText("10");
    }
    /**
     * setting menu hour item to 11
     * @param e listens the menu item
     */
    @FXML public void hourChosen11( ActionEvent e){
        hourButton.setText("11");
    }
    /**
     * setting menu hour item to 12
     * @param e listens the menu item
     */
    @FXML public void hourChosen12( ActionEvent e){
        hourButton.setText("12");
    }
    /**
     * setting menu hour item to 13
     * @param e listens the menu item
     */
    @FXML public void hourChosen13( ActionEvent e){
        hourButton.setText("13");
    }
    /**
     * setting menu hour item to 14
     * @param e listens the menu item
     */
    @FXML public void hourChosen14( ActionEvent e){
        hourButton.setText("14");
    }
    /**
     * setting menu hour item to 15
     * @param e listens the menu item
     */
    @FXML public void hourChosen15( ActionEvent e){
        hourButton.setText("15");
    }
    /**
     * setting menu hour item to 16
     * @param e listens the menu item
     */
    @FXML public void hourChosen16( ActionEvent e){
        hourButton.setText("16");
    }
    /**
     * setting menu minute item to 00
     * @param e listens the menu item
     */
    @FXML public void minuteChosen00( ActionEvent e){
        minuteButton.setText("00");
    }
    /**
     * setting menu minute item to 15
     * @param e listens the menu item
     */
    @FXML public void minuteChosen15( ActionEvent e){
        minuteButton.setText("15");
    }
    /**
     * setting menu minute item to 30
     * @param e listens the menu item
     */
    @FXML public void minuteChosen30( ActionEvent e){
        minuteButton.setText("30");
    }
    /**
     * setting menu minute item to 45
     * @param e listens the menu item
     */
    @FXML public void minuteChosen45( ActionEvent e){
        minuteButton.setText("45");
    }

    /**
     * converting date to string version of year, month and day
     * @return the string version of date
     */
    public String dateValue(){
        String date = String.valueOf(appointmentDate.getValue());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);
        String result = year + "-" + month + "-" + day;
        System.out.println(result);
        return result;
    }

    /**
     * getting text of hour and minute button and converting the right version of them for database
     * @return string version of time
     */
    public String timeValue(){
        String hour = hourButton.getText();
        String minute = minuteButton.getText();
        return hour + ":" + minute;
    }

    /**
     * saving appointment when save button is pressed
     * @param e listens the save button
     * @throws SQLException
     */
    @FXML public void saveAppointment(ActionEvent e) throws SQLException
    {
        // combining patient name and surname for database
        String patName = patientName.getText() + " " + patientSurname.getText();

        // finding patient key which is patient's ID
        int patientId = Database.findPatientKey( patientdbId );
        System.out.println(patientId);

        // combining doctor name and surname for database
        String docName = doctorName.getText() + " " + doctorSurname.getText();

        // finding doctor key which is doctor's ID
        int docId = Database.findDoctorKey( docName );

        // if doctor is available
        if ( Database.doctorAvailability( docId ))
        {
            // adding appointment
            boolean trial = Database.addAppointment(patientId, docId, dateValue(), timeValue() );

            // if it is successful showing an alert message
            if (trial)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Appointment added successfully.");
                alert.show();
                Stage stage = (Stage) saveAppointmentButton.getScene().getWindow(); stage.close();
            }
            // else wait for another input
            else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("A given info is wrong.");
                    alert.show();
                }
        }
        // if doctor is not available
        else
            { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Doctor is not available at given time. Please choose a different time.");
            alert.show();
            }
    }

    /**
     *
     * @throws SQLException
     */
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
        patientdbId = infoList.get(1);
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
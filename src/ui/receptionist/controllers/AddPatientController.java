package ui.receptionist.controllers;

import classes.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ui.receptionist.ReceptionistController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class AddPatientController {

    private ImageView photoView; //fotograf için
    @FXML
    private TextField addName;
    @FXML
    private TextField addID;
    @FXML
    private TextField addSurname;
    @FXML
    private DatePicker addBirthDate;
    @FXML
    private TextField addAddress;
    @FXML
    private TextField addCity;
    @FXML
    private TextField addPatientPhonenumber;
    @FXML
    private TextField addEmergencyName;
    @FXML
    private TextField addEmergencySurname;
    @FXML
    private TextField addEmergencyNo;
@FXML
private Button saveButton;
    @FXML
    private MenuButton sexMenu;
    @FXML
    private MenuButton bloodTypeMenu;
    @FXML
    private MenuButton insuranceMenu;

    //variables
    private String photoFile; //fotograf için
    private int sexChooser = 0;
    private int bloodTypeChooser = 0;
    private int insuranceChooser = 0;

    //constructor
    public AddPatientController(){}

    @FXML
    private void sexMaleChoice(ActionEvent e){
        sexMenu.setText("Male");
        sexChooser = 1;
    }

    @FXML
    private void sexFemaleChoice(ActionEvent e){
        sexMenu.setText("Female");
        sexChooser = 2;
    }

    @FXML
    private void aRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("A RH+");
        bloodTypeChooser = 1;
    }

    @FXML
    private void aRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("A RH-");
        bloodTypeChooser = 2;
    }

    @FXML
    private void bRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("B RH+");
        bloodTypeChooser = 3;
    }

    @FXML
    private void bRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("B RH-");
        bloodTypeChooser = 4;
    }

    @FXML
    private void abRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("AB RH+");
        bloodTypeChooser = 5;
    }

    @FXML
    private void abRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("AB RH-");
        bloodTypeChooser = 6;
    }

    @FXML
    private void zeroRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("0 RH+");
        bloodTypeChooser = 7;
    }

    @FXML
    private void zeroRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("0 RH-");
        bloodTypeChooser = 8;
    }

    @FXML
    private void sgkChoice(ActionEvent e) {
        insuranceMenu.setText("SGK");
        insuranceChooser = 1;
    }

    @FXML
    private void bagkurChoice(ActionEvent e) {
        insuranceMenu.setText("BAGKUR");
        insuranceChooser = 2;
    }

    @FXML
    private void privateChoice(ActionEvent e) {
        insuranceMenu.setText("PRIVATE");
        insuranceChooser = 3;
    }

    public String dateValue(){
        String date = String.valueOf(addBirthDate.getValue());
        String year = date.substring(0,4);
        String month = date.substring(5,7);
        String day = date.substring(8,10);

        return day+"/"+ month+"/"+ year;
    }

    @FXML
    private void savePatient(ActionEvent e) throws SQLException {
       database.Database.addPatient(addName.getText(),Integer.parseInt(addID.getText()),sexMenu.getText(),bloodTypeMenu.getText(),""+ dateValue(),
               addAddress.getText() + " " + addCity.getText(),Integer.parseInt(addPatientPhonenumber.getText()),
               insuranceMenu.getText(),addEmergencyName.getText() + " " + addEmergencySurname.getText(), Integer.parseInt(addEmergencyNo.getText()));

       }



    /*@FXML
    private void choosePictureFile(ActionEvent e) throws MalformedURLException {
        FileChooser pictureChooser = new FileChooser();
        pictureChooser.setTitle("Choose patient's picture!");
        pictureChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.png", "*.jpg"));
        File file = pictureChooser.showOpenDialog(new Stage());

        if (file != null){
            photoFile = file.toURI().toURL().toString();
            Image image = new Image(photoFile);
            photoView.setImage(image);
        }
    }
}*/
}
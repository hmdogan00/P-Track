package ui.receptionist.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;

public class AddPatientController {
    @FXML
    private ImageView photoView; //fotograf için

    @FXML
    private MenuButton sexMenu;

    //variables
    private String photoFile; //fotograf için
    private int sexChooser = 0;
    private int bloodTypeChooser = 0;

    //constructor
    public AddPatientController(){}

    @FXML
    private void sexMaleChoice(ActionEvent e){
        sexMenu.setText("Male");
        sexChooser = 1;
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
    }*/
}

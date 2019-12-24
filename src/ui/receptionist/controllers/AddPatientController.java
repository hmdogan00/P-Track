/**
 * this class controls and shows add patient scene for receptionist
 */
package ui.receptionist.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.SQLException;

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
    private Button savingButton;
    @FXML
    private MenuButton sexMenu;
    @FXML
    private MenuButton bloodTypeMenu;
    @FXML
    private MenuButton insuranceMenu;

    //variables
    private String photoFile; //fotograf için

    /**
     * a constructor creates add Patient controller class
     */
    public AddPatientController(){}

    /**
     * if male is chosen from the sexMenu
     * @param e listens the menu button
     */
    @FXML
    private void sexMaleChoice(ActionEvent e){
        sexMenu.setText("Male");
    }

    /**
     * if female is chosen from the sexMenu
     * @param e listenes the menu button
     */
    @FXML
    private void sexFemaleChoice(ActionEvent e){
        sexMenu.setText("Female");
    }

    @FXML
    private void aRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("A RH+");
    }

    @FXML
    private void aRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("A RH-");
    }

    @FXML
    private void bRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("B RH+");
    }

    @FXML
    private void bRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("B RH-");
    }

    @FXML
    private void abRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("AB RH+");
    }

    @FXML
    private void abRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("AB RH-");
    }

    @FXML
    private void zeroRHpositiveChoice(ActionEvent e){
        bloodTypeMenu.setText("0 RH+");
    }

    @FXML
    private void zeroRHnegativeChoice(ActionEvent e){
        bloodTypeMenu.setText("0 RH-");
    }

    @FXML
    private void sgkChoice(ActionEvent e) {
        insuranceMenu.setText("SGK");
    }

    @FXML
    private void bagkurChoice(ActionEvent e) {
        insuranceMenu.setText("BAGKUR");
    }

    @FXML
    private void privateChoice(ActionEvent e) {
        insuranceMenu.setText("PRIVATE");
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
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            database.Database.addPatient(addName.getText() + " " + addSurname.getText(),Integer.parseInt(addID.getText()),sexMenu.getText(),bloodTypeMenu.getText(),""+ dateValue(),
                    addAddress.getText() + " " + addCity.getText(),Integer.parseInt(addPatientPhonenumber.getText()),
                    insuranceMenu.getText(),addEmergencyName.getText() + " " + addEmergencySurname.getText(), Integer.parseInt(addEmergencyNo.getText()));
            Stage stage = (Stage) savingButton.getScene().getWindow();
            stage.close();
        }
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
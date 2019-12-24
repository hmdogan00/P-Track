package ui.authentication;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private MenuButton roleMenu;

    @FXML
    private MenuItem patientSec;

    @FXML
    private MenuItem doctorSec;

    @FXML
    private MenuItem receptionistSec;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField citizenshipIDField;

    @FXML
    private JFXHamburger settingsHamburger;

    //variables
    private int roleChooser = 0;

    //constructor
    public AuthController(){

    }

    @FXML
    private void patientChoice(ActionEvent e){
        roleMenu.setText("Patient");
        roleChooser = 1;
    }

    @FXML
    private void doctorChoice(ActionEvent e){
        roleMenu.setText("Doctor");
        roleChooser = 2;
    }

    @FXML
    private void receptionistChoice(ActionEvent e){
        roleMenu.setText("Receptionist");
        roleChooser = 3;
    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        if (roleChooser == 0){
            System.out.println("Entered Patient");
            /*int loginPatient = database.Database.patientAuth(citizenshipIDField.getText());
            if (loginPatient == citizenshipIDField.getText())
                loader.setLocation(getClass().getClassLoader().getResource("ui/patient/patientScene.fxml"));
            else{
                citizenshipIDField.clear();
                errorLabel.setText();
                errorLabel.setTextFill(Color.RED);
            }*/
        }
        else if (roleChooser == 2){
            System.out.println("Entered Doctor");
            String loginDoctor = database.Database.doctorAuth(userName.getText(), password.getText());
            if (loginDoctor.equals(userName.getText()))
                loader.setLocation(getClass().getClassLoader().getResource("ui/doctor/doctorHomePage.fxml"));
            else{
                userName.clear();
                password.clear();
                errorLabel.setText(loginDoctor);
                errorLabel.setTextFill(Color.RED);
            }
        }
        else if (roleChooser == 3){
            System.out.println("Entered Receptionist");
            String loginReceptionist = database.Database.registrationAuth(userName.getText(), password.getText());
            if (loginReceptionist.equals(userName.getText()))
                loader.setLocation(getClass().getClassLoader().getResource("ui/receptionist/receptionistHomePage.fxml"));
            else{
                userName.clear();
                password.clear();
                errorLabel.setText(loginReceptionist);
                errorLabel.setTextFill(Color.RED);
            }
        }
        else{
            errorLabel.setText("Please select a role to log in!");
            errorLabel.setTextFill(Color.RED);
        }

        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.setResizable(false);
            app_stage.show();
        }catch (RuntimeException r){
            System.out.println("Role is not selected!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HamburgerBasicCloseTransition transition1 = new HamburgerBasicCloseTransition(settingsHamburger);
        transition1.setRate(-1);
        settingsHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) ->{
            transition1.setRate(transition1.getRate() * -1);
            transition1.play();
        });
    }
}

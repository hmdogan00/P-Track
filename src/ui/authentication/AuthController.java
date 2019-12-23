package ui.authentication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.Objects;

public class AuthController {
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
        if (roleChooser == 1){
            System.out.println("Entered Patient");
            loader.setLocation(getClass().getClassLoader().getResource("ui/patient/patientScene.fxml"));
            System.out.println( loader.getLocation() );
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
}

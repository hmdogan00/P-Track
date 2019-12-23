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
    private void handleButtonAction(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        if (roleChooser == 1){
            System.out.println("Entered Patient");
            loader.setLocation(getClass().getClassLoader().getResource("ui/patient/patientScene.fxml"));
            System.out.println( loader.getLocation() );
        }
        else if (roleChooser == 2){
            System.out.println("Entered Doctor");
            loader.setLocation(getClass().getClassLoader().getResource("ui/doctor/doctorHomePage.fxml"));
            System.out.println( loader.getLocation() );
        }
        else if (roleChooser == 3){
            System.out.println("Entered Receptionist");
            loader.setLocation(getClass().getClassLoader().getResource("ui/receptionist/receptionistHomePage.fxml"));
            System.out.println( loader.getLocation() );
        }
        else{
            errorLabel.setText("Please select a role to log in!");
            errorLabel.setTextFill(Color.RED);
        }

        try {
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

            if (isMatch()){
                app_stage.setScene(scene);
                app_stage.setResizable(false);
                app_stage.show();
            }
            else{
                userName.clear();
                password.clear();
                errorLabel.setText("Username or password is wrong. Try Again!");
                errorLabel.setTextFill(Color.RED);
            }
        }catch (RuntimeException r){
            System.out.println("Role is not selected!");
        }

    }

    private boolean isMatch() {
        boolean check;
        System.out.println("username: " + userName.getText() + "\npassword: "
                + password.getText());

        if (userName.getText().equals("admin") & password.getText().equals("123")) {
            System.out.println("Successful");
            check = true;
        }
        else {
            System.out.println("Not Successful");
            check = false;
        }
        return check;
    }
}

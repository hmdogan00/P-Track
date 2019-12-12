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

    //constructor
    public AuthController(){

    }

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        System.out.println("click click bum");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/receptionist/receptionistHomePage.fxml"));
        System.out.println( loader.getLocation() );
        Parent parent = loader.load();

        //Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("receptionistHomePage.fxml")));
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        if (isMatch()){
            //app_stage.hide();
            app_stage.setScene(scene);
            app_stage.show();
        }
        else{
            userName.clear();
            password.clear();
            errorLabel.setText("Username or password is wrong. Try Again!");
            errorLabel.setTextFill(Color.RED);
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

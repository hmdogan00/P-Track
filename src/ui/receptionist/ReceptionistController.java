package ui.receptionist;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import database.Database;
import javafx.util.Callback;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReceptionistController implements Initializable {
    //Dashboard
    @FXML
    private Label receptionistUsernameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Label numberOfAppointmentsLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private TextArea noteTextArea;

    //Recent Patient 1
    @FXML
    private Label recentPatientLabel1;

    @FXML
    private Label idNoLabel1;

    @FXML
    private Label passedTimeLabel1;

    @FXML
    private Label passedDateLabel1;

    @FXML
    private Label phoneNumberLabel1;

    @FXML
    private Label doctorName1;

    @FXML
    private Label departmentName1;

    @FXML
    private Button detailsButton1;

    @FXML
    private Button registerButton1;

    //Recent Patient 2
    @FXML
    private Label recentPatientLabel2;

    @FXML
    private Label idNoLabel2;

    @FXML
    private Label passedTimeLabel2;

    @FXML
    private Label passedDateLabel2;

    @FXML
    private Label phoneNumberLabel2;

    @FXML
    private Label doctorName2;

    @FXML
    private Label departmentName2;

    @FXML
    private Button detailsButton2;

    @FXML
    private Button registerButton2;

    //Recent Patient 3
    @FXML
    private Label recentPatientLabel3;

    @FXML
    private Label idNoLabel3;

    @FXML
    private Label passedTimeLabel3;

    @FXML
    private Label passedDateLabel3;

    @FXML
    private Label phoneNumberLabel3;

    @FXML
    private Label doctorName3;

    @FXML
    private Label departmentName3;

    @FXML
    private Button detailsButton3;

    @FXML
    private Button registerButton3;

    //Recent Patient 4
    @FXML
    private Label recentPatientLabel4;

    @FXML
    private Label idNoLabel4;

    @FXML
    private Label passedTimeLabel4;

    @FXML
    private Label passedDateLabel4;

    @FXML
    private Label phoneNumberLabel4;

    @FXML
    private Label doctorName4;

    @FXML
    private Label departmentName4;

    @FXML
    private Button detailsButton4;

    @FXML
    private Button registerButton4;

    //Recent Patient 5
    @FXML
    private Label recentPatientLabel5;

    @FXML
    private Label idNoLabel5;

    @FXML
    private Label passedTimeLabel5;

    @FXML
    private Label passedDateLabel5;

    @FXML
    private Label phoneNumberLabel5;

    @FXML
    private Label doctorName5;

    @FXML
    private Label departmentName5;

    @FXML
    private Button detailsButton5;

    @FXML
    private Button registerButton5;

    //add new patient
    @FXML
    private Button addNewPatientButton;

    //Table View Variables
    @FXML
    private TableView<ModelTable> patientTable;

    @FXML
    private TableColumn<ModelTable,String> colName;

    @FXML
    private TableColumn<ModelTable,String> colBirthDate;

    @FXML
    private TableColumn<ModelTable,String> colID;

    @FXML
    private TableColumn<ModelTable,String> colInsurance;

    @FXML
    private TableColumn<ModelTable,String> colSex;

    @FXML
    private TableColumn<ModelTable,String> colBloodType;

    @FXML
    private TextField filterName;

    //variables

    //constructor
    public ReceptionistController(){}

    @FXML
    private void openAddPatient(ActionEvent e) throws IOException{
        System.out.println("Add patient opened!");
        loadWindow("ui/receptionist/FXML/addPatientScene.fxml", "Add New Patient");
    }

    @FXML
    private void openPatientDetails(ActionEvent e) throws IOException{
        System.out.println("Patient details opened!");
        loadWindow("ui/receptionist/FXML/patientDetails.fxml", "Patient Details"); //title can be change to "Bora Fatih Kazancı's Details etc."
    }

    @FXML
    private void logoutReceptionist(ActionEvent e) throws IOException{
        System.out.println("Logged out from Receptionist panel!");

        //back to auth scene
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ui/authentication/authentication.fxml"));
        System.out.println( loader.getLocation() );
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage app_stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.setResizable(false);
        app_stage.show();
    }

    private void loadWindow(String location, String title){
        try {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource(location));
            Stage newStage = new Stage(StageStyle.DECORATED);
            newStage.setTitle(title);
            Image icon = new Image("ui/icons/hospital.png");
            newStage.getIcons().add(icon);
            newStage.setScene(new Scene(parent));
            newStage.setResizable(false);
            newStage.show();
        }catch (IOException exception){}
    }

    @FXML
    private void getData(){
        ObservableList<ModelTable> obList = FXCollections.observableArrayList();
        try {
            Connection con = Database.connection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient");

            //add appointment button to a column
            TableColumn<ModelTable, Boolean> col_addApp = new TableColumn<>();
            col_addApp.setSortable(false);
            col_addApp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModelTable, Boolean>, ObservableValue<Boolean>>(){
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ModelTable, Boolean> p){
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });
            col_addApp.setCellFactory(new Callback<TableColumn<ModelTable, Boolean>, TableCell<ModelTable, Boolean>>(){
                @Override
                public TableCell<ModelTable, Boolean> call(TableColumn<ModelTable, Boolean> p){
                    return new ButtonAddApp(patientTable);
                }
            });
            patientTable.getColumns().add(col_addApp);

            //change info button to a column
            TableColumn<ModelTable, Boolean> col_changeInfo = new TableColumn<>();
            col_changeInfo.setSortable(false);
            col_changeInfo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ModelTable, Boolean>, ObservableValue<Boolean>>(){
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ModelTable, Boolean> p){
                    return new SimpleBooleanProperty(p.getValue() != null);
                }
            });
            col_changeInfo.setCellFactory(new Callback<TableColumn<ModelTable, Boolean>, TableCell<ModelTable, Boolean>>(){
                @Override
                public TableCell<ModelTable, Boolean> call(TableColumn<ModelTable, Boolean> p){
                    return new ButtonChangeInfo(patientTable);
                }
            });
            patientTable.getColumns().add(col_changeInfo);



            while (rs.next()) {
                obList.add(new ModelTable(rs.getString("name"), rs.getString("birth_date"),
                        rs.getString("citizenship_id"), rs.getString("insurance"),
                        rs.getString("gender"), rs.getString("blood_type")));
            }
        }catch (SQLException ex){}

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInsurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colBloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));

        patientTable.setItems(obList);
    }

    private void getFilteredData(){
        ObservableList<ModelTable> listFiltered = FXCollections.observableArrayList();
        try{
            Connection con = Database.connection();
            String nameFilter = filterName.getText();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM patient WHERE name LIKE '%" + nameFilter + "%' ");

            while (rs.next()) {
                listFiltered.add(new ModelTable(rs.getString("name"), rs.getString("birth_date"),
                        rs.getString("citizenship_id"), rs.getString("insurance"),
                        rs.getString("gender"), rs.getString("blood_type")));
            }
        }catch (SQLException ex){}

        patientTable.setItems(listFiltered);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("All data is uploaded");
        getData();
        patientTable.refresh();
    }

    @FXML
    private void findNameFromList(ActionEvent e) throws InvocationTargetException {
        if (filterName.getText().equals(""))
            getData();
        else
            getFilteredData();
        patientTable.refresh();
    }

    //inner classes to add buttons to Table
    private class ButtonAddApp extends TableCell<ModelTable, Boolean>{
        final Button addAppointmentButton = new Button("Add Appointment");
        ButtonAddApp(final TableView<ModelTable> tblView){
            addAppointmentButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadWindow("ui/receptionist/FXML/addAppointment.fxml", "Add Appointment");
                }
            });
        }

        @Override
        protected void updateItem(Boolean check, boolean empty){
            super.updateItem(check, empty);
            if (!empty)
                setGraphic(addAppointmentButton);
            else{
                setGraphic(null);
                setText("");
            }
        }
    }

    private class ButtonChangeInfo extends TableCell<ModelTable, Boolean>{
        final Button changeInfoButton = new Button("Change Info");
        ButtonChangeInfo(final TableView<ModelTable> tblView){
            changeInfoButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    loadWindow("ui/receptionist/FXML/changePatientInfo.fxml", "Change Patient Info");
                }
            });
        }

        @Override
        protected void updateItem(Boolean check, boolean empty){
            super.updateItem(check, empty);
            if (!empty)
                setGraphic(changeInfoButton);
            else{
                setGraphic(null);
                setText("");
            }
        }
    }


}

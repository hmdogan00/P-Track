package database;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String userName2;

    public static void main(String[] args) throws SQLException {
        System.out.println(doctorAvailability(1));
    }

    /**
     * Makes connection to database with username and password.
     * @return made connection.
     * @throws SQLException in case of an error in sql database.
     */
    public static Connection connection() throws SQLException {
        String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
        String user = "doadmin";
        String password = "ozhcj9y9h18feuqk";
        Connection myConn = DriverManager.getConnection(url, user, password);
        return myConn;
    }

    //Method to add new patient to database

    /**
     * Adds patient to the patient list.
     * @param name name of the patient
     * @param Id citizenship id of the patient.
     * @param sex sex of the patient.
     * @param blood_type blood type of the patient.
     * @param birth_date birth date of the patient.
     * @param address address of the patient
     * @param phoneNumber phone number of the patient.
     * @param insurance insurance type of the patient.
     * @param emergency_name name of the emergency contact of the patient.
     * @param emergency_number phone number of the emergency contact of the patient.
     * @throws SQLException in case of an error in sql database.
     */
    public static void addPatient(String name, int Id, String sex, String blood_type, String birth_date, String address,
                                  int phoneNumber, String insurance, String emergency_name, int emergency_number) throws SQLException {
        Connection myConn = connection();
        String sql = "INSERT INTO patient(name,birth_date,citizenship_id,insurance,gender,blood_type,emergency_name,emergency_number,patient_address,patient_phoneNumber) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, name);
        myStmt.setString(2, birth_date);
        myStmt.setString(3, "" + Id);
        myStmt.setString(4, insurance);
        myStmt.setString(5, sex);
        myStmt.setString(6, blood_type);
        myStmt.setString(7, emergency_name);
        myStmt.setString(8, "" + emergency_number);
        myStmt.setString(9, address);
        myStmt.setString(10, "" + phoneNumber);

       myStmt.executeUpdate();
    }

    //Method to find patient key number on table

    /**
     * Finds database key of the patient with given id.
     * @param id id of the patient.
     * @return key of the patient.
     * @throws SQLException in case of an error in sql database.
     */
    public static int findPatientKey(String id) throws SQLException {
        int patient_key = 0;
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String sql = "SELECT patient_id FROM patient WHERE citizenship_id = '" + id + "' ";
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            patient_key = rs.getInt("patient_id");
        }
        return patient_key;
    }

    //Method to update information of patient
    /**
     * Updates patients according to given info.
     * @param p_key database key of the patient to be updated.
     * @param p_address address of the patient.
     * @param patient_phone phone number of the patient.
     * @param insurance insurance type of the patient.
     * @param emergency_name emergency contact's name.
     * @param emergency_number emergency contact's phone number.
     * @throws SQLException in case of an error in sql database.
     */
    public static void updatePatient(int p_key, String p_address, int patient_phone, String insurance, String emergency_name, int emergency_number) throws SQLException {
        Connection myConn = connection();
        String sql = "UPDATE patient SET patient_address = ? , patient_phoneNumber = ?, insurance = ?, emergency_name = ?, emergency_number = ? WHERE patient_id = ? ";

        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, p_address);
        myStmt.setString(2, "" + patient_phone);
        myStmt.setString(3, insurance);
        myStmt.setString(4, emergency_name);
        myStmt.setString(5, "" + emergency_number);
        myStmt.setString(6, "" + p_key);
        myStmt.executeUpdate();
    }

    //Patient information with specific key value

    /**
     * Gives an arrayList containing all info of patient.
     * @param p_key database key of the patient.
     * @return an arraylist with patient infos.
     * @throws SQLException in case of an error in sql database.
     */
    public static ArrayList<String> patientDetails(int p_key) throws SQLException {
        String name = "" ;
        String id = "";
        String gender = "";
        String blood = "";
        String birth_date = "";
        String address = "";
        String insurance = "";
        String phone_number = "";
        String emergency_name = "";
        String emergency_number = "";

        ArrayList<String> patient_info = new ArrayList<>();

        Connection myConn = connection();
        String sql = " SELECT * FROM patient WHERE patient_id = '" + p_key + "' ";
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()) {
            name = rs.getString("name");
            id = rs.getString("citizenship_id");
            gender = rs.getString("gender");
            blood = rs.getString("blood_type");
            birth_date = rs.getString("birth_date");
            address = rs.getString("patient_address");
            insurance = rs.getString("insurance");
            phone_number = rs.getString("patient_phoneNumber");
            emergency_name = rs.getString("emergency_name");
            emergency_number = rs.getString("emergency_number");
        }

        patient_info.add(name);//0
        patient_info.add(id);//1
        patient_info.add(gender);//2
        patient_info.add(blood);//3
        patient_info.add(birth_date);//4
        patient_info.add(address);//5
        patient_info.add(insurance);//6
        patient_info.add(phone_number);//7
        patient_info.add(emergency_name);//8
        patient_info.add(emergency_number);//9

        return patient_info;
    }
//
    //Method for finding doctor key value
    public static int findDoctorKey(String doctor_name) throws SQLException {
        int doctor_key = 0;
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String sql = "SELECT doctor_id FROM doctor WHERE name = '" + doctor_name + "' ";
        ResultSet rs = myStmt.executeQuery(sql);
        while (rs.next()) {
            doctor_key = rs.getInt("doctor_id");
        }
        return doctor_key;
    }

    //Method for getting doctor details
    public static ArrayList<String> doctorDetails(int doctorKey) throws SQLException {
        String doctorName = "";
        String doctorDepartment = "";
        String doctor_roomNumber = "";
        String doctor_number = "";
        ArrayList<String> doctorInformation = new ArrayList<>();
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String sql = "SELECT * FROM doctor WHERE doctor_id = '" + doctorKey + "' ";
        ResultSet rs = myStmt.executeQuery(sql);

        while (rs.next()) {
            doctorName = rs.getString("name");
            doctorDepartment = rs.getString("department");
            doctor_roomNumber = rs.getString("room_number");
            doctor_number = rs.getString("phone_number");
        }
        doctorInformation.add(doctorName);
        doctorInformation.add(doctorDepartment);
        doctorInformation.add(doctor_roomNumber);
        doctorInformation.add(doctor_number);

        return doctorInformation;
    }

    //Local date returning method
    public static String date() throws SQLException {
        String date = "";
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String sql = "SET time_zone = '+03:00'; ";
        ResultSet rs = myStmt.executeQuery(sql);
        sql = "SELECT CURRENT_DATE()";
        ResultSet rs1 = myStmt.executeQuery(sql);
        while (rs1.next()) {
            date = (rs1.getString("CURRENT_DATE()"));
        }
        return date;
    }

    //Local time returning method
    public static String time() throws SQLException {
        String time = "";
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String sql = "SET time_zone = '+03:00'; ";
        ResultSet rs = myStmt.executeQuery(sql);
        sql = "SELECT CURRENT_TIME()";
        ResultSet rs2 = myStmt.executeQuery(sql);

        while(rs2.next()){
            time = rs2.getString("CURRENT_TIME()");
        }
        return time;
    }

    //Upcoming appointment creating method
    public static ArrayList appointmentOrder () throws SQLException{
     ArrayList<String> appointmentOrder = new ArrayList<>();
     Connection myConn = connection();
     Statement myStmt = myConn.createStatement();
     String sql = "SELECT * FROM appointment WHERE date = '" + date() + "' AND time >='" + time() + "' ORDER BY time ASC";
     ResultSet rs = myStmt.executeQuery(sql);

     while(rs.next()){
         appointmentOrder.add(rs.getString("patient_id"));
         appointmentOrder.add(rs.getString("doctor_id"));
         appointmentOrder.add(rs.getString("time"));
     }
     return appointmentOrder;
    }

    public static boolean addAppointment(int p_key, int d_key, String date, String time) throws SQLException {
        Connection myConn = connection();
        int appointmentDate = Integer.parseInt(date.substring(8)) * 1000000 + Integer.parseInt(date.substring(5,7)) * 10000 + Integer.parseInt(date.substring(0,4));
        int currentDate = Integer.parseInt(date().substring(8)) * 1000000 + Integer.parseInt(date().substring(5,7)) * 10000 + Integer.parseInt(date().substring(0,4));
        int appointmentTime = Integer.parseInt(time.substring(0,2)) * 100 + Integer.parseInt(time.substring(3));
        int currentTime = Integer.parseInt(time().substring(0,2)) * 100 + Integer.parseInt(time().substring(3,5));
        Boolean flag = true;
        String sql = "SELECT * FROM appointment WHERE doctor_id = '" + d_key + "'";
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            String docTime = rs.getString("time");
            String docDate = rs.getString("date");
            int timeValue = Integer.parseInt(docTime.substring(0,2)) * 100 + Integer.parseInt(docTime.substring(3));
            int dateValue = Integer.parseInt(docDate.substring(8)) * 1000000 + Integer.parseInt(docDate.substring(5,7)) * 10000 + Integer.parseInt(docDate.substring(0,4));
            if((dateValue == appointmentDate && timeValue == appointmentTime)){
                flag = false;
            }
            if(appointmentDate < currentDate ){
                flag = false;
            }
            else if(appointmentTime < currentTime && appointmentDate == currentDate){
                flag = false;
            }
        }

        if (flag) {
            String sql2 = "INSERT INTO appointment(patient_id,doctor_id,date,time) VALUES(?,?,?,?)";
            PreparedStatement myStmt2 = myConn.prepareStatement(sql2);
            myStmt2.setInt(1, p_key);
            myStmt2.setInt(2, d_key);
            myStmt2.setString(3, date);
            myStmt2.setString(4, time);
            myStmt2.executeUpdate();
        }
        return flag;
    }

    public static ArrayList doctorsAppointedPatients(int d_id) throws SQLException {
        ArrayList<String> doctorsAppointedPatients = new ArrayList<>();
        Connection myConn = connection();
        String sql ="SELECT * FROM appointment WHERE doctor_id = '" + d_id + "' ORDER BY time ASC" ;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            doctorsAppointedPatients.add(rs.getString("patient_id"));
            doctorsAppointedPatients.add(rs.getString("date"));
            doctorsAppointedPatients.add(rs.getString("time"));
        }
        return doctorsAppointedPatients;
    }

    public static String doctorAuth(String user_name, String password) throws SQLException {
        Connection myConn = connection();
        String userName = "";
        String userPassword = "";
        String userRole = "";
        String sql1 = "SELECT * FROM users WHERE user_name = '" + user_name + "' ";
        Statement myStmt = myConn.createStatement();
        ResultSet rs1 = myStmt.executeQuery(sql1);
        while(rs1.next()){
            userName = rs1.getString("user_name");
            userRole = rs1.getString("role");
            userPassword = rs1.getString("password");
        }
        if(user_name.equals("") || !userRole.equals("doctor")){
            return "User name or the role does not match.";
        }
        if(!userPassword.equals(password)){
            return "Password does not match with username.";
        }
        userName2 = user_name;
        return user_name;
    }

    public static String registrationAuth(String user_name, String password) throws SQLException {
        Connection myConn = connection();
        String userName = "";
        String userPassword = "";
        String userRole = "";
        String sql1 = "SELECT * FROM users WHERE user_name = '" + user_name + "' ";
        Statement myStmt = myConn.createStatement();
        ResultSet rs1 = myStmt.executeQuery(sql1);
        while(rs1.next()){
            userName = rs1.getString("user_name");
            userRole = rs1.getString("role");
            userPassword = rs1.getString("password");
        }
        if(user_name.equals("") || !userRole.equals("receptionist")){
            return "User name or the role does not match.";
        }
        if(!userPassword.equals(password)){
            return "Password does not match with username.";
        }
        userName2 = user_name;
        return user_name;
    }

    public static boolean doctorAvailability(int d_id) throws SQLException {
        Connection myConn = connection();
        boolean flag = true;
        String sql = "SELECT * FROM appointment WHERE doctor_id = '" + d_id + "' AND date = '" + date() +" ' " ;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            String docTime = rs.getString("time");
            System.out.println(docTime);
            int timeValue = Integer.parseInt(docTime.substring(0,2)) * 100 + Integer.parseInt(docTime.substring(3));
            int currentTime = Integer.parseInt(time().substring(0,2)) * 100 + Integer.parseInt(time().substring(3,5));

            if(rs.getString("date").equals(date()) && (rs.getString("time").equals(time())) || timeValue + 15 > currentTime ){
                flag = false;
            }
        }
        return flag;
    }

    public static String getUserName()
    {
        return userName2;
    }

    public static ArrayList getDoctorName() throws SQLException {
        Connection myConn = connection();
        ArrayList<String> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctor";
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            String dName = rs.getString("name") + "<" + rs.getString("department") + ">";
            doctorList.add(dName);
            System.out.println(dName);
        }
        return doctorList;
    }

    public static String patientAuth(String id) throws SQLException {
        Connection myConn = connection();
        String sql = "SELECT * FROM patient WHERE citizenship_id = '" + id + "' " ;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        String name = "";
        while(rs.next()){
            name = rs.getString("name");
        }
        if(name.equals("")){
            return "Patient does not exist.";
        }
        userName2 = id;
        return "" + id;
    }
}
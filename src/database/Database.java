package database;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static void main(String[] args) throws SQLException {
    }

    //Connection method including connection details
    public static Connection connection() throws SQLException {
        String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
        String user = "doadmin";
        String password = "ozhcj9y9h18feuqk";
        Connection myConn = DriverManager.getConnection(url, user, password);
        return myConn;
    }

    //Test Method
    public static void test() throws SQLException {
        Connection myConn = connection();
        Statement myStmt = myConn.createStatement();
        String search = "Mert";
        String sql = "SELECT * FROM patient WHERE name LIKE '" + search + "' ";
        ResultSet rs = myStmt.executeQuery(sql);

        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }

    //Method to add new patient to database
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

        patient_info.add(name);
        patient_info.add(id);
        patient_info.add(gender);
        patient_info.add(blood);
        patient_info.add(birth_date);
        patient_info.add(address);
        patient_info.add(insurance);
        patient_info.add(phone_number);
        patient_info.add(emergency_name);
        patient_info.add(emergency_number);

        return patient_info;
    }

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
        Boolean flag = true;
        String sql = "SELECT * FROM appointment WHERE time = '" + time + "' ";
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("time") == time &&
                    (rs.getString("doctor_id") ==  "" + d_key || rs.getString("patient_id") == "" + p_key)){
                flag = false;
            }
        }
        if (flag) {
            String sql1 = "INSERT INTO appointment(patient_id,doctor_id,date,time) VALUES(?,?,?,?)";
            PreparedStatement myStmt1 = myConn.prepareStatement(sql1);
            myStmt1.setInt(1, p_key);
            myStmt1.setInt(2, d_key);
            myStmt1.setString(3, date);
            myStmt1.setString(4, time);
            myStmt1.executeUpdate();
        }
        return flag;
    }
    public static ArrayList doctorsAppointment(int d_id) throws SQLException {
        ArrayList<String> doctorsAppointment = new ArrayList<>();
        Connection myConn = connection();
        String sql ="SELECT * FROM appointment WHERE doctor_id = '" + d_id + "' ORDER BY time ASC" ;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            doctorsAppointment.add(rs.getString("patient_id"));
            doctorsAppointment.add(rs.getString("date"));
            doctorsAppointment.add(rs.getString("time"));
        }
        return doctorsAppointment;
    }

    public static ArrayList patientAppointment(int p_id)throws SQLException{
        ArrayList<String> patientAppointment = new ArrayList<>();
        Connection myConn = connection();
        String sql = "SELECT * FROM appointment WHERE date = '" + date() + "' AND time >='" + time() + "' AND patient_id ='" + p_id + "'  ORDER BY time ASC";;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
        while(rs.next()){
            patientAppointment.add(rs.getString("doctor_id"));
            patientAppointment.add(rs.getString("date"));
            patientAppointment.add(rs.getString("time"));
        }
        return patientAppointment;
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
        if(user_name.equals("") || !userRole.equals("registration")){
            return "User name or the role does not match.";
        }
        if(!userPassword.equals(password)){
            return "Password does not match with username.";
        }
        return user_name;
    }

}
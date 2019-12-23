package database;

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
        String sql = "SELECT * FROM patient WHERE name LIKE '%" + search + "%' " ;
        ResultSet rs = myStmt.executeQuery(sql);

        while(rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }
    //Method to add new patient to database
    public static void addPatient(String name, int Id, String sex, String blood_type, String birth_date, String address,
                                  int phoneNumber, String insurance, String emergency_name, int emergency_number ) throws SQLException{
        Connection myConn = connection();
        String sql = "INSERT INTO patient(name,birth_date,citizenship_id,insurance,gender,blood_type,emergency_name,emergency_number,patient_address,patient_phoneNumber) VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1,name);
        myStmt.setString(2,birth_date);
        myStmt.setString(3,"" + Id);
        myStmt.setString(4,insurance);
        myStmt.setString(5,sex);
        myStmt.setString(6,blood_type);
        myStmt.setString(7,emergency_name);
        myStmt.setString(8, "" + emergency_number);
        myStmt.setString(9,address);
        myStmt.setString(10,"" + phoneNumber);

        int rowsAffected = myStmt.executeUpdate();
    }

    //Method to find patient key number on table
    public static void patientKey (String p_name) throws SQLException {
        Connection myConn = connection();
        String sql = "SELECT patient_id FROM patient WHERE name = ?";
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1,p_name);
        int rowsAfected = myStmt.executeUpdate();
    }

    //Method to update information of patient
    public static void updatePatient(int p_key, String p_address, int patient_phone, String insurance, String emergency_name, int emergency_number) throws SQLException{
        Connection myConn = connection();
        String sql ="UPDATE patient SET patient_address = ? , patient_phoneNumber = ?, insurance = ?, emergency_name = ?, emergency_number = ? WHERE patient_id = ? ";

        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, p_address);
        myStmt.setString(2,"" + patient_phone);
        myStmt.setString(3, insurance);
        myStmt.setString(4, emergency_name);
        myStmt.setString(5,"" + emergency_number);
        myStmt.setString(6,"" + p_key);
        int rowsAffected = myStmt.executeUpdate();
    }

    //Patient info with specific key value
    public static ArrayList<String> patientDetails(int p_key) throws SQLException {
        String name, id, gender, blood, birth_date, address, insurance, phone_number, emergency_name, emergency_number;

        ArrayList<String> patient_info = new ArrayList<>();

        Connection myConn = connection();
        String sql = " SELECT * FROM patient WHERE patient_id = " + p_key;
        Statement myStmt = myConn.createStatement();
        ResultSet rs = myStmt.executeQuery(sql);
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


}

package database;

import java.sql.*;

public class Database {
    public static void main(String[] args) throws SQLException {
        addPatient("Aynur Dayanık", 12312312, "Female","A+", "17/09/1960", "ankara",
                01237612736, "Private", "Mustafa Can Çavdar", 0123671723);


        }

        public static Connection connection() throws SQLException {
            String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
            String user = "doadmin";
            String password = "ozhcj9y9h18feuqk";
            Connection myConn = DriverManager.getConnection(url, user, password);
            return myConn;
    }
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

    }

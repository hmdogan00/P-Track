import java.sql.*;
import java.util.ArrayList;

public class Database {
    public static void main(String[] args) {
        }

        private static Connection connection() throws SQLException {
            String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
            String user = "doadmin";
            String password = "ozhcj9y9h18feuqk";
                Connection myConn = DriverManager.getConnection(url, user, password);
                return myConn;
    }
    private static void getPatient_Table() throws SQLException {
        ArrayList<String> patient_name = new ArrayList<>();
        ArrayList<String> birth_date = new ArrayList<>();
        ArrayList<String> gender = new ArrayList<>();
        ArrayList<String> citizenship_id = new ArrayList<>();
        ArrayList<String> insurance = new ArrayList<>();
        ArrayList<String> blood_type = new ArrayList<>();
            Connection myConn = connection();
            Statement myStmt = myConn.createStatement();
            String sql = "SELECT * FROM patient";
            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                patient_name.add(rs.getString("name"));
                birth_date.add(rs.getString("birth_date"));
                gender.add(rs.getString("gender"));
                citizenship_id.add(rs.getString("citizenship_id"));
                insurance.add(rs.getString("insurance"));
                blood_type.add(rs.getString("blood_type"));
            }
        }

    private static void getPatient_Table_Filtered(String filterName) throws SQLException {
        ArrayList<String> patient_name = new ArrayList<>();
        ArrayList<String> birth_date = new ArrayList<>();
        ArrayList<String> gender = new ArrayList<>();
        ArrayList<String> citizenship_id = new ArrayList<>();
        ArrayList<String> insurance = new ArrayList<>();
        ArrayList<String> blood_type = new ArrayList<>();
            Connection myConn = connection();
            Statement myStmt = myConn.createStatement();
            String sql = "SELECT * FROM patient WHERE name = 'filterName'";
            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                patient_name.add(rs.getString("name"));
                birth_date.add(rs.getString("birth_date"));
                gender.add(rs.getString("gender"));
                citizenship_id.add(rs.getString("citizenship_id"));
                insurance.add(rs.getString("insurance"));
                blood_type.add(rs.getString("blood_type"));
            }
        }
    }

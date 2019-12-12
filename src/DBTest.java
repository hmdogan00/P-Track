import java.sql.*;

public class DBTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
        String user = "doadmin";
        String password = "ozhcj9y9h18feuqk";
        try {
            Connection myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();
            String sql = "SELECT * FROM patients";
            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getString("patient_name") + "  ");
                System.out.print(rs.getString("patient_address") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

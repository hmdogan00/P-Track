import java.sql.*;

public class Database {
    public static void main(String[] args){
        String url = "jdbc:mysql://db-mysql-fra1-50385-do-user-6846837-0.db.ondigitalocean.com:25060/defaultdb?ssl-mode=REQUIRED";
        String user = "doadmin";
        String password = "ozhcj9y9h18feuqk";
        try {
            Connection myConn = DriverManager.getConnection(url, user, password);
            Statement myStmt = myConn.createStatement();
            String sql = "SELECT * FROM patient";
            ResultSet rs = myStmt.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getString("name") + "  ");
                System.out.print(rs.getString("birth_date") + " ");
                System.out.print(rs.getString("citizenship_id") + " ");
                System.out.print(rs.getString("insurance") + " ");
                System.out.print(rs.getString("blood_type") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

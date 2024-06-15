package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/hotelmanagementsystem";
                String user = "root";
                String password = "";
                conn = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return conn;
    }

    public static void closeCon() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

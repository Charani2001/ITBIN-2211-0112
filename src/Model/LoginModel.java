package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Charani
 */
public class LoginModel {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public LoginModel() throws SQLException, ClassNotFoundException {
        con = DBConnection.getConnection();
    }

    public boolean authenticateUser(String username, String password) {
        try {
            pst = con.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            pst.setString(1, username);
            pst.setString(2, password);
            rs = pst.executeQuery();

            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

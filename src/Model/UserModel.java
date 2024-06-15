package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Charani
 */
public class UserModel {
    private Connection con;

    public UserModel() {
        try {
            con = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel loadUser() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User ID");
        model.addColumn("Name");
        model.addColumn("Username");

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM user");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vector row = new Vector();
                row.add(rs.getString("Userid"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Username"));
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }

    public void addUser(String name, String username, String password) {
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO user(Name, Username, Password) VALUES(?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

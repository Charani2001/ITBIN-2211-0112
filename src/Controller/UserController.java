package Controller;

import Model.UserModel;
import View.Login;
import View.UserCreation;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Charani
 */
public class UserController {

    private final UserCreation view;
    private final UserModel model;
    private DefaultTableModel d;

    public UserController(UserCreation view) {
        this.view = view;
        this.model = new UserModel();
    }

    public void loadUser() {
        DefaultTableModel userModel = model.loadUser();
        view.getUserTable().setModel(userModel);
    }

    public void addUser() {
        String name = view.getNameText();
        String username = view.getUsernameText();
        String password = view.getPasswordText();
        model.addUser(name, username, password);
        JOptionPane.showMessageDialog(view, "User Added Successfully");
        loadUser();
        try {
            new Login().setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadUserData() {
        d = (DefaultTableModel) view.getUserTable().getModel();
        int selectIndex = view.getUserTable().getSelectedRow();

        view.setNameText(d.getValueAt(selectIndex, 0).toString());
        view.setUsernameText(d.getValueAt(selectIndex, 1).toString());
        view.setPasswordText(d.getValueAt(selectIndex, 2).toString());

        view.getAddButton().setEnabled(false);
    }

    
}
 

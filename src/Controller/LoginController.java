package Controller;

import Model.DBConnection; 
import Model.LoginModel;
import View.Login;
import View.Menu;
import View.UserCreation;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Charani
 */
public class LoginController {

    private final Login view;
    private final LoginModel loginModel;

    public LoginController(Login view) throws SQLException, ClassNotFoundException {
        this.view = view;
        this.loginModel = new LoginModel();
        
        
        connect();

        view.addLoginButtonListener(new LoginButtonListener());
        view.addCreateUserButtonListener(new CreateUserButtonListener());
        
    }

    public void connect() {
        try {
            DBConnection.getConnection(); 
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(view, "Failed to connect to the database.");
            System.exit(1); 
        }
    }

    public class LoginButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (username.isEmpty() || password.isEmpty()) {
                view.displayMessage("Please enter User name and Password");
            } else {
                if (loginModel.authenticateUser(username, password)) {
                    view.displayMessage("Login Successful");
                    Menu menu = new Menu();
                    menu.setVisible(true);
                    view.dispose();
                } else {
                    view.displayMessage("Username or Password incorrect");
                    view.clearUsernameField();
                    view.clearPasswordField();
                    view.focusUsernameField();
                }
            }
        }
    }

    public class CreateUserButtonListener implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            UserCreation userCreation = new UserCreation();
            userCreation.setVisible(true);
            view.dispose();
        }
    }

}

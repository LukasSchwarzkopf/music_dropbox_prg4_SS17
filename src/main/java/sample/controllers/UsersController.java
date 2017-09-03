package sample.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.Main;
import sample.environment.Database;
import sample.helper.Cryptor;
import sample.logic_model.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.regex.Pattern;

public class UsersController {
    @FXML
    private Node root;

    // USER SIGN IN
    // NOTE: password is stored in encrypted form inside the database
    public static boolean sign_in(String _username, String _password) throws GeneralSecurityException, UnsupportedEncodingException {
        String encrypted_password = Cryptor.encrypt(Database.secret, Database.secret2, _password);
        User user = User.find_by_authentication_data(_username, encrypted_password);

        if (user != null) {
            return true;
        } else {
            // User doesn't exist
            return false;
        }
    }

    // USER SIGN UP
    public static boolean sign_up(String _firstname, String _lastname, String _password, String _username, String _email) throws GeneralSecurityException, UnsupportedEncodingException {

        if (User.exists(_username)) {
            // user already exists
            return false;
        } else {
            User user = User.create(_firstname, _lastname, _username, _email, _password, "user");
            return true;
        }
    }

    // UPDATE USER
    public static boolean update(String _actual_username, String _actual_password, String _firstname, String _lastname, String _password, String _username) throws GeneralSecurityException, UnsupportedEncodingException {
        String encrypted_password = Cryptor.encrypt(Database.secret, Database.secret2, _actual_password);
        User user = User.find_by_authentication_data(_actual_username, encrypted_password);

        if (user != null) {
            user.m_password = _password;
            user.m_username = _username;
            user.m_firstname = _firstname;
            user.m_lastname = _lastname;
            return true;
        } else {
            // user doesn't exist
            return false;
        }
    }

    // DELETE USER
    public static boolean destroy(String _username, String _password) throws GeneralSecurityException, UnsupportedEncodingException {
        String encrypted_password = Cryptor.encrypt(Database.secret, Database.secret2, _password);
        User user = User.find_by_authentication_data(_username, encrypted_password);

        if (user != null) {
            user.delete();
            return true;
        } else {
            // user doesn't exist
            return false;
        }
    }

    public void signIn(ActionEvent actionEvent) throws GeneralSecurityException, IOException {
        GridPane login_layout = (GridPane) root.lookup("#login_layout");

        TextField username_field = (TextField) login_layout.lookup("#username");
        PasswordField password_field = (PasswordField) login_layout.lookup("#password_field");

        String username = username_field.getText();
        String password = password_field.getText();

        String encrypted_password = Cryptor.encrypt(Database.secret, Database.secret2, password);
        User user = User.find_by_authentication_data(username, encrypted_password);

        // AUTHORIZE USER AND REDIRECT OR FLASH ERRORS
        if (user != null) {
            System.out.println("LOGGING IN USER: " + username + " PASSWORD: " + password);
            Main.sm_current_user = user;
            Main.mainViewController.loadMainView("signed/dashboard.fxml");

        } else {
            System.out.println("Invalid User");
        }
    }

    public void renderSignup(ActionEvent actionEvent) throws IOException {
        Main.mainViewController.loadMainView("unsignes/users/signup.fxml");
    }

    public void renderSignin(ActionEvent actionEvent) throws IOException {
        Main.mainViewController.loadMainView("unsignes/users/signin.fxml");
    }

    public void signUp(ActionEvent actionEvent) throws IOException, GeneralSecurityException {
        GridPane login_layout = (GridPane) root.lookup("#login_layout");
        TextField username_field = (TextField) login_layout.lookup("#username");
        TextField firstname_field = (TextField) login_layout.lookup("#firstname");
        TextField lastname_field = (TextField) login_layout.lookup("#lastname");
        TextField email_field = (TextField) login_layout.lookup("#email");
        PasswordField password_field = (PasswordField) login_layout.lookup("#password_field");
        PasswordField password_confirmation_field = (PasswordField) login_layout.lookup("#password_confirmation_field");

        String firstname = firstname_field.getText();
        String lastname = lastname_field.getText();
        String username = username_field.getText();
        String email = email_field.getText();
        String password = password_field.getText();
        String password_confirmation = password_confirmation_field.getText();

        System.out.println("SIGNING UP USER: " + username + " EMAIL: " + email + " PASSWORD: " + password + " PASSWORD CONFIRMATION: " + password_confirmation);

        // AUTHORIZE USER AND REDIRECT OR FLASH ERRORS
        if (password.equals(password_confirmation)) {
            String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            Pattern pattern = Pattern.compile(EMAIL_PATTERN);

            if (pattern.matcher(email).matches()) {
                if (!User.exists(username)) {
                    // INSERT USER TO DATABASE
                    String encrypted_password = Cryptor.encrypt(Database.secret, Database.secret2, password);
                    User user = User.create(firstname, lastname, username, email, encrypted_password, "user");
                    // REDIRECT
                    Main.sm_current_user = user;
                    Main.mainViewController.loadMainView("signed/dashboard.fxml");
                } else {
                    // USER ALREADY EXISTS
                }
            } else {
                // ERROR INVALID EMAIL
                System.out.println("INVALID EMAIL");
            }

        } else {
            // ERROR INCORRECT PASSWORD
            System.out.println("INVALID PASSWORD");
        }
    }
}

package tictactoe.domain.usecases;

import javafx.scene.control.TextField;
import java.util.regex.Pattern;

public class ValidationUseCase {

    public String validateFields(TextField userNameField, TextField emailField, TextField passwordField, TextField confirmPasswordField) {
       
        String userNameError = validateUserName(userNameField.getText());
        
        if (userNameError != null) {
            return userNameError;
        }

        String emailError = validateEmail(emailField.getText());
        if (emailError != null) {
            return emailError;
        }

        String passwordError = validatePasswords(passwordField.getText(), confirmPasswordField.getText());
        if (passwordError != null) {
            return passwordError;
        }

        return null; 
    }
    
    public String validateFields( TextField emailField, TextField passwordField) {
       
        String emailError = validateEmail(emailField.getText());
        if (emailError != null) {
            return emailError;
        }

        String passwordError = validatePasswords(passwordField.getText());
        if (passwordError != null) {
            return passwordError;
        }

        return null; 
    }
    
    

    private String validateUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return "Username cannot be empty.";
        }
        if (userName.length() > 20) {
            return "Username must be 20 characters or less.";
        }
        return null; 
    }

    private String validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return "Email cannot be empty.";
        }
        String emailRegex = "^[\\w-\\.+]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            return "Invalid email format.";
        }
        return null; 
    }

    private String validatePasswords(String password, String confirmPassword) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return "Confirm password cannot be empty.";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }
        return null; 
    }
    
     private String validatePasswords(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }
        return null; 
    }
}

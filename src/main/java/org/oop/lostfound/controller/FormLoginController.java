package org.oop.lostfound.controller;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import org.oop.lostfound.config.Session;
import org.oop.lostfound.dao.Connector;
import org.oop.lostfound.dao.LoginDAO;
import org.oop.lostfound.model.User;


public class FormLoginController
{
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Hyperlink registerHyperlink;
    @FXML private Button loginButton;
    
    // Logout untuk setiap launch
    @FXML
    public void initialize()
    {
        Session.setId(0);
        Session.setUsername(null);
    }

    @FXML
    private void usernameTextFieldOnAction(ActionEvent event)
    {
        passwordField.requestFocus();
    }

    @FXML
    private void passwordFieldOnAction(ActionEvent event) throws IOException
    {
        loginButtonOnAction(event);
    }

    @FXML
    private void registerHyperlinkOnAction(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormRegister.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(parent));
        stage.show();
    }
    @FXML
    private void loginButtonOnAction(ActionEvent event) throws IOException
    {
        String username = usernameTextField.getText();
        String user_password = passwordField.getText();
        if (username.isEmpty() || user_password.isEmpty())
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("PESAN PERINGATAN");
            alert.setHeaderText(null);
            alert.setContentText("Username atau Password tidak boleh kosong!");
            alert.showAndWait();
            return;
        }
        LoginDAO loginDAO = new LoginDAO(Connector.getConnection());
        User user = loginDAO.checkLogin(username, user_password);
            if (user != null)
            {
                Session.setId(user.getId());
                Session.setUsername(user.getUsername());

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("PESAN SUKSES");
                alert.setHeaderText(null);
                alert.setContentText("LOGIN BERHASIL");
                alert.showAndWait();
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/oop/lostfound/FormMenuUtama.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();
                
            } else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("PESAN ERROR");
                alert.setHeaderText(null);
                alert.setContentText("LOGIN GAGAL");
                alert.showAndWait();
                return;
            }
        
    }
}
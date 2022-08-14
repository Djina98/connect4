/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.login;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Djina
 */
public class FXMLLoginController {
    
    @FXML
    public Label lblNickname;
    
    @FXML
    public TextField txtNickname;

    @FXML
    public Label lblPassword;
    
    @FXML
    public PasswordField txtPassword;
   
    @FXML
    public Label lblInfo;
    
    @FXML
    public Button btnLogin;
    
    @FXML
    public Button btnSignup;

    public GUILoginController guiLoginController;
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        guiLoginController = new GUILoginController(this);      
    }    
}

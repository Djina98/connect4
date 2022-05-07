/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.signup;

import client.communication.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Djina
 */
public class GUISignupController {
    FXMLSignupController fXMLSignupController;

    public GUISignupController(FXMLSignupController fXMLSignupController) {
        this.fXMLSignupController = fXMLSignupController;
        this.fXMLSignupController.btnSignup.setOnAction(event -> onSignup());
    }
    
    private void onSignup() {
        String nickname = this.fXMLSignupController.txtNickname.getText();
        String password = this.fXMLSignupController.txtPassword.getText();
        String rePassword = this.fXMLSignupController.txtRePassword.getText();
            
        if(!password.equals(rePassword)) {
            this.fXMLSignupController.lblInfo.setText("Password mismatch");
        } else {
            try {
                Controller.getInstance().signup(nickname, password);
                //GameStage.getInstance().setScene("forms/login/FXMLLoginPane.fxml");
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.fXMLSignupController.lblInfo.setText(ex.getMessage());
            }    
        }     
    }    
    
}

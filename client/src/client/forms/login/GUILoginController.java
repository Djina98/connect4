/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.login;

import client.communication.Controller;
import client.main.GameStage;
import client.session.Session;
import common.domain.Player;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Djina
 */
public class GUILoginController {
    FXMLLoginController fxmlLoginController;

    public GUILoginController(FXMLLoginController fxmlLoginController) {
        this.fxmlLoginController = fxmlLoginController;
        this.fxmlLoginController.btnLogin.setOnAction(event -> onLogin());
        this.fxmlLoginController.btnSignup.setOnAction(event -> onSignup());
    }
    
    private void onSignup() {
        GameStage.getInstance().setScene("client/forms/signup/FXMLSignup.fxml");
    }    

    private void onLogin() {
        fxmlLoginController.btnLogin.setOnMouseClicked((event) -> {
            String nickname = fxmlLoginController.txtNickname.getText();
            String password = fxmlLoginController.txtPassword.getText();
            try {
                Player player = Controller.getInstance().login(nickname, password);
                Session.getInstance().setPlayer(player);
                GameStage.getInstance().setScene("client/forms/home/FXMLHome.fxml");
            } catch (Exception ex) {
                System.out.println(ex);
                fxmlLoginController.lblInfo.setText(ex.getMessage());
            }
        });
    }
}

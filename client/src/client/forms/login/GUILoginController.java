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
import org.apache.commons.codec.digest.DigestUtils;

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
            String password = DigestUtils.shaHex(fxmlLoginController.txtPassword.getText());
            try {
                Player player = Controller.getInstance().login(nickname, password);
                Session.getInstance().setPlayer(player);
                GameStage.getInstance().setScene("client/forms/game/FXMLGame.fxml");
            } catch (Exception ex) {
                System.out.println(ex);
                fxmlLoginController.lblInfo.setText(ex.getMessage());
            }
        });
    }
}

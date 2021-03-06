/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.login;

import client.communication.Controller;
import client.forms.game.FXMLGameController;
import client.main.GameStage;
import client.session.Session;
import common.domain.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
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
                FXMLGameController controller;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
		GridPane rootGridPane = loader.load();
		controller = loader.getController();
		controller.createContent();
                Scene scene = new Scene(rootGridPane);
                GameStage.getInstance().setScene(scene);
                
            } catch (Exception ex) {
                System.out.println(ex);
                fxmlLoginController.lblInfo.setText(ex.getMessage());
            }
        });
    }
}

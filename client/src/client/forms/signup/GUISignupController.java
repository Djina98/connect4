/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.signup;

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
public class GUISignupController {
    FXMLSignupController fxmlSignupController;

    public GUISignupController(FXMLSignupController fxmlSignupController) {
        this.fxmlSignupController = fxmlSignupController;
        this.fxmlSignupController.btnSignup.setOnAction(event -> onSignup());
        this.fxmlSignupController.btnLogin.setOnAction(event -> onLogin());
    }
    
    private void onSignup() {
        String nickname = this.fxmlSignupController.txtNickname.getText();
        String password = DigestUtils.shaHex(this.fxmlSignupController.txtPassword.getText());
        String rePassword = DigestUtils.shaHex(this.fxmlSignupController.txtRePassword.getText());
            
        if(!password.equals(rePassword)) {
            this.fxmlSignupController.lblInfo.setText("Password mismatch");
        } else {
            try {
                Player player = Controller.getInstance().signup(nickname, password);
                player.setNickname(nickname);
                player.setPassword(password);
                Session.getInstance().setPlayer(player);
//                FXMLGameController controller;
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
//		GridPane rootGridPane = loader.load();
//		controller = loader.getController();
//		controller.createContent();
//                Scene scene = new Scene(rootGridPane);
//                GameStage.getInstance().setScene(scene);
                GameStage.getInstance().setScene("client/forms/mode/FXMLGameMode.fxml");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                this.fxmlSignupController.lblInfo.setText(ex.getMessage());
            }    
        }     
    }    

    private void onLogin() {
        GameStage.getInstance().setScene("client/forms/login/FXMLLogin.fxml");
    }
    
}

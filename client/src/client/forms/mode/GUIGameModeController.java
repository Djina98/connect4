/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.mode;

import client.communication.Controller;
import client.forms.game.FXMLGameController;
import client.main.GameStage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Djina
 */
public class GUIGameModeController {
    
    FXMLGameModeController fxmlGameModeController;
    
    public GUIGameModeController(FXMLGameModeController fxmlGameModeController) {
        this.fxmlGameModeController = fxmlGameModeController;
        this.fxmlGameModeController.btnOtherPlayer.setOnAction(event -> playWithOtherPlayer());
        this.fxmlGameModeController.btnComputer.setOnAction(event -> playWithComputer());
    }

    private void playWithOtherPlayer() {
        fxmlGameModeController.btnOtherPlayer.setOnMouseClicked((event) -> {
            try {
                
                //int signal = Controller.getInstance().playWithOtherPlayer();
                FXMLGameController controller;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
		GridPane rootGridPane = loader.load();
		controller = loader.getController();
		controller.createContent();
                Scene scene = new Scene(rootGridPane);
                GameStage.getInstance().setScene(scene);
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }

    private void playWithComputer() {
        fxmlGameModeController.btnComputer.setOnMouseClicked((event) -> {
           
            try {
                //int signal = Controller.getInstance().playWithComputer();
                FXMLGameController controller;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
		GridPane rootGridPane = loader.load();
		controller = loader.getController();
		controller.createContent();
                Scene scene = new Scene(rootGridPane);
                GameStage.getInstance().setScene(scene);
                
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });
    }
}

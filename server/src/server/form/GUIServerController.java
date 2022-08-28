/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.form;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import server.settings.Constants;
import server.settings.PropertiesLoader;
import server.start.Server;

/**
 *
 * @author Djina
 */
public class GUIServerController {
    FXMLServerController fxmlServerController;
    private Server server;
    
    public GUIServerController(FXMLServerController fxmlServerController) {
        this.fxmlServerController = fxmlServerController;
        this.fxmlServerController.lblStatus.setText("Server is not listening");
        this.fxmlServerController.lblStatus.setTextFill(Color.color(1, 0, 0));
        this.fxmlServerController.btnStartServer.setOnAction(event -> onStartServer());
    }

    private void onStartServer() {
        fxmlServerController.btnStartServer.setOnMouseClicked((event) -> {
            if (server == null || !server.isAlive()) {
                    try {
                        server = new Server(Integer.parseInt(PropertiesLoader.getInstance().getProperty(Constants.PORT)));
                        server.start();

                        this.fxmlServerController.lblStatus.setText("Server is listening on port " + Integer.parseInt(PropertiesLoader.getInstance().getProperty(Constants.PORT)));
                        this.fxmlServerController.lblStatus.setTextFill(Color.color(0, 1, 0));
                        this.fxmlServerController.btnStartServer.setDisable(false);
                    } catch (IOException ex) {
                        showAlert("Port" + PropertiesLoader.getInstance().getProperty(Constants.PORT) + " is already taken.");
                    } 
                    
                } else {
                    showAlert("Server is already listening on this port.");
                }
        });
    }
    
    public void showAlert(String message) { 
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

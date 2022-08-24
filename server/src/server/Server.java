/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import server.settings.Constants;
import server.settings.PropertiesLoader;

/**
 *
 * @author Djina
 */
public class Server extends Application{
 
    @Override
    public void start(Stage stage) {
        stage.setTitle("Connect 4 Server");
        stage.setResizable(false);
        ServerStage.setStage(stage);
        ServerStage.getInstance().setScene("server/form/FXMLServer.fxml");
    }

    public static void main(String[] args) throws IOException, SQLException {
//        Server server = Server.getInstance();
//        server.start();
        launch(args);
    }
    
}

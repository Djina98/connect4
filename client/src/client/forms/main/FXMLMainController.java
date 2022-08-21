/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.main;

import client.forms.game.FXMLGameController;
import client.main.GameStage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Djina
 */
public class FXMLMainController {
    
    @FXML
    public MenuBar menuBarMainMenu;
    
    @FXML
    public MenuItem mnItmPlayGame;

    @FXML
    public MenuItem mnItmGameRules;
    
    @FXML
    public Label lblWelcome;
    
    @FXML
    public Label lblPlayer;
    
    public GUIMainController guiMainController;
    
    @FXML
    private void playGame(ActionEvent event) {
        try {
            FXMLGameController controller;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
            GridPane rootGridPane = loader.load();
            controller = loader.getController();
            controller.createContent();
            Scene scene = new Scene(rootGridPane);
            GameStage.getInstance().setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void gameRules(ActionEvent event) {
        GameStage.getInstance().setScene("client/forms/rules/FXMLGameRules.fxml");
    }
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        guiMainController = new GUIMainController(this);      
    }
}

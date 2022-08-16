/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
    
//    @FXML
//    public Label lblWelcome;
//    
//    @FXML
//    public Label lblPlayer;
    
    public GUIMainController guiMainController;
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        guiMainController = new GUIMainController(this);      
    }
}

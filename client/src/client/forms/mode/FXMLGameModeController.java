/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.mode;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author Djina
 */
public class FXMLGameModeController {
    
    @FXML
    public Button btnOtherPlayer;
    
    @FXML
    public Button btnComputer;

    public GUIGameModeController guiGameModeController;
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        guiGameModeController = new GUIGameModeController(this);      
    }
}

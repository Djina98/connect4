/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 *
 * @author Djina
 */
public class FXMLGameRulesController {
    
    @FXML
    public TextArea txtRules;
    
    @FXML
    public Button btnBack;
    
    public GUIGameRulesController guiGameRulesController;
    
    @FXML
    public void initialize() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, IOException, FileNotFoundException, ClassNotFoundException {
        guiGameRulesController = new GUIGameRulesController(this);      
    }
    
}

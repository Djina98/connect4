/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.main;

//import client.forms.game.EnemyMove;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Djina
 */
public class MainStage extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Connect4");
        stage.setResizable(false);
        GameStage.setStage(stage);
        GameStage.getInstance().setScene("client/forms/login/FXMLLogin.fxml");
        //EnemyMove enemyMove = new EnemyMove();
        //enemyMove.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

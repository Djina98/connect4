/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.main;

import client.session.Session;

/**
 *
 * @author Djina
 */
public class GUIMainController {
    
    FXMLMainController fxmlMainController;
    
    public GUIMainController(FXMLMainController fxmlMainController) {
        this.fxmlMainController = fxmlMainController;
        this.fxmlMainController.lblPlayer.setText(Session.getInstance().getPlayer().getNickname());
//        this.fxmlMainController.mnItmPlayGame.setOnAction(event -> playGame());
//        this.fxmlMainController.mnItmGameRules.setOnAction(event -> gameRules());

    }

//    private void playGame() {
//        fxmlMainController.mnItmPlayGame.setOnAction((event) -> {          
//            try {
//                FXMLGameController controller;
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/forms/game/FXMLGame.fxml"));
//		GridPane rootGridPane = loader.load();
//		controller = loader.getController();
//		controller.createContent();
//                Scene scene = new Scene(rootGridPane);
//                GameStage.getInstance().setScene(scene);
//                
//            } catch (Exception ex) {
//                System.out.println(ex);
//            }
//        });
//    }
//    
//    private void gameRules() {
//        fxmlMainController.mnItmGameRules.setOnAction((event) -> {
//            GameStage.getInstance().setScene("client/forms/rules/FXMLGameRules.fxml");
//        });
//    }
}

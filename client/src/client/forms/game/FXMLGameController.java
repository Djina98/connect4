/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.game;

import client.communication.Controller;
import client.main.GameStage;
import common.domain.Move;
import common.response.Response;
import common.response.ResponseStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Djina
 */
public class FXMLGameController {
    
    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    
    public static int PLAYER1_WON = 1; // Indicate player 1 won
    public static int PLAYER2_WON = 2; // Indicate player 2 won
    public static int DRAW = 3; // Indicate a draw
    public static int CONTINUE = 4; // Indicate to continue
    
    // Indicate whether the player has the turn
    private boolean myTurn = false;
    
    // Indicate selected row and column by the current move
    private int rowSelected;
    private int columnSelected;
    
    // Continue to play?
    private boolean continueToPlay = true;

    // Wait for the player to mark a cell
    private boolean waiting = true;
    
    // Create and initialize a status label
//    private Label lblStatus = new Label();
    
    private boolean redMove = false;
    
    private Disc[][] grid = new Disc[COLUMNS][ROWS];
       
    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane gamePane;
    
    @FXML
    public Pane infoPane;
    
    @FXML
    public Label lblStatus;
    
    @FXML
    public Label lblTitle;
    
    public Parent createContent() {
        Shape gridShape = makeGrid();
        rootGridPane.add(gridShape, 0, 0);
        rootGridPane.getChildren().addAll(makeColumns());
        
        return rootGridPane;
    }
    
    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }

        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);

        return shape;
    }
    
    private List<Rectangle> makeColumns() {
        connectToServerAgainstComputer();
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            rect.setOnMouseClicked((MouseEvent event) -> {
                try {
                    int row = Controller.getInstance().getAvailableRow(column);
                    if(row != -1){
                        System.out.println("Available row: " + row);
                        if(myTurn){
                            placeDisc(new FXMLGameController.Disc(redMove), column, row);

                            myTurn = false;
                            rowSelected = row;
                            columnSelected = column;
                            lblStatus.setText("Waiting for the computer to move");
                            waiting = false; 
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            list.add(rect);
        }

        return list;
    }  
    
    private void placeDisc(Disc disc, int column, int row) {
        grid[column][row] = disc;
        gamePane.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.INDEFINITE.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);

        animation.play();
    }
    
    private static class Disc extends Circle {
        private final boolean red;
        public Disc(boolean red) {
            super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;

            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
    }
     
     private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }
     
    private void connectToServerAgainstComputer() {
        new Thread(() -> {
            try {
                    redMove = true;
                    Platform.runLater(() -> {
                        lblTitle.setText("You are player with red token");
                    });

                    // It is my turn
                    myTurn = true;
                    Platform.runLater(() -> lblStatus.setText("Your turn"));

                // Continue to play
                while (continueToPlay) {
                        waitForPlayerAction(); // Wait for player 1 to move
                        Response response = sendMove(); // Send the move to the server
                        receiveInfoFromServer(response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
    }
     
    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(3000);
        }

        waiting = true;
    }

    private Response sendMove() {
        Response response = null;
        try {
            response = Controller.getInstance().sendMove(rowSelected, columnSelected);
        } catch (Exception ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
    }

    private void receiveInfoFromServer(Response response) {

        if (response.getStatus() == ResponseStatus.PLAYER1_WON) {
            // Player 1 won, stop playing
            continueToPlay = false;
                Platform.runLater(() -> showMessage("Congratulations", "You won!"));
                Platform.runLater(() -> GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else if (response.getStatus() == ResponseStatus.PLAYER2_WON) {
            // Player 2 won, stop playing
            continueToPlay = false;
                receiveMove((Move) response.getResult());
                Platform.runLater(() -> showMessage("Game over", "Computer has won!"));
                Platform.runLater(() ->GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else if (response.getStatus() == ResponseStatus.DRAW) {
            // No winner, game is over
            continueToPlay = false;
            Platform.runLater(() -> showMessage("Game over", "No winner!"));
            Platform.runLater(() ->GameStage.getInstance().setScene("client/forms/main/FXMLMain.fxml"));
        } else {
            receiveMove((Move) response.getResult());
            Platform.runLater(() -> lblStatus.setText("Your turn"));
            myTurn = true; // It is my turn
        }
    }
    
    private void receiveMove(Move move) {
        try {
            int row = move.getRow();
            int column = move.getCol();
            System.out.println("Server move: " + row + " " + column);
            Platform.runLater(() -> placeDisc(new FXMLGameController.Disc(!redMove), column, row));
        } catch (Exception ex) {
            Logger.getLogger(FXMLGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showMessage(String header, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
    }

}

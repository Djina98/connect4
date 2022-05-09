/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.forms.game;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author Djina
 */
public class FXMLGameController extends Application{
    
    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    
    private boolean redMove = true;
       
    @FXML
    public GridPane rootGridPane;

    @FXML
    public Pane gamePane;

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
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            //final int column = x;
            //rect.setOnMouseClicked(e -> placeDisc(new Game.Disc(redMove), column));

            list.add(rect);
        }

        return list;
    }  
    
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}

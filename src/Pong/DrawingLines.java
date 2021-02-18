package Pong;/************************************************************************
 Made by        PatrickSys
 Date           18/02/2021
 Package        Pong
 Description:
 ************************************************************************/

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class DrawingLines extends Application {

    int WIDTH = 800;
    int HEIGTH = 800;

    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage stage) {

        stage.setTitle("Hello World Application");

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGTH, Color.BLACK);



        //Common Line

        Line line = new Line(WIDTH/2, HEIGTH, WIDTH/2, 0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(3);
        line.getStrokeDashArray().addAll(10d);



        root.getChildren().add(line);



        stage.setScene(scene);
        stage.show();

    }
}

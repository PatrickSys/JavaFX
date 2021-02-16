package Pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/


public class MyPong extends Application {



    public static Pane canvas;
    double WIDTH = 800;
    double HEIGHT = 600;
    int radi =15;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        canvas = new Pane();
        final Scene scene = new Scene(canvas, WIDTH, HEIGHT);


        //create scene, with pane
        primaryStage.setTitle("P O N G");
        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.requestFocus();

        //create rectangles
        int rectHeight=120;
        int rectWidth=15;
        RectangleTeclat rectangle1 = new RectangleTeclat(canvas, rectWidth,((int) HEIGHT -rectHeight)/2, rectHeight, rectWidth, "red");
        RectangleTeclat rectangle2 = new RectangleTeclat(canvas, ((int) WIDTH -rectWidth*2), ((int) HEIGHT -rectHeight)/2, rectHeight, rectWidth, "chartreuse");


        //create the ball
        Ball ball = new Ball(canvas, radi, Color.BLUE);
        ball.circle.relocate((WIDTH /2)-radi, (HEIGHT /2)-radi);


        //add shapes to pane
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(rectangle1, rectangle2);


        canvas.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case W:
                  if(rectangle1.checkRectangleLimits()!=1) {
                      rectangle1.moveUp();
                  }
                    break;
                case S:

                    if(rectangle1.checkRectangleLimits()!=-1) {
                        rectangle1.moveDown();
                    }

                    break;

                case UP:
                 if(rectangle2.checkRectangleLimits()!=1) {
                        rectangle2.moveUp();
                    }
                    break;
                case DOWN:

                    if(rectangle2.checkRectangleLimits()!=-1) {
                        rectangle2.moveDown();
                    }
                    break;

            }
        });


        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {



            @Override
            public void handle(final ActionEvent t) {

                boolean alreadyMoved = false;

                ball.moveBall();
                checkCollision(ball, rectangle1, rectangle2);

                }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }


    private void checkCollision(Ball ball, RectangleTeclat rect1, RectangleTeclat rect2){


        if(ball.circle.getBoundsInParent().intersects(rect1.rectangle.getBoundsInParent())||ball.circle.getBoundsInParent().intersects(rect2.rectangle.getBoundsInParent())){
            ball.deltaX *= -1;
            ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);
            //ball.setVelocitat(ball.velocitat +=0.1);
        }



    }






}


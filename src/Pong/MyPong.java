package Pong;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
    double amp = 800;
    double alt = 600;
    int radi =15;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        canvas = new Pane();
        final Scene scene = new Scene(canvas, amp, alt);


        //Cream escena, amb el panell, i mesures
        primaryStage.setTitle("P O N G");
        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.requestFocus();

        //cream rectangles
        int altura=120;
        int gruix=15;
        RectangleTeclat rectangle1 = new RectangleTeclat(canvas, gruix,((int)alt-altura)/2, altura, gruix, "red");
        RectangleTeclat rectangle2 = new RectangleTeclat(canvas, ((int)amp-gruix*2), ((int)alt-altura)/2, altura, gruix, "chartreuse");


        //cream bolla
        Ball ball = new Ball(canvas, radi, Color.BLUE);
        ball.circle.relocate((amp/2)-radi, (alt/2)-radi);


        //afegim al panell
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(rectangle1, rectangle2);


        canvas.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case W:
                  if(rectangle1.checkRectangleLimits()!=1) {
                      rectangle1.mouAmunt();
                  }
                    break;
                case S:

                    if(rectangle1.checkRectangleLimits()!=-1) {
                        rectangle1.mouAbaix();
                    }

                    break;

                case UP:
                 if(rectangle2.checkRectangleLimits()!=1) {
                        rectangle2.mouAmunt();
                    }
                    break;
                case DOWN:

                    if(rectangle2.checkRectangleLimits()!=-1) {
                        rectangle2.mouAbaix();
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


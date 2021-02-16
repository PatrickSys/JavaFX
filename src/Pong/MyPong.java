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
    int paddleHeight = 120;
    int paddleWidth = 15;
    int paddle1PosX = 20;
    int paddle2PosX = 760;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        canvas = new Pane();
        final Scene scene = new Scene(canvas, WIDTH, HEIGHT);


        //create scene, with pane
        primaryStage.setTitle("P O N G");
        canvas.setStyle("-fx-background-color: BLACK ");
        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.requestFocus();


        //create players with it's paddles
        Player player1 = new Player(canvas, paddle1PosX, paddleHeight, paddleWidth);
        Player player2 = new Player(canvas,paddle2PosX, paddleHeight, paddleWidth);


        //create the ball
        Ball ball = new Ball(canvas, radi, Color.BLUE);
        ball.circle.relocate((WIDTH /2)-radi, (HEIGHT /2)-radi);


        //add shapes to pane
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(player1.paddle, player2.paddle);


        //case on paddle moves
        canvas.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case W:
                  if(player1.paddle.checkPaddleBounds()!=1) {
                      player1.paddle.moveUp();
                  }
                    break;
                case S:

                    if(player1.paddle.checkPaddleBounds()!=-1) {
                        player1.paddle.moveDown();
                    }

                    break;

                case UP:
                 if(player2.paddle.checkPaddleBounds()!=1) {
                        player2.paddle.moveUp();
                    }
                    break;
                case DOWN:

                    if(player2.paddle.checkPaddleBounds()!=-1) {
                        player2.paddle.moveDown();
                    }
                    break;

            }
        });


        //thread main is calling moveBall while checking collisions between paddles and the ball
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {


                ball.moveBall();
                checkCollision(ball, player1.paddle);
                checkCollision(ball, player2.paddle);

                }
        }));


        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }

    //Checks collision between paddles and ball
    private void checkCollision(Ball ball, Paddle paddle){



        if(ball.circle.getBoundsInParent().intersects(paddle.paddle.getBoundsInParent())){

            System.out.println(paddle.getBotPosition());
            System.out.println(ball.circle.getLayoutX() + " ball X");
            System.out.println(ball.circle.getRadius() + " circ rad");


            //check if the ball touches paddle bot or top  then reverse Y
            if(ball.circle.getLayoutX()<paddle1PosX+paddleWidth+ball.circle.getRadius() || ball.circle.getLayoutX() > paddle2PosX - ball.circle.getRadius()){
                System.out.println("TOCA ");
                ball.deltaY *= -1;
            }

            else{
                ball.deltaX *= -1;

            }

            ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);

        }

    }


    private void checkGoal(Ball ball){



    }






}


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


import java.io.File;




/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/


public class MyPong extends Application {


    /**
     * Attributes
     */
    public static Pane canvas;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int radius = 10;
    private final int paddleHeight = 100;
    private final int paddleWidth = 10;
    private final int paddle1PosX = 20;
    private final int paddle2PosX = 760;
    private final int maxPoints = 20;

    //bouncing attributes, see below
    int leftBounceID = 50;
    int rightBounceID = 100;
    int bounceID = 0;
    int collisionCounter = 0;






    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage) {

        //create scene, with pane
        canvas = new Pane();
        final Scene scene = new Scene(canvas, WIDTH, HEIGHT);

        //create players with it's paddles
        Player player1 = new Player(canvas, paddle1PosX, paddleHeight, paddleWidth);
        Player player2 = new Player(canvas, paddle2PosX, paddleHeight, paddleWidth);



        primaryStage.setTitle("P O N G");
        canvas.setStyle("-fx-background-color: BLACK");
        primaryStage.setScene(scene);
        primaryStage.show();

        //sets points labels to 0
        player1.settlePoints(player1.player1Position);
        player2.settlePoints(player2.player2Position);


        //create the ball
        Ball ball = new Ball(canvas, radius, Color.BLUE, 0.6);


        ball.circle.relocate((WIDTH /2)- radius, (HEIGHT /2)- radius);



        //add shapes to pane
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(player1.paddle, player2.paddle);
        canvas.getChildren().addAll(player1.playerPoints, player2.playerPoints);
        canvas.requestFocus();

        //case on paddle moves
        canvas.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case W -> player1.paddle.moveUp();
                case S -> player1.paddle.moveDown();
                case UP -> player2.paddle.moveUp();
                case DOWN -> player2.paddle.moveDown();
            }
        });


        //thread main is calling moveBall while checking collisions between paddles and the ball
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(3), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {

                play(ball, player1, player2);
                
                }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }


    /**
     * Main method play, moves the ball while checking if a goal is scored
     * checks collisions
     */
    private void play(Ball ball, Player player1, Player player2){

        //invoke moveBall then play around it
        int scored = ball.moveBall();
        onGoal(scored, ball, player1, player2);
        checkCollision(ball, player1.paddle);
        checkCollision(ball, player2.paddle);

    }

    /**
     *Checks collision between paddle and ball
     *if top or bottom of the paddle are hit, it only reverses Y
     */
    private void checkCollision(Ball ball, Paddle paddle){
        //System.out.println(ball.deltaY + "there");

        if(collisionCounter == 0){
            ball.randomDeltaY(2);
            collisionCounter++;

        }

        //if bounds intersect means they're colliding
        if(ball.circle.getBoundsInParent().intersects(paddle.paddle.getBoundsInParent())){



            //plays bounce sound
            File bounce = new File("C:\\Users\\bitaz\\IdeaProjects\\JavaFX\\src\\PongSounds\\PaddleBounce.wav");
            PongSounds.Sounds.playSound(bounce);


            //if ball touches any side of the paddle only Y is inverted
            if(ball.circle.getLayoutX()+1 < paddle1PosX + paddleWidth + ball.circle.getRadius() || ball.circle.getLayoutX()-1 > paddle2PosX - ball.circle.getRadius()){
                System.out.println("TOCA ");
                ball.deltaY *= -1;
            }

            else{

                //if right paddle is hit, the bounce ID is assigned to the right bounce ID
                if(ball.circle.getLayoutX() > canvas.getWidth()/2 && rightBounceID != bounceID){
                    System.out.println("toca paleta dreta");
                    rightBounceID++;
                    bounceID=rightBounceID;
                }
                else{

                    //left bounce ID is assigned
                    System.out.println("toca paleta esquerra");
                    leftBounceID++;
                    bounceID=leftBounceID;
                }
                //ball trajectory is reversed anyways
                ball.deltaX *= -1;
            }

            //every 5 hits the speed is increased
            if(((leftBounceID-50)+(rightBounceID-100)) %5 == 0) {
                ball.increaseSpeed(1.2);
            }

            //relocates it
            ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);


            //updates collisionCounter if a collision was detected. Increase the speed at the first collision
            collisionCounter++;
            if(collisionCounter == 2){
                ball.increaseSpeed(2);
                collisionCounter++;
            }

        }
        //System.out.println(ball.getSpeed());
        //System.out.println(ball.deltaX + " deltaX");
        //System.out.println(collisionCounter);

    }



    /**
     *Checks goal for play method. Remember scored was a return value
     *from checkBallBounds method in ball class
     *where return depends on whose player scored
     */
    private void onGoal(int scored, Ball ball, Player player1, Player player2){

        //if any player wins sets final scene
        if(player1.points == this.maxPoints){

            System.out.println( "Player 1 won!");

            try {
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }

        if(player2.points == this.maxPoints){

            System.out.println( "Player 2 won!");

            try{
                Thread.sleep(10);
            }catch (Exception e){

            }
        }
        }


        //any player scored:
        if(scored !=0){

            //sleeps the thread
            try {
                Thread.sleep(400);
                ball.circle.relocate(this.WIDTH/2,this.HEIGHT/2);
                resetBouncesIDs();
                ball.resetSpeed();
                collisionCounter = 0;

            }catch (Exception e){
                e.printStackTrace();
            }

            if(scored == 1){

                System.out.println( "Player 1 Goal" );
                player1.points++;
                player1.updatePoints();

            }

            if(scored == 2){

                System.out.println( "Player 2 Goal" );
                player2.points++;
                player2.updatePoints();

            }

            System.out.println(ball.deltaY);

        }

    }

    //reset Bounces IDs to "stock"
    private void resetBouncesIDs(){
        leftBounceID = 50;
        rightBounceID = 100;
        bounceID = 0;
    }


}


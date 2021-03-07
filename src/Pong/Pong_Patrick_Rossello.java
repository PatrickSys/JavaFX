package Pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/


public class Pong_Patrick_Rossello extends Application {


    /**
     * Attributes
     */
    private static Pane canvas;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int radius = 10;
    private final int paddleHeight = 80;
    private final int paddleWidth = 10;
    private final int paddle1PosX = paddleWidth*2;
    private final int paddle2PosX = WIDTH-(paddleWidth*3);
    private final int maxPoints = 15;
    private int endCounter = 0;
    private  Font font;

    //bouncing attributes, see below
    private int leftBounceID = 50;
    private int rightBounceID = 100;
    private int bounceID = 0;
    private int collisionCounter = 0;


    //Sound files
    File paddleBounce = new File(getClass().getClassLoader().getResource("PaddleBounce.wav").getPath());
    File goalSound = new File(getClass().getClassLoader().getResource("GoalSound.wav").getPath());

    //Labels
    Label startLabel = new Label("Press SPACE to start");
    Label endLabel = new Label("Game Over");



    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage){

        //Create scene, with pane.
        canvas = new Pane();
        Scene game = new Scene(canvas, WIDTH, HEIGHT);
        setFont();

        //create players with it's paddles
        Player player1 = new Player(canvas, paddle1PosX, paddleHeight, paddleWidth);
        Player player2 = new Player(canvas, paddle2PosX, paddleHeight, paddleWidth);

        //set stage
        primaryStage.setTitle("P O N G");
        canvas.setStyle("-fx-background-color: BLACK");
        primaryStage.setScene(game);
        primaryStage.show();


        //sets points labels to 0
        player1.settlePoints(player1.getPlayer1Position());
        player2.settlePoints(player2.getPlayer2Position());

        //create the ball
        Ball ball = new Ball(canvas, radius, Color.WHITE, 0.8);

        //sets ball with decreased velocity
        ball.circle.relocate((WIDTH /2)- radius, (HEIGHT /2)- radius);
        ball.firstServe(2);

        //add shapes to pane
        addLine();
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(player1.paddle, player2.paddle);
        canvas.getChildren().addAll(player1.playerPoints, player2.playerPoints);

        //set the start Label "press Space to play"
        showLabel(startLabel, WIDTH /3.3);
        canvas.requestFocus();

        //case on space to start the game
        canvas.setOnKeyPressed(e -> {



            //Per què comença bolla a un cantó?
            if (e.getCode()== KeyCode.SPACE) {


                //removes start label
                canvas.getChildren().remove(startLabel);

                //case on paddle moves
                canvas.setOnKeyPressed(i -> {

                    switch (i.getCode()) {
                        case W -> player1.paddle.moveUp();
                        case S -> player1.paddle.moveDown();
                        case UP -> player2.paddle.moveUp();
                        case DOWN -> player2.paddle.moveDown();
                    }
                });

                //thread main is calling play method
                final Timeline loop = new Timeline(new KeyFrame(Duration.millis(2), new EventHandler<ActionEvent>() {

                    @Override
                    public  void handle(final ActionEvent t) {

                        play(ball, player1, player2);

                    }
                }));

                loop.setCycleCount(Timeline.INDEFINITE);
                loop.play();

            }
        });


    }


    /**
     * Play method, which takes the return from moveball
     * then passes it to onGoal to know the status of goals
     * also checks collision between paddles and ball
     */
    private void play(Ball ball, Player player1, Player player2){

        //invoke moveBall then play around it
        int scored = ball.moveBall();
        onGoal(scored, ball, player1, player2);
        checkCollision(ball, player1.paddle);
        checkCollision(ball, player2.paddle);

    }

    /**
     * Checks collision between paddle and ball
     * if top or bottom of the paddle are hit, it only reverses Y
     * updates collision counter at each collision to avoid more than one collision on the same paddle
     * gives gives the first collision a speed boost as it's been decreased after spawning the ball
     * increases ball speed every 5 hits
     */
    private void checkCollision(Ball ball, Paddle paddle){


        //if bounds intersect means they're colliding
        if(ball.circle.getBoundsInParent().intersects(paddle.paddle.getBoundsInParent())){

            //updates collisionCounter if a collision was detected. Increase the speed at the first collision
            collisionCounter++;

            //at first bounce sets speed again to stock
            if(collisionCounter == 1){
                ball.increaseSpeed(2);
                collisionCounter++;
            }


            //if ball touches any side of the paddle only Y is inverted
            if(ball.circle.getLayoutX()+1 < paddle1PosX + paddleWidth + ball.circle.getRadius() || ball.circle.getLayoutX()-1 > paddle2PosX - ball.circle.getRadius()){
                ball.deltaY *= -1;
            }

            else{

                //if right paddle is hit, the bounce ID is assigned to the right bounce ID
                if(ball.circle.getLayoutX() > canvas.getWidth()/2 && rightBounceID != bounceID){
                    playSound(paddleBounce);


                    rightBounceID++;
                    bounceID=rightBounceID;
                }
                else{

                    //left bounce ID is assigned
                    playSound(paddleBounce);
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

        }

    }


    /**
     * Checks goal for play method. Remember scored was a return value
     * from checkBallBounds method in ball class where return depends on whose player scored
     * at maxPoints reached it ends the game
     */
    private void onGoal(int scored, Ball ball, Player player1, Player player2) {


        //if any player wins sets final scene
        if(player1.getPoints() == this.maxPoints || player2.getPoints() == this.maxPoints) {

            endGame(ball);

        }

        //any player scored:
        if(scored !=0){

            //sleeps the thread to give room for the player to react
            try {
                Thread.sleep(50);

            }catch (Exception e){
                e.printStackTrace();
            }

            if(scored == 1){

                addGoal(player1, ball);
                ball.randomLocate(2,'+');
            }

            if(scored == 2){

                addGoal(player2, ball);
                ball.randomLocate(2, '-');
            }

        }
    }

    //reset Bounces IDs to "stock"
    private void resetBouncesIDs(){
        leftBounceID = 50;
        rightBounceID = 100;
        bounceID = 0;
    }

    //add stroke dashed line
    private void addLine(){

        Line line = new Line(WIDTH/2, HEIGHT, WIDTH/2, 0);
        line.setStroke(Color.WHITE);
        line.setStrokeWidth(2);
        line.getStrokeDashArray().addAll(10d);
        canvas.getChildren().add(line);

    }

    /**
     * plays the goalSound, updates the player points and it's label and
     * resets the "scene"
     */
    private void addGoal(Player player, Ball ball){

        playSound(goalSound);
        player.goal();
        player.updatePoints();
        collisionCounter = 0;
        ball.circle.relocate(this.WIDTH/2,this.HEIGHT/2);
        resetBouncesIDs();
        ball.resetSpeed();

    }


    /**
     * When we want to finish the game, it shows the "Game Over" label
     * stops the ball (to avoid weird effect between thread sleep and Platform exit
     * where the ball would move.
     * then exits the game.
     */

    private void endGame(Ball ball) {

        //counter to help the label show up before sleeping the thread
        if (endCounter == 0) {
            showLabel(endLabel, WIDTH / 2.7 );

            ball.stopBall();
        }

        else if (endCounter == 30 ) {
            try {

                Thread.sleep(500);
                Platform.exit();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        endCounter++;
    }

    //plays the sound
    public static void playSound(File Sound) {

        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to add labels and set it's font
    public void showLabel(Label label, double Xlocation){

            canvas.getChildren().addAll(label);
            label.setTextFill(Color.WHITE);
            label.relocate(Xlocation, HEIGHT/3);
            label.setFont(this.font);

        }

        //creates the font and sets the class font
        public void setFont(){
            InputStream fontStream = getClass().getClassLoader().getResourceAsStream("pixelFont.otf");
            if (fontStream != null) {
                this.font = Font.loadFont(fontStream, 40);

                try {
                    fontStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //getter
    public Font getFont() {
        return font;
    }
}


package Pong;

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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/


public class MyPong extends Application {

    class Ball {
        public double deltaX;
        public double deltaY;
        Circle circle;
        public Ball(int radi,Color color) {
            this.circle=new Circle(radi, color);
            this.deltaX=1;
            this.deltaY=1;
        }
    }

    public static Pane canvas;
    double amp = 480;
    double alt = 480;
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
        RectangleTeclat rectangle1 = new RectangleTeclat(canvas, 10,90, altura, gruix, "red");
        RectangleTeclat rectangle2 = new RectangleTeclat(canvas, 220, 90, altura, gruix, "chartreuse");


        Ball ball = new Ball(radi, Color.BLUE);
        ball.circle.relocate(240-radi, 240-radi);


        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(rectangle1, rectangle2);


        canvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    rectangle2.mouAmunt();
                    break;
                case DOWN:
                    rectangle2.mouAbaix();
                    break;
                case W:
                    rectangle1.mouAmunt();
                    break;
                case S:
                    rectangle1.mouAbaix();
                    break;
            }
        });

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(2000), new EventHandler<ActionEvent>() {




            // Formula en radians
            //double deltaX = 3*Math.cos(Math.PI/3);
            //double deltaY = 3*Math.sin(Math.PI/3);

            // Formula en graus
            double angle_en_radians =Math.toRadians(30);
            int velocitat=9;
            double deltaX = velocitat*Math.cos(angle_en_radians);
            double deltaY = velocitat*Math.sin(angle_en_radians);

            // Simulació gravitatòria
            double temps=0;


            final Bounds limits = canvas.getBoundsInLocal();

            @Override
            public void handle(final ActionEvent t) {
                boolean alreadyMoved = false;


                ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);
                ball.circle.setLayoutY(ball.circle.getLayoutY() + ball.deltaY);



                checkRectangleLimits(rectangle1, limits);
                checkRectangleLimits(rectangle2,limits);


                checkBallLimits(ball, limits);
                while(!alreadyMoved) {
                    checkCollision(ball, rectangle1, rectangle2);
                    alreadyMoved = true;
                }

                //System.out.println(rectangle1.getLayoutY());
                System.out.println("rect1 X : " + rectangle1.posicio.posX + " posY: " + rectangle1.posicio.posY );

                //System.out.println(rectangle2.getLayoutY());
                //System.out.println("rect2 X : " + rectangle2.posicio.posX + " posY: " + rectangle2.posicio.posY );
                }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
    }


    private void checkCollision(Ball ball, RectangleTeclat rect1, RectangleTeclat rect2){


        if(ball.circle.getBoundsInParent().intersects(rect1.rectangle.getBoundsInParent())||ball.circle.getBoundsInParent().intersects(rect2.rectangle.getBoundsInParent())){
            ball.deltaX *= -1;
            ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);
        }



    }

    private void checkBallLimits(Ball ball, Bounds limits){


        final boolean alLimitDret = ball.circle.getLayoutX() >= (limits.getMaxX() - ball.circle.getRadius());
        final boolean alLimitEsquerra = ball.circle.getLayoutX() <= (limits.getMinX() + ball.circle.getRadius());
        final boolean alLimitInferior = ball.circle.getLayoutY() >= (limits.getMaxY() - ball.circle.getRadius());
        final boolean alLimitSuperior = ball.circle.getLayoutY() <= (limits.getMinY() + ball.circle.getRadius());


        if (alLimitDret || alLimitEsquerra) {

            // Multiplicam pel signe de deltaX per mantenir la trajectoria


            ball.deltaX *= -1;
        }
        if (alLimitInferior || alLimitSuperior) {

            // Multiplicam pel signe de deltaX per mantenir la trajectoria
            ball.deltaY *= -1;
        }
    }

    private int checkRectangleLimits(RectangleTeclat rectangle, Bounds limits){

        int limitReached=0;
        final boolean alLimitInferior = rectangle.rectangle.getLayoutY() >= (limits.getMaxY() - rectangle.rectangle.getLayoutY());
        final boolean alLimitSuperior =  rectangle.rectangle.getLayoutY() <= (limits.getMinY() + rectangle.rectangle.getLayoutY());
//-180 adalt, 180 abaix


        if(alLimitInferior){
            limitReached = -1;
        }
        if(alLimitSuperior){
            limitReached = +1;
        }
        return limitReached;
    }
    }


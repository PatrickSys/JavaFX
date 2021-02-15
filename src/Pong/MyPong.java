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

import static java.lang.StrictMath.abs;

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
        public double velocitat=1;
        public Ball(int radi,Color color) {
            this.circle=new Circle(radi, color);
            this.deltaX=velocitat;
            this.deltaY=velocitat;
        }
        private void setVelocitat(double velocitat){
            this.deltaX=velocitat;
            this.deltaY=velocitat;
        }
    }

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
        Ball ball = new Ball(radi, Color.BLUE);

        ball.circle.relocate((amp/2)-radi, (alt/2)-radi);


        //afegim al panell
        canvas.getChildren().addAll(ball.circle);
        canvas.getChildren().addAll(rectangle1, rectangle2);


        canvas.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case W:
                  if(checkRectangleLimits(rectangle1)!=1) {
                      rectangle1.mouAmunt();
                  }
                    break;
                case S:

                    if(checkRectangleLimits(rectangle1)!=-1) {
                        rectangle1.mouAbaix();
                    }

                    break;

                case UP:
                 if(checkRectangleLimits(rectangle2)!=1) {
                        rectangle2.mouAmunt();
                    }
                    break;
                case DOWN:

                    if(checkRectangleLimits(rectangle2)!=-1) {
                        rectangle2.mouAbaix();
                    }
                    break;

            }


        });


        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {







            @Override
            public void handle(final ActionEvent t) {




                boolean alreadyMoved = false;

                moveBall(ball);
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

    private void checkBallLimits(Ball ball){
        final Bounds limits = canvas.getBoundsInLocal();
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
    private void moveBall(Ball ball){

        //comprova limits i recoloca bolla
        checkBallLimits(ball);
        ball.circle.setLayoutX(ball.circle.getLayoutX() + ball.deltaX);
        ball.circle.setLayoutY(ball.circle.getLayoutY() + ball.deltaY);

    }

    private int checkRectangleLimits(RectangleTeclat rectangle){

        //comprova els límits dels rectangles
        final boolean alLimitSuperior = -alt >= ((rectangle.rectangle.getLayoutY())*2-rectangle.altura);
        final boolean alLimitInferior = 0 <=((rectangle.rectangle.getLayoutY())-2*rectangle.altura);


        //retornam segons el limit trobat
        if(alLimitSuperior){
            System.out.println("limit superior");

            return +1;
        }

        if(alLimitInferior){

            System.out.println("limit inferior");
            return -1;
        }

        else {
            return 0;
        }
    }

}


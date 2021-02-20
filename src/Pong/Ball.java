package Pong;
/************************************************************************
 Made by        PatrickSys
 Date           14/02/2021
 Package        Pong
 Description:
 ************************************************************************/


import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.util.Random;


public class Ball{
        public double deltaX;
        public double deltaY;
        Circle circle;
        private double speed;
        private final Pane canvas;
        private double increasedSpeed = 1;
        public double stockSpeed;
        File wallSound = new File(getClass().getClassLoader().getResource("WallSound.wav").getPath());



        public Ball(Pane canvas, int radi, Color color, double speed) {
                this.circle = new Circle(radi, color);
                this.canvas = canvas;
                this.speed = speed;
                this.deltaX = speed;
                this.deltaY = speed / 1.5;
                this.stockSpeed = speed;
        }


        public int moveBall(){

                //check limits and relocates ball
                int limit = checkBallBounds();
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);
                return limit;
        }

        private int checkBallBounds(){
                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean rightLimit = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                final boolean leftLimit = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean botLimit = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                final boolean topLimit = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());
                int limit = 0;


                if (rightLimit) {
                        limit = 1;
                }
                if(leftLimit){
                        limit = 2;
                }
                if (botLimit || topLimit) {

                        MyPong.playSound(wallSound);
                        // Inverse Y to mantain trajectory
                        deltaY *= -1;
                }
                return limit;
        }


        public void increaseSpeed(double increase){

                deltaY *= increase;
                deltaX *= increase;
                increasedSpeed *= increase;

                  }
        public void resetSpeed(){
                deltaY /= increasedSpeed;
                deltaX /= increasedSpeed;
                increasedSpeed = 1;
        }



        //sets a random DeltaY and spawn for the ball
        public void randomLocate(double decrease, char trajectory) {
                Random random = new Random();
                int min = -30;
                int max = 30;
                double angle = (random.nextInt(max-min) + min);
                double delta = angle/100;
                this.speed = stockSpeed / decrease;

                if(trajectory =='+') {
                        this.deltaX = stockSpeed/decrease;
                }
                else if(trajectory =='-'){
                        this.deltaX = -stockSpeed/decrease;
                }
                this.circle.relocate(canvas.getWidth()/2, (canvas.getHeight()*0.8) *Math.random());
                this.deltaY = delta;

        }


         //Sets ball speed to 0 then relocates it to center, in purpose of making it "perfect"
        public void stopBall(){
                this.deltaX = 0;
                this.deltaY = 0;
                circle.relocate((canvas.getWidth() /2)- circle.getRadius(), (canvas.getHeight() /2)- circle.getRadius());
        }

        //gives offset on first serve
        public void firstServe(double offSet){
                this.speed = stockSpeed/offSet;
                this.deltaX = stockSpeed/offSet;
                this.deltaY = -0.25 ;
        }



}



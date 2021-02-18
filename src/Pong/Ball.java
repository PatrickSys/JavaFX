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

import java.util.Random;


public class Ball{
        public double deltaX;
        public double deltaY;
        Circle circle;
        private double speed;
        Pane canvas;
        double increasedSpeed = 1;
        public double stockSpeed;



        public Ball(Pane canvas, int radi, Color color, double speed) {
                this.circle = new Circle(radi, color);
                this.canvas = canvas;
                this.speed = speed;
                this.deltaX = speed;
                this.deltaY = speed/1.5;
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
                        // Inverse Y to mantain trajectory
                        deltaY *= -1;
                }
                return limit;
        }


        //on develope
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



        //sets a random DeltaY for the ball
        public void randomDeltaY(double decrease) {
                Random random = new Random();
                int min = -30;
                int max = 30;
                double angle = (random.nextInt(max-min) + min);
                double delta = angle/100;
                this.speed = stockSpeed / decrease;
                this.deltaX = stockSpeed / decrease;
                this.deltaY = delta;
                System.out.println(delta);
        }

        public double getSpeed() {
                return speed;
        }

        public double getStockSpeed() {
                return stockSpeed;
        }
}



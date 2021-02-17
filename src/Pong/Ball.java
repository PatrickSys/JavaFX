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

import static java.lang.StrictMath.abs;

public class Ball{
        public double deltaX;
        public double deltaY;
        Circle circle;
        public double speed = 0.6;
        Pane canvas;
        double increasedSpeed;



        public Ball(Pane canvas, int radi,Color color) {
                this.circle = new Circle(radi, color);
                this.deltaX = speed;
                this.deltaY = speed;
                this.canvas=canvas;
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

                        // Inverse X to mantain trajectory

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
        public void setSpeed(double increase){


                  }

        }


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

        public class Ball{
        public double deltaX;
        public double deltaY;
        Circle circle;
        public double speed =1;
        Pane canvas;



        public Ball(Pane canvas, int radi,Color color) {
                this.circle = new Circle(radi, color);
                this.deltaX = speed;
        this.deltaY = speed;
        this.canvas=canvas;
        }



        public void moveBall(){

                //check limits and relocates ball
                checkBallLimits();
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);
        }

        private void checkBallLimits(){
                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean rightLimit = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
                final boolean leftLimit = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
                final boolean botLimit = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
                final boolean topLimit = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());


                if (rightLimit || leftLimit) {

                        // Inverse X to mantain trajectory
                        deltaX *= -1;
                }
                if (botLimit || topLimit) {

                        // Inverse X to mantain trajectory
                        deltaY *= -1;
                }
        }


        //on develope
        private void setSpeed(double speed){
                this.deltaX= speed;
                this.deltaY= speed;
                  }
        }


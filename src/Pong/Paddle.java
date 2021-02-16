package Pong;


/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;


public class Paddle extends Rectangle {


    class Position {

        int posX;
        int posY;

        public Position(int x, int y) {
            this.posX = x;
            this.posY = y;
        }
    }

    Position position;


    int speed = 15;
    Pane canvas;
    Node paddle;
    int width;
    int height;
    int topPosition;



    public Paddle(Pane canvas, int height, int width, int posX, int posY,String color) {
        position = new Position(posX, posY);
        this.canvas = canvas;
        this.paddle = new Rectangle(position.posX, position.posY, width, height);
        this.height = height;
        this.width = width;
        position.posX = (int)paddle.getLayoutX();
        position.posY = (int)paddle.getLayoutY();
        this.paddle.setLayoutX(position.posX);
        this.paddle.setLayoutY(position.posY);
        this.canvas.getChildren().add(this.paddle);
        this.paddle.setStyle("-fx-fill:" + color + ";");
        this.topPosition = posY + height/2;

    }

    /**
     * Move rectangle up
     */
    public void moveUp() {
        position.posY = position.posY - this.speed;
        this.repaint();
    }

    /**
     * Move rectangle down
     */
    public void moveDown() {
        position.posY = position.posY + this.speed;
        this.repaint();
    }

    private void repaint() {
        this.paddle.setLayoutX(position.posX);
        this.paddle.setLayoutY(position.posY);
    }

    public int checkPaddleBounds() {

        //comprova els límits dels rectangles
        final boolean atTopBound = -canvas.getHeight() >= ((paddle.getLayoutY()) * 2 - this.height);
        final boolean atBottomBound = 0 <= ((paddle.getLayoutY()) - 2 * this.height);


        //retornam segons el limit trobat
        if (atTopBound) {
            System.out.println(getBotPosition());

            System.out.println("top bound");

            return +1;
        }

        if (atBottomBound) {
            System.out.println("bot bound");
            return -1;
        } else {
            return 0;
        }
    }



    public int getTopPosition() {

        return this.position.posY-this.height/2;
    }
    public int getBotPosition(){

        return this.position.posY+this.height/2;
    }

}

package Pong;


/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description: The Paddle class stores a paddle with it's attributes such
 as it's position and it is formed by a rectangle.
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

    /**
     * Attributes
     */
    Position position;
    private final int speed = 20;
    private final Pane canvas;
    final Node paddle;
    private final int height;


    /**
     * Constructor, sets the attributes
     * creates the rectangle for each paddle and it's style -color-
     */
    public Paddle(Pane canvas, int height, int width, int posX, int posY, String color) {
        position = new Position(posX, posY);
        this.canvas = canvas;
        this.paddle = new Rectangle(position.posX, position.posY, width, height);
        this.height = height;
        position.posX = (int)paddle.getLayoutX();
        position.posY = (int)paddle.getLayoutY();
        this.paddle.setLayoutX(position.posX);
        this.paddle.setLayoutY(position.posY);
        this.canvas.getChildren().add(this.paddle);
        this.paddle.setStyle("-fx-fill:" + color + ";");
    }

    /**
     * Move rectangle up
     */
    public void moveUp() {
        if(checkPaddleBounds()!=1) {
            position.posY = position.posY - this.speed;
        }
        this.repaint();
    }

    /**
     * Move rectangle down
     */
    public void moveDown() {
        if(checkPaddleBounds()!=-1) {
            position.posY = position.posY + this.speed;
        }
        this.repaint();
    }

    /**
     * Repaint
     */
    private void repaint() {
        this.paddle.setLayoutX(position.posX);
        this.paddle.setLayoutY(position.posY);
    }

    /**
     * Check rectangle bounds and return depending on the bound found
     */
    public int checkPaddleBounds() {

        //bounds calculation
        final boolean atTopBound = -canvas.getHeight()/2 >= ((paddle.getLayoutY()) - (height/2));
        final boolean atBottomBound = canvas.getHeight()/2 <= (paddle.getLayoutY() +  (height/2));

        //retornam segons el limit trobat
        if (atTopBound) {
            return 1;
        }

        if (atBottomBound) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

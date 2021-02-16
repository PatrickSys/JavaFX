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


public class RectangleTeclat extends Rectangle {


    Position position;
    int height;
    int speed = 15;
    Pane canvas;
    Node rectangle;
    public RectangleTeclat(Pane canvas, int posX, int posY, int height, int width, String color) {
        position = new Position(posX, posY);
        this.height = height;
        this.canvas = canvas;
        this.rectangle = new Rectangle(position.posX, position.posY, width, height);
        position.posX = 0;
        position.posY = 0;
        this.rectangle.setLayoutX(position.posX);
        this.rectangle.setLayoutY(position.posY);
        this.canvas.getChildren().add(this.rectangle);
        this.rectangle.setStyle("-fx-fill:" + color + ";");

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
        this.rectangle.setLayoutX(position.posX);
        this.rectangle.setLayoutY(position.posY);
    }

    public int checkRectangleLimits() {

        //comprova els límits dels rectangles
        final boolean atTopBound = -canvas.getHeight() >= ((rectangle.getLayoutY()) * 2 - height);
        final boolean atBottomBound = 0 <= ((rectangle.getLayoutY()) - 2 * height);


        //retornam segons el limit trobat
        if (atTopBound) {
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

    class Position {

        int posX;
        int posY;

        public Position(int x, int y) {
            this.posX = x;
            this.posY = y;
        }
    }


/*

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(panell, amp, alt));
        primaryStage.setTitle("Rectangle moguent..");
        primaryStage.show();
        panell.requestFocus();
        RectangleTeclat.Rectangle rectangle1=new RectangleTeclat.Rectangle(panell, 380, 140, 100, 20, "red");
        RectangleTeclat.Rectangle rectangle2=new RectangleTeclat.Rectangle(panell, 0, 140, 100, 20, "chartreuse");

        panell.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: rectangle1.mouAmunt(); break;
                case DOWN: rectangle1.mouAbaix(); break;


                case W:rectangle2.mouAmunt(); break;
                case S: rectangle2.mouAbaix(); break;
            }
        });
        
        //final boolean alLimitDret = cercle.getLayoutX() >= (limits.getMaxX() - cercle.getRadius());
        //final boolean alLimitEsquerra = cercle.getLayoutX() <= (limits.getMinX() + cercle.getRadius());
        //final boolean alLimitInferior = cercle.getLayoutY() >= (limits.getMaxY() - cercle.getRadius());
        //final boolean alLimitSuperior = cercle.getLayoutY() <= (limits.getMinY() + cercle.getRadius());
    }*/


}

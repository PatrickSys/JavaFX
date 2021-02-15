package Pong;


/************************************************************************
 Made by        PatrickSys
 Date           05/02/2021
 Package        Pong
 Description:
 ************************************************************************/

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.awt.*;


public class RectangleTeclat extends Rectangle {




        class Posicio {

            int posX;
            int posY;

            public Posicio(int x, int y) {
                this.posX = x;
                this.posY = y;
            }
        }



        Posicio posicio;
        int altura;
        int gruix;
        int velocitat = 10;
        Pane canvas;
        Node rectangle;



        public RectangleTeclat(Pane canvas, int posX, int posY, int altura, int gruix, String color){
            posicio=new Posicio(posX, posY);
            this.altura=altura;
            this.canvas = canvas;
            this.rectangle= new Rectangle( posicio.posX, posicio.posY, gruix, altura);
            posicio.posX=posX;
            posicio.posY=posY;
            this.rectangle.setLayoutX(posicio.posX);
            this.rectangle.setLayoutY(posicio.posY);
            this.canvas.getChildren().add(this.rectangle);
            //this.rectangle.setFill(Color.BLACK);
            this.rectangle.setStyle("-fx-fill:" + color + ";");

        }

        /**
         * Mou rectangle cap amunt
         */
        public void mouAmunt() {
            posicio.posY=posicio.posY-this.velocitat;
            this.repinta();
        }


        /**
         * Mou rectangle cap abaix
         */
        public void mouAbaix() {
            posicio.posY=posicio.posY+this.velocitat;
            this.repinta();
        }

        private void repinta() {
            this.rectangle.setLayoutX(posicio.posX);
            this.rectangle.setLayoutY(posicio.posY);
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

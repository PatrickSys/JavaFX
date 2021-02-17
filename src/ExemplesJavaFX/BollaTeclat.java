package ExemplesJavaFX;
//************************************************************************
//  Made by        PatrickSys
//  Date           02/02/2021
//  Package        ExemplesJavaFX
//
// Description 
//************************************************************************

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BollaTeclat extends Application {

    class Bolla {
        class Posicio {
            int posX;
            int posY;
            public Posicio(int x,int y) {
                this.posX=x;
                this.posY=y;
            }
        }
        Posicio posicio;
        int radi;
        int velocitat=10;
        Pane panell;
        Node bolla;

        public Bolla(Pane panell,int posX,int posY,int radi){
            posicio=new Posicio(posX,posY);
            this.radi=radi;
            this.panell=panell;
            this.bolla=new Circle(posicio.posX - radi, posicio.posY - radi, radi,Color.BLUE);
            posicio.posX=0;
            posicio.posY=0;
            this.bolla.setLayoutX(posicio.posX);
            this.bolla.setLayoutY(posicio.posY);
            this.panell.getChildren().add(this.bolla);
        }


        /**
         * Mou bolla cap amunt
         */
        public void mouAmunt() {
            posicio.posY=posicio.posY-this.velocitat;
            this.repinta();
            System.out.println("Amunt pitjat");
        }

        /**
         * Mou bolla cap abaix
         */
        public void mouAbaix() {
            posicio.posY=posicio.posY+this.velocitat;
            this.repinta();
            System.out.println("Abaix pitjat");
        }

        /**
         * Mou bolla cap a l'esquerra
         */
        public void mouEsq() {
            posicio.posX=posicio.posX-this.velocitat;
            this.repinta();
        }

        /**
         * Mou bolla cap a la dreta
         */
        public void mouDre() {
            posicio.posX=posicio.posX+this.velocitat;
            this.repinta();
        }
        private void repinta() {
            this.bolla.setLayoutX(posicio.posX);
            this.bolla.setLayoutY(posicio.posY);
        }

        /**
         *
         */
        public void mouDiagonal() {
            bolla1.mouDre();
            bolla1.mouAbaix();
        }
    }

    Pane panell = new Pane();
    double amp = 400;
    double alt = 400;
    Bolla bolla1;


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(panell, amp, alt));
        primaryStage.setTitle("Bolla rebotant..");
        primaryStage.show();
        panell.requestFocus();
        Bolla bolla1=new Bolla(panell,200,200,10);
        panell.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: bolla1.mouAmunt(); break;
                case DOWN: bolla1.mouAbaix(); break;
                case LEFT: bolla1.mouEsq(); break;
                case RIGHT: bolla1.mouDre(); break;
                //case W:bolla1.mouDiagonal(); break;
            }
        });

    }

}
package Pong;
/************************************************************************
 Made by        PatrickSys
 Date           15/02/2021
 Package        Player
 Description:
 ************************************************************************/


import javafx.scene.layout.Pane;

public class Player {

    Paddle paddle;


    public Player(Pane canvas, int paddlPosX, int paddleHeight, int paddleWidth){
        this.paddle = new Paddle(canvas, paddleHeight, paddleWidth, paddlPosX,(int) (canvas.getHeight() -120)/2, "WHITE" );
    }
}

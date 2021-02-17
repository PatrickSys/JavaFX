package Pong;
/************************************************************************
 Made by        PatrickSys
 Date           15/02/2021
 Package        Player
 Description:
 ************************************************************************/


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Player {

    Paddle paddle;
    private int points=0;
    Label playerPoints;
    static Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 40);
    Pane canvas;
    public int player1Position;
    public int player2Position;


    public Player(Pane canvas, int paddlPosX, int paddleHeight, int paddleWidth){
        this.paddle = new Paddle(canvas, paddleHeight, paddleWidth, paddlPosX,(int) (canvas.getHeight() -paddleHeight)/2, "WHITE" );
        this.canvas=canvas;
        this.player1Position = (int)(canvas.getWidth() / 2  - font.getSize()*2);
        this.player2Position = (int)(canvas.getWidth() / 2 + font.getSize()+10);
    }

    public static void settlePoints(int position, Label playerPoints) {

        playerPoints = new Label("0");

        playerPoints.setTextFill(Color.WHITE);
        playerPoints.relocate(position, 0);
        playerPoints.setFont(getFont());

    }

    public int getPlayer1Position() {
        return player1Position;
    }

    public int getPlayer2Position() {
        return player2Position;
    }

    public void goal(){
        this.points++;
    }

    public static Font getFont() {
        return font;
    }

    public static int  getFontSize() {
        return (int)font.getSize();
    }
}

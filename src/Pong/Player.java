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

    /**
     * Attributes
     */
    Paddle paddle;
    private Integer points = 0;
    Label playerPoints;
    Font font;
    private final Pane canvas;
    private final int player1Position;
    private final int player2Position;


    /*
      Creates and instance of pong to use it's setFont method and re-use code for this class
      sets attributes and so on
     */
    public Player(Pane canvas, int paddlPosX, int paddleHeight, int paddleWidth){
        MyPong pong = new MyPong();
        pong.setFont();
        this.font = pong.getFont();
        this.paddle = new Paddle(canvas, paddleHeight, paddleWidth, paddlPosX,(int) (canvas.getHeight() -paddleHeight)/2, "WHITE" );
        this.canvas=canvas;
        this.player1Position = (int)(canvas.getWidth() / 2  - pong.getFont().getSize()*2);
        this.player2Position = (int)(canvas.getWidth() / 2 + pong.getFont().getSize()+10);
        this.playerPoints = new Label(points.toString());
    }

    //sets points labels
    public void settlePoints(int position) {

        playerPoints.setTextFill(Color.WHITE);
        playerPoints.relocate(position, 0);
        playerPoints.setFont(font);
    }

    //updates text labels with it's points
    public void updatePoints(){
        playerPoints.setText(points.toString());
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

    public Integer getPoints() {
        return points;
    }
}

package Labels;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/************************************************************************
 Made by        PatrickSys
 Date           20/02/2021
 Package        Pong
 Description:
 ************************************************************************/


public class testLabels  {



        private static Font ttfBase = null;
        private static Font telegraficoFont = null;
        private static InputStream myStream = null;
        private static final String FONT_PATH_TELEGRAFICO = "imageFolder/TELEGRAFICO.TTF";

        public static Font createFont() {


            try {
                myStream = new BufferedInputStream(
                        new FileInputStream("Labels/stocky.ttf"));
                ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
                telegraficoFont = ttfBase.deriveFont(Font.PLAIN, 24);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println("Font not loaded.");
            }
            return telegraficoFont;
        }


    public static Font font = null;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    if (font == null) {
                        font = createFont();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: BLACK");
                Scene scene = new Scene(pane, 500, 500);
                Label label = new Label("estoejemplo");
                label.setBounds(50, 50, 50, 50);
                label.setLocation(50,50);
                label.setFont(font);

            } // public void run() Closing
        });
    }

}




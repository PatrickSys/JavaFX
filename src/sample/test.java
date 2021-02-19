package sample;



import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class test {

    Label label = new Label();
    public static void main(String[] args){

    }
        public void setLabelStyle(Label label) {
            label.setStyle("-fx-font-family: GoodDog; -fx-font-size: 25");
            Scene scene = new Scene(label);
            scene.getStylesheets().add("C:\\Users\\bitaz\\IdeaProjects\\JavaFX\\src\\PongFonts\\fontstyle\\fontstyle");
            label.setTextFill(Color.GRAY);


    }






    }






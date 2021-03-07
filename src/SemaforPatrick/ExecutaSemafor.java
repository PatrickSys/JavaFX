package SemaforPatrick;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static java.lang.System.exit;

public class ExecutaSemafor extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {

            // Haureu de provar els tres constructors amb les seves respectives línies que estan a continuació
            //SemaforPatrick semafor = new SemaforPatrick(EstatsSemafor.VERD);

            //Semafor semafor = new Semafor(EstatsSemafor.VERMELL);
            SemaforPatrick semafor = new SemaforPatrick(EstatsSemafor.VERD, 80, 50, 70);

            Pane panell = new Pane();
            double ampPanell = 400;
            double altPanell = 400;
            stage.setScene(new Scene(panell, ampPanell, altPanell,Color.BLUE));
            stage.setTitle("Examen 2a avaluacio");
            stage.show();
            panell.requestFocus();
            semafor.inicialitzaSemafor(panell);
            panell.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case UP:
                        try {
                            semafor.canviaLlumAmunt();
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                    case DOWN:
                        try {
                            semafor.canviaLlumAbaix();
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                        break;
                }
            });
        } catch (Exception e) {
            if (e.getMessage().equals("Mides incorrectes")) {
                System.out.println("No es pot inicialitzar. Mides del semàfor massa grosses");
                        exit(0);
            }
            else
                System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

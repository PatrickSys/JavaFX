package SemaforPatrick;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class SemaforPare {


    EstatsSemafor estatSemafor;
    int diametreVermell,diametreGroc,diametreVerd;
    Circle llumvermell,llumgroc,llumverd;
    int dimensioMax=80;
    int dimensioMin=20;

    public void defineixMides(int diametreVermell, int diametreGroc, int diametreVerd)
            throws Exception {}

    public void inicialitzaSemafor(Pane panell){



    }

    public void canviaLlums(){
        switch (this.estatSemafor) {
            case VERMELL -> {
                this.llumvermell.setFill(Color.RED);
                this.llumgroc.setFill(Color.GREY);
                this.llumverd.setFill(Color.GREY);
                break;}

            case GROC -> {
                this.llumvermell.setFill(Color.GREY);
                this.llumgroc.setFill(Color.YELLOW);
                this.llumverd.setFill(Color.GREY);
                break;}

            case VERD -> {
                this.llumvermell.setFill(Color.GREY);
                this.llumgroc.setFill(Color.GREY);
                this.llumverd.setFill(Color.GREEN);
                break;}
        }
    }
    protected void canviaLlumAmunt() throws Exception {}

    protected void canviaLlumAbaix() throws Exception {}
}


package SemaforPatrick;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class SemaforPatrick extends SemaforPare {




    /* Els següents constructors hauran de cridar al mètode defineixMides per establir les
    mides dels cercles del semafor i comprovar que no superen les dimensions especificades.
    Si les supera haurà d'enviar una excepció "Mides incorrectes".
     */

    //Un constructor per defecte sense paràmetres que inicialitza el semàfor a vermell amb dimensions 50,40,40
    public SemaforPatrick() throws Exception {


        defineixMides(50, 40, 40);
        this.estatSemafor = EstatsSemafor.VERMELL;



    }

    //Un segon constructor que inicialitza el semàfor al valor desitjat i mides 50,40,40
    public SemaforPatrick(EstatsSemafor estatdesitjat) throws Exception {

        defineixMides(50, 40, 40);
        this.estatSemafor = estatdesitjat;

    }

    // Un tercer constructor que inicialitza el semàfor al valor desitjat i mides especificades
    public SemaforPatrick(EstatsSemafor estatdesitjat, int diametreVermell, int diametreGroc, int diametreVerd) throws Exception {

        defineixMides(diametreVermell, diametreGroc, diametreVerd);
        this.estatSemafor = estatdesitjat;
    }

    /* Aquest mètode establirà les mides dels llums del semàfor */
    @Override
    public void defineixMides(int diametreVermell, int diametreGroc, int diametreVerd) throws Exception {



        this.diametreVermell = diametreVermell;
        this.diametreGroc = diametreGroc;
        this.diametreVerd= diametreVerd;

        if(diametreExcessiu() || diametreMinim()){
            throw new Exception("Mides incorrectes");
        }


        // Afegir el codi necessari

    }

    /* Aquest mètode colocarà el llum vermell a la posició x:200 y:140; el llum groc just
    abaix (x:200) separat 3 pixels del vermell i el llum verd abaix del groc (x:200) separat
    3 pixels. Haureu de calcular i parametritzar la posició dels llums groc i verd de manera
    que quedin al mateix eix (x:200) pero no es superposin, mantenint la distància indicada.
    Veure imatge adjunta a l'enunciat.
     Recordeu que per crear un cercle haureu de fer
     new Circle(posicióX, posicióY,radi)
     Es recomana utilitzar canviaLlums() dins aquest mètode
     */
    @Override
    public void inicialitzaSemafor(Pane panell) {



        this.llumvermell = new Circle(200, 140, diametreVermell/2);
        this.llumgroc = new Circle(200, 140+diametreVermell+3, diametreGroc/2);
        this.llumverd = new Circle(200, 140+diametreVermell+diametreGroc+6, diametreVerd/2);
        canviaLlums();

        pintaRectangleSemafor(panell);
        panell.getChildren().add(this.llumvermell);
        panell.getChildren().add(this.llumgroc);
        panell.getChildren().add(this.llumverd);

    }


    /* Els següents mètodes faran possible que el semàfor canvi el llum actiu vermell,
    groc o verd. Només un d'ells ha d'estar encès.
    Si quan es cridi a canviaLlumAmunt el semàfor ja està en vermell (a dalt de tot)
    llançarà l'excepcio "Semafor no pot canviar d'estat"
    Si quan es cridi a canviaLlumAbaix el semàfor ja està en verd (a baix de tot) llançarà
    l'excepcio "Semafor no pot canviar d'estat"
     Es recomana utilitzar canviaLlums() dins aquests mètodes
     */

    @Override
    public void canviaLlumAmunt() throws Exception {

        ArrayList <EstatsSemafor> estats = new ArrayList<>();
        estats.add(EstatsSemafor.VERMELL);
        estats.add(EstatsSemafor.GROC);
        estats.add(EstatsSemafor.VERD);

        if(this.estatSemafor==EstatsSemafor.VERMELL)throw new Exception("Semafor no pot canviar d'estat");

        int nouIndex =  estats.indexOf(this.estatSemafor) -1;
        this.estatSemafor = estats.get(nouIndex);
        canviaLlums();


    }


    @Override
    public void canviaLlumAbaix() throws Exception {


            ArrayList <EstatsSemafor> estats = new ArrayList<>();
            estats.add(EstatsSemafor.VERMELL);
            estats.add(EstatsSemafor.GROC);
            estats.add(EstatsSemafor.VERD);

            if(this.estatSemafor==EstatsSemafor.VERD)throw new Exception("Semafor no pot canviar d'estat");
            int nouIndex =  estats.indexOf(this.estatSemafor) +1;
            this.estatSemafor = estats.get(nouIndex);
            canviaLlums();

        }


    // No modificar aquest mètode
    public void pintaRectangleSemafor(Pane panell) {
        Rectangle rectangle = new
        Rectangle(this.llumvermell.getCenterX() - this.llumvermell.getRadius() - 10, this.llumvermell.
        getCenterY() - this.llumvermell.getRadius() - 10, this.diametreVermell + 10 * 2, this.diametreVermell + this.diametreGroc + this.diametreVerd + 10 * 2 + 3 * 2);
        rectangle.setFill(Color.BLACK);
        rectangle.setArcWidth(30.0);
        rectangle.setArcHeight(30.0);
        panell.getChildren().add(rectangle);
    }

    private boolean diametreExcessiu(){
        if(this.diametreVermell>dimensioMax || this.diametreGroc>dimensioMax || this.diametreVerd>dimensioMax){
            return true;
        }
        return false;
    }

    private boolean diametreMinim(){

        if(this.diametreVermell<dimensioMin || this.diametreGroc<dimensioMin || this.diametreVerd<dimensioMin){
            return true;
        }
        return false;
    }
}































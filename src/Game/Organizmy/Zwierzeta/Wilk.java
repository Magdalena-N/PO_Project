package Game.Organizmy.Zwierzeta;

import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Wilk extends Zwierze {


   public Wilk(Swiat xswiat, POLOZENIE xwsp) {
       super(xswiat, 9, 5, xwsp, 0);
       this.setKolor(Color.GRAY);
   }


    public Wilk(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek){
       super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
       this.setKolor(Color.GRAY);
    }


    @Override
    public String rysowanie() {
        return "W";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Wilk(swiat, wsp), wsp);
    }
}
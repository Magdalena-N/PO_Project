package Game.Organizmy.Rosliny;

import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Guarana extends Roslina {

    public Guarana(Swiat xswiat, POLOZENIE xwsp)
    {
        super(xswiat, xwsp);
        this.setKolor(Color.LIGHT_GRAY);
    }
    public Guarana(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek){
	    super(xswiat, xsila, xinicjatywa, xwsp, xwiek) ;
        this.setKolor(Color.LIGHT_GRAY);
    }

    @Override
    public String rysowanie() {
        return "GU";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Guarana(swiat, wsp), wsp);
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);
        atakujacy.setSila(atakujacy.getSila() + 3);
    }
}

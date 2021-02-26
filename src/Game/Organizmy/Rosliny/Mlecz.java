package Game.Organizmy.Rosliny;


import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Mlecz extends Roslina {


    public Mlecz(Swiat xswiat, POLOZENIE xwsp) {
        super(xswiat, xwsp);
        this.setKolor(Color.YELLOW);
    }
    public Mlecz(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(Color.YELLOW);
    }

    @Override
    public String rysowanie() {
        return "ML";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Mlecz(swiat, wsp), wsp);
    }

    @Override
    public void akcja() {
        for (int i = 0; i < 3; i++) {
            super.akcja();
        }
    }
}
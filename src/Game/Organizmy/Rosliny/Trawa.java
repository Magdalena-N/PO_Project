package Game.Organizmy.Rosliny;

import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Trawa extends Roslina {
    public Trawa(Swiat xswiat, POLOZENIE xwsp)
    {
        super(xswiat,xwsp);
        this.setKolor(Color.GREEN);
    }
    Trawa(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
    {
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(Color.GREEN);
    }

    @Override
    public String rysowanie() {
        return "TR";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Trawa(swiat, wsp), wsp);
        this.setKolor(Color.GREEN);
    }
}

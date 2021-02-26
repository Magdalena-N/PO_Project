package Game.Organizmy.Zwierzeta;

import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(Swiat xswiat, POLOZENIE xwsp)
	{
        super(xswiat, 4, 4, xwsp, 0);
        this.setKolor(new Color(190, 205, 229));
    }
    public Owca(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
        super(xswiat, xsila, xinicjatywa, xwsp,xwiek);
        this.setKolor(new Color(190, 205, 229));
    }

    @Override
    public String rysowanie() {
        return "OW";
    }

    @Override
    public  void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Owca(swiat, wsp), wsp);
    }

}

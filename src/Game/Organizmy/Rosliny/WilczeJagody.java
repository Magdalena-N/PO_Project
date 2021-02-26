package Game.Organizmy.Rosliny;

import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class WilczeJagody extends Roslina{


    public WilczeJagody(Swiat xswiat, POLOZENIE xwsp)
	{
	    super(xswiat,99,0,xwsp,0);
	    this.setKolor(Color.blue);
    }
    public WilczeJagody(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(Color.blue);
    }

    @Override
    public String rysowanie() {
        return "JAG";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new WilczeJagody(swiat, wsp), wsp);
    }


    @Override
    public void kolizja(Organizm atakujacy) {
        atakujacy.setIstnieje(false);
        //swiat.dodajdoUsuniecia(&atakujacy);
        swiat.dodajOrganizm(null, atakujacy.getWsp());
        String temp = "";
        temp = atakujacy.rysowanie() + " zjadl " + this.rysowanie() + " i umarl";
        swiat.setKomunikat(temp);
    }
}

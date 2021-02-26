package Game.Organizmy.Zwierzeta;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Lis extends Zwierze {

    public Lis(Swiat xswiat, POLOZENIE xwsp)
	{
	    super(xswiat,3 ,7, xwsp, 0);
	    this.setKolor(Color.ORANGE);
    }

    public Lis(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(Color.ORANGE);
    }

    @Override
    public String rysowanie() {
        return "LI";
    }
    @Override
    public void akcja() {
        KIERUNEK gdzie;
        int szukaj = 1;
        POLOZENIE wsp2;
        Organizm org;
        int licznik = 0;
        do {
            gdzie = losujKierunek(this,1);
            wsp2 = new POLOZENIE(this.getWsp().getX(),this.getWsp().getY());
            int x = wsp2.getX();
            int y = wsp2.getY();
            switch (gdzie) {
                case GORA:
                    y--;
                    break;
                case DOL:
                    y++;
                    break;
                case LEWO:
                    x--;
                    break;
                case PRAWO:
                    x++;
                    break;
                default:
                    break;
            }
            if (czyWolne(gdzie,this.getZasieg())) {
                ruch(this, gdzie);
                szukaj = 0;
            }
		    else {
		        wsp2.setX(x);
		        wsp2.setY(y);
                org = swiat.getOrganizm(wsp2);
                if (org.getSila() <= this.getSila()) {
                    szukaj = 0;
                    (swiat.getOrganizm(wsp2)).kolizja(this);
                    if (swiat.getOrganizm(this.getWsp()) != null && this.getOdbity() == false) {
                        ruch(this, gdzie);
                    }
                    else if (swiat.getOrganizm(this.getWsp()) != null && this.getOdbity() == true) {
                        this.setOdbity(false);
                    }

                }
                else {
                    licznik++;
                }

            }
        } while (szukaj == 1 && licznik < 4);
    }

    @Override
    public void dodaj(POLOZENIE wsp) {

        swiat.dodajOrganizm(new Lis(swiat, wsp), wsp);

    }
}

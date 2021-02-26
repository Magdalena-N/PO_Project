package Game.Organizmy.Zwierzeta;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;
import java.util.Random;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat xswiat, POLOZENIE xwsp)
	{
	    super(xswiat, 4, 4, xwsp, 0);
        this.setZasieg(2);
        this.setKolor(Color.magenta);
    }
    public Antylopa(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
	    super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setZasieg(2);
        this.setKolor(Color.magenta);
    }

    @Override
    public String rysowanie() {
        return "AN";
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        Random r = new Random();
        int tem = r.nextInt(100);
        KIERUNEK gdzie;

        if (this.rysowanie() == atakujacy.rysowanie()) {
            gdzie = losujKierunek(this,1);
            rozmnoz(gdzie);
            String temp = "";
            temp += atakujacy.rysowanie();
            temp += " urodzily ";
            temp += this.rysowanie();
            swiat.setKomunikat(temp);
        }

	    else if (tem < 50) {
            do {
                gdzie = losujKierunek(this,this.getZasieg());
            } while (!czyWolne(gdzie, 1));
            ucieknij(this, gdzie);
            String temp = "";
            temp += this.rysowanie();
            temp += " uciekla przed ";
            temp += atakujacy.rysowanie();
            swiat.setKomunikat(temp);
        }
        else {
            super.kolizja(atakujacy);
        }

    }
    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Antylopa(swiat, wsp), wsp);
    }
    public void ucieknij(Organizm org, KIERUNEK gdzie) {
        POLOZENIE wsp1 = org.getWsp();
        POLOZENIE wsp2 = new POLOZENIE(org.getWsp().getX(),org.getWsp().getY());
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
        wsp2.setX(x);
        wsp2.setY(y);
        org.setWsp(wsp2);
        swiat.dodajOrganizm(org, wsp2);
        swiat.dodajOrganizm(null, wsp1);
    }
}

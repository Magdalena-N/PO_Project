package Game.Organizmy.Zwierzeta;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;
import java.util.Random;

public class Zolw extends Zwierze {

    public Zolw(Swiat xswiat, POLOZENIE xwsp)
    {
        super(xswiat,2,1, xwsp, 0);
        this.setKolor(new Color(44, 140, 0));
    }
    public Zolw(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
    {
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(new Color(44, 140, 0));
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (this.rysowanie() == atakujacy.rysowanie()) {
            return false;
        }
        if (atakujacy.getSila() < 5) {
            return true;
        }
        else
            return false;
    }

    @Override
   public String rysowanie() {
       return "ZO";
   }

   @Override
    public void akcja() {
        Random random = new Random();
        int temp = random.nextInt(100);
        if (temp < 25) {
            KIERUNEK gdzie;
            POLOZENIE wsp2 = this.getWsp();
            gdzie = losujKierunek(this,this.getZasieg());
            if (czyWolne(gdzie,this.getZasieg())) {
                ruch(this, gdzie);
            }
		    else {

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

                (swiat.getOrganizm(wsp2)).kolizja(this);
                if (swiat.getOrganizm(this.getWsp()) != null && this.getOdbity() == false) {
                    ruch(this, gdzie);

                } else if (swiat.getOrganizm(this.getWsp()) != null) {
                    this.setOdbity(false);
                }


            }
        }
    }
    @Override
    public void kolizja(Organizm atakujacy) {

        if (czyOdbilAtak(atakujacy)) {
            String temp = "";
            temp += this.rysowanie();
            temp += " odbil atak ";
            temp += atakujacy.rysowanie();
            swiat.setKomunikat(temp);
            atakujacy.setOdbity(true);
        }
        else {
           super.kolizja(atakujacy);
        }


    }
    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Zolw(swiat, wsp), wsp);
    }
}

package Game.Organizmy.Rosliny;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Organizmy.Zwierzeta.Zwierze;
import Game.Swiat;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {


    public BarszczSosnowskiego(Swiat xswiat, POLOZENIE xwsp) {

        super(xswiat,10,0,xwsp,0);
        this.setKolor(Color.CYAN);
    }
    public BarszczSosnowskiego(Swiat xswiat,int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
	{
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek);
        this.setKolor(Color.CYAN);
    }
    @Override
    public String rysowanie() {
        return "BA";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new BarszczSosnowskiego(swiat, wsp), wsp);
    }

    @Override
    public void kolizja(Organizm atakujacy) {

        swiat.dodajOrganizm(null, atakujacy.getWsp());
        String temp = "";
        temp = atakujacy.rysowanie() + " zjadl " + this.rysowanie() + " i umarl. ";

        swiat.setKomunikat(temp);
    }

    @Override
    public void akcja() {
        POLOZENIE pozycja = this.getWsp();
        POLOZENIE pozycja2 = new POLOZENIE(pozycja.getX(),pozycja.getY());

        if (pozycja.getY() != 0 && czyWolne(KIERUNEK.GORA) == false){
            pozycja2.setY(pozycja2.getY()-1);
            if((swiat.getOrganizm(pozycja2)).rysowanie() != this.rysowanie()) {
                swiat.dodajOrganizm(null, pozycja2);
            }
            pozycja2.setY(pozycja2.getY()+1);
        }

        if (pozycja.getY() != swiat.getY() - 1 && czyWolne(KIERUNEK.DOL) == false) {
            pozycja2.setY(pozycja2.getY()+1);
            if(swiat.getOrganizm(pozycja2).rysowanie() != this.rysowanie()) {
                swiat.dodajOrganizm(null, pozycja2);
            }
            pozycja2.setY(pozycja2.getY()-1);
        }

        if (pozycja.getX() != 0 && czyWolne(KIERUNEK.LEWO) == false ){
            pozycja2.setX(pozycja2.getX()-1);
            if(swiat.getOrganizm(pozycja2).rysowanie() != this.rysowanie()) {
                swiat.dodajOrganizm(null, pozycja2);
            }
            pozycja2.setX(pozycja2.getX()+1);
        }
        if (pozycja.getX() != swiat.getX() - 1 && czyWolne(KIERUNEK.PRAWO) == false){
            pozycja2.setX(pozycja2.getX()+1);
            if(swiat.getOrganizm(pozycja2).rysowanie() != this.rysowanie())
                swiat.dodajOrganizm(null, pozycja2);
            pozycja2.setX(pozycja2.getX()-1);
        }


        super.akcja();

    }
}

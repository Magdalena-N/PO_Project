package Game.Organizmy.Zwierzeta;

import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.awt.*;

public class Czlowiek extends Zwierze{
    private int czekaj;
    private int pozostalyEliksir;


    public Czlowiek(Swiat xswiat, POLOZENIE xwsp){

	    super(xswiat, 5,4, xwsp,0);
        swiat.setCzlowiekZyje(true);
        this.czekaj = 0;
        this.pozostalyEliksir = 0;
        this.setKolor(Color.pink);
    }
    public Czlowiek(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
    {
        super(xswiat,xsila,xinicjatywa,xwsp,xwiek);
        swiat.setCzlowiekZyje(true);
        this.czekaj = 0;
        this.pozostalyEliksir = 0;
        this.setKolor(Color.pink);
    }

    @Override
    public void akcja() {
        if (this.getUmiejetnosc() == true) {
            this.uzyjUmiejetnosci();
            this.setUmiejetnosc(false);
        }
        super.akcja();
        if (swiat.getOrganizm(this.getWsp()) == null) {
            swiat.setCzlowiekZyje(false);
        }
        if (czekaj != 0) {
            czekaj--;
        }
        if (pozostalyEliksir > 0) {
            pozostalyEliksir--;
            this.setSila((this.getSila()) - 1);
            if (pozostalyEliksir == 0) {

                czekaj = 5;
            }
        }


    }
    @Override
    public void kolizja(Organizm atakujacy) {
        super.kolizja(atakujacy);

        if (swiat.getOrganizm(this.getWsp()) == null) {
            swiat.setCzlowiekZyje(false);
        }


    }

    @Override
    public String rysowanie() {
        return "?";
    }

    @Override
    public void dodaj(POLOZENIE wsp) {
        swiat.dodajOrganizm(new Czlowiek(swiat, wsp), wsp);
    }

    public void uzyjEliksiru() {
        swiat.setKomunikat("Czlowiek wypil eliksir");

        this.setSila(this.getSila() + 10);
        pozostalyEliksir = 5;

    }
    public void uzyjUmiejetnosci() {
        if (czekaj == 0) {
            uzyjEliksiru();
        }
    }
    public int getCzekaj() {
        return czekaj;
    }
    public void setCzekaj(int czekaj){
        this.czekaj= czekaj;
    }
    public int getPozostalyEliksir(){
        return pozostalyEliksir;
    }
    public void setPozostalyEliksir(int pozostalyEliksir){
        this.pozostalyEliksir=pozostalyEliksir;
    }

}
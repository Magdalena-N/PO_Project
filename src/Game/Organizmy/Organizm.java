package Game.Organizmy;


import Game.Swiat;

import java.awt.*;

public abstract class Organizm {
    private KIERUNEK nastepny_kierunek;
    private int sila;
    private int inicjatywa;
    private int wiek;
    private POLOZENIE wsp;
    private boolean istnieje;
    private boolean odbity;
    int zasieg;
    private boolean rozmnozone;
    private boolean umiejetnosc;

    protected Swiat swiat;

    private Color kolor;



    public Organizm(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek) {
        this.swiat=xswiat;
        this.nastepny_kierunek = KIERUNEK.BRAK;
        this.sila = xsila;
        this.inicjatywa = xinicjatywa;
        this.wsp = xwsp;
        this.wiek = xwiek;
        this.istnieje = true;
        this.odbity = false;
        this.zasieg = 1;
        this.rozmnozone = false;
        this.umiejetnosc = false;
        this.kolor = Color.WHITE;
    }

/////////////////////////////////////////////


    public int getSila() {
        return sila;
    }

    public void setSila(int xsila) {
        this.sila = xsila;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int xinicjatywa) {
        this.inicjatywa = xinicjatywa;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int xwiek) {
        this.wiek = xwiek;
    }

    public POLOZENIE getWsp()  {
        return wsp;
    }

    public void setWsp(POLOZENIE xwsp) {
        this.wsp = xwsp;
    }

    public KIERUNEK getNastepnyKierunek(){
        return nastepny_kierunek;
    }

    public void setNastepnyKierunek(KIERUNEK n_k) {
        this.nastepny_kierunek = n_k;
    }

    public boolean getIstnieje(){
        return istnieje;
    }

    public void setIstnieje(boolean xistnieje) {
        this.istnieje = xistnieje;
    }

    public boolean getOdbity()  {
        return odbity;
    }

    public void setOdbity(boolean xodbity) {
        this.odbity = xodbity;
    }

    public int getZasieg() {
        return zasieg;
    }
    public void setZasieg(int xzasieg) {
        this.zasieg = xzasieg;
    }

    public boolean getRozmnozone() {
        return rozmnozone;
    }

    public void setRozmnozone(boolean czyRozmnozone) {
        this.rozmnozone = czyRozmnozone;
    }

    public void setUmiejetnosc(boolean aktywowac) {
        this.umiejetnosc = aktywowac;
    }

    public boolean getUmiejetnosc() {
        return umiejetnosc;
    }

    public void setKolor(Color xkolor){
        this.kolor=xkolor;
    }

    public Color getKolor(){return kolor;}
///////////////////////////////////////////////////////////////
    public abstract String rysowanie();
    public abstract void akcja();
    public abstract void kolizja(Organizm atakujacy);
    public abstract boolean czyOdbilAtak(Organizm atakujacy);
}

package Game.Organizmy.Rosliny;

import java.util.Random;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Swiat;

public abstract class Roslina extends Organizm {
    Roslina(Swiat xswiat, POLOZENIE xwsp)
    {
        super(xswiat,0,0,xwsp,0);
    }
    Roslina(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
    {
        super(xswiat, xsila, xinicjatywa, xwsp, xwiek) ;

    }
    abstract void  dodaj(POLOZENIE wsp);

    @Override
    public void akcja() {
        Random r = new Random();
        int temp = r.nextInt(10);
        if (temp < 2) {
            KIERUNEK gdzie;
            POLOZENIE wsp2 = this.getWsp();
            gdzie = losujKierunek(this);
            zasiej(this, gdzie);
        }
    }

    public boolean czyWolne(KIERUNEK gdzie) {
        POLOZENIE pozycja = this.getWsp();

        int x = pozycja.getX();
        int y = pozycja.getY();

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
        }
        POLOZENIE nowapozycja = new POLOZENIE(x,y);
        if (swiat.getOrganizm(nowapozycja) == null) {
            return true;
        }
        else
            return false;
    }
    public void zasiej(Organizm xorg, KIERUNEK gdzie) {
        POLOZENIE wsp2 = new POLOZENIE(xorg.getWsp().getX(),xorg.getWsp().getY());
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
        if(x>=0 && x<swiat.getX() && y>=0 && y < swiat.getY()) {
            if (czyWolne(gdzie)) {
                wsp2.setX(x);
                wsp2.setY(y);
                this.dodaj(wsp2);
            }
        }
    }
    public KIERUNEK losujKierunek(Organizm org) {
        Random r = new Random();
        int temp;
        KIERUNEK gdzie = KIERUNEK.BRAK;
        POLOZENIE obecna_pozycja;
        obecna_pozycja = org.getWsp();
        //obiekt calkiem z lewej
        if (obecna_pozycja.getX() == 0) {
            //lewy gorny rog(PRAWO,DOL)
            if (obecna_pozycja.getY() == 0) {
                temp = r.nextInt(2) ;
                gdzie = temp == 0 ? KIERUNEK.PRAWO: KIERUNEK.LEWO;

            }
            //lewy dolny rog (GORA,PRAWO)
            else if (obecna_pozycja.getY() == swiat.getY() - 1) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.GORA : KIERUNEK.PRAWO;
            }
            //tylko w lewo nie moze
            else {
                temp = r.nextInt(3);
                switch (temp)
                {
                    case 0:
                        gdzie = KIERUNEK.GORA;
                        break;
                    case 1:
                        gdzie = KIERUNEK.PRAWO;
                        break;
                    case 2:
                        gdzie = KIERUNEK.DOL;
                        break;
                    default:
                        break;
                }
            }
        }
        //obiekt calkiem z prawej
        else if (obecna_pozycja.getX() == swiat.getX() - 1) {
            //prawy gorny rog
            if (obecna_pozycja.getY() == 0) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.LEWO : KIERUNEK.DOL;
            }
            //prawy dolny rog
            else if (obecna_pozycja.getY() == swiat.getY() - 1) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.LEWO : KIERUNEK.GORA;
            }
            //tylko w prawo nie moze
            else {
                temp = r.nextInt(3);
                switch (temp)
                {
                    case 0:
                        gdzie = KIERUNEK.GORA;
                        break;
                    case 1:
                        gdzie = KIERUNEK.LEWO;
                        break;
                    case 2:
                        gdzie = KIERUNEK.DOL;
                        break;
                    default:
                        break;
                }
            }

        }
        //calkiem u gory bez rogow
        else if (obecna_pozycja.getY() == 0) {
            temp = r.nextInt(3);
            switch (temp)
            {
                case 0:
                    gdzie = KIERUNEK.PRAWO;
                    break;
                case 1:
                    gdzie = KIERUNEK.LEWO;
                    break;
                case 2:
                    gdzie = KIERUNEK.DOL;
                    break;
                default:
                    break;
            }
        }
        //calkiem na dole bez rogow
        else if (obecna_pozycja.getY() == swiat.getY() - 1) {
            temp = r.nextInt(3);
            switch (temp)
            {
                case 0:
                    gdzie = KIERUNEK.PRAWO;
                    break;
                case 1:
                    gdzie = KIERUNEK.LEWO;
                    break;
                case 2:
                    gdzie = KIERUNEK.GORA;
                    break;
                default:
                    break;
            }
        }
        else {
            temp = r.nextInt(4);
            gdzie = KIERUNEK.values()[temp];
        }
        return gdzie;
    }

    @Override
    public void kolizja(Organizm atakujacy) {
        String temp = "";
        temp = atakujacy.rysowanie() + " zjadl " + this.rysowanie();
        swiat.setKomunikat(temp);
        this.setIstnieje(false);


        POLOZENIE wsp1 = this.getWsp();

        swiat.dodajOrganizm(null, wsp1);

    }
    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }
    public String rysowanie() {
        return "R";
    }

}

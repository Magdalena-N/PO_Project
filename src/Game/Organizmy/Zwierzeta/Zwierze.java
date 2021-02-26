package Game.Organizmy.Zwierzeta;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;

import Game.Organizmy.POLOZENIE;
import Game.Swiat;

import java.util.Random;

public abstract class Zwierze extends Organizm {


    Zwierze(Swiat xswiat, int xsila, int xinicjatywa, POLOZENIE xwsp, int xwiek)
    {

     super(xswiat, xsila, xinicjatywa, xwsp, xwiek);

    }


    public void akcja() {
        boolean czyRuchMozliwy = true;
        KIERUNEK gdzie;
        POLOZENIE wsp2 = new POLOZENIE(this.getWsp().getX(), this.getWsp().getY());

        if (this.getNastepnyKierunek() == KIERUNEK.BRAK && this.rysowanie() != "?") {
            gdzie = losujKierunek(this,this.getZasieg());
        } else {
            gdzie = this.getNastepnyKierunek();
            if ((gdzie == KIERUNEK.GORA && wsp2.getY() - 1 < 0) ||
                    (gdzie == KIERUNEK.DOL && wsp2.getY() + 1 > swiat.getY() - 1) ||
                    (gdzie == KIERUNEK.LEWO && wsp2.getX() - 1 < 0) ||
                    (gdzie == KIERUNEK.PRAWO && wsp2.getX() + 1 > swiat.getX() - 1) ||
                    (gdzie == KIERUNEK.BRAK))

                czyRuchMozliwy = false;
        }
        if (czyRuchMozliwy) {


            if (czyWolne(gdzie, this.getZasieg())) {
                ruch(this, gdzie);
            } else {
                switch (gdzie) {
                    case GORA:
                        wsp2.setY(wsp2.getY() - this.getZasieg());
                        //wsp2.y -= this->getZasieg();
                        break;
                    case DOL:
                        wsp2.setY(wsp2.getY() + this.getZasieg());
                        //wsp2.y += this->getZasieg();
                        break;
                    case LEWO:
                        wsp2.setX(wsp2.getX() - this.getZasieg());
                        //wsp2.x -= this->getZasieg();
                        break;
                    case PRAWO:
                        wsp2.setX(wsp2.getX() + this.getZasieg());
                        //wsp2.x += this->getZasieg();
                        break;
                    default:
                        break;
                }

                swiat.getOrganizm(wsp2).kolizja(this);

                if (swiat.getOrganizm(this.getWsp()) != null && this.getOdbity() == false &&
                        this.getRozmnozone() == false) {
                    ruch(this, gdzie);
                } else if (swiat.getOrganizm(this.getWsp()) != null) {
                    this.setOdbity(false);
                    this.setRozmnozone(false);
                }

            }
        }
    }
    public boolean czyWolne(KIERUNEK gdzie, int zasieg) {
        POLOZENIE pozycja = new POLOZENIE(this.getWsp().getX(),this.getWsp().getY());

        switch (gdzie) {
            case GORA:
                pozycja.setY(pozycja.getY()-zasieg);
                //pozycja.y -= zasieg;
                break;
            case DOL:
                pozycja.setY(pozycja.getY()+zasieg);
                break;
            case LEWO:
                pozycja.setX(pozycja.getX()-zasieg);
                break;
            case PRAWO:
                pozycja.setX(pozycja.getX()+zasieg);
                break;
        }
        if(swiat.getOrganizm(pozycja) == null){
            return true;
        }
        else{
            return false;
        }

    }

    KIERUNEK losujKierunek(Organizm org, int zasieg) {
        Random r = new Random();
        int temp;
        KIERUNEK gdzie = KIERUNEK.BRAK;
        POLOZENIE obecna_pozycja;
        obecna_pozycja = org.getWsp();
        //obiekt calkiem z lewej
        if (obecna_pozycja.getX() == 0 || obecna_pozycja.getX() == zasieg - 1) {
            //lewy gorny rog(PRAWO,DOL)
            if (obecna_pozycja.getY() == 0 || obecna_pozycja.getY() == zasieg - 1) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.PRAWO : KIERUNEK.DOL;

            }
            //lewy dolny rog (GORA,PRAWO)
            else if (obecna_pozycja.getY() == swiat.getY() - 1 || obecna_pozycja.getY() == swiat.getY() - zasieg ) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.GORA : KIERUNEK.PRAWO;
            }
            //tylko w lewo nie moze
            else {
                temp = r.nextInt(3);
                switch (temp) {
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
        else if (obecna_pozycja.getX() == swiat.getX() - 1 || obecna_pozycja.getX() == swiat.getX() - zasieg) {
            //prawy gorny rog
            if (obecna_pozycja.getY() == 0 || obecna_pozycja.getY() == zasieg - 1 ) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.LEWO : KIERUNEK.DOL;
            }
            //prawy dolny rog
            else if (obecna_pozycja.getY() == swiat.getY() - 1 || obecna_pozycja.getY() == swiat.getY() - zasieg ) {
                temp = r.nextInt(2);
                gdzie = temp == 0 ? KIERUNEK.LEWO: KIERUNEK.GORA;
            }
            //tylko w prawo nie moze
            else {
                temp = r.nextInt(3);
                switch (temp) {
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
        else if (obecna_pozycja.getY() == 0 || obecna_pozycja.getY() == zasieg - 1 ) {
            temp = r.nextInt(3);
            switch (temp) {
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
        else if (obecna_pozycja.getY() == swiat.getY() - 1 || obecna_pozycja.getY() == swiat.getY() - zasieg ) {
            temp = r.nextInt(3);
            switch (temp) {
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
        } else {
            temp = r.nextInt(4);
            gdzie = KIERUNEK.values()[temp];
        }
        return gdzie;
    }

    @Override
    public boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    public void rozmnoz(KIERUNEK gdzie) {
        POLOZENIE wsp2 = new POLOZENIE(this.getWsp().getX(),this.getWsp().getY());
        switch (gdzie) {
            case GORA:
                wsp2.setY(wsp2.getY()-1);
                //wsp2.y--;
                break;
            case DOL:
                wsp2.setY(wsp2.getY()+1);
                //wsp2.y++;
                break;
            case LEWO:
                wsp2.setX(wsp2.getX()-1);
                //wsp2.x--;
                break;
            case PRAWO:
                wsp2.setX(wsp2.getX()+1);
                //wsp2.x++;
                break;
            default:
                break;
        }


        if (czyWolne(gdzie, 1)) {

            this.dodaj(wsp2);

        }
	/*else {
		gdzie = losujKierunek(this);
		rozmnoz(gdzie);
	}*/

    }
    public abstract void dodaj(POLOZENIE wsp);
   // public abstract String rysowanie();

    @Override
    public void kolizja(Organizm atakujacy) {
        KIERUNEK gdzie;
        if (this.rysowanie() == atakujacy.rysowanie()){
            gdzie = losujKierunek(this,1);
            rozmnoz(gdzie);

            atakujacy.setRozmnozone(true);

        }
        else{
            //sila zaatakowanego mniejsza/rowna od atakujacego ginie zaatakowany
            if (this.getSila() <= atakujacy.getSila()){
                String temp = "";
                temp = atakujacy.rysowanie() + " zabil/a " + this.rysowanie();
                swiat.setKomunikat(temp);
                this.setIstnieje(false);

                swiat.dodajOrganizm(null, this.getWsp());


            }

            //w przeciwnym wypadku sila zaatakowanego wieksza ginie atakujacy
        else{
                String temp = "";
                temp += this.rysowanie() + " zabil/a " + atakujacy.rysowanie();

                swiat.setKomunikat(temp);
                atakujacy.setIstnieje(false);

                swiat.dodajOrganizm(null, atakujacy.getWsp());


            }
        }

    }

    public void ruch(Organizm org, KIERUNEK gdzie) {
        POLOZENIE wsp1 = org.getWsp();
        POLOZENIE wsp2 = new POLOZENIE(wsp1.getX(),wsp1.getY());
        //Organizm** temp = org->getSwiat()->getOrganizm(wsp2);

        switch (gdzie) {
            case GORA:
                wsp2.setY(wsp2.getY()-org.getZasieg());
                //wsp2.y -= org -> getZasieg();
                break;
            case DOL:
                wsp2.setY(wsp2.getY()+org.getZasieg());
                //wsp2.y += org -> getZasieg();
                break;
            case LEWO:
                wsp2.setX(wsp2.getX()-org.getZasieg());
                //wsp2.x -= org -> getZasieg();
                break;
            case PRAWO:
                wsp2.setX(wsp2.getX()+org.getZasieg());
                //wsp2.x += org -> getZasieg();
                break;
            default:
                break;
        }
        org.setWsp(wsp2);
        swiat.dodajOrganizm(org, wsp2);
        swiat.dodajOrganizm(null, wsp1);

    }
}
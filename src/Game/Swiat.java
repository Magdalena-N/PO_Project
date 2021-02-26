package Game;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Organizmy.Rosliny.*;
import Game.Organizmy.Zwierzeta.*;
import com.sun.org.apache.xpath.internal.operations.Or;


import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Comparator;

public class Swiat{

    private int x, y;
    private Organizm[][] mapa;
    //private final Vector<String> komunikaty = new Vector<>();
    private String komunikat = new String();
    private boolean czlowiekZyje;
    //private ArrayList<Organizm> doUsuniecia = new ArrayList<>();
    //private Vector<Organizm> organizmy = new Vector<>();

    public Swiat(int x,int y){
        this.x = x;
        this.y = y;
        mapa = new Organizm[y][x];
        for(int i = 0 ; i < y ; i++){
            for(int j = 0 ; j < x ; j++){
                mapa[i][j] = null;
            }
        }
        this.czlowiekZyje=false;

    }
    public void setCzlowiekZyje(boolean zyje) {
        this.czlowiekZyje = zyje;
    }
    public boolean getCzlowiekZyje() {
        return czlowiekZyje;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void wykonajTure(){

        //doUsuniecia.clear();
        Vector<Organizm> organizmy = new Vector<>();
        for(int i = 0; i< y ; i++){
            for( int j = 0; j < x ; j++){
                if(mapa[i][j] != null){
                    organizmy.add(mapa[i][j]);
                }
            }
        }
        Collections.sort(organizmy, new Comparator<Organizm>() {
            @Override
            public int compare(Organizm o1, Organizm o2) {
                if(o1.getInicjatywa()>o2.getInicjatywa()){
                    return -1;
                }
                else if(o1.getInicjatywa()== o2.getInicjatywa())
                    if(o1.getWiek()>o2.getWiek())
                        return -1;
                    else
                        return 1;
                else
                    return 1;
            }
        });

        /*organizmy.sort((Organizm o1,Organizm o2)-> compare(o1,o2){//new Comparator<Organizm>() {
           // @Override
            public int compare(o1, o2) {
                if(o1.getInicjatywa()>o2.getInicjatywa()){
                    return 1;
                }
                else if(o1.getInicjatywa()== o2.getInicjatywa())
                    if(o1.getWiek()>o2.getWiek())
                        return 1;
                    else
                        return -1;
                else
                    return 0;

            }
        });*/
        for (Organizm org:organizmy)
        {
         org.setWiek(org.getWiek()+1);
        }

        for (Organizm it : organizmy) {
            if (it.getIstnieje()) {
                it.akcja();

            }
        }
        organizmy.clear();


    }
    /*public boolean czyIstnieje(Organizm org) {

        if (org != null) {
            return true;
        }
        else
            return false;

    }*/

    public Organizm getOrganizm(POLOZENIE pozycja) {
        return mapa[pozycja.getY()][pozycja.getX()];
    }

    public void dodajOrganizm(Organizm xorgan, POLOZENIE pozycja) {
       mapa[pozycja.getY()][pozycja.getX()] = xorgan;
    }

    public void setKomunikat(String xlog) {

        komunikat = komunikat + xlog + "\n";

    }
    public String getKomunikat() {
        return komunikat;
    }
    void piszKomunikat() {
        System.out.println(komunikat);
        komunikat = "";
    }

    public void uruchomUmiejetnosc() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (mapa[i][j] != null && mapa[i][j].rysowanie() == "?") {
                    mapa[i][j].setUmiejetnosc(true);
                }

            }
        }

    }
    public void ustawKierunekCzlowiekowi(KIERUNEK kierunek) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (mapa[i][j] != null && mapa[i][j].rysowanie() == "?") {
                    mapa[i][j].setNastepnyKierunek(kierunek);
                }

            }
        }

    }

    public void save() {
        Vector<Organizm> organizmy = new Vector<>();
        for(int i = 0; i< y ; i++){
            for( int j = 0; j < x ; j++){
                if(mapa[i][j] != null){
                    organizmy.add(mapa[i][j]);
                }
            }
        }
        try (PrintWriter writer = new PrintWriter("save.txt", "UTF-8")) {
            writer.println(this.x + " " + this.y);
            for (Organizm org : organizmy) {
                writer.println(org.getClass().getSimpleName());

                if(org instanceof Czlowiek){
                    writer.println(org.getWsp().getX() + " " +
                            org.getWsp().getY() + " " +
                            org.getInicjatywa() + " " +
                            org.getSila() + " " +
                            org.getWiek() + " " +
                            org.getUmiejetnosc() + " " +
                            ((Czlowiek) org).getCzekaj() + " " +
                            ((Czlowiek) org).getPozostalyEliksir());

                }
                else{
                    writer.println(org.getWsp().getX() + " " +
                            org.getWsp().getY() + " " +
                            org.getInicjatywa() + " " +
                            org.getSila() + " " +
                            org.getWiek() + " " +
                            org.getUmiejetnosc() + " ");
                }
                //writer.println(komunikat);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public Swiat load() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("save.txt"));
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Swiat swiat = new Swiat(x, y);
        while (scanner.hasNext()) {
            scanner.nextLine();
            String name = scanner.nextLine();
            int px = scanner.nextInt();
            int py = scanner.nextInt();
            POLOZENIE wsp2 = new POLOZENIE(px,py);
            int inicjatywa = scanner.nextInt();
            int sila = scanner.nextInt();
            int wiek = scanner.nextInt();
            boolean umiejetnosc = scanner.nextBoolean();
            Organizm org;
            switch (name) {
                case "Czlowiek":
                    org = new Czlowiek(swiat,wsp2);
                    int czekaj = scanner.nextInt();
                    ((Czlowiek) org).setCzekaj(czekaj);
                    int pozostalyEliksir = scanner.nextInt();
                    ((Czlowiek) org).setPozostalyEliksir(pozostalyEliksir);

                    break;
                case "Wilk": {
                    org = new Wilk(swiat,wsp2);
                    break;
                }
                case "Trawa":{
                    org = new Trawa(swiat,wsp2);
                    break;
                }
                case "BarszczSosnowskiego":{
                    org = new BarszczSosnowskiego(swiat,wsp2);
                    break;
                }
                case "Guarana":{
                    org = new Guarana(swiat,wsp2);
                    break;
                }
                case "Mlecz":{
                    org = new Mlecz(swiat,wsp2);
                    break;
                }
                case "WilczeJagody":{
                    org = new WilczeJagody(swiat,wsp2);
                    break;
                }
                case "Antylopa":{
                    org = new Antylopa(swiat,wsp2);
                    break;
                }
                case "Lis":{
                    org = new Lis(swiat,wsp2);
                    break;
                }
                case "Owca":{
                    org = new Owca(swiat,wsp2);
                    break;
                }
                case "Zolw":{
                    org = new Zolw(swiat,wsp2);
                    break;
                }
                default: {
                    org = null;
                    break;
                }
            }
            if(org != null){
                org.setInicjatywa(inicjatywa);
                org.setSila(sila);
                org.setWiek(wiek);
                org.setUmiejetnosc(umiejetnosc);
                swiat.dodajOrganizm(org,org.getWsp());
                swiat.setKomunikat(komunikat);

            }

        }
        return swiat;
    }


}

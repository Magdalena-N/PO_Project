package Game;

import Game.Organizmy.POLOZENIE;
/*//import Game.Organizmy.Rosliny.Trawa;
//import Game.Organizmy.Zwierzeta.Czlowiek;
import Game.Organizmy.Rosliny.BarszczSosnowskiego;
import Game.Organizmy.Rosliny.Guarana;
import Game.Organizmy.Rosliny.Trawa;
import Game.Organizmy.Rosliny.WilczeJagody;*/
import Game.Organizmy.Rosliny.*;
import Game.Organizmy.Zwierzeta.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Swiat swiat = new Swiat(10,10);
        //swiat.losujOrganizmy(swiat);
        swiat.dodajOrganizm(new Zolw(swiat,new POLOZENIE(7,0)), new POLOZENIE(7,0));
        swiat.dodajOrganizm(new Owca(swiat,new POLOZENIE(1,7)),new POLOZENIE(1,7));
        swiat.dodajOrganizm(new Lis(swiat,new POLOZENIE(6,3)),new POLOZENIE(6,3));
        swiat.dodajOrganizm(new Antylopa(swiat,new POLOZENIE(8,0)),new POLOZENIE(8,0));
        swiat.dodajOrganizm(new WilczeJagody(swiat,new POLOZENIE(3,2)),new POLOZENIE(3,2));
        swiat.dodajOrganizm(new Guarana(swiat,new POLOZENIE(8,1)),new POLOZENIE(8,1));
        swiat.dodajOrganizm(new BarszczSosnowskiego(swiat,new POLOZENIE(4,4)),new POLOZENIE(4,4));
        swiat.dodajOrganizm(new Trawa(swiat,new POLOZENIE(5,5)),new POLOZENIE(5,5));
        swiat.dodajOrganizm(new Wilk(swiat,new POLOZENIE(5,8)),new POLOZENIE(5,8));
        swiat.dodajOrganizm(new Wilk(swiat,new POLOZENIE(7,8)),new POLOZENIE(7,8));
        swiat.dodajOrganizm(new Czlowiek(swiat,new POLOZENIE(1,1)),new POLOZENIE(1,1));
        EventQueue.invokeLater(() -> {
            new Okienko(swiat);
        });
       /* EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Okienko(swiat);
            }
        });*/

    }

}

package Game;

import Game.Organizmy.POLOZENIE;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;

import javax.swing.*;
import java.awt.*;

public class Plansza extends JPanel{
    private JPanel plansza;
    private JButton[][] pola;

    public Plansza(Swiat swiat){
        plansza = new JPanel();
        GridLayout gridLayout = new GridLayout(swiat.getY(),swiat.getX());
        plansza.setMaximumSize(new Dimension(700,800));
        plansza.setLayout(gridLayout);
        plansza.setBorder(BorderFactory.createTitledBorder("Plansza"));
        pola = new JButton[swiat.getY()][swiat.getX()];

        for(int i = 0; i<swiat.getY();i++){
            for(int j = 0; j<swiat.getX();j++){
                pola[i][j] = new JButton();
                pola[i][j].setBackground(Color.WHITE);
                plansza.add(pola[i][j]);
            }
        }

    }
    public JPanel getPlansza(){
        return plansza;
    }

    public JButton[][] getPola(){return pola;}

    public void rysujPlansze(Swiat swiat){
        //setLayout(new GridLayout(swiat.getY(),swiat.getX()));
        POLOZENIE temp_pozycja = new POLOZENIE(0,0);
        for(int i = 0; i<swiat.getY();i++){
            for(int j = 0 ; j < swiat.getX(); j++){
                temp_pozycja.setX(j);
                temp_pozycja.setY(i);
                if(swiat.getOrganizm(temp_pozycja)==null){
                    pola[i][j].setText("");
                    pola[i][j].setBackground(Color.WHITE);
                }
                else{
                    pola[i][j].setText(swiat.getOrganizm(temp_pozycja).rysowanie());
                    pola[i][j].setBackground(swiat.getOrganizm(temp_pozycja).getKolor());
                }
            }
        }

    }
}

package Game;

import Game.Organizmy.KIERUNEK;
import Game.Organizmy.Organizm;
import Game.Organizmy.POLOZENIE;
import Game.Organizmy.Rosliny.*;
import Game.Organizmy.Zwierzeta.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;


public class Okienko extends JFrame implements KeyEventDispatcher {

    private Plansza plansza;
    private Menu menu;
    private JButton nowaTura;
    private JButton umiejetnosc;
    private JButton save;
    private JButton load;
    private Swiat swiat;
    private BorderLayout borderLayout;

    public Okienko(Swiat xswiat){
        this.swiat = xswiat;

        this.setSize(1000,800);
        this.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Magdalena Nagel 175741");

        borderLayout = new BorderLayout(7,0);
        setLayout(borderLayout);
        setFocusable(true);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);


        plansza = new Plansza(swiat);
        plansza.rysujPlansze(swiat);
        add(plansza.getPlansza(),borderLayout.WEST);

        JButton[][] przyciski = plansza.getPola();

        for(int i = 0; i< swiat.getY();i++){
            for(int j = 0; j< swiat.getX();j++){
                int finalJ = j;
                int finalI = i;
                przyciski[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (swiat.getOrganizm(new POLOZENIE(finalJ, finalI)) == null) {
                            Object[] possibleValues = {"Trawa", "Wilk" , "BarszczSosnowskiego", "Guarana", "Mlecz", "WilczeJagody" , "Antylopa" ,"Lis","Owca","Zolw"};
                            JOptionPane optionPane = new JOptionPane();
                            optionPane.setFocusable(false);
                            Object selectedValue = JOptionPane.showInputDialog(null, "Wybierz zwierze", "Dodawanie organizmu", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
                            Organizm org;
                            if (selectedValue != null) {
                                switch (selectedValue.toString()) {

                                    case "Wilk": {
                                        org = new Wilk(swiat, new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Trawa": {
                                        org = new Trawa(swiat, new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "BarszczSosnowskiego":{
                                        org = new BarszczSosnowskiego(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Guarana":{
                                        org = new Guarana(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Mlecz":{
                                        org = new Mlecz(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "WilczeJagody":{
                                        org = new WilczeJagody(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Antylopa":{
                                        org = new Antylopa(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Lis":{
                                        org = new Lis(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Owca":{
                                        org = new Owca(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    case "Zolw":{
                                        org = new Zolw(swiat,new POLOZENIE(finalJ, finalI));
                                        break;
                                    }
                                    default: {
                                        org = null;
                                        break;
                                    }
                                }
                                swiat.dodajOrganizm(org, org.getWsp());
                                plansza.rysujPlansze(swiat);

                            }
                        }
                    }
                });

            }
        }

        menu = new Menu(swiat);
        add(menu.getMenu(),borderLayout.EAST);

        JPanel opcje = new JPanel();
        nowaTura = new JButton("Nowa Tura");
        nowaTura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //menu.wyczyscRamke(swiat);
                remove(menu.getMenu());
                swiat.piszKomunikat();
                swiat.wykonajTure();
                plansza.rysujPlansze(swiat);

                //swiat.setKomunikat("");

                menu = new Menu(swiat);
                add(menu.getMenu(),borderLayout.EAST);
            }
        });
        nowaTura.setFocusable(false);

        umiejetnosc = new JButton("Umiejetnosc");
        umiejetnosc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(swiat.getCzlowiekZyje() == true){
                    swiat.uruchomUmiejetnosc();
                }
            }
        });
        umiejetnosc.setFocusable(false);
        save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swiat.save();
            }
        });
        //save.addActionListener(this);
        load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    swiat = swiat.load();
                    plansza.rysujPlansze(swiat);


                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        opcje.add(nowaTura);
        opcje.add(umiejetnosc);
        opcje.add(save);
        opcje.add(load);
        add(opcje,borderLayout.SOUTH);

        //pack();


        this.setVisible(true);
        requestFocus();
    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        //System.out.println(e.paramString());
        if (e.getID() == KeyEvent.KEY_PRESSED) {


            if (swiat.getCzlowiekZyje()) {
                KIERUNEK kierunek = KIERUNEK.BRAK;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        kierunek = KIERUNEK.GORA;
                        break;
                    case KeyEvent.VK_DOWN:
                        kierunek = KIERUNEK.DOL;
                        break;
                    case KeyEvent.VK_RIGHT:
                        kierunek = KIERUNEK.PRAWO;
                        break;
                    case KeyEvent.VK_LEFT:
                        kierunek = KIERUNEK.LEWO;
                        break;
                    case KeyEvent.VK_SPACE:
                        swiat.uruchomUmiejetnosc();
                        break;
                    case KeyEvent.VK_N:
                        remove(menu.getMenu());
                        swiat.piszKomunikat();
                        swiat.wykonajTure();
                        plansza.rysujPlansze(swiat);

                        //swiat.setKomunikat("");

                        menu = new Menu(swiat);
                        add(menu.getMenu(), borderLayout.EAST);
                        break;
                    default: {
                        return false;
                    }
                }


                swiat.ustawKierunekCzlowiekowi(kierunek);

            } else
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_N:
                        remove(menu.getMenu());
                        swiat.piszKomunikat();
                        swiat.wykonajTure();
                        plansza.rysujPlansze(swiat);
                        menu = new Menu(swiat);
                        add(menu.getMenu(), borderLayout.EAST);
                        break;
                    default:
                        return false;
                }
        }
        return false;
    }

}

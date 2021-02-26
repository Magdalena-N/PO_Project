package Game;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


public class Menu extends JPanel{
    JPanel menu;
    JTextArea logArea;
    JScrollPane logScroller;

    public Menu(Swiat swiat){
        menu=new JPanel();
        menu.setBorder(BorderFactory.createTitledBorder("Menu"));
        menu.setMinimumSize(new Dimension(300,800));

        /*JTextArea textArea = new JTextArea(40,30);
        textArea.setText(swiat.getKomunikat().toString());
        textArea.setLineWrap(true);*/


        /*JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(300,800);*/

        logArea = new JTextArea(40, 30);
        logArea.setWrapStyleWord(true);
        logArea.setLineWrap(true);
        logArea.append(swiat.getKomunikat());
        logArea.setEditable(false);

        logScroller = new JScrollPane();
        logScroller.setSize(300,800);
        logScroller.setViewportView(logArea);
        menu.add(logScroller);

       /* menu.setBorder(BorderFactory.createTitledBorder("Komentarze"));
        menu.setMaximumSize(new Dimension(300,800));
        JTextArea area = new JTextArea(swiat.getKomunikat(), 1000, 50);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVisible(true);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        menu.add(scroll);*/
        //add(panel,BorderLayout.EAST);

    }
    /*public void wyczyscRamke(Swiat swiat){
        swiat.piszKomunikat();
        //logArea.setText(null);
    }*/


    public JPanel getMenu(){
        return menu;
    }

}

package pongbreaker.pongbreaker;

import javax.swing.SwingUtilities;
import pongbreaker.gui.Kayttoliittyma;
import pongbreaker.peli.Pongbreaker;

public class Main {

    public static void main(String[] args) {
        
        Pongbreaker peli = new Pongbreaker();

        Kayttoliittyma kali = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kali);

        while (kali.getPaivitettava() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        peli.setPaivitettava(kali.getPaivitettava());
        peli.start();
        
    }

}

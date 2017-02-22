package pongbreaker.pongbreaker;

import javax.swing.SwingUtilities;
import pongbreaker.gui.Kayttoliittyma;
import pongbreaker.gui.PisteidenKasittelija;
import pongbreaker.peli.Pongbreaker;

public class Main {

    /**
     * Main-luokka.
     * @param args .
     */
    public static void main(String[] args) {
        
        Pongbreaker peli = new Pongbreaker(500, 350);

        Kayttoliittyma kali = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kali);

        while (kali.getPiirtoalusta() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }

        peli.lisaaPaivitettava(kali.getPiirtoalusta());
        peli.lisaaPaivitettava(kali.getPisteidenKasittelija());
        peli.start();

    }

}

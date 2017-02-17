package pongbreaker.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import pongbreaker.peli.Pongbreaker;

/**
 * Luokka avaa ikkunan, jossa peliä pelataan.
 *
 * @author wolli
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private Piirtoalusta piirtoalusta;
    private Pongbreaker peli;

    /**
     * Luokan konstruktori.
     *
     * @param peli Pongbreaker-olio, joka siirretään eteenpäin muille luokille.
     */
    public Kayttoliittyma(Pongbreaker peli) {
        this.peli = peli;
    }

    @Override
    public void run() {
        frame = new JFrame("Pongbreaker");
        frame.setPreferredSize(new Dimension(500, 350));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    /**
     * Lisää komponentit käyttöliittymään.
     *
     * @param container sisältää komponentit.
     */
    private void luoKomponentit(Container container) {
        piirtoalusta = new Piirtoalusta(peli);
        container.add(piirtoalusta);

        Nappaimistonkuuntelija kuuntelija = new Nappaimistonkuuntelija(peli);
        frame.addKeyListener(kuuntelija);
    }

    public Paivitettava getPaivitettava() {
        return piirtoalusta;
    }

}

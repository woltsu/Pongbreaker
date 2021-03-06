package pongbreaker.gui;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import pongbreaker.domain.Peliolio;
import pongbreaker.peli.Pongbreaker;

/**
 * Luokka on vastuussa grafiikoiden piirtämisestä näytölle.
 *
 * @author wolli
 */
public class Piirtoalusta extends JPanel implements Paivitettava {

    private Pongbreaker peli;
    private BufferedImage img;
    private Graphics gfx;

    /**
     * Luokan konstruktori.
     *
     * @param peli Pongbreaker-olio, jolta luokka saa piirrettävät pelioliot.
     */
    public Piirtoalusta(Pongbreaker peli) {
        super.setBackground(Color.black);
        this.peli = peli;
        this.img = new BufferedImage(500, 350, BufferedImage.TYPE_BYTE_INDEXED);
        this.gfx = this.img.createGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(gfx);
        for (Peliolio piirrettava : this.peli.getPiirrettavat()) {
            piirrettava.piirra(gfx);
        }
        gfx.setColor(Color.white);
        if (!peli.getOnkoPaalla()) {
            gfx.drawString("Press spacebar", 200, 175);
        }
        gfx.drawString("Score: " + peli.getPelaaja().getPisteet(), 50, 20);
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void paivita() {
        super.repaint();
    }

}

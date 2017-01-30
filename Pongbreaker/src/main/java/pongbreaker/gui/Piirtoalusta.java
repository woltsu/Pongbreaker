package pongbreaker.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import pongbreaker.domain.Piirrettava;
import pongbreaker.peli.Pongbreaker;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Pongbreaker peli;
    private BufferedImage img;
    private Graphics gfx;

    public Piirtoalusta(Pongbreaker peli) {
        super.setBackground(Color.black);
        this.peli = peli;
        this.img = new BufferedImage(500, 350, BufferedImage.TYPE_BYTE_INDEXED);
        this.gfx = this.img.createGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(gfx);;
        for (Piirrettava piirrettava : this.peli.getPiirrettavat()) {
            piirrettava.piirra(gfx);
        }
        //Viivat todennäköisesti poistetaan jossakin vaiheessa
        //gfx.drawLine(peli.getPaatyrajanleveys(), 0, peli.getPaatyrajanleveys(), 350);
        //gfx.drawLine(peli.getLeveys() - peli.getPaatyrajanleveys() - 10, 0, peli.getLeveys() - peli.getPaatyrajanleveys() - 10, peli.getKorkeus());
        g.drawImage(img, 0, 0, this);
    }

    @Override
    public void paivita() {
        super.repaint();
    }

}

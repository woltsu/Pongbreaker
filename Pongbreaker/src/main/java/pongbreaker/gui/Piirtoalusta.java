package pongbreaker.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import pongbreaker.peli.Pongbreaker;

public class Piirtoalusta extends JPanel implements Paivitettava {

    private Pongbreaker peli;

    public Piirtoalusta(Pongbreaker peli) {
        super.setBackground(Color.black);
        this.peli = peli;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.peli.getPallo().piirra(g);
    }

    @Override
    public void paivita() {
        super.repaint();
    }

}

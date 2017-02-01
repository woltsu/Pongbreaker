package pongbreaker.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pongbreaker.peli.Pelaaja;
import pongbreaker.peli.Pongbreaker;

public class Nappaimistonkuuntelija implements KeyListener {

    private Pongbreaker peli;
    private Pelaaja pelaaja;
    
    public Nappaimistonkuuntelija(Pongbreaker peli) {
        this.peli = peli;
        this.pelaaja = peli.getPelaaja();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja.setLiikkuuko(true);
            pelaaja.vahennaKiihtyvyytta();
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja.setLiikkuuko(true);
            pelaaja.lisaaKiihtyvyytta();
            
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!peli.onkoPaalla()) {
                peli.kaynnistaPeli();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pelaaja.setLiikkuuko(false);
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pelaaja.setLiikkuuko(false);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}

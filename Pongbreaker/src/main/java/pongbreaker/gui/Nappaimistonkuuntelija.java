package pongbreaker.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pongbreaker.peli.Pelaaja;

public class Nappaimistonkuuntelija implements KeyListener {

    private Pelaaja pelaaja;

    public Nappaimistonkuuntelija(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.pelaaja.setLiikkuuko(true);
            this.pelaaja.vahennaKiihtyvyytta();
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.pelaaja.setLiikkuuko(true);
            this.pelaaja.lisaaKiihtyvyytta();
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.pelaaja.setLiikkuuko(false);
            
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.pelaaja.setLiikkuuko(false);
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

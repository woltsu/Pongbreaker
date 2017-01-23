package pongbreaker.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pongbreaker.domain.Pallo;
import pongbreaker.gui.Paivitettava;

public class Pongbreaker extends Timer implements ActionListener {
    
    private Paivitettava paivitettava;
    private Pallo pallo;
    
    public Pongbreaker() {
        super(1000, null);
        addActionListener(this);
        this.pallo = new Pallo(5, 50, 50);
    }
    
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }
    
    public Pallo getPallo() {
        return this.pallo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        pallo.liiku();
        paivitettava.paivita();
        setDelay(30);
    }
    
    
    
}

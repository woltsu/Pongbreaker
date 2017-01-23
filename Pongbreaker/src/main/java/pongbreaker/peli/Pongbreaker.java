package pongbreaker.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pongbreaker.domain.Pallo;
import pongbreaker.gui.Paivitettava;

public class Pongbreaker extends Timer implements ActionListener {
    private int leveys;
    private int korkeus;
    
    private Paivitettava paivitettava;
    private Pallo pallo;
    
    public Pongbreaker(int leveys, int korkeus) {
        super(1000, null);
        addActionListener(this);
        
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.pallo = new Pallo(6, 50, 50);
    }
    
    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }
    
    public Pallo getPallo() {
        return this.pallo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (pallo.getX() <= pallo.getR() || pallo.getX() >= this.leveys - pallo.getR() - 10) {
            pallo.kaannaXNopeus();
        }
        
        if (pallo.getY() <= pallo.getR() || pallo.getY() >= this.korkeus - pallo.getR() - 30) {
            pallo.kaannaYNopeus();
        }
        
        pallo.liiku();
        paivitettava.paivita();
        setDelay(30);
    }
    
    
    
}

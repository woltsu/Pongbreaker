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
        super(100, null);
        addActionListener(this);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.pallo = new Pallo(6, this.leveys / 2 - 10, this.korkeus / 2 - 30);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Pallo getPallo() {
        return this.pallo;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public void tarkistaOsuukoPalloReunoihin() {
        /*Luvut -10 ja -30 tulevat siitä, että näytettävä ikkunan koko ei ole sama kuin alkuperäisesti
        asetettu leveys ja korkeus*/
        if (pallo.getX() <= pallo.getR() || pallo.getX() >= this.leveys - pallo.getR() - 10) {
            pallo.kaannaXNopeus();
        }

        if (pallo.getY() <= pallo.getR() || pallo.getY() >= this.korkeus - pallo.getR() - 30) {
            pallo.kaannaYNopeus();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tarkistaOsuukoPalloReunoihin();
        pallo.liiku();
        paivitettava.paivita();
        setDelay(30);
    }

}

package pongbreaker.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;
import pongbreaker.gui.Paivitettava;

public class Pongbreaker extends Timer implements ActionListener {

    private int leveys;
    private int korkeus;
    private int paatyrajanLeveys;

    private Paivitettava paivitettava;
    private Pallo pallo;
    private Pelaaja pelaaja;
    private Vastustaja vastustaja;

    private List<Peliolio> piirrettavat;
    private TormayksienHavaitsija tormayksienHavaitsija;

    public Pongbreaker(int leveys, int korkeus) {
        super(100, null);
        addActionListener(this);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.paatyrajanLeveys = 30;

        this.pallo = new Pallo(6, this.leveys / 2 - 10, this.korkeus / 2 - 30);
        this.pelaaja = new Pelaaja(new Maila(this.paatyrajanLeveys, this.korkeus / 2));
        this.vastustaja = new Vastustaja(new Maila(this.leveys - 10 - this.paatyrajanLeveys, this.korkeus / 2), pallo);
        pallo.setYNopeus(2);
        pallo.setXNopeus(4);

        this.piirrettavat = new ArrayList<>();
        this.piirrettavat.add(pallo);
        this.piirrettavat.add(this.pelaaja.getMaila());
        this.piirrettavat.add(this.vastustaja.getMaila());
        this.tormayksienHavaitsija = new TormayksienHavaitsija(pallo, pelaaja, vastustaja);

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

    public boolean tarkistaOhittaakoPalloPaatyrajan() {
        //Tulostaminen on väliaikasta
        if (pallo.getX() <= paatyrajanLeveys - pallo.getR()) {
            System.out.println("Pelaaja 1 hävisi");
            return true;

        } else if (pallo.getX() >= this.leveys - paatyrajanLeveys - 10 + pallo.getR()) {
            System.out.println("Pelaaja 2 hävisi");
            return true;
        }

        return false;
    }

    public boolean tarkistaMeneekoMailaYliRajojen(Maila maila) {
        if (maila.getY() < maila.getKorkeus() / 2) {
            maila.setY(maila.getKorkeus() / 2);
            return true;
        } else if (maila.getY() > this.korkeus - 30 - maila.getKorkeus() / 2) {
            maila.setY(this.korkeus - 30 - maila.getKorkeus() / 2);
            return true;
        }
        
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tarkistaOsuukoPalloReunoihin();
        tarkistaOhittaakoPalloPaatyrajan();

        pelaaja.liiku();
        vastustaja.liiku();
        tormayksienHavaitsija.tarkistaTormaykset();
        pallo.liiku();

        tarkistaMeneekoMailaYliRajojen(pelaaja.getMaila());
        tarkistaMeneekoMailaYliRajojen(vastustaja.getMaila());

        paivitettava.paivita();
        setDelay(30);
    }

    public void setPaivitettava(Paivitettava paivitettava) {
        this.paivitettava = paivitettava;
    }

    public Pallo getPallo() {
        return this.pallo;
    }

    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public int getLeveys() {
        return this.leveys;
    }

    public int getKorkeus() {
        return this.korkeus;
    }

    public int getPaatyrajanleveys() {
        return this.paatyrajanLeveys;
    }

    public List<Peliolio> getPiirrettavat() {
        return this.piirrettavat;
    }

}

package pongbreaker.peli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import pongbreaker.domain.Laatikko;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;
import pongbreaker.gui.Paivitettava;

public class Pongbreaker extends Timer implements ActionListener {

    private int leveys;
    private int korkeus;
    private int paatyrajanLeveys;

    private boolean onkoPaalla;

    private Paivitettava paivitettava;
    private Pallo pallo;
    private Pelaaja pelaaja;
    private Vastustaja vastustaja;

    private List<Peliolio> piirrettavat;
    private TormayksienHavaitsija tormayksienHavaitsija;
    
    private int laatikoita;

    public Pongbreaker(int leveys, int korkeus) {
        super(100, null);
        addActionListener(this);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.paatyrajanLeveys = 30;

        this.onkoPaalla = false;

        this.pallo = new Pallo(6, this.leveys / 2 - 10, this.korkeus / 2 - 30);
        this.pelaaja = new Pelaaja(new Maila(this.paatyrajanLeveys, this.korkeus / 2 - 30));
        this.vastustaja = new Vastustaja(new Maila(this.leveys - 10 - this.paatyrajanLeveys, this.korkeus / 2 - 30), pallo);
        pallo.setYNopeus(2);
        pallo.setXNopeus(4);
        this.vastustaja.setKiihtyvyys(1);

        this.piirrettavat = new ArrayList<>();
        this.piirrettavat.add(pallo);
        this.piirrettavat.add(this.pelaaja.getMaila());
        this.piirrettavat.add(this.vastustaja.getMaila());
        this.tormayksienHavaitsija = new TormayksienHavaitsija(this.piirrettavat);
        this.laatikoita = 0;
    }

    public void tarkistaOsuukoPalloReunoihin() {
        /*Luvut -10 ja -30 tulevat siitä, että näytettävä ikkunan koko ei ole sama kuin alkuperäisesti
        asetettu leveys ja korkeus*/
        if (pallo.getX() <= pallo.getR()) {
            pallo.setX(pallo.getR());
            pallo.kaannaXNopeus();

        } else if (pallo.getX() >= leveys - pallo.getR() - 10) {
            pallo.setX(leveys - pallo.getR() - 10);
            pallo.kaannaXNopeus();

        }

        if (pallo.getY() <= pallo.getR()) {
            pallo.setY(pallo.getR());
            pallo.kaannaYNopeus();

        } else if (pallo.getY() >= this.korkeus - pallo.getR() - 30) {
            pallo.setY(korkeus - pallo.getR() - 30);
            pallo.kaannaYNopeus();

        }
    }

    public boolean tarkistaOhittaakoPalloPaatyrajan() {
        if (pallo.getX() <= paatyrajanLeveys) {
            //System.out.println("Pelaaja 1 hävisi");
            onkoPaalla = false;
            return true;

        } else if (pallo.getX() >= this.leveys - paatyrajanLeveys - 10) {
            //System.out.println("Pelaaja 2 hävisi");
            onkoPaalla = false;
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

    public void arvoLaatikot() {
        Random r = new Random();
        int i = 0;

        while (i < 15) {
            int x = 150 + r.nextInt(151);
            int y = 20 + r.nextInt(280);
            Laatikko laatikko = new Laatikko(x, y);
            if (!tormayksienHavaitsija.osuuko(laatikko)) {
                this.piirrettavat.add(new Laatikko(x, y));
                laatikoita++;
                i++;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (onkoPaalla) {
            tarkistaOsuukoPalloReunoihin();

            if (tarkistaOhittaakoPalloPaatyrajan()) {
                return;
            }

            pelaaja.liiku();
            vastustaja.liiku();
            tormayksienHavaitsija.tarkistaTormaykset();
            laatikoita -= tormayksienHavaitsija.poistaLaatikot();
            if (laatikoita == 0) {
                arvoLaatikot();
            }
            pallo.liiku();

            tarkistaMeneekoMailaYliRajojen(pelaaja.getMaila());
            tarkistaMeneekoMailaYliRajojen(vastustaja.getMaila());
        }

        paivitettava.paivita();
        setDelay(22);
    }

    public void kaynnistaPeli() {
        tormayksienHavaitsija.poistaKaikkiLaatikot();
        arvoLaatikot();
        pallo.setX(leveys / 2 - 10);
        pallo.setY(korkeus / 2 - 30);
        pallo.setKiihtyvyys(1);
        this.onkoPaalla = true;
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

    public boolean onkoPaalla() {
        return this.onkoPaalla;
    }

    public List<Peliolio> getPiirrettavat() {
        return this.piirrettavat;
    }

}

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
import pongbreaker.domain.PowerUp;
import pongbreaker.gui.Paivitettava;

/**
 * Luokka sisältää pelilogiikkaa, joiden mukaan peli toimii oikein.
 *
 * @author wolli
 */
public class Pongbreaker extends Timer implements ActionListener {

    private int leveys;
    private int korkeus;
    private int paatyrajanLeveys;
    private boolean onkoPaalla;
    private List<Paivitettava> paivitettavat;
    private Pallo pallo;
    private Pelaaja pelaaja;
    private Vastustaja vastustaja;
    private List<Peliolio> piirrettavat;
    private TormayksienHavaitsija tormayksienHavaitsija;
    private RajojenTarkkailija rajojenTarkkailija;
    private int laatikoita;
    private String kumpiOsuiViimeksi;
    private double laatikkoTodnak;

    /**
     * Luokan konstruktori.
     *
     * @param leveys Pelikentän leveys.
     * @param korkeus Pelikentän korkeus.
     */
    public Pongbreaker(int leveys, int korkeus) {
        super(100, null);
        addActionListener(this);
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.paatyrajanLeveys = 30;
        alustaPiirrettavat();
        this.tormayksienHavaitsija = new TormayksienHavaitsija(this.piirrettavat);
        this.rajojenTarkkailija = new RajojenTarkkailija(leveys, korkeus, 30);
        this.kumpiOsuiViimeksi = "Vastustaja";
        this.laatikkoTodnak = 0.01;
        this.paivitettavat = new ArrayList<>();
        this.onkoPaalla = true;
    }

    /**
     * Alustaa pelikentän olioita.
     */
    private void alustaPiirrettavat() {
        this.pallo = new Pallo(6, this.leveys / 2 - 10, this.korkeus / 2 - 30);
        this.pelaaja = new Pelaaja(new Maila(this.paatyrajanLeveys, this.korkeus / 2 - 30));
        this.vastustaja = new Vastustaja(new Maila(this.leveys - 10 - this.paatyrajanLeveys, this.korkeus / 2 - 30), pallo);
        this.piirrettavat = new ArrayList<>();
        this.piirrettavat.add(pallo);
        this.piirrettavat.add(this.pelaaja.getMaila());
        this.piirrettavat.add(this.vastustaja.getMaila());
    }

    /**
     * Metodi arpoo tietyllä välillä oleviin satunnaisiin koordinaatteihin
     * piirrettäviä laatikko-olioita.
     */
    public void arvoLaatikot() {
        Random r = new Random();
        if (r.nextDouble() < laatikkoTodnak) { //0.01
            while (true) {
                int x = 150 + r.nextInt(151);
                int y = 20 + r.nextInt(281);
                Laatikko laatikko = new Laatikko(x, y);
                if (!tormayksienHavaitsija.osuuko(laatikko) && x % 25 == 0 && y % 25 == 0) {
                    this.piirrettavat.add(new Laatikko(x, y));
                    laatikoita++;
                    break;
                }
            }
        }
    }

    /**
     * Käynnistää pelin, eli resetoi olioiden tilat ja paikat.
     */
    public void kaynnistaPeli() {
        tormayksienHavaitsija.poistaKaikkiLaatikot();
        laatikoita = 0;
        pallo.setX(leveys / 2 - 10);
        pallo.setY(korkeus / 2 - 30);
        pallo.resetoi();
        pelaaja.resetoiPowerupit();
        pelaaja.setKiihtyvyys(0);
        vastustaja.resetoiPowerupit();
        pallo.setTuhoutumaton(false);
        laatikkoTodnak = 0.01;
        onkoPaalla = true;
        pelaaja.setPisteet(0);
    }

    /**
     * Tarkistaa onko laatikoissa, joihin pallo on osunut, power uppeja.
     *
     * @param laatikot Tarkistettavat laatikot.
     */
    public void tarkistaPowerupit(List<Peliolio> laatikot) {
        for (Peliolio p : laatikot) {
            Laatikko laatikko = (Laatikko) p;
            if (laatikko.getSisaltaakoPowerupin()) {
                PowerUp pu = laatikko.getPowerUp();
                if (pu == PowerUp.TUHOUTUMATON_PALLO) {
                    pallo.setTuhoutumaton(true);
                    laatikkoTodnak = 0.4;
                } else {
                    if (pelaaja.getMaila().getOnkoOsunutViimeksi()) {
                        pelaaja.reagoiPowerUpiin(laatikko.getPowerUp());
                        pelaaja.setPisteet(pelaaja.getPisteet() + 10);
                    } else {
                        vastustaja.reagoiPowerUpiin(laatikko.getPowerUp());
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (onkoPaalla) {
            rajojenTarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
            if (rajojenTarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo)) {
                onkoPaalla = false;
                return;
            }
            pelaaja.liiku();
            vastustaja.liiku();
            tormayksienHavaitsija.tarkistaTormaykset();
            if (pelaaja.getMaila().getOnkoOsunutViimeksi() && vastustaja.getMaila().getOnkoOsunutViimeksi()) {
                if (kumpiOsuiViimeksi.equals("Pelaaja")) {
                    pelaaja.getMaila().setOnkoOsunutViimeksi(false);
                    kumpiOsuiViimeksi = "Vastustaja";
                } else {
                    vastustaja.getMaila().setOnkoOsunutViimeksi(false);
                    kumpiOsuiViimeksi = "Pelaaja";
                }
            }
            List<Peliolio> poistetutLaatikot = tormayksienHavaitsija.poistaLaatikotJoihinOsuttu();
            tarkistaPowerupit(poistetutLaatikot);
            laatikoita -= poistetutLaatikot.size();
            if (laatikoita < 73) { //80
                arvoLaatikot();
            }
            pallo.liiku();
            rajojenTarkkailija.tarkistaMeneekoMailaYliRajojen(pelaaja.getMaila());
            rajojenTarkkailija.tarkistaMeneekoMailaYliRajojen(vastustaja.getMaila());
        }
        for (Paivitettava paivitettava : paivitettavat) {
            paivitettava.paivita();
        }
        setDelay(25);
    }

    public void lisaaPaivitettava(Paivitettava p) {
        this.paivitettavat.add(p);
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

    public boolean getOnkoPaalla() {
        return this.onkoPaalla;
    }

    public List<Peliolio> getPiirrettavat() {
        return this.piirrettavat;
    }
}
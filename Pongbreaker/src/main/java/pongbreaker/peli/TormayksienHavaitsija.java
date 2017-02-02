package pongbreaker.peli;

import java.util.List;
import java.util.Random;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

public class TormayksienHavaitsija {

    private Pallo pallo;
    private Pelaaja pelaaja;
    private Vastustaja vastustaja;

    public TormayksienHavaitsija(Pallo pallo, Pelaaja pelaaja, Vastustaja vastustaja) {
        this.pallo = pallo;
        this.pelaaja = pelaaja;
        this.vastustaja = vastustaja;
    }

    public boolean tarkistaTormaykset() { //booleanin palauttaminen helpottaa testejä
        if (pelaaja.getMaila().getHitbox().intersects(pallo.getHitbox())) {
            pallo.kaannaXNopeus();
            pallo.setX(pelaaja.getMaila().getX() + pelaaja.getMaila().getLeveys() / 2 + pallo.getR());
            pallo.paivitaHitbox();
            arvoUudetNopeudetPallolle();
            return true;
        }

        if (vastustaja.getMaila().getHitbox().intersects(pallo.getHitbox())) {
            pallo.kaannaXNopeus();
            pallo.setX(vastustaja.getMaila().getX() - vastustaja.getMaila().getLeveys() / 2 - pallo.getR());
            pallo.paivitaHitbox();
            arvoUudetNopeudetPallolle();
            return true;
        }

        return false;
    }

    private void arvoUudetNopeudetPallolle() {
        Random r = new Random();
        int xNopeus = 2 + r.nextInt(2) + 1;
        int yNopeus = 2 + r.nextInt(1) + 1;

        if (pallo.getXNopeus() > 0) {
            pallo.setXNopeus(xNopeus);
        } else {
            pallo.setXNopeus(-1 * xNopeus);
        }

        if (pallo.getYNopeus() > 0) {
            pallo.setYNopeus(yNopeus);
        } else {
            pallo.setYNopeus(-1 * yNopeus);
        }
        //Tällä hetkellä pallo kiihtyy joka osuman jälkeen, ei kuitenkaan liikaa ettei pallo mene mailan läpi
        if (pallo.getKiihtyvyys() < 2.45) {
            pallo.setKiihtyvyys(pallo.getKiihtyvyys() + 0.05);
        }
    }
}

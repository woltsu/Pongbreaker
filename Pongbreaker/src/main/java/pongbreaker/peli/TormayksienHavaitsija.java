package pongbreaker.peli;

import java.util.List;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

public class TormayksienHavaitsija {

    private Pallo pallo;
    private Pelaaja pelaaja;

    public TormayksienHavaitsija(Pallo pallo, Pelaaja pelaaja) {
        this.pallo = pallo;
        this.pelaaja = pelaaja;
    }

    public void tarkistaTormaykset() {

        if (pelaaja.getMaila().getHitbox().intersects(pallo.getHitbox())) {

            if (pallo.getY() >= this.pelaaja.getMaila().getY() + this.pelaaja.getMaila().getKorkeus() / 2) {
                pallo.kaannaYNopeus();
                pallo.liiku();
            } else if (pallo.getY() <= this.pelaaja.getMaila().getY() - this.pelaaja.getMaila().getKorkeus() / 2) {
                pallo.kaannaYNopeus();
                pallo.liiku();
            } else {
                pallo.kaannaXNopeus();
            }

        }

    }
}

package pongbreaker.peli;

import java.util.List;
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

    public void tarkistaTormaykset() {

        if (pelaaja.getMaila().getHitbox().intersects(pallo.getHitbox())) {
            pallo.kaannaXNopeus();
            pallo.setX(pelaaja.getMaila().getX() + pelaaja.getMaila().getLeveys() / 2 + pallo.getR());

        }

        if (vastustaja.getMaila().getHitbox().intersects(pallo.getHitbox())) {
            pallo.kaannaXNopeus();
            //System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
            pallo.setX(vastustaja.getMaila().getX() - vastustaja.getMaila().getLeveys() / 2 - pallo.getR());

        }

    }
}

package pongbreaker.peli;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

public class TormayksienHavaitsijaTest {

     Pallo pallo;
    TormayksienHavaitsija havaitsija;

    Maila pelaajanMaila;
    Maila vastustajanMaila;

    @Before
    public void setUp() {
        pallo = new Pallo(5, 0, 100);
        pallo.setKiihtyvyys(1);
        pallo.setXNopeus(1);
        pallo.setYNopeus(1);

        pelaajanMaila = new Maila(100, 100);
        vastustajanMaila = new Maila(300, 100);

        List<Peliolio> pelioliot = new ArrayList<>();
        pelioliot.add(pallo);
        pelioliot.add(pelaajanMaila);
        pelioliot.add(vastustajanMaila);
        havaitsija = new TormayksienHavaitsija(pelioliot);
    }

    @Test
    public void havaitseeKunPalloOsuuPelaajanMailaan() {
        pallo.setX(pelaajanMaila.getX() - pelaajanMaila.getLeveys() / 2 - pallo.getR());
        pallo.paivitaHitbox();
        assertFalse(havaitsija.tarkistaTormaykset());
        pallo.setX(pallo.getX() + 1);
        pallo.paivitaHitbox();
        assertTrue(havaitsija.tarkistaTormaykset());
        pallo.setX(pelaajanMaila.getX() + pelaajanMaila.getLeveys() / 2 + pallo.getR());
        pallo.paivitaHitbox();
        assertFalse(havaitsija.tarkistaTormaykset());
        pallo.setX(pallo.getX() - 1);
        pallo.paivitaHitbox();
        assertTrue(havaitsija.tarkistaTormaykset());
    }

    @Test
    public void havaitseeKunPalloOsuuVastustajanMailaan() {
        pallo.setX(vastustajanMaila.getX() - vastustajanMaila.getLeveys() / 2 - pallo.getR());
        pallo.paivitaHitbox();
        assertFalse(havaitsija.tarkistaTormaykset());
        pallo.setX(pallo.getX() + 1);
        pallo.paivitaHitbox();
        assertTrue(havaitsija.tarkistaTormaykset());
        pallo.setX(vastustajanMaila.getX() + vastustajanMaila.getLeveys() / 2 + pallo.getR());
        pallo.paivitaHitbox();
        assertFalse(havaitsija.tarkistaTormaykset());
        pallo.setX(pallo.getX() - 1);
        pallo.paivitaHitbox();
        assertTrue(havaitsija.tarkistaTormaykset());
    }

}

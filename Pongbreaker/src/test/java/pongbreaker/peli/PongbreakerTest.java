package pongbreaker.peli;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Laatikko;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;
import pongbreaker.domain.PowerUp;

public class PongbreakerTest {

    Pongbreaker peli;
    Pallo pallo;

    @Before
    public void setUp() {
        peli = new Pongbreaker(500, 300);
        pallo = peli.getPallo();
    }

    @Test
    public void alustaaLeveydenJaKorkeudenOikein() {
        assertEquals(500, peli.getLeveys());
        assertEquals(300, peli.getKorkeus());
    }

    @Test
    public void alustaaPallonOikein() {
        assertEquals(6, peli.getPallo().getR());
        assertEquals(peli.getLeveys() / 2 - 10, peli.getPallo().getX());
        assertEquals(peli.getKorkeus() / 2 - 30, peli.getPallo().getY());
    }

    @Test
    public void pelinKaynnistaminenToimii() {
        Pelaaja pelaaja = peli.getPelaaja();
        int vanhaKorkeus = pelaaja.getMaila().getKorkeus();
        double vanhaNopeus = pelaaja.getNopeus();
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);

        Pallo pallo = peli.getPallo();
        assertTrue(peli.getOnkoPaalla());

        pallo.setX(0);
        pallo.setY(0);
        pallo.setKiihtyvyys(10);
        pallo.setTuhoutumaton(true);

        peli.kaynnistaPeli();

        assertEquals(240, pallo.getX());
        assertEquals(120, pallo.getY());
        assertEquals(1, pallo.getKiihtyvyys(), 0);
        assertTrue(peli.getOnkoPaalla());
        assertEquals(3, pallo.getXNopeus());
        assertEquals(6, pallo.getR());
        assertEquals(vanhaKorkeus, pelaaja.getMaila().getKorkeus());
        assertEquals(vanhaNopeus, pelaaja.getNopeus(), 0);
        assertFalse(pallo.getTuhoutumaton());
    }

    @Test
    public void tarkistaPowerupitToimiiPallolle() {
        Laatikko laatikko = new Laatikko(0, 0);
        laatikko.setPowerUp(PowerUp.TUHOUTUMATON_PALLO);
        laatikko.setSisaltaakoPowerupin(true);

        List<Peliolio> laatikot = new ArrayList<>();
        laatikot.add(laatikko);

        peli.tarkistaPowerupit(laatikot);
        Pallo pallo = peli.getPallo();
        assertTrue(pallo.getTuhoutumaton());

        pallo.setTuhoutumaton(false);
        laatikko.setPowerUp(PowerUp.MAILA_KASVAA);
        Pelaaja pelaaja = peli.getPelaaja();
        pelaaja.getMaila().setOnkoOsunutViimeksi(true);
        peli.tarkistaPowerupit(laatikot);

        assertEquals(65, pelaaja.getMaila().getKorkeus());
    }
}

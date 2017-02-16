package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
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
        assertFalse(peli.onkoPaalla());
        
        pallo.setX(0);
        pallo.setY(0);
        pallo.setKiihtyvyys(10);
        pallo.setTuhoutumaton(true);
        
        peli.kaynnistaPeli();
        
        assertEquals(240, pallo.getX());
        assertEquals(120, pallo.getY());
        assertEquals(0.7, pallo.getKiihtyvyys(), 0);
        assertTrue(peli.onkoPaalla());
        assertEquals(3, pallo.getXNopeus());
        assertEquals(6, pallo.getR());
        assertEquals(vanhaKorkeus, pelaaja.getMaila().getKorkeus());
        assertEquals(vanhaNopeus, pelaaja.getNopeus(), 0);
        assertFalse(pallo.getTuhoutumaton());
    }

}

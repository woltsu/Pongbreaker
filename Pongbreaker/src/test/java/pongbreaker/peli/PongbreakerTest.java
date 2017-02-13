package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

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
        Pallo pallo = peli.getPallo();
        assertFalse(peli.onkoPaalla());
        
        pallo.setX(0);
        pallo.setY(0);
        pallo.setKiihtyvyys(10);
        
        peli.kaynnistaPeli();
        
        assertEquals(240, pallo.getX());
        assertEquals(120, pallo.getY());
        assertEquals(0.7, pallo.getKiihtyvyys(), 0);
        assertTrue(peli.onkoPaalla());
    }

}

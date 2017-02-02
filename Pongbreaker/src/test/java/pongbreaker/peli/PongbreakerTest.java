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
    public void havaitseeJosPalloOsuuReunoihin() {
        pallo.setXNopeus(3);
        pallo.setYNopeus(3);

        pallo.setY(pallo.getR());
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(-3, pallo.getYNopeus());

        pallo.setY(300 - pallo.getR() - 30);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(3, pallo.getYNopeus());
        pallo.setY(peli.getKorkeus() / 2 - 30);

        pallo.setX(pallo.getR());
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(-3, pallo.getXNopeus());

        pallo.setX(500 - pallo.getR() - 10);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(3, pallo.getXNopeus());
    }
    
    @Test
    public void palloEiVoiYlittaaRajoja() {
        pallo.setX(-10);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(pallo.getR(), pallo.getX());
        
        pallo.setX(600);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(500 - pallo.getR() - 10, pallo.getX());
        
        pallo.setY(-10);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(pallo.getR(), pallo.getY());
        
        pallo.setY(600);
        peli.tarkistaOsuukoPalloReunoihin();
        assertEquals(peli.getKorkeus() - 30 - pallo.getR(), pallo.getY());
    }

    @Test
    public void havaitseeJosPalloMeneePaatyrajojenYli() {
        peli.kaynnistaPeli();
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());

        pallo.setX(peli.getPaatyrajanleveys() + 1);
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());
        assertTrue(peli.onkoPaalla());

        pallo.setX(peli.getPaatyrajanleveys());
        assertTrue(peli.tarkistaOhittaakoPalloPaatyrajan());
        assertFalse(peli.onkoPaalla());
        
        peli.kaynnistaPeli();
        
        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys() - 1);
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());
        assertTrue(peli.onkoPaalla());

        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys());
        assertTrue(peli.tarkistaOhittaakoPalloPaatyrajan());
        assertFalse(peli.onkoPaalla());
    }
    
    @Test
    public void mailaEiVoiMennaYliRajojen() {
        Maila maila = peli.getPelaaja().getMaila();
        assertFalse(peli.tarkistaMeneekoMailaYliRajojen(maila));
        
        maila.setY(0);
        assertTrue(peli.tarkistaMeneekoMailaYliRajojen(maila));
        assertEquals(maila.getKorkeus() / 2, maila.getY());
        
        maila.setY(600);
        assertTrue(peli.tarkistaMeneekoMailaYliRajojen(maila));
        assertEquals(300 - 30 - maila.getKorkeus() / 2, maila.getY());
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
        assertEquals(1, pallo.getKiihtyvyys(), 0);
        assertTrue(peli.onkoPaalla());
    }

}

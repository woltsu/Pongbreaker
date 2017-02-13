package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

public class RajojenTarkkailijaTest {

    Pongbreaker peli;
    Pallo pallo;

    RajojenTarkkailija tarkkailija;

    @Before
    public void setUp() {
        peli = new Pongbreaker(500, 300);
        pallo = peli.getPallo();
        tarkkailija = new RajojenTarkkailija(peli.getLeveys(), peli.getKorkeus(), peli.getPaatyrajanleveys());
    }

    @Test
    public void havaitseeJosPalloOsuuReunoihin() {
        pallo.setXNopeus(3);
        pallo.setYNopeus(3);

        pallo.setY(pallo.getR());
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(-3, pallo.getYNopeus());

        pallo.setY(300 - pallo.getR() - 30);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(3, pallo.getYNopeus());
        pallo.setY(peli.getKorkeus() / 2 - 30);

        pallo.setX(pallo.getR());
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(-3, pallo.getXNopeus());

        pallo.setX(500 - pallo.getR() - 10);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(3, pallo.getXNopeus());
    }

    @Test
    public void palloEiVoiYlittaaRajoja() {
        pallo.setX(-10);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(pallo.getR(), pallo.getX());

        pallo.setX(600);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(500 - pallo.getR() - 10, pallo.getX());

        pallo.setY(-10);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(pallo.getR(), pallo.getY());

        pallo.setY(600);
        tarkkailija.tarkistaOsuukoPalloReunoihin(pallo);
        assertEquals(peli.getKorkeus() - 30 - pallo.getR(), pallo.getY());
    }

    @Test
    public void havaitseeJosPalloMeneePaatyrajojenYli() {
        peli.kaynnistaPeli();
        assertFalse(tarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo));

        pallo.setX(peli.getPaatyrajanleveys() + 1);
        assertFalse(tarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo));

        pallo.setX(peli.getPaatyrajanleveys());
        assertTrue(tarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo));

        peli.kaynnistaPeli();

        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys() - 1);
        assertFalse(tarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo));

        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys());
        assertTrue(tarkkailija.tarkistaOhittaakoPalloPaatyrajan(pallo));
    }
    
    @Test
    public void mailaEiVoiMennaYliRajojen() {
        Maila maila = peli.getPelaaja().getMaila();
        assertFalse(tarkkailija.tarkistaMeneekoMailaYliRajojen(maila));
        
        maila.setY(0);
        assertTrue(tarkkailija.tarkistaMeneekoMailaYliRajojen(maila));
        assertEquals(maila.getKorkeus() / 2, maila.getY());
        
        maila.setY(600);
        assertTrue(tarkkailija.tarkistaMeneekoMailaYliRajojen(maila));
        assertEquals(300 - 30 - maila.getKorkeus() / 2, maila.getY());
    }

}

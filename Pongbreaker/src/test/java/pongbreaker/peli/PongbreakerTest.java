package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Pallo;

public class PongbreakerTest {

    Pongbreaker peli;

    @Before
    public void setUp() {
        this.peli = new Pongbreaker(500, 300);
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
        Pallo pallo = peli.getPallo();
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
    public void peliHavaitseeJosPalloMeneePaatyrajojenYli() {
        Pallo pallo = peli.getPallo();
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());

        pallo.setX(peli.getPaatyrajanleveys() + 1);
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());

        pallo.setX(peli.getPaatyrajanleveys());
        assertTrue(peli.tarkistaOhittaakoPalloPaatyrajan());

        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys() - 1);
        assertFalse(peli.tarkistaOhittaakoPalloPaatyrajan());

        pallo.setX(peli.getLeveys() - 10 - peli.getPaatyrajanleveys());
        assertTrue(peli.tarkistaOhittaakoPalloPaatyrajan());
    }

}

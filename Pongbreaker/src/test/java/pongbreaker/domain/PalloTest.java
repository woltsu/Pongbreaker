package pongbreaker.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalloTest {

    Pallo pallo;

    @Before
    public void setUp() {
        pallo = new Pallo(5, 5, 5);
        pallo.setXNopeus(3);
        pallo.setYNopeus(3);
        pallo.setKiihtyvyys(1);
    }

    @Test
    public void alustaaSijainninOikein() {
        assertEquals(5, pallo.x);
        assertEquals(5, pallo.y);
    }

    @Test
    public void alustaaPallonSateenOikein() {
        assertEquals(5, pallo.getR());
    }

    @Test
    public void alustaaPallonNopeudetJaKiihtyvyydenOikein() {
        assertEquals(3, pallo.getXNopeus());
        assertEquals(3, pallo.getYNopeus());
        assertEquals(1, pallo.getKiihtyvyys(), 0);
    }

    @Test
    public void alustaaHitboxinOikein() {
        assertEquals(0, pallo.getHitbox().x, 0);
        assertEquals(0, pallo.getHitbox().y, 0);
    }

    @Test
    public void nopeuksienKaantaminenToimii() {
        pallo.kaannaXNopeus();
        assertEquals(-3, pallo.getXNopeus());

        pallo.kaannaYNopeus();
        assertEquals(-3, pallo.getYNopeus());
    }

    @Test
    public void pallonJaHitboxinLiikkuminenOikealleJaVasemmalleToimii() {
        pallo.liiku();
        assertEquals(5 + pallo.getXNopeus(), pallo.getX());
        assertEquals(pallo.getXNopeus(), pallo.getHitbox().x);
        pallo.kaannaXNopeus();
        pallo.liiku();
        assertEquals(5, pallo.getX());
        assertEquals(0, pallo.getHitbox().x);
    }

    @Test
    public void pallonJaHitboxinLiikkuminenYlosJaAlasToimii() {
        pallo.liiku();
        assertEquals(5 + pallo.getYNopeus(), pallo.getY());
        assertEquals(pallo.getYNopeus(), pallo.getHitbox().y);
        pallo.kaannaYNopeus();
        pallo.liiku();
        assertEquals(5, pallo.getY());
        assertEquals(0, pallo.getHitbox().y);
    }

    @Test
    public void tuhoutumatonPalloEiKasvaLiikaa() {
        pallo.setTuhoutumaton(true);
        assertEquals(10, pallo.getR());
        pallo.setTuhoutumaton(false);
        assertEquals(10, pallo.getR());
        pallo.setTuhoutumaton(true);
        pallo.setTuhoutumaton(true);
        pallo.setTuhoutumaton(true);
        assertEquals(25, pallo.getR());
        pallo.setTuhoutumaton(true);
        assertEquals(25, pallo.getR());
    }

    @Test
    public void palloReagoiOikeinOsumaan() {
        pallo.setXNopeus(5);
        pallo.setYNopeus(5);
        int vanhaXNopeus = pallo.getXNopeus();
        int vanhaYNopeus = pallo.getYNopeus();
        int vanhaX = pallo.getX();
        int vanhaY = pallo.getY();
        double vanhaKiihtyvyys = pallo.getKiihtyvyys();
        pallo.reagoiOsumaan();

        assertTrue(pallo.getXNopeus() < 0);
        assertTrue(pallo.getYNopeus() > 0);
        assertTrue(pallo.getX() != vanhaX);
        assertTrue(pallo.getY() != vanhaY);

        assertEquals(vanhaKiihtyvyys + 0.02, pallo.getKiihtyvyys(), 0);

        for (int i = 0; i < 200; i++) {
            pallo.reagoiOsumaan();
        }

        assertEquals(4, pallo.getKiihtyvyys(), 0.1);
    }

}

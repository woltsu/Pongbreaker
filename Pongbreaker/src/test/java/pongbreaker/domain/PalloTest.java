package pongbreaker.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalloTest {
    
    Pallo pallo;
    
    @Before
    public void setUp() {
        this.pallo = new Pallo(5, 5, 5);
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
        assertEquals(1, (int) pallo.getKiihtyvyys());
    }
    
    @Test
    public void alustaaHitboxinOikein() {
        assertEquals(0, (int) pallo.getHitbox().x);
        assertEquals(0, (int) pallo.getHitbox().y);
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
    
}

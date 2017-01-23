package pongbreaker.peli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertEquals(peli.getKorkeus()/ 2 - 30, peli.getPallo().getY());
    }

}

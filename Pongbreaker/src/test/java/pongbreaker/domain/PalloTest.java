package pongbreaker.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalloTest {
    
    Pallo pallo;
    
    @Before
    public void setUp() {
        this.pallo = new Pallo(5, 5, 5);
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
    public void alustaaPallonNopeudetOikein() {
        assertEquals(3, pallo.getXNopeus());
        assertEquals(3, pallo.getYNopeus());
    }
    
    @Test
    public void nopeuksienKaantaminenToimii() {
        pallo.kaannaXNopeus();
        assertEquals(-3, pallo.getXNopeus());
        
        pallo.kaannaYNopeus();
        assertEquals(-3, pallo.getYNopeus());
    }
    
}

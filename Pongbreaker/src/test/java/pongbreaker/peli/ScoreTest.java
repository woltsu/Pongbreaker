package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    Score score;
    
    @Before
    public void setUp() {
        this.score = new Score("pelaaja", 50);
    }
    
    @Test
    public void alustaaOikein() {
        assertEquals("pelaaja", score.getNimi());
        assertEquals(50, score.getPisteet());
    }
    
    @Test
    public void compareToToimii() {
        Score toinenScore = new Score("a", 100);
        Score kolmasScore = new Score("b", 0);
        assertTrue(score.compareTo(toinenScore) > 0);
        assertTrue(score.compareTo(kolmasScore) < 0);
        assertTrue(score.compareTo(score) == 0);
    }

}

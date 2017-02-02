package pongbreaker.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MailaTest {
    Maila maila;
    int x;
    int y;
    
    @Before
    public void setUp() {
        x = 100;
        y = 100;
        maila = new Maila(x, y);
    }
    
    @Test
    public void asettaaHitboxinOikein() {
        assertEquals(x - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(y - maila.getKorkeus() / 2, maila.getHitbox().y);
    }
}

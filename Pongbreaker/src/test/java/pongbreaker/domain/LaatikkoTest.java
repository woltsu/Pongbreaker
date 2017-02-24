package pongbreaker.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaatikkoTest {

    Laatikko laatikko;

    @Before
    public void setUp() {
        laatikko = new Laatikko(0, 0);
    }

    @Test
    public void poistetaankoOnAlussaFalse() {
        assertEquals(false, laatikko.getPoistetaanko());
    }

    @Test
    public void laatikonHitboxOikeassaPaikassa() {
        assertEquals(laatikko.x - laatikko.getSivunPituus() / 2, laatikko.getHitbox().x);
        assertEquals(laatikko.y - laatikko.getSivunPituus() / 2, laatikko.getHitbox().y);
    }

    @Test
    public void laatikkoReagoiOsumaanOikein() {
        assertFalse(laatikko.getPoistetaanko());
        if (laatikko.getHitpoints() == 2) {
            assertEquals(2, laatikko.getHitpoints());
            laatikko.reagoiOsumaan();
            assertFalse(laatikko.getPoistetaanko());
            assertEquals(1, laatikko.getHitpoints());
            laatikko.reagoiOsumaan();
            assertEquals(0, laatikko.getHitpoints());
            assertTrue(laatikko.getPoistetaanko());

        } else {
            assertEquals(1, laatikko.getHitpoints());
            laatikko.reagoiOsumaan();
            assertEquals(0, laatikko.getHitpoints());
            assertTrue(laatikko.getPoistetaanko());
        }
    }

    @Test
    public void equals() {
        assertTrue(laatikko.equals(laatikko));
        Laatikko toinenLaatikko = new Laatikko(10, 10);
        assertFalse(laatikko.equals(toinenLaatikko));
        assertFalse(laatikko.equals(null));
        assertFalse(laatikko.equals(new Pallo(1, 1, 1)));
        Laatikko kolmasLaatikko = new Laatikko(0, 0);
        assertTrue(laatikko.equals(kolmasLaatikko));
    }

}

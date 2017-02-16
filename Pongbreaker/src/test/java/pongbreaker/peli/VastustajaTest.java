package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.PowerUp;

public class VastustajaTest {

    Maila maila;
    Pallo pallo;
    Vastustaja vastustaja;

    @Before
    public void setUp() {
        maila = new Maila(100, 100);
        pallo = new Pallo(5, 50, 100);
        vastustaja = new Vastustaja(maila, pallo);
    }

    @Test
    public void vastustajaEiLiikuJosPallonYOnSamaKuinMailanY() {
        vastustaja.liiku();
        assertEquals(100, maila.getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(100 - maila.getKorkeus() / 2, maila.getHitbox().y);
        assertFalse(vastustaja.liiku());
    }

    @Test
    public void vastustajaJaHitboxLiikkuuOikeanMaaranYlospainKunPallonJaMailanYErotusOnAlleViisiYksikkoa() {
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        pallo.setY(101);
        vastustaja.liiku();
        assertEquals(101, maila.getY());
        assertEquals(101 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(103);
        vastustaja.liiku();
        assertEquals(103, maila.getY());
        assertEquals(103 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(106);
        vastustaja.liiku();
        assertEquals(106, maila.getY());
        assertEquals(106 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(110);
        vastustaja.liiku();
        assertEquals(110, maila.getY());
        assertEquals(110 - maila.getKorkeus() / 2, maila.getHitbox().y);
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
    }

    @Test
    public void vastustajaJaHitboxLiikkuuOikeanMaaranYlospainKunPallonJaMailanYErotusOnYliNeljaYksikkoa() {
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        pallo.setY(110);
        vastustaja.liiku();
        assertEquals(105, maila.getY());
        assertEquals(105 - maila.getKorkeus() / 2, maila.getHitbox().y);
        vastustaja.liiku();
        assertEquals(110, maila.getY());
        assertEquals(110 - maila.getKorkeus() / 2, maila.getHitbox().y);
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
    }

    @Test
    public void vastustajaJaHitboxLiikkuuOikeanMaaranAlaspainKunPallonJaMailanYErotusOnAlleViisiYksikkoa() {
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        pallo.setY(99);
        vastustaja.liiku();
        assertEquals(99, maila.getY());
        assertEquals(99 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(97);
        vastustaja.liiku();
        assertEquals(97, maila.getY());
        assertEquals(97 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(94);
        vastustaja.liiku();
        assertEquals(94, maila.getY());
        assertEquals(94 - maila.getKorkeus() / 2, maila.getHitbox().y);
        pallo.setY(90);
        vastustaja.liiku();
        assertEquals(90, maila.getY());
        assertEquals(90 - maila.getKorkeus() / 2, maila.getHitbox().y);
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
    }

    @Test
    public void vastustajaJaHitboxLiikkuuOikeanMaaranAlaspainKunPallonJaMailanYErotusOnYliNeljaYksikkoa() {
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        pallo.setY(90);
        vastustaja.liiku();
        assertEquals(95, maila.getY());
        assertEquals(95 - maila.getKorkeus() / 2, maila.getHitbox().y);
        vastustaja.liiku();
        assertEquals(90, maila.getY());
        assertEquals(90 - maila.getKorkeus() / 2, maila.getHitbox().y);
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
    }
    
    @Test
    public void reagoiEriPoweruppeihinOikein() {
        int mailanVanhaKorkeus = maila.getKorkeus();
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        assertEquals(mailanVanhaKorkeus + 20, maila.getKorkeus());
        
        double vanhaNopeus = vastustaja.getNopeus();
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        assertEquals(vanhaNopeus + 0.5, vastustaja.getNopeus(), 0);
        
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        vastustaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        vastustaja.reagoiPowerUpiin(PowerUp.RESETOI_POWERUPIT);
        
        assertEquals(mailanVanhaKorkeus, maila.getKorkeus());
        assertEquals(vanhaNopeus, vastustaja.getNopeus(), 0);
    }

}

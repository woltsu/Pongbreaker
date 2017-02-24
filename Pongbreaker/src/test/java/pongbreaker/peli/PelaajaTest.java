/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.PowerUp;

public class PelaajaTest {

    Pelaaja pelaaja;
    Maila maila;

    @Before
    public void setUp() {
        pelaaja = new Pelaaja(new Maila(100, 100));
        maila = pelaaja.getMaila();
    }

    @Test
    public void eiVoiLiikkuaJosKiihtyvyysOnNolla() {
        pelaaja.liiku();
        assertEquals(100, maila.getX());
        assertEquals(100, maila.getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(100 - maila.getKorkeus() / 2, maila.getHitbox().y);
    }

    @Test
    public void liikkuminenYlosToimiiJaLiikuttaaHitboxia() {
        pelaaja.setNopeus(5);
        pelaaja.setKiihtyvyys(1);
        pelaaja.liiku();
        assertEquals(105, pelaaja.getMaila().getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(105 - maila.getKorkeus() / 2, maila.getHitbox().y);
    }

    @Test
    public void liikkuminenAlasToimiiJaLiikuttaaHitboxia() {
        pelaaja.setNopeus(5);
        pelaaja.setKiihtyvyys(-1);
        pelaaja.liiku();
        assertEquals(95, pelaaja.getMaila().getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(95 - maila.getKorkeus() / 2, maila.getHitbox().y);
    }

    @Test
    public void kiihtyvyydenSaateleminenToimiiOikein() {
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        pelaaja.lisaaKiihtyvyytta();
        assertEquals(1, pelaaja.getKiihtyvyys(), 0);
        pelaaja.lisaaKiihtyvyytta();
        assertEquals(1, pelaaja.getKiihtyvyys(), 0);
        pelaaja.vahennaKiihtyvyytta();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        pelaaja.vahennaKiihtyvyytta();
        assertEquals(-1, pelaaja.getKiihtyvyys(), 0);
        pelaaja.vahennaKiihtyvyytta();
        assertEquals(-1, pelaaja.getKiihtyvyys(), 0);
    }

    @Test
    public void liikkuminenVahentaaKiihtyvyyttaOikein() {
        pelaaja.lisaaKiihtyvyytta();
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        pelaaja.vahennaKiihtyvyytta();
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
    }

    @Test
    public void reagoiEriPoweruppeihinOikein() {
        int mailanVanhaKorkeus = maila.getKorkeus();
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        assertEquals(mailanVanhaKorkeus + 20, maila.getKorkeus());

        double vanhaNopeus = pelaaja.getNopeus();
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        assertEquals(vanhaNopeus + 1, pelaaja.getNopeus(), 0);

        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_KASVAA);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        pelaaja.reagoiPowerUpiin(PowerUp.MAILA_NOPEUTUU);
        pelaaja.reagoiPowerUpiin(PowerUp.RESETOI_POWERUPIT);

        assertEquals(mailanVanhaKorkeus, maila.getKorkeus());
        assertEquals(vanhaNopeus, pelaaja.getNopeus(), 0);
    }

    @Test
    public void liikkumisenRajatapauksia() {
        pelaaja.setKiihtyvyys(0.5);
        pelaaja.setLiikkuuko(false);
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
        
        pelaaja.setKiihtyvyys(-0.5);
        pelaaja.setLiikkuuko(false);
        pelaaja.liiku();
        assertEquals(0, pelaaja.getKiihtyvyys(), 0);
    }

}

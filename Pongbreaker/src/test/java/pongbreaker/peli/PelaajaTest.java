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

public class PelaajaTest {
    Pelaaja pelaaja;

    @Before
    public void setUp() {
        pelaaja = new Pelaaja(new Maila(100, 100));
    }
    
    @Test
    public void eiVoiLiikkuaJosKiihtyvyysOnNolla() {
        Maila maila = pelaaja.getMaila();
        pelaaja.liiku();
        assertEquals(100, maila.getX());
        assertEquals(100, maila.getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(100 - maila.getKorkeus() / 2, maila.getHitbox().y);
    }
    
    @Test
    public void liikkuminenYlosToimiiJaLiikuttaaHitboxia() {
        Maila maila = pelaaja.getMaila();
        pelaaja.setNopeus(5);
        pelaaja.setKiihtyvyys(1);
        pelaaja.liiku();
        assertEquals(105, pelaaja.getMaila().getY());
        assertEquals(100 - maila.getLeveys() / 2, maila.getHitbox().x);
        assertEquals(105 - maila.getKorkeus() / 2, maila.getHitbox().y);    
    }
    
    @Test
    public void liikkuminenAlasToimiiJaLiikuttaaHitboxia() {
        Maila maila = pelaaja.getMaila();
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
    
}

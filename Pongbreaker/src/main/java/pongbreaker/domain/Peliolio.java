package pongbreaker.domain;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Luokka kuvaa pelissä esiintyvää oliota, joka reagoi muihin peliolioihin sekä
 * piirretään peliin.
 *
 * @author wolli
 */
public abstract class Peliolio {

    protected int x;
    protected int y;

    /**
     * Luokan konstruktori. Asettaa x- ja y-arvot, jotka kuvaavat pistettä
     * koordinaatistossa.
     *
     * @param x pisteen x-arvo.
     * @param y pisteen y-arvo.
     */
    public Peliolio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodi piirtää halutun grafiikan ruudulle.
     *
     * @param g grafiikka, jota käytetään piirtämiseen.
     */
    public abstract void piirra(Graphics g);

    /**
     * Metodi sisältää tiedon siitä, kuinka perivän luokan tulee käyttäytyä, kun
     * toisen peliolion hitbox osuu luokan omaan hitboxiin.
     *
     * @see pongbreaker.peli.TormayksienHavaitsija#tarkistaTormaykset()
     */
    public abstract void reagoiOsumaan();

    /**
     * Palauttaa Rectangle-olion, joka kuvaa peliolion hitboxia.
     *
     * @return Rectangle-olio, joka olion luokan hitboxia.
     */
    public abstract Rectangle getHitbox();

}

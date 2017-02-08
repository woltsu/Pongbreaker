package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.Random;

/**
 * Luokka kuvaa pelikentälle ilmestyviä laatikoita, joista osa voi sisältää
 * power-uppeja.
 *
 * @author wolli
 */
public class Laatikko extends Peliolio {

    private int sivunPituus;

    private Rectangle hitbox;
    private boolean poistetaanko;

    private int hitpoints;

    private boolean sisaltaakoPowerupin;

    /**
     * Luokan konstruktori.
     *
     * @param x laatikon x-arvo koordinaatistossa
     * @param y laatikon y-arvo koordinaatistossa
     * @see pongbreaker.domain.Peliolio#Peliolio(int, int)
     */
    public Laatikko(int x, int y) {
        super(x, y);
        sivunPituus = 25;
        hitbox = new Rectangle(x - sivunPituus / 2, y - sivunPituus / 2, sivunPituus, sivunPituus);
        poistetaanko = false;
        hitpoints = 2;

        Random random = new Random();
        if (random.nextDouble() < 0.15) {
            sisaltaakoPowerupin = true;
            hitpoints = 1;
        }

    }

    public boolean getPoistetaanko() {
        return poistetaanko;
    }

    /**
     * @see Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laatikko other = (Laatikko) obj;
        if (this.sivunPituus != other.sivunPituus) {
            return false;
        }
        if (!Objects.equals(this.hitbox, other.hitbox)) {
            return false;
        }
        return true;
    }

    /**
     * @see pongbreaker.domain.Peliolio#piirra(java.awt.Graphics)
     */
    @Override
    public void piirra(Graphics g) {
        if (sisaltaakoPowerupin) {
            g.setColor(Color.red);
        } else {
            if (hitpoints == 2) {
                g.setColor(Color.darkGray);
            } else {
                g.setColor(Color.lightGray);
            }
        }
        g.fillRect(x - sivunPituus / 2, y - sivunPituus / 2, sivunPituus, sivunPituus);
    }

    /**
     * @see pongbreaker.domain.Peliolio#reagoiOsumaan()
     */
    @Override
    public void reagoiOsumaan() {
        hitpoints--;
        if (hitpoints == 0) {
            poistetaanko = true;
        }
    }

    /**
     * @see pongbreaker.domain.Peliolio#getHitbox()
     */
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getSivunPituus() {
        return sivunPituus;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

}

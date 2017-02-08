package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.Random;

public class Laatikko extends Peliolio {

    int sivunPituus;

    private Rectangle hitbox;
    private boolean poistetaanko;

    private int hitpoints;

    private boolean sisaltaakoPowerupin;

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

    public boolean poistetaanko() {
        return poistetaanko;
    }

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

    @Override
    public void reagoiOsumaan() {
        hitpoints--;
        if (hitpoints == 0) {
            poistetaanko = true;
        }
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

}
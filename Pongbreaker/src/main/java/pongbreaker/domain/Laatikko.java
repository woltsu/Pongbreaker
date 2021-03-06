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
    private PowerUp powerUp;

    /**
     * Luokan konstruktori. Arpoo samalla sisältääkö laatikko power-upin, ja jos
     * sisältää, niin minkä power-upin.
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
        if (random.nextDouble() < 0.6) {
            sisaltaakoPowerupin = true;
            hitpoints = 1;

            double luku = random.nextDouble();
            if (luku < 0.45) {
                this.powerUp = PowerUp.MAILA_KASVAA;
            } else if (luku < 0.85) {
                this.powerUp = PowerUp.MAILA_NOPEUTUU;
            } else if (luku < 0.96) {
                this.powerUp = PowerUp.RESETOI_POWERUPIT;
            } else {
                this.powerUp = PowerUp.TUHOUTUMATON_PALLO;
            }

        }

    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public void setSisaltaakoPowerupin(boolean sisaltaakoPowerupin) {
        this.sisaltaakoPowerupin = sisaltaakoPowerupin;
    }

    public boolean getPoistetaanko() {
        return poistetaanko;
    }

    public boolean getSisaltaakoPowerupin() {
        return sisaltaakoPowerupin;
    }

    public PowerUp getPowerUp() {
        return powerUp;
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

        if (!Objects.equals(this.hitbox, other.hitbox)) {
            return false;
        }
        return true;
    }

    @Override
    public void piirra(Graphics g) {
        if (sisaltaakoPowerupin) {

            if (powerUp == PowerUp.MAILA_KASVAA) {
                g.setColor(Color.MAGENTA);
            } else if (powerUp == PowerUp.MAILA_NOPEUTUU) {
                g.setColor(Color.yellow);
            } else if (powerUp == PowerUp.TUHOUTUMATON_PALLO) {
                g.setColor(Color.red);
            } else if (powerUp == PowerUp.RESETOI_POWERUPIT) {
                g.setColor(Color.white);
            }

        } else {
            if (hitpoints == 2) {
                g.setColor(Color.darkGray);
            } else {
                g.setColor(Color.gray);
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

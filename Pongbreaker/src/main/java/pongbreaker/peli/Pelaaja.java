package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.PowerUp;

/**
 * Luokka kuvaa pelaajaa, jonka mailalla on eri ominaisuuksia.
 *
 * @author wolli
 */
public class Pelaaja {

    private Maila maila;

    private double nopeus;
    private double kiihtyvyys;

    private boolean liikkuuko;
    private int pisteet;

    /**
     * Luokan konstruktori.
     *
     * @param maila Pelaajan maila.
     */
    public Pelaaja(Maila maila) {
        this.maila = maila;
        this.nopeus = 5;
        this.kiihtyvyys = 0;
        this.liikkuuko = false;
        this.pisteet = 0;
        //this.maila.setKorkeus(200);
    }

    /**
     * Muuttaa luokan mailan ja sen hitboxin koordinaatteja. Pelaajan nopeus ja
     * kiihtyvyys vaikuttavat liikutun matkan määrään koordinaatistossa.
     */
    public void liiku() {
        maila.setY(maila.getY() + (int) (nopeus * kiihtyvyys));
        maila.getHitbox().setLocation(maila.getX() - maila.getLeveys() / 2, maila.getY() - maila.getKorkeus() / 2);
        if (!liikkuuko) {
            if (this.kiihtyvyys > 0) {
                if (this.kiihtyvyys - 1 < 0) {
                    this.kiihtyvyys = 0;
                } else {
                    this.kiihtyvyys -= 1;
                }

            } else if (this.kiihtyvyys < 0) {
                if (this.kiihtyvyys + 1 > 0) {
                    this.kiihtyvyys = 0;
                } else {
                    this.kiihtyvyys += 1;
                }

            }
        }
    }

    /**
     * Lisää pelaajan kiihtyvyyttä.
     */
    public void lisaaKiihtyvyytta() {
        if (this.kiihtyvyys < 1) {
            this.kiihtyvyys += 1;
        }
    }

    /**
     * Vähentää pelaajan kiihtyvyyttä.
     */
    public void vahennaKiihtyvyytta() {
        if (this.kiihtyvyys > -1) {
            this.kiihtyvyys -= 1;
        }
    }

    /**
     * Metodi määrittää kuinka pelaaja reagoi kuhunkin power uppiin.
     *
     * @param p powerup.
     */
    public void reagoiPowerUpiin(PowerUp p) {
        if (p == PowerUp.MAILA_KASVAA) {
            maila.setKorkeus(maila.getKorkeus() + 20);
        } else if (p == PowerUp.MAILA_NOPEUTUU) {
            this.nopeus += 1;
        } else if (p == PowerUp.RESETOI_POWERUPIT) {
            resetoiPowerupit();
        }
    }

    /**
     * Metodi asettaa pelaajan attribuutit alkuperäiseen tilaan.
     */
    public void resetoiPowerupit() {
        this.nopeus = 5;
        this.maila.setKorkeus(45);
    }

    public void setLiikkuuko(boolean liikkuuko) {
        this.liikkuuko = liikkuuko;
    }

    public double getNopeus() {
        return this.nopeus;
    }

    public double getKiihtyvyys() {
        return this.kiihtyvyys;
    }

    public void setKiihtyvyys(double kiihtyvyys) {
        this.kiihtyvyys = kiihtyvyys;
    }

    public void setNopeus(double nopeus) {
        this.nopeus = nopeus;
    }

    public Maila getMaila() {
        return this.maila;
    }

    public void setPisteet(int pisteet) {
        this.pisteet = pisteet;
    }

    public int getPisteet() {
        return this.pisteet;
    }

}

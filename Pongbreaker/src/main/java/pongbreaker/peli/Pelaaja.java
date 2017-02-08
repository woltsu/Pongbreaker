package pongbreaker.peli;

import pongbreaker.domain.Maila;

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

}

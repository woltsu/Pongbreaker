package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.PowerUp;

/**
 * Luokka kuvaa pelissä olevaa vastustajaa, jonka mailalla on eri ominaisuuksia.
 * Vastustaja liikkuu yksinkertaisella tekoälyllä.
 *
 * @author wolli
 */
public class Vastustaja {

    private Maila maila;
    private Pallo seurattavaPallo;

    private double kiihtyvyys;
    private double nopeus;

    /**
     * Luokan konstruktori.
     *
     * @param maila Vastustajan maila.
     * @param seurattavaPallo Pallo, jota vastustaja seuraa.
     */
    public Vastustaja(Maila maila, Pallo seurattavaPallo) {
        this.maila = maila;
        this.seurattavaPallo = seurattavaPallo;
        this.kiihtyvyys = 1;
        this.nopeus = 5;
    }

    /**
     * Liikuttaa Vastustajan mailaa. Liikuttu matka riippuu seurattavan pallon
     * sijainnista sekä pelaajan kiihtyvyydestä.
     */
    public boolean liiku() {
        if (maila.getY() < seurattavaPallo.getY()) {

            if (Math.abs(seurattavaPallo.getY() - maila.getY()) > nopeus) {
                maila.setY(maila.getY() + (int) (kiihtyvyys * nopeus));

            } else {
                maila.setY(maila.getY() + (int) (kiihtyvyys * (Math.abs(seurattavaPallo.getY() - maila.getY()))));

            }

        } else if (maila.getY() > seurattavaPallo.getY()) {
            if (Math.abs(seurattavaPallo.getY() - maila.getY()) > nopeus) {
                maila.setY(maila.getY() - (int) (kiihtyvyys * nopeus));

            } else {
                maila.setY(maila.getY() - (int) (kiihtyvyys * (Math.abs(seurattavaPallo.getY() - maila.getY()))));

            }

        } else {
            return false;
        }

        maila.getHitbox().setLocation(maila.getX() - maila.getLeveys() / 2, maila.getY() - maila.getKorkeus() / 2);
        return true;
    }

    public void reagoiPowerUpiin(PowerUp p) {
        if (p == PowerUp.MAILA_KASVAA) {
            maila.setKorkeus(maila.getKorkeus() + 20);
        } else if (p == PowerUp.MAILA_NOPEUTUU) {
            nopeus += 0.5;
        } else if (p == PowerUp.RESETOI_POWERUPIT) {
            resetoiPowerupit();
        }
    }

    public void resetoiPowerupit() {
        this.nopeus = 5;
        this.maila.setKorkeus(45);
    }

    public Maila getMaila() {
        return maila;
    }

    public void setKiihtyvyys(double kiihtyvyys) {
        this.kiihtyvyys = kiihtyvyys;
    }
    
    public double getNopeus() {
        return this.nopeus;
    }

}

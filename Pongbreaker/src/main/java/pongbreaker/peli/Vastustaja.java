package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

/**
 * Luokka kuvaa pelissä olevaa vastustajaa, jonka mailalla on eri ominaisuuksia.
 * Vastustaja liikkuu yksinkertaisella tekoälyllä.
 * @author wolli
 */

public class Vastustaja {

    private Maila maila;
    private Pallo seurattavaPallo;

    private double kiihtyvyys;

    /**
     * Luokan konstruktori.
     * @param maila Vastustajan maila.
     * @param seurattavaPallo Pallo, jota vastustaja seuraa.
     */
    
    public Vastustaja(Maila maila, Pallo seurattavaPallo) {
        this.maila = maila;
        this.seurattavaPallo = seurattavaPallo;
        this.kiihtyvyys = 1;
    }
    
    /**
     * Liikuttaa Vastustajan mailaa.
     * Liikuttu matka riippuu seurattavan pallon sijainnista sekä pelaajan kiihtyvyydestä.
     */

    public void liiku() {
        if (maila.getY() < seurattavaPallo.getY()) {

            if (Math.abs(seurattavaPallo.getY() - maila.getY()) > 5) {
                maila.setY(maila.getY() + (int) (kiihtyvyys * 5));

            } else {
                maila.setY(maila.getY() + (int)(kiihtyvyys * (Math.abs(seurattavaPallo.getY() - maila.getY()))));

            }

        } else if (maila.getY() > seurattavaPallo.getY()) {
            if (Math.abs(seurattavaPallo.getY() - maila.getY()) > 5) {
                maila.setY(maila.getY() - (int) (kiihtyvyys * 5));

            } else {
                maila.setY(maila.getY() - (int)(kiihtyvyys * (Math.abs(seurattavaPallo.getY() - maila.getY()))));

            }

        }

        maila.getHitbox().setLocation(maila.getX() - maila.getLeveys() / 2, maila.getY() - maila.getKorkeus() / 2);
    }

    public Maila getMaila() {
        return maila;
    }

    public void setKiihtyvyys(double kiihtyvyys) {
        this.kiihtyvyys = kiihtyvyys;
    }

}

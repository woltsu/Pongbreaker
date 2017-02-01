package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

public class Vastustaja {

    private Maila maila;
    private Pallo seurattavaPallo;

    private int nopeus;

    public Vastustaja(Maila maila, Pallo seurattavaPallo) {
        this.maila = maila;
        this.seurattavaPallo = seurattavaPallo;
        this.nopeus = 2;
    }

    public void liiku() {
        if (maila.getY() < seurattavaPallo.getY()) {
            maila.setY(maila.getY() + this.nopeus);

        } else if (maila.getY() > seurattavaPallo.getY()) {
            maila.setY(maila.getY() - this.nopeus);

        }
        
        maila.getHitbox().setLocation(maila.getX() - maila.getLeveys() / 2, maila.getY() - maila.getKorkeus() / 2);
    }

    public Maila getMaila() {
        return maila;
    }

}

package pongbreaker.peli;

import pongbreaker.domain.Maila;

public class Pelaaja {

    private Maila maila;

    private double nopeus;
    private double kiihtyvyys;

    public Pelaaja(Maila maila) {
        this.maila = maila;
        this.nopeus = 5;
        this.kiihtyvyys = 0;
    }

    public void liiku() {
        maila.setY((int) (nopeus * kiihtyvyys));
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
    
    public Maila getMaila() {
        return this.maila;
    }
}

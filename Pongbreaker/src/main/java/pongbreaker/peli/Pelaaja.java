package pongbreaker.peli;

import pongbreaker.domain.Maila;

public class Pelaaja {

    private Maila maila;

    private double nopeus;
    private double kiihtyvyys;

    private boolean liikkuuko;

    public Pelaaja(Maila maila) {
        this.maila = maila;
        this.nopeus = 5;
        this.kiihtyvyys = 0;
        this.liikkuuko = false;
    }

    public void liiku() {
        maila.setY(maila.getY() + (int) (nopeus * kiihtyvyys));
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

    public void lisaaKiihtyvyytta() {
        if (this.kiihtyvyys < 1) {
            this.kiihtyvyys += 1;
        }
    }

    public void vahennaKiihtyvyytta() {
        if (this.kiihtyvyys > -1) {
            this.kiihtyvyys -= 1;
        }
    }

    public void setLiikkuuko(boolean liikkuuko) {
        this.liikkuuko = liikkuuko;
    }

    public Maila getMaila() {
        return this.maila;
    }
    
}
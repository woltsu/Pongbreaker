package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

/**
 * Luokka pitää huolen siitä, ettei mm. maila ja pallo lähde pois pelikentältä.
 *
 * @author wolli
 */
public class RajojenTarkkailija {

    private int leveys;
    private int korkeus;
    private int paatyrajanLeveys;

    /**
     * Luokan konstruktori.
     *
     * @param leveys Tarkasteltavan kentän leveys.
     * @param korkeus Tarkasteltavan kentän korkeus.
     * @param paatyrajanLeveys Tarkasteltavan kentän päätyrajojen leveys.
     */
    public RajojenTarkkailija(int leveys, int korkeus, int paatyrajanLeveys) {
        this.leveys = leveys;
        this.korkeus = korkeus;
        this.paatyrajanLeveys = paatyrajanLeveys;
    }

    /**
     * Metodi pitää huolen siitä, ettei pallo liiku pois kentältä.
     *
     * @param pallo Tarkasteltava pallo.
     */
    public void tarkistaOsuukoPalloReunoihin(Pallo pallo) {
        if (pallo.getX() <= pallo.getR()) {
            pallo.setX(pallo.getR());
            pallo.kaannaXNopeus();
        } else if (pallo.getX() >= leveys - pallo.getR() - 10) {
            pallo.setX(leveys - pallo.getR() - 10);
            pallo.kaannaXNopeus();
        }
        if (pallo.getY() <= pallo.getR()) {
            pallo.setY(pallo.getR());
            pallo.kaannaYNopeus();
        } else if (pallo.getY() >= this.korkeus - pallo.getR() - 30) {
            pallo.setY(korkeus - pallo.getR() - 30);
            pallo.kaannaYNopeus();
        }
    }

    /**
     * Metodi tarkastaa onko pelin pallo ylittänyt päätyrajan, jolloin peli
     * loppuu.
     *
     * @param pallo Tarkasteltava pallo.
     * @return true jos pallo on ylittänyt päätyrajan, false jos ei.
     */
    public boolean tarkistaOhittaakoPalloPaatyrajan(Pallo pallo) {
        if (pallo.getX() <= paatyrajanLeveys) {
            return true;
        } else if (pallo.getX() >= this.leveys - paatyrajanLeveys - 10) {
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkastaa ettei maila poistu pelikentältä.
     *
     * @param maila Maila, jota tarkastellaan.
     * @return true jos maila on poistunut pelikentältä, false jos ei.
     */
    public boolean tarkistaMeneekoMailaYliRajojen(Maila maila) {
        if (maila.getY() < maila.getKorkeus() / 2) {
            maila.setY(maila.getKorkeus() / 2);
            return true;
        } else if (maila.getY() > this.korkeus - 30 - maila.getKorkeus() / 2) {
            maila.setY(this.korkeus - 30 - maila.getKorkeus() / 2);
            return true;
        }
        return false;
    }

}

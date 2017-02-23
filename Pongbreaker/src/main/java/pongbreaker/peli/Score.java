package pongbreaker.peli;

/**
 * Luokkaa käytetään pisteiden tallentamisessa.
 * @author wolli
 */

public class Score implements Comparable<Score> {
    
    String nimi;
    int pisteet;

    /**
     * Luokan konstruktori.
     * @param nimi Nimimerkki.
     * @param pisteet Pistemäärä.
     */
    
    public Score(String nimi, int pisteet) {
        this.nimi = nimi;
        this.pisteet = pisteet;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public int getPisteet() {
        return this.pisteet;
    }

    @Override
    public int compareTo(Score o) {
        return o.pisteet - this.pisteet;
    }

}

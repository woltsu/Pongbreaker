package pongbreaker.peli;

public class Score implements Comparable<Score> {
    
    String nimi;
    int pisteet;

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

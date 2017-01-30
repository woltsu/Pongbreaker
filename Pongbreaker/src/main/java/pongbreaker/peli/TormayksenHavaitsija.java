package pongbreaker.peli;

import java.util.List;
import pongbreaker.domain.Peliolio;

public class TormayksenHavaitsija {
    private List<Peliolio> tarkistettavat;

    public TormayksenHavaitsija(List<Peliolio> tarkistettavat) {
        this.tarkistettavat = tarkistettavat;
    }
    
    public void tarkistaTormaykset() {
        
        for (int i = 0; i < tarkistettavat.size(); i++) {
            for (int j = i + 1; j < tarkistettavat.size(); j++) {
                
                
                
            }
        }
        
    }
}

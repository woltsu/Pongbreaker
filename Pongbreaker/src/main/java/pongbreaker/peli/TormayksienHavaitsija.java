package pongbreaker.peli;

import java.util.List;
import java.util.Random;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

public class TormayksienHavaitsija {

    private List<Peliolio> pelioliot;
    
    public TormayksienHavaitsija(List<Peliolio> pelioliot) {
        this.pelioliot = pelioliot;
    }
    
    public boolean tarkistaTormaykset() { //booleanin palauttaminen helpottaa testejä

        for (int i = 0; i < pelioliot.size(); i++) {
            
            for (int j = 0; j < pelioliot.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (pelioliot.get(i).getHitbox().intersects(pelioliot.get(j).getHitbox())) {
                    pelioliot.get(i).reagoiOsumaan();
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
}

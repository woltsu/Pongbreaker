package pongbreaker.peli;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pongbreaker.domain.Laatikko;
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
                List<Peliolio> poistettavat = new ArrayList<>();
                if (pelioliot.get(i).getHitbox().intersects(pelioliot.get(j).getHitbox())) {
                    
                    if (pelioliot.get(i).getClass() == Pallo.class) {
                        if (pelioliot.get(j).getClass() == Laatikko.class) {
                            
                            Pallo pallo = (Pallo) pelioliot.get(i);
                            Laatikko laatikko = (Laatikko) pelioliot.get(j);
                            
                            poistettavat.add(laatikko);
                            
                            if (pallo.getY() > laatikko.getY() + 12 || pallo.getY() < laatikko.getY() - 12) {
                                pallo.kaannaYNopeus();
                            } else {
                                pallo.reagoiOsumaan();
                            }
                            
                        } else {
                           pelioliot.get(i).reagoiOsumaan();
                        }
                    
                    } else {
                        pelioliot.get(i).reagoiOsumaan();
                    }
                    
                    return true;
                
                }
            }
            
        }
        
        return false;
    }
    
    public boolean osuuko(Peliolio peliolio) {
        
        for (Peliolio p : pelioliot) {
            if (peliolio.getHitbox().intersects(p.getHitbox())) {
                return true;
            }
        }
        
        return false;
    }
    
}

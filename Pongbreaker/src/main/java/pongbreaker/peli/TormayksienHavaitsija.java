package pongbreaker.peli;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
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
        boolean onkoTormayksia = false;
        for (int i = 0; i < pelioliot.size(); i++) {

            for (int j = 0; j < pelioliot.size(); j++) {
                if (i == j) {
                    continue;
                }
                
                if (pelioliot.get(i).getHitbox().intersects(pelioliot.get(j).getHitbox())) {

                    if (pelioliot.get(i).getClass() == Pallo.class) {
                        Pallo pallo = (Pallo) pelioliot.get(i);
                        
                        int tulos = pelioliot.get(j).getHitbox().outcode(pallo.getX(), pallo.getY());

                            if (tulos == Rectangle2D.OUT_TOP || tulos == Rectangle2D.OUT_BOTTOM) {
                                pallo.kaannaYNopeus();
                                pallo.kaannaXNopeus();
                                pallo.reagoiOsumaan();
                                pelioliot.get(j).reagoiOsumaan();
                                continue;
                                
                            } else {
                                pallo.reagoiOsumaan();
                                pelioliot.get(j).reagoiOsumaan();
                                continue;
                                
                            }
                        
                    }
                    
                    //pelioliot.get(i).reagoiOsumaan();
                    onkoTormayksia = true;
                }
            }
        }

        return onkoTormayksia;
    }

    public int poistaLaatikot() {
        int poistetut = 0;
        List<Peliolio> poistettavat = new ArrayList<>();

        for (Peliolio peliolio : pelioliot) {
            if (peliolio.getClass() == Laatikko.class) {
                Laatikko laatikko = (Laatikko) peliolio;
                if (laatikko.getPoistetaanko()) {
                    poistettavat.add(laatikko);
                    poistetut++;
                }
            }
        }

        for (Peliolio peliolio : poistettavat) {
            pelioliot.remove(peliolio);
        }

        return poistetut;
    }

    public void poistaKaikkiLaatikot() {
        List<Peliolio> poistettavat = new ArrayList<>();

        for (Peliolio peliolio : pelioliot) {
            if (peliolio.getClass() == Laatikko.class) {
                Laatikko laatikko = (Laatikko) peliolio;
                poistettavat.add(laatikko);
            }
        }

        for (Peliolio peliolio : poistettavat) {
            pelioliot.remove(peliolio);
        }
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

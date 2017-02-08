package pongbreaker.peli;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pongbreaker.domain.Laatikko;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

/**
 * Luokka havaitsee jos peliolioiden hitboxit
 * osuvat toisiinsa.
 * @author wolli
 */

public class TormayksienHavaitsija {

    private List<Peliolio> pelioliot;
    
    /**
     * Luokan konstruktori.
     * @param pelioliot Peliolioit, joiden 'törmäyksiä' tulee havaita.
     */
    
    public TormayksienHavaitsija(List<Peliolio> pelioliot) {
        this.pelioliot = pelioliot;
    }
    
    /**
     * Käy läpi kaikki pelin pelioloit ja tarkistaa osuvatko ne toisiinsa.
     * Pallo tarkistetaan erikseen, koska pallon uusi suunta riippuu siitä,
     * mistä suunnasta pallo osuu toiseen peliolioon.
     * @see pongbreaker.domain.Peliolio#reagoiOsumaan() 
     * @return true, jos pelissä on havaittu törmäys, false jos ei.
     */
    
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
    
    /**
     * Poistaa pelissä olevat laatikot, joihin pallo on osunut.
     * @return Poistettujen laatikoiden määrä.
     */
    
    public int poistaLaatikotJoihinOsuttu() {
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
    
    /**
     * Poistaa kaikki pelin laatikot.
     */
    
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
    
    /**
     * Tarkistaa osuuko annettu peliolio johonkin pelissä olevaan toiseen peliolioon.
     * @param peliolio Peliolio, jota tarkastellaan.
     * @return true, jos annettu peliolio osuu johonkin toiseen peliolioon, false jos ei.
     */

    public boolean osuuko(Peliolio peliolio) {

        for (Peliolio p : pelioliot) {
            if (peliolio.getHitbox().intersects(p.getHitbox())) {
                return true;
            }
        }

        return false;
    }

}

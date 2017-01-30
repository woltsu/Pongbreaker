package pongbreaker.peli;

import java.util.List;
import pongbreaker.domain.Pallo;
import pongbreaker.domain.Peliolio;

public class TormayksienHavaitsija {
    private Pallo pallo;
    private Pelaaja pelaaja;
    
    public TormayksienHavaitsija(Pallo pallo, Pelaaja pelaaja) {
        this.pallo = pallo;
        this.pelaaja = pelaaja;
    }
    
    public void tarkistaTormaykset() {
        
        if (this.pallo.osuuko(this.pelaaja.getMaila().getX(), this.pelaaja.getMaila().getY())) {
            this.pallo.kaannaXNopeus();
        }
        
    }
}

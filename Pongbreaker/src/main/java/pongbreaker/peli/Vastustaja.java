package pongbreaker.peli;

import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

public class Vastustaja {
    
    private Maila maila;
    private Pallo seurattavaPallo;
    
    public Vastustaja(Maila maila, Pallo seurattavaPallo) {
        this.maila = maila;
        this.seurattavaPallo = seurattavaPallo;
    }
    
    public void liiku() {
        if (maila.getY() < seurattavaPallo.getY()) {
            maila.setY(maila.getY() + 1);
        
        } else if (maila.getY() > seurattavaPallo.getY()) {
            maila.setY(maila.getY() - 1);
            
        }
    }
    
    public Maila getMaila() {
        return maila;
    }
    
}

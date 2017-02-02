package pongbreaker.peli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pongbreaker.domain.Maila;
import pongbreaker.domain.Pallo;

public class VastustajaTest {

    Maila maila;
    Pallo pallo;
    Vastustaja vastustaja;

    @Before
    public void setUp() {
        maila = new Maila(100, 100);
        pallo = new Pallo(5, 50, 100);
        vastustaja = new Vastustaja(maila, pallo);
    }
    
    
}

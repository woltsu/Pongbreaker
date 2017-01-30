package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;

public class Maila extends Piirrettava {
    private int leveys;
    private int korkeus;
    
    public Maila(int x, int y) {
        super(x, y);
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }
    
}

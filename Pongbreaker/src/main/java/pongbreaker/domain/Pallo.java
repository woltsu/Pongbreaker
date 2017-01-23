package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;

public class Pallo extends Piirrettava {
    private int r;
    
    private int xNopeus;
    private int yNopeus;
    
    public Pallo(int r, int x, int y) {
        super(x, y);
        this.r = r;
        
        xNopeus = 2;
        yNopeus = 2;
    }
    
    public void liiku() {
        super.x += xNopeus;
        super.y += yNopeus;
    }
    
    public void kaannaXNopeus() {
        xNopeus *= -1;
    }
    
    public void kaannaYNopeus() {
        yNopeus *= -1;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - r, y - r, 2*r, 2*r);
    }

}

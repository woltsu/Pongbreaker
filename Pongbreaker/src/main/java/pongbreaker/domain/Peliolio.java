package pongbreaker.domain;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Peliolio {

    protected int x;
    protected int y;

    public Peliolio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

    public abstract void piirra(Graphics g);
    public abstract void reagoiOsumaan();
    public abstract Rectangle getHitbox();

}

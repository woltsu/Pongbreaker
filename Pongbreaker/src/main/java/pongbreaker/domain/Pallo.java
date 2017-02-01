package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pallo extends Peliolio {

    private int r;
    private int xNopeus;
    private int yNopeus;
    private double kiihtyvyys;
    private Rectangle hitbox;

    public Pallo(int r, int x, int y) {
        super(x, y);
        this.r = r;
        xNopeus = 3;
        yNopeus = 3;
        kiihtyvyys = 1;
        this.hitbox = new Rectangle(x - r, y - r, 2 * r, 2 * r);
    }

    public void liiku() {
        super.x += xNopeus * kiihtyvyys;
        super.y += yNopeus * kiihtyvyys;
        hitbox.setLocation(x - r, y - r);
    }

    public void kaannaXNopeus() {
        xNopeus *= -1;
    }

    public void kaannaYNopeus() {
        yNopeus *= -1;
    }

    public int getXNopeus() {
        return xNopeus;
    }

    public int getYNopeus() {
        return yNopeus;
    }
    
    public void setXNopeus(int nopeus) {
        xNopeus = nopeus;
    }
    
    public void setYNopeus(int nopeus) {
        yNopeus = nopeus;
    }
    
    public double getKiihtyvyys() {
        return this.kiihtyvyys;
    }
    
    public void setKiihtyvyys(double kiihtyvyys) {
        this.kiihtyvyys = kiihtyvyys;
    }
    
    public int getR() {
        return r;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

//    @Override
//    public boolean osuuko(int x, int y) {
//        if (Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) <= Math.pow(this.r, 2)) {
//            return true;
//        }
//        return false;
//    }

}

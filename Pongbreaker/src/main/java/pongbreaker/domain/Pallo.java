package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

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
        paivitaHitbox();
    }
    
    public void paivitaHitbox() {
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
    
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        //g.fillOval(x - r, y - r, 2 * r, 2 * r);
        g.fillRect(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void reagoiOsumaan() {
        kaannaXNopeus();
        Random random = new Random();
        
        liiku();
        
        int uusiXNopeus = 2 + random.nextInt(2) + 1;
        int uusiYNopeus = 2 + random.nextInt(2) + 1;
        
        if (xNopeus > 0) {
            xNopeus = uusiXNopeus;
        } else {
            xNopeus = -1 * uusiXNopeus;
        }
        
        if (yNopeus > 0) {
            yNopeus = uusiYNopeus;
        } else {
            yNopeus = -1 * uusiYNopeus;
        }
        
        if (kiihtyvyys < 2.45) {
            kiihtyvyys += 0.05;
        }
        
    }

}

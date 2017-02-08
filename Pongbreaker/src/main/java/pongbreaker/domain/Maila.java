package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;

/**
 * Luokka kuvaa pelikentällä olevia mailoja.
 * @author wolli
 */

public class Maila extends Peliolio {

    private int leveys;
    private int korkeus;
    
    private Rectangle hitbox;
    
    /**
     * Luokan konstruktori.
     * @param x mailan x-arvo koordinaatistossa.
     * @param y mailan y-arvo koordinaatistossa.
     * @see pongbreaker.domain.Peliolio#Peliolio(int, int) 
     */

    public Maila(int x, int y) {
        super(x, y);
        this.leveys = 10;
        this.korkeus = 45;
        this.hitbox = new Rectangle(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
        this.hitbox = new Rectangle(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    } 
    
    /**
     * @see pongbreaker.domain.Peliolio#getHitbox() 
     */
    
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    /**
     * @see pongbreaker.domain.Peliolio#piirra(java.awt.Graphics) 
     */

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }
    
    /**
     * @see pongbreaker.domain.Peliolio#reagoiOsumaan() 
     */

    @Override
    public void reagoiOsumaan() {
        //Äänet
    }

}

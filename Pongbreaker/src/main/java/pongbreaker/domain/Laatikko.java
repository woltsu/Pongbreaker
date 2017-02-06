package pongbreaker.domain;

import com.sun.javafx.scene.text.HitInfo;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Laatikko extends Peliolio {
    
    int sivunPituus;
    
    private Rectangle hitbox;
    
    public Laatikko(int x, int y) {
        super(x, y);
        sivunPituus = 25;
        hitbox = new Rectangle(x - sivunPituus / 2, y - sivunPituus / 2, sivunPituus, sivunPituus);
    }

    @Override
    public void piirra(Graphics g) {
        g.fillRect(x - sivunPituus / 2, y - sivunPituus / 2, sivunPituus, sivunPituus);
    }

    @Override
    public void reagoiOsumaan() {
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

}

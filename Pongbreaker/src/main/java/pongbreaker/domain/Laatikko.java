package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Objects;

public class Laatikko extends Peliolio {
    
    int sivunPituus;
    
    private Rectangle hitbox;
    
    public Laatikko(int x, int y) {
        super(x, y);
        sivunPituus = 25;
        hitbox = new Rectangle(x - sivunPituus / 2, y - sivunPituus / 2, sivunPituus, sivunPituus);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laatikko other = (Laatikko) obj;
        if (this.sivunPituus != other.sivunPituus) {
            return false;
        }
        if (!Objects.equals(this.hitbox, other.hitbox)) {
            return false;
        }
        return true;
    }
    

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
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

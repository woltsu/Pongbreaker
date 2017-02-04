package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;

public class Maila extends Peliolio {

    private int leveys;
    private int korkeus;
    
    private Rectangle hitbox;

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
    
    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }

    @Override
    public void reagoiOsumaan() {
        //Äänet
    }

}

package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;

public class Maila extends Peliolio {

    private int leveys;
    private int korkeus;

    public Maila(int x, int y) {
        super(x, y);
        this.leveys = 10;
        this.korkeus = 45;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }

    @Override
    public boolean osuuko(int x, int y) {
        if (x >= this.x - this.leveys / 2 && x <= this.x + this.leveys / 2 && y >= this.y - this.korkeus / 2 && y <= this.y + this.korkeus / 2) {
            return true;
        }
        return false;
    }

}

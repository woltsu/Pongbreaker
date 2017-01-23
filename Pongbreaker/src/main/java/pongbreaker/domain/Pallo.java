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

        xNopeus = 3;
        yNopeus = 3;
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

    public int getXNopeus() {
        return xNopeus;
    }

    public int getYNopeus() {
        return yNopeus;
    }

    public int getR() {
        return r;
    }

    @Override
    public void piirra(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
    }

}

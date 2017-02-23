package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 * Luokka kuvaa pelikentällä olevaa palloa.
 *
 * @author wolli
 */
public class Pallo extends Peliolio {

    private int r;
    private int xNopeus;
    private int yNopeus;
    private double kiihtyvyys;
    private Rectangle hitbox;
    private boolean tuhoutumaton;

    /**
     * Luokan konstruktori.
     *
     * @param r pallon säde.
     * @param x pallon x-arvo koordinaatistossa.
     * @param y pallon y-arvo koordinaatistossa.
     * @see pongbreaker.domain.Peliolio
     */
    public Pallo(int r, int x, int y) {
        super(x, y);
        this.r = r;
        xNopeus = 3;
        yNopeus = 3;
        kiihtyvyys = 1;
        this.hitbox = new Rectangle(x - r, y - r, 2 * r, 2 * r);
        tuhoutumaton = false;
    }

    /**
     * Palauttaa pallon ominaisuudet alkuperäiseen tilaan.
     */
    public void resetoi() {
        setKiihtyvyys(1);
        setXNopeus(3);
        setR(6);
    }

    /**
     * Muuttaa x- ja y-koordinaatteja riippuen x- ja y-nopeuksista sekä
     * kiihtyvyydestä. Päivittää samalla hitboxin sijainnin.
     *
     * @see pongbreaker.domain.Pallo#paivitaHitbox()
     */
    public void liiku() {
        super.x += xNopeus * kiihtyvyys;
        super.y += yNopeus * kiihtyvyys;
        paivitaHitbox();
    }

    /**
     * Asettaa hitboxin sijainnin pallon sijaintiin.
     */
    public void paivitaHitbox() {
        hitbox.setLocation(x - r, y - r);
    }

    /**
     * Muuttaa x-nopeuden sen vastaluvuksi.
     */
    public void kaannaXNopeus() {
        xNopeus *= -1;
    }

    /**
     * Muuttaa y-nopeuden sen vastaluvuksi.
     */
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

    /**
     * Muuttaa pallon säteen suuruutta sekä muuttaa myös hitboxin kokoa.
     *
     * @param r Uuden säteen koko.
     */
    public void setR(int r) {
        this.r = r;
        this.hitbox = new Rectangle(x - r, y - r, 2 * r, 2 * r);
    }

    public boolean getTuhoutumaton() {
        return tuhoutumaton;
    }

    /**
     * Muuttaa pallon 'tuhoutumattomaksi' ja kasvattaa pallon sädettä, jos se on
     * alle 25.
     *
     * @param b Halutaanko tehdä pallosta tuhoutumaton ja kasvattaa sädettä.
     */
    public void setTuhoutumaton(boolean b) {
        tuhoutumaton = b;
        if (b) {
            if (r <= 20) {
                setR(r + 5);
            }
        }
    }

    @Override
    public Rectangle getHitbox() {
        return hitbox;
    }

    @Override
    public void piirra(Graphics g) {
        if (tuhoutumaton) {
            Random random = new Random();
            double luku = random.nextDouble();
            if (luku < 0.2) {
                g.setColor(Color.red);
            } else if (luku < 0.4) {
                g.setColor(Color.blue);
            } else if (luku < 0.6) {
                g.setColor(Color.green);
            } else if (luku < 0.8) {
                g.setColor(Color.pink);
            } else {
                g.setColor(Color.orange);
            }
        } else {
            g.setColor(Color.white);
        }
        g.fillOval(x - r, y - r, 2 * r, 2 * r);
        //g.fillRect(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public void reagoiOsumaan() {
        kaannaXNopeus();
        Random random = new Random();

        liiku();

        int uusiXNopeus = 2 + random.nextInt(1);
        int uusiYNopeus = 2 + random.nextInt(1);

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

        if (kiihtyvyys < 4) { //2.45
            kiihtyvyys += 0.02;
        }

    }

}

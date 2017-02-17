package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javafx.scene.shape.Circle;

/**
 * Luokka kuvaa pelikentällä olevia mailoja.
 *
 * @author wolli
 */
public class Maila extends Peliolio {

    private int leveys;
    private int korkeus;

    private Rectangle hitbox;
    private boolean onkoOsunutViimeksi;

    /**
     * Luokan konstruktori.
     *
     * @param x mailan x-arvo koordinaatistossa.
     * @param y mailan y-arvo koordinaatistossa.
     * @see pongbreaker.domain.Peliolio#Peliolio(int, int)
     */
    public Maila(int x, int y) {
        super(x, y);
        this.leveys = 10;
        this.korkeus = 45;
        this.hitbox = new Rectangle(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
        this.onkoOsunutViimeksi = false;
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    /**
     * Vaihtaa mailan sekä hitboxin y-koordinaattia.
     * @param korkeus Uusi korkeus.
     */
    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
        this.hitbox = new Rectangle(x - leveys / 2, y - korkeus / 2, leveys, korkeus);
    }

    public boolean getOnkoOsunutViimeksi() {
        return onkoOsunutViimeksi;
    }

    public void setOnkoOsunutViimeksi(boolean b) {
        onkoOsunutViimeksi = b;
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
        onkoOsunutViimeksi = true;
        //Äänet
    }

}

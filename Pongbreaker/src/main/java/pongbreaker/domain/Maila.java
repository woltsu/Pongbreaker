package pongbreaker.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Circle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Luokka kuvaa pelikentällä olevia mailoja.
 *
 * @author wolli
 */
public class Maila extends Peliolio {

    private Clip clip;
    private int leveys;
    private int korkeus;

    private Rectangle hitbox;
    private boolean onkoOsunutViimeksi;

    /**
     * Luokan konstruktori. Luo samalla Clip:in.
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

//        File soundFile = new File(getClass().getClassLoader().getResource("test/maila.wav").getPath());
//
//        try {
//            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
//            clip = AudioSystem.getClip();
//            clip.open(audioIn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Vaihtaa mailan sekä hitboxin y-koordinaattia.
     *
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
//        if (clip.isRunning()) {
//            clip.stop();
//        }
//        clip.setFramePosition(0);
//        clip.start();
    }

}

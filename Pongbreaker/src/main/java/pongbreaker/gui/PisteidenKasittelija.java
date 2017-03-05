package pongbreaker.gui;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pongbreaker.peli.Pongbreaker;
import pongbreaker.peli.Score;

/**
 * Luokka pitää kirjaa pisteistä ja kirjoittaa niitä tiedostoon.
 *
 * @author wolli
 */
public class PisteidenKasittelija implements Paivitettava {

    private String tiedosto;
    private Frame frame;
    private Pongbreaker peli;
    private boolean naytetaanko;
    private List<Score> highscores;

    /**
     * Luokan konstruktori.
     *
     * @param tiedosto Tiedosto, johon pistemäärät kirjoitetaan.
     * @param frame Tarvitsee tätä kysyessään pelaajan nimeä.
     * @param peli Ottaa pelistä pelaajan pisteet.
     */
    public PisteidenKasittelija(String tiedosto, Frame frame, Pongbreaker peli) {
        this.tiedosto = tiedosto;
        this.frame = frame;
        this.peli = peli;
        this.naytetaanko = false;
        this.highscores = new ArrayList<>();
        this.haeHighscoret();
    }

    /**
     * Kirjoittaa uuden pistemäärän tiedostoon.
     *
     * @param nimi Pelaajan nimi.
     * @param score Pistemäärä.
     */
    public void lisaaHighscore(String nimi, int score) {
        try {
            
            FileWriter kirjoittaja = new FileWriter(new File(getClass().getClassLoader().getResource("test/highscore.txt").getPath()), true);
            kirjoittaja.write(nimi + ":" + score + "\n");
            kirjoittaja.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String haeHighscoret() {
        String palautus = "";
        List<Score> scores = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("test/highscore.txt");
            //System.out.println(getClass().getClassLoader().getResourceAsStream("test/highscore.txt"));
            Scanner lukija = new Scanner(is);

            while (lukija.hasNextLine()) {
                String[] osat = lukija.nextLine().split(":");
                scores.add(new Score(osat[0], Integer.parseInt(osat[1])));
            }

            lukija.close();
        } catch (Exception e) {
            this.highscores = new ArrayList<>();
            return null;
        }

        Collections.sort(scores);
        this.highscores = scores;
        int i = 0;
        for (Score score : scores) {
            i++;
            palautus += i + ". " + score.getNimi() + " -------- " + score.getPisteet() + "\n";
            if (i == 6) {
                break;
            }
        }
        return palautus;
    }

    @Override
    public void paivita() {
        if (!peli.getOnkoPaalla()) {
            if (!this.naytetaanko) {
                lisaaListaan();
                JOptionPane.showMessageDialog(frame, haeHighscoret(), "Top 6", JOptionPane.PLAIN_MESSAGE);
                this.naytetaanko = true;
            }
        } else {
            this.naytetaanko = false;
        }
    }

    private void lisaaListaan() {
        if (paaseekoListaan(peli.getPelaaja().getPisteet())) {
            String nimi = JOptionPane.showInputDialog(frame, "Anna nimi: ", "Nimi", JOptionPane.PLAIN_MESSAGE);
            if (nimi != null && !nimi.isEmpty()) {
                lisaaHighscore(nimi, peli.getPelaaja().getPisteet());
            }
        }
    }

    private boolean paaseekoListaan(int score) {
        if (this.highscores.size() < 6) {
            return true;
        }

        int pieninScore = this.highscores.get(5).getPisteet();
        if (score > pieninScore) {
            return true;
        }

        return false;
    }

}

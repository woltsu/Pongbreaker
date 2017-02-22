package pongbreaker.gui;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pongbreaker.peli.Pongbreaker;
import pongbreaker.peli.Score;

public class PisteidenKasittelija implements Paivitettava {

    private String tiedosto;
    private Frame frame;
    private Pongbreaker peli;
    private boolean naytetaanko;
    private List<Score> highscores;

    public PisteidenKasittelija(String tiedosto, Frame frame, Pongbreaker peli) {
        this.tiedosto = tiedosto;
        this.frame = frame;
        this.peli = peli;
        this.naytetaanko = false;
        this.highscores = new ArrayList<>();
        this.haeHighscoret();
    }

    public void lisaaHighscore(String nimi, int score) {
        try {
            FileWriter kirjoittaja = new FileWriter(new File(tiedosto), true);
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
            Scanner lukija = new Scanner(new File(tiedosto));

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
                if (paaseekoListaan(peli.getPelaaja().getPisteet())) {
                    String nimi = JOptionPane.showInputDialog(frame, "Anna nimi: ", "Nimi", JOptionPane.PLAIN_MESSAGE);
                    lisaaHighscore(nimi, peli.getPelaaja().getPisteet());
                }
                JOptionPane.showMessageDialog(frame, haeHighscoret(), "Top 6", JOptionPane.PLAIN_MESSAGE);
                this.naytetaanko = true;
            }
        } else {
            this.naytetaanko = false;
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

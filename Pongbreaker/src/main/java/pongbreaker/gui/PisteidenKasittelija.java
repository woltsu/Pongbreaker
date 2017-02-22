package pongbreaker.gui;

import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pongbreaker.peli.Pongbreaker;

public class PisteidenKasittelija implements Paivitettava {

    private String tiedosto;
    private Frame frame;
    private Pongbreaker peli;
    private boolean naytetaanko;

    public PisteidenKasittelija(String tiedosto, Frame frame, Pongbreaker peli) {
        this.tiedosto = tiedosto;
        this.frame = frame;
        this.peli = peli;
        this.naytetaanko = false;
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
        try {
            Scanner lukija = new Scanner(new File(tiedosto));

            while (lukija.hasNextLine()) {
                String[] osat = lukija.nextLine().split(":");
                palautus += osat[0] + " ---------- " + osat[1] + "\n";
            }

            lukija.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return palautus;
    }

    @Override
    public void paivita() {
        if (!peli.getOnkoPaalla()) {
            if (!this.naytetaanko) {
                this.naytetaanko = true;
                String nimi = JOptionPane.showInputDialog(frame, "Anna nimi: ");
                lisaaHighscore(nimi, peli.getPelaaja().getPisteet());
                haeHighscoret();
                JOptionPane.showMessageDialog(frame, haeHighscoret());
            }
        } else {
            this.naytetaanko = false;
        }
    }

}

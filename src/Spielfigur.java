package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Spielfigur {
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private int id;
    private Image aktuelleFigur;
    private Image figur;
    private Image figurKrug;

    public Spielfigur(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        try {
            figur = ImageIO.read(new File("src/img/figuren/spieler.png"));

        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden");
        }
        try {
            figurKrug = ImageIO.read(new File("src/img/figuren/spieler-krug.png"));

        } catch (IOException e) {
            System.err.println("Spieler mit Krug konnte nicht geladen werden");
        }
        aktuelleFigur = figur;
    }

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, aktuelleFigur, x*breite, y*hoehe, breite, hoehe);
    }

    public void bewegen(int deltaX, int deltaY, ZeichenFlaeche14 zf) {
        zf.verschieben(id, deltaX*breite, deltaY*hoehe);
        setX(getX()+deltaX);
        setY(getY()+deltaY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBreite() {
        return breite;
    }

    public int getHoehe() {
        return hoehe;
    }

    public int getId() {
        return id;
    }

    public Image getFigur() {
        return figur;
    }

    public Image getFigurKrug() {
        return figurKrug;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public void setAktuelleFigur(Image aktuelleFigur) {
        this.aktuelleFigur = aktuelleFigur;
    }
}

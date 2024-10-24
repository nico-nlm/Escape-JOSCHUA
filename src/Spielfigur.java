package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Spielfigur {
    private int x;
    private int y;
    private int neachsteX;
    private int neachsteY;
    private int breite;
    private int hoehe;
    private int id;
    private Image figur;

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
    }

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, figur, x*breite, y*hoehe, breite, hoehe);
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

    public int getNeachsteX() {
        return neachsteX;
    }

    public int getNeachsteY() {
        return neachsteY;
    }

    public int getId() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setNeachsteX(int neachsteX) {
        this.neachsteX = neachsteX;
    }

    public void setNeachsteY(int neachsteY) {
        this.neachsteY = neachsteY;
    }
}

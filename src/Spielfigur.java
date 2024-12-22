package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Spielfigur extends Identitaet {
    private Image figur;
    private Image figurKrug;
    private Image aktuelleFigur;

    public Spielfigur(int x, int y, int breite, int hoehe, int id) {
        super(x, y, breite, hoehe, id);
        try {
            figur = ImageIO.read(new File("src/img/figuren/spieler.png"));
            figurKrug = ImageIO.read(new File("src/img/figuren/spieler-krug.png"));
        } catch (IOException e) {
            System.err.println("Spieler konnten nicht geladen werden");
        }
        aktuelleFigur = figur;
    }

    public void bewegen(int deltaX, int deltaY, ZeichenFlaeche14 zf) {
        zf.verschieben(id, deltaX*breite, deltaY*hoehe);
        x += deltaX;
        y += deltaY;
    }

    public Image getFigur() {
        return figur;
    }

    public Image getFigurKrug() {
        return figurKrug;
    }

    public Image getAktuelleFigur() {
        return aktuelleFigur;
    }

    public void setAktuelleFigur(Image aktuelleFigur) {
        this.aktuelleFigur = aktuelleFigur;
    }
}

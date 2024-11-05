package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Krug {
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private int id;
    private Image krug;

    public Krug(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        try {
            krug = ImageIO.read(new File("src/img/items/krug.png"));
        } catch (IOException e) {
            System.err.println("Krug konnte nicht geladen werden");
        }
    }

    public void zeichneKrug(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, krug, x, y, breite, hoehe);
    }

    public void collect(ZeichenFlaeche14 zf, Spielfigur spieler) {
        zf.loeschen(id);
        zf.loeschen(spieler.getId());
        zf.setzeBild(spieler.getId(), spieler.getFigurKrug(), spieler.getX(), spieler.getY(), spieler.getBreite(), spieler.getHoehe());
    }
}

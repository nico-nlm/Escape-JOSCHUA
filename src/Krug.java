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

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, krug, x*breite, y*hoehe, breite, hoehe);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }


}

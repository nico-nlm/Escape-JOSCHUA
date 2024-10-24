import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Npc {
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private int id;
    private Image figur;

    public Npc(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        try {
            figur = ImageIO.read(new File("img/figuren/joschua.jpg"));

        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden");
        }
    }

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, figur, x, y, breite, hoehe);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
}

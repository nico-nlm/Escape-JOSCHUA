package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Npc {
    private int x;
    private int y;
    private int breite;
    private int hoehe;
    private int id;
    private Image figur;
    private Random rnd;

    public Npc(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        try {
            figur = ImageIO.read(new File("src/img/figuren/joschua.jpg"));

        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden");
        }
        rnd = new Random();
    }

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, figur, x*breite, y*hoehe, breite, hoehe);
    }

    public void bewegen(int[][] map, ZeichenFlaeche14 zf) {
        int richtung = rnd.nextInt(4);
        switch (richtung) {
            case 0:
                if (map[y-1][x] == 0) {
                    zf.verschieben(id, 0, -hoehe);
                    y--;
                }
                break;
            case 1:
                if (map[y+1][x] == 0) {
                    zf.verschieben(id, 0, hoehe);
                    y++;
                }
                break;
            case 2:
                if (map[y][x-1] == 0) {
                    zf.verschieben(id, -breite, 0);
                    x--;
                }
                break;
            case 3:
                if (map[y][x+1] == 0) {
                    zf.verschieben(id, 0, breite);
                    x++;
                }
                break;
            default:
        }
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

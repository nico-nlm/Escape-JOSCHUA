package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Npc extends Identitaet {
    private Image figur;
    private Random rnd;
    private int richtung;
    private boolean richtungGefunden;

    public Npc(int x, int y, int breite, int hoehe, int id) {
        super(x, y, breite, hoehe, id);
        try {
            figur = ImageIO.read(new File("src/img/figuren/joschua.jpg"));

        } catch (IOException e) {
            System.err.println("Bild konnte nicht geladen werden");
        }
        rnd = new Random();
    }

    public void bewegen(int[][] map, ZeichenFlaeche14 zf) {
        richtungGefunden = false;
        while (!richtungGefunden) {
            richtung = rnd.nextInt(4);
            switch (richtung) {
                case 0:
                    if (map[y-1][x] == 0) {
                        zf.verschieben(id, 0, -hoehe);
                        y--;
                        richtungGefunden = true;
                    }
                    break;
                case 1:
                    if (map[y+1][x] == 0) {
                        zf.verschieben(id, 0, hoehe);
                        y++;
                        richtungGefunden = true;
                    }
                    break;
                case 2:
                    if (map[y][x-1] == 0) {
                        zf.verschieben(id, -breite, 0);
                        x--;
                        richtungGefunden = true;
                    }
                    break;
                case 3:
                    if (map[y][x+1] == 0) {
                        zf.verschieben(id, breite, 0);
                        x++;
                        richtungGefunden = true;
                    }
                    break;
                default:
            }
        }
    }

    public Image getFigur() {
        return figur;
    }
}

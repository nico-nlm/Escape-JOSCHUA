package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Spielfigur extends Identitaet {

    public Spielfigur(int x, int y, int breite, int hoehe, Image grafik, int id) {
        super(x, y, breite, hoehe, grafik, id);
    }

    public void bewegen(int deltaX, int deltaY, ZeichenFlaeche14 zf) {
        zf.verschieben(id, deltaX*breite, deltaY*hoehe);
        x += deltaX;
        y += deltaY;
    }
}

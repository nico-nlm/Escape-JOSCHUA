package src;

import java.awt.*;

public class Schablone extends Identitaet{
    public Schablone(int x, int y, int breite, int hoehe, Image grafik, int id) {
        super(x, y, breite, hoehe, grafik, id);
    }

    public void zeichneSchablone(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, grafik, x, y, breite, hoehe);
    }

    public void bewegenSchablone(int deltaX, int deltaY, ZeichenFlaeche14 zf) {
        zf.verschieben(id, deltaX, deltaY);
        x += deltaX;
        y += deltaY;
    }
}
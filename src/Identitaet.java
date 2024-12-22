package src;

import java.awt.*;

public class Identitaet {
    protected int x;
    protected int y;
    protected int breite;
    protected int hoehe;
    protected int id;

    Identitaet(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
    }

    protected void zeichnen(ZeichenFlaeche14 zf, Image img) {
        zf.setzeBild(id, img, x*breite, y*hoehe, breite, hoehe);
    }

    protected int getX() {
        return x;
    }
    protected void setX(int x) {
        this.x = x;
    }
    protected int getY() {
        return y;
    }
    protected void setY(int y) {
       this.y = y;
    }
    protected int getBreite() {
        return breite;
    }
    protected void setBreite(int breite) {
        this.breite = breite;
    }
    protected int getHoehe() {
        return hoehe;
    }
    protected void setHoehe(int hoehe) {
        this.hoehe = hoehe;
    }
    protected int getId() {
        return id;
    }
}

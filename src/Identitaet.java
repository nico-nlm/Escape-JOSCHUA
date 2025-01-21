package src;

import java.awt.*;

public class Identitaet {
    protected int x;
    protected int y;
    protected int breite;
    protected int hoehe;
    protected Image grafik;
    protected int id;

    Identitaet(int x, int y, int breite, int hoehe, Image grafik, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.grafik = grafik;
        this.id = id;
    }

    protected void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(id, grafik, x*breite, y*hoehe, breite, hoehe);
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
    protected void setGrafik(Image grafik) {
        this.grafik = grafik;
    }
    protected int getId() {
        return id;
    }
}

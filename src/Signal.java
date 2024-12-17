package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Signal {
    private Image signalImg;
    private int id;
    private int x;
    private int y;
    private int breite;
    private int hoehe;

    public Signal(int x, int y, int breite, int hoehe, int id) {
        this.x = x;
        this.y = y;
        this.breite = breite;
        this.hoehe = hoehe;
        this.id = id;
        try {
            signalImg = ImageIO.read(new File("src/img/maps/signal.png"));
        } catch (
                IOException e) {
            System.err.println("Signal konnte nicht geladen werden");
        }
    }

    public void zeichnen(ZeichenFlaeche14 zf) {
        zf.setzeBild(9, signalImg, x*breite, y*hoehe, breite, hoehe);
    }
}

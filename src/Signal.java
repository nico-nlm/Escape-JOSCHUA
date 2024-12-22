package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Signal extends Identitaet {
    private Image img;

    public Signal(int x, int y, int breite, int hoehe, int id) {
        super(x, y, breite, hoehe, id);
        try {
            img = ImageIO.read(new File("src/img/maps/signal.png"));
        } catch (
                IOException e) {
            System.err.println("Signal konnte nicht geladen werden");
        }
    }

    public Image getImg() {
        return img;
    }
}

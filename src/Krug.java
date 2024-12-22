package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Krug extends Identitaet {
    private Image img;

    public Krug(int x, int y, int breite, int hoehe, int id) {
        super(x, y, breite, hoehe, id);
        try {
            img = ImageIO.read(new File("src/img/items/krug.png"));
        } catch (IOException e) {
            System.err.println("Krug konnte nicht geladen werden");
        }
    }

    public Image getImg() {
        return img;
    }


}

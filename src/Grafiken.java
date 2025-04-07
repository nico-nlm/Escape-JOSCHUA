package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Grafiken {
    //Figuren
    private String spieler;
    private String spielerKrug;
    private String joschua;
    private Image spielerImg;
    private Image spielerKrugImg;
    private Image joschuaImg;

    //Items

    private String krug;
    private String darkroomSchalter;
    private String darkroomSchalterUmgelegt;
    private Image krugImg;
    private Image darkroomSchalterImg;
    private Image darkroomSchalterUmgelegtImg;

    //Maps
    private String hauptraum;
    private String druckplattenraum;
    private String darkroom;
    private String darkroomFrei;
    private String schablone;
    private String signal;
    private String outside;
    private Image hauptraumImg;
    private Image druckplattenraumImg;
    private Image darkroomImg;
    private Image darkroomFreiImg;
    private Image schabloneImg;
    private Image signalImg;
    private Image outsideImg;


    Grafiken() {
        //Figuren
        spieler = "src/img/figuren/spieler.png";
        spielerKrug = "src/img/figuren/spieler-krug.png";
        joschua =  "src/img/figuren/joschua.jpg";

        //Items
        krug = "src/img/items/krug.PNG";
        darkroomSchalter = "src/img/items/hebel.png";
        darkroomSchalterUmgelegt = "src/img/items/hebel-umgelegt.png";

        //Maps
        hauptraum = "src/img/maps/hauptraum.png";
        druckplattenraum = "src/img/maps/druckplattenraum.png";
        darkroom = "src/img/maps/darkroom.png";
        darkroomFrei = "src/img/maps/darkroomFrei.png";
        schablone = "src/img/maps/schablone.png";
        signal = "src/img/maps/signal.PNG";
        outside = "src/img/maps/outside.jpeg";

        try {
            //Figuren
            spielerImg = ImageIO.read(new File(spieler));
            spielerKrugImg = ImageIO.read(new File(spielerKrug));
            joschuaImg = ImageIO.read(new File(joschua));

            //Items
            krugImg = ImageIO.read(new File(krug));
            darkroomSchalterImg = ImageIO.read(new File(darkroomSchalter));
            darkroomSchalterUmgelegtImg = ImageIO.read(new File(darkroomSchalterUmgelegt));

            //Maps
            hauptraumImg = ImageIO.read(new File(hauptraum));
            druckplattenraumImg = ImageIO.read(new File(druckplattenraum));
            darkroomImg = ImageIO.read(new File(darkroom));
            darkroomFreiImg = ImageIO.read(new File(darkroomFrei));
            schabloneImg = ImageIO.read(new File(schablone));
            signalImg = ImageIO.read(new File(signal));
            outsideImg = ImageIO.read(new File(outside));

        } catch (IOException e) {
            System.err.println("Eine Figur konnte nicht geladen werden");
        }

    }

    // Getter-Methoden für die Images

    // Figuren
    public Image getSpielerImg() {
        return spielerImg;
    }

    public Image getSpielerKrugImg() {
        return spielerKrugImg;
    }

    public Image getJoschuaImg() {
        return joschuaImg;
    }

    // Items
    public Image getKrugImg() {
        return krugImg;
    }

    // Maps
    public Image getHauptraumImg() {
        return hauptraumImg;
    }

    public Image getDruckplattenraumImg() {
        return druckplattenraumImg;
    }

    public Image getSchabloneImg() {
        return schabloneImg;
    }

    public Image getSignalImg() {
        return signalImg;
    }

    public Image getDarkroomImg() {
        return darkroomImg;
    }

    public Image getDarkroomFreiImg() {
        return darkroomFreiImg;
    }

    public Image getDarkroomSchalterImg() {
        return darkroomSchalterImg;
    }

    public Image getDarkroomSchalterUmgelegtImg() {
        return darkroomSchalterUmgelegtImg;
    }

    public Image getOutsideImg() {
        return outsideImg;
    }
}

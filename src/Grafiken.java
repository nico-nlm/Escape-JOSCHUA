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
    private String hebel;
    private String hebelUmgelegt;
    private Image krugImg;
    private Image hebelImg;
    private Image hebelUmgelegtImg;

    //Maps
    private String hauptraum;
    private String druckplattenraum;
    private String schablone;
    private String signal;
    private String test;
    private Image hauptraumImg;
    private Image druckplattenraumImg;
    private Image schabloneImg;
    private Image signalImg;
    private Image testImg;

    Grafiken() {
        //Figuren
        spieler = "src/img/figuren/spieler.png";
        spielerKrug = "src/img/figuren/spieler-krug.png";
        joschua =  "src/img/figuren/joschua.jpg";

        //Items
        krug = "src/img/items/krug.PNG";
        hebel = "src/img/items/hebel.png";
        hebelUmgelegt = "src/img/items/hebel-umgelegt.png";

        //Maps
        hauptraum = "src/img/maps/hauptraum.png";
        druckplattenraum = "src/img/maps/druckplattenraum.png";
        schablone = "src/img/maps/schablone.png";
        signal = "src/img/maps/signal.PNG";
        test = "src/img/maps/test.png";

        try {
            //Figuren
            spielerImg = ImageIO.read(new File(spieler));
            spielerKrugImg = ImageIO.read(new File(spielerKrug));
            joschuaImg = ImageIO.read(new File(joschua));

            //Items
            krugImg = ImageIO.read(new File(krug));
            hebelImg = ImageIO.read(new File(hebel));
            hebelUmgelegtImg = ImageIO.read(new File(hebelUmgelegt));

            //Maps
            hauptraumImg = ImageIO.read(new File(hauptraum));
            druckplattenraumImg = ImageIO.read(new File(druckplattenraum));
            schabloneImg = ImageIO.read(new File(schablone));
            signalImg = ImageIO.read(new File(signal));
            testImg = ImageIO.read(new File(test));
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

    public Image getHebelImg() {
        return hebelImg;
    }

    public Image getHebelUmgelegtImg() {
        return hebelUmgelegtImg;
    }

    public Image getTestImg() {
        return testImg;
    }



}

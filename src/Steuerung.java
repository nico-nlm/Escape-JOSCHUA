package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Steuerung implements KeyListener {
    private ZeichenFlaeche14 zf;
    private Grafiken g;
    private Map m;
    private Spielfigur spieler;
    private Npc npc;
    private Krug krug1;
    private Krug krug2;
    private Krug krug3;
    private Krug krug4;
    private Schablone schablone;
    private Hebel hebel;
    private Signal signalDruckplatte;
    private Signal signalDarkroom;
    private int groesse = 700;
    private int[][] aktuellesLevel;
    private Image aktuellesLevelImg;
    private int eingesammelterKrug;
    private boolean druckplatte1;
    private boolean druckplatte2;
    private boolean hebelUmgelegt;


    public Steuerung() {
        zf = new ZeichenFlaeche14();
        g = new Grafiken();
        m = new Map();
        spieler = new Spielfigur(m.getHauptraumStartX(), m.getHauptraumStartY(), 35, 35, g.getSpielerImg(), 2);
        npc = new Npc(m.getNpcStartX(), m.getNpcStartY(), 35, 35, g.getJoschuaImg(), 3);
        krug1 = new Krug(6, 4, 35, 35, g.getKrugImg(), 4);
        krug2 = new Krug(7, 4, 35, 35, g.getKrugImg(), 5);
        krug3 = new Krug(12, 4, 35, 35, g.getKrugImg(), 6);
        krug4 = new Krug(13, 4, 35, 35, g.getKrugImg(), 7);
        schablone = new Schablone(-193, -298, groesse*2, groesse*2, g.getSchabloneImg(), 10);
        hebel = new Hebel(m.getDarkroomHebelY(), m.getDarkroomHebelY(), 35, 35, g.getHebelImg(), 11);
        aktuellesLevel = m.hauptraum;
        aktuellesLevelImg = g.getHauptraumImg();
        eingesammelterKrug = 0;
        druckplatte1 = false;
        druckplatte2 = false;

    }

    public void starten() {
        zf.macheZeichenFlaecheSichtbar(groesse, groesse, g);
        zf.addKeyListener(this);
        zf.requestFocus();
        zeichneSpielflaeche();
        spieler.zeichnen(zf);
    }

    public void zeichneSpielflaeche() {
        zf.setzeBild(1, aktuellesLevelImg, 0, 0, groesse, groesse);
    }

    public void checkKrug() {
        if (!druckplatte1 || !druckplatte2) {
            if (eingesammelterKrug == 0) {
                switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
                    case 6, 7, 8, 9:
                        zf.setzeText(8, "Drücke 'E' um den Krug aufzuheben", 210, 250, 18, Color.BLACK);
                        break;
                    default:
                        zf.loeschen(8);
                }
            } else if (aktuellesLevel[spieler.getY()][spieler.getX()] == 0) {
                zf.loeschen(8);
                zf.setzeText(8, "Drücke 'E' um den Krug abzulegen", 210, 250, 18, Color.BLACK);
            } else zf.loeschen(8);
        }
    }

    public void checkNpc() {
        if (spieler.getX() == npc.getX() && spieler.getY() == npc.getY()) {
            zf.loeschen(spieler.getId());
            spieler.setX(m.getDruckplatteStartX());
            spieler.setY(m.getDruckplatteStartY());
            spieler.zeichnen(zf);
            System.out.println("erkannt");
        }
    }

    public void checkLevel() {
        if (eingesammelterKrug == 0) {
            switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
                case 2:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    zf.loeschen(npc.getId());
                    zf.loeschen(4);
                    zf.loeschen(5);
                    zf.loeschen(6);
                    zf.loeschen(7);
                    zf.loeschen(8);
                    zf.loeschen(10);
                    eingesammelterKrug = 0;
                    aktuellesLevel = m.hauptraum;
                    aktuellesLevelImg = g.getHauptraumImg();
                    schablone.setX(-193);
                    schablone.setY(-298);
                    spieler.setGrafik(g.getSpielerImg());
                    spieler.setX(m.getHauptraumStartX());
                    spieler.setY(m.getHauptraumStartY());
                    zeichneSpielflaeche();
                    if (druckplatte1 && druckplatte2) signalDruckplatte = new Signal(7, 8, 35, 35, g.getSignalImg(), 9);
                    if (hebelUmgelegt) signalDarkroom = new Signal(13,  8, 35, 35, g.getSignalImg(), 9);
                    try {
                        signalDruckplatte.zeichnen(zf);
                        signalDarkroom.zeichnen(zf);
                    } catch (Exception e) {
                        System.err.println("Eines der Rätsel wurde noch nicht gelöst");
                    }
                    spieler.zeichnen(zf);
                    break;
                case 3:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    aktuellesLevel = m.druckplatte;
                    aktuellesLevelImg = g.getDruckplattenraumImg();
                    spieler.setGrafik(g.getSpielerImg());
                    spieler.setX(m.getDruckplatteStartX());
                    spieler.setY(m.getDruckplatteStartY());
                    m.setHauptraumStartX(9);
                    m.setHauptraumStartY(4);
                    zeichneSpielflaeche();
                    krug1.zeichnen(zf);
                    krug2.zeichnen(zf);
                    krug3.zeichnen(zf);
                    krug4.zeichnen(zf);
                    npc.zeichnen(zf);
                    spieler.zeichnen(zf);
                    break;
                case 4:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    aktuellesLevel = m.darkroom;
                    spieler.setX(m.getDarkroomStartX());
                    spieler.setY(m.getDarkroomStartY());
                    m.setHauptraumStartX(14);
                    m.setHauptraumStartY(11);
                    zeichneSpielflaeche();
                    spieler.zeichnen(zf);
                    hebel.zeichnen(zf);
                    schablone.zeichneSchablone(zf);
                    break;
                default:
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (aktuellesLevel[spieler.getY()-1][spieler.getX()] != 1) {
                spieler.bewegen(0, -1, zf);
                if (aktuellesLevel == m.druckplatte && (!druckplatte1 || !druckplatte2)) npc.bewegen(aktuellesLevel, zf);
                if (aktuellesLevel == m.darkroom) schablone.bewegen(0, -spieler.getHoehe(), zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (aktuellesLevel[spieler.getY()+1][spieler.getX()] != 1) {
                spieler.bewegen(0, 1, zf);
                if (aktuellesLevel == m.druckplatte && (!druckplatte1 || !druckplatte2)) npc.bewegen(aktuellesLevel, zf);
                if (aktuellesLevel == m.darkroom) schablone.bewegen(0, spieler.getHoehe(), zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()-1] != 1) {
                spieler.bewegen(-1, 0, zf);
                if (aktuellesLevel == m.druckplatte && (!druckplatte1 || !druckplatte2)) npc.bewegen(aktuellesLevel, zf);
                if (aktuellesLevel == m.darkroom) schablone.bewegen(-spieler.getBreite(), 0, zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()+1] != 1) {
                spieler.bewegen(1, 0, zf);
                if (aktuellesLevel == m.druckplatte && (!druckplatte1 || !druckplatte2)) npc.bewegen(aktuellesLevel, zf);
                if (aktuellesLevel == m.darkroom) schablone.bewegen(spieler.getBreite(), 0, zf);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (!druckplatte1 || !druckplatte2) {
                if (eingesammelterKrug == 0) {
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 6) {
                        zf.loeschen(krug1.getId());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 1;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setGrafik(g.getSpielerKrugImg());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 7) {
                        zf.loeschen(krug2.getId());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 2;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setGrafik(g.getSpielerKrugImg());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 8) {
                        zf.loeschen(krug3.getId());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 3;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setGrafik(g.getSpielerKrugImg());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 9) {
                        zf.loeschen(krug4.getId());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 4;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setGrafik(g.getSpielerKrugImg());
                        spieler.zeichnen(zf);
                    }
                    if (spieler.getX() == m.getDruckPlatteX1() && spieler.getY() == m.getDruckplatteY1()) druckplatte1 = false;
                    if (spieler.getX() == m.getDruckPlatteX2() && spieler.getY() == m.getDruckplatteY2()) druckplatte2 = false;
                } else if (aktuellesLevel[spieler.getY()][spieler.getX()] == 0){
                    switch (eingesammelterKrug) {
                        case 1:
                            krug1.setX(spieler.getX());
                            krug1.setY(spieler.getY());
                            krug1.zeichnen(zf);
                            m.druckplatte[spieler.getY()][spieler.getX()] = 6;
                            zf.loeschen(8);
                            break;
                        case 2:
                            krug2.setX(spieler.getX());
                            krug2.setY(spieler.getY());
                            krug2.zeichnen(zf);
                            m.druckplatte[spieler.getY()][spieler.getX()] = 7;
                            zf.loeschen(8);
                            break;
                        case 3:
                            krug3.setX(spieler.getX());
                            krug3.setY(spieler.getY());
                            krug3.zeichnen(zf);
                            m.druckplatte[spieler.getY()][spieler.getX()] = 8;
                            zf.loeschen(8);
                            break;
                        case 4:
                            krug4.setX(spieler.getX());
                            krug4.setY(spieler.getY());
                            krug4.zeichnen(zf);
                            m.druckplatte[spieler.getY()][spieler.getX()] = 9;
                            zf.loeschen(8);
                            break;
                        default:
                    }
                    eingesammelterKrug = 0;
                    zf.loeschen(spieler.getId());
                    spieler.setGrafik(g.getSpielerImg());
                    spieler.zeichnen(zf);
                    if (spieler.getX() == m.getDruckPlatteX1() && spieler.getY() == m.getDruckplatteY1()) druckplatte1 = true;
                    if (spieler.getX() == m.getDruckPlatteX2() && spieler.getY() == m.getDruckplatteY2()) druckplatte2 = true;
                    if (druckplatte1 && druckplatte2) {
                        zf.loeschen(8);
                        zf.setzeText(8, "Im Hauptraum ist etwas passiert", 220, 250, 18, Color.BLACK);
                    }
                }
            }
        }
        checkLevel();
        if (aktuellesLevel == m.druckplatte) {
            checkKrug();
            checkNpc();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

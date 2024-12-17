package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Steuerung implements KeyListener {
    private ZeichenFlaeche14 zf;
    private Spielfigur spieler;
    private Npc npc;
    private Krug krug1;
    private Krug krug2;
    private Krug krug3;
    private Krug krug4;
    private Map m;
    private Signal signalDruckplatte;
    private Signal signalDarkroom;
    private Signal signalJoschua;
    private int groesse = 700;
    private int[][] aktuellesLevel;
    private Image aktuellesLevelImg;
    private int eingesammelterKrug;
    private boolean druckplatte1;
    private boolean druckplatte2;


    public Steuerung() {
        zf = new ZeichenFlaeche14();
        m = new Map();
        spieler = new Spielfigur(8, 11, 35, 35, 2);
        npc = new Npc(6, 6, 35, 35, 3);
        krug1 = new Krug(6, 4, 35, 35, 4);
        krug2 = new Krug(7, 4, 35, 35, 5);
        krug3 = new Krug(12, 4, 35, 35, 6);
        krug4 = new Krug(13, 4, 35, 35, 7);
        aktuellesLevel = m.hauptraum;
        aktuellesLevelImg = m.getHauptraumImg();
        eingesammelterKrug = 0;
        druckplatte1 = false;
        druckplatte2 = false;

    }

    public void starten() {
        zf.macheZeichenFlaecheSichtbar(groesse, groesse);
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
                    eingesammelterKrug = 0;
                    aktuellesLevel = m.hauptraum;
                    aktuellesLevelImg = m.getHauptraumImg();
                    spieler.setAktuelleFigur(spieler.getFigur());
                    spieler.setX(m.getHauptraumStartX());
                    spieler.setY(m.getHauptraumStartY());
                    zeichneSpielflaeche();
                    if (druckplatte1 && druckplatte2) signalDruckplatte = new Signal(7, 8, 35, 35, 9);
                    try {
                        signalDruckplatte.zeichnen(zf);
                    } catch (Exception e) {
                        System.err.println("Druckplattenraum noch nicht gelöst");
                    }
                    spieler.zeichnen(zf);
                    break;
                case 3:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    aktuellesLevel = m.druckplatte;
                    aktuellesLevelImg = m.getDruckplattenImg();
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
                    aktuellesLevelImg = m.getTestImg();
                    spieler.setX(m.getTestStartX());
                    spieler.setY(m.getTestStartY());
                    m.setHauptraumStartX(9);
                    m.setHauptraumStartY(18);
                    zeichneSpielflaeche();
                    spieler.zeichnen(zf);
                    break;
                case 5:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    aktuellesLevelImg = m.getTestImg();
                    spieler.setX(m.getTestStartX());
                    spieler.setY(m.getTestStartY());
                    m.setHauptraumStartX(13);
                    m.setHauptraumStartY(11);
                    zeichneSpielflaeche();
                    spieler.zeichnen(zf);
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
                if (aktuellesLevel == m.druckplatte && !druckplatte1 || !druckplatte2) {
                    npc.bewegen(aktuellesLevel, zf);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (aktuellesLevel[spieler.getY()+1][spieler.getX()] != 1) {
                spieler.bewegen(0, 1, zf);
                if (aktuellesLevel == m.druckplatte && !druckplatte1 || !druckplatte2) {
                    npc.bewegen(aktuellesLevel, zf);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()-1] != 1) {
                spieler.bewegen(-1, 0, zf);
                if (aktuellesLevel == m.druckplatte && !druckplatte1 || !druckplatte2) {
                    npc.bewegen(aktuellesLevel, zf);
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()+1] != 1) {
                spieler.bewegen(1, 0, zf);
                if (aktuellesLevel == m.druckplatte && !druckplatte1 || !druckplatte2) {
                    npc.bewegen(aktuellesLevel, zf);
                }
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (!druckplatte1 || !druckplatte2) {
                if (eingesammelterKrug == 0) {
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 6) {
                        zf.loeschen(krug1.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 1;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 7) {
                        zf.loeschen(krug2.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 2;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 8) {
                        zf.loeschen(krug3.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 3;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        spieler.zeichnen(zf);
                    }
                    if (aktuellesLevel[spieler.getY()][spieler.getX()] == 9) {
                        zf.loeschen(krug4.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                        eingesammelterKrug = 4;
                        zf.loeschen(8);
                        zf.loeschen(spieler.getId());
                        spieler.setAktuelleFigur(spieler.getFigurKrug());
                        spieler.zeichnen(zf);
                    }
                    if (spieler.getX() == m.getDruckPlatteX1() && spieler.getY() == m.getDruckplatteY1()) druckplatte1 = false;
                    if (spieler.getX() == m.getDruckPlatteX2() && spieler.getY() == m.getDruckplatteY2()) druckplatte2 = false;
                } else if (eingesammelterKrug !=0 && aktuellesLevel[spieler.getY()][spieler.getX()] == 0){
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
                    spieler.setAktuelleFigur(spieler.getFigur());
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

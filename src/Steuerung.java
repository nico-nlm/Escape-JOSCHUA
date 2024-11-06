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
    private int groesse = 700;
    private int[][] aktuellesLevel;
    private Image aktuellesLevelImg;
    private int eingesammelterKrug;


    public Steuerung() {
        zf = new ZeichenFlaeche14();
        m = new Map();
        spieler = new Spielfigur(8, 11, 35, 35, 2);
        npc = new Npc(200, 200, 70, 70, 3);
        krug1 = new Krug(6, 4, 35, 35, 4);
        krug2 = new Krug(7, 4, 35, 35, 5);
        krug3 = new Krug(12, 4, 35, 35, 6);
        krug4 = new Krug(13, 4, 35, 35, 7);
        aktuellesLevel = m.hauptraum;
        aktuellesLevelImg = m.getHauptraumImg();
        eingesammelterKrug = 0;
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
        if (eingesammelterKrug == 0) {
            switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
                case 6, 7, 8, 9:
                    zf.setzeText(8, "Drücke 'E' um den Krug aufzuheben", 210, 250, 18, Color.BLACK);
                    break;
                default:
                    zf.loeschen(8);
            }
        } else {
            zf.loeschen(8);
            zf.setzeText(8, "Drücke 'E' um den Krug abzulegen", 210, 250, 18, Color.BLACK);
        }

    }

    public void checkLevel() {
        switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
            case 2:
                zf.loeschen(1);
                zf.loeschen(spieler.getId());
                aktuellesLevel = m.hauptraum;
                aktuellesLevelImg = m.getHauptraumImg();
                spieler.setX(m.getHauptraumStartX());
                spieler.setY(m.getHauptraumStartY());
                zeichneSpielflaeche();
                spieler.zeichnen(zf);
                break;
            case 3:
                zf.loeschen(1);
                zf.loeschen(spieler.getId());
                aktuellesLevel = m.druckplatte;
                aktuellesLevelImg = m.getDruckplattenImg();
                spieler.setX(m.getDruckplatteStartX());
                spieler.setY(m.getDruckplatteStartY());
                m.setHauptraumStartX(2);
                m.setHauptraumStartY(11);
                zeichneSpielflaeche();
                krug1.zeichnen(zf);
                krug2.zeichnen(zf);
                krug3.zeichnen(zf);
                krug4.zeichnen(zf);
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

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            if (aktuellesLevel[spieler.getY()-1][spieler.getX()] != 1) {
                spieler.bewegen(0, -1, zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            if (aktuellesLevel[spieler.getY()+1][spieler.getX()] != 1) {
                spieler.bewegen(0, 1, zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()-1] != 1) {
                spieler.bewegen(-1, 0, zf);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (aktuellesLevel[spieler.getY()][spieler.getX()+1] != 1) {
                spieler.bewegen(1, 0, zf);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            if (eingesammelterKrug == 0) {
                if (aktuellesLevel[spieler.getY()][spieler.getX()] == 6) {
                    //zf.loeschen(spieler.getId());
                    zf.loeschen(krug1.getId());
                    spieler.setFigur(spieler.getFigurKrug());
                    m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                    eingesammelterKrug = 1;
                    zf.loeschen(8);
                }
                if (aktuellesLevel[spieler.getY()][spieler.getX()] == 7) {
                    //zf.loeschen(spieler.getId());
                    zf.loeschen(krug2.getId());
                    spieler.setFigur(spieler.getFigurKrug());
                    m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                    eingesammelterKrug = 2;
                    zf.loeschen(8);
                }
                if (aktuellesLevel[spieler.getY()][spieler.getX()] == 8) {
                    //zf.loeschen(spieler.getId());
                    zf.loeschen(krug3.getId());
                    spieler.setFigur(spieler.getFigurKrug());
                    m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                    eingesammelterKrug = 3;
                    zf.loeschen(8);
                }
                if (aktuellesLevel[spieler.getY()][spieler.getX()] == 9) {
                    //zf.loeschen(spieler.getId());
                    zf.loeschen(krug4.getId());
                    spieler.setFigur(spieler.getFigurKrug());
                    m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                    eingesammelterKrug = 4;
                    zf.loeschen(8);
                }
            } else {
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
            }
        }
        checkLevel();
        checkKrug();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

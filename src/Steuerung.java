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
    private Map m;
    private int groesse = 700;
    private int[][] aktuellesLevel;
    private int aktuellesLevelInt;
    private Image aktuellesLevelImg;
    private int naechstesLevel;

    public Steuerung() {
        zf = new ZeichenFlaeche14();
        m = new Map();
        spieler = new Spielfigur(8, 11, 35, 35, 2);
        npc = new Npc(200, 200, 70, 70, 3);
        aktuellesLevel = m.hauptraum;
        aktuellesLevelInt = 1;
        aktuellesLevelImg = m.getHauptraumImg();
        naechstesLevel = 2;
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

    public void checkLevel() {
        switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
            case 4:
                zf.loeschen(1);
                zf.loeschen(spieler.getId());
                aktuellesLevel = m.hauptraum;
                aktuellesLevelImg = m.getHauptraumImg();
                spieler.setX(m.getHauptraumStartX());
                spieler.setY(m.getHauptraumStartY());
                zeichneSpielflaeche();
                spieler.zeichnen(zf);
                break;
            case 5:
                zf.loeschen(1);
                zf.loeschen(spieler.getId());
                aktuellesLevel = m.druckplatte;
                aktuellesLevelImg = m.getDruckplattenImg();
                spieler.setX(m.getDruckplatteStartX());
                spieler.setY(m.getDruckplatteStartY());
                m.setHauptraumStartX(2);
                m.setHauptraumStartY(11);
                zeichneSpielflaeche();
                spieler.zeichnen(zf);
                break;
            case 6:
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
            case 7:
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
        checkLevel();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

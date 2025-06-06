package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Steuerung implements KeyListener {
    private ZeichenFlaeche14 zf;
    private Grafiken g;
    private Spielfigur spieler;
    private Npc npc;
    private NpcTimer npcTimer;
    private Krug krug1;
    private Krug krug2;
    private Krug krug3;
    private Krug krug4;
    private Hebel darkroomHebel;
    private Schablone schablone;
    private Map m;
    private Signal signalDruckplatte;
    private Signal signalDarkroom;
    private int groesse = 700;
    private int[][] aktuellesLevel;
    private Image aktuellesLevelImg;
    private int eingesammelterKrug;
    private boolean druckplatte1;
    private boolean druckplatte2;
    private boolean darkroomHebelUmgelegt;


    public Steuerung() {
        zf = new ZeichenFlaeche14();
        g = new Grafiken();
        m = new Map();
        spieler = new Spielfigur(m.getHauptraumStartX(), m.getHauptraumStartY(), 35, 35, g.getSpielerImg(), 8);
        npc = new Npc(m.getNpcStartX(), m.getNpcStartY(), 35, 35, g.getJoschuaImg(), 9);
        npcTimer = new NpcTimer(zf);
        krug1 = new Krug(6, 4, 35, 35, g.getKrugImg(), 2);
        krug2 = new Krug(7, 4, 35, 35, g.getKrugImg(), 3);
        krug3 = new Krug(12, 4, 35, 35, g.getKrugImg(), 4);
        krug4 = new Krug(13, 4, 35, 35, g.getKrugImg(), 5);
        darkroomHebel = new Hebel(m.getDarkroomSchalterX(), m.getDarkroomSchalterY(), 35, 35, g.getDarkroomSchalterImg(), 6);
        signalDarkroom = new Signal(11, 13, 35, 35, g.getSignalImg(), 7);
        signalDruckplatte = new Signal(11, 9, 35, 35, g.getSignalImg(), 7);
        schablone = new Schablone(-123, -368, groesse*2, groesse*2, g.getSchabloneImg(), 10);
        aktuellesLevel = m.hauptraum;
        aktuellesLevelImg = g.getHauptraumImg();
        eingesammelterKrug = 0;
        druckplatte1 = false;
        druckplatte2 = false;
        darkroomHebelUmgelegt = false;


    }

    public void starten() {
        zf.macheZeichenFlaecheSichtbar(groesse, groesse, g);
        zf.addKeyListener(this);
        zf.requestFocus();
        zeichneSpielflaeche();
        spieler.zeichnen(zf);
        npcTimer.run();
    }

    public void zeichneSpielflaeche() {
        zf.setzeBild(1, aktuellesLevelImg, 0, 0, groesse, groesse);
    }

    public void checkPosition() {
        if (aktuellesLevel == m.hauptraum && druckplatte1 && druckplatte2 && darkroomHebelUmgelegt) {
            m.hauptraum[19][9] = 5;
            zf.setzeText(11, "Du kannst den Dungeon nun", 210, 390, 18, Color.BLACK);
            zf.setzeText(11, "durch die untere Tür verlassen", 210, 420, 18, Color.BLACK);
        } else zf.loeschen(11);
        if ((!druckplatte1 || !druckplatte2) && aktuellesLevel == m.druckplatte) {
            if (eingesammelterKrug == 0) {
                switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
                    case 6, 7, 8, 9:
                        zf.setzeText(11, "Drücke 'E' um den Krug aufzuheben", 210, 250, 18, Color.BLACK);
                        break;
                    default:
                        zf.loeschen(11);
                }
            } else if (aktuellesLevel[spieler.getY()][spieler.getX()] == 0) {
                zf.loeschen(11);
                zf.setzeText(11, "Drücke 'E' um den Krug abzulegen", 210, 250, 18, Color.BLACK);
            } else zf.loeschen(11);
        }
        if (aktuellesLevel == m.darkroom && !darkroomHebelUmgelegt) {
            if (spieler.getX() == m.getDarkroomStachelSchalterX() && spieler.getY() == m.getDarkroomStachelSchalterY()) {
                zf.setzeText(11, "Dieser Schalter scheint defekt zu sein.", 210, 250, 18, Color.WHITE);
                zf.setzeText(11, "Versuche es mit dem anderen!", 210, 280, 18, Color.WHITE);
            } else if (spieler.getX() == m.getDarkroomStachelSchalterX() && spieler.getY() == m.getDarkroomStachelSchalterY2()) {
                zf.setzeText(11, "Drücke 'E' um die Stacheln zu deaktivieren!", 190, 250, 18, Color.WHITE);
            }
        }

        if (aktuellesLevel == m.darkroomFrei && !darkroomHebelUmgelegt) {
            if (spieler.getX() == m.getDarkroomSchalterX() && spieler.getY() == m.getDarkroomSchalterY()) {
                zf.setzeText(11, "Du hast noch einen Schalter gefunden!", 210, 250, 18, Color.WHITE);
                zf.setzeText(11, "Drücke 'E' um ihn zu betätigen", 210, 280, 18, Color.WHITE);
            } else zf.loeschen(11);
        }
    }

    public void checkNpc() {
        if (spieler.getX() == npc.getX() && spieler.getY() == npc.getY()) {
            zf.loeschen(spieler.getId());
            spieler.setX(m.getDruckplatteStartX());
            spieler.setY(m.getDruckplatteStartY());
            spieler.zeichnen(zf);
        }
    }

    public void checkLevel() {
        if (eingesammelterKrug == 0) {
            switch (aktuellesLevel[spieler.getY()][spieler.getX()]) {
                case 2:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    zf.loeschen(npc.getId());
                    zf.loeschen(krug1.getId());
                    zf.loeschen(krug2.getId());
                    zf.loeschen(krug3.getId());
                    zf.loeschen(krug4.getId());
                    zf.loeschen(darkroomHebel.getId());
                    zf.loeschen(schablone.getId());
                    eingesammelterKrug = 0;
                    aktuellesLevel = m.hauptraum;
                    aktuellesLevelImg = g.getHauptraumImg();
                    schablone.setX(-123);
                    schablone.setY(-368);
                    spieler.setGrafik(g.getSpielerImg());
                    spieler.setX(m.getHauptraumStartX());
                    spieler.setY(m.getHauptraumStartY());
                    zeichneSpielflaeche();
                    if (druckplatte1 && druckplatte2) signalDruckplatte.zeichnen(zf);
                    if (darkroomHebelUmgelegt) signalDarkroom.zeichnen(zf);
                    spieler.zeichnen(zf);
                    break;
                case 3:
                    zf.loeschen(1);
                    zf.loeschen(spieler.getId());
                    zf.loeschen(signalDruckplatte.getId());
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
                    zf.loeschen(signalDruckplatte.getId());
                    zf.loeschen(spieler.getId());
                    aktuellesLevel = m.darkroom;
                    aktuellesLevelImg = g.getDarkroomImg();
                    spieler.setX(m.getDarkroomStartX());
                    spieler.setY(m.getDarkroomStartY());
                    m.setHauptraumStartX(14);
                    m.setHauptraumStartY(11);
                    zeichneSpielflaeche();
                    darkroomHebel.zeichnen(zf);
                    spieler.zeichnen(zf);
                    if (!darkroomHebelUmgelegt) schablone.zeichneSchablone(zf);
                    break;
                case 5:
                    zf.loeschen(1);
                    zf.loeschen(signalDruckplatte.getId());
                    zf.loeschen(spieler.getId());
                    zf.loeschen(11);
                    aktuellesLevel = m.outside;
                    aktuellesLevelImg = g.getOutsideImg();
                    spieler.setX(10);
                    spieler.setY(18);
                    zeichneSpielflaeche();
                    spieler.zeichnen(zf);
                    zf.setzeText(11, "Du bist entkommen", 200, 250, 32, Color.WHITE);
                default:
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (aktuellesLevel != m.outside) {
            if (e.getKeyCode() == KeyEvent.VK_W) {
                if (aktuellesLevel[spieler.getY()-1][spieler.getX()] != 1) {
                    spieler.bewegen(0, -1, zf);
                    if (aktuellesLevel == m.darkroom || aktuellesLevel ==  m.darkroomFrei) schablone.bewegen(0, -spieler.getHoehe(), zf);
                }
                checkLevel();
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                if (aktuellesLevel[spieler.getY()+1][spieler.getX()] != 1) {
                    spieler.bewegen(0, 1, zf);
                    if (aktuellesLevel == m.darkroom || aktuellesLevel ==  m.darkroomFrei) schablone.bewegen(0, spieler.getHoehe(), zf);
                }
                checkLevel();
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (aktuellesLevel[spieler.getY()][spieler.getX()-1] != 1) {
                    spieler.bewegen(-1, 0, zf);
                    if (aktuellesLevel == m.darkroom || aktuellesLevel ==  m.darkroomFrei) schablone.bewegen(-spieler.getBreite(), 0, zf);
                }
                checkLevel();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (aktuellesLevel[spieler.getY()][spieler.getX()+1] != 1) {
                    spieler.bewegen(1, 0, zf);
                    if (aktuellesLevel == m.darkroom || aktuellesLevel ==  m.darkroomFrei) schablone.bewegen(spieler.getBreite(), 0, zf);
                }
                checkLevel();
            }

            if (e.getKeyCode() == KeyEvent.VK_E) {
                if (aktuellesLevel == m.druckplatte) {
                    if (!druckplatte1 || !druckplatte2) {
                        if (eingesammelterKrug == 0) {
                            if (aktuellesLevel[spieler.getY()][spieler.getX()] == 6) {
                                zf.loeschen(krug1.getId());
                                m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                                eingesammelterKrug = 1;
                                zf.loeschen(11);
                                zf.loeschen(spieler.getId());
                                spieler.setGrafik(g.getSpielerKrugImg());
                                spieler.zeichnen(zf);
                            }
                            if (aktuellesLevel[spieler.getY()][spieler.getX()] == 7) {
                                zf.loeschen(krug2.getId());
                                m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                                eingesammelterKrug = 2;
                                zf.loeschen(11);
                                zf.loeschen(spieler.getId());
                                spieler.setGrafik(g.getSpielerKrugImg());
                                spieler.zeichnen(zf);
                            }
                            if (aktuellesLevel[spieler.getY()][spieler.getX()] == 8) {
                                zf.loeschen(krug3.getId());
                                m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                                eingesammelterKrug = 3;
                                zf.loeschen(11);
                                zf.loeschen(spieler.getId());
                                spieler.setGrafik(g.getSpielerKrugImg());
                                spieler.zeichnen(zf);
                            }
                            if (aktuellesLevel[spieler.getY()][spieler.getX()] == 9) {
                                zf.loeschen(krug4.getId());
                                m.druckplatte[spieler.getY()][spieler.getX()] = 0;
                                eingesammelterKrug = 4;
                                zf.loeschen(11);
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
                                    zf.loeschen(11);
                                    break;
                                case 2:
                                    krug2.setX(spieler.getX());
                                    krug2.setY(spieler.getY());
                                    krug2.zeichnen(zf);
                                    m.druckplatte[spieler.getY()][spieler.getX()] = 7;
                                    zf.loeschen(11);
                                    break;
                                case 3:
                                    krug3.setX(spieler.getX());
                                    krug3.setY(spieler.getY());
                                    krug3.zeichnen(zf);
                                    m.druckplatte[spieler.getY()][spieler.getX()] = 8;
                                    zf.loeschen(11);
                                    break;
                                case 4:
                                    krug4.setX(spieler.getX());
                                    krug4.setY(spieler.getY());
                                    krug4.zeichnen(zf);
                                    m.druckplatte[spieler.getY()][spieler.getX()] = 9;
                                    zf.loeschen(11);
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
                                zf.loeschen(11);
                                zf.setzeText(11, "Im Hauptraum ist etwas passiert", 220, 250, 18, Color.BLACK);
                            }
                        }
                    }
                }
                if (aktuellesLevel == m.darkroom && !darkroomHebelUmgelegt) {
                    if (spieler.getX() == m.getDarkroomStachelSchalterX() && spieler.getY() == m.getDarkroomStachelSchalterY2()) {
                        aktuellesLevel = m.darkroomFrei;
                        zf.loeschen(1);
                        aktuellesLevelImg = g.getDarkroomFreiImg();
                        zeichneSpielflaeche();
                        zf.loeschen(11);
                    }
                }

                if (aktuellesLevel == m.darkroomFrei && !darkroomHebelUmgelegt) {
                    if (spieler.getX() == m.getDarkroomSchalterX() && spieler.getY() == m.getDarkroomSchalterY()) {
                        zf.loeschen(darkroomHebel.getId());
                        zf.loeschen(schablone.getId());
                        zf.loeschen(11);
                        darkroomHebel.setGrafik(g.getDarkroomSchalterUmgelegtImg());
                        zeichneSpielflaeche();
                        darkroomHebel.zeichnen(zf);
                        darkroomHebelUmgelegt = true;
                        zf.setzeText(11, "Im Hauptraum ist etwas passiert", 220, 250, 18, Color.BLACK);
                    }
                }
            }
            checkPosition();
            if (aktuellesLevel == m.druckplatte) {
                checkNpc();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    class NpcTimer implements Runnable {
        private ZeichenFlaeche14 zf;

        NpcTimer(ZeichenFlaeche14 zf) {
            this.zf = zf;
        }

        @Override
        public void run() {
            try {
                while (1==1) {
                    if (aktuellesLevel == m.druckplatte && (!druckplatte1 || !druckplatte2)) {
                        npc.bewegen(m.druckplatte, zf);
                        checkNpc();
                    }
                    Thread.sleep(200);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

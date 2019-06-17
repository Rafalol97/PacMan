package rafalwisnia;

import rafalwisnia.Entity.Pacmann;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

class MenucanvasUP extends JPanel {
    private int coSto = 0;
    public static boolean animacja = true;
    private Integer pozycjaX = 60, pozycjaY = 20, kierunek = 3, klatka = 0, kierunekDuszka = 3, klatkaDuszka = 0;
    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/title.png");
    Image title2 = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/menuUp.png");
    Image title3 = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/podTytulem.png");

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
                            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
                            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
                            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    Sprite[][][] duszki = {{{Sprite.ghost_1_1, Sprite.ghost_1_2},{Sprite.ghost_1_3, Sprite.ghost_1_4}, {Sprite.ghost_1_5, Sprite.ghost_1_6}, {Sprite.ghost_1_7, Sprite.ghost_1_8}},
            {{Sprite.ghost_2_1, Sprite.ghost_2_2}, {Sprite.ghost_2_3, Sprite.ghost_2_4}, {Sprite.ghost_2_5, Sprite.ghost_2_6}, {Sprite.ghost_2_7, Sprite.ghost_2_8}},
            {{Sprite.ghost_3_1, Sprite.ghost_3_2}, {Sprite.ghost_3_3, Sprite.ghost_3_4}, {Sprite.ghost_3_5, Sprite.ghost_3_6}, {Sprite.ghost_3_7, Sprite.ghost_3_8}},
            {{Sprite.ghost_4_1, Sprite.ghost_4_2}, {Sprite.ghost_4_3, Sprite.ghost_4_4}, {Sprite.ghost_4_5, Sprite.ghost_4_6}, {Sprite.ghost_4_7, Sprite.ghost_4_8}}};

    private BufferedImage pacmanImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pacmanPixels = ((DataBufferInt)pacmanImage.getRaster().getDataBuffer()).getData();

    private BufferedImage duszekImage1 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels1 = ((DataBufferInt)duszekImage1.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels2 = ((DataBufferInt)duszekImage2.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage3 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels3 = ((DataBufferInt)duszekImage3.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage4 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels4 = ((DataBufferInt)duszekImage4.getRaster().getDataBuffer()).getData();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 100, this);
        g.drawImage(title2, 0, 0, this);
        g.drawImage(title3, 0, 319, this);

        if(animacja) {
            for(int i = 0; i < 2500; i++) {
                pacmanPixels[i] = pacman[kierunek][klatka].pixels[i];
                duszekPixels1[i] = duszki[0][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels2[i] = duszki[1][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels3[i] = duszki[2][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels4[i] = duszki[3][kierunekDuszka][klatkaDuszka].pixels[i];
            }

            g.drawImage(pacmanImage, pozycjaX, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage1, pozycjaX + 100, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage2, pozycjaX + 170, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage3, pozycjaX + 240, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage4, pozycjaX + 310, pozycjaY, 50, 50, null);
        }
    }

    public void animacja() {
        if (animacja) {
            if (pozycjaX > -361) {
                pozycjaX--;
                if (pozycjaX == 50) MenucanvasLEFT.animacja = true;
                klatka = coSto == 10 ? klatka + 1 : klatka;
                klatka = klatka < 4 ? klatka : 0;
                klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                coSto = coSto > 10 ? 0 : coSto + 1;


                repaint();
            } else {
                pozycjaX = 440;
                klatka = 0;
                animacja = false;
            }
        }
    }
}

class MenucanvasDOWN extends JPanel {
    private int coSto = 0;
    public static boolean animacja = false;
    private Integer pozycjaX = -50, pozycjaY = 30, kierunek = 1, klatka = 0, kierunekDuszka = 0, klatkaDuszka = 0;
    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/menuDown.png");

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    Sprite[][][] duszki = {{{Sprite.ghost_1_1, Sprite.ghost_1_2},{Sprite.ghost_1_3, Sprite.ghost_1_4}, {Sprite.ghost_1_5, Sprite.ghost_1_6}, {Sprite.ghost_1_7, Sprite.ghost_1_8}},
            {{Sprite.ghost_2_1, Sprite.ghost_2_2}, {Sprite.ghost_2_3, Sprite.ghost_2_4}, {Sprite.ghost_2_5, Sprite.ghost_2_6}, {Sprite.ghost_2_7, Sprite.ghost_2_8}},
            {{Sprite.ghost_3_1, Sprite.ghost_3_2}, {Sprite.ghost_3_3, Sprite.ghost_3_4}, {Sprite.ghost_3_5, Sprite.ghost_3_6}, {Sprite.ghost_3_7, Sprite.ghost_3_8}},
            {{Sprite.ghost_4_1, Sprite.ghost_4_2}, {Sprite.ghost_4_3, Sprite.ghost_4_4}, {Sprite.ghost_4_5, Sprite.ghost_4_6}, {Sprite.ghost_4_7, Sprite.ghost_4_8}}};

    private BufferedImage pacmanImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pacmanPixels = ((DataBufferInt)pacmanImage.getRaster().getDataBuffer()).getData();

    private BufferedImage duszekImage1 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels1 = ((DataBufferInt)duszekImage1.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels2 = ((DataBufferInt)duszekImage2.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage3 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels3 = ((DataBufferInt)duszekImage3.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage4 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels4 = ((DataBufferInt)duszekImage4.getRaster().getDataBuffer()).getData();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 20, this);

        if(animacja) {
            for(int i = 0; i < 2500; i++) {
                pacmanPixels[i] = pacman[kierunek][klatka].pixels[i];
                duszekPixels1[i] = duszki[0][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels2[i] = duszki[1][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels3[i] = duszki[2][kierunekDuszka][klatkaDuszka].pixels[i];
                duszekPixels4[i] = duszki[3][kierunekDuszka][klatkaDuszka].pixels[i];
            }

            g.drawImage(pacmanImage, pozycjaX, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage1, pozycjaX - 100, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage2, pozycjaX - 170, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage3, pozycjaX - 240, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage4, pozycjaX - 310, pozycjaY, 50, 50, null);
        }
    }

    public void animacja() {
        if (animacja) {
            if (pozycjaX < 801) {
                pozycjaX++;
                if (pozycjaX == 390) MenucanvasRIGHT.animacja = true;
                klatka = coSto == 10 ? klatka + 1 : klatka;
                klatka = klatka < 4 ? klatka : 0;
                klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                coSto = coSto > 10 ? 0 : coSto + 1;

                repaint();
            } else {
                pozycjaX = -50;
                klatka = 0;
                animacja = false;
            }
        }
    }
}

class MenucanvasLEFT extends JPanel {
    private int coSto = 0;
    private boolean etap1 = true, etap1duszka1 = true, etap1duszka2 = true, etap1duszka3 = true, etap1duszka4 = true;
    private boolean blokadaPacman = true, blokadaDuszka1 = true, blokadaDuszka2 = true, blokadaDuszka3 = true;
    public static boolean animacja = false;
    private Integer pozycjaX = 130, pozycjaY = 20, kierunek = 3, klatka = 0, klatkaDuszka = 0;
    private Integer pozycjaXduszka1 = 230,  pozycjaXduszka2 = 300,  pozycjaXduszka3 = 370,  pozycjaXduszka4 = 440,
                    pozycjaYduszka1 = 20,   pozycjaYduszka2 = 20,   pozycjaYduszka3 = 20,   pozycjaYduszka4 = 20,
                    kierunekDuszka1 = 3,    kierunekDuszka2 = 3,    kierunekDuszka3 = 3,    kierunekDuszka4 = 3;

    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/menuLeft.png");
    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    Sprite[][][] duszki = {{{Sprite.ghost_1_1, Sprite.ghost_1_2},{Sprite.ghost_1_3, Sprite.ghost_1_4}, {Sprite.ghost_1_5, Sprite.ghost_1_6}, {Sprite.ghost_1_7, Sprite.ghost_1_8}},
            {{Sprite.ghost_2_1, Sprite.ghost_2_2}, {Sprite.ghost_2_3, Sprite.ghost_2_4}, {Sprite.ghost_2_5, Sprite.ghost_2_6}, {Sprite.ghost_2_7, Sprite.ghost_2_8}},
            {{Sprite.ghost_3_1, Sprite.ghost_3_2}, {Sprite.ghost_3_3, Sprite.ghost_3_4}, {Sprite.ghost_3_5, Sprite.ghost_3_6}, {Sprite.ghost_3_7, Sprite.ghost_3_8}},
            {{Sprite.ghost_4_1, Sprite.ghost_4_2}, {Sprite.ghost_4_3, Sprite.ghost_4_4}, {Sprite.ghost_4_5, Sprite.ghost_4_6}, {Sprite.ghost_4_7, Sprite.ghost_4_8}}};

    private BufferedImage pacmanImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pacmanPixels = ((DataBufferInt)pacmanImage.getRaster().getDataBuffer()).getData();

    private BufferedImage duszekImage1 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels1 = ((DataBufferInt)duszekImage1.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels2 = ((DataBufferInt)duszekImage2.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage3 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels3 = ((DataBufferInt)duszekImage3.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage4 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels4 = ((DataBufferInt)duszekImage4.getRaster().getDataBuffer()).getData();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 0, this);

        if(animacja) {
            for(int i = 0; i < 2500; i++) {
                pacmanPixels[i] = pacman[kierunek][klatka].pixels[i];
                duszekPixels1[i] = duszki[0][kierunekDuszka1][klatkaDuszka].pixels[i];
                duszekPixels2[i] = duszki[1][kierunekDuszka2][klatkaDuszka].pixels[i];
                duszekPixels3[i] = duszki[2][kierunekDuszka3][klatkaDuszka].pixels[i];
                duszekPixels4[i] = duszki[3][kierunekDuszka4][klatkaDuszka].pixels[i];
            }

            g.drawImage(pacmanImage, pozycjaX, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage1, pozycjaXduszka1, pozycjaYduszka1, 50, 50, null);
            g.drawImage(duszekImage2, pozycjaXduszka2, pozycjaYduszka2, 50, 50, null);
            g.drawImage(duszekImage3, pozycjaXduszka3, pozycjaYduszka3, 50, 50, null);
            g.drawImage(duszekImage4, pozycjaXduszka4, pozycjaYduszka4, 50, 50, null);
        }
    }

    public void animacja() {
        if (animacja) {
            if (pozycjaX > 20 && etap1 && blokadaPacman) {
                pozycjaX--;

            } else {
                kierunek = 2;
                etap1 = false;
                if(pozycjaY < 730 && blokadaPacman) {
                    pozycjaY++;

                } else {
                    kierunek = 1;
                    if (pozycjaX < 131 && blokadaPacman){
                        pozycjaX++;
                        if (pozycjaX == 30) MenucanvasDOWN.animacja = true;

                    } else {
                        pozycjaX = 130;
                        pozycjaY = 20;
                        kierunek = 3;
                        klatka = 0;
                        if(blokadaPacman) blokadaPacman = false;
                        etap1 = true;
                    }
                }
            }

            if (pozycjaXduszka1 > 20 && etap1duszka1 && blokadaDuszka1) {
                pozycjaXduszka1--;
            } else {
                kierunekDuszka1 = 1;
                etap1duszka1 = false;
                if(pozycjaYduszka1 < 730 && blokadaDuszka1) {
                    pozycjaYduszka1++;
                } else {
                    kierunekDuszka1 = 0;
                    if (pozycjaXduszka1 < 131 && blokadaDuszka1){
                        pozycjaXduszka1++;
                    } else {
                        pozycjaXduszka1 = 230;
                        pozycjaYduszka1 = 20;
                        kierunekDuszka1 = 3;
                        if(blokadaDuszka1) blokadaDuszka1 = false;
                        etap1duszka1 = true;
                    }
                }
            }

            if (pozycjaXduszka2 > 20 && etap1duszka2 && blokadaDuszka2) {
                pozycjaXduszka2--;
            } else {
                kierunekDuszka2 = 1;
                etap1duszka2 = false;
                if(pozycjaYduszka2 < 730 && blokadaDuszka2) {
                    pozycjaYduszka2++;
                } else {
                    kierunekDuszka2 = 0;
                    if (pozycjaXduszka2 < 131 && blokadaDuszka2){
                        pozycjaXduszka2++;
                    } else {
                        pozycjaXduszka2 = 300;
                        pozycjaYduszka2 = 20;
                        kierunekDuszka2 = 3;
                        if(blokadaDuszka2) blokadaDuszka2 = false;
                        etap1duszka2 = true;
                    }
                }
            }

            if (pozycjaXduszka3 > 20 && etap1duszka3 && blokadaDuszka3) {
                pozycjaXduszka3--;
            } else {
                kierunekDuszka3 = 1;
                etap1duszka3 = false;
                if(pozycjaYduszka3 < 730 && blokadaDuszka3) {
                    pozycjaYduszka3++;
                } else {
                    kierunekDuszka3 = 0;
                    if (pozycjaXduszka3 < 131 && blokadaDuszka3){
                        pozycjaXduszka3++;
                    } else {
                        pozycjaXduszka3 = 370;
                        pozycjaYduszka3 = 20;
                        kierunekDuszka3 = 3;
                        if(blokadaDuszka3) blokadaDuszka3 = false;
                        etap1duszka3 = true;
                    }
                }
            }

            if (pozycjaXduszka4 > 20 && etap1duszka4) {
                pozycjaXduszka4--;
                klatka = coSto == 10 ? klatka + 1 : klatka;
                klatka = klatka < 4 ? klatka : 0;
                klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                coSto = coSto > 10 ? 0 : coSto + 1;
                repaint();
            } else {
                kierunekDuszka4 = 1;
                etap1duszka4 = false;
                if(pozycjaYduszka4 < 730) {
                    pozycjaYduszka4++;
                    klatka = coSto == 10 ? klatka + 1 : klatka;
                    klatka = klatka < 4 ? klatka : 0;
                    klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                    klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                    coSto = coSto > 10 ? 0 : coSto + 1;
                    repaint();
                } else {
                    kierunekDuszka4 = 0;
                    if (pozycjaXduszka4 < 131){
                        pozycjaXduszka4++;
                        klatka = coSto == 10 ? klatka + 1 : klatka;
                        klatka = klatka < 4 ? klatka : 0;
                        klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                        klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                        coSto = coSto > 10 ? 0 : coSto + 1;
                        repaint();
                    } else {
                        pozycjaXduszka4 = 440;
                        pozycjaYduszka4 = 20;
                        kierunekDuszka4 = 3;
                        klatkaDuszka = 0;
                        etap1duszka4 = true;
                        blokadaPacman = true;
                        blokadaDuszka1 = true;
                        blokadaDuszka2 = true;
                        blokadaDuszka3 = true;
                        animacja = false;
                    }
                }
            }
        }
    }
}

class MenucanvasRIGHT extends JPanel {
    private int coSto = 0;
    private boolean etap1 = true, etap1duszka1 = true, etap1duszka2 = true, etap1duszka3 = true, etap1duszka4 = true;
    private boolean blokadaPacman = true, blokadaDuszka1 = true, blokadaDuszka2 = true, blokadaDuszka3 = true;
    public static boolean animacja = false;
    private Integer pozycjaX = -50, pozycjaY = 730, kierunek = 1, klatka = 3, klatkaDuszka = 0;
    private Integer pozycjaXduszka1 = -150,  pozycjaXduszka2 = -220,  pozycjaXduszka3 = -290,  pozycjaXduszka4 = -360,
                    pozycjaYduszka1 = 730,   pozycjaYduszka2 = 730,   pozycjaYduszka3 = 730,   pozycjaYduszka4 = 730,
                    kierunekDuszka1 = 0,    kierunekDuszka2 = 0,    kierunekDuszka3 = 0,    kierunekDuszka4 = 0;

    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/menuRight.png");
    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    Sprite[][][] duszki = {{{Sprite.ghost_1_1, Sprite.ghost_1_2},{Sprite.ghost_1_3, Sprite.ghost_1_4}, {Sprite.ghost_1_5, Sprite.ghost_1_6}, {Sprite.ghost_1_7, Sprite.ghost_1_8}},
            {{Sprite.ghost_2_1, Sprite.ghost_2_2}, {Sprite.ghost_2_3, Sprite.ghost_2_4}, {Sprite.ghost_2_5, Sprite.ghost_2_6}, {Sprite.ghost_2_7, Sprite.ghost_2_8}},
            {{Sprite.ghost_3_1, Sprite.ghost_3_2}, {Sprite.ghost_3_3, Sprite.ghost_3_4}, {Sprite.ghost_3_5, Sprite.ghost_3_6}, {Sprite.ghost_3_7, Sprite.ghost_3_8}},
            {{Sprite.ghost_4_1, Sprite.ghost_4_2}, {Sprite.ghost_4_3, Sprite.ghost_4_4}, {Sprite.ghost_4_5, Sprite.ghost_4_6}, {Sprite.ghost_4_7, Sprite.ghost_4_8}}};

    private BufferedImage pacmanImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pacmanPixels = ((DataBufferInt)pacmanImage.getRaster().getDataBuffer()).getData();

    private BufferedImage duszekImage1 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels1 = ((DataBufferInt)duszekImage1.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage2 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels2 = ((DataBufferInt)duszekImage2.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage3 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels3 = ((DataBufferInt)duszekImage3.getRaster().getDataBuffer()).getData();
    private BufferedImage duszekImage4 = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] duszekPixels4 = ((DataBufferInt)duszekImage4.getRaster().getDataBuffer()).getData();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 0, this);

        if(animacja) {
            for(int i = 0; i < 2500; i++) {
                pacmanPixels[i] = pacman[kierunek][klatka].pixels[i];
                duszekPixels1[i] = duszki[0][kierunekDuszka1][klatkaDuszka].pixels[i];
                duszekPixels2[i] = duszki[1][kierunekDuszka2][klatkaDuszka].pixels[i];
                duszekPixels3[i] = duszki[2][kierunekDuszka3][klatkaDuszka].pixels[i];
                duszekPixels4[i] = duszki[3][kierunekDuszka4][klatkaDuszka].pixels[i];
            }

            g.drawImage(pacmanImage, pozycjaX, pozycjaY, 50, 50, null);
            g.drawImage(duszekImage1, pozycjaXduszka1, pozycjaYduszka1, 50, 50, null);
            g.drawImage(duszekImage2, pozycjaXduszka2, pozycjaYduszka2, 50, 50, null);
            g.drawImage(duszekImage3, pozycjaXduszka3, pozycjaYduszka3, 50, 50, null);
            g.drawImage(duszekImage4, pozycjaXduszka4, pozycjaYduszka4, 50, 50, null);
        }
    }

    public void animacja() {
        if (animacja) {
            if (pozycjaX < 10 && etap1 && blokadaPacman) {
                pozycjaX++;

            } else {
                kierunek = 0;
                etap1 = false;
                if(pozycjaY > 20 && blokadaPacman) {
                    pozycjaY--;

                } else {
                    kierunek = 3;
                    if (pozycjaX > -51 && blokadaPacman){
                        pozycjaX--;
                        if (pozycjaX == 0) MenucanvasUP.animacja = true;

                    } else {
                        pozycjaX = -50;
                        pozycjaY = 730;
                        kierunek = 1;
                        klatka = 0;
                        if(blokadaPacman) blokadaPacman = false;
                        etap1 = true;
                    }
                }
            }

            if (pozycjaXduszka1 < 10 && etap1duszka1 && blokadaDuszka1) {
                pozycjaXduszka1++;
            } else {
                kierunekDuszka1 = 2;
                etap1duszka1 = false;
                if(pozycjaYduszka1 > 20 && blokadaDuszka1) {
                    pozycjaYduszka1--;
                } else {
                    kierunekDuszka1 = 3;
                    if (pozycjaXduszka1 > -51 && blokadaDuszka1){
                        pozycjaXduszka1--;
                    } else {
                        pozycjaXduszka1 = -150;
                        pozycjaYduszka1 = 730;
                        kierunekDuszka1 = 0;
                        if(blokadaDuszka1) blokadaDuszka1 = false;
                        etap1duszka1 = true;
                    }
                }
            }

            if (pozycjaXduszka2 < 10 && etap1duszka2 && blokadaDuszka2) {
                pozycjaXduszka2++;
            } else {
                kierunekDuszka2 = 2;
                etap1duszka2 = false;
                if(pozycjaYduszka2 > 20 && blokadaDuszka2) {
                    pozycjaYduszka2--;
                } else {
                    kierunekDuszka2 = 3;
                    if (pozycjaXduszka2 > -51 && blokadaDuszka2){
                        pozycjaXduszka2--;
                    } else {
                        pozycjaXduszka2 = -220;
                        pozycjaYduszka2 = 730;
                        kierunekDuszka2 = 0;
                        if(blokadaDuszka2) blokadaDuszka2 = false;
                        etap1duszka2 = true;
                    }
                }
            }

            if (pozycjaXduszka3 < 10 && etap1duszka3 && blokadaDuszka3) {
                pozycjaXduszka3++;
            } else {
                kierunekDuszka3 = 2;
                etap1duszka3 = false;
                if(pozycjaYduszka3 > 20 && blokadaDuszka3) {
                    pozycjaYduszka3--;
                } else {
                    kierunekDuszka3 = 3;
                    if (pozycjaXduszka3 > -51 && blokadaDuszka3){
                        pozycjaXduszka3--;
                    } else {
                        pozycjaXduszka3 = -290;
                        pozycjaYduszka3 = 730;
                        kierunekDuszka3 = 0;
                        if(blokadaDuszka3) blokadaDuszka3 = false;
                        etap1duszka3 = true;
                    }
                }
            }

            if (pozycjaXduszka4 < 10 && etap1duszka4) {
                pozycjaXduszka4++;
                klatka = coSto == 10 ? klatka + 1 : klatka;
                klatka = klatka < 4 ? klatka : 0;
                klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                coSto = coSto > 10 ? 0 : coSto + 1;
                repaint();
            } else {
                kierunekDuszka4 = 2;
                etap1duszka4 = false;
                if(pozycjaYduszka4 > 20) {
                    pozycjaYduszka4--;
                    klatka = coSto == 10 ? klatka + 1 : klatka;
                    klatka = klatka < 4 ? klatka : 0;
                    klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                    klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                    coSto = coSto > 10 ? 0 : coSto + 1;
                    repaint();
                } else {
                    kierunekDuszka4 = 3;
                    if (pozycjaXduszka4 > -51){
                        pozycjaXduszka4--;
                        klatka = coSto == 10 ? klatka + 1 : klatka;
                        klatka = klatka < 4 ? klatka : 0;
                        klatkaDuszka = coSto == 10 ? klatkaDuszka + 1 : klatkaDuszka;
                        klatkaDuszka = klatkaDuszka < 2 ? klatkaDuszka : 0;
                        coSto = coSto > 10 ? 0 : coSto + 1;
                        repaint();
                    } else {
                        pozycjaXduszka4 = -360;
                        pozycjaYduszka4 = 730;
                        kierunekDuszka4 = 0;
                        klatkaDuszka = 0;
                        etap1duszka4 = true;
                        blokadaPacman = true;
                        blokadaDuszka1 = true;
                        blokadaDuszka2 = true;
                        blokadaDuszka3 = true;
                        animacja = false;
                    }
                }
            }
        }
    }
}

class MenucanvasMIDDLE extends JPanel {
    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/miedzy.png");
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 0, this);
    }
}

public class Game extends Canvas implements Runnable {
    //private  static final long serialVersionUID = 1L;

    public static int width = 1600;
    public static int height = width / 16 * 9;
    public static int scale = 1;
    public static String title = "PacMan";
    JButton wznow = new JButton("Wznów grę");
    JButton resetGry = new JButton("Restart");
    JButton wylacz = new JButton("Wyjdź :(");
    JButton edytor = new JButton("Edytor poziomu");


    private Thread thread;
    private JFrame gameWindow;
    private static Keyboard key;
    private boolean running = false;

    public static Screen screen;
    private Level level;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli


    public Game() {
        Dimension size = new Dimension(width * scale, height * scale); //ustawienie wilkosci
        setPreferredSize(size); //ustawienie wielkosci procesu gry

        screen = new Screen(width, height); //tworzymy obiekt screen którym będziemy zmieniac nasze piksele

        gameWindow = new JFrame(); //stworzenie nowego obiektu okienka javy
        key = new Keyboard();
        level = new Level();
        level.add(new Pacmann(800,600,key,3));
        addKeyListener(key);


    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //RUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0; // ta zmiena bedzie zliczal ile ramek generujemy na sek
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
            lastTime = now;

            while (delta >= 1) { //timer upewniajacy sie ze robi update 60 razy na sek
                update();
                updates++; //ile updatow robimy na sek
                delta--;


            }
            render();
            frames++; //ile ramek robimy na sek
            if (System.currentTimeMillis() - timer > 1000) { //FPS i updaty
                timer += 1000;
             //   System.out.println(updates + " ups, " + frames + " fps");
                gameWindow.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //UPDATE TU !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void update() {
        key.update();
        if(key.esc){
            if(level.pauza){
                level.pauza=false;
                wznow.setVisible(false);
                wylacz.setVisible(false);
                resetGry.setVisible(false);
                edytor.setVisible(false);
                key.keys[KeyEvent.VK_ESCAPE]=false;
            }
            else{
                level.pauza=true;
                wznow.setVisible(true);
                wylacz.setVisible(true);
                resetGry.setVisible(true);
                edytor.setVisible(true);
                key.keys[KeyEvent.VK_ESCAPE]=false;
            }
        }

        level.update();
    }

    //RENDEROWANIE !!!!!!!!!
    public void render() {
        BufferStrategy bs = getBufferStrategy(); //pobieramy ustawienia buffera z canvas'a
        if (bs == null) {
            createBufferStrategy(3); //stworzenie buffera jesli nie istnial z wartoscia 3 dla bufferowania wiekszej ilosci rzeczy naraz (bufferują się dwa kolejne obrazy na raz)
            return;
        }

        screen.clear();

        level.render(screen);
        Color color ;

        if(level.pauza) {

            for (int i = 0; i < pixels.length; i++) {
                color = new Color(screen.pixels[i]);
                pixels[i] = color.darker().darker().darker().getRGB();
            }
        }
        else{
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
        }

        Graphics g = bs.getDrawGraphics(); //ustawienie bufferow pod grafike


        g.drawImage(image, 0,0, getWidth(), getHeight(), null);

        g.dispose(); //wyswietlenie grafiki stworzonej powyzej
        bs.show(); //pokazanie buffera w celu jego wyczyszczenia
    }



    public static void main(String[] args) {
        final boolean[] Dont = {true};
        JButton startButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        MenucanvasUP menucanvasUP = new MenucanvasUP();
        MenucanvasDOWN menucanvasDOWN = new MenucanvasDOWN();
        MenucanvasRIGHT menucanvasRIGHT = new MenucanvasRIGHT();
        MenucanvasLEFT menucanvasLEFT = new MenucanvasLEFT();
        MenucanvasMIDDLE menucanvasMIDDLE = new MenucanvasMIDDLE();

        JFrame menuWindow = new JFrame();
        menuWindow.setLayout(null);

        menuWindow.setResizable(false);
        menuWindow.setUndecorated(true);
        menuWindow.setSize(600, 800);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menucanvasUP.setBounds(80,0,440,450);
        menucanvasDOWN.setBounds(80, 700, 440, 100);
        menucanvasLEFT.setBounds(0,0,80,800);
        menucanvasRIGHT.setBounds(520,0,80,800);
        menucanvasMIDDLE.setBounds(80,550,440,50);

        startButton.setBounds(80, 450, 440, 100);
        exitButton.setBounds(80, 600, 440, 100);

        menuWindow.add(menucanvasUP);
        menuWindow.add(menucanvasDOWN);
        menuWindow.add(menucanvasLEFT);
        menuWindow.add(menucanvasRIGHT);
        menuWindow.add(menucanvasMIDDLE);

        menuWindow.add(startButton);
        menuWindow.add(exitButton);

        menuWindow.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dont[0] = false;
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });



        Game game = new Game();
        //ustawienia okienka
       // jPanel.setBackground(Color.white);
        game.gameWindow.setResizable(false); //NIE MA MAKSYMALIZACJI
        game.gameWindow.setTitle(Game.title); //Tytuł okienka

        game.wznow.setBounds(725,335,150,50);
        game.resetGry.setBounds(725,395,150,50);
        game.edytor.setBounds(725,455,150,50);
        game.wylacz.setBounds(725,515,150,50);
        game.wznow.setVisible(false);
        game.resetGry.setVisible(false);
        game.wylacz.setVisible(false);
        game.edytor.setVisible(false);
        game.gameWindow.add(game.wznow);
        game.gameWindow.add(game.resetGry);
        game.gameWindow.add(game.wylacz);
        game.gameWindow.add(game.edytor);

        game.wznow.setForeground(Color.BLACK);
        game.wznow.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        game.wznow.setBorder(compound);
        game.wznow.setFocusPainted(false);

        game.resetGry.setForeground(Color.BLACK);
        game.resetGry.setBackground(new Color(0x78BEAF0E));
        game.resetGry.setBorder(compound);
        game.resetGry.setFocusPainted(false);
;

        game.wylacz.setForeground(Color.BLACK);
        game.wylacz.setBackground(new Color(0x9B3B35));
        game.wylacz.setBorder(compound);
        game.wylacz.setFocusPainted(false);

        game.edytor.setForeground(Color.BLACK);
        game.edytor.setBackground(new Color(0x459B59));
        game.edytor.setBorder(compound);
        game.edytor.setFocusPainted(false);


        game.wznow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                game.level.pauza=false;
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.resetGry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.level.pauza=false;
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                game.level.clearLevel(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.wylacz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.edytor.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mam cie");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton[] jButtons = new JButton[3];



        game.gameWindow.add(game); //Wypełniamy okno naszą "grą" dzięki canvas'owi
        //game.gameWindow.setUndecorated(true);  //Usuniecie paska na gorze
        game.gameWindow.pack(); //ustawia wielkosc okna na podstawie wcześniej ustawione "size" w konstruktorze
        game.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wyłączenie procesu razem z zamknięciem okna
        game.gameWindow.setLocationRelativeTo(null); //ustawienie żeby okienko uruchamiało się w środku ekranu
        //game.gameWindow.setUndecorated(true);


        while(Dont[0]) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menucanvasUP.animacja();
            menucanvasDOWN.animacja();
            menucanvasLEFT.animacja();
            menucanvasRIGHT.animacja();
        }

        menuWindow.setVisible(false);
        game.gameWindow.setVisible(true); //ustawienie okienka żeby było widoczne

        //start gry
        game.start();

    }
    public static void endthis(){

    }

}

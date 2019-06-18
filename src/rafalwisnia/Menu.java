package rafalwisnia;

import rafalwisnia.UI.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * W tym miejscu  jest rozplanowywane okienko startowe oraz zamieszczane sa wszystkie grafiki w nim zawarte
 * Dodatkowo, powyzej glownej klasy menu znajduja sie 4 klasy realizujace animacje glownego okienka oraz jedna klasa
 * wypleniajaca miejsce miedzy przyciskami
 *
 * MenucanvasUP. Realizuje animacje gornej czesci okienka oraz zamieszcza grafike w okienku
 *
 * Gra w grze ~ Wisnia
 */
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
            if(klatkaDuszka==2)klatkaDuszka=0;
            if(klatka==4)klatka=0;
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
                if(coSto==10){
                    if(klatka+1<4)klatka++;
                    else klatka=0;
                    if(klatkaDuszka+1<2)klatkaDuszka++;
                    else klatkaDuszka=0;
                }
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


/**
 *
 * MenucanvasDown. Realizuje animacje dolnej czesci okienka
 *
 */
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
            if(klatkaDuszka==2)klatkaDuszka=0;
            if(klatka==4)klatka=0;
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
                if(coSto==10){
                    if(klatka+1<4)klatka++;
                    else klatka=0;
                    if(klatkaDuszka+1<2)klatkaDuszka++;
                    else klatkaDuszka=0;
                }
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

/**
 * MenucanvasLeft. Realizuje animacje okienka w lewej czesci. W plynny sposob przedstawia przejscie ruchu
 * pacmana i duszkow w poziomie na pionowy ruch.
 * Jest to zrealizowane za pomoca trzech oddzielnych zagniezdzonych warunkow
 */

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
            if(klatkaDuszka==2)klatkaDuszka=0;
            if(klatka==4)klatka=0;
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
                if(coSto==10){
                    if(klatka+1<4)klatka++;
                    else klatka=0;
                    if(klatkaDuszka+1<2)klatkaDuszka++;
                    else klatkaDuszka=0;
                }
                coSto = coSto > 10 ? 0 : coSto + 1;
                repaint();
            } else {
                kierunekDuszka4 = 1;
                etap1duszka4 = false;
                if(pozycjaYduszka4 < 730) {
                    pozycjaYduszka4++;
                    if(coSto==10){
                        if(klatka+1<4)klatka++;
                        else klatka=0;
                        if(klatkaDuszka+1<2)klatkaDuszka++;
                        else klatkaDuszka=0;
                    }
                    coSto = coSto > 10 ? 0 : coSto + 1;
                    repaint();
                } else {
                    kierunekDuszka4 = 0;
                    if (pozycjaXduszka4 < 131){
                        pozycjaXduszka4++;
                        if(coSto==10){
                            if(klatka+1<4)klatka++;
                            else klatka=0;
                            if(klatkaDuszka+1<2)klatkaDuszka++;
                            else klatkaDuszka=0;
                        }
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

/**
 * MenucanvasRight. Realizuje animacje okienka w prawej czesci. W plynny sposob przedstawia przejscie ruchu
 * pacmana i duszkow w poziomie na pionowy ruch.
 * Jest to zrealizowane za pomoca trzech oddzielnych zagniezdzonych warunkow
 */

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
            if(klatkaDuszka==2)klatkaDuszka=0;
            if(klatka==4)klatka=0;
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
                if(coSto==10){
                    if(klatka+1<4)klatka++;
                    else klatka=0;
                    if(klatkaDuszka+1<2)klatkaDuszka++;
                    else klatkaDuszka=0;
                }
                coSto = coSto > 10 ? 0 : coSto + 1;
                repaint();
            } else {
                kierunekDuszka4 = 2;
                etap1duszka4 = false;
                if(pozycjaYduszka4 > 20) {
                    pozycjaYduszka4--;
                    if(coSto==10){
                        if(klatka+1<4)klatka++;
                        else klatka=0;
                        if(klatkaDuszka+1<2)klatkaDuszka++;
                        else klatkaDuszka=0;
                    }
                    coSto = coSto > 10 ? 0 : coSto + 1;
                    repaint();
                } else {
                    kierunekDuszka4 = 3;
                    if (pozycjaXduszka4 > -51){
                        pozycjaXduszka4--;
                        if(coSto==10){
                            if(klatka+1<4)klatka++;
                            else klatka=0;
                            if(klatkaDuszka+1<2)klatkaDuszka++;
                            else klatkaDuszka=0;
                        }
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

/**
 * MenucanvasMiddle. Zapelnia przestrzen miedzy przyciskami grafika
 */
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

/**
 *  Sa tutaj tworzone przyciski uzywane w Menu i sa wstawiane do nich grafiki
 */
public class Menu extends JPanel {
    public JFrame menuWindow;
    public JButton startButton;
    public JButton exitButton;

    public MenucanvasUP menucanvasUP;
    public MenucanvasDOWN menucanvasDOWN;
    public MenucanvasRIGHT menucanvasRIGHT;
    public MenucanvasLEFT menucanvasLEFT;
    public MenucanvasMIDDLE menucanvasMIDDLE;

    public Menu() {
        startButton = new JButton();
        exitButton = new JButton();

        menucanvasUP = new MenucanvasUP();
        menucanvasDOWN = new MenucanvasDOWN();
        menucanvasRIGHT = new MenucanvasRIGHT();
        menucanvasLEFT = new MenucanvasLEFT();
        menucanvasMIDDLE = new MenucanvasMIDDLE();

        menuWindow = new JFrame();
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

        //startButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20));
        ImageIcon button = new ImageIcon("resources/textures/Board/newgamebutton.png");
        ImageIcon button2 = new ImageIcon("resources/textures/Board/exitbutton.png");

        startButton.setIcon(button);
        exitButton.setIcon(button2);

        menuWindow.add(menucanvasUP);
        menuWindow.add(menucanvasDOWN);
        menuWindow.add(menucanvasLEFT);
        menuWindow.add(menucanvasRIGHT);
        menuWindow.add(menucanvasMIDDLE);

        menuWindow.add(startButton);
        menuWindow.add(exitButton);
    }
}

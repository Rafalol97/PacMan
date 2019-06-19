package rafalwisnia;

import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Edytor.java Nieskonczana klasa ktora miala realizowac edytor poziomow dla uzytkownika
 *
 */
public class Edytor extends JPanel implements java.awt.event.MouseListener {
    public JFrame frame;
    public Screen screen;
    private int[] aktualnyPacman = {-1, -1};
    private BufferedImage Border;
    private BufferedImage image = new BufferedImage(1400, 900, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli
    private int width = 1400, height = 900;
    private boolean flagaWybierania = true;
    private int[] materialy = {0, 99, -1, -2};
    private int wybranyMaterial = 9;

    public JButton saveButton;
    public JButton loadButton;
    public JButton loadToGameButton;
    public JButton loadFromGameButton;
    public JButton reset;

    private int tilesClean[][] = {
            {1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 2},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 1, 88, 17, 17, 8, 2, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 5, 11, 11, 11, 11, 6, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {5, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 6},
    };

    private int tilesInEditor[][] = {
            {1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 2},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 1, 88, 17, 17, 8, 2, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 5, 11, 11, 11, 11, 6, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
            {5, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 6},
    };

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getX() > 220 && e.getX() < 270 && e.getY() > 100 && e.getY() < 150) {
            wybranyMaterial = 0;
            repaint();
            System.out.println("Wybrany material: " + wybranyMaterial);

        }
        if (e.getX() > 220 && e.getX() < 270 && e.getY() > 200 && e.getY() < 250) {
            wybranyMaterial = 1;
            repaint();
            System.out.println("Wybrany material: " + wybranyMaterial);
        }
        if (e.getX() > 220 && e.getX() < 270 && e.getY() > 300 && e.getY() < 350) {
            wybranyMaterial = 2;
            repaint();
            System.out.println("Wybrany material: " + wybranyMaterial);
        }
        if (e.getX() > 220 && e.getX() < 270 && e.getY() > 400 && e.getY() < 450) {
            wybranyMaterial = 3;
            repaint();
            System.out.println("Wybrany material: " + wybranyMaterial);
        }
        if (e.getX() > 670 && e.getX() < 970 && e.getY() > 400 && e.getY() < 600) {
            System.out.println("Jestem na: x-" + ((e.getX() - 323) / 50) + " y-" + ((e.getY() - 100) / 50));
            System.out.println("Nie wolno zmieniac spawna duszkow!");
        } else if (e.getX() > 370 && e.getX() < 1270 && e.getY() > 150 && e.getY() < 750) {
            if (wybranyMaterial == 3) {
                if (this.aktualnyPacman[0] == -1 && this.aktualnyPacman[1] == -1) {
                    aktualnyPacman[0] =(e.getX()-323) / 50;
                    aktualnyPacman[1] =(e.getY() -105)/ 50;
                } else {
                    tilesInEditor[aktualnyPacman[1]][aktualnyPacman[0]] = 0;
                    aktualnyPacman[0] = (e.getX()-323) / 50;
                    aktualnyPacman[1] = (e.getY() -105)/ 50;
                }
            }
            tilesInEditor[(e.getY() -105)/ 50 ][(e.getX()-323) / 50] = materialy[wybranyMaterial];
            System.out.println("Jestem na: x-" + ((e.getX()-323) / 50 ) + " y-" + ((e.getY()-105)/ 50 ));
            repaint();

        }
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

    public Edytor() {
        Dimension size = new Dimension(1400, 900); //ustawienie wilkosci
        setPreferredSize(size);

        screen = new Screen(1400, 900);

        frame = new JFrame();

        try {
            Border = ImageIO.read(new File("resources/textures/Board/edytortlo.bmp"));
        } catch (IOException e) {

            e.printStackTrace();
        }

        addMouseListener(this);

        saveButton = new JButton("Save to file");
        loadButton = new JButton("Load from file");
        loadToGameButton = new JButton("Load to game");
        loadFromGameButton = new JButton("Load from game");
        reset = new JButton("Reset editor");

        reset.setBounds(1120, 35, 200, 50);
        saveButton.setBounds(1120, 815, 200, 50);
        loadButton.setBounds(870, 815, 200, 50);
        loadToGameButton.setBounds(620, 815, 200, 50);
        loadFromGameButton.setBounds(370, 815, 200, 50);

        frame.add(reset);
        frame.add(saveButton);
        frame.add(loadButton);
        frame.add(loadToGameButton);
        frame.add(loadFromGameButton);
    }

    //flip 1 - oY, 2 - oX, 3 - pkt 0,0
    public void reorderBoard() {
        //wyjscie duszkow musi byc otwarte
        if (tilesInEditor[5][9] >= 1 || tilesInEditor[5][9] == -2) {
            tilesInEditor[5][9] = 0;
            System.out.println("Duszki musza miec mozliwosc wyjscia!");
        }
        if (tilesInEditor[5][10] >= 1 || tilesInEditor[5][10] == -2) {
            tilesInEditor[5][10] = 0;
            System.out.println("Duszki musza miec mozliwosc wyjscia!");
        }

        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 20; x++) {
                //alg ukladajacy
                if (x == 0 && y == 0) {
                } else if (x == 19 && y == 0) {
                } else if (x == 19 && y == 13) {
                } else if (x == 0 && y == 13) {
                } else if (x == 0) {
                    if (tilesInEditor[y][x + 1] >= 1) {
                        tilesInEditor[y][x] = 21;
                    }
                } else if (y == 0) {
                    if (tilesInEditor[y + 1][x] >= 1) {
                        tilesInEditor[y][x] = 13;
                    }
                } else if (y == 13) {
                    if (tilesInEditor[y - 1][x] >= 1) {
                        tilesInEditor[y][x] = 14;
                    }
                } else if (x == 19) {
                    if (tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 18;
                    }
                } else {
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 19;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 44;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 8;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 10;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 4;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 3;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 1;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] < 1) {
                        tilesInEditor[y][x] = 21;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 88;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 6;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 11;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] < 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 14;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 9;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] < 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 18;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] < 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 13;
                    }
                    if (tilesInEditor[y][x] >= 1 && tilesInEditor[y - 1][x] >= 1 && tilesInEditor[y][x + 1] >= 1 && tilesInEditor[y + 1][x] >= 1 && tilesInEditor[y][x - 1] >= 1) {
                        tilesInEditor[y][x] = 20;
                    }
                }

            }
        }
        //sprawdzenie scian obok bramy aby zachowaly swoj ksztalt
        if (tilesInEditor[5][8] >= 1) {
            tilesInEditor[6][8] = 6;
        } else {
            tilesInEditor[6][8] = 88;
        }
        if (tilesInEditor[5][11] >= 1) {
            tilesInEditor[6][11] = 5;
        } else {
            tilesInEditor[6][11] = 8;
        }
        //ponowne ustawienie bramy
        tilesInEditor[6][9] = 17;
        tilesInEditor[6][10] = 17;
    }

    public void printBoard() {
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 20; x++) {
                if (tilesInEditor[y][x] == 0) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.podloga, 0);
                else if (tilesInEditor[y][x] == 1) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_1, 0);
                else if (tilesInEditor[y][x] == 2) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_2, 0);
                else if (tilesInEditor[y][x] == 3) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_3, 0);
                else if (tilesInEditor[y][x] == 4) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_4, 0);
                else if (tilesInEditor[y][x] == 5) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_5, 0);
                else if (tilesInEditor[y][x] == 6) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_6, 0);
                else if (tilesInEditor[y][x] == 7) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_7, 0);
                else if (tilesInEditor[y][x] == 8) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_8, 0);
                else if (tilesInEditor[y][x] == 9) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_9, 0);
                else if (tilesInEditor[y][x] == 10) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_10, 0);
                else if (tilesInEditor[y][x] == 11) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_11, 0);
                else if (tilesInEditor[y][x] == 12) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_12, 0);
                else if (tilesInEditor[y][x] == 44) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_4, 2);
                else if (tilesInEditor[y][x] == 88) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_8, 1);
                else if (tilesInEditor[y][x] == 13) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_13, 0);
                else if (tilesInEditor[y][x] == 14) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_13, 2);
                else if (tilesInEditor[y][x] == 15) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_13, 3);
                else if (tilesInEditor[y][x] == 16) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_13, 4);
                else if (tilesInEditor[y][x] == 17) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_17, 0);
                else if (tilesInEditor[y][x] == 18) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_18, 0);
                else if (tilesInEditor[y][x] == 19) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_19, 0);
                else if (tilesInEditor[y][x] == 20) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_20, 0);
                else if (tilesInEditor[y][x] == 21) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.brick_18, 1);
                else if (tilesInEditor[y][x] == -1) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.bigCoin, 0);
                else if (tilesInEditor[y][x] == -2) screen.renderTile((x * 50) + 320, (y * 50) + 100, Sprite.pacmann_lewo_0, 0);
                screen.dlaWisni(x * 50 + 320, y * 50 + 100, Color.GRAY);
            }
        }
    }

    public void printPanel() {
        if(wybranyMaterial == 0) {
            screen.renderTileBright(220, 100, Sprite.podloga, 0);
        } else {
            screen.renderTile(220, 100, Sprite.podloga, 0);
        }
        if(wybranyMaterial == 1) {
            screen.renderTileBright(220, 200, Sprite.brick_19, 0);
        } else {
            screen.renderTile(220, 200, Sprite.brick_19, 0);
        }
        if(wybranyMaterial == 2) {
            screen.renderTileBright(220, 300, Sprite.bigCoin, 0);
        } else {
            screen.renderTile(220, 300, Sprite.bigCoin, 0);
        }
        if(wybranyMaterial == 3) {
            screen.renderTileBright(220, 400, Sprite.pacmann_lewo_0, 0);
        } else {
            screen.renderTile(220, 400, Sprite.pacmann_lewo_0, 0);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        screen.clear();

        for (int y = 0; y < 900; y++) {
            for (int x = 0; x < 1400; x++) {
                screen.pixels[x + y * 1400] = Border.getRGB(x, y);
            }
        }

        reorderBoard();
        printBoard();
        printPanel();
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public int[][] getTilesInEditor() {
        return tilesInEditor;
    }

    public void setTilesInEditor(int[][] tilesInEditor) {
        this.tilesInEditor = tilesInEditor;
    }

    void writeMatrix() {
        FileWriter fileWriter;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "pacjar");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                System.out.println(fileChooser.getSelectedFile().getAbsolutePath() + fileChooser.getSelectedFile().getName()+".pacjar");
                fileWriter = new FileWriter(fileChooser.getSelectedFile().getAbsolutePath() + fileChooser.getSelectedFile().getName()+".pacjar");

                BufferedWriter bw = new BufferedWriter(fileWriter);

                for (int i = 0; i < tilesInEditor.length; i++) {
                    for (int j = 0; j < tilesInEditor[i].length; j++) {
                        bw.write(tilesInEditor[i][j] + ((j == tilesInEditor[i].length - 1) ? "" : ","));
                    }
                    bw.newLine();
                }
                bw.flush();


            } catch (IOException e) {
            e.printStackTrace();
        }


    }
    }

}

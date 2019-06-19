package rafalwisnia;

import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Edytor.java Nieskonczana klasa ktora miala realizowac edytor poziomow dla uzytkownika
 *
 */
public class Edytor extends JPanel implements java.awt.event.MouseListener {
    public JFrame frame;
    public Screen screen;

    private BufferedImage image = new BufferedImage(1400, 900, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli

    private int tiles[][]={
            {1,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,2},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,11,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,1,88,17,17,8,2,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,3,0,0,0,0,3,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,3,0,0,0,0,3,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,5,11,11,11,11,6,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {5,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,6},
    };

    @Override
    public void mouseClicked(MouseEvent e) {

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

    public Edytor() {
        Dimension size = new Dimension(1400, 900); //ustawienie wilkosci
        setPreferredSize(size);

        screen = new Screen(1400, 900);

        frame = new JFrame();
        //frame.setLayout(null);


        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //flip 1 - oY, 2 - oX, 3 - pkt 0,0
    public void reorderBoard(){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++) {
                if(x == 0 && y == 0) {}
                else if (x == 19 && y == 0) {}
                else if (x == 19 && y == 13) {}
                else if (x == 0 && y == 13) {}
                else if (x == 0) {
                   if(tiles[y][x+1]>=1) {tiles[y][x] = 21;}
                }
                else if (y == 0) {
                    if(tiles[y+1][x]>=1) {tiles[y][x] = 13;}
                }
                else if (y == 13) {
                    if(tiles[y-1][x]>=1) {tiles[y][x] = 14;}
                }
                else if (x == 19) {
                    if(tiles[y][x-1]>=1) {tiles[y][x] = 18;}
                }
                else {
                    //if(tiles[y+1][x+1]>=1 && )
                }
            }
        }
    }

    public void printBoard() {
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                if(tiles[y][x]==0) screen.renderTile((x*50)+320,(y*50)+100, Sprite.podloga,0);
                else if(tiles[y][x]==1) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_1,0);
                else if(tiles[y][x]==2) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_2,0);
                else if(tiles[y][x]==3) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_3,0);
                else if(tiles[y][x]==4) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_4,0);
                else if(tiles[y][x]==5) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_5,0);
                else if(tiles[y][x]==6) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_6,0);
                else if(tiles[y][x]==7) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_7,0);
                else if(tiles[y][x]==8) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_8,0);
                else if(tiles[y][x]==9) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_9,0);
                else if(tiles[y][x]==10) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_10,0);
                else if(tiles[y][x]==11) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_11,0);
                else if(tiles[y][x]==12) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_12,0);
                else if(tiles[y][x]==44) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_4,2);
                else if(tiles[y][x]==88) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_8,1);
                else if(tiles[y][x]==13) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_13,0);
                else if(tiles[y][x]==14) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_13,2);
                else if(tiles[y][x]==15) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_13,3);
                else if(tiles[y][x]==16) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_13,4);
                else if(tiles[y][x]==17) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_17,0);
                else if(tiles[y][x]==18) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_18,0);
                else if(tiles[y][x]==19) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_19,0);
                else if(tiles[y][x]==20) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_20,0);
                else if(tiles[y][x]==21) screen.renderTile((x*50)+320,(y*50)+100, Sprite.brick_18,1);
                else if(tiles[y][x]==-1) screen.renderTile((x*50)+320,(y*50)+100, Sprite.bigCoin,0);
            }
        }
    }

    public void printPanel() {
        screen.renderTile(220,100, Sprite.podloga,0);
        screen.renderTile(220,200, Sprite.brick_19,0);
        screen.renderTile(220,300, Sprite.bigCoin,0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        screen.clear();

        for (int i = 0; i < screen.pixels.length; i++) {
            screen.pixels[i] = 0x2E8B57;
        }

        reorderBoard();

        printBoard();

        printPanel();

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
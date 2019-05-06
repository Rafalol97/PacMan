package UI;

import Level.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Screen   {

    private int width, height;
    public int[] pixels;

    public int[] tiles = new int[4 * 4];

    private Board plansza;
    private BufferedImage tile, brick;
    private Random random = new Random();

    public Screen(int width, int height) {
        try {
            tile = ImageIO.read(new File("C:\\Users\\rafal\\Desktop\\pacman_plansza.bmp"));
            brick = ImageIO.read(new File("C:\\Users\\rafal\\Desktop\\hehebitmapa.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        plansza = new Board(width - 600, height - 200);
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        for (int i = 0; i < 4 * 4; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < 1300 && x >= 300 && y < 800 && y >= 100) {
                    pixels[x + y * width] = plansza.getPixelsFromBoard(x - 300, y - 100);
                     }
                      else {
                       pixels[x+y*width] = tile.getRGB(x,y);
                      }

                }
            }
        }
    }






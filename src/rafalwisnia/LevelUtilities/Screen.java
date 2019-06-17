package rafalwisnia.LevelUtilities;



import rafalwisnia.UI.Sprite;
import rafalwisnia.UI.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;


/**
 * Z neta
 */
public class Screen {

    private int width, height;
    public int[] pixels;
    private String pathToBorder = "resources/textures/Board/board.bmp";
    private BufferedImage Border;
    public static final int ALPHA_COL = -16777216;

    public Screen(int width, int height) {

        try {
            Border = ImageIO.read(new File(pathToBorder));
        } catch (IOException e) {
            e.printStackTrace();
        }



        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }



    public void renderBorder() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 1300 || x < 300 || y > 800 || y < 100)
                    pixels[x + y * width] = Border.getRGB(x, y);
            }
        }
    }

    public void renderMob(int xp, int yp, Sprite sprite, int flip) {
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) ys = sprite.SIZE-1 - y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) xs = sprite.SIZE-1 - x;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = sprite.pixels[xs + ys * sprite.SIZE];
                if (col != ALPHA_COL) pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {   ///TODO do zmiany
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                int col = sprite.pixels[x + y * sprite.SIZE];
                if (col != ALPHA_COL) pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderTile(int xp, int yp, Sprite sprite) { ///TODO do zmiany

        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x <sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
            }
        }
    }
    public void renderTile(int xp, int yp, Sprite sprite, int flip) {
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) ys = sprite.SIZE-1 - y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) xs = sprite.SIZE-1 - x;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] =sprite.pixels[xs + ys * sprite.SIZE];
            }
        }
    }
    public void renderColor(int xp, int yp,int width,int height, Color color) { ///TODO do zmiany

        for (int y = 0; y < height; y++) {
            int ya = y + yp;
            for (int x = 0; x <width; x++) {
                int xa = x + xp;
                pixels[xa + ya * this.width] =color.getRGB();
            }
        }
    }
    public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
        int color;

        for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
            int ya = y + yp;
            for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
                color= sheet.pixels[x + y * sheet.SPRITE_WIDTH];
                if (color != ALPHA_COL) pixels[xa + ya * width] = color;
            }
        }
    }
}









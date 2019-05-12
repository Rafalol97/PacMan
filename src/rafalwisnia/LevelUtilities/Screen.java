package rafalwisnia.LevelUtilities;



import rafalwisnia.Entity.Ghost1;
import rafalwisnia.Entity.Ghost2;
import rafalwisnia.Entity.Mob;
import rafalwisnia.UI.Sprite;

import rafalwisnia.UI.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Screen {

    private int width, height;
    public int[] pixels;
    private String pathToBorder = "resources/textures/Board/board.bmp";
    private BufferedImage Border;
    public static final int ALPHA_COL = 0x0;

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

    public void renderMob(int xp, int yp, Mob mob) {
        for (int y = 0; y < 32; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < 32; x++) {
                int xa = x + xp;
                int xs = x;
                if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int col = mob.getSprite().pixels[xs + ys * 32];
                if ((mob instanceof Ghost1) && col == 0xff472BBF) col = 0xffBA0015;
                if ((mob instanceof Ghost2) && col == 0xff472BBF) col = 0xffE8E83A;
                if (col != ALPHA_COL) pixels[xa + ya * width] = col;
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
                if (col != ALPHA_COL && col != 0xff7f007f) pixels[xa + ya * width] = col;
            }
        }
    }

    public void renderTile(int xp, int yp, Tile tile) { ///TODO do zmiany

        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
            }
        }
    }
}









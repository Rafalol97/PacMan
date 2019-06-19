package rafalwisnia.LevelUtilities;



import rafalwisnia.UI.Sprite;
import rafalwisnia.UI.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;


/**
 * Screen.java Klasa przechowuje tablice pikseli renderowana na ekran. Zawiera funkcje przetwarzajace wczesniej zapisane grafiki w obiektu klas Sprite i SpriteSheet.
 *
 */
public class Screen {

    private int width, height;
    public int[] pixels;
    private String pathToBorder = "resources/textures/Board/boardKoncowy.bmp";
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

    /**
     * Funkcja wczytujaca piksele z obrazu przedstawiajacego tlo gry i obramowanie do tablicy pixels
     */

    public void renderBorder() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 1300 || x < 300 || y > 800 || y < 100)
                    pixels[x + y * width] = Border.getRGB(x, y);
            }
        }
    }

    /**
     * Funkcja przepisujaca piksele z otrzymanych parametrow
     * @param xp  wspolrzedna x miejsca rozpoczecia wczytywania na tablice pikseli
     * @param yp  wspolrzedna y miejsca rozpoczecia wczytywania na tablice pikseli
     * @param sprite obiekt klasy Sprite przechowywujacy grafike w pikselach i rozmiar
     * @param flip  parametr decydujacy o kolejnosci wczytywania pikseli (umozliwia odbicia lustrzane w poziomie i pionie)
     *  ALPHA_COL - parametr okreslajacy wartosc piksela nie przepisywanego z tablicy pikseli przechowywanego w obiekcie klasy Sprite
     */

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

    /**
     *Funkcja przepisujaca piksele z otrzymanych parametrow
     * @param xp wspolrzedna x miejsca rozpoczecia wczytywania na tablice pikseli
     * @param yp wspolrzedna y miejsca rozpoczecia wczytywania na tablice pikseli
     * @param sprite obiekt klasy Sprite przechowywujacy grafike w pikselach i rozmiar
     *  ALPHA_COL - parametr okreslajacy wartosc piksela nie przepisywanego z tablicy pikseli przechowywanego w obiekcie klasy Sprite
     */
    public void renderSprite(int xp, int yp, Sprite sprite) {
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
    /**
     * Funkcja przepisujaca piksele z otrzymanych parametrow
     * @param xp  wspolrzedna x miejsca rozpoczecia wczytywania na tablice pikseli
     * @param yp  wspolrzedna y miejsca rozpoczecia wczytywania na tablice pikseli
     * @param sprite obiekt klasy Sprite przechowywujacy grafike w pikselach i rozmiar
     * @param flip  parametr decydujacy o kolejnosci wczytywania pikseli (umozliwia odbicia lustrzane w poziomie i pionie)
     */
    public void renderTile(int xp, int yp, Sprite sprite, int flip) {
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) ys = sprite.SIZE - 1 - y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) xs = sprite.SIZE - 1 - x;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] =sprite.pixels[xs + ys * sprite.SIZE];
            }
        }
    }

    public void renderTileBright(int xp, int yp, Sprite sprite, int flip) {
        Color color ;
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            if (flip == 2 || flip == 3) ys = sprite.SIZE - 1 - y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp;
                int xs = x;
                if (flip == 1 || flip == 3) xs = sprite.SIZE - 1 - x;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                color = new Color(sprite.pixels[xs + ys * sprite.SIZE]);
                if(color.getRGB() == 0xffffff) {
                    pixels[xa + ya * width] = color.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().getRGB();
                } else {
                    pixels[xa + ya * width] = color.brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().brighter().getRGB();
                }
            }
        }
    }
    /**
     *Funkcja przepisujaca piksele z otrzymanych parametrow
     * @param xp wspolrzedna x miejsca rozpoczecia wczytywania na tablice pikseli
     * @param yp wspolrzedna y miejsca rozpoczecia wczytywania na tablice pikseli
     * @param sprite obiekt klasy Sprite przechowywujacy grafike w pikselach i rozmiar

     */
    public void renderLastNumber(int xp, int yp, Sprite sprite) {
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.SIZE-20; x++) {
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] =sprite.pixels[x + y * sprite.SIZE];
            }
        }
    }

    /**
     *Funkcja przepisujaca piksele z otrzymanych parametrow
     * @param xp wspolrzedna x miejsca rozpoczecia wczytywania na tablice pikseli
     * @param yp wspolrzedna y miejsca rozpoczecia wczytywania na tablice pikseli
     * @param sheet obiekt klasy Sprite przechowywujacy grafike w pikselach i rozmiar

     */
    public void renderSheet(int xp, int yp, SpriteSheet sheet) {
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
    public void dlaWisni(int xp, int yp, Color color) {

        for (int y = 0; y < 50; y++) {
            int ya = y + yp;
            for (int x = 0; x < 50; x++) {
                int xa = x + xp;
                if (xa < -50 || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                if (!(x >=1 && x <= 48 && y >= 1 && y <= 48)) {
                pixels[xa + ya * width] = color.getRGB();
                   }


            }
        }
    }
}









package rafalwisnia.UI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

 class SpriteSheet {
    private String path;
    private final int SIZE;
    private final int SPRITE_WIDTH,SPRITE_HEIGHT;
    private  int width, height;
    public int pixels[];

    public static SpriteSheet pacmann = new SpriteSheet("resources/textures/Pacman/teksturyprawdziwe4.png", 1000);

    public SpriteSheet(String path, int SIZE) {
        this.path = path;
        this.SIZE = SIZE;
        SPRITE_WIDTH = SIZE;
        SPRITE_HEIGHT = SIZE;
        try {
            System.out.print("Trying to load: " + path + "...");
            BufferedImage image = ImageIO.read(new File(path));
            System.out.println(" succeeded!");
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(" failed!");
        }

    }

     public int getSPRITE_WIDTH() {
         return SPRITE_WIDTH;
     }
 }

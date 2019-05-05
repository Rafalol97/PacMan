package Level;

import Level.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileGrass  implements Tile {
    private String path;
    private int pixel[][];
    private BufferedImage tileGrass ;


    public TileGrass() {
        try {
            tileGrass =  ImageIO.read(new File("C:\\Users\\rafal\\Desktop\\podloze.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pixel = new int[50][50];
        creatPixels();

    }
    @Override
    public int getPixelFromTile(int x, int y) {
        return pixel[x][y];
    }

    @Override
    public void creatPixels() {
        for(int y=0;y<50;y++){
            for(int x=0;x<50;x++){
                pixel[x][y]=tileGrass.getRGB(x,y);
            }
        }
    }
}

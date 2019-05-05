package Level;

import Level.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileBrick implements Tile {
        private String path;
        private int pixel[];
        private BufferedImage tileBrick ;


        public TileBrick() {
            try {
                tileBrick =  ImageIO.read(new File("C:\\Users\\rafal\\Desktop\\hehebitmapa.bmp"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pixel = new int[50*50];
            creatPixels();
        }
        @Override
        public int getPixelFromTile(int x, int y) {
            return pixel[x+y*50];
        }

        @Override
        public void creatPixels() {
            for(int y=0;y<50;y++){
                for(int x=0;x<50;x++){
                    pixel[x+y*50]=tileBrick.getRGB(x,y);
                }
            }
        }
    }



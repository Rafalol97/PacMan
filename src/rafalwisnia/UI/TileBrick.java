package rafalwisnia.UI;


import rafalwisnia.LevelUtilities.Screen;


import java.awt.image.BufferedImage;


public class TileBrick extends Tile {
        public TileBrick(Sprite sprite) {
           super(sprite);
        }
        public void render(int x, int y, Screen screen){
            screen.renderTile(x,y,this);
        }
    }



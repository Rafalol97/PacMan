package rafalwisnia.UI;

import rafalwisnia.LevelUtilities.Screen;

public class Tile{
    public Sprite sprite;
   // int getPixelFromTile(int x,int y);
   // void creatPixels();
    public static Tile podloga = new TileGrass(Sprite.podloga);
    public static Tile brick = new TileBrick(Sprite.brick);

   public void render(int x, int y, Screen screen) {
   }

    public boolean solid() {
        return false;
    }

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }
}

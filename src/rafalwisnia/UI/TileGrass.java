package rafalwisnia.UI;



import rafalwisnia.LevelUtilities.Screen;


public class TileGrass extends Tile {
    private String path;


    public TileGrass(Sprite sprite) {
        super(sprite);
    }
    public void render(int x, int y, Screen screen){
        screen.renderTile(x,y,this);
    }

}

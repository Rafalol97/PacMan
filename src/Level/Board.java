package Level;

import UI.Entity;
import UI.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public List<Entity> ghosts = new ArrayList<Entity>();
    public Entity Pacmann = new Entity();
    // Oznacznik klockow z ktorych sie sklada mapa - 0 droga 1 - sciana 2- podłoga z coinem
    private int tiles[][]={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0},
            {1,0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,1,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {1,0,1,0,0,0,1,1,0,0,1,0,0,0,0,1,0,0,0,0},
            {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    };
    private Tile[] blankTile;
    private int pixels[];
    private int width;
    private int height;
    public Board(int width,int height) {
        this.width = width;
        this.pixels = new int[width*height];
        this.blankTile = new Tile[2];
        this.blankTile[0] = new TileGrass();
        this.blankTile[1] = new TileBrick();
        setDefaultPixels();

    }


    public int getPixelsFromBoard(int x, int y) {
        return pixels[x+y*width];
    }

    public void  setDefaultPixels(){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                if(tiles[y][x]==0)renderTile(Sprite.podloga,x,y);
                else if(tiles[y][x]==1) renderTile(Sprite.podlogaCoin,x,y);
                else if(tiles[y][x]==2)renderTile(Sprite.brick,x,y);
            }
        }
    }


    private void renderTile(Sprite sprite,int offsetX,int offsetY ){
        for(int j =0;j<sprite.SIZE;j++  ){
            for(int i=0;i<sprite.SIZE;j++ ){
                pixels[offsetX*50+offsetY*50*width] = sprite.getPixel(i,j);

            }
        }
    }
    public void renderTiles(){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                if(tiles[y][x]==0)renderTile(Sprite.podloga,x,y);
                else if(tiles[y][x]==1) renderTile(Sprite.podlogaCoin,x,y);
                else if(tiles[y][x]==2)renderTile(Sprite.brick,x,y);
            }
        }

    }

}

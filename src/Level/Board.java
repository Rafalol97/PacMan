package Level;

import Level.Tile;
import Level.TileBrick;
import Level.TileGrass;

public class Board {
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
    private int pixels[][];
    private int width;
    private int height;
    public Board(int width,int height) {
        this.width = width;
        this.pixels = new int[width][height];
        this.blankTile = new Tile[2];
        this.blankTile[0] = new TileGrass();
        this.blankTile[1] = new TileBrick();
        setDefaultPixels();

    }


    public int getPixelsFromBoard(int x, int y) {
        return pixels[x][y];
    }

    public void  setDefaultPixels(){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                writePixelsToTile(x,y);
            }
        }
    }

    private void writePixelsToTile(int x,int y){
        if(tiles[y][x]==1) {
            for (int j = 0; j < 50; j++) {
                for (int i = 0; i < 50; i++) {
                     pixels[x*49+i][y*49+j] = blankTile[1].getPixelFromTile(i, j);

                }
            }

        }
        else if (tiles[y][x] == 0) {
            for (int j = 0; j < 50; j++) {
                for (int i = 0; i < 50; i++) {
                    pixels[x*49+i][y*49+j] = blankTile[0].getPixelFromTile(i, j);
                }
            }
        }
    }

}

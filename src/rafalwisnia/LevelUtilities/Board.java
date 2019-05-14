package rafalwisnia.LevelUtilities;


import rafalwisnia.UI.Sprite;
import rafalwisnia.UI.Tile;

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

    private int pixels[];
    private int width;
    private int height;
    public Board(int width,int height) {
        this.width = width;
        this.pixels = new int[width*height];

    }

    public int[][] getTiles() {
        return tiles;
    }

    private void renderTile(Sprite sprite, int offsetX, int offsetY ){
        for(int j =0;j<sprite.SIZE;j++  ){
            for(int i=0;i<sprite.SIZE;j++ ){
                pixels[offsetX*50+offsetY*50*width] = sprite.getPixel(i,j);

            }
        }
    }


    public void render(Screen screen){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                if(tiles[y][x]==1) screen.renderTile((x*50)+300,(y*50)+100, Tile.brick);
                else if(tiles[y][x]==0) screen.renderTile((x*50)+300,(y*50)+100, Tile.podloga);
            }
        }
    }
    public int[] getTileWhereAmI(int x,int y){
        int wspolrzedne[] = new int [2];
       wspolrzedne[1]=(x-x%50-300)/50;
       wspolrzedne[0]=(y-y%50-100)/50;
       return  wspolrzedne;

    }
    public int getTileAlias(int y, int x){
        if(y<0||y>13|x<0||x>19)return 1;
        return tiles[y][x];
    }




}

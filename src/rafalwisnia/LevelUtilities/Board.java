package rafalwisnia.LevelUtilities;


import rafalwisnia.UI.Sprite;

public class Board {
    // Oznacznik klockow z ktorych sie sklada mapa - 0 droga 1 - sciana 2- podłoga z coinem
    private int tiles[][]={
            {1,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,2},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {3,0,8,11,11,11,2,0,8,11,11,88,0,7,11,11,11,88,0,3},
            {3,0,0,0,0,0,3,0,0,5,6,0,0,3,0,0,0,0,0,3},
            {3,0,8,11,88,0,5,88,0,0,0,0,8,6,0,8,11,88,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {5,11,11,88,0,4, 0,1,88,0,0,8,2,0,4, 0,8,11,11,6},
            {0,0, 0, 0, 0,3, 0,3,0, 0,0,0,3,0,3, 0,0,0 ,0 ,0},
            {1,11,11,88,0,3,0,3,0, 0,0,0,3,0,3,0,8,11,11,2},
            {3,0, 0, 0, 0,44, 0,5,11, 11,11,11,6,0, 44,0, 0,0, 0,3},
            {3,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,3},
            {3,0,5,11,11,11,88,0,8,11,11,88,0,8,11,11,11,6,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {5,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,6},
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
                if(tiles[y][x]==0) screen.renderTile((x*50)+300,(y*50)+100, Sprite.podloga);
                else if(tiles[y][x]==1) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_1);
                else if(tiles[y][x]==2) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_2);
                else if(tiles[y][x]==3) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_3);
                else if(tiles[y][x]==4) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_4);
                else if(tiles[y][x]==5) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_5);
                else if(tiles[y][x]==6) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_6);
                else if(tiles[y][x]==7) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_7);
                else if(tiles[y][x]==8) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_8);
                else if(tiles[y][x]==9) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_9);
                else if(tiles[y][x]==10) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_10);
                else if(tiles[y][x]==11) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_11);
                else if(tiles[y][x]==12) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_12);
                else if(tiles[y][x]==44) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_4,2);
                else if(tiles[y][x]==88) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_8,1);
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

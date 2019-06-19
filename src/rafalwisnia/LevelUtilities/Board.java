package rafalwisnia.LevelUtilities;


import rafalwisnia.UI.Sprite;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Klasa przechowuje tablice reprezentujaca plansze gry na podstawie ktorej wczytywane sa odpowiednie tekstury przechowywane w obiekcie klasy Sprite
 *
 */
public class Board {
    public Board() {

    }

    // Oznacznik klockow z ktorych sie sklada mapa - 0 droga 1 - sciana 2- podłoga z coinem
    private static int tiles[][]={
            {1,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,2},
            {3,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,3},
            {3,0,8,11,11,11,2,0,8,13,16,88,0,7,11,11,11,88,0,3},
            {3,0,0,0,0,-1,3,0,0,3,3,0,0,3,-1,0,0,0,0,3},
            {3,0,8,11,88,0,5,88,0,5,6,0,8,6,0,8,11,88,0,3},
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3},
            {5,11,11,88,0,4, 0,1,88,17,17,8,2,0,4, 0,8,11,11,6},
            {0,0, 0, 0, 0,3, 0,3,0, 0,0,0,3,0,3, 0,0,0 ,0 ,0},
            {1,11,11,88,0,3,0,3,0, 0,0,0,3,0,3,0,8,11,11,2},
            {3,0, 0, 0, 0,44, 0,5,11, 11,11,11,6,0, 44,0, 0,0, 0,3},
            {3,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,3},
            {3,0,5,15,11,11,88,0,8,11,11,88,0,8,11,11,14,6,0,3},
            {3,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,3},
            {5,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,11,6},
    };

    /**
     * Metoda renderujaca odpowiednie bloki na podstawie liczb w tablice tiles[][]
     * @param screen - referencja do obiektu klasy Screen
     */
    public void render(Screen screen){
        for (int y=0;y<14;y++){
            for(int x=0;x<20;x++){
                if(tiles[y][x]==0) screen.renderTile((x*50)+300,(y*50)+100, Sprite.podloga,0);
                else if(tiles[y][x]==1) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_1,0);
                else if(tiles[y][x]==2) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_2,0);
                else if(tiles[y][x]==3) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_3,0);
                else if(tiles[y][x]==4) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_4,0);
                else if(tiles[y][x]==5) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_5,0);
                else if(tiles[y][x]==6) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_6,0);
                else if(tiles[y][x]==7) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_7,0);
                else if(tiles[y][x]==8) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_8,0);
                else if(tiles[y][x]==9) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_9,0);
                else if(tiles[y][x]==10) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_10,0);
                else if(tiles[y][x]==11) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_11,0);
                else if(tiles[y][x]==12) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_12,0);
                else if(tiles[y][x]==44) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_4,2);
                else if(tiles[y][x]==88) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_8,1);
                else if(tiles[y][x]==13) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_13,0);
                else if(tiles[y][x]==14) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_13,2);
                else if(tiles[y][x]==15) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_13,3);
                else if(tiles[y][x]==16) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_13,4);
                else if(tiles[y][x]==17) screen.renderTile((x*50)+300,(y*50)+100, Sprite.brick_17,0);

                else if (tiles[y][x] == 18) screen.renderTile((x * 50) + 300, (y * 50) + 100, Sprite.brick_18, 0);
                else if (tiles[y][x] == 19) screen.renderTile((x * 50) + 300, (y * 50) + 100, Sprite.brick_19, 0);
                else if (tiles[y][x] == 20) screen.renderTile((x * 50) + 300, (y * 50) + 100, Sprite.brick_20, 0);
                else if (tiles[y][x] == 21) screen.renderTile((x * 50) + 300, (y * 50) + 100, Sprite.brick_18, 1);
            }
        }
    }

    /**
     * Metoda zamienia  wspolrzedne na ekranie w dwie wartosci wskazujace wartosc reporezenetujaca obiekt na ekranie
     * @param x - wspolrzedna x ekranu
     * @param y - wspolrzedna y ekranu
     * @return tablica wspolrzednych w postaci {y,x}
     */

    public int[] getTileWhereAmI(int x,int y){
        int wspolrzedne[] = new int [2];
       wspolrzedne[1]=(x-x%50-300)/50;
       wspolrzedne[0]=(y-y%50-100)/50;
       return  wspolrzedne;

    }

    /**
     Metoda zwracajaca wartosc reprezentujaca obiekt na ekranie z tablicy tiles pod wspolrzednymi {y,x}
     * @param y - wspolrzedna y do tablicy tiles
     * @param x - wspolrzedna x do tablicy tiles
     * @return alias bloku
     */

    public int getTileAlias(int y, int x){
        if(y<0||y>13|x<0||x>19)return 1;
        return tiles[y][x];
    }
    public static void readMatrixFromGame() {
        String pobranaLinia;
        String[] czesci;
        FileWriter fileWriter;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "pacjar");
        fileChooser.setFileFilter(filter);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
                try {
                    for (int i = 0; i < 14; i++) {
                        pobranaLinia = bufferedReader.readLine();
                        czesci = pobranaLinia.split(",");
                        for (int j = 0; j < 20; j++) {
                            if(Integer.parseInt(czesci[j])==-2){
                                Level.PacX = j*50+300;
                                Level.PacY =i*50+100;
                                Board.tiles[i][j]=0;
                            }
                            else{
                                Board.tiles[i][j] = Integer.parseInt(czesci[j]);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }
}

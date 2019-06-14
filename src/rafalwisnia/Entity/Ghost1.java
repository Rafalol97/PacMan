package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;

import java.util.Random;

public class Ghost1 extends Ghost  {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];
    private Sprite sprite;
     Random random = new Random();

    public Ghost1(int x,int y,Board board) {
        klatkiDuszekRight[0] = new AnimatedSprite(Sprite.ghost_1_1);
        klatkiDuszekRight[1] = new AnimatedSprite(Sprite.ghost_1_2);

        klatkiDuszekDown[0] = new AnimatedSprite(Sprite.ghost_1_3);
        klatkiDuszekDown[1] = new AnimatedSprite(Sprite.ghost_1_4);

        klatkiDuszekUp[0] = new AnimatedSprite(Sprite.ghost_1_5);
        klatkiDuszekUp[1] = new AnimatedSprite(Sprite.ghost_1_6);

        klatkiDuszekLeft[0] = new AnimatedSprite(Sprite.ghost_1_7);
        klatkiDuszekLeft[1] = new AnimatedSprite(Sprite.ghost_1_8);

        listaKlatek.add(klatkiDuszekUp);
        listaKlatek.add(klatkiDuszekRight);
        listaKlatek.add(klatkiDuszekDown);
        listaKlatek.add(klatkiDuszekLeft);

        this.x=x;
        this.y=y;
        frameSpeed = 10;


    }

    public void render(Screen screen){
        if (!isScared()) {
            sprite =listaKlatek.get(directionIter)[klatka].getSprite();
        }
        else{
            sprite = klatkiDuszekPrzestraszony[klatka].getSprite();
        }
        screen.renderMob(x,y,sprite,0);
    }



    @Override
    public void update(Board board) {

        if(random.nextInt(4)==0){
            changeToRandomDirection(board);
        }

        if (chceckforObstacles(board)) {
            changeToRandomDirection(board);
        } else {
            move();
        }

    }

}

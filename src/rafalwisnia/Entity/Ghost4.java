package rafalwisnia.Entity;

import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

;import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost4 extends Ghost implements EventListener {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];
    private Sprite sprite;
    Random random = new Random();


    public Ghost4(int x,int y,Board board, Level parentLevel) {
        klatkiDuszekRight[0] = new AnimatedSprite(Sprite.ghost_4_1);
        klatkiDuszekRight[1] = new AnimatedSprite(Sprite.ghost_4_2);

        klatkiDuszekDown[0] = new AnimatedSprite(Sprite.ghost_4_3);
        klatkiDuszekDown[1] = new AnimatedSprite(Sprite.ghost_4_4);

        klatkiDuszekUp[0] = new AnimatedSprite(Sprite.ghost_4_5);
        klatkiDuszekUp[1] = new AnimatedSprite(Sprite.ghost_4_6);

        klatkiDuszekLeft[0] = new AnimatedSprite(Sprite.ghost_4_7);
        klatkiDuszekLeft[1] = new AnimatedSprite(Sprite.ghost_4_8);

        listaKlatek.add(klatkiDuszekUp);
        listaKlatek.add(klatkiDuszekRight);
        listaKlatek.add(klatkiDuszekDown);
        listaKlatek.add(klatkiDuszekLeft);

        this.x=x;
        this.y=y;
        direction = Directions.UP;
        frameSpeed = 10;
        parentLevel.setEventListenerGhost4(this);

    }

    public void render(Screen screen) {
        if (isGhostVisible()) {
            if (!isScared()) {
                sprite = listaKlatek.get(directionIter)[klatka].getSprite();
            } else {
                sprite = klatkiDuszekPrzestraszony[klatka].getSprite();
            }
            screen.renderMob(x, y, sprite, 0);
        }
    }


    @Override
    public void update(Board board) {
        if (started) {
            if (random.nextInt(5) == 0) {
                changeToRandomDirection(board);
            }

            if (chceckforObstacles(board, 1)) {
                changeToRandomDirection(board);
            } else {
                move();
            }
        } else if (this.frameAmountLeave != 0) {
            direction = Directions.UP;
            frameAmountLeave--;
            move();
            if(frameAmountLeave==0){
                started=true;
            }
        }

    }
    public void onEvent(Event event) {
        if(event.getType()==Event.Type.StartGhost4)
        {
            frameAmountLeave= (150);
        }
    }
    public void resetToDefault() {
        frameAmountLeave=0;
        scared=false;
        started = false;
        this.x=800;
        this.y=500;
    }

    @Override
    public void updateAIbyCherry(Board board, int PacManX, int PacManY) {

    }
}

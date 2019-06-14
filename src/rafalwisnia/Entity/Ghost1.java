package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;

import java.util.Random;

public class Ghost1 extends Ghost implements EventListener {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];
    private Sprite sprite;
     Random random = new Random();

    public Ghost1(int x, int y, Board board, Level parentLevel) {
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
        parentLevel.setEventListenerGhost1(this);
    }


    public void render(Screen screen) {
        if (this.isGhostVisible()) {
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
            //Jesli sobie chodzi
            if (random.nextInt(4) == 0) {
                changeToRandomDirection(board);
            }

            if (chceckforObstacles(board, 1)) {
                changeToRandomDirection(board);
            } else {
                move();
            }

            //Jesli jest w boxie
        } else if (this.frameAmountLeave != 0) {
            direction = Directions.UP;
            frameAmountLeave--;
            move();
            if(frameAmountLeave==0){
                started=true;
            }
        }
    }

    @Override
    public void onEvent(Event event) {
        if(event.getType()==Event.Type.StartGhost1)
        {
            frameAmountLeave= (100);
        }
    }

    @Override
    public void resetToDefault() {
        frameAmountLeave=0;
        scared=false;
        started = false;
        this.x=750;
        this.y=450;

    }

    @Override
    public void updateAIbyCherry(Board board, int PacManX, int PacManY) {
        if(this.x == PacManX || this.y == PacManY) {
            if(PacManX < this.x){
                if(this.direction == Directions.LEFT) {
                    if(!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                        System.out.println("WIDZE PACMANA SKURWIELA PO LEWO");
                    }
                }
            }
            if(PacManX > this.x) {
                if(this.direction == Directions.RIGHT) {
                    if(!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                        System.out.println("WIDZE PACMANA SKURWIELA PO PRAWO");
                    }
                }
            }
            if(PacManY < this.y) {
                if(this.direction == Directions.UP) {
                    if(!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                        System.out.println("WIDZE PACMANA SKURWIELA NA GORZE");
                    }
                }
            }
            if(PacManY > this.y) {
                if(this.direction == Directions.DOWN) {
                    if(!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                        System.out.println("WIDZE PACMANA SKURWIELA NA DOLE");
                    }

                }
            }
        }
    }
}

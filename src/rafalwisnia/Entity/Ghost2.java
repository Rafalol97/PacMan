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

public class Ghost2 extends Ghost implements EventListener {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>();
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];
    private Sprite sprite;
    Random random = new Random();

    public Ghost2(int x, int y, Board board,Level parentLevel) {
        klatkiDuszekRight[0] = new AnimatedSprite(Sprite.ghost_2_1);
        klatkiDuszekRight[1] = new AnimatedSprite(Sprite.ghost_2_2);

        klatkiDuszekDown[0] = new AnimatedSprite(Sprite.ghost_2_3);
        klatkiDuszekDown[1] = new AnimatedSprite(Sprite.ghost_2_4);

        klatkiDuszekUp[0] = new AnimatedSprite(Sprite.ghost_2_5);
        klatkiDuszekUp[1] = new AnimatedSprite(Sprite.ghost_2_6);

        klatkiDuszekLeft[0] = new AnimatedSprite(Sprite.ghost_2_7);
        klatkiDuszekLeft[1] = new AnimatedSprite(Sprite.ghost_2_8);

        listaKlatek.add(klatkiDuszekUp);
        listaKlatek.add(klatkiDuszekRight);
        listaKlatek.add(klatkiDuszekDown);
        listaKlatek.add(klatkiDuszekLeft);


        this.x = x;
        this.y = y;
        direction = Directions.UP;
        frameSpeed = 10;
        parentLevel.setEventListenerGhost2(this);
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
            if (random.nextInt(3) == 0) {
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
        if(event.getType()==Event.Type.StartGhost2)
        {
            frameAmountLeave= (100);
        }
    }
    public void resetToDefault() {
        frameAmountLeave=0;
        scared=false;
        started = false;
        chase = false;
        lastSaw = -1;
        this.x=800;
        this.y=450;
    }

    @Override
    public void updateAIbyCherry(Board board, int PacManX, int PacManY) {
        if(this.x == PacManX || this.y == PacManY) {
            if(PacManX < this.x&&this.direction == Directions.LEFT&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                //  System.out.println("WIDZE PACMANA SKURWIELA PO LEWO");
                lastSaw=3;
                chase=true;
            }
            else  if(PacManX > this.x&&this.direction == Directions.RIGHT&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {
                //  System.out.println("WIDZE PACMANA SKURWIELA PO PRAWO");
                lastSaw=1;
                chase=true;

            }
            else  if(PacManY < this.y&&this.direction == Directions.UP&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {
                //   System.out.println("WIDZE PACMANA SKURWIELA NA GORZE");
                lastSaw=0;
                chase=true;
            }
            else if(PacManY > this.y&&this.direction == Directions.DOWN&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {

                //   System.out.println("WIDZE PACMANA SKURWIELA NA DOLE");
                lastSaw=2;
                chase=true;
            }
            else{
                lastSaw=-1;

            }

        }
    }

    public boolean andDirectionIsGOOD(Board board, int PacManX, int PacManY) {
        if(wrazieW == 3) {
            return true;
        }
        else {
            if (this.direction == Directions.UP && PacManY > this.y) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.DOWN && PacManY < this.y) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.RIGHT && PacManX < this.x) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.LEFT && PacManX > this.x) {
                wrazieW++;
                return false;
            } else return true;
        }
    }

    public void chceckForErrors(Board board, int PacManX, int PacManY) {
        while(chceckforObstacles(board, 1) || !andDirectionIsGOOD(board, PacManX, PacManY)) {
            if (this.direction == Directions.UP) {
                this.direction = Directions.RIGHT;
            } else if (this.direction == Directions.RIGHT) {
                this.direction = Directions.DOWN;;
            } else if (this.direction == Directions.DOWN) {
                this.direction = Directions.LEFT;
            } else if (this.direction == Directions.LEFT) {
                this.direction = Directions.UP;
            }
        }
    }

    public void updateChase(Board board, int PacManX, int PacManY) {
        if(this.x%50==0&&this.y%50==0) {
            if (this.lastSaw == 0) {
                this.direction = Directions.UP;
            } else if (this.lastSaw == 1) {
                this.direction = Directions.RIGHT;
            } else if (this.lastSaw == 2) {
                this.direction = Directions.DOWN;
            } else if (this.lastSaw == 3) {
                this.direction = Directions.LEFT;
            } else if (PacManY < this.y) {
                this.direction = Directions.UP;
            } else if (PacManY > this.y) {
                this.direction = Directions.DOWN;
            } else if (PacManX < this.x) {
                this.direction = Directions.LEFT;
            } else if (PacManX > this.x) {
                this.direction = Directions.RIGHT;
            }

            if (chceckforObstacles(board, 1)) {
                if (this.direction == Directions.LEFT || this.direction == Directions.RIGHT) {
                    if (PacManY < this.y) {
                        System.out.println("UP od sciany");
                        this.direction = Directions.UP;
                    } else if (PacManY > this.y) {
                        System.out.println("DOWN od sciany");
                        this.direction = Directions.DOWN;
                    } else {
                        if (this.direction == Directions.LEFT) this.direction = Directions.RIGHT;
                        else if (this.direction == Directions.RIGHT) this.direction = Directions.LEFT;
                    }
                } else if (this.direction == Directions.UP || this.direction == Directions.DOWN) {
                    if (PacManX < this.x) {
                        System.out.println("LEFT od sciany");
                        this.direction = Directions.LEFT;
                    } else if (PacManX > this.x) {
                        System.out.println("RIGHT od sciany");
                        this.direction = Directions.RIGHT;
                    } else {
                        if (this.direction == Directions.UP) this.direction = Directions.DOWN;
                        else if (this.direction == Directions.DOWN) this.direction = Directions.UP;
                    }
                }
            }

            chceckForErrors(board, PacManX, PacManY);
            wrazieW = 0;
            System.out.println("yo2");
        }
        move();
    }
}







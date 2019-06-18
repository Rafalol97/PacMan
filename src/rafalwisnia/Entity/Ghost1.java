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
    boolean kontrolna = false;
    private int waitAfterDeath;


    public Ghost1(int x, int y, Board board, Level parentLevel,int speed) {
        super(x,y,speed);
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
        if(dead){
            sprite=oczyDuszkaPoSmierci[directionIter];
            screen.renderMob(x,y,sprite,0);
        }
        else if (this.isGhostVisible()) {
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
        if(waitAfterDeath>0){
            waitAfterDeath--;
        }
        else if(dead){
            if(this.y<this.yStartowe){
                this.direction=Directions.DOWN;
                this.directionIter=1;
            }
            if(this.y>this.yStartowe){
                this.direction=Directions.UP;
                this.directionIter=2;
            }
            if(this.x<this.xStartowe){
                this.direction=Directions.RIGHT;
                this.directionIter=0;
            }
            if(this.x>this.xStartowe){
                this.direction=Directions.LEFT;
                this.directionIter=3;
            }
            move();

            if(this.x==this.xStartowe&&this.y==this.yStartowe){
                System.out.println("I'm alive");
                dead=false;
                resetToDefault();
                frameAmountLeave=100;


            }

        }
        else {
            if (started) {
                //Jesli sobie chodzi
                if (random.nextInt(4) == 0) {
                    changeToRandomDirection(board);
                }

                if (chceckforObstacles(board, 1)) {
                    changeToRandomDirection(board);
                } else {
                    //System.out.println("i move");
                    move();
                }
                //Jesli jest w boxie
            } else if (this.frameAmountLeave != 0) {
                direction = Directions.UP;
                frameAmountLeave--;
                move();
                //System.out.println("i start");
                if (frameAmountLeave == 0) {
                    started = true;
                }

            }
        }
    }

    @Override
    public void onEvent(Event event) {
        int odleglosc=0;
        if(event.getType()==Event.Type.Dead)
        {
            dead=true;

            scared=false;
            chase=false;
            lastSaw=-1;
            waitAfterDeath=  (60*speedTemp);
            direction=Directions.UP;
            //System.out.println("I'm dead");
        }
        if(event.getType()==Event.Type.StartGhost1)

        {
            frameAmountLeave=100;
            directionIter=0;
        }
    }

    @Override
    public void resetToDefault() {
        frameAmountLeave=0;
        scared=false;
        started = false;
        chase = false;
        lastSaw = -1;
        this.x=750;
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
            if (this.direction == Directions.UP && PacManY < this.y) {
                return true;
            } else if (this.direction == Directions.DOWN && PacManY > this.y) {
                return true;
            } else if (this.direction == Directions.RIGHT && PacManX > this.x) {
                return true;
            } else if (this.direction == Directions.LEFT && PacManX < this.x) {
                return true;
            } else {
                wrazieW++;
                return false;
            }
        }
    }

    public void chceckForErrors(Board board, int PacManX, int PacManY) {
        while(chceckforObstacles(board, 1) || !andDirectionIsGOOD(board, PacManX, PacManY)) {
            if (this.direction == Directions.UP) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            } else if (this.direction == Directions.RIGHT) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (this.direction == Directions.DOWN) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (this.direction == Directions.LEFT) {
                this.direction = Directions.UP;
                directionIter = 0;
            }
            System.out.println("Jestem w petli gonienia");
        }
    }


    public void updateChase(Board board, int PacManX, int PacManY) {
        if(this.x%50==0&&this.y%50==0) {
            if (this.lastSaw == 0) {
                this.direction = Directions.UP;
                directionIter = 0;
            } else if (this.lastSaw == 1) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            } else if (this.lastSaw == 2) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (this.lastSaw == 3) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (PacManY <= this.y) {
                this.direction = Directions.UP;
                directionIter = 0;
            } else if (PacManY >= this.y) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (PacManX <= this.x) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (PacManX >= this.x) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            }
            lastSaw = -1;

            if (chceckforObstacles(board, 1)) {
                if (this.direction == Directions.LEFT || this.direction == Directions.RIGHT) {
                    if (PacManY < this.y) {
                        this.direction = Directions.UP;
                        directionIter = 0;
                    } else if (PacManY > this.y) {
                        this.direction = Directions.DOWN;
                        directionIter = 2;
                    } else {
                        if (this.direction == Directions.LEFT) {this.direction = Directions.RIGHT; directionIter = 1;}
                        else if (this.direction == Directions.RIGHT) {this.direction = Directions.LEFT; directionIter = 3;}
                    }
                } else if (this.direction == Directions.UP || this.direction == Directions.DOWN) {
                    if (PacManX < this.x) {
                        this.direction = Directions.LEFT;
                        directionIter = 3;
                    } else if (PacManX > this.x) {
                        this.direction = Directions.RIGHT;
                        directionIter = 1;
                    } else {
                        if (this.direction == Directions.UP) {this.direction = Directions.DOWN; directionIter = 2;}
                        else if (this.direction == Directions.DOWN) {this.direction = Directions.UP; directionIter = 0;}
                    }
                }
            }
            chceckForErrors(board, PacManX, PacManY);
            kontrolna = false;
            wrazieW = 0;
        }
        move();
    }


}

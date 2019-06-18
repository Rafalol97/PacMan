package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;
import java.util.Random;

public abstract class Ghost extends Mob {

    Sprite[] oczyDuszkaPoSmierci = new Sprite[4];

    AnimatedSprite klatkiDuszekPrzestraszony[] = new AnimatedSprite[2];
    static Random random = new Random();
    int frameAmountLeave;

    private boolean leaveNest;
    boolean dead;
    boolean scared;
    boolean ghostVisible;
    boolean started;
    public boolean chase;
    int lastSaw;
    int wrazieW = 0;
    int xStartowe,yStartowe;


    boolean kontrolna = false;
    int do4 = 0;

    public boolean isScared() {
        return scared;
    }

    public void setScared(boolean scared) {
        this.scared = scared;
    }

    public abstract void updateAIbyCherry(Board board, int PacManX, int PacManY);

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    Ghost(int xStartowe, int yStartowe,int speed) {
        super(speed);
        oczyDuszkaPoSmierci[0]=Sprite.oczy_Prawo;
        oczyDuszkaPoSmierci[1]=Sprite.oczy_Dol;
        oczyDuszkaPoSmierci[2]=Sprite.oczy_Gora;
        oczyDuszkaPoSmierci[3]=Sprite.oczy_Lewa;

        this.xStartowe=xStartowe;
        this.yStartowe=yStartowe;
        this.speed = speed;
        speedTemp=2;
        leaveNest = false;
        klatkiDuszekPrzestraszony[0] = new AnimatedSprite(Sprite.duszekPrzestraszony1);
        klatkiDuszekPrzestraszony[1] = new AnimatedSprite(Sprite.duszekPrzestraszony2);
    }

    @Override
    public void changeFrame() {
        if (frameWait >= frameSpeed) {
            if (klatka >= 1) {
                kierunekKlatek = false;
            }
            if (klatka <= 0) {
                kierunekKlatek = true;
            }
            if (kierunekKlatek) klatka++;
            else klatka--;
            frameWait = 0;
        }
    }

    @Override
    public abstract void render(Screen screen);

    @Override
    public abstract void update(Board board);



    void changeToRandomDirection(Board board) {
        if (this.x % 50 == 0 && this.y % 50 == 0) {
            ArrayList<Integer> lista = new ArrayList<>();
            if (checkPossibleDirectionChangeGhost(Directions.DOWN, board) && direction != Directions.DOWN) {
                lista.add(2);
            }
            if (checkPossibleDirectionChangeGhost(Directions.UP, board) && direction != Directions.UP) {
                lista.add(0);
            }
            if (checkPossibleDirectionChangeGhost(Directions.RIGHT, board) && direction != Directions.RIGHT) {
                lista.add(1);
            }
            if (checkPossibleDirectionChangeGhost(Directions.LEFT, board) && direction != Directions.LEFT) {
                lista.add(3);
            }
            if (lista.size() != 0) {
                int los = lista.get(random.nextInt(lista.size()));
                if (los == 0) {
                    direction = Directions.UP;
                    directionIter = 0;
                }
                if (los == 1) {
                    direction = Directions.RIGHT;
                    directionIter = 1;
                }
                if (los == 2) {
                    direction = Directions.DOWN;
                    directionIter = 2;
                }
                if (los == 3) {
                    direction = Directions.LEFT;
                    directionIter = 3;
                }
            }
        }
    }

    public abstract void resetToDefault();

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isGhostVisible() {
        return ghostVisible;
    }

    public void setGhostVisible(boolean ghostVisible) {
        this.ghostVisible = ghostVisible;
    }
    public abstract void updateChase(Board board, int PacManX, int PacManY);

    public boolean andDirectionIsGOODscared(Board board, int PacManX, int PacManY) {
        if(this.wrazieW == 3) {
            return true;
        }
        else {
            if (this.direction == Directions.UP && PacManY > this.y) {
                return true;
            } else if (this.direction == Directions.DOWN && PacManY < this.y) {
                return true;
            } else if (this.direction == Directions.RIGHT && PacManX < this.x) {
                return true;
            } else if (this.direction == Directions.LEFT && PacManX > this.x) {
                return true;
            } else {
                this.wrazieW++;
                return false;
            }
        }
    }

    public void chceckForErrorsScared(Board board, int PacManX, int PacManY) {
        while(chceckforObstacles(board, 1) || andDirectionIsGOODscared(board, PacManX, PacManY)) {
            if (this.direction == Directions.UP) {
                this.direction = Directions.RIGHT;
            } else if (this.direction == Directions.RIGHT) {
                this.direction = Directions.DOWN;
            } else if (this.direction == Directions.DOWN) {
                this.direction = Directions.LEFT;
            } else if (this.direction == Directions.LEFT) {
                this.direction = Directions.UP;
            }
            System.out.println("Kierunek: "+this.direction);
        }
    }

    public void chceckForErrorsGOOD(Board board, int PacManX, int PacManY) {
        while(chceckforObstacles(board, 1)) {
            if(do4 < 4) {
                if (this.direction == Directions.UP) {
                    if (this.x < PacManX && !kontrolna) {
                        this.direction = Directions.LEFT;
                        kontrolna = true;
                    }
                    if (this.x > PacManX && kontrolna) {
                        this.direction = Directions.RIGHT;
                        kontrolna = false;
                    }
                } else if (this.direction == Directions.RIGHT) {
                    if (this.y < PacManY && kontrolna) {
                        this.direction = Directions.UP;
                        kontrolna = false;
                    }
                    if (this.y > PacManY && !kontrolna) {
                        this.direction = Directions.DOWN;
                        kontrolna = true;
                    }
                } else if (this.direction == Directions.DOWN) {
                    if (this.x < PacManX && kontrolna) {
                        this.direction = Directions.LEFT;
                        kontrolna = false;
                    }
                    if (this.x > PacManX && !kontrolna) {
                        this.direction = Directions.RIGHT;
                        kontrolna = true;
                    }
                } else if (this.direction == Directions.LEFT) {
                    if (this.y < PacManY && !kontrolna) {
                        this.direction = Directions.UP;
                        kontrolna = true;
                    }
                    if (this.y > PacManY && kontrolna) {
                        this.direction = Directions.DOWN;
                        kontrolna = false;
                    }
                }
                do4++;
            } else {
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
            }
        }
    }

    public void updateScared(Board board, int PacManX, int PacManY) {
        if(this.x%50==0&&this.y%50==0) {
            if(Math.abs(this.x-PacManX) < 200 && Math.abs(this.y-PacManY) < 200) {
                if (PacManY < this.y) {
                    this.direction = Directions.DOWN;
                    System.out.println("ide w dol bo pac man nademna");
                } else if (PacManY > this.y) {
                    this.direction = Directions.UP;
                    System.out.println("ide w gore bo pac man podemna");
                } else if (PacManX < this.x) {
                    this.direction = Directions.RIGHT;
                    System.out.println("ide w prawo bo pac man po lewo");
                } else if (PacManX > this.x) {
                    this.direction = Directions.LEFT;
                    System.out.println("ide w lewo bo pac man po prawo");
                }

                chceckForErrorsGOOD(board, PacManX, PacManY);
                do4 = 0;
                this.kontrolna = false;
                move();
                //System.out.println("I run away to: "+this.direction);
            } else {
                if (random.nextInt(4) == 0) {
                    changeToRandomDirection(board);
                }

                if (chceckforObstacles(board, 1)) {
                    changeToRandomDirection(board);
                } else {
                    move();
                }
            }
        }
        else{
            move();
        }

    }
}

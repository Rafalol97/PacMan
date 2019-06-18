package rafalwisnia.Entity;

import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;
import java.util.List;
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

    Ghost(int xStartowe, int yStartowe,double speed) {
        super(speed);
        oczyDuszkaPoSmierci[0]=Sprite.oczy_Prawo;
        oczyDuszkaPoSmierci[1]=Sprite.oczy_Dol;
        oczyDuszkaPoSmierci[2]=Sprite.oczy_Gora;
        oczyDuszkaPoSmierci[3]=Sprite.oczy_Lewa;

        this.xStartowe=xStartowe;
        this.yStartowe=yStartowe;
        this.speed = speed;
        speed=2;
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

    protected void changeDirectionTowardsNode(List<PathFinder.Node> nodes, int[] coordinates) {
        if (nodes.get(0).x > coordinates[0]) {
            direction = Directions.RIGHT;
        }
        if (nodes.get(0).x < coordinates[0]) {
            direction = Directions.LEFT;
        }
        if (nodes.get(0).y > coordinates[1]) {
            direction = Directions.DOWN;
        }
        if (nodes.get(0).y < coordinates[1]) {
            direction = Directions.RIGHT;
        }
    }

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
            if (this.direction == Directions.UP && PacManY < this.y) {
                this.wrazieW++;
                return false;
            } else if (this.direction == Directions.DOWN && PacManY > this.y) {
                this.wrazieW++;
                return false;
            } else if (this.direction == Directions.RIGHT && PacManX > this.x) {
                this.wrazieW++;
                return false;
            } else if (this.direction == Directions.LEFT && PacManX < this.x) {
                this.wrazieW++;
                return false;
            } else {
                return true;
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
        }
    }

    public void updateScared(Board board, int PacManX, int PacManY) {
        if(this.x%50==0&&this.y%50==0) {
            if(Math.abs(this.x%50-PacManX) < 3 || Math.abs(this.y%50-PacManY) < 3) {
                if (PacManY <= this.y) {
                    this.direction = Directions.DOWN;
                } else if (PacManY >= this.y) {
                    this.direction = Directions.UP;
                } else if (PacManX <= this.x) {
                    this.direction = Directions.RIGHT;
                } else if (PacManX >= this.x) {
                    this.direction = Directions.LEFT;
                }

                chceckForErrorsScared(board, PacManX, PacManY);
                this.wrazieW = 0;
                move();
            } else {
                this.update(board);
            }
        }
        move();
    }
}

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
}

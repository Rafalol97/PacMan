package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;

import static rafalwisnia.UI.Sprite.*;

public class Pacmann extends  Mob {
    private Keyboard input;
    private Sprite sprite;
    public int lives =3;
    private boolean enraged;
    public int enrageRate;
    private Directions directionTemp;

    private EventListener eventListener;
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiPacmannUp[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannRight[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannDown[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannLeft[] = new AnimatedSprite[4];

    public boolean isEnraged() {
        return enraged;
    }

    public void setEnraged(boolean enraged) {
        this.enraged = enraged;
    }


    public Pacmann(int x,int y,Keyboard input) {

        klatkiPacmannRight[0]= new AnimatedSprite(pacmann_prawo_0);
        klatkiPacmannRight[1]= new AnimatedSprite(pacmann_prawo_1);
        klatkiPacmannRight[2]= new AnimatedSprite(pacmann_prawo_2);
        klatkiPacmannRight[3]= new AnimatedSprite(pacmann_prawo_3);

        klatkiPacmannLeft[0]= new AnimatedSprite(pacmann_lewo_0);
        klatkiPacmannLeft[1]= new AnimatedSprite(pacmann_lewo_1);
        klatkiPacmannLeft[2]= new AnimatedSprite(pacmann_lewo_2);
        klatkiPacmannLeft[3]= new AnimatedSprite(pacmann_lewo_3);

        klatkiPacmannDown[0] = new AnimatedSprite(pacmann_dol_0);
        klatkiPacmannDown[1] = new AnimatedSprite(pacmann_dol_1);
        klatkiPacmannDown[2] = new AnimatedSprite(pacmann_dol_2);
        klatkiPacmannDown[3] = new AnimatedSprite(pacmann_dol_3);

        klatkiPacmannUp[0] = new AnimatedSprite(pacmann_gora_0);
        klatkiPacmannUp[1] = new AnimatedSprite(pacmann_gora_1);
        klatkiPacmannUp[2] = new AnimatedSprite(pacmann_gora_2);
        klatkiPacmannUp[3] = new AnimatedSprite(pacmann_gora_3);

        listaKlatek.add(klatkiPacmannUp);
        listaKlatek.add(klatkiPacmannRight);
        listaKlatek.add(klatkiPacmannDown);
        listaKlatek.add(klatkiPacmannLeft);

        sprite=klatkiPacmannRight[0].getSprite();
        this.input = input;
        this.x=x;
        this.y=y;
        kierunekKlatek=true;
        klatka=0;
        frameSpeed=10;
        this.directionIter=0;
    }
    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public void update(Board board) {
            if (this.isAlive()) {
                remember();
                if (this.x % 50 == 0 && this.y % 50 == 0) {
                    if (eventListener != null) eventListener.onEvent(new Event(Event.Type.CheckCoin, this.x, this.y));
                }
                if (this.checkPossibleDirectionChange(directionTemp, board)) {
                    this.direction = directionTemp;
                    if (this.direction == Directions.UP) {
                        directionIter = 0;

                    }
                    if (this.direction == Directions.RIGHT) {
                        directionIter = 1;

                    }
                    if (this.direction == Directions.DOWN) {
                        directionIter = 2;

                    }
                    if (this.direction == Directions.LEFT) {
                        directionIter = 3;
                    }

                }
                if (chceckforObstacles(board)) {

                    moving = true;
                    //klatka =3;
                } else {
                    move();
                }
            } else {
                this.x = 600;
                this.y = 500;
                this.setAlive(true);
                lives--;
                if(lives==0){
                    if (eventListener != null) eventListener.onEvent(new Event(Event.Type.Reset, this.x, this.y));
                }
            }

        }


    public void remember() {
        if (input.up) {directionTemp = Directions.UP;

        }
        if (input.down) {directionTemp = Directions.DOWN;
        }
        if (input.right) {directionTemp = Directions.RIGHT;
        }
        if (input.left) {directionTemp = Directions.LEFT;
        }
    }

   @Override
    public void changeFrame(){
        if(frameWait>=frameSpeed) {
            if (klatka >= 3) {
                kierunekKlatek = false;
            }
            if(klatka<=0) {
                kierunekKlatek = true;
            }
            if(kierunekKlatek)klatka++;else klatka--;
            frameWait=0;
        }
    }

    public void render(Screen screen){
    int flip = 0;
        sprite = listaKlatek.get(directionIter)[klatka].getSprite();
        screen.renderMob(x,y,sprite,flip);
    }


}

package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.awt.*;
import java.util.ArrayList;

import static rafalwisnia.UI.Sprite.*;

public class Pacmann extends  Mob {
    private Keyboard input;
    private Sprite sprite;
    private int lives =3;
    private boolean enraged;
    public int enrageRate;
    private Directions directionTemp;



    private Integer respawnTimeLeft;
    private EventListener eventListener;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiPacmannUp[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannRight[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannDown[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannLeft[] = new AnimatedSprite[4];
    public Integer getRespawnTimeLeft() {
        return respawnTimeLeft;
    }

    public void setRespawnTimeLeft(Integer respawnTimeLeft) {
        this.respawnTimeLeft = respawnTimeLeft;
    }
    public boolean isEnraged() {
        return enraged;
    }

    public void setEnraged(boolean enraged) {
        this.enraged = enraged;
    }

    public Pacmann(int x,int y,Keyboard input,double speed) {
        super(speed);
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
        if(this.x<280)
        {
            this.x=1279;
        }
        else if( this.x>1280)
        {
            this.x=281;
        }
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
        if (chceckforObstacles(board,0)) {

            moving = true;
            if (this.x <= 300 && this.direction == Directions.LEFT) {
                move();
            }
        } else {
            if(moving) move();
        }

    }

    public void remember() {
        if (input.up) {directionTemp = Directions.UP;
            moving=true;
        }
        if (input.down) {directionTemp = Directions.DOWN;
            moving=true;
        }
        if (input.right) {directionTemp = Directions.RIGHT;
            moving=true;
        }
        if (input.left) {directionTemp = Directions.LEFT;
            moving=true;
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

    public void renderLives(Screen screen){

        if(lives>=1){
            screen.renderSprite(1150,22,Sprite.pacmann_prawo_0,false);
        }
        if(lives>=2){
            screen.renderSprite(1200,22,Sprite.pacmann_prawo_0,false);
        }
        if(lives>=3){
            screen.renderSprite(1250,22,Sprite.pacmann_prawo_0,false);
        }
    }

    public void renderDeath(Screen screen){

    }
    public void resetPacman(){
        this.x=800;
        this.y=600;
        this.direction=Directions.UP;
        moving=false;
    }


}

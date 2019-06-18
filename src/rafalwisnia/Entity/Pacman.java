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

/**
 *
 */
public class Pacman extends  Mob {
    private Keyboard input;
    private Sprite sprite;
    private int lives =3;
    private boolean enraged;
    public int enrageRate;
    private Directions directionTemp;
    private int klatkiSmierc;
    private boolean umiera=false;

    private Integer respawnTimeLeft;
    private EventListener eventListener;

    public boolean isUmiera() {
        return umiera;
    }

    public void setUmiera(boolean umiera) {
        this.umiera = umiera;
    }

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
    private AnimatedSprite klatkiPacmanSmierc[]= new AnimatedSprite[10];
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

    public Pacman(int x, int y, Keyboard input, int speed) {
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

        klatkiPacmanSmierc[0]= new AnimatedSprite(pacman_smierc1);
        klatkiPacmanSmierc[1]= new AnimatedSprite(pacman_smierc2);
        klatkiPacmanSmierc[2]= new AnimatedSprite(pacman_smierc3);
        klatkiPacmanSmierc[3]= new AnimatedSprite(pacman_smierc4);
        klatkiPacmanSmierc[4]= new AnimatedSprite(pacman_smierc5);
        klatkiPacmanSmierc[5]= new AnimatedSprite(pacman_smierc6);
        klatkiPacmanSmierc[6]= new AnimatedSprite(pacman_smierc7);
        klatkiPacmanSmierc[7]= new AnimatedSprite(pacman_smierc8);
        klatkiPacmanSmierc[8]= new AnimatedSprite(pacman_smierc9);
        klatkiPacmanSmierc[9]= new AnimatedSprite(pacman_smierc10);


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
        klatkiSmierc=-1;
        frameSpeed=10;
        this.directionIter=0;
    }

    public int getKlatkiSmierc() {
        return klatkiSmierc;
    }

    public void setKlatkiSmierc(int klatkiSmierc) {
        this.klatkiSmierc = klatkiSmierc;
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }

    public void update(Board board) {
        if(this.x<299)
        {
            this.x=1289;
        }
        else if( this.x>1290)
        {
            this.x=300;
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
            screen.renderSprite(1150,22,Sprite.pacmann_prawo_0);
        }
        if(lives>=2){
            screen.renderSprite(1200,22,Sprite.pacmann_prawo_0);
        }
        if(lives>=3){
            screen.renderSprite(1250,22,Sprite.pacmann_prawo_0);
        }
    }

    public void renderDeath(Screen screen){
        if(klatkiSmierc==-1)klatkiSmierc=0;
        screen.renderMob(x,y,klatkiPacmanSmierc[klatkiSmierc].getSprite(),0);
    }
    public void resetPacman(){
        this.x=800;
        this.y=600;
        this.direction=Directions.UP;
        moving=false;

    }
    public void resetPacmanToDefault(boolean wygrana){
        this.x=800;
        this.y=600;
        this.direction=Directions.UP;
        moving=false;
        if(!wygrana)this.setLives(3);
        this.setKlatkiSmierc(0);
        this.setUmiera(false);
        this.setAlive(true);
        this.setRespawnTimeLeft(0);
        this.setEnraged(false);
        this.direction=Directions.LEFT;
        this.moving=false;
    }



}

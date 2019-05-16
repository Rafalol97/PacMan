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

public class Pacmann extends  Mob implements EventListener {
    private Keyboard input;
    private Sprite sprite;

    private Directions directionTemp;
    private int directionIter;
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiPacmannUp[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannRight[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannDown[] = new AnimatedSprite[4];
    private AnimatedSprite klatkiPacmannLeft[] = new AnimatedSprite[4];



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
        kierunekKlatek=false;
        klatka=0;
        frameSpeed=6;
        this.directionIter=0;
    }

    @Override
    public void onEvent(Event event) {
    }
    public void update(Board board) {
        remember();
        if(chceckforObstacles(board)) {

            moving=false;
            klatka =3;
        }
        else{
            move();
        }
        if (this.checkPossibleDirectionChange(directionTemp, board)) {
            this.direction = directionTemp;
            if(this.direction==Directions.UP){
                directionIter =0;
            }
            if(this.direction==Directions.RIGHT){
                directionIter =1;
            }
            if(this.direction==Directions.DOWN){
                directionIter =2;
            }
            if(this.direction==Directions.LEFT){
                directionIter =3;
            }
          klatka =0;
        }

    }

    @Override
    public void update() {

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
        int flip =0;
        sprite = listaKlatek.get(directionIter)[klatka].getSprite();
        screen.renderMob(x,y,sprite,flip);
    }


}

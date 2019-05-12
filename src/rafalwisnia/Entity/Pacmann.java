package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import static rafalwisnia.UI.Sprite.*;

public class Pacmann extends  Mob implements EventListener {
    private Keyboard input;
    private Sprite sprite;
    private int klatka;
    private boolean kierunekKlatek;
    private int frameSpeed;
    private int frameWait;
    private Directions directionTemp;
    private AnimatedSprite klatkiPacmann[] = new AnimatedSprite[8];


    public Pacmann(int x,int y,Keyboard input) {


        klatkiPacmann[0]= new AnimatedSprite(pacmann_prawo_0);
        klatkiPacmann[1]= new AnimatedSprite(pacmann_prawo_1);
        klatkiPacmann[2]= new AnimatedSprite(pacmann_prawo_2);
        klatkiPacmann[3]= new AnimatedSprite(pacmann_prawo_3);
        klatkiPacmann[4]= new AnimatedSprite(pacmann_lewo_0);
        klatkiPacmann[5]= new AnimatedSprite(pacmann_lewo_1);
        klatkiPacmann[6]= new AnimatedSprite(pacmann_lewo_2);
        klatkiPacmann[7]= new AnimatedSprite(pacmann_lewo_3);
        sprite=klatkiPacmann[0].getSprite();
        this.input = input;
        this.x=x;
        this.y=y;
        kierunekKlatek=false;
        klatka=0;
        frameSpeed=6;
    }

    @Override
    public void onEvent(Event event) {
    }
    public void update(Board board) {   ///TODO Pacman musi móc się obracać w poziomie i pionie za kazdym raze,
        remember();
        if(chceckforObstacles(board)) {
            moving=false;
            if(direction==Directions.DOWN||direction==Directions.LEFT){
                klatka=7;

            }
            else klatka=3;
        }
        else{
            move();
        }
        if (this.checkPossibleDirectionChange(directionTemp, board)) {
            this.direction = directionTemp;
            if(directionTemp==Directions.DOWN||directionTemp==Directions.LEFT){
                klatka=7;

            }
            else klatka=3;
          //  directionTemp = null;

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

    public void move(){
        if(direction==Directions.UP){
            y-=speed;
            if(frameWait>=frameSpeed) {
                if (klatka >= 7) {
                    kierunekKlatek = false;
                }
                if(klatka<=4) {
                    kierunekKlatek = true;
                }
                if(kierunekKlatek)klatka++;else klatka--;
                frameWait=0;
            }
        }
        else  if(direction==Directions.DOWN){
            y+=speed;
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
        else if(direction==Directions.RIGHT){
            x+=speed;
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
        else if(direction==Directions.LEFT){
           x-=speed;
            if(frameWait>=frameSpeed) {
                if (klatka >= 7) {
                    kierunekKlatek = false;
                }
                if(klatka<=4) {
                    kierunekKlatek = true;

                }
                if(kierunekKlatek)klatka++;else klatka--;
                frameWait=0;
            }
        }
        frameWait++;
    }
    private void moveUP(){

    }
    private void moveDOWN(){

    }
    private void moveRIGHT(){

    }
    private void moveLEFT(){

    }

    public void render(Screen screen){
        int flip =0;
        sprite = klatkiPacmann[klatka].getSprite();
        screen.renderMob(x,y,sprite,flip);
    }


}

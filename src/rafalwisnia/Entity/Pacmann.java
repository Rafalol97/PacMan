package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import static rafalwisnia.UI.Sprite.pacmann;

public class Pacmann extends  Mob implements EventListener {
    private Keyboard input;
    private Sprite sprite;
    private AnimatedSprite klatki_Pacmann;


    public Pacmann() {
        sprite = pacmann;
        klatki_Pacmann = new AnimatedSprite(sprite);///TODO inicjalizacja w wierszu lub w kolumnie
        input = new Keyboard();

    }

    public Pacmann(int x,int y,Keyboard input) {
        sprite = pacmann;
        klatki_Pacmann = new AnimatedSprite(sprite);
        this.input = input;
        this.x=x;
        this.y=y;

    }


    @Override
    public void onEvent(Event event) {

    }
    public void update(){
       // this.x+=speed;
        if(input.up){
            changeDirection(Directions.UP);

        }
        if(input.down){
            changeDirection(Directions.DOWN);

        }
        if(input.right){
            changeDirection(Directions.RIGHT);

        }
        if(input.left){
            changeDirection(Directions.LEFT );

        }
        move();
    }
    public void move(){

        if(direction==Directions.UP){
            y-=speed;
        }
        else  if(direction==Directions.DOWN){
            y+=speed;
        }
        else if(direction==Directions.RIGHT){
            x+=speed;
        }
        else if(direction==Directions.LEFT){
           x-=speed;
        }
    }
    public void render(Screen screen){
        int flip =0;
       // sprite = klatki_Pacmann[0].getSprite();
        screen.renderMob(x,y,sprite,flip);
    }


}

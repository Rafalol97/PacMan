package Entity;

import Events.Event;
import Events.EventListener;
import Events.Keyboard;
import UI.AnimatedSprite;
import UI.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pacmann extends  Mob implements EventListener {
    private Keyboard input;
    private Sprite sprite;
    private AnimatedSprite klatki_Pacmann[] = new AnimatedSprite[5];


    public Pacmann() {
    for(int i=0;i<5;i++){
        klatki_Pacmann[i] = new AnimatedSprite();///TODO inicjalizacja w wierszu lub w kolumnie
    }

    }

    public Pacmann(int x,int y,Keyboard input) {
        this.input = input;
        this.x=x;
        this.y=y;
        this.input= input;
    }


    @Override
    public void onEvent(Event event) {

    }
    public void update(){

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
        move(direction);
    }
    public void move(Directions directions){
        if(input.up){
            changeDirection(Directions.UP);
            x+=speed;
        }
        if(input.down){
            changeDirection(Directions.DOWN);
            x-=speed;
        }
        if(input.right){
            changeDirection(Directions.RIGHT);
            y+=speed;
        }
        if(input.left){
            changeDirection(Directions.LEFT );
            y-=speed;
        }
    }

}

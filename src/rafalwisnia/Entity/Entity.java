package rafalwisnia.Entity;


import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.UI.Sprite;
import rafalwisnia.LevelUtilities.Screen;


import java.util.Random;

public class Entity {
    protected int x,y;
    private Sprite sprite;
    private boolean removed = false;
    protected final Random random = new Random();

    public Entity() {
    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public void update(Board board){

    }
    public void render(Screen screen){
        if(sprite != null) screen.renderSprite(x,y,sprite,true); ///TODO int x int y Sprite sprite fixed?

    }
    public void remove(){
        removed= true ;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }
    public void init(Level level){
        //this.level =level;
    }

}

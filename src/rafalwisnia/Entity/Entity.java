package rafalwisnia.Entity;


import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.UI.Sprite;
import rafalwisnia.LevelUtilities.Screen;


public abstract class Entity {
    protected int x,y;
    private Sprite sprite;
    private boolean removed = false;
    private boolean alive = true;

    public Entity() {

    }

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public abstract void update(Board board);

    //public abstract void update();


    public void render(Screen screen){
        if(sprite != null) screen.renderSprite(x,y,sprite,true); ///TODO int x int y Sprite sprite fixed?

    }

    public void setRemoved(){
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

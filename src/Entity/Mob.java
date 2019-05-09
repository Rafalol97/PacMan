package Entity;

public class Mob extends Entity {
    protected boolean moving = false;
    protected boolean walking = false;

    protected boolean live = false;

    protected enum Directions {
        UP, DOWN, RIGHT, LEFT
    }

    protected double speed;
    protected Directions direction;

    public void changeDirection(Directions dir) {
        if (dir == Directions.UP) {
            direction = Directions.UP;
            if (dir == Directions.DOWN) direction = Directions.DOWN;
            if (dir == Directions.RIGHT) direction = Directions.RIGHT;
            if (dir == Directions.LEFT) direction = Directions.LEFT;

        }
    }

    public Mob() {

    }

}

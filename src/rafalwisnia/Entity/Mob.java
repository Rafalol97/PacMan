package rafalwisnia.Entity;


import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;

public abstract class Mob extends Entity {
    protected boolean moving = false;
    protected boolean walking = false;

    protected boolean live = false;

    protected enum Directions {
        UP, DOWN, RIGHT, LEFT
    }

    protected enum boolean2 {
        true1, false1
    }

    Directions direction;
    double speed;

    void changeDirection(Directions dir, Board board) {
    }

    Mob() {
        this.speed = 2;
    }

    public abstract void render(Screen screen);

    public abstract void update(Board board);

    protected boolean checkPossibleDirectionChange(Directions direction, Board board) {
        if (this.direction == Directions.UP && direction == Directions.DOWN) return true;
        else if (this.direction == Directions.DOWN && direction == Directions.UP) return true;
        else if (this.direction == Directions.RIGHT && direction == Directions.LEFT) return true;
        else if (this.direction == Directions.LEFT && direction == Directions.RIGHT) return true;
        else if (checkNeighbour(board, direction) && this.x % 50 == 0 && this.y % 50 == 0) return true;
        else return false;
    }

    private boolean checkNeighbour(Board board, Directions direction) {
        int boardTile[] = board.getTileWhereAmI(this.x, this.y);
        if (direction == Directions.UP) {

            return (board.getTileAlias(boardTile[0] - 1, boardTile[1]) == 0);
        }
        if (direction == Directions.DOWN) {

            return (board.getTileAlias(boardTile[0]+ 1, boardTile[1] ) == 0);
        }
        if (direction == Directions.RIGHT) {


            return (board.getTileAlias(boardTile[0] , boardTile[1]+ 1) == 0);
        }
        if (direction == Directions.LEFT) {

            return (board.getTileAlias(boardTile[0] , boardTile[1]- 1) == 0);
        }
        return false;

    }
    protected boolean chceckforObstacles(Board board){
        if(this.x%50==0&&this.y%50==0) {
            int boardTile[] = board.getTileWhereAmI(this.x, this.y);
            if (this.direction == Directions.UP && (board.getTileAlias(boardTile[0] - 1, boardTile[1]) == 0))
                return false;
            else if (this.direction == Directions.DOWN && (board.getTileAlias(boardTile[0] + 1, boardTile[1]) == 0))
                return false;
            else if (this.direction == Directions.RIGHT && (board.getTileAlias(boardTile[0], boardTile[1] + 1) == 0))
                return false;
            else if (this.direction == Directions.LEFT && (board.getTileAlias(boardTile[0], boardTile[1] - 1) == 0))
                return false;
            else return true;
        }
        return false;
    }
}



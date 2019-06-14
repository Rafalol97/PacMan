package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;

public abstract class Mob extends Entity {
     boolean moving = false;
     boolean kierunekKlatek;
    private boolean alive;
    protected double speed;
    int klatka;
    int frameSpeed;
    int frameWait;
    int directionIter;
    protected enum Directions {
        UP, DOWN, RIGHT, LEFT
        }
    Directions direction;

    Mob() {
        this.speed =1 ;
    }

    public double getSpeed() {
        return speed;
    }

    boolean checkPossibleDirectionChange(Directions direction, Board board) {
        if (this.direction == Directions.UP && direction == Directions.DOWN) return true;
        else if (this.direction == Directions.DOWN && direction == Directions.UP) return true;
        else if (this.direction == Directions.RIGHT && direction == Directions.LEFT) return true;
        else if (this.direction == Directions.LEFT && direction == Directions.RIGHT) return true;
        else if (checkNeighbour(board, direction,0) && this.x % 50 == 0 && this.y % 50 == 0) return true;
        else if (this.direction==direction) return  true;
        else return false;
    }
    boolean checkPossibleDirectionChangeGhost(Directions direction, Board board) {
        return checkNeighbour(board, direction,1);
    }

     private boolean checkNeighbour(Board board, Directions direction,int type) {
        int boardTile[] = board.getTileWhereAmI(this.x, this.y);
        if (direction == Directions.UP) {
            if (type == 1 && board.getTileAlias(boardTile[0] - 1, boardTile[1])==17) return true;
            return (board.getTileAlias(boardTile[0] - 1, boardTile[1]) == 0)||(board.getTileAlias(boardTile[0] - 1, boardTile[1]) == -1);
        }
        if (direction == Directions.DOWN) {

            return (board.getTileAlias(boardTile[0]+ 1, boardTile[1] ) == 0)||(board.getTileAlias(boardTile[0]+ 1, boardTile[1] ) == -1);
        }
        if (direction == Directions.RIGHT) {


            return (board.getTileAlias(boardTile[0] , boardTile[1]+ 1) == 0)||(board.getTileAlias(boardTile[0] , boardTile[1]+ 1) == -1);
        }
        if (direction == Directions.LEFT) {

            return (board.getTileAlias(boardTile[0] , boardTile[1]- 1) == 0)||(board.getTileAlias(boardTile[0] , boardTile[1]- 1) == -1);
        }
        return false;

    }
    boolean chceckforObstacles(Board board, int type){
        if(this.x%50==0&&this.y%50==0) {
            int boardTile[] = board.getTileWhereAmI(this.x, this.y);
            if (this.direction == Directions.UP && (board.getTileAlias(boardTile[0] - 1, boardTile[1]) == 0 || board.getTileAlias(boardTile[0] - 1, boardTile[1]) == -1)||(type == 1 && board.getTileAlias(boardTile[0] - 1, boardTile[1])==17)) {
                return false;
            }
            else if (this.direction == Directions.DOWN && (board.getTileAlias(boardTile[0] + 1, boardTile[1]) == 0) || board.getTileAlias(boardTile[0] + 1, boardTile[1]) == -1)
                return false;
            else if (this.direction == Directions.RIGHT && (board.getTileAlias(boardTile[0], boardTile[1] + 1) == 0) || board.getTileAlias(boardTile[0], boardTile[1] + 1) == -1)
                return false;
            else if (this.direction == Directions.LEFT && (board.getTileAlias(boardTile[0], boardTile[1] - 1) == 0) || board.getTileAlias(boardTile[0], boardTile[1] - 1) == -1)
                return false;
            else return true;
        }
        if(this.x==300||this.x==1300)return true;

        return false;
    }
    boolean checkforObstaclesByCherry(Board board, int GhostX, int GhostY, int PacX, int PacY) {
        int tabGhost[] = board.getTileWhereAmI(GhostX, GhostY);
        int tabPac[] = board.getTileWhereAmI(PacX, PacY);
        int zmienna;
        if (PacX < GhostX) {
            zmienna = GhostX;
            while (zmienna > 300) {
                zmienna -= 50;
                tabGhost[0]--;
                if(board.getTileAlias(tabGhost[0], tabGhost[1]) > 1) {
                    return true;
                }
                if(tabGhost[0] == tabPac[0]){
                    return false;
                }
            }
        }
        if (PacX > GhostX) {
            zmienna = GhostX;
            while (zmienna < 1300) {
                zmienna += 50;
                tabGhost[0]++;
                if(board.getTileAlias(tabGhost[0], tabGhost[1]) > 1) {
                    return true;
                }
                if(tabGhost[0] == tabPac[0]){
                    return false;
                }
            }
        }
        if (PacY < GhostY) {
            zmienna = GhostY;
            while (zmienna > 100) {
                zmienna -= 50;
                tabGhost[1]--;
                if(board.getTileAlias(tabGhost[0], tabGhost[1]) > 1) {
                    return true;
                }
                if(tabGhost[1] == tabPac[1]){
                    return false;
                }
            }
        }
        if (PacY > GhostY) {
            zmienna = GhostY;
            while (zmienna < 800) {
                zmienna += 50;
                tabGhost[1]++;
                if(board.getTileAlias(tabGhost[0], tabGhost[1]) > 1) {
                    return true;
                }
                if(tabGhost[1] == tabPac[1]){
                    return false;
                }
            }
        }
        return true;
    }
    void move(){
        if(direction==Directions.UP){
            y-=1;
            changeFrame();
        }
        else  if(direction==Directions.DOWN){
            y+=1;
            changeFrame();
        }
        else if(direction==Directions.RIGHT){
            x+=1;
            changeFrame();
        }
        else if(direction==Directions.LEFT){
            x-=1;
            changeFrame();
        }
        frameWait++;
    }

    public abstract void changeFrame();

    public abstract void render(Screen screen);

    public abstract void update(Board board);


}



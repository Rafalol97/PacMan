package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;

/**
 * Klasa dziedziczaca z klasy Entity, tworzaca podstawe do wszystkich poruszajacych sie obiektow
 * Przechowuje zmienne i stany takich jak:
 * predkosc, (etap) klatki, kierunek w ktorym sa obrocone
 */
public abstract class Mob extends Entity {
     boolean moving = false;
     boolean kierunekKlatek;
     int speedTemp;
    private boolean alive;
    protected int speed;
    int klatka;
    int frameSpeed;
    int frameWait;
    int directionIter;
    protected enum Directions {
        UP, DOWN, RIGHT, LEFT
        }
    Directions direction;

    Mob(int speed) {
        this.speed=speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getSpeedTemp() {
        return speedTemp;
    }

    /** Metoda sprwadzajaca czy pacman moze zmienic kierunek
     *
     *
     * @param direction - kierunek na ktory chcemy zmienic
     * @param board - refernecja do obiektu Klasy board
     * @return zwaraca wartosc logiczna prawda lub falsz
     */
    boolean checkPossibleDirectionChange(Directions direction, Board board) {
        if (this.direction == Directions.UP && direction == Directions.DOWN) return true;
        else if (this.direction == Directions.DOWN && direction == Directions.UP) return true;
        else if (this.direction == Directions.RIGHT && direction == Directions.LEFT) return true;
        else if (this.direction == Directions.LEFT && direction == Directions.RIGHT) return true;
        else if (checkNeighbour(board, direction,0) && this.x % 50 == 0 && this.y % 50 == 0) return true;
        else if (this.direction==direction) return  true;
        else return false;
    }

    /**
     * Metoda sprawdzajaca czy duch moze zmienic kierunek
     * @param direction- kierunek na ktory chcemy zmienic
     * @param board  - refernecja do obiektu Klasy board
     * @return zwaraca wartosc logiczna prawda lub falsz
     */
    boolean checkPossibleDirectionChangeGhost(Directions direction, Board board) {
        return checkNeighbour(board, direction,1);
    }

    /**Metoda sprawdzajaca czy mozemy isc w wybrana strone
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param direction podana kierunek typu Direction
     * @param type typ obiektu ktory wywoluje metode 0-pacman 1-duch
     * @return  zwaraca wartosc logiczna prawda lub falsz
     */
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

    /** Metoda sprwadza czy w strone w ktora idziemy jest przeszkoda
     *  Metoda pozwala na przemiszczenie sie pacmanowi w tunelu z "teleportem"
     *
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param type typ obiektu ktory wywoluje metode 0-pacman 1-duch
     * @return zwaraca wartosc logiczna prawda lub falsz
     */
    boolean chceckforObstacles(Board board, int type){

        if((this.x==300||(this.x>1200&&this.y==450))&&type==0) {
            System.out.println(this.x+" " +this.y);
            return false;
        }
        if(this.x%50==0&&this.y%50==0) {
            int boardTile[] = board.getTileWhereAmI(this.x, this.y);
            if (this.direction == Directions.UP && (board.getTileAlias(boardTile[0] - 1, boardTile[1]) == 0 || board.getTileAlias(boardTile[0] - 1, boardTile[1]) == -1||(type == 1 && board.getTileAlias(boardTile[0] - 1, boardTile[1])==17))) {
                return false;
            }
            else if (this.direction == Directions.DOWN && ((board.getTileAlias(boardTile[0] + 1, boardTile[1]) == 0) || board.getTileAlias(boardTile[0] + 1, boardTile[1]) == -1))
                return false;
            else if (this.direction == Directions.RIGHT && ((board.getTileAlias(boardTile[0], boardTile[1] + 1) == 0) || board.getTileAlias(boardTile[0], boardTile[1] + 1) == -1))
                return false;
            else if (this.direction == Directions.LEFT && ((board.getTileAlias(boardTile[0], boardTile[1] - 1) == 0) || board.getTileAlias(boardTile[0], boardTile[1] - 1) == -1))
                return false;
            else return true;
        }


        return false;
    }

    /** Metoda sprawdza czy na drodze miedzy podanymi podanymi wspolrzednymi znajduje sie przeszkoda
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param GhostX - pozycja x  sprawdzanego ducha
     * @param GhostY - pozycja y  sprawdzanego ducha
     * @param PacX -
     * @param PacY
     * @return
     */
    boolean checkforObstaclesByCherry(Board board, int GhostX, int GhostY, int PacX, int PacY) {
        int tabGhost[] = board.getTileWhereAmI(GhostX, GhostY);
        int tabPac[] = board.getTileWhereAmI(PacX, PacY);
        int zmienna;
        if (PacX < GhostX) {
            zmienna = GhostX;
            while (zmienna > 300) {
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
        if (PacX > GhostX) {
            zmienna = GhostX;
            while (zmienna < 1300) {
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
        if (PacY < GhostY) {
            zmienna = GhostY;
            while (zmienna > 100) {
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
        if (PacY > GhostY) {
            zmienna = GhostY;
            while (zmienna < 800) {
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



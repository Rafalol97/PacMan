package rafalwisnia.Entity;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;
import java.util.Random;
/**Klasa przechowujaca atrybuty i stany danego duszka
 *
 */
public class Ghost2 extends Ghost implements EventListener {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>();
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];
    private Sprite sprite;
    Random random = new Random();
    private int waitAfterDeath;
    public Ghost2(int x, int y, Board board,Level parentLevel,int speed) {
        super(x,y,speed);
        klatkiDuszekRight[0] = new AnimatedSprite(Sprite.ghost_2_1);
        klatkiDuszekRight[1] = new AnimatedSprite(Sprite.ghost_2_2);

        klatkiDuszekDown[0] = new AnimatedSprite(Sprite.ghost_2_3);
        klatkiDuszekDown[1] = new AnimatedSprite(Sprite.ghost_2_4);

        klatkiDuszekUp[0] = new AnimatedSprite(Sprite.ghost_2_5);
        klatkiDuszekUp[1] = new AnimatedSprite(Sprite.ghost_2_6);

        klatkiDuszekLeft[0] = new AnimatedSprite(Sprite.ghost_2_7);
        klatkiDuszekLeft[1] = new AnimatedSprite(Sprite.ghost_2_8);

        listaKlatek.add(klatkiDuszekUp);
        listaKlatek.add(klatkiDuszekRight);
        listaKlatek.add(klatkiDuszekDown);
        listaKlatek.add(klatkiDuszekLeft);


        this.x = x;
        this.y = y;
        direction = Directions.UP;
        frameSpeed = 10;
        parentLevel.setEventListenerGhost2(this);
    }
    /** Metoda wywolujaca metode z obiektu Klasy screen, w zalzenosci od
     * stanu duszka, kieynku duszka i klatki
     *
     * @param screen refernacja obiektu klasy Screen
     */
    public void render(Screen screen) {
        if(dead){
            sprite=oczyDuszkaPoSmierci[directionIter];
            screen.renderMob(x,y,sprite,0);
        }
        else if (isGhostVisible()) {
            if (!isScared()) {
                sprite = listaKlatek.get(directionIter)[klatka].getSprite();
            } else {
                sprite = klatkiDuszekPrzestraszony[klatka].getSprite();
            }
            screen.renderMob(x, y, sprite, 0);
        }
    }


    /** Metoda aktualizujaca stany i atrybuty duszka w zaleznosci od sytuacji na poziomie.
     *
     *
     * @param board referencja obiektu klasy Board
     */
    @Override
    public void update(Board board) {
        if(waitAfterDeath>0){
            waitAfterDeath--;
        }
        else if(dead){
            if(this.y<this.yStartowe){
                this.direction=Directions.DOWN;
                this.directionIter=1;
            }
            if(this.y>this.yStartowe){
                this.direction=Directions.UP;
                this.directionIter=2;
            }
            if(this.x<this.xStartowe){
                this.direction=Directions.RIGHT;
                this.directionIter=0;
            }
            if(this.x>this.xStartowe){
                this.direction=Directions.LEFT;
                this.directionIter=3;
            }

            move();

            if(this.x==this.xStartowe&&this.y==this.yStartowe){
                System.out.println("I'm alive");
                dead=false;
                resetToDefault();
                frameAmountLeave=100;


            }

        }
        else {
            if (started) {
                if (random.nextInt(3) == 0) {
                    changeToRandomDirection(board);
                }

                if (chceckforObstacles(board, 1)) {
                    changeToRandomDirection(board);
                } else {
                    move();
                }
            } else if (this.frameAmountLeave != 0) {
                direction = Directions.UP;
                frameAmountLeave--;
                System.out.println("i start 1");
                move();
                if (frameAmountLeave == 0) {
                    started = true;
                }
            }
        }
    }
    /** Metoda wywoływana przez zdarzenie wyslane do tego obiektu z klasy Level
     *  Typ zdarzenia - Dead - smierc duszka
     *  Typ zdarzenia - StartGhost1 - wyjscie duszka boksu
     * @param event zdarzenie
     */
    @Override
    public void onEvent(Event event) {
        if(event.getType()==Event.Type.Dead)
        {
            dead=true;

            scared=false;
            chase=false;
            lastSaw=-1;
            waitAfterDeath= (int) (60*speedTemp);
            System.out.println("I'm dead");
            direction=Directions.UP;
        }
        if(event.getType()==Event.Type.StartGhost2)
        {
            frameAmountLeave= (100);
            directionIter=0;
        }
    }
    /** Metoda resetujaca duszka do stanu poczatkowego
     *
     */
    @Override
    public void resetToDefault() {
        frameAmountLeave=0;
        scared=false;
        started = false;
        chase = false;
        lastSaw = -1;
        this.x=800;
        this.y=450;
    }

    /** Metoda ktora zmieniajaca parametr zauwazenia duszka lastSaw
     * oraz rozpoczynajaca poscig duszka za pacmanem
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param PacManX - wspolrzedne x pacmana
     * @param PacManY - wspolrzedne y pacmana
     */
    @Override
    public void updateAIbyCherry(Board board, int PacManX, int PacManY) {
        if(this.x == PacManX || this.y == PacManY) {
            if(PacManX < this.x&&this.direction == Directions.LEFT&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)){
                //  System.out.println("WIDZE PACMANA SKURWIELA PO LEWO");
                lastSaw=3;
                chase=true;
            }
            else  if(PacManX > this.x&&this.direction == Directions.RIGHT&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {
                //  System.out.println("WIDZE PACMANA SKURWIELA PO PRAWO");
                lastSaw=1;
                chase=true;

            }
            else  if(PacManY < this.y&&this.direction == Directions.UP&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {
                //   System.out.println("WIDZE PACMANA SKURWIELA NA GORZE");
                lastSaw=0;
                chase=true;
            }
            else if(PacManY > this.y&&this.direction == Directions.DOWN&&!checkforObstaclesByCherry(board, this.x, this.y, PacManX, PacManY)) {

                //   System.out.println("WIDZE PACMANA SKURWIELA NA DOLE");
                lastSaw=2;
                chase=true;
            }
            else{
                lastSaw=-1;

            }

        }
    }
    /** Funkcja wykorzystywana w updacie gonieniu duchow. Sprawdza czy kierunek wybrany ducha jest zgony do kierunku w ktorym znajduje sie pacman
     * Funkcja jest opatrzona zmienna bezpieczenstwa ktora po 3 probach losuje kierunek, gdyby odpowiedni wybor byl niemozliwy
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param PacManX - wspolrzedne x pacmana
     * @param PacManY - wspolrzedne y pacmana
     * @return wartosc logiczna prawda lub falsz
     */
    public boolean andDirectionIsGOOD(Board board, int PacManX, int PacManY) {
        if(wrazieW == 3) {
            return true;
        }
        else {
            if (this.direction == Directions.UP && PacManY > this.y) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.DOWN && PacManY < this.y) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.RIGHT && PacManX < this.x) {
                wrazieW++;
                return false;
            } else if (this.direction == Directions.LEFT && PacManX > this.x) {
                wrazieW++;
                return false;
            } else return true;
        }
    }
    /** Funkcja upewniajaca sie ze kierunek wybrany przez ghosta jest dozwolony i odpowiedni.
     * Wykorzystuje metode andDirectionIsGOOD.
     *
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param PacManX - wspolrzedne x pacmana
     * @param PacManY - wspolrzedne y pacmana
     */
    public void chceckForErrors(Board board, int PacManX, int PacManY) {
        while(chceckforObstacles(board, 1) || !andDirectionIsGOOD(board, PacManX, PacManY)) {
            if (this.direction == Directions.UP) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            } else if (this.direction == Directions.RIGHT) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (this.direction == Directions.DOWN) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (this.direction == Directions.LEFT) {
                this.direction = Directions.UP;
                directionIter = 0;
            }
            System.out.println("Jestem w petli gonienia");
        }
    }
    /** Update wykonywany kiedy duch jest w trybie poscigu. Jego celem jest symulacja gonienia pacmana (AI)
     *
     * @param board  - refernecja do obiektu Klasy board
     * @param PacManX - wspolrzedne x pacmana
     * @param PacManY - wspolrzedne y pacmana
     */
    public void updateChase(Board board, int PacManX, int PacManY) {
        if(this.x%50==0&&this.y%50==0) {
            if (this.lastSaw == 0) {
                this.direction = Directions.UP;
                directionIter = 0;
            } else if (this.lastSaw == 1) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            } else if (this.lastSaw == 2) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (this.lastSaw == 3) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (PacManY <= this.y) {
                this.direction = Directions.UP;
                directionIter = 0;
            } else if (PacManY >= this.y) {
                this.direction = Directions.DOWN;
                directionIter = 2;
            } else if (PacManX <= this.x) {
                this.direction = Directions.LEFT;
                directionIter = 3;
            } else if (PacManX >= this.x) {
                this.direction = Directions.RIGHT;
                directionIter = 1;
            }
            lastSaw = -1;

            if (chceckforObstacles(board, 1)) {
                if (this.direction == Directions.LEFT || this.direction == Directions.RIGHT) {
                    if (PacManY < this.y) {
                        this.direction = Directions.UP;
                        directionIter = 0;
                    } else if (PacManY > this.y) {
                        this.direction = Directions.DOWN;
                        directionIter = 2;
                    } else {
                        if (this.direction == Directions.LEFT) {this.direction = Directions.RIGHT; directionIter = 1;}
                        else if (this.direction == Directions.RIGHT) {this.direction = Directions.LEFT; directionIter = 3;}
                    }
                } else if (this.direction == Directions.UP || this.direction == Directions.DOWN) {
                    if (PacManX < this.x) {
                        this.direction = Directions.LEFT;
                        directionIter = 3;
                    } else if (PacManX > this.x) {
                        this.direction = Directions.RIGHT;
                        directionIter = 1;
                    } else {
                        if (this.direction == Directions.UP) {this.direction = Directions.DOWN; directionIter = 2;}
                        else if (this.direction == Directions.DOWN) {this.direction = Directions.UP; directionIter = 0;}
                    }
                }
            }
            chceckForErrors(board, PacManX, PacManY);
            wrazieW = 0;
        }
        move();
    }
}







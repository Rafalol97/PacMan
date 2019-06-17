package rafalwisnia.LevelUtilities;


import rafalwisnia.Entity.*;
import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.UI.Sprite;
import rafalwisnia.UI.SpriteSheet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Level implements EventListener {
    private List<Ghost> ghosts;
    private List<Mob> update;
    private Pacmann pacman;
    private Border border;
    private Points points;
    private List<Coin> coins;
    private int pacmanSpeed;
    private int timer;
    private Board board;
    private Coin[][] coinsTable;

    public boolean pauza = false;

    public Level() {
        LinkedList<Double> speeds = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            speeds.add(1.0);
        }
        createLevel(speeds);
    }

    private Integer licznikCzasu=0;
    private boolean allGhostsOut=false;
    private EventListener eventListenerGhost1;
    private EventListener eventListenerGhost2;
    private EventListener eventListenerGhost3;
    private EventListener eventListenerGhost4;

    private void createLevel(LinkedList<Double> speeds) {
        coinsTable=new Coin[14][20];
        ghosts = new ArrayList<>();
        coins = new ArrayList<>();
        border = new Border();
        board = new Board(1000, 700);
        points = new Points();
        ghosts.add(new Ghost1(750, 450, board,this,speeds.get(0)));
        ghosts.add(new Ghost2(800, 450, board,this,speeds.get(1)));
        ghosts.add(new Ghost3(750, 500, board,this,speeds.get(2)));
        ghosts.add(new Ghost4(800, 500, board,this,speeds.get(3)));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 14; j++) {
                if (board.getTileAlias(j, i) == 0 && !(i >= 8 && i <= 11 && j >= 6 && j <= 9)&&!(i==10&&j==10) ){
                    coinsTable[j][i]= new Coin(i,j,Sprite.smallCoin);
                    Coin.count++;
                }
                if (board.getTileAlias(j, i) == -1) {
                    coinsTable[j][i]=new Coin(i,j,Sprite.bigCoin);
                    Coin.count++;
                }
            }
        }
        pacmanSpeed = 3;
        timer = 0;
        showAllGhosts();
        //testy wychodzenia

    }

    public void clearLevel(boolean wygrana) {
        LinkedList<Double> speeds = new LinkedList<>();
        if(wygrana) {
            double ghostSpeed;
            for (Ghost ghost : ghosts) {
                ghostSpeed =(int) (ghost.getSpeed() + (int) (ghost.getSpeed() / 2));
                if (ghostSpeed == 1) ghostSpeed = 2;
                speeds.add(ghostSpeed);
                System.out.println(speeds.getLast());
            }
            points.addLevel();
        }
        else {
            for (int i = 0; i < 4; i++) {
                speeds.add(1.0);
                this.points.resetLevel();
            }
        }
        pacman.setLives(3);
        createLevel(speeds);

        allGhostsOut = false;
        licznikCzasu = 0;
        pacman.resetPacman();

    }

    public void render(Screen screen) {
        border.render(screen);
        board.render(screen);

        for(int i=0;i<20;i++){
            for(int j=0;j<14;j++){
                if(coinsTable[j][i]!=null)coinsTable[j][i].render(screen);
            }
        }
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).render(screen);
        }
        pacman.render(screen);
        points.render(screen);
        pacman.renderLives(screen);
        screen.renderSheet(550,300, SpriteSheet.youdied,false);
    }
    public void add(Entity e) {
        if (e instanceof Pacmann) {
            ((Pacmann) e).setEventListener(this);
            pacman = (Pacmann) e;

        } else {
            ghosts.add((Ghost) e);
        }
    }
    public void setEventListenerGhost1(EventListener eventListener){
        this.eventListenerGhost1 = eventListener;
    }
    public void setEventListenerGhost2(EventListener eventListener){
        this.eventListenerGhost2 = eventListener;
    }
    public void setEventListenerGhost3(EventListener eventListener){
        this.eventListenerGhost3 = eventListener;
    }
    public void setEventListenerGhost4(EventListener eventListener){
        this.eventListenerGhost4 = eventListener;
    }
    public void update() {
        if(pacman.isAlive() && !pauza) {
            if (pacman.isEnraged()) {
                if (timer > 0) {
                    timer--;
                } else {
                    pacmanSpeed = 3;
                    pacman.setEnraged(false);
                    pacman.enrageRate = 1;
                    for (int i = 0; i < 4; i++) {
                        ghosts.get(i).setScared(false);
                    }
                }
            }
            for (int i = 0; i < pacmanSpeed; i++) {
                pacman.update(board);

            }

            for (int i = 0; i < ghosts.size(); i++) {
                    for (int j = 0; j < ghosts.get(i).getSpeed(); j++) {
                        if (!ghosts.get(i).chase) {
                            ghosts.get(i).update(board);
                        } else if (ghosts.get(i).chase) {
                            ghosts.get(i).updateChase(board, pacman.getX(), pacman.getY());
                        }
                        if(!ghosts.get(i).isDead()) {
                            ghosts.get(i).updateAIbyCherry(board, pacman.getX(), pacman.getY());
                        }
                    }
                }
            checkForCollisionWithGhosts();
            if (!allGhostsOut) {
                licznikCzasu++;

                if (licznikCzasu == 2) {
                    if (eventListenerGhost1 != null)
                        eventListenerGhost1.onEvent(new Event(Event.Type.StartGhost1, 0, 0));
                }

                if (licznikCzasu == 5 * 60) {
                    if (eventListenerGhost2 != null)
                        eventListenerGhost2.onEvent(new Event(Event.Type.StartGhost2, 0, 0));
                }

                if (licznikCzasu == 10 * 60) {

                    if (eventListenerGhost3 != null)
                        eventListenerGhost3.onEvent(new Event(Event.Type.StartGhost3, 0, 0));
                }
                if (licznikCzasu == 15 * 60) {
                    if (eventListenerGhost4 != null)
                        eventListenerGhost4.onEvent(new Event(Event.Type.StartGhost4, 0, 0));
                    allGhostsOut = true;
                    licznikCzasu = 0;
                }


            }
        }
        else if(!pacman.isAlive())
        {
            //Update ogolnie wszystkiego
            if(pacman.getRespawnTimeLeft()==0) {
                pacman.setAlive(true);
                pacman.resetPacman();
                pacman.setLives(pacman.getLives()-1);
                if(pacman.getLives()==0){
                    this.clearLevel(false);
                }
                for(int i=0;i<ghosts.size();i++){
                    ghosts.get(i).resetToDefault();
                    allGhostsOut= false;
                    licznikCzasu=0;
                    showAllGhosts();
                }
            }
            else {pacman.setRespawnTimeLeft(pacman.getRespawnTimeLeft()-1);}
        }
    }
    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(Event event) { //Daruj boże za te grzechy...
        if (Event.Type.CheckCoin == event.getType()) {
            int coordinates[] = board.getTileWhereAmI(event.getX(), event.getY());
            if (board.getTileAlias(coordinates[0], coordinates[1]) == 0 || board.getTileAlias(coordinates[0], coordinates[1]) == -1) {
                if ((coinsTable[coordinates[0]][coordinates[1]] != null)) {

                    if (coinsTable[coordinates[0]][coordinates[1]].getSprite() == Sprite.bigCoin) {
                        pacmanSpeed = 5;
                        timer = 60 * 5;
                        scareAllGhosts();
                        pacman.setEnraged(true);
                        points.add(1000);
                    } else {
                        points.add(100);
                    }
                    coinsTable[coordinates[0]][coordinates[1]] = null;
                    Coin.count--;
                }
            }
            if (Coin.count == 0) {
                System.out.println("Koniec");

                this.clearLevel(true);

            }

        }

    }

    public void scareAllGhosts() {
        for (int i = 0; i < 4; i++) {
            ghosts.get(i).setScared(true);
        }
    }

    public void checkForCollisionWithGhosts() {
        int ghostX, ghostY;
        int pacmanX = pacman.getX();
        int pacmanY = pacman.getY();
        for (int i = 0; i < ghosts.size(); i++) {
            ghostX = ghosts.get(i).getX();
            ghostY = ghosts.get(i).getY();
            if (Math.sqrt(Math.pow((ghostX - pacmanX), 2) + Math.pow((ghostY - pacmanY), 2)) < 25&&!ghosts.get(i).isDead()) {

                if (pacman.isEnraged() && ghosts.get(i).isScared()) {

                   killThemaAll(i);
                    points.add(pacman.enrageRate * 200);
                    pacman.enrageRate++;
                } else {
                    pacman.setAlive(false);
                    pacman.setRespawnTimeLeft(5*60);
                    hideAllGhosts();
                }
            }
        }
    }

    public void killThemaAll(int number)
    {

        if (number ==0) {

            if (eventListenerGhost1 != null) eventListenerGhost1.onEvent(new Event(Event.Type.Dead, 0, 0));
        }

        if (number ==1) {

            if (eventListenerGhost2 != null) eventListenerGhost2.onEvent(new Event(Event.Type.Dead, 0, 0));
        }
        if (number ==2) {

            if (eventListenerGhost3 != null) eventListenerGhost3.onEvent(new Event(Event.Type.Dead, 0, 0));
        }
        if (number == 3) {

            if (eventListenerGhost4 != null) eventListenerGhost4.onEvent(new Event(Event.Type.Dead, 0, 0));

        }

    }
    public void hideAllGhosts(){
        for(int i=0;i<ghosts.size();i++)
        {
            ghosts.get(i).setGhostVisible(false);
        }
    }

    public void showAllGhosts(){
        for(int i=0;i<ghosts.size();i++)
        {
            ghosts.get(i).setGhostVisible(true);
        }
    }
}



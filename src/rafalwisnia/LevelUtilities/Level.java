package rafalwisnia.LevelUtilities;


import rafalwisnia.Entity.*;
import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.UI.Sprite;
import rafalwisnia.UI.SpriteSheet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Level.java
 * Klasa level w pragramie odpowiada za aktualizowanie danych  i  renderowanych grafik.
 * Funkcje zawarte w tej klasie obsluguja kolejnosc i logike za wykonywanymi aktualizacjami o odpowiednim umieszczaniem i ich kolejnosci na ekranie
 *
 */
public class Level implements EventListener {
    private List<Ghost> ghosts;
    private List<Mob> update;
    private Pacman pacman;
    private List<Coin> coins;
    private int pacmanSpeed;
    private int timer;
    private Board board;
    private Coin[][] coinsTable;
    private boolean pokazYouDied=false;
    private boolean pokazYouWon=false;
    private boolean zresetujPoWygranej =false;
    public boolean pauza = false;
    public static int PacX;
    public static int PacY;


    public Level() {
        LinkedList<Integer> speeds = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            speeds.add(1);
        }
        PacX=10*50+300;
        PacY=10*50+100;

        createLevel(speeds);

    }

    private Integer licznikCzasu=0;
    private boolean allGhostsOut=false;
    private EventListener eventListenerGhost1;
    private EventListener eventListenerGhost2;
    private EventListener eventListenerGhost3;
    private EventListener eventListenerGhost4;

    /**
     * Funkcja tworząca wszystkie obiektuy potrzebne do poprawnego dzialania gry.
     * Funkcja jest uzywana nie tylko przy poczatkowej inicjalizacji obiektu klasy Level ale takze przy resetowaniu stanu gry, po przegranej, lub wygranej
     * @param speeds Lista nowych predkosci nadanych duchom.
     */
    private void createLevel(LinkedList<Integer> speeds) {
        coinsTable=new Coin[14][20];
        ghosts = new ArrayList<>();
        coins = new ArrayList<>();

        board = new Board();

        ghosts.add(new Ghost1(750, 450, board,this,speeds.get(0)));
        ghosts.add(new Ghost2(800, 450, board,this,speeds.get(1)));
        ghosts.add(new Ghost3(750, 500, board,this,speeds.get(2)));
        ghosts.add(new Ghost4(800, 500, board,this,speeds.get(3)));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 14; j++) {
                if (board.getTileAlias(j, i) == 0 && !(i >= 7 && i <= 12 && j >= 5 && j <= 9)&&!((PacX-300)%50==i&&(PacY-100)%50==j) ){
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

    /**
     * Funkcja rozrozniajaca typ zakonczenia rozgrywki. Rozroznia go na przegrana lub wygrana.
     * W przypadku wygranej funkcja zwieksza predkosc wszystkich duchow, w celu utrudnienia rozgrywki - nastepnych poziomow
     * @param wygrana - true - wygrana rozgrywka; false - przegrana rozgrywka
     */
    public void clearLevel(boolean wygrana) {
        LinkedList<Integer> speeds = new LinkedList<>();
        if(wygrana) {
            int ghostSpeed;
            for (Ghost ghost : ghosts) {
                ghostSpeed = (int) Math.floor( (ghost.getSpeed() + Math.floor (ghost.getSpeed() / 2)));
                if (ghostSpeed == 1) ghostSpeed = 2;
                speeds.add(ghostSpeed);
                System.out.println(speeds.getLast());
            }
            Points.addLevel();
        }
        else {
            for (int i = 0; i < 4; i++) {
                speeds.add(1);
                Points.resetLevel();
            }
            Points.resetPoints();
        }
        Coin.count=0;
        pacman.setDeafultX(PacX);
        pacman.setDeafultY(PacY);
        pacman.resetPacmanToDefault(wygrana);
        createLevel(speeds);
        allGhostsOut = false;
        licznikCzasu = 0;
        pokazYouWon=false;
        pokazYouDied=false;
        zresetujPoWygranej =false;


    }

    /** Funkcja oblugujaca wywolanie funkcji render w poszczegolnych skladnickach obiektu klasy Lewel.
     * Wykonywana jest w odpowiedniej kolejnosci aby rzeczy ktore sa przkrywane znajdowaly sie na odpowiednim miejscu.
     *
     * @param screen referencja do obiektu klasy Screen
     */
    public void render(Screen screen) {
        screen.renderBorder();
        board.render(screen);

        for(int i=0;i<20;i++){
            for(int j=0;j<14;j++){
                if(coinsTable[j][i]!=null)coinsTable[j][i].render(screen);
            }
        }
        for (int i = 0; i < ghosts.size(); i++) {
            ghosts.get(i).render(screen);
        }

        Points.render(screen);
        pacman.renderLives(screen);
        if(pacman.isUmiera())pacman.renderDeath(screen);
        else if(pacman.isAlive()) pacman.render(screen);

        if(pokazYouDied){
            if(Points.poziom>3) screen.renderSheet(400,450,SpriteSheet.youdied_darksouls);
            else  screen.renderSheet(400,450,SpriteSheet.youdied_generic);
        }
        if(pokazYouWon){
            screen.renderSheet(400,450,SpriteSheet.youwon);
        }


    }

    /**
     * Funkcja przypisujacego ducha lub pacmana w zaleznosci od typu danej wejsciowej do listy Duszkow lub Pacmana
     *
     * @param e refernecja do obiektu klasu Entity - podstawowego obiektu przedstawiajacego stworzenie lub przedmiot
     */
    public void add(Entity e) {
        if (e instanceof Pacman) {
            ((Pacman) e).setEventListener(this);
            pacman = (Pacman) e;
            pacman.setDeafultX(PacX);
            pacman.setDeafultY(PacY);
            pacman.resetPacman();

        } else {
            ghosts.add((Ghost) e);
        }
    }

    /**
     * Funkcja przeypisujaca do atrybutu eventListenerGhost1 - czyli atrybutu odpowiadajacego za nasluchiwanie obiektu tej klasy przez obiekt klasy dziedziczacej z klasy Event Listener
     * @param eventListener referencja do obiektu rozszerzajacego klase EventListener
     */
    public void setEventListenerGhost1(EventListener eventListener){
        this.eventListenerGhost1 = eventListener;
    }
    /**
     * Funkcja przeypisujaca do atrybutu eventListenerGhost2 - czyli atrybutu odpowiadajacego za nasluchiwanie obiektu tej klasy przez obiekt klasy dziedziczacej z klasy Event Listener
     * @param eventListener referencja do obiektu rozszerzajacego klase EventListener
     */
    public void setEventListenerGhost2(EventListener eventListener){
        this.eventListenerGhost2 = eventListener;
    }
    /**
     * Funkcja przeypisujaca do atrybutu eventListenerGhost3 - czyli atrybutu odpowiadajacego za nasluchiwanie obiektu tej klasy przez obiekt klasy dziedziczacej z klasy Event Listener
     * @param eventListener referencja do obiektu rozszerzajacego klase EventListener
     */
    public void setEventListenerGhost3(EventListener eventListener){
        this.eventListenerGhost3 = eventListener;
    }
    /**
     * Funkcja przeypisujaca do atrybutu eventListenerGhost4 - czyli atrybutu odpowiadajacego za nasluchiwanie obiektu tej klasy przez obiekt klasy dziedziczacej z klasy Event Listener
     * @param eventListener referencja do obiektu rozszerzajacego klase EventListener
     */
    public void setEventListenerGhost4(EventListener eventListener){
        this.eventListenerGhost4 = eventListener;
    }

    /**Funkcja update sterujaca logika i kolejnoscia wykonywania updatow wszystkich obiektow w rozgrywce.
     * Steruje jej zakonczeniem, restartem, pauza i ponowieniem.
     * Intrukcje warunkowe zwarte w tej funkcji sterują przebiegiem pojawiania się zmian po ustawieniu ustawieniu odpowiednich flag przez inne metody
     * Metoda ta zostaje wywolana w przyblizeniu 60 razy na sekunde
     */
    public void update() {
        if(!zresetujPoWygranej) {
            if (pacman.isAlive() && !pauza) {
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
                int ghostSpeed;
                for (int i = 0; i < ghosts.size(); i++) {
                    ghostSpeed=ghosts.get(i).getSpeed();
                    if(ghosts.get(i).isDead())ghostSpeed=ghosts.get(i).getSpeedTemp();
                    for (int j = 0; j < ghostSpeed; j++) {
                        if (ghosts.get(i).isScared() && ghosts.get(i).isStarted()) {
                            ghosts.get(i).updateScared(board, pacman.getX(), pacman.getY());
                        } else if (ghosts.get(i).chase && ghosts.get(i).isStarted() && !ghosts.get(i).isScared()) {
                            ghosts.get(i).updateChase(board, pacman.getX(), pacman.getY());
                        } else if (!ghosts.get(i).isStarted() || (ghosts.get(i).isStarted() && !ghosts.get(i).isScared() || (ghosts.get(i).isDead()))) {
                            ghosts.get(i).update(board);
                        }
                        if (!ghosts.get(i).isDead() && !ghosts.get(i).chase && !ghosts.get(i).isScared()) {
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
            } else if (!pacman.isAlive()) {
                if (pacman.getRespawnTimeLeft() + 110 > 5 * 60) {
                    if ((pacman.getRespawnTimeLeft() - 5 * 60 + 110) % 10 == 0) {
                        pacman.setKlatkiSmierc(pacman.getKlatkiSmierc() + 1);
                    }
                    if (pacman.getKlatkiSmierc() == 10) {
                        pacman.setKlatkiSmierc(-1);
                        pacman.setUmiera(false);
                    }
                }
                if (pacman.getRespawnTimeLeft() == 5 * 60 - 110) {
                    if (pacman.getLives() - 1 == 0) pokazYouDied = true;
                }
                //Update ogolnie wszystkiego
                if (pacman.getRespawnTimeLeft() == 0) {
                    pokazYouDied = false;
                    pacman.setAlive(true);
                    pacman.resetPacman();
                    pacman.setLives(pacman.getLives() - 1);
                    if (pacman.getLives() == 0) {
                        this.clearLevel(false);
                    }
                    for (int i = 0; i < ghosts.size(); i++) {
                        ghosts.get(i).resetToDefault();
                        allGhostsOut = false;
                        licznikCzasu = 0;
                        showAllGhosts();
                    }
                } else {
                    pacman.setRespawnTimeLeft(pacman.getRespawnTimeLeft() - 1);

                }

            }
        }
        else
        {
            if (pacman.getRespawnTimeLeft() == 3 * 60 ) {
                pokazYouWon=true;
            }
            if(pacman.getRespawnTimeLeft()==0){
                pokazYouWon=false;
                zresetujPoWygranej =false;

                this.clearLevel(true);
            }
            pacman.setRespawnTimeLeft(pacman.getRespawnTimeLeft()-1);
        }
    }


    public Board getBoard() {
        return board;
    }

    @Override
    /** Metoda onEvent
     *  Metoda ta jest wywoływana za każdym razem jak zostanie odebrane zdarzenie przez obiekt.
     *  Typ sprawdzanego zdarzenia  - CheckCoin.
     *  Metoda sprawdza czy pacman wszedl na coin i jakiego typu jest ten coin: maly - dodaje 100pkt do wyniku (poprzez klase Points),
     *  duży - dodoaje 1000pkt, zmienia tryb Pacmana, oraz "przestrasza duszki""
     *  Gdy liczba coinow na podlodze jest rowna zero - wywolywana jest sekwencja poziomu wygranego
     * @param event - zdarzenie
     */
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
                        Points.add(1000);
                    } else {
                        Points.add(100);
                    }
                    coinsTable[coordinates[0]][coordinates[1]] = null;
                    Coin.count--;
                }
            }
            if (Coin.count == 0) {
                zresetujPoWygranej =true;
                pacman.setRespawnTimeLeft(3*60);
            }

        }

    }

    /**
     * "Przestrasza" wszystkie duszki
     */
    public void scareAllGhosts() {
        for (int i = 0; i < 4; i++) {
            ghosts.get(i).chase=false;
            if (!ghosts.get(i).isDead()) {
                ghosts.get(i).setScared(true);
            }
        }
    }

    /**Metoda sprawdza kolizje z duszkami
     * Jesli PacMan ma zmieniony tryb, "zjada duszki oraz dostaje 200pkt*liczbe zjedzonych duszkow bez zmiany trybu
     */
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
                    Points.add(pacman.enrageRate * 200);
                    pacman.enrageRate++;
                } else {
                    pacman.setAlive(false);
                    pacman.setUmiera(true);
                    pacman.setRespawnTimeLeft(5*60);
                    hideAllGhosts();

                }
            }
        }
    }

    /**
     * Zabija odpowiedniego duszka w zaleznosci od
     * @param number - numer duszka do zabicia
     */
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

    /**
     *  Chowa wszystkie duchy
     */
    public void hideAllGhosts(){
        for(int i=0;i<ghosts.size();i++)
        {
            ghosts.get(i).setGhostVisible(false);
        }
    }

    /**
     * Pokazuje wszystkie duchy
     */
    public void showAllGhosts(){
        for(int i=0;i<ghosts.size();i++)
        {
            ghosts.get(i).setGhostVisible(true);
        }
    }
}



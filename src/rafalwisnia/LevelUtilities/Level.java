package rafalwisnia.LevelUtilities;


import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.Entity.*;
import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.Events.Keyboard;
import rafalwisnia.Game;
import rafalwisnia.UI.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Level implements EventListener {
    private List<Ghost> ghosts;
    private Pacmann pacman;
    private Border border;
    private Points points;
    private List<Coin> coins ;
    private int pacmanSpeed;
    private int timer;
    private Board board ;

        public Level() {
            createLevel();

        }
        private void createLevel(){
            ghosts = new ArrayList<>();
            coins = new ArrayList<>();
            border = new Border();
            board = new Board(1000, 700);
            points = new Points();
            ghosts.add(new Ghost1(800, 500, board));
            ghosts.add(new Ghost2(800, 500, board));
            ghosts.add(new Ghost3(800, 500, board));
            ghosts.add(new Ghost4(800, 500, board));
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 14; j++) {
                    if (board.getTileAlias(j, i) == 0 && !(i >= 8 && i <= 13 && j >= 6 && j <= 11)) {
                        coins.add(new Coin(i, j, Sprite.smallCoin));
                        Coin.count++;
                    }
                    if (board.getTileAlias(j, i) == -1) {
                        coins.add(new Coin(i, j, Sprite.bigCoin));
                        Coin.count++;
                    }
                }
            }
            pacmanSpeed = 3;
            timer = 0;
        }
        private void clearLevel(){
            pacman.lives=3;
            createLevel();
        }
    public void render(Screen screen){
        border.render(screen);
        board.render(screen);
        for(int i =0;i<coins.size();i++){
            coins.get(i).render(screen);
        }
        for(int i=0;i<ghosts.size();i++){
            ghosts.get(i).render(screen);
        }
        pacman.render(screen);
        points.render(screen);
    }


    public void add(Entity e) {
        e.init(this);
        if (e instanceof Pacmann) {
            ((Pacmann) e).setEventListener(this);
            pacman= (Pacmann) e;

        } else {
            ghosts.add((Ghost) e);
        }
    }
    public void update() { //Daruj boże...
        if (timer > 0) {
            timer--;
        } else {
            pacmanSpeed = 3;
            pacman.setEnraged(false);
            pacman.enrageRate=1;
        }
        for (int i = 0; i < pacmanSpeed; i++) {
            pacman.update(board);

        }

        for (int i = 0; i < ghosts.size(); i++) {
            //  ghosts.get(i).update();
            ghosts.get(i).update(board);
            ghosts.get(i).update(board);
            //  ghosts.get(i).update();
        }
    checkForCollisionWithGhosts();
    }
    private void remove() {
      pacman.remove();
    }
    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(Event event) { //Daruj boże za te grzechy...
            if(Event.Type.CheckCoin==event.getType()) {
                int coordinates[] = board.getTileWhereAmI(event.getX(), event.getY());
                if (board.getTileAlias(coordinates[0], coordinates[1]) == 0 || board.getTileAlias(coordinates[0], coordinates[1]) == -1) {
                    for (int i = 0; i < coins.size(); i++) {
                        if (coins.get(i).getX() == coordinates[1] && coins.get(i).getY() == coordinates[0]) {
                            if (coins.get(i).getSprite() == Sprite.bigCoin) {
                                pacmanSpeed = 5;
                                timer = 60 * 15;
                                scareAllGhosts();
                                pacman.setEnraged(true);
                                points.add(1000);
                            } else {
                                points.add(100);
                            }

                            coins.remove(i);

                            break;
                        }
                    }
                }
                if (coins.size() == 0) {
                    System.out.println("Koniec");
                }
            }
            else if(Event.Type.Reset==event.getType()){
                this.clearLevel();
            }
    }

    public void scareAllGhosts(){
            for(int i=0;i<4;i++){
                ghosts.get(i).setScared(true);
            }
    }
    public void checkForCollisionWithGhosts(){
            int ghostX,ghostY;
            int pacmanX=pacman.getX();
            int pacmanY=pacman.getY();
            for(int i=0;i<4;i++){
                ghostX=ghosts.get(i).getX();
                ghostY= ghosts.get(i).getY();
                if(ghostX==pacmanX){
                    if(Math.abs(ghostY-pacmanY)<30) {
                        if (pacman.isEnraged()&&ghosts.get(i).isScared()) {
                            ghosts.get(i).resetToDefault();
                            points.add(pacman.enrageRate*200);
                            pacman.enrageRate++;
                        } else {
                            pacman.setAlive(false);
                        }
                    }
                }
                if(ghostY==pacmanY){
                    if(Math.abs(ghostX-pacmanX)<30){
                        if (pacman.isEnraged()&&ghosts.get(i).isScared()) {
                            ghosts.get(i).resetToDefault();
                            points.add(pacman.enrageRate*200);
                            pacman.enrageRate++;
                        } else {
                            pacman.setAlive(false);
                        }
                    }
                }
            }
    }

}

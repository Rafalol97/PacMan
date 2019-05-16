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
    private List<Entity> ghosts = new ArrayList<>();
    private Entity pacman;
    private Border border;
    private PathFinder pathFinder;
    private List<Coin> coins = new ArrayList<>();
    private int pacmanSpeed;
    private int timer;
    private Board board ;
        public Level() {
            border = new Border();
            board = new Board(1000,700);
           ghosts.add(new Ghost1(500,500,board));
            ghosts.add(new Ghost2(500,500,board));
         ghosts.add(new Ghost3(500,500,board));
           ghosts.add(new Ghost4(500,500,board));
            for(int i= 0 ; i<20;i++){
                for(int j=0;j<14;j++){
                    if(board.getTileAlias(j,i)==0){
                      coins.add(new Coin(i,j, Sprite.smallCoin));
                      Coin.count++;
                    }
                    if(board.getTileAlias(j,i)==-1){
                        coins.add(new Coin(i,j, Sprite.bigCoin));
                        Coin.count++;
                    }
                }
            }
            pacmanSpeed=3;
            timer=0;

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

    }


    public void add(Entity e) {
        e.init(this);
        if (e instanceof Pacmann) {
            ((Pacmann) e).setEventListener(this);
            pacman=e;

        } else {
            ghosts.add(e);
        }
    }
        public void update() { //Daruj boże...
            if(timer>0) {
                timer--;
            }
            else {
                pacmanSpeed = 3;
            }
    for(int i=0;i<pacmanSpeed;i++){
        pacman.update(board);

    }
    for(int i=0;i<ghosts.size();i++){
      //  ghosts.get(i).update();
        ghosts.get(i).update(board);
        ghosts.get(i).update(board);
      //  ghosts.get(i).update();
    }

    }
    private void remove() {
      pacman.remove();
    }
    public Board getBoard() {
        return board;
    }

    @Override
    public void onEvent(Event event) { //Daruj boże za te grzechy...

           int coordinates[]= board.getTileWhereAmI(event.getX(),event.getY());
            if(board.getTileAlias(coordinates[0],coordinates[1])==0||board.getTileAlias(coordinates[0],coordinates[1])==-1) {
                for(int i=0;i<coins.size();i++){
                    if(coins.get(i).getX()==coordinates[1]&&coins.get(i).getY()==coordinates[0]){
                       if(coins.get(i).getSprite()==Sprite.bigCoin){
                           pacmanSpeed =5;
                           timer = 60*15;
                       }

                        coins.remove(i);

                        break;
                    }
                }
            }
            if(coins.size()==0){
                System.out.println("Koniec");
            }
    }

}

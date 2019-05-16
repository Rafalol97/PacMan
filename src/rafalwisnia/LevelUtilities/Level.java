package rafalwisnia.LevelUtilities;


import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.Entity.Entity;
import rafalwisnia.Entity.Ghost1;
import rafalwisnia.Entity.Pacmann;
import rafalwisnia.Events.Keyboard;

import java.util.ArrayList;
import java.util.List;


public class Level  {
    private List<Entity> ghosts = new ArrayList<Entity>();
    private Entity pacman;
    private Border border;
    private PathFinder pathFinder;
    public Board getBoard() {
        return board;
    }


    private Board board ;
        public Level(Keyboard key) {
            this.add(new Pacmann(500,600,key));
            border = new Border();
            board = new Board(1000,700);
            pathFinder = new PathFinder(board.getTiles(),pacman,board);
            ghosts.add(new Ghost1(500,500,pathFinder,pacman,board));
        }

    public void render(Screen screen){
        border.render(screen);
        board.render(screen);
        for(int i=0;i<ghosts.size();i++){
            ghosts.get(i).render(screen);
        }
        pacman.render(screen);
    }


    public void add(Entity e) {
        e.init(this);
        if (e instanceof Pacmann) {
            pacman=e;
        } else {
            ghosts.add(e);
        }
    }
    public void update() {
    for(int i=0;i<4;i++){
        pacman.update(board);

    }
    for(int i=0;i<ghosts.size();i++){
        ghosts.get(i).update();
        ghosts.get(i).update();
        ghosts.get(i).update();
        ghosts.get(i).update();
    }

    }
    private void remove() {
      pacman.remove();
    }
}

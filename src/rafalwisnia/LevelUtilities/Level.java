package rafalwisnia.LevelUtilities;


import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.Entity.Entity;
import rafalwisnia.Entity.Ghost1;
import rafalwisnia.Entity.Pacmann;

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
        public Level() {

            border = new Border();
            board = new Board(1000,700);
            ghosts.add(new Ghost1(500,500));
            ghosts.add(new Ghost1(500,500));
            ghosts.add(new Ghost1(500,500));
            ghosts.add(new Ghost1(500,500));
            pathFinder = new PathFinder(board.getTiles());

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
        ghosts.get(i).update(board,pathFinder,pacman.getX(),pacman.getY());
        ghosts.get(i).update(board);
        ghosts.get(i).update(board);
        ghosts.get(i).update(board);
    }

    }
    private void remove() {
      pacman.remove();
    }
}

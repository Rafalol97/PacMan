package rafalwisnia.LevelUtilities;


import rafalwisnia.Entity.Entity;
import rafalwisnia.Entity.Pacmann;

import java.util.ArrayList;
import java.util.List;


public class Level  {
    private List<Entity> ghosts = new ArrayList<Entity>();
    private Entity pacman;
    private Border border;
    private Board board ;
        public Level() {
            border = new Border();
            board = new Board(1000,700);
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
        pacman.update(board);
    }
    private void remove() {
      pacman.remove();
    }
}

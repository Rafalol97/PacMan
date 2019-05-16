package rafalwisnia.Entity;

import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

;import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost1 extends Mob  {
    private AnimatedSprite spriteTymczasowy = new AnimatedSprite(Sprite.ghost_1);
     Random random = new Random();
     Random random2 = new Random();
    private Board board;
    private PathFinder pathFinder;
    private Entity pacman;

    public Ghost1(int x,int y,PathFinder pathFinder,Entity pacman,Board board) {
        this.pathFinder = pathFinder;
        this.pacman = pacman;
        this.board = board;
        this.x=x;
        this.y=y;
        direction = Directions.UP;
    }

    public void render(Screen screen){
        screen.renderMob(x,y,spriteTymczasowy.getSprite(),0);
    }

    public void update() {
        if (this.x % 50 == 0 && this.y % 50 == 0) {
            int coordinates[] = board.getTileWhereAmI(x, y);
          /*  List<PathFinder.Node> nodes = pathFinder.compute(new PathFinder.Node(coordinates[0], coordinates[1]));
            if (nodes != null) {
                for (int i = 0; i < nodes.size() - 1; i++) {
                    System.out.println(nodes.get(i).toString());
                }
            }
            if (nodes != null) {
                changeDirectionTowardsNode(nodes, coordinates);
            }
             (else {

                System.out.printf("Mam cie");
            }

           */
        }
        if (chceckforObstacles(board)) {
            changeToRandomDirection(board);
        } else {
            move();
        }
    }
    private void changeDirectionTowardsNode(List<PathFinder.Node> nodes,int [] coordinates){
        if(nodes.get(0).x>coordinates[0]){
            direction = Directions.RIGHT;
        }
        if(nodes.get(0).x<coordinates[0]){
            direction = Directions.LEFT;
        }
        if(nodes.get(0).y>coordinates[1]){
            direction = Directions.DOWN;
        }
        if(nodes.get(0).y<coordinates[1]){
            direction = Directions.RIGHT;
        }
    }
    @Override
    public void update(Board board) {

    }

    @Override
    public void changeFrame() {
    }

    private void changeToRandomDirection(Board board){
        ArrayList<Integer> lista = new ArrayList<>();
        if (checkPossibleDirectionChangeGhost(Directions.DOWN, board)) {
            lista.add(2);
        }
        if (checkPossibleDirectionChangeGhost(Directions.UP, board)) {
            lista.add(0);
        }
        if (checkPossibleDirectionChangeGhost(Directions.RIGHT, board)) {
            lista.add(1);
        }
        if (checkPossibleDirectionChangeGhost(Directions.LEFT, board)) {
            lista.add(3);
        }
        int los = lista.get(random.nextInt(lista.size()));
        if(los==0)direction = Directions.UP;
        if(los==1)direction = Directions.RIGHT;
        if(los==2)direction = Directions.DOWN;
        if(los==3)direction = Directions.LEFT;
    }


}

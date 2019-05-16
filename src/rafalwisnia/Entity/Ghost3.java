package rafalwisnia.Entity;

import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

;import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost3 extends Ghost  {
    private ArrayList<AnimatedSprite[]> listaKlatek = new ArrayList<>() ;
    private AnimatedSprite klatkiDuszekUp[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekDown[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekRight[] = new AnimatedSprite[2];
    private AnimatedSprite klatkiDuszekLeft[] = new AnimatedSprite[2];

    private Sprite sprite;
    Random random = new Random();
    Random random2 = new Random();
    private Board board;
    private PathFinder pathFinder;
    private Entity pacman;

    public Ghost3(int x,int y,Board board) {
        klatkiDuszekRight[0] = new AnimatedSprite(Sprite.ghost_3_1);
        klatkiDuszekRight[1] = new AnimatedSprite(Sprite.ghost_3_2);

        klatkiDuszekDown[0] = new AnimatedSprite(Sprite.ghost_3_3);
        klatkiDuszekDown[1] = new AnimatedSprite(Sprite.ghost_3_4);

        klatkiDuszekUp[0] = new AnimatedSprite(Sprite.ghost_3_5);
        klatkiDuszekUp[1] = new AnimatedSprite(Sprite.ghost_3_6);

        klatkiDuszekLeft[0] = new AnimatedSprite(Sprite.ghost_3_7);
        klatkiDuszekLeft[1] = new AnimatedSprite(Sprite.ghost_3_8);

        listaKlatek.add(klatkiDuszekUp);
        listaKlatek.add(klatkiDuszekRight);
        listaKlatek.add(klatkiDuszekDown);
        listaKlatek.add(klatkiDuszekLeft);

        this.board = board;
        this.x=x;
        this.y=y;
        direction = Directions.UP;
        frameSpeed = 10;

    }

    public void render(Screen screen){
        sprite =listaKlatek.get(directionIter)[klatka].getSprite();
        screen.renderMob(x,y,sprite,0);
    }


    @Override
    public void update(Board board) {
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
        if(random.nextInt(2)==0){
            changeToRandomDirection(board);
        }
        if (chceckforObstacles(board)) {
            changeToRandomDirection(board);

        } else {
            move();
        }
    }




}

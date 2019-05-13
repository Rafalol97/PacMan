package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.AnimatedSprite;
import rafalwisnia.UI.Sprite;

;import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost1 extends Mob  {
    private AnimatedSprite spriteTymczasowy = new AnimatedSprite(Sprite.pacmann_dol_3);
     Random random = new Random();
     Random random2 = new Random();

    public Ghost1(int x,int y) {
        this.x=x;
        this.y=y;
        direction = Directions.RIGHT;
    }

    public void render(Screen screen){
        screen.renderMob(x,y,spriteTymczasowy.getSprite(),0);
    }
    public void update(Board board){
        if(chceckforObstacles(board)) {
            changeToRandomDirection(board);
        }
        else{
            move();
        }
        if(random.nextInt(10)==5) {

            changeToRandomDirection(board);
        }
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
    protected boolean checkPossibleDirectionChangeGhost(Directions direction, Board board) {
        if (checkNeighbour(board, direction) && this.x % 50 == 0 && this.y % 50 == 0) return true;
        else if (this.direction==direction) return  true;
        else return false;
    }

}

package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

public class Coin extends Entity {

    public static int count=0;
    public Coin(int x, int y, Sprite sprite) {
        super(x*50+300, y*50+100, sprite);
    }

    public void render(Screen screen){
       screen.renderSprite(x,y,this.getSprite(),true);
   }

    @Override
    public void update(Board board) {

    }
}

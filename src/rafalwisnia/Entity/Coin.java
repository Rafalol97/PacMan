package rafalwisnia.Entity;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

/** Coin.java
 * Klasa reprezentujaca coin lezacy na ziemi
 * Posiada element static count dzieki ktorremu zliczamy liczbe pozostalych do zjedzenia przez pacmana coinow
 *
 */
public class Coin extends Entity {

    public static int count=0;
    public Coin(int x, int y, Sprite sprite) {
        super(x*50+300, y*50+100, sprite);
    }

    /** Metoda render wywoluje funkcje render w obiekcie klasy Screeen, ktora wstawia coin w wybrane miejsce na planszy
     *
     * @param screen - referencja obiektu klasy Screen
     */
    public void render(Screen screen){
       screen.renderSprite(x,y,this.getSprite());
   }

    @Override
    public void update(Board board) {

    }
}

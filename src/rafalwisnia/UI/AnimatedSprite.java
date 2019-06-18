package rafalwisnia.UI;

/**
 * AnimatedSprite.java  W tej klasie zapisujemy wszystkie animowane obiekty klasy Sprite w naszej grze.
 */

public class AnimatedSprite {
    private Sprite sprite;
    public Sprite getSprite(){
       return  sprite;
    }

    public AnimatedSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}

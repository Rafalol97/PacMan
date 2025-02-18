package rafalwisnia.Events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**  Keybord.java
 *   Klasa obslugujaca nacisniecie klawiszy WASD, ESC oraz strzalek
 *
 */
public class Keyboard implements KeyListener {

    public boolean[] keys = new boolean[210];
    public boolean up, down, left, right, esc;

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        esc = keys[KeyEvent.VK_ESCAPE];
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] =false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] =true;
    }
}

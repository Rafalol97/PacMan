package rafalwisnia;

import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Edytor extends Canvas {

    public Screen screen;

    private BufferedImage image = new BufferedImage(1400, 900, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli


    public JFrame frame;

    public Edytor() {
        Dimension size = new Dimension(1400, 900); //ustawienie wilkosci
        setPreferredSize(size);

        screen = new Screen(1400, 900);

        frame = new JFrame();
        //frame.setLayout(null);


        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy(); //pobieramy ustawienia buffera z canvas'a
        if (bs == null) {
            createBufferStrategy(3); //stworzenie buffera jesli nie istnial z wartoscia 3 dla bufferowania wiekszej ilosci rzeczy naraz (bufferują się dwa kolejne obrazy na raz)
            return;
        }

        screen.clear();


        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 200;

            Graphics g = bs.getDrawGraphics(); //ustawienie bufferow pod grafike


            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

            g.dispose(); //wyswietlenie grafiki stworzonej powyzej
            bs.show(); //pokazanie buffera w celu jego wyczyszczenia
        }
    }

}

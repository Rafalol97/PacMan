package rafalwisnia;

import rafalwisnia.AstarSearchAlgorithm.PathFinder;
import rafalwisnia.Entity.Pacmann;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable {
    //private  static final long serialVersionUID = 1L;

    public static int width = 1600;
    public static int height = width / 16 * 9;
    public static int scale = 1;
    public static String title = "PacMan";


    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private boolean running = false;

    public static Screen screen;
    private Level level;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale); //ustawienie wilkosci
        setPreferredSize(size); //ustawienie wielkosci procesu gry

        screen = new Screen(width, height); //tworzymy obiekt screen którym będziemy zmieniac nasze piksele

        frame = new JFrame(); //stworzenie nowego obiektu okienka javy
        level = new Level();
        key = new Keyboard();
        addKeyListener(key);
        level.add(new Pacmann(700,600,key));
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //RUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0; // ta zmiena bedzie zliczal ile ramek generujemy na sek
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) *(60) /1000000000.0;
            lastTime = now;

            while (delta >= 1) { //timer upewniajacy sie ze robi update 60 razy na sek
                update();
                updates++; //ile updatow robimy na sek
                delta--;

            }

            render();
            frames++; //ile ramek robimy na sek
            if (System.currentTimeMillis() - timer > 1000) { //FPS i updaty
                timer += 1000;
                System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
    }

    //UPDATE TU !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void update() {
        key.update();
        level.update();
    }

    //RENDEROWANIE !!!!!!!!!
    public void render() {
        BufferStrategy bs = getBufferStrategy(); //pobieramy ustawienia buffera z canvas'a
        if (bs == null) {
            createBufferStrategy(3); //stworzenie buffera jesli nie istnial z wartoscia 3 dla bufferowania wiekszej ilosci rzeczy naraz (bufferują się dwa kolejne obrazy na raz)
            return;
        }

        screen.clear();

        level.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics(); //ustawienie bufferow pod grafike

        g.drawImage(image, 0,0, getWidth(), getHeight(), null);

        g.dispose(); //wyswietlenie grafiki stworzonej powyzej
        bs.show(); //pokazanie buffera w celu jego wyczyszczenia
    }

    public static void main(String[] args) {
        Game game = new Game();
        //ustawienia okienka
        game.frame.setResizable(false); //NIE MA MAKSYMALIZACJI
        game.frame.setTitle(Game.title); //Tytuł okienka
        game.frame.add(game); //Wypełniamy okno naszą "grą" dzięki canvas'owi
        //game.frame.setUndecorated(true);  //Usuniecie paska na gorze
        game.frame.pack(); //ustawia wielkosc okna na podstawie wcześniej ustawione "size" w konstruktorze
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wyłączenie procesu razem z zamknięciem okna
        game.frame.setLocationRelativeTo(null); //ustawienie żeby okienko uruchamiało się w środku ekranu
        game.frame.setVisible(true); //ustawienie okienka żeby było widoczne


        //start gry
        game.start();

    }

}

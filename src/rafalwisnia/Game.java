package rafalwisnia;

import rafalwisnia.Entity.Pacman;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Board;
import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Game.java - glowna klasa programu. Z jej pomoca:
 * - przechowujemy i tworzymy okno w ktorym przechowujemy Canvas
 * - Klasa ta implementuje interfejs Runnable ktory jest glownym watkiem naszej gry
 * - Zarzadza odpowiednim przydzialem czasu dla update (aktualizowania logiki)
 * - Zarzadza przydzielaniem czasu dla render (rysowanie i tworzenie okno gry)
 * - Powstaje dwoch klas Edytor i Menu Startowe
 * - Dodatkowo wszystkie przyciski powstaja w tej klasie dla wszystkich trzech okien
 *
 */
public class Game extends Canvas implements Runnable {
    //private  static final long serialVersionUID = 1L;

    public static int width = 1600;
    public static int height = width / 16 * 9;
    public static int scale = 1;
    public static String title = "PacMan";
    JButton wznow = new JButton("Wznów grę");
    JButton resetGry = new JButton("Restart");
    JButton wylacz = new JButton("Wyjdź :(");
    JButton edytor = new JButton("Edytor poziomu");


    private Thread thread;
    private JFrame gameWindow;
    private static Keyboard key;
    private boolean running = false;

    public static Screen screen;
    private static Level level;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //obraz w ktorym bedziemy modyfikowac pixele
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //zmieniamy obiekt stworzony wyzej w tablice pixeli


    public Game() {
        Dimension size = new Dimension(width * scale, height * scale); //ustawienie wilkosci
        setPreferredSize(size); //ustawienie wielkosci procesu gry

        screen = new Screen(width, height); //tworzymy obiekt screen którym będziemy zmieniac nasze piksele

        gameWindow = new JFrame(); //stworzenie nowego obiektu okienka javy
        key = new Keyboard();
        level = new Level();
        level.add(new Pacman(800,600,key,3));
        addKeyListener(key);


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


    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0; // ta zmiena bedzie zliczal ile ramek generujemy na sek
        int updates = 0;
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) /ns;
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
             //   System.out.println(updates + " ups, " + frames + " fps");
                gameWindow.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    public void update() {
        key.update();
        if(key.esc){
            if(level.pauza){
                level.pauza=false;
                wznow.setVisible(false);
                wylacz.setVisible(false);
                resetGry.setVisible(false);
                edytor.setVisible(false);
                key.keys[KeyEvent.VK_ESCAPE]=false;
            }
            else{
                level.pauza=true;
                wznow.setVisible(true);
                wylacz.setVisible(true);
                resetGry.setVisible(true);
                edytor.setVisible(true);
                key.keys[KeyEvent.VK_ESCAPE]=false;
            }
        }

        level.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy(); //pobieramy ustawienia buffera z canvas'a
        if (bs == null) {
            createBufferStrategy(3); //stworzenie buffera jesli nie istnial z wartoscia 3 dla bufferowania wiekszej ilosci rzeczy naraz (bufferują się dwa kolejne obrazy na raz)
            return;
        }

        screen.clear();

        level.render(screen);
        Color color ;

        if(level.pauza) {

            for (int i = 0; i < pixels.length; i++) {
                color = new Color(screen.pixels[i]);
                pixels[i] = color.darker().darker().darker().getRGB();
            }
        }
        else{
            for (int i = 0; i < pixels.length; i++) { pixels[i] = screen.pixels[i];
            }
        }

        Graphics g = bs.getDrawGraphics(); //ustawienie bufferow pod grafike


        g.drawImage(image, 0,0, getWidth(), getHeight(), null);

        g.dispose(); //wyswietlenie grafiki stworzonej powyzej
        bs.show(); //pokazanie buffera w celu jego wyczyszczenia
    }

    public void renderEditor(Edytor edytor) {
        edytor.repaint();
    }

    /**
     * Tutaj tworzymy:
     * - obiekt klasy Dame
     * - obiekt klasy Menu
     * - obiekt klasy Edytor
     * i ustawiamy parametry ich okienek oraz funkcje klawiszy
     * @param args parametry wejsciowe programu
     */
    public static void main(String[] args) {
        final boolean[] Dont = {true};

        Menu menu = new Menu();
        menu.menuWindow.setVisible(true);

        menu.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dont[0] = false;
            }
        });

        menu.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        Edytor edytorWindow = new Edytor();
        edytorWindow.frame.setResizable(false);
        edytorWindow.frame.add(edytorWindow);
        edytorWindow.frame.pack();
        edytorWindow.frame.setLocationRelativeTo(null);
        edytorWindow.frame.setVisible(true);

        edytorWindow.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               edytorWindow.writeMatrix("level.txt");
            }
        });

        edytorWindow.loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("elo");
            }
        });

        edytorWindow.loadToGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("elo");
                Board.readMatrixFromGame("level.txt");
                Game.level.clearLevel(false);
            }
        });

        edytorWindow.loadFromGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("elo");
            }
        });

        edytorWindow.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("elo");
            }
        });


        Game game = new Game();
        game.renderEditor(edytorWindow);
        //ustawienia okienka
       // jPanel.setBackground(Color.white);
        game.gameWindow.setResizable(false);
        game.gameWindow.setTitle(Game.title); //Tytuł okienka

        game.wznow.setBounds(725,335,150,50);
        game.resetGry.setBounds(725,395,150,50);
        game.edytor.setBounds(725,455,150,50);
        game.wylacz.setBounds(725,515,150,50);
        game.wznow.setVisible(false);
        game.resetGry.setVisible(false);
        game.wylacz.setVisible(false);
        game.edytor.setVisible(false);
        game.gameWindow.add(game.wznow);
        game.gameWindow.add(game.resetGry);
        game.gameWindow.add(game.wylacz);
        game.gameWindow.add(game.edytor);

        game.wznow.setForeground(Color.BLACK);
        game.wznow.setBackground(Color.WHITE);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        game.wznow.setBorder(compound);
        game.wznow.setFocusPainted(false);

        game.resetGry.setForeground(Color.BLACK);
        game.resetGry.setBackground(new Color(0x78BEAF0E));
        game.resetGry.setBorder(compound);
        game.resetGry.setFocusPainted(false);
;

        game.wylacz.setForeground(Color.BLACK);
        game.wylacz.setBackground(new Color(0x9B3B35));
        game.wylacz.setBorder(compound);
        game.wylacz.setFocusPainted(false);

        game.edytor.setForeground(Color.BLACK);
        game.edytor.setBackground(new Color(0x459B59));
        game.edytor.setBorder(compound);
        game.edytor.setFocusPainted(false);

        game.wznow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.edytor.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                Game.level.pauza=false;
            }
        });

        game.resetGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.level.pauza=false;
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.edytor.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                Game.level.clearLevel(false);
            }
        });

        game.wylacz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        game.edytor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 edytorWindow.frame.setVisible(true);
                 game.renderEditor(edytorWindow);

            }
        });

        game.gameWindow.add(game); //Wypełniamy okno naszą "grą" dzięki canvas'owi
        //game.gameWindow.setUndecorated(true);  //Usuniecie paska na gorze
        game.gameWindow.pack(); //ustawia wielkosc okna na podstawie wcześniej ustawione "size" w konstruktorze
        game.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wyłączenie procesu razem z zamknięciem okna
        game.gameWindow.setLocationRelativeTo(null); //ustawienie żeby okienko uruchamiało się w środku ekranu
        //game.gameWindow.setUndecorated(true);


        while(Dont[0]) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            menu.menucanvasUP.animacja();
            menu.menucanvasDOWN.animacja();
            menu.menucanvasLEFT.animacja();
            menu.menucanvasRIGHT.animacja();
        }

        menu.menuWindow.setVisible(false);
        game.gameWindow.setVisible(true); //ustawienie okienka żeby było widoczne

        //start gry
        game.start();

    }
    public static void endthis(){

    }

}

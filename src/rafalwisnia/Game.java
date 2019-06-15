package rafalwisnia;

import rafalwisnia.Entity.Pacmann;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

class MenucanvasUP extends Canvas {
    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/123.png");
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 0, this);
    }
}

class MenucanvasDOWN extends Canvas {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

class MenucanvasLEFT extends Canvas {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

class MenucanvasRIGHT extends Canvas {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

class MenucanvasMIDDLE extends Canvas {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

public class Game extends Canvas implements Runnable {
    //private  static final long serialVersionUID = 1L;

    public static int width = 1600;
    public static int height = width / 16 * 9;
    public static int scale = 1;
    public static String title = "PacMan";


    private Thread thread;
    private JFrame gameWindow;
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

        gameWindow = new JFrame(); //stworzenie nowego obiektu okienka javy
        key = new Keyboard();
        level = new Level();
        level.add(new Pacmann(800,600,key));
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

    //RUNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN
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
                System.out.println(updates + " ups, " + frames + " fps");
                gameWindow.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
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

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 500, 800);
    }

    public static void main(String[] args) {
        final boolean[] Dont = {true};
        JButton startButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        MenucanvasUP menucanvasUP = new MenucanvasUP();
        MenucanvasDOWN menucanvasDOWN = new MenucanvasDOWN();
        MenucanvasRIGHT menucanvasRIGHT = new MenucanvasRIGHT();
        MenucanvasLEFT menucanvasLEFT = new MenucanvasLEFT();
        MenucanvasMIDDLE menucanvasMIDDLE = new MenucanvasMIDDLE();

        JFrame menuWindow = new JFrame();
        menuWindow.setLayout(null);

        menuWindow.setResizable(false);
        menuWindow.setUndecorated(true);
        menuWindow.setSize(500, 800);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menucanvasUP.setBounds(100,0,300,400);
        menucanvasDOWN.setBounds(100, 550, 300, 250);
        menucanvasLEFT.setBounds(0,0,100,800);
        menucanvasRIGHT.setBounds(400,0,100,800);
        menucanvasMIDDLE.setBounds(100,450,300,50);

        startButton.setBounds(100, 400, 300, 50);
        exitButton.setBounds(100, 500, 300, 50);

        menuWindow.add(menucanvasUP);
        menuWindow.add(menucanvasDOWN);
        menuWindow.add(menucanvasLEFT);
        menuWindow.add(menucanvasRIGHT);
        menuWindow.add(menucanvasMIDDLE);

        menuWindow.add(startButton);
        menuWindow.add(exitButton);

        menuWindow.setVisible(true);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dont[0] = false;
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });



        Game game = new Game();
        //ustawienia okienka
        game.gameWindow.setResizable(false); //NIE MA MAKSYMALIZACJI
        game.gameWindow.setTitle(Game.title); //Tytuł okienka
        game.gameWindow.add(game); //Wypełniamy okno naszą "grą" dzięki canvas'owi
        //game.gameWindow.setUndecorated(true);  //Usuniecie paska na gorze
        game.gameWindow.pack(); //ustawia wielkosc okna na podstawie wcześniej ustawione "size" w konstruktorze
        game.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wyłączenie procesu razem z zamknięciem okna
        game.gameWindow.setLocationRelativeTo(null); //ustawienie żeby okienko uruchamiało się w środku ekranu

        while(Dont[0]) {
            System.out.println("O MAJ FUCKING GAD");;
        }

        menuWindow.setVisible(false);
        game.gameWindow.setVisible(true); //ustawienie okienka żeby było widoczne

        //start gry
        game.start();

    }
    public static void endthis(){

    }

}

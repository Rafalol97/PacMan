package rafalwisnia;

import rafalwisnia.Entity.Pacmann;
import rafalwisnia.Events.Keyboard;

import rafalwisnia.LevelUtilities.Level;
import rafalwisnia.LevelUtilities.Screen;
import rafalwisnia.UI.Sprite;

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

class MenucanvasUP extends Canvas {
    Integer pozycja;
    Image title = Toolkit.getDefaultToolkit().getImage("resources/textures/Board/title.png");

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
                            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
                            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
                            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    public MenucanvasUP(Integer pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
        g.drawImage(title, 0, 80, this);

    }
}

class MenucanvasDOWN extends Canvas {
    Integer pozycja;

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    public MenucanvasDOWN(Integer pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

class MenucanvasLEFT extends Canvas {
    Integer pozycja;

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    public MenucanvasLEFT(Integer pozycja) {
        this.pozycja = pozycja;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,1000,1000);
    }
}

class MenucanvasRIGHT extends Canvas {
    Integer pozycja;

    Sprite[][] pacman =    {{Sprite.pacmann_gora_0, Sprite.pacmann_gora_1, Sprite.pacmann_gora_2, Sprite.pacmann_gora_3},
            {Sprite.pacmann_prawo_0, Sprite.pacmann_prawo_1, Sprite.pacmann_prawo_2, Sprite.pacmann_prawo_3},
            {Sprite.pacmann_dol_0, Sprite.pacmann_dol_1, Sprite.pacmann_dol_2, Sprite.pacmann_dol_3},
            {Sprite.pacmann_lewo_0, Sprite.pacmann_lewo_1, Sprite.pacmann_lewo_2, Sprite.pacmann_lewo_3}};

    public MenucanvasRIGHT(Integer pozycja) {
        this.pozycja = pozycja;
    }

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
    JButton wznow = new JButton("Wznów grę");
    JButton resetGry = new JButton("Restart");
    JButton wylacz = new JButton("Wyjdź :(");
    JButton edytor = new JButton("Edytor poziomu");


    private Thread thread;
    private JFrame gameWindow;
    private static Keyboard key;
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
        level.add(new Pacmann(800,600,key,3));
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
             //   System.out.println(updates + " ups, " + frames + " fps");
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

    //RENDEROWANIE !!!!!!!!!
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
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = screen.pixels[i];
            }
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

        Integer pozycja = 0;

        MenucanvasUP menucanvasUP = new MenucanvasUP(pozycja);
        MenucanvasDOWN menucanvasDOWN = new MenucanvasDOWN(pozycja);
        MenucanvasRIGHT menucanvasRIGHT = new MenucanvasRIGHT(pozycja);
        MenucanvasLEFT menucanvasLEFT = new MenucanvasLEFT(pozycja);
        MenucanvasMIDDLE menucanvasMIDDLE = new MenucanvasMIDDLE();

        JFrame menuWindow = new JFrame();
        menuWindow.setLayout(null);

        menuWindow.setResizable(false);
        menuWindow.setUndecorated(true);
        menuWindow.setSize(600, 800);
        menuWindow.setLocationRelativeTo(null);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menucanvasUP.setBounds(80,0,440,450);
        menucanvasDOWN.setBounds(80, 700, 440, 100);
        menucanvasLEFT.setBounds(0,0,80,800);
        menucanvasRIGHT.setBounds(520,0,80,800);
        menucanvasMIDDLE.setBounds(80,550,440,50);

        startButton.setBounds(80, 450, 440, 100);
        exitButton.setBounds(80, 600, 440, 100);

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
       // jPanel.setBackground(Color.white);
        game.gameWindow.setResizable(false); //NIE MA MAKSYMALIZACJI
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


        game.wznow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                game.level.pauza=false;
            }
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.resetGry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.level.pauza=false;
                game.wznow.setVisible(false);
                game.wylacz.setVisible(false);
                game.resetGry.setVisible(false);
                game.requestFocus();
                game.level.clearLevel(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.wylacz.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        game.edytor.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mam cie");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton[] jButtons = new JButton[3];



        game.gameWindow.add(game); //Wypełniamy okno naszą "grą" dzięki canvas'owi
        //game.gameWindow.setUndecorated(true);  //Usuniecie paska na gorze
        game.gameWindow.pack(); //ustawia wielkosc okna na podstawie wcześniej ustawione "size" w konstruktorze
        game.gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //wyłączenie procesu razem z zamknięciem okna
        game.gameWindow.setLocationRelativeTo(null); //ustawienie żeby okienko uruchamiało się w środku ekranu
        //game.gameWindow.setUndecorated(true);


        while(Dont[0]) {
            System.out.println("hhe");
        }

        menuWindow.setVisible(false);
        game.gameWindow.setVisible(true); //ustawienie okienka żeby było widoczne

        //start gry
        game.start();

    }
    public static void endthis(){

    }

}

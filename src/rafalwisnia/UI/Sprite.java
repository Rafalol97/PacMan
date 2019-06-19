package rafalwisnia.UI;

/**
 * Sprite.
 * Przechowuje i umozliwia generowanie obrazu koncowego z poszczegolnych grafik pikselowych.
 * Grafiki pacmana i obiektow terenu sa tworzone za pomoca wyciecia ich z obiektu klasu SpriteSheet.
 * Wszystkie obiekty typu Sprite są tworzone z typem danych static, w celu prostego odwolania sie w innych klasach bez potrzeby
 * tworzenia obiektu tej klasy
 */
public class Sprite {
    public final int SIZE;
    private int x,y;
    private int width,height;
    private SpriteSheet sheet;
    public int [] pixels;

    //Sciany
    public static Sprite brick_1 = new Sprite(50,0,17,SpriteSheet.pacmann);
    public static Sprite brick_2 = new Sprite(50,1,17,SpriteSheet.pacmann);
    public static Sprite brick_3 = new Sprite(50,0,18,SpriteSheet.pacmann);
    public static Sprite brick_4 = new Sprite(50,1,18,SpriteSheet.pacmann);
    public static Sprite brick_5 = new Sprite(50,0,19,SpriteSheet.pacmann);
    public static Sprite brick_6 = new Sprite(50,1,19,SpriteSheet.pacmann);

    //Sciany obrocome
    public static Sprite brick_7 = new Sprite(50,2,18,SpriteSheet.pacmann);
    public static Sprite brick_8 = new Sprite(50,3,18,SpriteSheet.pacmann);
    public static Sprite brick_9 = new Sprite(50,4,18,SpriteSheet.pacmann);
    public static Sprite brick_10 = new Sprite(50,2,19,SpriteSheet.pacmann);
    public static Sprite brick_11 = new Sprite(50,3,19,SpriteSheet.pacmann);
    public static Sprite brick_12 = new Sprite(50,4,19,SpriteSheet.pacmann);

    //Dodatkowe
    public static Sprite brick_13 = new Sprite(50,2,17,SpriteSheet.pacmann);
    public static Sprite brick_17 = new Sprite(50,3,17,SpriteSheet.pacmann);
    //Podloga
    public static Sprite podloga = new Sprite(50,10,10,SpriteSheet.pacmann);

    //Do edytora
    public static Sprite brick_18 = new Sprite(50,6,19,SpriteSheet.pacmann);
    public static Sprite brick_19 = new Sprite(50,5,19,SpriteSheet.pacmann);
    public static Sprite brick_20 = new Sprite(50,5,18,SpriteSheet.pacmann);


    //Pacman
    public static Sprite pacmann_prawo_0 = new Sprite(50,0,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_1 = new Sprite(50,1,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_2 = new Sprite(50,2,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_3 = new Sprite(50,3,0,SpriteSheet.pacmann);

    public static Sprite pacmann_lewo_0 = new Sprite(50,0,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_1 = new Sprite(50,1,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_2 = new Sprite(50,2,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_3 = new Sprite(50,3,1,SpriteSheet.pacmann);

    public static Sprite pacmann_gora_0 = new Sprite(50,4,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_1 = new Sprite(50,5,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_2 = new Sprite(50,6,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_3 = new Sprite(50,7,1,SpriteSheet.pacmann);

    public static Sprite pacmann_dol_0 = new Sprite(50,4,0,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_1 = new Sprite(50,5,0,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_2 = new Sprite(50,6,0,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_3 = new Sprite(50,7,0,SpriteSheet.pacmann);
    //Pacman po śmierci

    public static Sprite pacman_smierc1 = new Sprite(50,8,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc2 = new Sprite(50,9,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc3  = new Sprite(50,10,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc4  = new Sprite(50,11,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc5 = new Sprite(50,12,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc6 = new Sprite(50,13,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc7  = new Sprite(50,14,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc8  = new Sprite(50,15,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc9  = new Sprite(50,16,0,SpriteSheet.pacmann);
    public static Sprite pacman_smierc10  = new Sprite(50,17,0,SpriteSheet.pacmann);

    //Ghost1
    public static Sprite ghost_1_1  =new Sprite(50,0,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_2  =new Sprite(50,1,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_3  =new Sprite(50,2,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_4  =new Sprite(50,3,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_5  =new Sprite(50,4,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_6  =new Sprite(50,5,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_7  =new Sprite(50,6,2,SpriteSheet.pacmann);
    public static Sprite ghost_1_8  =new Sprite(50,7,2,SpriteSheet.pacmann);
    //Ghost2
    public static Sprite ghost_2_1  =new Sprite(50,0,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_2  =new Sprite(50,1,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_3  =new Sprite(50,2,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_4  =new Sprite(50,3,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_5  =new Sprite(50,4,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_6  =new Sprite(50,5,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_7  =new Sprite(50,6,3,SpriteSheet.pacmann);
    public static Sprite ghost_2_8  =new Sprite(50,7,3,SpriteSheet.pacmann);
    //Ghost3
    public static Sprite ghost_3_1  =new Sprite(50,0,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_2  =new Sprite(50,1,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_3  =new Sprite(50,2,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_4  =new Sprite(50,3,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_5  =new Sprite(50,4,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_6  =new Sprite(50,5,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_7  =new Sprite(50,6,4,SpriteSheet.pacmann);
    public static Sprite ghost_3_8  =new Sprite(50,7,4,SpriteSheet.pacmann);
    //Ghost4
    public static Sprite ghost_4_1  =new Sprite(50,0,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_2  =new Sprite(50,1,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_3  =new Sprite(50,2,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_4  =new Sprite(50,3,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_5  =new Sprite(50,4,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_6  =new Sprite(50,5,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_7  =new Sprite(50,6,5,SpriteSheet.pacmann);
    public static Sprite ghost_4_8  =new Sprite(50,7,5,SpriteSheet.pacmann);
    public static Sprite ghostScared1 =new Sprite(50,6,5,SpriteSheet.pacmann);
    public static Sprite ghostScared2  =new Sprite(50,7,5,SpriteSheet.pacmann);

    public static  Sprite smallCoin = new Sprite(50,0,6,SpriteSheet.pacmann);
    public static  Sprite bigCoin = new Sprite(50,1,6,SpriteSheet.pacmann);

    public static  Sprite number_0 = new Sprite(50,0,16,SpriteSheet.pacmann);
    public static  Sprite number_1 = new Sprite(50,1,16,SpriteSheet.pacmann);
    public static  Sprite number_2 = new Sprite(50,2,16,SpriteSheet.pacmann);
    public static  Sprite number_3 = new Sprite(50,3,16,SpriteSheet.pacmann);
    public static  Sprite number_4 = new Sprite(50,4,16,SpriteSheet.pacmann);
    public static  Sprite number_5 = new Sprite(50,5,16,SpriteSheet.pacmann);
    public static  Sprite number_6 = new Sprite(50,6,16,SpriteSheet.pacmann);
    public static  Sprite number_7 = new Sprite(50,7,16,SpriteSheet.pacmann);
    public static  Sprite number_8 = new Sprite(50,8,16,SpriteSheet.pacmann);
    public static  Sprite number_9 = new Sprite(50,9,16,SpriteSheet.pacmann);
    public static  Sprite number_10 = new Sprite(50,0,15,SpriteSheet.pacmann);

    public static Sprite duszekPrzestraszony1 = new Sprite(50,2,6,SpriteSheet.pacmann);
    public static Sprite duszekPrzestraszony2 = new Sprite(50,3,6,SpriteSheet.pacmann);
    public static Sprite duszekPrzestraszony3 = new Sprite(50,4,6,SpriteSheet.pacmann);
    public static Sprite duszekPrzestraszony4 = new Sprite(50,4,6,SpriteSheet.pacmann);

    public static Sprite oczy_Prawo = new Sprite(50,0,7,SpriteSheet.pacmann);
    public static Sprite oczy_Dol = new Sprite(50,1,7,SpriteSheet.pacmann);
    public static Sprite oczy_Gora = new Sprite(50,2,7,SpriteSheet.pacmann);
    public static Sprite oczy_Lewa = new Sprite(50,3,7,SpriteSheet.pacmann);


    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        SIZE = size;
        this.width = size;
        this.height = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }


    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        this.pixels = pixels;

    }
    private void load() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getSPRITE_WIDTH()];
            }
        }
    }
    public int getPixel(int x, int y){
        return  pixels[x+y*this.SIZE];
    }

}

package rafalwisnia.UI;

public class Sprite {
    public final int SIZE;
    private int x,y;
    private int width,height;
    public SpriteSheet sheet;
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

    public static Sprite brick = new Sprite(50,1,0,SpriteSheet.tiles);

    //Podloga
    public static Sprite podloga = new Sprite(50,10,10,SpriteSheet.pacmann);


    //Pacmann
    public static Sprite pacmann_prawo_0 = new Sprite(50,0,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_1 = new Sprite(50,1,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_2 = new Sprite(50,2,0,SpriteSheet.pacmann);
    public static Sprite pacmann_prawo_3 = new Sprite(50,3,0,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_0 = new Sprite(50,0,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_1 = new Sprite(50,1,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_2 = new Sprite(50,2,1,SpriteSheet.pacmann);
    public static Sprite pacmann_lewo_3 = new Sprite(50,3,1,SpriteSheet.pacmann);

    public static Sprite pacmann_gora_0 = new Sprite(50,0,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_1 = new Sprite(50,1,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_2 = new Sprite(50,2,1,SpriteSheet.pacmann);
    public static Sprite pacmann_gora_3 = new Sprite(50,3,1,SpriteSheet.pacmann);

    public static Sprite pacmann_dol_0 = new Sprite(50,0,1,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_1 = new Sprite(50,1,1,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_2 = new Sprite(50,2,1,SpriteSheet.pacmann);
    public static Sprite pacmann_dol_3 = new Sprite(50,3,1,SpriteSheet.pacmann);
   public static Sprite ghost_1  =new Sprite(50,0,3,SpriteSheet.pacmann);
    public static Sprite coin = new Sprite(50,3,1,SpriteSheet.pacmann);
  /*
   // public static Sprite podlogaCoin = new Sprite(50,1,0,SpriteSheet.tiles);
    public static Sprite pacmann_prawo_0 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);
  //  public static Sprite pacmann_2 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);
   // public static Sprite pacmann_3 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);
  //  public static Sprite pacmann_4 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);
   // public static Sprite pacmann_5 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);
  //  public static Sprite pacmann_6 = new Sprite(50,1,1,SpriteSheet.pacmann_prawo_0);






    public static Sprite ghost_1  =new Sprite(50,0,0,SpriteSheet.ghosts);
    public static Sprite ghost_1_down = new Sprite (50,0,1,SpriteSheet.ghosts);
    public static Sprite ghost_1_right = new Sprite (50 ,1,0,SpriteSheet.ghosts);
    public static Sprite ghost_1_left = new Sprite (50 ,1,1,SpriteSheet.ghosts);

    public static Sprite ghost_2  =new Sprite(50,0,0,SpriteSheet.ghosts);
    public static Sprite ghost_2_down = new Sprite (50,0,1,SpriteSheet.ghosts);
    public static Sprite ghost_2_right = new Sprite (50 ,1,0,SpriteSheet.ghosts);
    public static Sprite ghost_2_left = new Sprite (50 ,1,1,SpriteSheet.ghosts);

    public static Sprite ghost_3  =new Sprite(50,0,0,SpriteSheet.ghosts);
    public static Sprite ghost_3_down = new Sprite (50,0,1,SpriteSheet.ghosts);
    public static Sprite ghost_3_right = new Sprite (50 ,1,0,SpriteSheet.ghosts);
    public static Sprite ghost_3_left = new Sprite (50 ,1,1,SpriteSheet.ghosts);

    public static Sprite ghost_4  =new Sprite(50,0,0,SpriteSheet.ghosts);
    public static Sprite ghost_4_down = new Sprite (50,0,1,SpriteSheet.ghosts);
    public static Sprite ghost_4_right = new Sprite (50 ,1,0,SpriteSheet.ghosts);
    public static Sprite ghost_4_left = new Sprite (50 ,1,1,SpriteSheet.ghosts);
*/

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
        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
    }
    private void load() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
            }
        }
    }
    public int getPixel(int x, int y){
        return  pixels[x+y*this.SIZE];
    }

}

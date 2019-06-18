package rafalwisnia.LevelUtilities;

import rafalwisnia.Events.Event;
import rafalwisnia.Events.EventListener;
import rafalwisnia.UI.Sprite;



public class Points implements EventListener {
    private Sprite  cyferki[]= new Sprite[11];
    public static int poziom;
    private int liczbaPunktow;
    public Points() {
        cyferki[0] = Sprite.number_0;
        cyferki[1] = Sprite.number_1;
        cyferki[2] = Sprite.number_2;
        cyferki[3] = Sprite.number_3;
        cyferki[4] = Sprite.number_4;
        cyferki[5] = Sprite.number_5;
        cyferki[6] = Sprite.number_6;
        cyferki[7] = Sprite.number_7;
        cyferki[8] = Sprite.number_8;
        cyferki[9] = Sprite.number_9;
        cyferki[10] = Sprite.number_10;

    }

    @Override
    public void onEvent(Event event) {

    }
    public void render(Screen screen){
        if(liczbaPunktow>100000){
            liczbaPunktow=99999;
        }
        if(liczbaPunktow>=10000) {
            screen.renderTile(300, 22, cyferki[liczbaPunktow / 10000],0);
        }
        else {
            screen.renderTile(300, 22, cyferki[10],0);
        }

        if(liczbaPunktow>=1000) {
            screen.renderTile(330, 22, cyferki[(liczbaPunktow / 1000)%10],0);
        }
        else {
            screen.renderTile(330, 22, cyferki[10],0);
        }
        if(liczbaPunktow>=100) {
            screen.renderTile(360,22,cyferki[(liczbaPunktow/100)%10],0);
        }
        else {
            screen.renderTile(360, 22, cyferki[10],0);
        }
        if(liczbaPunktow>=10) {
            screen.renderTile(390,22,cyferki[(liczbaPunktow/10)%10],0);
        }
        else {
            screen.renderTile(390, 22, cyferki[10],0);
        }
        if(liczbaPunktow>0) {
            screen.renderLastNumber(420,22,cyferki[liczbaPunktow%10]);
        }
        else {
            screen.renderLastNumber(420, 22, cyferki[10]);
        }
        if(poziom>9){
            screen.renderTile(390,822,cyferki[poziom/10],0);
        }
        screen.renderLastNumber(420,822,cyferki[poziom%10]);

    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    public void add(int points){
        this.liczbaPunktow+=points;
    }

    public void addLevel(){this.poziom++;}

    public void resetLevel(){this.poziom=0;}

}

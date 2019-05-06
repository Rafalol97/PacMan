import UI.Screen;

import java.util.ArrayList;
import java.util.List;

public class Level extends Layer {
    public List<Entity> ghosts = new ArrayList<Entity>();
    public Entity Pacmann = new Entity();

    public void render(Screen screen){
        for(int i=0;i<ghosts.size();i++){
            ghosts.get(i).render(screen);
        }
        Pacmann.render(screen);


    }

}

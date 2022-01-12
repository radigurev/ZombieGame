package Entity;

import Entity.Humans.Player;
import com.game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {
    private Handler handler;



    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> Sort=new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            if(o1.getY()+o1.getHeight()<o2.getY()+o2.getHeight()){
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler,Player player){
        this.handler=handler;
        this.player=player;
        entities=new ArrayList<Entity>();
        addEntity(player);
    }
    public void tick(){
        Iterator<Entity> it=entities.iterator();
       while(it.hasNext()) {
            Entity e=it.next();
            e.tick();
            if(!e.isActive()){
               it.remove();
            }
        }
        entities.sort(Sort);
    }
    public void render(Graphics g){
        for (int i = 0; i <entities.size() ; i++) {
            Entity e=entities.get(i);
            e.render(g);
        }
        player.postRender(g);
    }
    public void addEntity(Entity a){
        entities.add(a);
    }
//Setters && getters
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}

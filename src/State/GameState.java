package State;

import World.World;
import com.game.Handler;

import java.awt.*;

public class GameState extends State {


    private World world;

    public GameState(Handler handler){
        super(handler);
        world=new World(handler,"/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Worlds/world1.txt");
        handler.setWorld(world);

    }

    public void tick() {
        world.tick();
        if(world.getZombies().length==0) {
            State.setState(new GameState(handler));
        }
    }

    public void render(Graphics g) {
        world.render(g);
    }
}

package Entity.StaticEntities;

import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;

import java.awt.*;

public class Door extends StaticEntity{
    public Door(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TileWidth, Tile.TileHeight);

        bounds.x=0;
        bounds.y=0;
        bounds.width=0;
        bounds.height=0;
    }

    @Override
    public void tick() {

    }
    public void die(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Door,(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height,null);

        //Visible hitbox
/*
        g.setColor(Color.CYAN);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);

 */


    }
}

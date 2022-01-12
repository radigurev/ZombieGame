package Entity.StaticEntities;
import Entity.Entity;
import Entity.StaticEntities.StaticEntity;
import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;
import Item.Item;
import java.awt.*;



public class Thing extends StaticEntity {
    public Thing(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TileWidth-25, Tile.TileHeight);

        bounds.x=0;
        bounds.y=0;
        bounds.width=10;
        bounds.height=30;
    }

    @Override
    public void tick() {

    }
    public void die(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Thing,(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height,null);

        //Visible hitbox
/*
        g.setColor(Color.CYAN);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);

 */


    }
}

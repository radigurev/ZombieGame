package Entity.StaticEntities;
import Entity.Entity;
import Entity.StaticEntities.StaticEntity;
import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;
import Item.Item;
import java.awt.*;



public class Flower extends StaticEntity {
    public Flower(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TileWidth, Tile.TileHeight+25);

        bounds.x=20;
        bounds.y=95;
        bounds.width=30;
        bounds.height=30;
    }

    @Override
    public void tick() {

    }
    public void die(){

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Flower,(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height,null);

        //Visible hitbox
        /*
        g.setColor(Color.CYAN);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);
*/



    }
}

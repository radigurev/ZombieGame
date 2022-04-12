package Entity.StaticEntities;

import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;

import java.awt.*;

public class Bench2 extends StaticEntity{
    public Bench2(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TileWidth*3, Tile.TileHeight*4);

        bounds.x=40;
        bounds.y=0;
        bounds.width=Tile.TileWidth*3-90;
        bounds.height=Tile.TileHeight*3;
    }

    @Override
    public void tick() {

    }
    public void die(){

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.Bench2,(int) (x-handler.getGameCamera().getxOffset()),(int) (y-handler.getGameCamera().getyOffset()),width,height,null);

        //Visible hitbox

        g.setColor(Color.CYAN);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);








    }
}

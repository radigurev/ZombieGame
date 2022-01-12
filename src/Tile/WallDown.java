package Tile;

import Graphic.Assets;

public class WallDown extends Tile{
    public WallDown(int id) {
        super(Assets.wallDown, id);

    }
    public boolean isSolid(){
        return true;
    }
}

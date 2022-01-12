package Tile;

import Graphic.Assets;


public class WallUp extends Tile{

    public WallUp(int id) {
        super(Assets.wallUp,id);
    }
    public boolean isSolid(){
        return true;
    }
}

package Tile;

import Graphic.Assets;

public class WallRight extends Tile{
    public WallRight(int id) {
        super(Assets.rightwall,id);
    }
    public boolean isSolid(){
        return true;
    }
}

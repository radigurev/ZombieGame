package Tile;

import Graphic.Assets;

public class DownRightCorner extends Tile{
    public DownRightCorner(int id) {
        super(Assets.DownRightCorner, id);
    }


    public boolean isSolid(){
        return true;
    }
}

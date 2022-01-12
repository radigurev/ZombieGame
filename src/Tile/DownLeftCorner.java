package Tile;

import Graphic.Assets;

public class DownLeftCorner extends Tile{

    public DownLeftCorner(int id) {
        super(Assets.DownLeftCorner, id);
    }


    public boolean isSolid(){
        return true;
    }

}

package Tile;

import Graphic.Assets;

public class TopRightCorner extends Tile{

    public TopRightCorner(int id) {
        super(Assets.toprightcorner, id);
    }


    public boolean isSolid(){
        return true;
    }

}
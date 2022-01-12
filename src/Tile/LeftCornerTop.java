package Tile;

import Graphic.Assets;

public class LeftCornerTop extends Tile{

        public LeftCornerTop(int id) {
            super(Assets.topleftcorner, id);
        }


    public boolean isSolid(){
        return true;
    }

}

package Tile;

import Graphic.Assets;

import java.awt.image.BufferedImage;

public class WallLeft extends Tile{
    public WallLeft(int id) {
        super(Assets.wallLeft, id);

    }
    public boolean isSolid(){
        return true;
    }

}
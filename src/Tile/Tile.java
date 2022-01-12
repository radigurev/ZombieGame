package Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    //TILE ID
    public static Tile[] tiles=new Tile[256];
    public static Tile WallTile=new WallTile(0);
    public static Tile FloorTile=new FloorTile(1);
    public static Tile WallUp=new WallUp(2);
    public static Tile WallLeft=new WallLeft(3);
    public static Tile LeftCornerTop=new LeftCornerTop(4);
    public static Tile TopRightCorner= new TopRightCorner(5);
    public static Tile RightCorner = new WallRight(6);
    public static Tile DownWall=new WallDown(7);
    public static Tile DownLeftCorner= new DownLeftCorner(8);
    public static Tile DownRightCorner= new DownRightCorner(9);

    private boolean positioned=false;
    protected float x,y;

    public static final int TileWidth=64, TileHeight=64;

    protected BufferedImage texture;
    protected final int id;
    public Tile(BufferedImage texture,int id){
        this.texture=texture;
        this.id=id;

        tiles[id]=this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        if(!this.positioned){
            this.x=x;
            this.y=y;
            this.positioned=true;
        }

        g.drawImage(texture,x,y,TileWidth,TileHeight,null);
    }
public boolean isSolid(){
        return false;
}
    public int getId(){
        return id;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }
}

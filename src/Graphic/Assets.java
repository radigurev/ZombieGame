package Graphic;

import Entity.StaticEntities.DoorReverse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    private static final int width=80,height=80;
    public static Font font;
    public static BufferedImage c1,c2,c3,c4,tree,wall,floor,wallLeft,wallUp,bed,topleftcorner,toprightcorner,
            rightwall,wallDown,DownLeftCorner,DownRightCorner,Thing,BedsideTable,Chair,Flower,HospitalBed,HospitalBedReverse,
            BedsideTableReverse,Chair2,Chair3,Bench1,Bench2,Door;
    public static BufferedImage[] ZombieRight,ZombieLeft,playerLeft,playerRight,Button,AttackRight,playerUp,playerDown,ZombieUp,ZombieDown,playerIdle,AttackLeft,AttackDown;
    public static BufferedImage inventoryScreen, DoorReverse;
    public static void init() {
        //Sprite sheets
        font=FontLoader.loadFont(System.getProperty("user.dir")+"\\src\\Images\\ARCADECLASSIC.TTF",28);
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/MainChWalkLeft-Sheet.png"));
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/sheet.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/MainChWalkRight-Sheet.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/ZombiesWalkLeft-Sheet.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/ZombiesWalkRight-Sheet.png"));
        SpriteSheet Attackright= new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/AttackRight.png"));
        SpriteSheet playerup=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/walkUp.png"));
        SpriteSheet playerdown=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/walkDown.png"));
        SpriteSheet zombieUp=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/ZombieUp.png"));
        SpriteSheet zombieDown=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/ZombieDown.png"));
        SpriteSheet idle=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/idle.png"));
        SpriteSheet attackLeft=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/AttackLeft.png"));
        SpriteSheet attackDown=new SpriteSheet(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/AttackDown.png"));

        //basic picture
        inventoryScreen = (ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/inventoryScreen.png"));
        floor=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/FloorTile.png"));
        wall=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/WallTiles.png"));
        wallLeft=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/WallLeft.png"));
        wallUp=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Wall.png"));
        bed=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Bed.png"));
        topleftcorner=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/topleftcorner.png"));
        toprightcorner=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/TopRightCorner.png"));
        rightwall=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/WallRight.png"));
        wallDown=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/WallDown.png"));
        DownLeftCorner=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/DownLeftCorner.png"));
        Bench1=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/bench1.png"));
        Bench2=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Bench2.png"));
        DownRightCorner=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/LeftDown.png"));
        Thing=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/THING.png"));
        BedsideTable=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/BedsideTable.png"));
        BedsideTableReverse=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/BedsideTableReverse.png"));
        Chair=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Chair.png"));
        Chair2=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Taboretka.png"));
        Chair3=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Taboretka2.png"));
        Flower=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Flower.png"));
        HospitalBed=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/HospitalBed.png"));
        HospitalBedReverse=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/HospitalBedReverse.png"));
        Door=(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/Door.png"));
        DoorReverse =(ImageLoader.loadImage("/Users/rado/Documents/Java/Zombie game/ZombieGame/src/Images/DoorLeft.png"));
        //Left
        playerLeft=new BufferedImage[8];
        for (int i = 0; i < playerLeft.length; i++) {
            playerLeft[i]=sheet.crop(width*i, 0, width, height);
        }
        //Right
        playerRight=new BufferedImage[8];
        for (int i = 0; i < playerRight.length; i++) {
            playerRight[i]=sheet2.crop(width*i, 0, width, height);
        }
        //left zombie
        ZombieLeft=new BufferedImage[8];
        for (int i = 0; i < ZombieLeft.length; i++) {
            ZombieLeft[i]=sheet3.crop(width*i, 0, width, height);
        }
        //right zombie
        ZombieRight=new BufferedImage[8];
        for (int i = 0; i < ZombieRight.length; i++) {
            ZombieRight[i]=sheet4.crop(width*i, 0, width, height);
        }

        AttackRight=new BufferedImage[3];
        for (int i = 0; i < AttackRight.length; i++) {
            AttackRight[i]=Attackright.crop(width*i, 0, width, height);
        }

        AttackLeft=new BufferedImage[3];
        for (int i = 0; i < AttackLeft.length; i++) {
            AttackLeft[i]=attackLeft.crop(width*i, 0, width, height);
        }

        playerUp=new BufferedImage[8];
        for (int i = 0; i < playerUp.length; i++) {
            playerUp[i]=playerup.crop(width*i,0,width,height);
        }
        playerDown=new BufferedImage[7];
        for (int i = 0; i < playerDown.length; i++) {
            playerDown[i]=playerdown.crop(width*i,0,width,height);
        }

        ZombieUp=new BufferedImage[8];
        for (int i = 0; i < ZombieUp.length; i++) {
            ZombieUp[i]=zombieUp.crop(width*i,0,width,height);
        }
        ZombieDown=new BufferedImage[7];
        for (int i = 0; i < ZombieDown.length; i++) {
            ZombieDown[i]=zombieDown.crop(width*i,0,width,height);
        }
        playerIdle=new BufferedImage[2];
        for (int i = 0; i < playerIdle.length; i++) {
            playerIdle[i]=idle.crop(width*i,0,width,height);
        }

        AttackDown=new BufferedImage[7];
        for (int i = 0; i < AttackDown.length; i++) {
            AttackDown[i]=attackDown.crop(width*i,0,width,height);
        }

      /*  for (int i = 0; i < 2; i++) {
            if(i==0)
            playerRight[i]=sheet1.crop(0,0,width,height);
            else
                playerRight[i]=sheet1.crop(width*i,0,width,height);
        }

       */


        //BUTTON
        Button=new BufferedImage[2];
        Button[0]= sheet1.crop(32 * 6, 32 * 4, 32 * 2, 32);
        Button[1] = sheet1.crop(32 * 6, 32 * 5, 32 * 2, 32);









    }

}

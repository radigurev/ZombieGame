package World;

import Entity.EntityManager;
import Entity.Humans.Player;
import Entity.StaticEntities.*;
import Entity.Zombie.Zombie;
import Item.Item;
import Item.ItemManager;
import State.State;
import Tile.Tile;
import Utils.Utils;
import com.game.Handler;

import java.awt.*;
import java.util.Arrays;

public class World {
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    Zombie zombies[] = new Zombie[0];
    //Enteties
    private EntityManager entityManager;
    //Items
    private ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        //entityManager.addEntity(new Tree(handler,100,200));
        //entityManager.addEntity(new Tree(handler,100,300));
        //entityManager.addEntity(new Tree(handler,100,400));

        //Room 1 (spawn Room)
        entityManager.addEntity(new Bed(handler, 65, 60));
        entityManager.addEntity(new Thing(handler, 185, 30));
        entityManager.addEntity(new Chair(handler, 400, 55));
        entityManager.addEntity(new Flower(handler, 65, 155));
        entityManager.addEntity(new BedsideTable(handler, 65, 100));
        //Corridor
        //TOPCHAIRS
        entityManager.addEntity(new Chair(handler, 650, 55));
        entityManager.addEntity(new Chair(handler, 780, 55));
        entityManager.addEntity(new Chair(handler, 910, 55));
        entityManager.addEntity(new Chair(handler, 1040, 55));
        entityManager.addEntity(new Chair(handler, 1160, 55));
        entityManager.addEntity(new Chair(handler, 1540, 55));
        //DownRightChair
        entityManager.addEntity(new Chair(handler, 1350, 890));
        entityManager.addEntity(new Chair(handler, 1415, 890));
        entityManager.addEntity(new Flower(handler, 1790, 930));
        //DownCenterChairs
        entityManager.addEntity(new Chair3(handler, 1030, 965));
        entityManager.addEntity(new Chair3(handler, 1095, 965));
        entityManager.addEntity(new Chair3(handler, 830, 965));
        entityManager.addEntity(new Chair3(handler, 960, 965));
        entityManager.addEntity(new Chair3(handler, 895, 965));
        //Middle
        entityManager.addEntity(new HospitalBed(handler, 446, 320));
        entityManager.addEntity(new Chair3(handler, 585, 512));
        entityManager.addEntity(new Bench1(handler, 495, 578));
        entityManager.addEntity(new Bench2(handler, 1230, 330));
        entityManager.addEntity(new Bench2(handler, 1230, 670));
        //Doors
        entityManager.addEntity(new Door(handler, 1855, 900));
        entityManager.addEntity(new DoorReverse(handler, 0, 310));


        //Room 2 (top right room)
        entityManager.addEntity(new Bed(handler, 1475, 250));
        entityManager.addEntity(new Thing(handler, 1475, 185));
        entityManager.addEntity(new BedsideTableReverse(handler, 1805, 250));
        entityManager.addEntity(new Flower(handler, 1795, 40));

        //Big room Right
        //Leftside
        entityManager.addEntity(new HospitalBed(handler, 1410, 435));
        entityManager.addEntity(new HospitalBed(handler, 1410, 565));
        entityManager.addEntity(new HospitalBed(handler, 1410, 695));
        entityManager.addEntity(new BedsideTable(handler, 1415, 370));
        entityManager.addEntity(new BedsideTable(handler, 1415, 500));
        entityManager.addEntity(new BedsideTable(handler, 1415, 630));
        entityManager.addEntity(new Flower(handler, 1405, 730));
        //Rightside
        entityManager.addEntity(new HospitalBedReverse(handler, 1724, 435));
        entityManager.addEntity(new HospitalBedReverse(handler, 1724, 565));
        entityManager.addEntity(new HospitalBedReverse(handler, 1724, 695));
        entityManager.addEntity(new BedsideTableReverse(handler, 1800, 370));
        entityManager.addEntity(new BedsideTableReverse(handler, 1800, 500));
        entityManager.addEntity(new BedsideTableReverse(handler, 1800, 630));
        entityManager.addEntity(new Flower(handler, 1790, 730));
        entityManager.addEntity(new Chair(handler, 1610, 370));

        //Big room leftSide
        //Leftside
        entityManager.addEntity(new HospitalBed(handler, 65, 570));
        entityManager.addEntity(new Chair(handler, 200, 570));
        entityManager.addEntity(new HospitalBed(handler, 65, 698));
        entityManager.addEntity(new HospitalBed(handler, 65, 826));
        entityManager.addEntity(new BedsideTable(handler, 70, 505));
        entityManager.addEntity(new BedsideTable(handler, 70, 633));
        entityManager.addEntity(new BedsideTable(handler, 70, 761));

        //Rightside
        entityManager.addEntity(new HospitalBedReverse(handler, 385, 570));
        entityManager.addEntity(new HospitalBedReverse(handler, 385, 698));
        entityManager.addEntity(new Chair(handler, 325, 698));
        entityManager.addEntity(new HospitalBedReverse(handler, 385, 826));
        entityManager.addEntity(new BedsideTableReverse(handler, 456, 505));
        entityManager.addEntity(new BedsideTableReverse(handler, 456, 633));
        entityManager.addEntity(new BedsideTableReverse(handler, 456, 761));


        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        /////////////////////Zombie spawn
        for (int i = 0; i < 6; i++) {
            addZombie(700 + i * 64, 100 + i * 64);
            addZombie(700 + i * 64, 800);
        }
        for (int i = 0; i < 3; i++) {
            addZombie(200 + i * 64, 580);
        }
        for (int i = 0; i < 4; i++) {
            addZombie(1550, 450 + i * 64);
        }


        // addZombie(500,500);
        /////////////////////
    }

    public void tick() {
        if (handler.getKeyManager().pause) {
            return;
        }

        itemManager.tick();
        entityManager.tick();

        entityManager.getPlayer().attemptToHit(zombies);

        for (int i = zombies.length - 1; i >= 0; i--) {
            zombies[i].tick();
            zombies[i].lookFor(entityManager.getPlayer());

            if (!zombies[i].isAlive()) {
                handler.getWorld().getItemManager()
                        .addItem(Item.coinItem.createNew((int) zombies[i].getX() + zombies[i].getWidth() / 2 - 15
                                , (int) zombies[i].getY() + zombies[i].getHeight() / 2));
                removeZombie(i);
            }
        }
    }

    ////////////////////////////////////Zombies


    public void addZombie(float x, float y) {
        zombies = Arrays.copyOf(zombies, zombies.length + 1);

        zombies[zombies.length - 1] = new Zombie(handler, x, y);
    }

    public void removeZombie(int index) {
        for (int i = index; i < zombies.length - 1; i++) {
            zombies[i] = zombies[i + 1];
        }

        zombies = Arrays.copyOf(zombies, zombies.length - 1);
    }

    public void removeZombie() {
        removeZombie(zombies.length - 1);
    }
    ////////////////////////////////////

    public void render(Graphics g) {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TileWidth);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TileWidth + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TileHeight);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TileHeight + 1);

        for (int i = yStart; i < yEnd; i++) {
            for (int j = xStart; j < xEnd; j++) {
                getTile(j, i).render(g, (int) (j * Tile.TileWidth - handler.getGameCamera().getxOffset()),
                        (int) (i * Tile.TileHeight - handler.getGameCamera().getyOffset()));

            }
        }
        //items
        itemManager.render(g);
        //Entitiies
        entityManager.render(g);
        for (int i = 0; i < zombies.length; i++) {
            zombies[i].render(g);
        }

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.FloorTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.FloorTile;

        return t;
    }

    public Tile getTileByLocation(float locationX, float locationY) {
        return this.getTile((int) (locationX / width), (int) (locationY / height));
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }

    }
    //GETTERS && SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Zombie[] getZombies() {
        return zombies;
    }

    public void setZombies(Zombie[] zombies) {
        this.zombies = zombies;
    }
}

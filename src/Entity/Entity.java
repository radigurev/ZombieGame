package Entity;

import com.game.Handler;

import java.awt.*;

public abstract class Entity {
    public static final int DefaultHealth=10;
    protected Handler handler;
    protected int health,maxHealth;
    protected float x, y;
    protected int width, height;
    protected boolean active= true;
    public Rectangle bounds;

    private float healthBarWidth,healthBarHeight;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        health=DefaultHealth;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.healthBarWidth=this.width*1.25f;

        this.healthBarHeight=this.height*0.15f;

        bounds=new Rectangle(0,0,width,height);

        this.setHealth(DefaultHealth);
    }

    public abstract void tick();

    public void render(Graphics g){
        int healthBarX=(int)(this.x+this.width/2-this.healthBarWidth/2-handler.getGameCamera().getxOffset());
        int healthBarY=(int)(this.y-this.healthBarHeight-handler.getGameCamera().getyOffset());

        g.setColor(Color.RED);
        g.fillRect(healthBarX,healthBarY,(int)this.healthBarWidth,(int)this.healthBarHeight);

        g.setColor(Color.GREEN);
        g.fillRect(healthBarX,healthBarY,(int)((float)this.health/this.maxHealth*this.healthBarWidth),(int)this.healthBarHeight);
    }

    public abstract void die();

    public void hurt(int amt){
        health-=amt;
        if(health<=0){
            active=false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset,float yOffset){
        for(Entity e: handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset))){
                return true;
            }
        }
        return false;
    }

    public boolean isColliding(Entity entity){
        if(this.bounds.x+x+this.bounds.width>=entity.bounds.x+entity.x && entity.bounds.x+entity.x+entity.bounds.width>=this.bounds.x+x){
            if(this.bounds.y+y+this.bounds.height>=entity.bounds.y+entity.y && entity.bounds.y+entity.y+entity.bounds.height>=this.bounds.y+y){
                return true;
            }
        }

        return false;
    }

    public boolean isColliding(float x,float y,float width,float height){
        if(this.x+this.width>=x && x+width>=this.x){
            if(this.y+this.height>=y && y+height>=this.y){
                return true;
            }
        }

        return false;
    }

    public Rectangle getCollisionBounds(float xOffset,float yOffset){
    return new Rectangle((int)(x+bounds.x+xOffset),(int) (y+bounds.y+yOffset),bounds.width,bounds.height);
    }
//SETTERS && GETTERS

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

        this.maxHealth=health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;

    }
}

package Entity.Humans;
import Entity.Entity;
import Entity.Humans.Human;
import Entity.Zombie.Zombie;
import Graphic.Animation;
import Graphic.Assets;
import Item.Inventory;
import com.game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    private Rectangle ar = null;

    //Animation
    private Animation animationDown,animationUp,animationLeft,animationRight,aminationAttackRight,idle,animationAttackLeft,animationAttackDown;
    private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;

    private int attackDuration=0;
    //IVNENTORY
    private Inventory inventory;
    public Player(Handler handler,float x,float y) {
        super(handler,x, y,Human.DefaultWidth,Human.DefaultHeight);
        bounds.x=25;
        bounds.y=50;
        bounds.width=50;
        bounds.height=50;

        idle=new Animation(150,Assets.playerIdle);
        animationLeft=new Animation(150,Assets.playerLeft);
        animationRight=new Animation(150,Assets.playerRight);
        animationUp=new Animation(150,Assets.playerUp);
        animationDown=new Animation(150,Assets.playerDown);


        aminationAttackRight=new Animation(150, Assets.AttackRight);
        animationAttackLeft=new Animation(150,Assets.AttackLeft);
        animationAttackDown=new Animation(150, Assets.AttackDown);
        inventory=new Inventory(handler);
    }

    public void attemptToHit(Zombie zombie){

        if(ar==null){
            return;
        }
        if(zombie.isColliding(this.ar.x,this.ar.y,this.ar.width,this.ar.height)){
            zombie.takeDamage(this.damage);
        }
    }

    public void attemptToHit(Zombie zombies[]){
        for(int i=0;i<zombies.length;i++){
            attemptToHit(zombies[i]);
        }
    }

    @Override
    public void move(){
        super.move();

        if(this.ar!=null){
            this.ar.x+=this.xMove;
            this.ar.y+=this.yMove;
        }
    }

    public void tick() {
        //Animations

        idle.tick();
        animationRight.tick();
        animationLeft.tick();
        animationUp.tick();
        animationDown.tick();
        aminationAttackRight.tick();
        animationAttackLeft.tick();
        animationAttackDown.tick();
        //Movement
       getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();
        inventory.tick();
    }


        private void checkAttacks(){
            attackTimer += System.currentTimeMillis() - lastAttackTimer;
            lastAttackTimer = System.currentTimeMillis();
            if(attackTimer < attackCooldown)
                return;

            if(inventory.isActive()){
                return;
            }

            Rectangle cb = getCollisionBounds(0, 0);

            if(this.attackTimer<this.attackCooldown && ar!=null){
                ar=null;
            }

            ar=new Rectangle();

            int arSize = 32;
            ar.width = arSize;
            ar.height = arSize;

            if(handler.getKeyManager().aUp){
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
            }else if(handler.getKeyManager().aDown){
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;

            }else if(handler.getKeyManager().aLeft){
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            }else if(handler.getKeyManager().aRight){
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;

            }else{
                ar=null;
                return;
            }

            attackTimer = 0;

            for(Entity e : handler.getWorld().getEntityManager().getEntities()){
                if(e.equals(this))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(ar)){
                    e.hurt(1);
                    return;
                }
            }

        }

    public void die(){
        System.out.println("You lose");
    }


    public void render(Graphics g) {
        if(!this.isAlive()){
            return;
        }

        super.render(g);

        g.drawImage(getCurrentAnimationFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width, height, null);

       inventory.render(g);
        //Visible hitbox
/*
        g.setColor(Color.red);
	    g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);


 */



        /*if(ar!=null){
            g.setColor(Color.RED);
            g.fillRect((int)(ar.x-handler.getGameCamera().getxOffset()),(int)(ar.y-handler.getGameCamera().getyOffset()),ar.width,ar.height);
        }

         */
    }
    public void postRender(Graphics g){
        inventory.render(g);
    }


    private BufferedImage getCurrentAnimationFrame(){
        if(handler.getKeyManager().aRight){
           return aminationAttackRight.getCurrentFrame();
        }else if(handler.getKeyManager().aLeft){
            return animationAttackLeft.getCurrentFrame();
        }else if(handler.getKeyManager().aDown){
            return animationAttackDown.getCurrentFrame();
        }else if(handler.getKeyManager().aUp){
            return animationUp.getCurrentFrame();
        }else
        if(xMove>0){
            return animationRight.getCurrentFrame();
        }else if(xMove<0){
            return animationLeft.getCurrentFrame();
        }else if(yMove<0){
            return animationUp.getCurrentFrame();
        }else if(yMove>0){
            return animationDown.getCurrentFrame();
        }else
            return idle.getCurrentFrame();
    }
    //GETTERS && SETTERS
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}

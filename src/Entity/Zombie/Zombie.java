package Entity.Zombie;

import Entity.Humans.Human;
import Entity.Humans.Player;
import Graphic.Animation;
import Graphic.Assets;
import Tile.Tile;
import com.game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Zombie extends Human{
    private ObjectivePoint objectivePoints[]=new ObjectivePoint[0];
    private boolean isAggro=false;

    private int currentObjectivePoint=0;

    private Player target;

    private Animation animationLeft,animationRight,animationDown,animationUp;

    private float viewRange;

    private final float originalSpeed;

    private int attackCooldown,attackTimer;

    public Zombie(Handler handler,float x,float y){
        super(handler,x,y,Human.DefaultWidth,Human.DefaultHeight);
        this.bounds.width=80;
        this.bounds.height=80;

        this.viewRange=this.width*6;

        this.addObjectivePoint(this.x,this.y);

        this.speed*=0.4;

        this.originalSpeed=this.speed;

        this.attackCooldown=50;

        this.attackTimer=0;

        animationLeft=new Animation(500, Assets.ZombieLeft);
        animationRight=new Animation(500,Assets.ZombieRight);
        animationDown=new Animation(500,Assets.ZombieDown);
        animationUp=new Animation(500,Assets.ZombieUp);

        this.health=20;
        this.maxHealth=20;
        this.damage=2;
    }

    public void gotoLocation(float x,float y){
        float distX=x-this.x;
        float distY=y-this.y;

        float distance=this.distanceTo(x,y);

        this.move(distX/distance*this.speed,distY/distance*this.speed);
    }

    private void followObjectives(){
        ObjectivePoint currentObjective=this.objectivePoints[this.currentObjectivePoint];

        if(this.distanceTo(currentObjective.x,currentObjective.y)<=this.speed){
            this.x=currentObjective.x;
            this.y=currentObjective.y;

            if(this.objectivePoints[this.currentObjectivePoint].isReady()){
                this.updateObjective();
            }else{
                this.objectivePoints[this.currentObjectivePoint].update();
            }
        }else{
            this.gotoLocation(currentObjective.x,currentObjective.y);
            currentObjective.despawnUpdate();
            if(currentObjective.isDespawned()){
                currentObjective.x=this.x;
                currentObjective.y=this.y;
            }
        }
    }

    private void updateObjective(){
        float targetX,targetY;

        do{
            targetX=this.x+(float)Math.random()*(this.width*6)-this.width*3;
            targetY=this.y+(float)Math.random()*(this.height*6)-this.height*3;
        }while(handler.getWorld().getTileByLocation(targetX,targetY).isSolid());
        addObjectivePoint(targetX,targetY);


        removeObjectivePoint(this.objectivePoints.length-2);
    }

    public void lookFor(Player player){
        if(!player.isAlive()){
            this.isAggro=false;
            this.speed=this.originalSpeed;
            return;
        }
        float viewAreaX=this.x+this.width/2-this.viewRange/2,viewAreaY=this.y+this.height/2-this.viewRange/2;

        if(player.isColliding(viewAreaX,viewAreaY,viewRange,viewRange)){
            this.isAggro=true;
            this.target=player;
        }else{
            if(this.isAggro){//This code only happens in the first frame after the zombie is no longer aggro
                this.clearObjectivePoints();
                this.addObjectivePoint(this.x,this.y);//The zombie stops at one place and wait for a while after losing the human
            }

            this.isAggro=false;
            this.target=null;
        }
    }

    @Override
    public void tick(){
        animationLeft.tick();
        animationRight.tick();
        if(this.attackTimer<this.attackCooldown){
            this.attackTimer++;
        }

        if(!this.isAggro){
            this.followObjectives();
            this.speed=this.originalSpeed;
        }else{
            this.gotoLocation(target.getX(),target.getY());
            this.speed=this.originalSpeed*1.75f;

            if(this.isColliding(target)){
                if(this.attackTimer>=this.attackCooldown){
                    target.takeDamage(this.damage);
                    this.attackTimer=0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g){
        if(!this.isAlive()){
            return;
        }

        super.render(g);

        g.drawImage(getCurrentAnimationFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()), width, height, null);

        float cameraX=handler.getGameCamera().getxOffset();
        float cameraY=handler.getGameCamera().getyOffset();

/*
        g.setColor(Color.red);
	g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
			(int) (y + bounds.y - handler.getGameCamera().getyOffset()),
			bounds.width, bounds.height);
*/



      //  g.setColor(Color.GREEN);
        //g.fillRect((int)(x-cameraX),(int)(y-cameraY),width,height);


        if(this.isAggro){
            g.setColor(new Color(255,0,0,50));
        }else{
            g.setColor(new Color(0,255,0,25));
        }

        //g.fillRect((int)(this.x+this.width/2-this.viewRange/2-cameraX),(int)(this.y+this.height/2-this.viewRange/2-cameraY),(int)this.viewRange,(int)this.viewRange);

        /*
        g.setColor(Color.YELLOW);
        g.fillRect((int)(objectivePoints[currentObjectivePoint].x-cameraX),(int)(objectivePoints[currentObjectivePoint].y-cameraY),width/4,height/4);
        */
    }
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove>0){
            return animationRight.getCurrentFrame();
        }else if(xMove<0){
            return animationLeft.getCurrentFrame();
        }else if(yMove<0){
            return animationUp.getCurrentFrame();
        }else {
            return animationDown.getCurrentFrame();
        }
    }
    @Override
    public void die(){

    }

    public void addObjectivePoint(float x,float y){
        objectivePoints=Arrays.copyOf(objectivePoints,objectivePoints.length+1);

        objectivePoints[objectivePoints.length-1]=new ObjectivePoint(x,y,(int)(Math.random()*500),this);
    }

    public void removeObjectivePoint(int index){
        for(int i=index;i<objectivePoints.length-1;i++){
            objectivePoints[i]=objectivePoints[i+1];
        }

        objectivePoints=Arrays.copyOf(objectivePoints,objectivePoints.length-1);
    }

    public void removeObjectivePoint(){
        removeObjectivePoint(objectivePoints.length-1);
    }

    public void clearObjectivePoints(){
        this.objectivePoints=new ObjectivePoint[0];
    }

    public float distanceTo(float x,float y){
        return (float)Math.sqrt(Math.pow(x-this.x,2)+Math.pow(y-this.y,2));
    }
}

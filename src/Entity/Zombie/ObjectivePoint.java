package Entity.Zombie;

import Entity.Humans.Human;

public class ObjectivePoint{
    public float x,y;
    public int delay;

    private int timer=0;

    private int despawnTime=0,maxDespawnTime;

    public ObjectivePoint(float x,float y,int delay,Zombie zombie){
        this.x=x;
        this.y=y;

        this.delay=delay;

        this.maxDespawnTime=(int)(zombie.distanceTo(x,y)/zombie.getSpeed());
    }

    public void update(){
        if(this.timer<this.delay){
            this.timer++;
        }
    }

    public void despawnUpdate(){
        if(this.despawnTime<this.maxDespawnTime){
            this.despawnTime++;
        }
    }

    public boolean isReady(){
        return this.timer>=this.delay;
    }

    public boolean isDespawned(){
        return this.despawnTime>=this.maxDespawnTime;
    }
}

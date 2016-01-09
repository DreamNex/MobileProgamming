package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.Circle;
import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.Vector2D;

import java.util.LinkedList;

/**
 * Created by Nil on 6/12/2015.
 */
public class Scanner {
    private Vector2D location;
    private CollisionComponent colCmpt;
    private int health;
    private float duration;
    private float rateOfFire;
    private float MAX_RATE;

    //Constructor(s)
    public Scanner(){}
    public Scanner(Vector2D location, int health, float duration, float rateOfFire, float MAX_RATE){
        this.colCmpt = new Circle(location, 10.f);
        this.location = location;
        this.colCmpt.setOrigin(location);
        this.health = health;
        this.duration = duration;
        this.rateOfFire = 0;
        this.MAX_RATE = MAX_RATE;
    }

    //Mutator Function(s)
    public void setLocation(Vector2D location){this.location = location;}
    public void setHealth(int health){this.health = health;}
    public void setDuration(int damage){this.duration = duration;}
    public void setColCmpt(CollisionComponent colCmpt){this.colCmpt = colCmpt;}
    public void setRateOfFire(float rateOfFire){this.rateOfFire = rateOfFire;}
    public void setMaxRate(float MAX_RATE){this.MAX_RATE = MAX_RATE;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public int getHealth(){return this.health;}
    public float getDuration(){return this.duration;}
    public CollisionComponent getColCmpt(){return this.colCmpt;}
    public float getRateOfFire(){return this.rateOfFire;}
    public float getMaxRate(){return this.MAX_RATE;}

    public Vector2D Update(float dt, LinkedList<Enemy>targets){
        if (!(targets.isEmpty())){
            if (rateOfFire >= 0){
                rateOfFire -= dt;
            }else{
                rateOfFire = MAX_RATE;
                duration -= dt;
                return targets.getFirst().getLocation();
            }
        }
        Vector2D scanBullet = new Vector2D();
        scanBullet.setZero();
        duration -= dt;
        return scanBullet;
    }
}

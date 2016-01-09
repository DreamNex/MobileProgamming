package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.PhysicsComponent;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 6/12/2015.
 */
public class Environment {
    private Vector2D location;
    private CollisionComponent colCmpt;
    private float bounceMultiplier;
    private int damage;

    //Constructor(s)
    public Environment(){}
    public Environment(Vector2D location, float bounceMultiplier, int damage){
        this.location = location;
        this.bounceMultiplier = bounceMultiplier;
        this.damage = damage;
    }

    //Mutator Function(s)
    public void setLocation(Vector2D location){this.location = location;}
    public void setBounceMultiplier(float bounceMultiplier){this.bounceMultiplier = bounceMultiplier;}
    public void setDamage(int damage){this.damage = damage;}
    public void setColCmpt(CollisionComponent colCmpt){this.colCmpt = colCmpt;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public float getBounceMultiplier(){return this.bounceMultiplier;}
    public int getDamage(){return this.damage;}
    public CollisionComponent getColCmpt(){return this.colCmpt;}
}

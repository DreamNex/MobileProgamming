package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.Circle;
import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.PhysicsComponent;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 6/12/2015.
 */
public class Enemy {
    private Vector2D location;
    private int health;
    private int damage;
    private float speedMultiplier;
    private PhysicsComponent phyCompt;
    private CollisionComponent colCompt;


    //Constructor(s)
    public Enemy(){}
    public Enemy(Vector2D location, int health, int damage, float speedMultiplier){
        this.colCompt = new Circle(location, 10.0f);
        this.phyCompt = new PhysicsComponent(5.0f);
        this.phyCompt.setMAX_VELOCITY(1.f);
        this.location = location;
        this.health = health;
        this.damage = damage;
        this.speedMultiplier = speedMultiplier;
    }

    //Mutator Function(s)
    public void set(Vector2D location, int health, int damage, float speedMultiplier){
        this.colCompt = new Circle(location, 30.0f);
        this.phyCompt = new PhysicsComponent(5.0f);
        this.location = location;
        this.health = health;
        this.damage = damage;
        this.speedMultiplier = speedMultiplier;
    }
    public void setHealth(int health){this.health = health;}
    public void setLocation(Vector2D location){this.location = location;colCompt.Update(location);}
    public void setDamage(int damage){this.damage = damage;}
    public void setPhyCompt(PhysicsComponent phyCompt){this.phyCompt = phyCompt;}
    public void setColCompt(CollisionComponent colCompt){this.colCompt = colCompt;}
    public void setSpeedMultiplier(float m){this.speedMultiplier = m;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public int getHealth(){return this.health;}
    public int getDamage(){return this.damage;}
    public PhysicsComponent getPhyCompt(){return this.phyCompt;}
    public CollisionComponent getColCompt(){return this.colCompt;}

    //Update Function
    public void Update(float dt, Vector2D target){
        //Chase Player
        Vector2D path = new Vector2D(0,0);
        path = path.DeductRef(target, location);
        path.normalize();
        path = path.multiplyRef(path, speedMultiplier * dt);
        location.add(path);
        //phyCompt.applyForce(path);
        //location.add(path);
        //Updates Physical Component
        //location = phyCompt.Update(dt, location);

        //Updates Collision Component
        colCompt.Update(location);
    }
}

package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.Circle;
import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.PhysicsComponent;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 6/12/2015.
 */
public class Projectile {
    private Vector2D location;
    private Vector2D path;
    private int health;
    private int damage;
    private float speedMultiplier;
    private PhysicsComponent phyCompt;
    private CollisionComponent colCompt;


    //Constructor(s)
    public Projectile(){
        this.location = new Vector2D(0,0);
        this.colCompt = new Circle(location, 3.0f);
        this.phyCompt = new PhysicsComponent(3.0f);
        this.damage = 1;
        this.health = 1;
        this.speedMultiplier = 0.01f;
    }
    public Projectile(Vector2D location, Vector2D target, int health, int damage, float speedMultiplier){
        this.colCompt = new Circle(location, 20.0f);
        this.phyCompt = new PhysicsComponent(3.0f);
        this.location = location;
        this.health = health;
        this.damage = damage;
        this.speedMultiplier = speedMultiplier;

        path = new Vector2D(0,0);
        path = path.DeductRef(target, location);
        path.normalize();
        //phyCompt.applyForce(path, speedMultiplier);
    }

    //Mutator Function(s)
    public void set(Vector2D location, Vector2D target, int health, int damage, float speedMultiplier){
        this.colCompt = new Circle(location, 30.0f);
        this.phyCompt = new PhysicsComponent(3.0f);
        this.location = location;
        this.health = health;
        this.damage = damage;
        this.speedMultiplier = speedMultiplier;

        path = path.DeductRef(target, location);
        path.normalize();
        //phyCompt.applyForce(path, speedMultiplier);
    }
    public void setHealth(int health){this.health = health;}
    public void setLocation(Vector2D location){this.location = location;}
    public void setDamage(int damage){this.damage = damage;}
    public void setPhyCompt(PhysicsComponent phyCompt){this.phyCompt = phyCompt;}
    public void setColCompt(CollisionComponent colCompt){this.colCompt = colCompt;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public int getHealth(){return this.health;}
    public int getDamage(){return this.damage;}
    public PhysicsComponent getPhyCompt(){return this.phyCompt;}
    public CollisionComponent getColCompt(){return this.colCompt;}

    //Update Function
    public void Update(float dt){
        //Updates Physical Component
        //phyCompt.applyForce(path);
        //location = phyCompt.Update(dt,location);
        location.add(path);

        //Updates Collision Component
        colCompt.Update(location);
    }
}

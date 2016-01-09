package com.sidm.assignment1.Assets;
import com.sidm.assignment1.Components.Circle;
import com.sidm.assignment1.Components.Vector2D;

import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.PhysicsComponent;

/**
 * Created by Nil on 6/12/2015.
 */
public class Player {
    private Vector2D location;
    private int health;
    private int regenRate;
    private int MAX_HEALTH;
    private float regenDelay;
    private float MAX_REGENDELAY;
    private PhysicsComponent phyCompt;
    private CollisionComponent colCompt;


    //Constructor(s)
    public Player(){}
    public Player(Vector2D location, int health, int MAX_HEALTH, int regenRate, float regenDelay, float MaxRegenDelay){
        this.colCompt = new Circle(location, 80.0f);
        this.phyCompt = new PhysicsComponent(10.0f);
        this.location = location;
        this.health = health;
        this.MAX_HEALTH = MAX_HEALTH;
        this.regenRate = regenRate;
        this.regenDelay = regenDelay;
        this.MAX_REGENDELAY = MaxRegenDelay;
    }

    //Mutator Function(s)
    public void set(Vector2D location, int health,int MAX_HEALTH, int regenRate, float regenDelay, float MaxRegenDelay){
        this.colCompt = new Circle(location, 7.0f);
        this.phyCompt = new PhysicsComponent(10.0f);
        this.location = location;
        this.health = health;
        this.MAX_HEALTH = MAX_HEALTH;
        this.regenRate = regenRate;
        this.regenDelay = regenDelay;
        this.MAX_REGENDELAY = MaxRegenDelay;
    }
    public void setLocation(Vector2D location){this.location = location; colCompt.Update(location);}
    public void setHealth(int health){this.health = health;}
    public void setRegenRate(int regenRate){this.regenRate = regenRate;}
    public void setMaxHealth(int MAX_HEALTH){this.MAX_HEALTH = MAX_HEALTH;}
    public void setRegenDelay(float regenDelay){this.regenDelay = regenDelay;}
    public void setMaxRegenDelay(float MaxRegenDelay){this.MAX_REGENDELAY = MaxRegenDelay;}
    public void setPhyCompt(PhysicsComponent phyCompt){this.phyCompt = phyCompt;}
    public void setColCompt(CollisionComponent colCompt){this.colCompt = colCompt;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public int getHealth(){return this.health;}
    public int getRegenRate(){return this.regenRate;}
    public int getMaxHealth(){return this.MAX_HEALTH;}
    public float getRegenDelay(){return this.regenDelay;}
    public float getMaxRegenDelay(){return this.MAX_REGENDELAY;}
    public PhysicsComponent getPhyCompt(){return this.phyCompt;}
    public CollisionComponent getColCompt(){return this.colCompt;}


    //Update Function
    public void Update(float dt){
        //Updates Physical Component
        //location = phyCompt.Update(dt,location);

        //Updates Collision Component
        colCompt.Update(location);

        //Updates Health Regen
        if (health < MAX_HEALTH){//If Damaged
            if (regenDelay > 0)//If Have Regen Delay
                regenDelay -= dt;
            else{//If No Regen Delay
                health += regenRate;//Regen Health
                if (health >= MAX_HEALTH){//If Regen reached Max
                    regenDelay = MAX_REGENDELAY;
                    health = MAX_HEALTH;
                }
            }
        }
    }
}

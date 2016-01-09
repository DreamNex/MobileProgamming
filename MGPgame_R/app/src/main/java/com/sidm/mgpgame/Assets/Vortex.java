package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.Circle;
import com.sidm.assignment1.Components.CollisionComponent;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 6/12/2015.
 */
public class Vortex {
    private Vector2D location;
    private CollisionComponent colCmpt;
    private float forceMultiplier;
    private float duration;

    //Constructor(s)
    public Vortex(){}
    public Vortex(Vector2D location, float forceMultiplier, float duration){
        this.colCmpt = new Circle(location, 160.f);
        this.location = location;
        this.colCmpt.setOrigin(location);
        this.forceMultiplier = forceMultiplier;
        this.duration = duration;
    }

    //Mutator Function(s)
    public void setLocation(Vector2D location){this.location = location;}
    public void setForceMultiplier(float forceMultiplier){this.forceMultiplier = forceMultiplier;}
    public void setDuration(int duration){this.duration = duration;}
    public void setColCmpt(CollisionComponent colCmpt){this.colCmpt = colCmpt;}

    //Accessor Function(s)
    public Vector2D getLocation(){return this.location;}
    public float getForceMultiplier(){return this.forceMultiplier;}
    public float getDuration(){return this.duration;}
    public CollisionComponent getColCmpt(){return this.colCmpt;}

    public void Update(double dt){
        duration -= dt;
    }
}

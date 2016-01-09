package com.sidm.assignment1.Components;

/**
 * Created by Nil on 2/12/2015.
 */
public class CollisionComponent {
    protected Vector2D origin;
    protected float scale;
    protected int type;

    public CollisionComponent(){
        type = 0;
        origin.setZero();
        scale = 1.0f;
    }

    public CollisionComponent(Vector2D origin){
        this.origin = origin;
    }

    public Vector2D getPos(){return this.origin;}

    public void setOrigin(Vector2D origin){this.origin = origin;}

    public void Update(Vector2D location){
        this.origin = location;
    }
}

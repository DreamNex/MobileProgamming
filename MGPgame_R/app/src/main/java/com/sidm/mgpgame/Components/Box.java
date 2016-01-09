package com.sidm.assignment1.Components;

/**
 * Created by Nil on 28/11/2015.
 */
public final class Box extends CollisionComponent {
    //Origin is Min Point
    private Vector2D max;

    public Box(){
        max.setZero();
    }
    public Box(Vector2D o, float s, Vector2D max){
        super(o);
        this.max = max;
    }

    public Vector2D getMax(){return this.max;}
    public void setMax(Vector2D max){this.max = max;}
}

package com.sidm.assignment1.Components;

/**
 * Created by Nil on 28/11/2015.
 */
public final class Circle extends CollisionComponent {
    private float radius;

    public Circle(){
        this.radius = 1.0f;
        this.type = 1;
    }
    public Circle(Vector2D o, float radius){
        super(o);
        this.radius = radius;
        this.type = 1;
    }

    public float getRadius(){return this.radius;}
    public void setRadius(float radius){this.radius = radius;}
}

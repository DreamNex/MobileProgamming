package com.sidm.assignment1.Assets;

import com.sidm.assignment1.Components.LineSegment;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 6/12/2015.
 */
public class Wire {
    private Vector2D location;
    private LineSegment wireLine;
    private int damage;
    private int targetIndex;
    private float duration;
    private float slowMultiplier;

    //Constructor(s)
    public Wire(){}
    public Wire(Vector2D start, Vector2D end, int targetIndex, int damage, float slowMultiplier, float duration){
        this.targetIndex = targetIndex;
        this.wireLine = new LineSegment(start, end);
        this.location = start;
        wireLine.setOrigin(location);
        this.damage = damage;
        this.slowMultiplier = slowMultiplier;
        this.duration = duration;
    }

    //Mutator(s)
    public void setLocation(Vector2D location){this.location = location;}
    public void setWireLine(LineSegment lineSegment){this.wireLine = lineSegment;}
    public void setDamage(int damage){this.damage = damage;}
    public void setDuration(float dur){this.duration = dur;}
    public void setSlowMultiplier(float slowMultiplier){this.slowMultiplier = slowMultiplier;}
    public void setTargetIndex(int targetIndex){this.targetIndex = targetIndex;}

    //Accessor(s)
    public Vector2D getLocation(){return this.location;}
    public LineSegment getWireLine(){return this.wireLine;}
    public int getDamage(){return this.damage;}
    public float getDuration(){return this.duration;}
    public float getSlowMultiplier(){return this.slowMultiplier;}
    public int getTargetIndex(){return this.targetIndex;}

    public void Update(float dt, Vector2D target){
        wireLine.setEnd(target);
        duration -= dt;
    }
}

package com.sidm.assignment1.Components;

/**
 * Created by Nil on 5/12/2015.
 */

public final class LineSegment extends CollisionComponent {
    private Vector2D end;

    public LineSegment(){
    }

    public LineSegment(Vector2D o,Vector2D end){
        super(o);
        this.end = end;
    }

    public Vector2D getEnd() {return this.end;}
    public void setEnd(Vector2D direction){this.end = end;}

}

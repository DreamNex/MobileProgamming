package com.sidm.assignment1.Components;
import com.sidm.assignment1.Components.Vector2D;

/**
 * Created by Nil on 28/11/2015.
 */
public class CollisionHandler {
    public CollisionHandler(){

    }

    public boolean CircleToCircle(Circle c1, Circle c2){
        float dist = ((c1.getPos().x - c2.getPos().x) * (c1.getPos().x - c2.getPos().x))
                       + ((c1.getPos().y - c2.getPos().y) * (c1.getPos().y - c2.getPos().y));

        return (dist <= ((c1.getRadius() + c2.getRadius()) * (c1.getRadius() + c2.getRadius())));
    }

    public boolean CircleToLineSegment(Circle c1, LineSegment c2){
        Vector2D closestP = closestPoint(c2, c1);
        Vector2D distance = new Vector2D(0,0);
        distance = distance.DeductRef(c1.getPos(), closestP);
        float shortestDist = distance.length();
        return shortestDist <= c1.getRadius();
    }
    public Vector2D closestPoint(LineSegment lineSegment, Circle circle){
        Vector2D perpDistance; //Perpendicular distance
        Vector2D vSegment = new Vector2D(0,0);
        vSegment = vSegment.DeductRef(lineSegment.getEnd(), lineSegment.getPos());

        Vector2D Circ_Segment = new Vector2D(0,0);
        Circ_Segment = Circ_Segment.DeductRef(circle.getPos(), lineSegment.getPos());

        Vector2D vSegmentNorm = vSegment;
        vSegmentNorm.divide(vSegment.length());

        float projection = Circ_Segment.dot(vSegmentNorm);
        if (projection <= 0)
            return lineSegment.getPos();
        if (projection >= vSegment.length())
            return lineSegment.getEnd();

        Vector2D projectionVal = vSegmentNorm;
        projectionVal.multiply(projection);

        perpDistance = projectionVal;
        perpDistance.add(lineSegment.getPos());
        return perpDistance;
    }

    public boolean checkCollision(CollisionComponent c1, CollisionComponent c2){
        if (c1 instanceof Circle){
            if (c2 instanceof Circle){return CircleToCircle((Circle)c1, (Circle)c2);}
            else if (c2 instanceof LineSegment){return CircleToLineSegment((Circle) c1, (LineSegment) c2);}
            return false;
        }else if (c1 instanceof LineSegment){
            if (c2 instanceof Circle){return CircleToLineSegment((Circle)c2, (LineSegment)c1);}
            return false;
        }
        return false;
    }

}

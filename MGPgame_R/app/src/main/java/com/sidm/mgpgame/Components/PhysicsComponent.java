package com.sidm.assignment1.Components;


/**
 * Created by Nil on 30/11/2015.
 */
public class PhysicsComponent {
    private final float gravity = 10.f;
    private Vector2D velocity;
    private Vector2D acceleration;
    private Vector2D force;
    private float mass;
    private float kineticCoFriction;
    private float staticCoFriction;
    private boolean enableGravity;
    private boolean enableFriction;
    private boolean isMoving;
    private float MAX_VELOCITY;

    public PhysicsComponent(){}
    public PhysicsComponent(float mass){
        this.velocity = new Vector2D(0,0);
        this.acceleration = new Vector2D(0,0);
        this.force = new Vector2D(0,0);
        this.mass = mass;

        this.enableGravity = false;
        this.enableFriction = true;

        this.staticCoFriction = 0.60f;
        this.kineticCoFriction = 0.55f;
        this.isMoving = false;
    }

    //Mutator Functions
    public void setMass(float mass){this.mass = mass;}
    public void setEnableGravity(boolean enable){this.enableGravity = enable;}
    public void setEnableFriction(boolean enable){this.enableFriction = enable;}
    public void setMAX_VELOCITY(float max){this.MAX_VELOCITY = max;}


    //Accessor Functions
    public Vector2D getVelocity(){return this.velocity;}
    public Vector2D getAcceleration(){return this.acceleration;}
    public Vector2D getForce(){return this.force;}

    public void applyForce(Vector2D appliedForce){
        force.add(appliedForce);
    }

    public void applyForce(Vector2D direction, float scalarForce){
        Vector2D appliedForce = direction;

        appliedForce.multiply(scalarForce);
        force.add(appliedForce);
    }

    public Vector2D Update(float dt, Vector2D location){
        isMoving = true;
        if (isMoving){
            acceleration = acceleration.multiplyRef(force, 1/mass);
            velocity.add(acceleration);
            if (velocity.lengthSquared() >= MAX_VELOCITY * MAX_VELOCITY)
                velocity.deduct(acceleration);
            location.add(velocity);
        }
        return location;
    }

    public Vector2D UpdateFriction(Vector2D location){
        Vector2D gravityForce = new Vector2D(0, gravity);
        float netForce = force.length();
        if (netForce != 0.f){//There is force
            if (!isMoving){//Standing Still
                Vector2D budgingForce = new Vector2D(0, 0);
                budgingForce = budgingForce.multiplyRef(gravityForce, mass);
                budgingForce.multiply(staticCoFriction);

                if (netForce > budgingForce.length()){
                    isMoving = true;
                    Vector2D opposingForce= new Vector2D(0,0);
                    opposingForce = opposingForce.multiplyRef(force.normalized(), budgingForce.length());
                    force = force.DeductRef(force, opposingForce);
                }

            }else if (isMoving){//Moving
                Vector2D kineticForce = new Vector2D(0,0);
                kineticForce = kineticForce.multiplyRef(gravityForce, mass);
                kineticForce.multiply(kineticCoFriction);

                Vector2D opposingForce = new Vector2D(0,0);
                opposingForce = opposingForce.multiplyRef(velocity.normalized(), kineticForce.length());

                if (force.lengthSquared() < opposingForce.lengthSquared()) {
                    velocity.setZero();
                    acceleration.setZero();
                    force.setZero();
                    isMoving = false;
                    return location;
                }else
                    force = force.DeductRef(force, opposingForce);
            }
        }
        return location;
    }
}
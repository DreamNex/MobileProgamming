package com.sidm.assignment1.Components;

/**
 * Created by Nil on 27/11/2015.
 */
public class Vector2D {
    public float x, y;

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void Set(float a, float b){

        this.x = a;
        this.y = b;
    }

    public void setZero(){
        this.x = 0;
        this.y = 0;
    }

    public boolean isZero(){
        if (this.x == 0.0f && this.y == 0.0f)
            return true;
        else
            return false;
    }

    public void add(Vector2D toAdd){
        this.x += toAdd.x;
        this.y += toAdd.y;
    }

    public Vector2D addRef(Vector2D A, Vector2D B)
    {
        Vector2D ref = new Vector2D();
        ref.Set((this.x = A.x + B.x), (this.x = A.y + B.y));
        return ref;
    }

    public void deduct(Vector2D toDeduct){
        this.x -= toDeduct.x;
        this.y -= toDeduct.y;
    }

    public Vector2D DeductRef(Vector2D A, Vector2D B){
        Vector2D result = new Vector2D();
        result.Set(this.x = A.x - B.x, this.y = A.y - B.y);
        return result;
    }

    public void Negate(Vector2D tonegate){
        this.x = tonegate.x * -1;
        this.y = tonegate.y * -1;
    }

    public boolean compare(Vector2D toCompare){
        return ((this.x == toCompare.x) &&
                (this.y == toCompare.y));
    }

    public void assign(Vector2D toAssign){
        this.x = toAssign.x;
        this.y = toAssign.y;
    }

    public void multiply(float toMultiply){
        this.x *= toMultiply;
        this.y *= toMultiply;
    }

    public Vector2D multiplyRef(Vector2D v1, float toMultiply){
        Vector2D result = new Vector2D();
        result.Set(this.x = v1.x * toMultiply, this.y = v1.y * toMultiply);
        return result;
    }

    public void divide(float toDivide){
        this.x /= toDivide;
        this.y /= toDivide;
    }

    public Vector2D divideRef(Vector2D v1, float toDivide){
        Vector2D result = new Vector2D();
        result.Set(this.x = v1.x / toDivide ,this.y = v1.y / toDivide);
        return result;
    }

    public float lengthSquared(){
        return ((x * x) + (y * y));
    }

    public float length(){
        double sqrtThis = (double)lengthSquared();
        sqrtThis = Math.sqrt(sqrtThis);
        return (float)sqrtThis;
    }

    public float dot(Vector2D toDot){
        return (x * toDot.x + y * toDot.y);
    }

    public Vector2D cross(Vector2D toCross){
        Vector2D crossProduct = new Vector2D();
        return crossProduct;
    }

    public void normalize(){

        float d = length();
        x /= d;
        y /= d;
    }

    public Vector2D normalized(){
        Vector2D result = new Vector2D();
        float d = length();
        result.Set(x / d, y / d);
        return result;
    }
}

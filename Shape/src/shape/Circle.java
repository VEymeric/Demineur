/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;

/**
 *
 * @author gaetane
 */
public class Circle extends Shape{
    float radius;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        if(radius < 0){
            throw new java.lang.IllegalArgumentException(" Radius lower than 0");
        }
        else{
            this.radius = radius;  
        }
    }
   
}

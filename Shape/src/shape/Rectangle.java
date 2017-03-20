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
public class Rectangle extends Shape {
    float length;
    float width ;

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        if(length < 0){
            throw new java.lang.IllegalArgumentException(" Length lower than 0");
        }
        else{
            this.length = length;  
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWitdh(float width) {
        if(width < 0){
            throw new java.lang.IllegalArgumentException(" Width lower than 0");
        }
        else{
            this.width = width;  
        }
    }
}

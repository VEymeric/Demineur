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
public class Shape {

    Point center ;
    Color color ;
    
    public Shape(){
        Point point = new Point(0,0);
        this.center = point;
        this.color = Color.BLACK;
    }
    
    public Shape(Point point){
        this(point,Color.BLACK);
    }
    
    public Shape(Point point ,Color color){
        center = point;
        this.color = color;
    }
    
    public Shape(Color color){
        Point point = new Point(0,0);
        this.center = point;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
    
    public Point moveTo(float x,float y){
        center.setX(x);
        center.setY(y);
        return center;
    }
    
    public static void main(String[] args) {
        Shape shape = new Shape();
        shape.setColor(Color.BLUE);
        Point center = new Point(1,2);
        shape.setCenter(center);

        System.out.println(" Couleur : "+shape.getColor() +", Position en x : "+shape.getCenter().getX() + ", Position en y : "+ shape.getCenter().getY());

    }
    
}

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
public enum Color {
    BLUE("blue"),
    BLACK("black"),
    RED("red");
    
    private String colors;
    
    private String getColors(){
        return this.colors;
    }
    
    public static Color getColor(String color){
        if(color.equals(Color.BLACK.getColors())){
            return Color.BLACK;
        }
        if(color.equals(Color.BLUE.getColors())){
            return Color.BLUE;
        }
        return Color.RED;
    }
    
    private Color(String color){
        this.colors = color;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author gaetane
 */
public class Button extends JButton {
    public int x;
    public int y;
    public String text;
    public Dimension size;
    public Color color;

    public Button() {
        color = Color.BLACK;
    }

    public String getTextAH() {
        return text;
    }

    public void setTextAH(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Dimension getSizeAH() {
        return size;
    }

    public void setSizeAH(Dimension size) {
        this.size = size;
    }

    
    
    @Override
    public void paintComponent(Graphics g){ // ecrire sans utilisé le setText 
        super.paintComponent(g); // appel la fonction original
        g.setColor(color);
        g.drawString(text,size.width/2 , size.height/2 );
    }

}

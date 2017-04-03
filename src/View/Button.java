/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JButton;

/**
 *
 * @author gaetane
 */
public class Button extends JButton {

    public int x;
    public int y;
    public String text;
    public Image img;
    public Dimension size;
    public Color color;

    public Button() {
        color = Color.BLACK;
    }

    public String getString() {
        return text;
    }

    public void setString(String text) {
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
    public void paintComponent(Graphics g) { // ecrire sans utilisé le setText 
        FontMetrics metrics = g.getFontMetrics();
        int x = (this.getWidth() - metrics.stringWidth(this.getString())) / 2;
        int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        super.paintComponent(g); // appel la fonction original
        g.setColor(color);
        g.drawString(getString(), x, y);
    }
    
}

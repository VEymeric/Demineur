/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
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
    public Color color;

    public Button(String etat) {
        text = etat;
        color = Color.BLACK;
    }
    
    @Override
    public void paintComponent(Graphics g){ // ecrire sans utilisé le setText 
        super.paintComponent(g); // appel la fonction original
        g.setColor(color);
        g.drawString(text, x, y);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;

/**
 *
 * @author gaetane
 */
public class Button extends JButton{
    public int x;
    public int y;
    
    public Button(String etat){
        this.setText(etat);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;

/**
 *
 * @author gaetane
 */
public class Option {
    public JFrame pop = new JFrame(); // nouvelle fenetre
   
    public void montre(){
        pop.setVisible(true);
        pop.setTitle(" Choose your option ");
        pop.setSize(300,400);
    }
    
    
}

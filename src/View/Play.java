/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controleur.GameController;
import Modele.Matrice;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author gaetane
 */
public class Play implements Observer {
    public JFrame game = new JFrame(); // nouvelle fenetre
    private JPanel grid;
    private final GameController controleur;
    
    public Play(GameController controleur){
        this.controleur = controleur;
        System.out.println(" je m'affiche ");
        game.setTitle(" Let's go ! ");
        game.setSize(controleur.m.getWidth()*80,controleur.m.getHeight()*80);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        game.setLayout(new BorderLayout (5,5));     
        game.add(new JButton(" Nombre de mines restantes: "),BorderLayout.SOUTH);
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(),controleur) ,BorderLayout.CENTER);
        game.setVisible(true);
    }
      
    private JComponent withCase(int x,int y, GameController controleur){
        grid = new JPanel();
        grid.setLayout(new BorderLayout (5,5));
        grid.setLayout(new GridLayout(y, x, 5,5));
        for(int j = 0; j < y;j++){
            for(int i = 0;i < x ;i++){
                Button button = new Button( " # " );
                button.addActionListener( controleur );
                button.x = i;
                button.y = j;
                grid.add(button);
            }
        }
        return grid;
    }
    
    @Override
    public void update(Observable obs, Object arg) {
        if(obs instanceof Matrice){
            for( int j = 0; j < controleur.m.getHeight() ; j++){
                for(int i= 0; i< controleur.m.getWidth() ;i++){
                    showOrnot(i,j);
                }
            }
        }
    } 
    
    private void showOrnot(int i,int j){
        System.out.println(" x : " + i +" y : "+  j);
        Button button;
        if(controleur.m.gridInit[j][i].isShow() == true){
            button = getButton(i,j);
            button.setText(String.valueOf(controleur.m.gridInit[j][i].getEtat().getValue()));
        }
    }
    
    private Button getButton(int i,int j){
        return (Button) grid.getComponent(j*grid.getY()+i );
    }
    
}
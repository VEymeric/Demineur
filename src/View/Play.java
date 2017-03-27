/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controleur.GameController;
import Modele.CaseHide;
import Modele.CaseInit;
import Modele.Matrice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author gaetane
 */
public class Play implements Observer{
    public JFrame game = new JFrame(); // nouvelle fenetre
    private JPanel grid;
    private final JMenuBar gameMenu;
    private final GameController controleur;
    
    public Play(GameController controleur){
        this.controleur = controleur;
        game.setTitle(" Let's go ! ");
        game.setSize(controleur.m.getWidth()*80,controleur.m.getWidth()*80);
        game.setMinimumSize(new Dimension(controleur.m.getWidth()*70,controleur.m.getHeight()*70));
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        game.setLayout(new BorderLayout (5,5));     
        game.add(debugger(),BorderLayout.WEST); 
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(),controleur) ,BorderLayout.CENTER);
        gameMenu = new GameMenu(controleur);
        game.setJMenuBar(gameMenu);
        game.add(new JButton(" Nombre de mines restantes: "+ controleur.m.getCountMine()),BorderLayout.SOUTH);        
        game.setVisible(true);
    }  
    
    public void refreshGrid(){
        game.remove(grid);
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(),controleur) ,BorderLayout.CENTER);
        game.setVisible(true);
    }
    private JComponent debugger(){
        JButton actionDebug = new JButton("debug");
        actionDebug.addMouseListener( controleur );
        return actionDebug;
    }
      
    private JComponent withCase(int x,int y, GameController controleur){
        grid = new JPanel();
        grid.setLayout(new BorderLayout (5,5));
        grid.setLayout(new GridLayout(y, x, 5,5));
        for(int j = 0; j < y;j++){
            for(int i = 0;i < x ;i++){
                Button button = new Button(" # ");
                button.addMouseListener( controleur );
                button.x = i;
                button.y = j;
                button.setEnabled(true);
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
        Button button = getButton(i,j);
        if(!controleur.m.gridInit[j][i].isHide()){
            button.setText(String.valueOf(controleur.m.gridInit[j][i].getEtat()));
            if(controleur.m.gridInit[j][i].getEtat() == CaseInit.NUMBER ){               
                button.setText(String.valueOf(controleur.m.gridInit[j][i].getBombes()));
                button.setEnabled(false);                
                button.setForeground((Color) controleur.m.gridInit[j][i].getColor());
            }
            else if(controleur.m.gridInit[j][i].getCache() == CaseHide.FLAG || controleur.m.gridInit[j][i].getCache() == CaseHide.UNKNOW ) {
               button.setText(String.valueOf(controleur.m.gridInit[j][i].getCache().getString()));
            }
            else{
               button.setText(String.valueOf(controleur.m.gridInit[j][i].getEtat().getString()));   
               button.setEnabled(false);                
            }
        }
        else{
            button.setText(" # ");
        }
    }
    
    private Button getButton(int i,int j){
        return (Button) grid.getComponent(j*controleur.m.getWidth() +i );
    }
    
}

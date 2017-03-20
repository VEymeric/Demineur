/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

/**
 *
 * @author gaetane
 */
public class LetsGo {
    
    public Create all;
    public Rules mouv;
    private int i,j;
    public String action;
    
    public LetsGo(Create dim){
        all = dim;
    }
      
    private void affichage(){
        for( int x = 0; x < all.getI() ; x++){
            for( int y = 0; y< all.getJ() ; y++){
                System.out.print("#   ");
            }
            System.out.println("  ");
        }    
    }


    
    // crée une fonction pour : changer une case par rapport a ce que le joueur a marqué
    //                          pour afficher les case si ils ouvrent une case
    //                          défaite si c'est une croix
    
    
    
    
}

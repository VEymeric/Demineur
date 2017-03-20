/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.Scanner;

/**
 *
 * @author gaetane
 */
public class Sokoban {  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String action;
        Scanner sc = new Scanner(System.in);
        Plateau plateau = new Plateau(1);
        plateau.creationNiveau1();
        Regle go = new Regle(plateau);
        go.nouveau();
        sc.nextLine();
        while( go.victoire() != false){
            System.out.print(" Action : ");
            action = sc.nextLine();
            go.order(action);            
        }
    }
    
}

package minesweeper;

import Controleur.GameController;
import View.Play;
import java.util.Scanner;


public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GameController controler = new GameController(12,3,15);    
        
        Scanner sc = new Scanner(System.in);
        System.out.println("ceci est le main, allons y !");
        System.out.println("il ne passera plus rien  ici !");

    }

}

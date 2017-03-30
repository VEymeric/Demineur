package minesweeper;

import Controleur.GameController;
import java.util.Scanner;

public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("ceci est le main, allons y !");
        GameController controler = new GameController(10, 10, 15);
        Scanner sc = new Scanner(System.in);
        System.out.println("il ne passera plus rien  ici !");

    }

}

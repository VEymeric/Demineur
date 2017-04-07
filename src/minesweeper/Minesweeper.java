package minesweeper;

import Controleur.GameController;
import java.io.IOException;
import java.util.Scanner;

public class Minesweeper {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("ceci est le main, allons y !");
        GameController controler = new GameController(10, 10, 15);
        Scanner sc = new Scanner(System.in);
        System.out.println("il ne passera plus rien  ici !");

    }

}

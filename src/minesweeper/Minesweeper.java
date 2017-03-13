package minesweeper;

import Controleur.GameController;
import java.util.Scanner;


public class Minesweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String action;
        /*int width,height = 5;
        int percentage = 30;
        System.out.println("_______________MINESWEEPER_______________");
        System.out.print(" Width of grid : ");
        width = sc.nextInt();
        System.out.print(" Height of grid : ");
        height = sc.nextInt();
        System.out.print(" Difficulty : %");
        percentage = sc.nextInt();
<<<<<<< HEAD
        System.out.println("_________________LET'S GO___________________"); */
        /*Generate generate = new Generate(10, 10, 15);
=======
        System.out.println("_________________LET'S GO___________________"); 
        Generate generate = new Generate(10, 10, 15);
>>>>>>> a7793ce01af08d661a538a74f56386ff3c5211ee
        System.out.println("chat");
        generate.creatGrid();
        generate.affichage();
        LetsGo demineur = new LetsGo(generate);
        //demineur.hideTicTac();
        while (demineur.isEnd() != false) {
            action = sc.nextLine();
            demineur.order(action, false);
        }*/
        System.out.println("ceci est le main, allons y !");

        GameController controler = new GameController(5,5,15);
        //controler.affichage();
        controler.test();
        
        System.out.println("il ne passera plus rien  ici !");

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;

/**
 *
 * @author gaetane
 */
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
        System.out.println("_________________LET'S GO___________________"); */
        Generate generate = new Generate(10, 10, 15);
        System.out.println("chat");
        generate.creatGrid();
        generate.affichage();
        LetsGo demineur = new LetsGo(generate);
        //demineur.hideTicTac();
        while (demineur.isEnd() != false) {
            action = sc.nextLine();
            demineur.order(action, false);
        }
        
        

    }

}

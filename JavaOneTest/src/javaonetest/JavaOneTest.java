/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaonetest;

import java.util.Scanner;

/**
 *
 * @author gaetane
 */
public class JavaOneTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        float values=0 ;
        float result=0 ;
        String choice ;
        Scanner sc = new Scanner(System.in);
        String more = "O";
        while(!"N".equals(more)){
            System.out.println("Quel est la valeur a convertir ?");
            values = sc.nextFloat();
            sc.nextLine();
            System.out.println("En quoi voulez-vous convertir votre valeur ?");    
            choice= sc.nextLine();
            switch(choice){
                case "Degres":
                    result = ((values-32)*5)/9;
                    System.out.println(values+" F = "+ result + " °C");
                    break;
                case "Fahrenheit":
                    result = (9/5)*values+32;
                    System.out.println(values+"°C = "+ result + " F ");
                    break;
                default : 
                    System.out.println("Pas compris demerde toi :p ");
            }
            System.out.println(" Voulez vous en refaire une ? O/N");
            more = sc.nextLine();
        }
        Ville ville = new  Ville(); // constructeur par defaut
        Ville ville1 = new Ville("Lille","France",500); // constructeur avec paramettre
        Ville ville2 = new Ville("Tokyo","Japon",5000);
        
        ville2.setNomVille("Rio");
        ville2.setNomPays("Brésil");
        ville2.setNbreHabitants(600);
        System.out.println("Changement de ville : "+ ville2.getNomVille() + "situé au "+ ville2.getNomPays());
        ville.compareTwoCity(ville1,ville2);
    }
}

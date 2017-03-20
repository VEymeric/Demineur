/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.util.Scanner;

/**
 *
 * @author gaetane
 */
public class Rules {
    private String action;
    

    
    public Rules(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
     
    public String execution(){
        switch(action){
            case "uncloak":
                return reveal();
            case "flag":
                return flag();
            case "unknown" :
                return unknown();
            default :
                return("Order unknown / misunderstood");
        }
    }
    
    public String reveal(){    // fonction pour dévoiler la case
        return "val";       
    }
    
    public String flag(){
        return "!";
    }
    
    public String unknown(){
        return "?";
    }
    
   /*     public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }*/
        
    private void division(){
        String[] cr = getAction().split(":");
        String[] place = cr[1].split(";");
        setAction(cr[0]);
       // setI(Integer.parseInt(place[0]));
       // setJ(Integer.parseInt(place[1]));
    }
    public void action(){
        System.out.print(" What do you want to do : uncloack / flag /unknown : x;y ");
        Scanner sc = new Scanner(System.in);
        action = sc.nextLine();
        division();
        
    }
}

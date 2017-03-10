/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;

/**
 *
 * @author gaetane
 */
public class Matrice extends Observable {
    int i,j;
    Case[][] gridHide;
    Case[][] gridInit;

    public Matrice( int i, int j){
        this.i = i;
        this.j = j;
        generateInit();
    }    
  
    public Case getGridInitCase(int y, int x) {
        return gridInit[y][x];
    }

    public void setGridInitCase(Case c, int i,int j) {
        this.gridInit[j][i] =  c ;
    }
    
    public int getI() {
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
    }
        
    private void generateInit(){ // tableau de valeur initialisé
        for(int x = 0; x < getI(); x++){
            for(int y = 0 ; y < getJ() ; y++){
                gridInit[y][x] = Case.NUMBER;
            }
        }
    }    
    private void generatHide(){ // tableau caché initialisé
        for(int x = 0; x < getI(); x++){
            for(int y = 0 ; y < getJ() ; y++){
                gridHide[y][x] = Case.UNKNOW;
            }
        }        
    }
    
    
    private void initValues(){
        // je met une bombe (aleatoire ) -> majNumbers
        // Case.Number.getValueI() == 0 -> gridInit[][] = Case.ALONE;
    }
}

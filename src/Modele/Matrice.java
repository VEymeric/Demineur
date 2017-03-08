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
    
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(gridInit[y-1][x-1] == Case.NUMBER ) gridInit[y-1][x-1].setValueI(gridInit[y-1][x-1].getValueI()+1);
        }
        if (y>0) { // voisin haut
            if(gridInit[y-1][x] == Case.NUMBER ) gridInit[y-1][x].setValueI(gridInit[y-1][x].getValueI()+1);
        }
        if (x<getI() && y>0) { // voisin haut droit
            if(gridInit[y-1][x+1] == Case.NUMBER ) gridInit[y-1][x+1].setValueI(gridInit[y-1][x+1].getValueI()+1);
        }
        if(x>0){//voisin gauche
            if(gridInit[y][x-1] == Case.NUMBER ) gridInit[y][x-1].setValueI(gridInit[y][x-1].getValueI()+1);
        }
        if(x<getI()){//voisin droit
            if(gridInit[y][x+1] == Case.NUMBER ) gridInit[y][x+1].setValueI(gridInit[y][x+1].getValueI()+1);
        }
        if (x>0 && y<getJ()) { // voisin bas gauche
            if(gridInit[y+1][x-1] == Case.NUMBER ) gridInit[y+1][x-1].setValueI(gridInit[y+1][x-1].getValueI()+1);
        }
        if (y<getJ()) { // voisin bas
            if(gridInit[y+1][x] == Case.NUMBER ) gridInit[y+1][x].setValueI(gridInit[y+1][x].getValueI()+1);
        }
        if (x<getI() && y<getJ()) { // voisin haut droit
            if(gridInit[y-1][x+1] == Case.NUMBER ) gridInit[y-1][x+1].setValueI(gridInit[y-1][x+1].getValueI()+1);
        }
    }
    
    private void initValues(){
        // je met une bombe (aleatoire ) -> majNumbers
        // Case.Number.getValueI() == 0 -> gridInit[][] = Case.ALONE;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Case;

/**
 *
 * @author gaetane
 */
public class Do {
    Modele.Matrice m;
    
    public Do(int i, int j){
        m = new Modele.Matrice(i,j);
        m.
    }
    
    
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(m.getGridInitCase(y-1,x-1) == Case.NUMBER ) gridInit[y-1][x-1].setValueI(gridInit[y-1][x-1].getValueI()+1);
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
    
    
    
    
    
    
    
}

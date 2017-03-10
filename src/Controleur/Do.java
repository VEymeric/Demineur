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
    private int nbMine = 0;
    
    
    
    public Do(int i, int j, int percentMine){
        m = new Modele.Matrice(i,j);
        nbMine = (i * j) * percentMine/ 100;        
    }
    
    
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(m.getGridInitCase(y-1,x-1) == Case.NUMBER ) m.getGridInitCase(y-1, x-1).setValueI(m.getGridInitCase(y-1, x-1).getValueI()+1);
        }
        if (y>0) { // voisin haut
            if(m.getGridInitCase(y-1,x) == Case.NUMBER ) m.getGridInitCase(y-1, x).setValueI(m.getGridInitCase(y-1, x).getValueI()+1);
        }
        if (x<m.getI() && y>0) { // voisin haut droit
            if(m.getGridInitCase(y-1,x+1) == Case.NUMBER ) m.getGridInitCase(y-1, x+1).setValueI(m.getGridInitCase(y-1, x+1).getValueI()+1);
        }
        if(x>0){//voisin gauche
            if(m.getGridInitCase(y,x-1) == Case.NUMBER ) m.getGridInitCase(y, x-1).setValueI(m.getGridInitCase(y, x-1).getValueI()+1);
        }
        if(x<m.getI()){//voisin droit
            if(m.getGridInitCase(y,x+1) == Case.NUMBER ) m.getGridInitCase(y, x+1).setValueI(m.getGridInitCase(y, x+1).getValueI()+1);
        }
        if (x>0 && y<m.getJ()) { // voisin bas gauche
            if(m.getGridInitCase(y+1,x-1) == Case.NUMBER ) m.getGridInitCase(y+1, x-1).setValueI(m.getGridInitCase(y+1, x-1).getValueI()+1);
        }
        if (y<m.getJ()) { // voisin bas
            if(m.getGridInitCase(y+1,x) == Case.NUMBER ) m.getGridInitCase(y+1, x).setValueI(m.getGridInitCase(y+1, x).getValueI()+1);
        }
        if (x<m.getI() && y<m.getJ()) { // voisin haut droit
            if(m.getGridInitCase(y-1,x+1) == Case.NUMBER ) m.getGridInitCase(y-1, x+1).setValueI(m.getGridInitCase(y-1, x+1).getValueI()+1);
        }
    }
    
    private void generateMatrice(int x, int y){//Genère une matrice de jeu à partir du premier clic en x;y
       int mineUnused = nbMine;
       while(mineUnused>0){
            int randI = (int) (Math.random() * m.getI());
            int randJ = (int) (Math.random() * m.getJ());
            if (Case.NUMBER.equals(m.getGridInitCase(randJ, randI)) && (randI != x && randJ != y)) {
                m.setGridInitCase(Case.MINE, randJ, randI);
                //majNumbers(randI, randJ);
                mineUnused--;
            }
       }
    }
    
    
    
    
    
    
    
}

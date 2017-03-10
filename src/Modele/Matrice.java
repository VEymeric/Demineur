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
    int i=0,j=0,mine=0;
    Case[][] gridHide;
    Case[][] gridInit;

    public Matrice( int i, int j, int nbMine){
        this.i = i;
        this.j = j;
        generateMatrice(i,j,nbMine);
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
    
    private void generateMatrice(int i, int j, int nbMine){//Genère une matrice de jeu à partir du premier clic en x;y
       int mineUnused = nbMine;
       while(mineUnused>0){
            int randI = (int) (Math.random() * getI());
            int randJ = (int) (Math.random() * getJ());
            if (Case.NUMBER.equals(getGridInitCase(randJ, randI)) && (randI != i && randJ != j)) {
                setGridInitCase(Case.MINE, randJ, randI);
                majNumbers(randI, randJ);
                mineUnused--;
            }
       }
       for(int x=0; x<getI(); x++){
           for(int y=0; y<getJ(); y++){
               if(getGridInitCase(y, x).getValueI() == 0){
                   setGridInitCase(Case.ALONE, y,x);
               }
           }
       }
    }
    
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(getGridInitCase(y-1,x-1) == Case.NUMBER ) getGridInitCase(y-1, x-1).setValueI(getGridInitCase(y-1, x-1).getValueI()+1);
        }
        if (y>0) { // voisin haut
            if(getGridInitCase(y-1,x) == Case.NUMBER ) getGridInitCase(y-1, x).setValueI(getGridInitCase(y-1, x).getValueI()+1);
        }
        if (x<getI() && y>0) { // voisin haut droit
            if(getGridInitCase(y-1,x+1) == Case.NUMBER ) getGridInitCase(y-1, x+1).setValueI(getGridInitCase(y-1, x+1).getValueI()+1);
        }
        if(x>0){//voisin gauche
            if(getGridInitCase(y,x-1) == Case.NUMBER ) getGridInitCase(y, x-1).setValueI(getGridInitCase(y, x-1).getValueI()+1);
        }
        if(x<getI()){//voisin droit
            if(getGridInitCase(y,x+1) == Case.NUMBER ) getGridInitCase(y, x+1).setValueI(getGridInitCase(y, x+1).getValueI()+1);
        }
        if (x>0 && y<getJ()) { // voisin bas gauche
            if(getGridInitCase(y+1,x-1) == Case.NUMBER ) getGridInitCase(y+1, x-1).setValueI(getGridInitCase(y+1, x-1).getValueI()+1);
        }
        if (y<getJ()) { // voisin bas
            if(getGridInitCase(y+1,x) == Case.NUMBER ) getGridInitCase(y+1, x).setValueI(getGridInitCase(y+1, x).getValueI()+1);
        }
        if (x<getI() && y<getJ()) { // voisin haut droit
            if(getGridInitCase(y-1,x+1) == Case.NUMBER ) getGridInitCase(y-1, x+1).setValueI(getGridInitCase(y-1, x+1).getValueI()+1);
        }
    }
    
    /*private void mark(int i, int j, String mark) {
        switch (mark) {
            case "#":
                ticTac[i][j] = Case.HIDE;
                break;
            case "?":
                ticTac[i][j] = Case.UNKNOW;
                break;
            case "!":
                ticTac[i][j] = Case.FLAG;
                break;
            default : 
               help();
        }
        showTicTac();
    }*/
}

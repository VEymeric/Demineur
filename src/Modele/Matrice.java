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

    public void setGridInitCase(Case c, int j,int i) {
        this.gridInit[j][i] =  c ;
    }

    public Case getGridHideCase(int y,int x) {
        return gridHide[y][x];
    }

    public void setGridHideCase(Case c, int j,int i) {
        this.gridHide[j][i] = c;
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
    
    public void mark(int i, int j, String mark) {
        switch (mark) {
            case "#":
                setGridHideCase(Case.HIDE, j ,i);
                break;
            case "?":
                setGridHideCase(Case.UNKNOW, j ,i);
                break;
            case "!":
                setGridHideCase(Case.FLAG, j ,i);
                break;
            default : 
                System.out.println(" je sais pas quoi mettre ");
        }
    }

    private boolean replace(int i, int j) {
        if (getGridInitCase(i, j) == Case.MINE) {
            return false;
        }
        setGridHideCase(getGridInitCase(i, j),j ,i);
        if( getGridInitCase(i, j) == Case.ALONE){
            propagation(i,j);
        }
        showTicTac();
    }    
    
    private void propagation(int x, int y) {
        if (x + 1 <= getI() - 1) {
            if (checkOrNot(x + 1, y) == false) {
                if (getGridInitCase(y, x+1) == Case.ALONE) {
                    setGridHideCase(Case.ALONE,y,y+1);
                    propagation(x + 1, y);

                }
                if (getGridInitCase(y, x+1 ) == Case.NUMBER) {
                    setGridHideCase(getGridInitCase(y,x+1),y,x+1);
                }
            }
        }
        if (x + 1 <= getI() - 1 && y + 1 <= getJ() - 1) {
            if (checkOrNot(x + 1, y + 1) == false) {
                if (getGridInitCase(x + 1, y + 1) == Case.ALONE) {
                    setGridHideCase(Case.ALONE,y + 1,x + 1);
                    propagation(x + 1, y + 1);
                }
                if (getGridInitCase(x + 1, y + 1) == Case.NUMBER) {
                    setGridHideCase(getGridInitCase(y + 1, x + 1),y + 1,x + 1);
                }
            }
        }
        if (y + 1 <= getJ() - 1) {
            if (checkOrNot(x, y + 1) == false) {                               
                if (getGridInitCase(x, y + 1) == Case.ALONE) {
                    setGridHideCase(x][y + 1] = Case.ALONE;
                    propagation(x, y + 1);
                }
                if (getGridInitCase(x, y + 1) == Case.NUMBER) {
                    setGridHideCase(x][y + 1] = getGridInitCase(x, y + 1);
                }
            }
        }
        if (y - 1 >= 0) {
            if (checkOrNot(x, y - 1) == false) {
                if (getGridInitCase(x, y - 1) == Case.ALONE) {
                    setGridHideCase(x][y - 1] = Case.ALONE;
                    propagation(x, y - 1);
                }
                if (getGridInitCase(x, y - 1) == Case.NUMBER) {
                    setGridHideCase(x][y - 1] = getGridInitCase(x, y - 1);
                }
            }
        }
        if (x - 1 >= 0) {
            if (checkOrNot(x - 1, y) == false) {
                if (getGridInitCase(x - 1, y) == Case.ALONE) {
                    setGridHideCase(x - 1][y] = Case.ALONE;
                    propagation(x - 1, y);
                }
                if (getGridInitCase(x - 1, y) == Case.NUMBER) {
                    setGridHideCase(x - 1][y] = getGridInitCase(x - 1, y);
                }
            }
        }
        if (y - 1 >= 0 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y - 1) == false) {
                if (getGridInitCase(x - 1, y - 1) == Case.ALONE) {
                    setGridHideCase(x - 1][y - 1] = Case.ALONE;
                    propagation(x - 1, y - 1);
                }
                if (getGridInitCase(x - 1, y - 1) == Case.NUMBER) {
                    setGridHideCase(x - 1][y - 1] = getGridInitCase(x - 1, y - 1);
                }
            }
        }
        if (y + 1 <= minus.getJ() - 1 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y + 1) == false) {
                if (getGridInitCase(x - 1, y + 1) == Case.ALONE) {
                    setGridHideCase(x - 1][y + 1] = Case.ALONE;
                    propagation(x - 1, y + 1);
                }
                if (getGridInitCase(x - 1, y + 1) == Case.NUMBER) {
                    setGridHideCase(x - 1][y + 1] = getGridInitCase(x - 1, y + 1);
                }
            }
        }
        if (y - 1 >= 0 && x + 1 <= minus.getI() - 1) {
            if (checkOrNot(x + 1, y - 1) == false) {
                if (getGridInitCase(x + 1, y - 1) == Case.ALONE) {
                    setGridHideCase(x + 1][y - 1] = Case.ALONE;
                    propagation(x + 1, y - 1);
                }
                if (getGridInitCase(x + 1, y - 1) == Case.NUMBER) {
                    setGridHideCase(x + 1][y - 1] = getGridInitCase(x + 1, y - 1);
                }
            }
        }
    } 
    
    private boolean checkOrNot(int x, int y) {
        return getGridHideCase(y,x) != Case.HIDE;
    }
    
    
}

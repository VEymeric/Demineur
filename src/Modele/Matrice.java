package Modele;

import java.util.Observable;
import Controleur.GameController;
import java.util.concurrent.ThreadLocalRandom;

public class Matrice extends Observable{
    GameController g;
    int width=0,height=0,mine=0;
    public Case[][] gridInit;

    public Matrice(int width, int height, int nbMine){
        System.out.println("Vous générez une nouvelle partie");
        this.width = width;
        this.height = height;
        mine = nbMine;
        generateMatrice(0,0,this.mine);
    }    
  
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
        
    private void generateInit(){ // tableau de valeur initialisé
        Case[][] grid = new Case[getHeight()][getWidth()];
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0 ; y < getHeight() ; y++){
                grid[y][x] = new Case(x,y);
            }
        }
        gridInit = grid;
    }    
    
    //Genère une matrice de jeu à partir du premier clic en x;y
    private void generateMatrice(int xClic, int yClic, int nbMine){
       generateInit();
       int mineUnused = nbMine;
       System.out.println( mineUnused +" "+ getWidth() +" "+ getHeight() );
       while(mineUnused>0){
            int randI = ThreadLocalRandom.current().nextInt(0, getWidth());
            int randJ = ThreadLocalRandom.current().nextInt(0, getHeight());
            System.out.println( randJ +"  "+ randI);
            if ( gridInit[randJ][randI].getEtat() == CaseInit.NUMBER){   
                if(randI != xClic || randJ != yClic) {
                    gridInit[randJ][randI].setEtat(CaseInit.MINE);
                    majNumbers(randI,randJ);
                    mineUnused--;
                }
                else{
                    System.out.println("yolo");
                }
            }
            else{ 
                System.out.println(gridInit[randJ][randI].getEtat());
            }
       }
       for(int x=0; x<getWidth(); x++){
           for(int y=0; y<getHeight(); y++){
               if(gridInit[y][x].isNumber()){
                   if(gridInit[y][x].getBombes()  == 0){
                        gridInit[y][x].setEtat(CaseInit.ALONE);
                   }
               }
           }
       }
    }
    
    //fonction qui ajoute 1 à toutes les cases autour d'une bombe : efficace pour initiliser
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(gridInit[y-1][x-1].isNumber()) gridInit[y-1][x-1].setBombes(gridInit[y-1][x-1].getBombes() +1);
        } 
        if (y>0) { // voisin haut
            if(gridInit[y-1][x].getEtat() == CaseInit.NUMBER ) gridInit[y-1][x].setBombes(gridInit[y-1][x].getBombes() +1);
        }
        if (x < getWidth()-1 && y>0) { // voisin haut droit
            if(gridInit[y-1][x+1].getEtat() == CaseInit.NUMBER ) gridInit[y-1][x+1].setBombes(gridInit[y-1][x+1].getBombes() +1);
        }
        if(x>0){//voisin gauche
            if(gridInit[y][x-1].getEtat() == CaseInit.NUMBER ) gridInit[y][x-1].setBombes(gridInit[y][x-1].getBombes() +1);
        }
        if(x<getWidth()-1){//voisin droit
            if(gridInit[y][x+1].getEtat() == CaseInit.NUMBER ) gridInit[y][x+1].setBombes(gridInit[y][x+1].getBombes() +1);
        }
        if (x>0 && y<getHeight()-1) { // voisin bas gauche
            if(gridInit[y+1][x-1].getEtat() == CaseInit.NUMBER ) gridInit[y+1][x-1].setBombes(gridInit[y+1][x-1].getBombes() +1);
        }
        if (y<getHeight()-1) { // voisin bas
            if(gridInit[y+1][x].getEtat() == CaseInit.NUMBER ) gridInit[y+1][x].setBombes(gridInit[y+1][x].getBombes() +1);
        }
        if (x<getWidth()-1 && y<getHeight()-1) { // voisin bas droit
            if(gridInit[y+1][x+1].getEtat() == CaseInit.NUMBER ) gridInit[y+1][x+1].setBombes(gridInit[y+1][x+1].getBombes() +1);
        }    
    }
    
    /*public void mark(int i, int j, String mark) {
        switch (mark) {
            case "#":
                setGridHideCase(CaseHide.HIDE, j ,i);
                break;
            case "?":
                setGridHideCase(CaseHide.UNKNOW, j ,i);
                break;
            case "!":
                setGridHideCase(CaseHide.FLAG, j ,i);
                break;
            default : 
                System.out.println(" je sais pas quoi mettre ");
        }
    }/*

    public boolean replace(int i, int j) {
        if (gridInit[i, j) == CaseInit.MINE) {
            return false;
        }
        setGridHideCase(CaseHide.SHOW ,j ,i);
        if( gridInit[i, j) == CaseInit.ALONE){
            propagation(i,j);
        }
        return true;
    }    
    
    
    private void propagation(int x, int y) {
        /*if (x + 1 <= getWidth() - 1) {
            if (checkOrNot(x + 1, y) == false) {
                if (gridInit[y, x+1) == Case.ALONE) {
                    setGridHideCase(Case.ALONE,y,y+1);
                    propagation(x + 1, y);

                }
                if (gridInit[y, x+1 ) == Case.NUMBER) {
                    setGridHideCase(gridInit[y,x+1),y,x+1);
                }
            }
        }
        if (x + 1 <= getWidth() - 1 && y + 1 <= getHeight() - 1) {
            if (checkOrNot(x + 1, y + 1) == false) {
                if (gridInit[x + 1, y + 1) == Case.ALONE) {
                    setGridHideCase(Case.ALONE,y + 1,x + 1);
                    propagation(x + 1, y + 1);
                }
                if (gridInit[x + 1, y + 1) == Case.NUMBER) {
                    setGridHideCase(gridInit[y + 1, x + 1),y + 1,x + 1);
                }
            }
        }
        if (y + 1 <= getHeight() - 1) {
            if (checkOrNot(x, y + 1) == false) {                               
                if (gridInit[x, y + 1) == Case.ALONE) {
                    setGridHideCase(x][y + 1] = Case.ALONE;
                    propagation(x, y + 1);
                }
                if (gridInit[x, y + 1) == Case.NUMBER) {
                    setGridHideCase(x][y + 1] = gridInit[x, y + 1);
                }
            }
        }
        if (y - 1 >= 0) {
            if (checkOrNot(x, y - 1) == false) {
                if (gridInit[x, y - 1) == Case.ALONE) {
                    setGridHideCase(x][y - 1] = Case.ALONE;
                    propagation(x, y - 1);
                }
                if (gridInit[x, y - 1) == Case.NUMBER) {
                    setGridHideCase(x][y - 1] = gridInit[x, y - 1);
                }
            }
        }
        if (x - 1 >= 0) {
            if (checkOrNot(x - 1, y) == false) {
                if (gridInit[x - 1, y) == Case.ALONE) {
                    setGridHideCase(x - 1][y] = Case.ALONE;
                    propagation(x - 1, y);
                }
                if (gridInit[x - 1, y) == Case.NUMBER) {
                    setGridHideCase(x - 1][y] = gridInit[x - 1, y);
                }
            }
        }
        if (y - 1 >= 0 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y - 1) == false) {
                if (gridInit[x - 1, y - 1) == Case.ALONE) {
                    setGridHideCase(x - 1][y - 1] = Case.ALONE;
                    propagation(x - 1, y - 1);
                }
                if (gridInit[x - 1, y - 1) == Case.NUMBER) {
                    setGridHideCase(x - 1][y - 1] = gridInit[x - 1, y - 1);
                }
            }
        }
        if (y + 1 <= minus.getHeight() - 1 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y + 1) == false) {
                if (gridInit[x - 1, y + 1) == Case.ALONE) {
                    setGridHideCase(x - 1][y + 1] = Case.ALONE;
                    propagation(x - 1, y + 1);
                }
                if (gridInit[x - 1, y + 1) == Case.NUMBER) {
                    setGridHideCase(x - 1][y + 1] = gridInit[x - 1, y + 1);
                }
            }
        }
        if (y - 1 >= 0 && x + 1 <= minus.getWidth() - 1) {
            if (checkOrNot(x + 1, y - 1) == false) {
                if (gridInit[x + 1, y - 1) == Case.ALONE) {
                    setGridHideCase(x + 1][y - 1] = Case.ALONE;
                    propagation(x + 1, y - 1);
                }
                if (gridInit[x + 1, y - 1) == Case.NUMBER) {
                    setGridHideCase(x + 1][y - 1] = gridInit[x + 1, y - 1);
                }
            }
        }
    } */
    
    /*private boolean checkOrNot(int x, int y) {
        return getGridHideCase(y,x) != CaseHide.HIDE;
    }*/
    
    
}

package Modele;

import java.util.Observable;
import Controleur.GameController;
import java.util.concurrent.ThreadLocalRandom;

public class Matrice extends Observable{
    GameController g;
    int width=0,height=0,mine=0;
    Case[][] gridInit;

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
       while(mineUnused>0){
            int randI = ThreadLocalRandom.current().nextInt(0, getWidth());
            int randJ = ThreadLocalRandom.current().nextInt(0, getHeight());
            if ( gridInit[randJ][randI] == CaseInit.NUMBER){   
                if(randI != xClic && randJ != yClic) {
                    setGridInitCase(CaseInit.MINE, randJ, randI);
                    System.out.println( randJ +"  "+ randI);
                    majNumbers(1,1);
                    mineUnused--;
                }
            }
       }
       for(int x=0; x<getWidth(); x++){
           for(int y=0; y<getHeight(); y++){
               if(getGridInitCase(y, x) == CaseInit.NUMBER){
                   if(getGridInitCase(y, x).getValue() == 0){
                        setGridInitCase(CaseInit.ALONE, y,x);
                   }
               }
           }
       }
    }
    
    //fonction qui ajoute 1 à toutes les cases autour d'une bombe : efficace pour initiliser
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(getGridInitCase(y-1,x-1) == CaseInit.NUMBER ){
                CaseInit.NUMBER.setValue(getGridInitCase(y-1,x-1).getValue()+1);
                getGridInitCase(y-1, x-1).setValue(CaseInit.NUMBER.getValue());
            }
        }
        
        /*if (y>0) { // voisin haut
            if(getGridInitCase(y-1,x) == CaseInit.NUMBER ) getGridInitCase(y-1, x).setValue(getGridInitCase(y-1, x).getValue()+1);
        }
        if (x<getWidth() && y>0) { // voisin haut droit
            if(getGridInitCase(y-1,x+1) == CaseInit.NUMBER ) getGridInitCase(y-1, x+1).setValue(getGridInitCase(y-1, x+1).getValue()+1);
        }
        if(x>0){//voisin gauche
            if(getGridInitCase(y,x-1) == CaseInit.NUMBER ) getGridInitCase(y, x-1).setValue(getGridInitCase(y, x-1).getValue()+1);
        }
        if(x<getWidth()){//voisin droit
            if(getGridInitCase(y,x+1) == CaseInit.NUMBER ) getGridInitCase(y, x+1).setValue(getGridInitCase(y, x+1).getValue()+1);
        }
        if (x>0 && y<getHeight()) { // voisin bas gauche
            if(getGridInitCase(y+1,x-1) == CaseInit.NUMBER ) getGridInitCase(y+1, x-1).setValue(getGridInitCase(y+1, x-1).getValue()+1);
        }
        if (y<getHeight()) { // voisin bas
            if(getGridInitCase(y+1,x) == CaseInit.NUMBER ) getGridInitCase(y+1, x).setValue(getGridInitCase(y+1, x).getValue()+1);
        }
        if (x<getWidth() && y<getHeight()) { // voisin haut droit
            if(getGridInitCase(y-1,x+1) == CaseInit.NUMBER ) getGridInitCase(y-1, x+1).setValue(getGridInitCase(y-1, x+1).getValue()+1);
        }*/
    }
    
    public void mark(int i, int j, String mark) {
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
    }

    public boolean replace(int i, int j) {
        if (getGridInitCase(i, j) == CaseInit.MINE) {
            return false;
        }
        setGridHideCase(CaseHide.SHOW ,j ,i);
        if( getGridInitCase(i, j) == CaseInit.ALONE){
            propagation(i,j);
        }
        return true;
    }    
    
    
    private void propagation(int x, int y) {
        /*if (x + 1 <= getWidth() - 1) {
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
        if (x + 1 <= getWidth() - 1 && y + 1 <= getHeight() - 1) {
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
        if (y + 1 <= getHeight() - 1) {
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
        if (y + 1 <= minus.getHeight() - 1 && x - 1 >= 0) {
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
        if (y - 1 >= 0 && x + 1 <= minus.getWidth() - 1) {
            if (checkOrNot(x + 1, y - 1) == false) {
                if (getGridInitCase(x + 1, y - 1) == Case.ALONE) {
                    setGridHideCase(x + 1][y - 1] = Case.ALONE;
                    propagation(x + 1, y - 1);
                }
                if (getGridInitCase(x + 1, y - 1) == Case.NUMBER) {
                    setGridHideCase(x + 1][y - 1] = getGridInitCase(x + 1, y - 1);
                }
            }
        }*/
    } 
    
    private boolean checkOrNot(int x, int y) {
        return getGridHideCase(y,x) != CaseHide.HIDE;
    }
    
    
}

package Modele;

import java.util.Observable;

public class Matrice extends Observable {
    int width=0,height=0,mine=0;
    Case[][] gridHide;
    Case[][] gridInit;

    public Matrice(int width, int height, int nbMine){
        System.out.println("Vous générez une nouvelle partie");
        this.width = width;
        this.height = height;
        generateMatrice(width,height,nbMine);
    }    
  
    public Case getGridInitCase(int y, int x) {
        return gridInit[y][x];
    }

    public void setGridInitCase(Case c, int i,int j) {
        this.gridInit[j][i] =  c ;
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
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0 ; y < getHeight() ; y++){
                gridInit[y][x] = Case.NUMBER;
            }
        }
    }    
    private void generatHide(){ // tableau caché initialisé
        for(int x = 0; x < getWidth(); x++){
            for(int y = 0 ; y < getHeight() ; y++){
                gridHide[y][x] = Case.UNKNOW;
            }
        }        
    }
    
    //Genère une matrice de jeu à partir du premier clic en x;y
    private void generateMatrice(int xClic, int yClic, int nbMine){
       int mineUnused = nbMine;
       while(mineUnused>0){
            int randI = (int) (Math.random() * getWidth());
            int randJ = (int) (Math.random() * getHeight());
            if (Case.NUMBER.equals(gridInit[randJ][randI])){   //erreur ici
                if(randI != xClic && randJ != yClic) {
                    setGridInitCase(Case.MINE, randJ, randI);
                    majNumbers(randI, randJ);
                    mineUnused--;
                }
            }
       }
       for(int x=0; x<getWidth(); x++){
           for(int y=0; y<getHeight(); y++){
               if(getGridInitCase(y, x).getValueI() == 0){
                   setGridInitCase(Case.ALONE, y,x);
               }
           }
       }
    }
    
    //fonction qui ajoute 1 à toutes les cases autour d'une bombe : efficace pour initiliser
    private void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(getGridInitCase(y-1,x-1) == Case.NUMBER ) getGridInitCase(y-1, x-1).setValueI(getGridInitCase(y-1, x-1).getValueI()+1);
        }
        if (y>0) { // voisin haut
            if(getGridInitCase(y-1,x) == Case.NUMBER ) getGridInitCase(y-1, x).setValueI(getGridInitCase(y-1, x).getValueI()+1);
        }
        if (x<getWidth() && y>0) { // voisin haut droit
            if(getGridInitCase(y-1,x+1) == Case.NUMBER ) getGridInitCase(y-1, x+1).setValueI(getGridInitCase(y-1, x+1).getValueI()+1);
        }
        if(x>0){//voisin gauche
            if(getGridInitCase(y,x-1) == Case.NUMBER ) getGridInitCase(y, x-1).setValueI(getGridInitCase(y, x-1).getValueI()+1);
        }
        if(x<getWidth()){//voisin droit
            if(getGridInitCase(y,x+1) == Case.NUMBER ) getGridInitCase(y, x+1).setValueI(getGridInitCase(y, x+1).getValueI()+1);
        }
        if (x>0 && y<getHeight()) { // voisin bas gauche
            if(getGridInitCase(y+1,x-1) == Case.NUMBER ) getGridInitCase(y+1, x-1).setValueI(getGridInitCase(y+1, x-1).getValueI()+1);
        }
        if (y<getHeight()) { // voisin bas
            if(getGridInitCase(y+1,x) == Case.NUMBER ) getGridInitCase(y+1, x).setValueI(getGridInitCase(y+1, x).getValueI()+1);
        }
        if (x<getWidth() && y<getHeight()) { // voisin haut droit
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

package Modele;

import java.util.Observable;
import Controleur.GameController;
import java.util.concurrent.ThreadLocalRandom;

public class Matrice extends Observable{
    GameController g;
    int width=0,height=0,mine=0;
    boolean inGame = true;
    int countCase, countMine = 0;
    public Case[][] gridInit;

    public Matrice(int width, int height, int nbMine){
        System.out.println("Vous générez une nouvelle partie");
        this.width = width;
        this.height = height;
        this.mine = nbMine;
        this.countCase = this.width*this.height - this.mine;
        generateInit();
    }    
  
    public void update(){ // Observer 
        this.setChanged();
        this.notifyObservers();
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
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
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

    public int getCountCase() {
        return countCase;
    }

    public void setCountCase(int countCase) {
        this.countCase = countCase;
    }

    public int getCountMine() {
        return countMine;
    }

    public void setCountMine(int countMine) {
        this.countMine = countMine;
    }
    
    //Genère une matrice de jeu à partir du premier clic en x;y
    public void generateMatrice(int xClic, int yClic){
       System.out.println("Votre matrice viens de se générer.");
       this.countMine = this.mine;
       int mineUnused = this.mine;
       while(mineUnused>0){
            int randI = ThreadLocalRandom.current().nextInt(0, getWidth());
            int randJ = ThreadLocalRandom.current().nextInt(0, getHeight());
            if ( gridInit[randJ][randI].getEtat() == CaseInit.NUMBER){   
                if(randI != xClic || randJ != yClic) {
                    gridInit[randJ][randI].setEtat(CaseInit.MINE);
                    majNumbers(randI,randJ);
                    mineUnused--;
                }
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
       for(int j= 0; j<getHeight();j++ ){
           for(int i = 0; i < getWidth();i++){
               if( gridInit[j][i].getEtat() == CaseInit.NUMBER){
                System.out.print( gridInit[j][i].getEtat().getValue());
               }else{
                 System.out.print( gridInit[j][i].getEtat().getString());
               }
           }
           System.out.println(" ");
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
    
    public void mark(int i, int j, String mark) {
        switch (mark) {
            case "#":
                gridInit[j][i].setCache(CaseHide.HIDE);
                break;
            case "?":
                gridInit[j][i].setCache(CaseHide.UNKNOW);
                break;
            case "!":
                gridInit[j][i].setCache(CaseHide.FLAG);
                break;
            default : 
                System.out.println(" je sais pas quoi mettre ");
        }
    }

    public void reveal(int x, int y){
        if(!this.isInGame() || x < 0 || y < 0 || x >= this.width || y >= this.height || this.gridInit[y][x].isShow()){ // cas non nécessaire
            return;
        }
        this.gridInit[y][x].setCache(CaseHide.SHOW);
        //(on pourrait retirer une case du compteur des cases sans bombe ici)
        if(this.gridInit[y][x].isMine()){
            this.setInGame(false); // -> nullos qui perd
            this.setCountMine(this.getCountMine()-1);
            //gameOver();
        }else{
            this.setCountCase(this.getCountCase()-1);
            if(this.getCountCase() == 0){
                this.setInGame(false); // champion qui gagne
            } 

            if(this.gridInit[y][x].isAlone()){
                reveal(x-1, y-1);
                reveal(x-1, y);
                reveal(x-1, y+1);
                reveal(x, y-1);
                reveal(x, y+1);
                reveal(x+1, y-1);
                reveal(x+1, y);
                reveal(x+1, y+1);
            }
        }
    }
    
}

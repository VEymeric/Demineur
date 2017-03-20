/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author gaetane
 */
public class Regle {
    private Plateau init;
    private int i,j;
    public Case[][] plateau;
    
    
    public Regle(Plateau plateau){
        this.init = plateau;
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
    

    public Case getPlateau(int i, int j) {
        return plateau[i][j];
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }
    
    public boolean victoire(){
        return false;
    }
    
    public void nouveau(){
        Case[][] wireRackS = new Case[init.getI()][init.getJ()];
        for (int x = 0; x < init.getI() ; x++) {
            for (int y = 0; y < init.getJ(); y++) {
                wireRackS[x][y] = init.getPlateau(x, y);
            }
        }
        setPlateau(wireRackS);
        affichage();
    }
    
    private void positionP(){
        int x=0,y=0;
        while( plateau[x][y] != Case.GARDIEN){
            x++;
            y++;        
        }
        setI(x);
        setJ(y);
    }
    
    public void affichage(){
        for( int x = 0; x < init.getI() ; x++){
            for( int y = 0; y< init.getJ(); y++){
                System.out.print( plateau[x][y].getValueS());
            }
            System.out.println("  ");
        }    
    }
    
    public void order(String go){
        switch(go){
            case "z":
                haut(getI(),getJ());
                break;
            case "s":
                bas(getI(),getJ());
                break;
            case "q":
                gauche(getI(),getJ());
                break;
            case "d":
                droite(getI(),getJ());
                break;
            case "a":
                recommence();
            default :
                System.out.println(" commande incomprise");
        }             
    }
    
    public void recommence(){
        init.creationNiveau1();
        nouveau();
    }
    
    public boolean stop(int i, int j){
        return plateau[i][j] != Case.MUR;
    }
    
    public boolean mouvementP(int i,int j){
        if( stop(i,j) == true){
            if( plateau[i][j]== Case.CIBLE){
                plateau[i][j] = Case.SUR;
            }
            else{
                plateau[i][j] = Case.GARDIEN;         
            }
            return true;
        }
        return false;
    }
    
    public boolean mouvementC(int i, int j){
        if( stop(i,j) == true && plateau[i][j]!=Case.CAISSE){
            if( plateau[i][j]== Case.CIBLE){
                plateau[i][j] = Case.END;
            }
            else{
                plateau[i][j] = Case.CAISSE;         
            }
            return true;
        }
        return false;        
    }
    
    private void haut(int i,int j){
        if(plateau[i][j-1] == Case.CAISSE ){
            if(mouvementP(i,j-1) == true || mouvementC(i,j-2)== true){
                plateau[i][j] = init.getPlateau(i,j);
            }            
        }
        else if(stop(i,j-1) == true){
            plateau[i][j] = Case.GARDIEN;
            plateau[i][j] = init.getPlateau(i,j);            
        }
        affichage();
    }
    
    private void bas(int i, int j){
        if(plateau[i][j+1] == Case.CAISSE ){
            if(mouvementP(i,j+1) == true || mouvementC(i,j+2)== true){
                plateau[i][j] = init.getPlateau(i,j);
            }            
        }
        else if(stop(i,j+1) == true){
            plateau[i][j] = Case.GARDIEN;
            plateau[i][j] = init.getPlateau(i,j);            
        }
        affichage();
    }
    
    private void gauche(int i, int j){
        if(plateau[i-1][j] == Case.CAISSE ){
            if(mouvementP(i-1,j) == true || mouvementC(i,j)== true){
                plateau[i][j] = init.getPlateau(i,j);
            }            
        }
        else if(stop(i-1,j) == true){
            plateau[i][j] = Case.GARDIEN;
            plateau[i][j] = init.getPlateau(i,j);            
        }
        affichage();
    }   
    
    private void droite(int i, int j){
        if(plateau[i+1][j] == Case.CAISSE ){
            if(mouvementP(i+1,j) == true || mouvementC(i+2,j)== true){
                plateau[i][j] = init.getPlateau(i,j);
            }            
        }
        else if(stop(i+1,j) == true){
            plateau[i][j] = Case.GARDIEN;
            plateau[i][j] = init.getPlateau(i,j);            
        } 
        affichage();
    }   
}

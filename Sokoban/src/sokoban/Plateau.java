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
public class Plateau {
    private int niveau;
    private int i,j;
    Case[][] plateau;
    
    public Plateau(int x){
        niveau = x ;
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

    public Case[][] get(){
        return plateau;
    }
    
    public Case getPlateau(int i,int j) {
        return plateau[i][j];
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }
    
    // creation du plateau
    public void creationNiveau1(){
        setI(11);
        setJ(19);
        defautPlateau();
z
        
        plateau[0][4] = Case.MUR;
        plateau[0][5] = Case.MUR;        
        plateau[0][6] = Case.MUR;
        plateau[0][7] = Case.MUR;        
        plateau[0][8] = Case.MUR;
        
        
        plateau[1][4] = Case.MUR;
        plateau[1][8] = Case.MUR;
        
        plateau[2][4] = Case.MUR;
        plateau[2][5] = Case.CAISSE;
        plateau[2][8] = Case.MUR;
        
        plateau[3][7] = Case.CAISSE;
        plateau[3][2] = Case.MUR;
        plateau[3][3] = Case.MUR;        
        plateau[3][4] = Case.MUR;
        plateau[3][8] = Case.MUR;        
        plateau[3][9] = Case.MUR;

        plateau[4][2] = Case.MUR;        
        plateau[4][9] = Case.MUR;        
        plateau[4][5] = Case.CAISSE;
        plateau[4][7] = Case.CAISSE;

        plateau[5][0] = Case.MUR;
        plateau[5][1] = Case.MUR;        
        plateau[5][2] = Case.MUR;
        plateau[5][4] = Case.MUR;        
        plateau[5][6] = Case.MUR;        
        plateau[5][7] = Case.MUR;
        plateau[5][9] = Case.MUR;        
        plateau[5][13] = Case.MUR;
        plateau[5][14] = Case.MUR;        
        plateau[5][15] = Case.MUR;         
        plateau[5][16] = Case.MUR;
        plateau[5][17] = Case.MUR;        
        plateau[5][18] = Case.MUR;        

        plateau[6][0] = Case.MUR;
        plateau[6][4] = Case.MUR;        
        plateau[6][6] = Case.MUR;
        plateau[6][7] = Case.MUR;        
        plateau[6][9] = Case.MUR;        
        plateau[6][10] = Case.MUR;
        plateau[6][11] = Case.MUR;        
        plateau[6][12] = Case.MUR;
        plateau[6][13] = Case.MUR;
        plateau[6][18] = Case.MUR;        
        plateau[6][17] = Case.CIBLE;
        plateau[6][16] = Case.CIBLE;
        
        plateau[7][0] = Case.MUR;
        plateau[7][18] = Case.MUR;        
        plateau[7][17] = Case.CIBLE;
        plateau[7][16] = Case.CIBLE;       
        plateau[7][2] = Case.CAISSE;
        plateau[7][5] = Case.CAISSE;
        
        plateau[8][17] = Case.CIBLE;
        plateau[8][16] = Case.CIBLE;
        plateau[8][11] = Case.GARDIEN;
        plateau[8][0] = Case.MUR;
        plateau[8][1] = Case.MUR;        
        plateau[8][2] = Case.MUR;
        plateau[8][3] = Case.MUR;        
        plateau[8][4] = Case.MUR;        
        plateau[8][6] = Case.MUR;
        plateau[8][7] = Case.MUR;        
        plateau[8][8] = Case.MUR;
        plateau[8][10] = Case.MUR;
        plateau[8][12] = Case.MUR;
        plateau[8][13] = Case.MUR;
        plateau[8][14] = Case.MUR;
        plateau[8][18] = Case.MUR;
        
        plateau[9][4] = Case.MUR;
        plateau[9][10] = Case.MUR;        
        plateau[9][11] = Case.MUR;
        plateau[9][12] = Case.MUR;        
        plateau[9][15] = Case.MUR;        
        plateau[9][16] = Case.MUR;
        plateau[9][17] = Case.MUR;        
        plateau[9][10] = Case.MUR;
        plateau[9][12] = Case.MUR;
        plateau[9][13] = Case.MUR;
        plateau[9][14] = Case.MUR;
        plateau[9][18] = Case.MUR;
        
        plateau[10][4] = Case.MUR;
        plateau[10][5] = Case.MUR;        
        plateau[10][6] = Case.MUR;
        plateau[10][7] = Case.MUR;        
        plateau[10][8] = Case.MUR;
        plateau[10][9] = Case.MUR;
        plateau[10][10] = Case.MUR;        
        plateau[10][11] = Case.MUR;
    }
    
    private void defautPlateau(){
        Case[][] wireRackS = new Case[getI()][getJ()];
        for (Case[] wireRackS1 : wireRackS) {
            for (int y = 0; y < wireRackS1.length; y++) {
                wireRackS1[y] = Case.RIEN;
            }
        }
        setPlateau(wireRackS); 
    } 
}

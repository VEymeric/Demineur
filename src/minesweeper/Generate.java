/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import Modele.Case;

/**
 *
 * @author gaetane
 */
public class Generate {

    private Case[][] grid;  // tableau
    private int i, j, percentage, numberOfMine; // dimention

    public Generate() {
        this(5, 5, 25);
    }

    public Generate(int i, int j, int percentage) {
        this.i = i;
        this.j = j;
        this.percentage = percentage;
    } // constructeur

    private Generate(int i, int j, String percentage) {
        String[] cr = percentage.split("%");
        this.percentage = Integer.parseInt(cr[0]);
        this.i = i;
        this.j = j;
    } // constructeur

    private Generate(int i, int percentage) {
        this(i, i, percentage);
    } // constructeur

    private Generate(int i, String percentage) {
        this(i, i, percentage);
    } // constructeur

    public Case getCase(int i, int j) {
        return grid[i][j];
    }

    public void setGrid(Case[][] grid) {
        this.grid = grid;
    }

    public int getI() {
        return i;
    }

    
    public void setI(int i) {
        if (i <= 0) {
            throw new java.lang.IllegalArgumentException(" Row too low ");
        } else {
            this.i = i;
        }
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        if (j <= 0) {
            throw new java.lang.IllegalArgumentException(" Line too low ");
        } else {
            this.j = j;
        }
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        if (percentage < 0 || percentage > 85) {
            throw new java.lang.IllegalArgumentException(" Percentage is not consistent ");
        } else {
            this.percentage = percentage;
        }
    }

    public int getNumberOfMine() {
        return numberOfMine;
    }

    public void setNumberOfMine(int percentage) {
        this.numberOfMine = (getI() * getJ()) * percentage / 100;
    }

    private void defaultGrid() {
        Case[][] gridS = new Case[getI()][getJ()];
        for (int x = 0; x < getI(); x++) {
            for (int y = 0; y < getJ(); y++) {
                gridS[x][y] = Case.NUMBER;
            }
        }
        setGrid(gridS);
    }    // construire le tableau par default avec la valeur 0 partout
    
    //fonction qui met à jour les voisins à chaque installation de bombe
    public void majNumbers(int x, int y){
        if (x>0 && y>0) { // voisin haut gauche
            if(grid[y-1][x-1] == Case.NUMBER ) grid[y-1][x-1].setValueI(grid[y-1][x-1].getValueI()+1);
        }
        if (y>0) { // voisin haut
            if(grid[y-1][x] == Case.NUMBER ) grid[y-1][x].setValueI(grid[y-1][x].getValueI()+1);
        }
        if (x<getI() && y>0) { // voisin haut droit
            if(grid[y-1][x+1] == Case.NUMBER ) grid[y-1][x+1].setValueI(grid[y-1][x+1].getValueI()+1);
        }
        if(x>0){//voisin gauche
            if(grid[y][x-1] == Case.NUMBER ) grid[y][x-1].setValueI(grid[y][x-1].getValueI()+1);
        }
        if(x<getI()){//voisin droit
            if(grid[y][x+1] == Case.NUMBER ) grid[y][x+1].setValueI(grid[y][x+1].getValueI()+1);
        }
        if (x>0 && y<getJ()) { // voisin bas gauche
            if(grid[y+1][x-1] == Case.NUMBER ) grid[y+1][x-1].setValueI(grid[y+1][x-1].getValueI()+1);
        }
        if (y<getJ()) { // voisin bas
            if(grid[y+1][x] == Case.NUMBER ) grid[y+1][x].setValueI(grid[y+1][x].getValueI()+1);
        }
        if (x<getI() && y<getJ()) { // voisin haut droit
            if(grid[y-1][x+1] == Case.NUMBER ) grid[y-1][x+1].setValueI(grid[y-1][x+1].getValueI()+1);
        }
    }
    

    public void affichage() {
        for (int y = 0; y < getJ(); y++) {
            for (int x = 0; x < getI(); x++) {
                if (grid[x][y] == Case.NUMBER) {
                    System.out.print(grid[x][y].getValueI() + "  ");
                } else {
                    System.out.print(grid[x][y].getValueS() + "  ");
                }
            }
            System.out.println("  ");
        }
    }

    public void creatGrid() {
        int counter = 0;
        int mine = 0;
        defaultGrid();
        setNumberOfMine( getPercentage());
        while (counter != getNumberOfMine()) {
            int randI = (int) (Math.random() * getI());
            int randJ = (int) (Math.random() * getJ());
            if (Case.NUMBER.equals(grid[randI][randJ])) {
                grid[randI][randJ] = Case.MINE;
                counter++;
            }
        }
        for (int a = 0; a < getI(); a++) {
            for (int b = 0; b < getJ(); b++) {
                if (Case.NUMBER.equals(grid[a][b])) {
                    //mine = searchMine(a, b);
                    if (mine == 0) {
                        grid[a][b] = Case.ALONE;
                    } else {
                        Case.NUMBER.setValueI(mine);
                        System.out.print(Case.NUMBER.getValueI()+ "   ");                    
                        grid[a][b] = Case.NUMBER ;
                        System.out.println(grid[a][b].getValueI());

                    }
                }
            }
        }
    }   // La grille est crée, apres que la premiere case ne soit dévoiler
}

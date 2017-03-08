/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

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

    private int searchMine(int x, int y) {
        int mine = 0;
        // on vérifie si la case existe et sinon on regarde ce qu'il y a dedans
        if (x + 1 <= getI() - 1) {
            mine += (Case.MINE.equals(grid[x + 1][y])) ? 1 : 0;
        }         // coté droit
        if (x + 1 <= getI() - 1 && y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(grid[x + 1][y + 1])) ? 1 : 0;
        }    // diagonal haute droite
        if (x + 1 <= getI() - 1 && y - 1 >= 0) {
            mine += (Case.MINE.equals(grid[x + 1][y - 1])) ? 1 : 0;
        }// diagonale basse droite
        if (x - 1 >= 0) {
            mine += (Case.MINE.equals(grid[x - 1][y])) ? 1 : 0;
        }     // coté gauche
        if (x - 1 >= 0 && y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(grid[x - 1][y + 1])) ? 1 : 0;
        }     //diagonale haute gauche
        if (x - 1 >= 0 && y - 1 >= 0) {
            mine += (Case.MINE.equals(grid[x - 1][y - 1])) ? 1 : 0;
        }    // diagonale basse gauche
        if (y - 1 >= 0) {
            mine += (Case.MINE.equals(grid[x][y - 1])) ? 1 : 0;
        }    // coté haut
        if (y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(grid[x][y + 1])) ? 1 : 0;
        }    // coté bas
        return mine;
    } // Parcour des voisins pour voir si il y a une mine
    
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
                    mine = searchMine(a, b);
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

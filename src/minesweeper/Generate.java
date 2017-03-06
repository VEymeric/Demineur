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

    private Case[][] wireRack;  // tableau
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

    public Case getWireRack(int i, int j) {
        return wireRack[i][j];
    }

    public void setWireRack(Case[][] wireRack) {
        this.wireRack = wireRack;
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
        if (percentage < 0 || percentage > 100) {
            throw new java.lang.IllegalArgumentException(" Percentage is not consistent ");
        } else {
            this.percentage = percentage;
        }
    }

    public int getNumberOfMine() {
        return numberOfMine;
    }

    public void setNumberOfMine(int numberOfMine) {
        this.numberOfMine = numberOfMine;
    }

    private void defaultWireRack() {
        Case[][] wireRackS = new Case[getI()][getJ()];
        for (int x = 0; x < getI(); x++) {
            for (int y = 0; y < getJ(); y++) {
                wireRackS[x][y] = Case.NUMBER;
            }
        }
        setWireRack(wireRackS);
    }    // construire le tableau par default avec la valeur 0 partout

    private int searchMine(int x, int y) {
        int mine = 0;
        // on vérifie si la case existe et sinon on regarde ce qu'il y a dedans
        if (x + 1 <= getI() - 1) {
            mine += (Case.MINE.equals(wireRack[x + 1][y])) ? 1 : 0;
        }         // coté droit
        if (x + 1 <= getI() - 1 && y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(wireRack[x + 1][y + 1])) ? 1 : 0;
        }    // diagonal haute droite
        if (x + 1 <= getI() - 1 && y - 1 >= 0) {
            mine += (Case.MINE.equals(wireRack[x + 1][y - 1])) ? 1 : 0;
        }// diagonale basse droite
        if (x - 1 >= 0) {
            mine += (Case.MINE.equals(wireRack[x - 1][y])) ? 1 : 0;
        }     // coté gauche
        if (x - 1 >= 0 && y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(wireRack[x - 1][y + 1])) ? 1 : 0;
        }     //diagonale haute gauche
        if (x - 1 >= 0 && y - 1 >= 0) {
            mine += (Case.MINE.equals(wireRack[x - 1][y - 1])) ? 1 : 0;
        }    // diagonale basse gauche
        if (y - 1 >= 0) {
            mine += (Case.MINE.equals(wireRack[x][y - 1])) ? 1 : 0;
        }    // coté haut
        if (y + 1 <= getJ() - 1) {
            mine += (Case.MINE.equals(wireRack[x][y + 1])) ? 1 : 0;
        }    // coté bas
        return mine;
    } // Parcour des voisins pour voir si il y a une mine

    public void numberOfMine() { // Calcule du nombre de mine
        setNumberOfMine((getI() * getJ()) * getPercentage() / 100);
    }

    public void affichage() {
        for (int y = 0; y < getJ(); y++) {
            for (int x = 0; x < getI(); x++) {
                if (wireRack[x][y] == Case.NUMBER) {
                    System.out.print(wireRack[x][y].getValueI() + "  ");
                } else {
                    System.out.print(wireRack[x][y].getValueS() + "  ");
                }
            }
            System.out.println("  ");
        }
    }

    public void creatWireRack() {
        int counter = 0;
        int mine = 0;
        defaultWireRack();
        numberOfMine();
        while (counter != getNumberOfMine()) {
            int randI = (int) (Math.random() * getI());
            int randJ = (int) (Math.random() * getJ());
            if (Case.NUMBER.equals(wireRack[randI][randJ])) {
                wireRack[randI][randJ] = Case.MINE;
                counter++;
            }
        }
        for (int a = 0; a < getI(); a++) {
            for (int b = 0; b < getJ(); b++) {
                if (Case.NUMBER.equals(wireRack[a][b])) {
                    mine = searchMine(a, b);
                    if (mine == 0) {
                        wireRack[a][b] = Case.ALONE;
                    } else {
                        Case.NUMBER.setValueI(mine);
                        wireRack[a][b] = Case.NUMBER;
                    }
                }
            }
        }
    }   // La grille est crée, apres que la premiere case ne soit dévoiler
}

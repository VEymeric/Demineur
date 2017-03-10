package minesweeper;

import Modele.Case;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author gaetane
 */
public class LetsGo {

    private final Generate minus;
    private Case[][] ticTac;
    public boolean end = true;

    public LetsGo(Generate minus) {
        this.minus = minus;
    }

    public Case[][] getTicTac() {
        return ticTac;
    }

    public void setTicTac(Case[][] ticTac) {
        this.ticTac = ticTac;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public void order(String go,boolean ok) {
        String[] cr = go.split(" ");
        switch (cr[0]) {
            case "d":
                if (cr.length == 3) {
                    print(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
                    break;
                } else {
                    help();
                    break;
                }
            case "q":
                stop(" give up ");
                break;
            case "m":
                if (cr.length == 4) {
                    mark(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]), cr[3]);
                    break;
                } else {
                    help();
                    break;
                }
            default:
                System.out.println(" Not understand your order");
                help();
        }
    }

    private void print(int i, int j) {
        if (minus.getCase(i, j) == Case.MINE) {
            stop(" loose... ");
        }
        ticTac[i][j] = minus.getCase(i, j);
        if( minus.getCase(i, j) == Case.ALONE){
            propagation(i,j);
        }
        showTicTac();
    }
    
    private void mark(int i, int j, String mark) {
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
    }
    
    private void help() {
        System.out.println(" Not complete order ");
        System.out.println(" d x y -> show this case ");
        System.out.println(" m x y ?/! -> mark case with ?/!");
        System.out.println(" q -> quit ");
        showTicTac();
    }    
    
    private void propagation(int x, int y) {
        if (x + 1 <= minus.getI() - 1) {
            if (checkOrNot(x + 1, y) == false) {
                if (minus.getCase(x + 1, y) == Case.ALONE) {
                    ticTac[x + 1][y] = Case.ALONE;
                    propagation(x + 1, y);

                }
                if (minus.getCase(x + 1, y) == Case.NUMBER) {
                    ticTac[x + 1][y] = minus.getCase(x + 1, y);
                }
            }
        }
        if (x + 1 <= minus.getI() - 1 && y + 1 <= minus.getJ() - 1) {
            if (checkOrNot(x + 1, y + 1) == false) {
                if (minus.getCase(x + 1, y + 1) == Case.ALONE) {
                    ticTac[x + 1][y + 1] = Case.ALONE;
                    propagation(x + 1, y + 1);
                }
                if (minus.getCase(x + 1, y + 1) == Case.NUMBER) {
                    ticTac[x + 1][y + 1] = minus.getCase(x + 1, y + 1);
                }
            }
        }
        if (y + 1 <= minus.getJ() - 1) {
            if (checkOrNot(x, y + 1) == false) {                               
                if (minus.getCase(x, y + 1) == Case.ALONE) {
                    ticTac[x][y + 1] = Case.ALONE;
                    propagation(x, y + 1);
                }
                if (minus.getCase(x, y + 1) == Case.NUMBER) {
                    ticTac[x][y + 1] = minus.getCase(x, y + 1);
                }
            }
        }
        if (y - 1 >= 0) {
            if (checkOrNot(x, y - 1) == false) {
                if (minus.getCase(x, y - 1) == Case.ALONE) {
                    ticTac[x][y - 1] = Case.ALONE;
                    propagation(x, y - 1);
                }
                if (minus.getCase(x, y - 1) == Case.NUMBER) {
                    ticTac[x][y - 1] = minus.getCase(x, y - 1);
                }
            }
        }
        if (x - 1 >= 0) {
            if (checkOrNot(x - 1, y) == false) {
                if (minus.getCase(x - 1, y) == Case.ALONE) {
                    ticTac[x - 1][y] = Case.ALONE;
                    propagation(x - 1, y);
                }
                if (minus.getCase(x - 1, y) == Case.NUMBER) {
                    ticTac[x - 1][y] = minus.getCase(x - 1, y);
                }
            }
        }
        if (y - 1 >= 0 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y - 1) == false) {
                if (minus.getCase(x - 1, y - 1) == Case.ALONE) {
                    ticTac[x - 1][y - 1] = Case.ALONE;
                    propagation(x - 1, y - 1);
                }
                if (minus.getCase(x - 1, y - 1) == Case.NUMBER) {
                    ticTac[x - 1][y - 1] = minus.getCase(x - 1, y - 1);
                }
            }
        }
        if (y + 1 <= minus.getJ() - 1 && x - 1 >= 0) {
            if (checkOrNot(x - 1, y + 1) == false) {
                if (minus.getCase(x - 1, y + 1) == Case.ALONE) {
                    ticTac[x - 1][y + 1] = Case.ALONE;
                    propagation(x - 1, y + 1);
                }
                if (minus.getCase(x - 1, y + 1) == Case.NUMBER) {
                    ticTac[x - 1][y + 1] = minus.getCase(x - 1, y + 1);
                }
            }
        }
        if (y - 1 >= 0 && x + 1 <= minus.getI() - 1) {
            if (checkOrNot(x + 1, y - 1) == false) {
                if (minus.getCase(x + 1, y - 1) == Case.ALONE) {
                    ticTac[x + 1][y - 1] = Case.ALONE;
                    propagation(x + 1, y - 1);
                }
                if (minus.getCase(x + 1, y - 1) == Case.NUMBER) {
                    ticTac[x + 1][y - 1] = minus.getCase(x + 1, y - 1);
                }
            }
        }
    }    
    
    private boolean checkOrNot(int x, int y) {
        return ticTac[x][y] != Case.HIDE;
    }

    private boolean win() {
        int nbCase = 0;
        for (int x = 0; x < minus.getI() - 1; x++) {
            for (int y = 0; y < minus.getJ() - 1; y++) {
                if( ticTac[x][y] == Case.NUMBER || ticTac[x][y] == Case.ALONE ){
                    nbCase++;
                }
            }
        }
        return nbCase == minus.getI() * minus.getJ() - minus.getNumberOfMine();
    }

    private void stop(String oh) {
        System.out.println("____________End of the game : you " + oh + "__________ ");
        setEnd(false);
    }    
    
    public void showTicTac() { // erreur ! affichage des point si passent avant alors ne s'affiche pas !
        for (int y = 0; y < minus.getJ(); y++) {
            for (int x = 0; x < minus.getI(); x++) {
                if (Case.NUMBER.equals(ticTac[x][y])) {
                    System.out.print(ticTac[x][y].getValueI() + "  ");
                } else {
                    if (Case.ALONE.equals(ticTac[x][y])) {
                        propagation(x, y);
                    }
                    System.out.print(ticTac[x][y].getValueS() + "  ");
                }
            }
            System.out.println(" ");
        }
        if (win() == true) { // case cache sont des mines
            stop(" WIN !!!!! ");
        }
    }    

    public void hideTicTac() {
        Case[][] GridS = new Case[minus.getI()][minus.getJ()];
        for (int x = 0; x < minus.getI(); x++) {
            for (int y = 0; y < minus.getJ(); y++) {
                GridS[x][y] = Case.HIDE;
            }
        }
        setTicTac(GridS);
        showTicTac();
    }    
  
    
}
package Modele;

import java.util.Observable;
import java.awt.Color;
import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Matrice extends Observable implements Serializable{
    int width = 0, height = 0, mine = 0, time =0;
    private boolean inGame = true;
    private int countCase, countMine = 0;
    public Case[][] gridInit;

    public Matrice(int width, int height, int nbMine) {
        this.width = width;
        this.height = height;
        this.mine = nbMine;
        this.countCase = this.width * this.height - this.mine;
        generateInit();
    }

    public Case getCase(int i, int j){
        return gridInit[j][i];
    }

    public void update() { // Observer 
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

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }
    
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean isInGame() {
        return inGame;
    }

    private void generateInit() { // tableau de valeur initialisé
        Case[][] grid = new Case[getHeight()][getWidth()];
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                grid[y][x] = new Case(x, y);
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
    
    public void reloadMatrice( Matrice m){
        System.out.println(" ok ok  j'ai redonnée les valeurs");
        this.gridInit = m.gridInit;
        this.width = m.width;
        this.height = m.height;
        this.mine = m.mine;
        update();
    }
    
    
    //Genère une matrice de jeu à partir du premier clic en x;y
    public void generateMatrice(int xClic, int yClic) {
        System.out.println("Votre matrice viens de se générer.");
        this.countMine = this.mine;
        int mineUnused = this.mine;
        while (mineUnused > 0) {
            int randI = ThreadLocalRandom.current().nextInt(0, getWidth());
            int randJ = ThreadLocalRandom.current().nextInt(0, getHeight());
            if (gridInit[randJ][randI].getEtat() == CaseInit.NUMBER) {
                if (randI != xClic || randJ != yClic) {
                    gridInit[randJ][randI].setEtat(CaseInit.MINE);
                    majNumbers(randI, randJ);
                    mineUnused--;
                }
            }
        }
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (gridInit[y][x].isNumber()) {
                    gridInit[y][x].setColor(makeColor(gridInit[y][x].getBombes()));
                    if (gridInit[y][x].getBombes() == 0) {
                        gridInit[y][x].setEtat(CaseInit.ALONE);
                    }
                }
            }
        }
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++) {
                if (gridInit[j][i].getEtat() == CaseInit.NUMBER) {
                    System.out.print(gridInit[j][i].getEtat().getValue());
                } else {
                    System.out.print(gridInit[j][i].getEtat().getString());
                }
            }
            System.out.println(" ");
        }
        for (int j = 0; j < getHeight(); j++) {
            for (int i = 0; i < getWidth(); i++) {
                if (gridInit[j][i].isFlag()) {
                    this.countMine--;
                }
            }
        }
        reveal(xClic, yClic);
    }

    // verifier si il y a des drapeau a coté utile pour le double clic
    public int checkNeighbord(int x, int y) {
        int numberOfFlag = 0;
        if (x > 0 && y > 0) { // voisin haut gauche
            if (gridInit[y - 1][x - 1].isFlag()) { // voisin haut gauche
                numberOfFlag++;
            }
        }
        if (y > 0) { // voisin haut
            if (gridInit[y - 1][x].isFlag()) { // voisin haut
                numberOfFlag++;

            }
        }
        if (x < getWidth() - 1 && y > 0) { // voisin haut droit
            if (gridInit[y - 1][x + 1].isFlag()) { // voisin haut droit
                numberOfFlag++;

            }
        }
        if (x > 0) {//voisin gauche
            if (gridInit[y][x - 1].isFlag()) {//voisin gauche
                numberOfFlag++;
            }
        }
        if (x < getWidth() - 1) {//voisin droit
            if (gridInit[y][x + 1].isFlag()) {//voisin droit
                numberOfFlag++;

            }
        }
        if (x > 0 && y < getHeight() - 1) { // voisin bas gauche
            if (gridInit[y + 1][x - 1].isFlag()) { // voisin bas gauche
                numberOfFlag++;

            }
        }
        if (y < getHeight() - 1) { // voisin bas
            if (gridInit[y + 1][x].isFlag()) { // voisin bas
                numberOfFlag++;

            }
        }
        if (x < getWidth() - 1 && y < getHeight() - 1) { // voisin bas droit
            if (gridInit[y + 1][x + 1].isFlag()) { // voisin bas droit
                numberOfFlag++;

            }
        }
        return numberOfFlag;
    }

    // reveler les cases 
    public void revealDoubleClick(int x, int y) {
        reveal(x - 1, y - 1);
        reveal(x-1, y);
        reveal(x - 1, y + 1);
        reveal(x, y-1);
        reveal(x + 1, y);
        reveal(x + 1, y - 1);
        reveal(x, y + 1);
        reveal(x + 1, y + 1);
    }

    //Génère une matrice du jeu en créant un drapeau en premiere ligne
    public void startWithFlag(int xClic,int yClic) {
        System.out.println("Votre matrice viens de se générer.");
        this.countMine = this.mine;
        int mineUnused = this.mine;
        while (mineUnused > 0) {
            int randI = ThreadLocalRandom.current().nextInt(0, getWidth());
            int randJ = ThreadLocalRandom.current().nextInt(0, getHeight());
            if (gridInit[randJ][randI].getEtat() == CaseInit.NUMBER) {
                gridInit[randJ][randI].setEtat(CaseInit.MINE);
                majNumbers(randI, randJ);
                mineUnused--;
            }
        }
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (gridInit[y][x].isNumber()) {
                    gridInit[y][x].setColor(makeColor(gridInit[y][x].getBombes()));
                    if (gridInit[y][x].getBombes() == 0) {
                        gridInit[y][x].setEtat(CaseInit.ALONE);
                    }
                }
            }
        }
        markPrint(xClic,yClic);
    }

    //fonction qui ajoute 1 à toutes les cases autour d'une bombe : efficace pour initiliser
    private void majNumbers(int x, int y) {
        if (x > 0 && y > 0) { // voisin haut gauche
            if (gridInit[y - 1][x - 1].isNumber()) {
                gridInit[y - 1][x - 1].setBombes(gridInit[y - 1][x - 1].getBombes() + 1);
            }
        }
        if (y > 0) { // voisin haut
            if (gridInit[y - 1][x].getEtat() == CaseInit.NUMBER) {
                gridInit[y - 1][x].setBombes(gridInit[y - 1][x].getBombes() + 1);
            }
        }
        if (x < getWidth() - 1 && y > 0) { // voisin haut droit
            if (gridInit[y - 1][x + 1].getEtat() == CaseInit.NUMBER) {
                gridInit[y - 1][x + 1].setBombes(gridInit[y - 1][x + 1].getBombes() + 1);
            }
        }
        if (x > 0) {//voisin gauche
            if (gridInit[y][x - 1].getEtat() == CaseInit.NUMBER) {
                gridInit[y][x - 1].setBombes(gridInit[y][x - 1].getBombes() + 1);
            }
        }
        if (x < getWidth() - 1) {//voisin droit
            if (gridInit[y][x + 1].getEtat() == CaseInit.NUMBER) {
                gridInit[y][x + 1].setBombes(gridInit[y][x + 1].getBombes() + 1);
            }
        }
        if (x > 0 && y < getHeight() - 1) { // voisin bas gauche
            if (gridInit[y + 1][x - 1].getEtat() == CaseInit.NUMBER) {
                gridInit[y + 1][x - 1].setBombes(gridInit[y + 1][x - 1].getBombes() + 1);
            }
        }
        if (y < getHeight() - 1) { // voisin bas
            if (gridInit[y + 1][x].getEtat() == CaseInit.NUMBER) {
                gridInit[y + 1][x].setBombes(gridInit[y + 1][x].getBombes() + 1);
            }
        }
        if (x < getWidth() - 1 && y < getHeight() - 1) { // voisin bas droit
            if (gridInit[y + 1][x + 1].getEtat() == CaseInit.NUMBER) {
                gridInit[y + 1][x + 1].setBombes(gridInit[y + 1][x + 1].getBombes() + 1);
            }
        }
    }

    // Marquage des cases : drapeau/point d'interrogation / rien par rapport a la console
    public void mark(int i, int j, String mark) {
        if (this.isInGame() && !gridInit[j][i].isShow()) {
            switch (mark) {
                case "#":
                    if (gridInit[j][i].getCache() == CaseHide.FLAG) {
                        setCountMine(getCountMine() + 1);
                    }
                    gridInit[j][i].setCache(CaseHide.HIDE);

                    break;
                case "?":
                    if (gridInit[j][i].getCache() == CaseHide.FLAG) {
                        setCountMine(getCountMine() + 1);
                    }
                    gridInit[j][i].setCache(CaseHide.UNKNOW);

                    break;
                case "!":
                    gridInit[j][i].setCache(CaseHide.FLAG);
                    setCountMine(getCountMine() - 1);
                    break;
                default:
                    System.out.println(" je sais pas quoi mettre ");
            }
        }
    }

    // Cas pour l'affichage
    public void markPrint(int i, int j) {
        if(!this.isInGame()) return;
        if (gridInit[j][i].getCache() == CaseHide.HIDE ) {
            gridInit[j][i].setCache(CaseHide.FLAG);
            setCountMine(getCountMine() - 1);
        }else if (gridInit[j][i].isFlag()) {
            gridInit[j][i].setCache(CaseHide.UNKNOW);
            setCountMine(getCountMine() + 1);
        } else {
            gridInit[j][i].setCache(CaseHide.HIDE);
        }  
        update();
    }

    // Fonction pour dévoiler la case 
    public void reveal(int x, int y) {
        if (!this.isInGame() || x < 0 || y < 0 || x >= this.width || y >= this.height || this.gridInit[y][x].isShow() || this.gridInit[y][x].isFlag() || this.gridInit[y][x].isUnknow()) { // cas non nécessaire
            return;
        }
        this.gridInit[y][x].setCache(CaseHide.SHOW);
        if (this.gridInit[y][x].isMine()) {
            this.setInGame(false); // -> nullos qui perd
            this.setCountMine(this.getCountMine() - 1);
        } else {
            this.setCountCase(this.getCountCase() - 1);
            if (this.getCountCase() == 0) {
                this.setInGame(false); //  -> champion qui gagne
            }
            if (this.gridInit[y][x].isAlone()) {
                reveal(x - 1, y - 1);
                reveal(x - 1, y);
                reveal(x - 1, y + 1);
                reveal(x, y - 1);
                reveal(x, y + 1);
                reveal(x + 1, y - 1);
                reveal(x + 1, y);
                reveal(x + 1, y + 1);
            }
        }
    }

    // Couleur assigné a chaque case possédant un numero
    private Color makeColor(int numberMine) {
        switch (numberMine) {
            case 1:
                return Color.blue;
            case 2:
                return Color.green;
            case 3:
                return Color.red;
            case 4:
                return Color.yellow;
            case 5:
                return Color.pink;
            case 6:
                return Color.magenta;
            case 7:
                return Color.gray;
            case 8:
                return Color.orange;
            default:
                return Color.black;
        }
    }

}

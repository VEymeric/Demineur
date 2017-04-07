package Controleur;

import Modele.CaseHide;
import Modele.Matrice;
import Modele.Scores;
import View.Button;
import View.ControllTimer;
import View.Play;
import View.Print;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Math.round;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class GameController implements ActionListener, MouseListener {

    public Matrice m;
    File scoreSaveB = new File(" scoreOfMinesweaperBeginer.winner");
    File scoreSaveE = new File(" scoreOfMinesweaperExpert.winner");
    File scoreSaveI = new File(" scoreOfMinesweaperIntermediaire.winner");

    int nbSave = 0;
    ObjectInputStream checkScore;
    ObjectOutputStream toSave;
    public ControllTimer time;
    Print gameViewConsole;
    Play gameViewWindow;
    private String action;
    boolean messageEndSended = false;
    Scores test;
    Scanner sc;

    public GameController(int x, int y, double percentMine){ // Constructeur
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x, y, (int) round((x * y) * percentMine / 100.0));
        gameViewConsole = new Print();
        gameViewWindow = new Play(this);
        m.addObserver(gameViewWindow);
        m.addObserver(gameViewConsole);
        m.update();
        //time = new ControllTimer();
        this.startGame();  
    }

    // Refaire une nouvelle partie
    public void restart(int x, int y, double percentMine) {
        System.out.println("Votre controller a été créé");
        this.time.initChrono();
        m = new Modele.Matrice(x, y, (int) round((x * y) * percentMine / 100.0));
        gameViewConsole = new Print();
        //gameViewWindow = new Play(this);
        gameViewWindow.refreshGrid();
        m.addObserver(gameViewWindow);
        m.addObserver(gameViewConsole);
        m.update();
        messageEndSended = false;
    }

    public void exit() {
        System.exit(0);
    }

    // Tourne en rond pendant le jeu
    public final void startGame(){
        System.out.println("Chuuuuuttt ! Silence ! Le jeu commence :");
        sc = new Scanner(System.in);
        while (m.isInGame()) {
            action = sc.nextLine();
            System.out.println("action " + action);
            order(action);
        }
        gameOver();
    }

    // Action après la défaite
    public void gameOver() {
        if (this.messageEndSended) {
            return;
        }
        this.time.stopChrono();
        this.m.setInGame(false);
        this.messageEndSended = true;
        if (m.getCountCase() > 0) {
            System.out.println("Une belle défaite ! Il restait " + m.getCountCase() + " cases à deminer.");
            JOptionPane.showMessageDialog(null, "Le Joker a eu raison de vous.", null, JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Gotham est sauvé !", null, JOptionPane.INFORMATION_MESSAGE);
            System.out.println("La foule est en délire devant notre demineur pro !");
            if (m.getHeight() == 9 && m.getWidth() == 9 && m.getMine() == 10) {
                test = new Scores(1, gameViewWindow.giveName(), this.time.value());
            }
            if (m.getHeight() == 16 && m.getWidth() == 16 && m.getMine() == 40) {
                test = new Scores(2, gameViewWindow.giveName(), this.time.value());
            }
            if (m.getHeight() == 16 && m.getWidth() == 30 && m.getMine() == 100) {
                test = new Scores(3, gameViewWindow.giveName(), this.time.value());
            }
        }
        this.showAllCase();
        System.out.println("Merci d'avoir joué !");
    }

    //Recupère les ordre donnés par la console et les traites, si inconnu affiche help;
    public void order(String go) {
        String[] cr = go.split(" ");
        switch (cr[0]) {
            case "debug":
                debug();
                break;
            case "d":
                if (cr.length == 3) {
                    if (m.getCountMine() <= 0) {//Cas possible que si le jeu n'a pas commence
                        m.generateMatrice(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
                        this.time.startChrono();
                    }
                    m.reveal(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
                    break;
                } else {
                    help();
                    break;
                }
            case "q":
                exit();
                break;
            case "m":
                if (cr.length == 4) {
                    if (m.getCountMine() == 0) {
                        m.startWithFlag(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
                        break;
                    } else {
                        m.mark(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]), cr[3]);
                        break;
                    }
                }
            default:
                help();
        }
        m.update();
    }

    public void save() throws IOException {
        // Fichier dans lequel on va écrire;
        File fichier = new File("test1.minesweaper");
        // Ouverture du flux du fichier 
        ObjectOutputStream fluxFichier = new ObjectOutputStream(new FileOutputStream(fichier));
        // Serialisation de l'objet
        fluxFichier.writeInt(m.getWidth());
        fluxFichier.writeInt(m.getHeight());
        fluxFichier.writeInt(m.getMine());
        System.out.println(m.getMine());
        for (int j = 0; j < m.getHeight(); j++) {
            for (int i = 0; i < m.getWidth(); i++) {
                m.gridInit[j][i].writeObject(fluxFichier);
            }
        }
        System.out.println(" Sérialisation ok");
    }

    //récuperer un niveau ou reprendre une partie
    public void load() throws IOException, ClassNotFoundException {
        File fichier = new File("test1.minesweaper");
        // Ouverture du flux fichier pour récuperer
        ObjectInputStream fluxRentrant = new ObjectInputStream(new FileInputStream(fichier));
        int x = fluxRentrant.readInt();
        int y = fluxRentrant.readInt();
        int mines = fluxRentrant.readInt();
        Matrice map = new Matrice(x, y, mines);
        System.out.println(map.getMine());
        for (int j = 0; j < map.getHeight(); j++) {
            for (int i = 0; i < map.getWidth(); i++) {
                map.gridInit[j][i].readObject(fluxRentrant);
            }
        }
        map.setInGame(true);
        startGame();
        System.out.println(" Normalement déserializé ");
    }
/*
    public void saveScore() throws IOException {
        setNbSave(recupNbSave());
        nbSave++;
        switch (test.getLevel()) {
            case 1: //beginner
                toSave = new ObjectOutputStream(new FileOutputStream(scoreSaveB));
                break;
            case 2: //intermediaire
                toSave = new ObjectOutputStream(new FileOutputStream(scoreSaveI));
                break;
            case 3: // expert
                toSave = new ObjectOutputStream(new FileOutputStream(scoreSaveE));
                break;
        }
        toSave.writeInt(nbSave);
        for (int i = 0; i < nbSave; i++) {
            toSave.writeObject(test.getPseudo() + test.getTimer());
        }
        System.out.println(" ok ser ");
    }

    public void loadScore() throws IOException, ClassNotFoundException {
        setNbSave(recupNbSave());
        for (int i = 0; i < nbSave; i++) {
            System.out.println(String.valueOf(checkScore.readObject()));
        }
    }

    public int getNbSave() {
        return nbSave;
    }

    public void setNbSave(int nbSave) {
        this.nbSave = nbSave;
    }

    public int recupNbSave() throws IOException {
        switch (test.getLevel()) {
            case 1: //beginner
                checkScore = new ObjectInputStream(new FileInputStream(scoreSaveB));
                break;
            case 2: //intermediaire
                checkScore = new ObjectInputStream(new FileInputStream(scoreSaveI));
                break;
            case 3: // expert
                checkScore = new ObjectInputStream(new FileInputStream(scoreSaveE));
                break;
        }
        setNbSave(checkScore.readInt());
        return getNbSave();
    }
*/
    //affiche les diffèrentes commandes possibles
    private void help() {
        System.out.println(" Not complete order ");
        System.out.println(" d x y -> show this case ");
        System.out.println(" m x y ?/! -> mark case with ?/!");
        System.out.println(" q -> quit ");
    }

    // Option pour afficher toutes les mines normalement que pour le controleur !
    public void debug() {
        if (m.isInGame()) {
            for (int j = 0; j < m.getHeight(); j++) {
                for (int i = 0; i < m.getWidth(); i++) {
                    if (m.gridInit[j][i].isMine()) {
                        switch (m.gridInit[j][i].getCache()) {
                            case FLAG:
                                m.gridInit[j][i].setCache(CaseHide.FLAG);
                                break;
                            case UNKNOW:
                                m.gridInit[j][i].setCache(CaseHide.UNKNOW);
                                break;
                            case INTACTE:
                                m.gridInit[j][i].setCache(CaseHide.HIDE);
                                break;
                            default:
                                m.gridInit[j][i].setCache(CaseHide.INTACTE);
                                break;
                        }
                    }
                }
            }
        }
        m.update();
    }

    // En cas de défaite affiche toutes les cases BOMBES
    public void showAllCase() {
        for (int j = 0; j < m.getHeight(); j++) {
            for (int i = 0; i < m.getWidth(); i++) {
                if (m.gridInit[j][i].isMine() && m.gridInit[j][i].isHide()) {
                    m.gridInit[j][i].setCache(CaseHide.SHOW);
                }
            }
        }
        m.update();
    }

    // Action lors d'un double clics sur une case
    private void doubleClick(int x, int y) {
        System.out.println(" je suis un double clic ");
        int numberOfMine = m.gridInit[y][x].getBombes();
        if (numberOfMine == m.checkNeighbord(x, y)) {
            m.revealDoubleClick(x, y);
        }
        m.update();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    // Action quand on clic avec la souris
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof Button) {
            Button source = (Button) e.getSource();
            if (source.isEnabled()) {
                switch (e.getButton()) {
                    case 3:
                        m.markPrint(source.x, source.y);
                        break;
                    case 1:
                        action = String.valueOf("d " + source.x + " " + source.y);
                        order(action);
                        break;
                    default:
                        System.out.println(" rien compris ");
                        break;
                }
            } else if (e.getClickCount() == 2) {
                doubleClick(source.x, source.y);
            }
            gameOver();
        } else {
            debug();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

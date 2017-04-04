package Controleur;

import Modele.CaseHide;
import Modele.Matrice;
import View.Button;
import View.Play;
import View.Print;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class GameController implements ActionListener, MouseListener {
    public Matrice m;
    Print gameViewConsole;
    Play gameViewWindow;
    private String action;
    boolean messageEndSended = false;
    Scanner sc;

    public GameController(int x, int y, int percentMine) { // Constructeur
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x, y, (x * y) * percentMine / 100);
        gameViewConsole = new Print();
        gameViewWindow = new Play(this);
        //gameViewWindow.withCase(x, y, this);
        m.addObserver(gameViewWindow);
        m.addObserver(gameViewConsole);
        m.update();
        this.startGame();
    }

    // Refaire une nouvelle partie
    public void restart(int x, int y, int percentMine) {
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x, y, (x * y) * percentMine / 100);
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
    public final void startGame() {
        System.out.println("Chuuuuuttt ! Silence ! Le jeu commence :");
        sc = new Scanner(System.in);
        while (m.isInGame()) {
            action = sc.nextLine();
            order(action);
        }
        gameOver();
    }
    
    // Action après la défaite
    public void gameOver() {
        if (this.messageEndSended) {
            return;
        }
        this.messageEndSended = true;
        if (m.getCountCase() > 0) {
            System.out.println("Une belle défaite ! Il restait " + m.getCountCase() + " cases à deminer.");
            showAllCase();
        } else {
            System.out.println("La foule est en délire devant notre demineur pro !");
        }
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
                    if (m.getCountMine() == 0) {//Cas possible que si le jeu n'a pas commence
                        m.generateMatrice(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
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

    // En cas de défaite affiche toutes les cases
    public void showAllCase() {
        for (int j = 0; j < m.getHeight(); j++) {
            for (int i = 0; i < m.getWidth(); i++) {
                m.gridInit[j][i].setCache(CaseHide.SHOW);
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
            if (m.isInGame() != true) {
                this.gameOver();
            }
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

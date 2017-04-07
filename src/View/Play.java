package View;

import View.ControllTimer;
import Controleur.GameController;
import Modele.CaseHide;
import Modele.CaseInit;

import Modele.Matrice;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author gaetane
 */
public class Play implements Observer {

    public JFrame game = new JFrame(); // nouvelle fenetre
    private JPanel grid;
    private JButton countOfMine = new JButton();
    private JPanel panelTimer = new JPanel();
    private final JMenuBar gameMenu;
    private final GameController controleur;

    public Play(GameController controleur) {
        this.controleur = controleur;
        game.setTitle(" Let's go ! ");
        game.setSize(controleur.m.getWidth() * 80, controleur.m.getWidth() * 80);
        game.setMinimumSize(new Dimension(controleur.m.getWidth() * 70, controleur.m.getHeight() * 70));
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new BorderLayout(5, 5));
        setTimer(panelTimer, "TIMER : ");
        
        game.add(panelTimer, BorderLayout.NORTH);
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(), controleur), BorderLayout.CENTER);
        gameMenu = new GameMenu(controleur);
        game.setJMenuBar(gameMenu);
        countOfMine.setEnabled(false);
        game.add(countOfMine, BorderLayout.SOUTH);
        game.setVisible(true);
    }

    
    private JPanel setTimer(JPanel panel, String txt){
        JLabel textTime = new JLabel(txt);
        JLabel myLabel = new JLabel();
        ControllTimer myTimer = new ControllTimer(myLabel);
        panel.add(myTimer);
        controleur.time = myTimer;
        myLabel.setText(Integer.toString(myTimer.value()));
        panel.add(textTime);
        panel.add(myLabel);
        
        return panel;
    }
    // récupère le nombre de mine pour la mise a jour
    private String countMine() {
        String ok;
        if (controleur.m.getCountMine() == 0) {
            ok = "Partie pas encore commencée";
        } else {
            ok = "Nombre de mines restantes:" + String.valueOf(controleur.m.getCountMine());
        }
        return ok;
    }    

    public void refreshGrid() {
        game.remove(grid);
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(), controleur), BorderLayout.CENTER);
        game.setVisible(true);
    }

    // Bouton debbuger
    private JComponent debugger() {
        JButton actionDebug = new JButton("debug");
        actionDebug.addMouseListener(controleur);
        return actionDebug;
    }

    // Crée les cases dans la vue
    private JComponent withCase(int x, int y, GameController controleur) {
        grid = new JPanel();
        grid.setLayout(new BorderLayout(5, 5));
        grid.setLayout(new GridLayout(y, x, 5, 5));
        for (int j = 0; j < y; j++) {
            for (int i = 0; i < x; i++) {
                Button button = new Button();
                button.setSizeAH(button.getSize());
                button.addMouseListener(controleur);
                button.x = i;
                button.y = j;
                button.setEnabled(true);
                grid.add(button);
            }
        }
        return grid;
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof Matrice) {
            for (int j = 0; j < controleur.m.getHeight(); j++) {
                for (int i = 0; i < controleur.m.getWidth(); i++) {
                    countOfMine.setText(countMine());
                    showOrnot(i, j);
                }
            }
        }
    }

    // Dévoiler ou non 
    private void showOrnot(int i, int j) {
        Button button = getButton(i, j);
        button.setString(String.valueOf(controleur.m.gridInit[j][i].getEtat()));
        if (!controleur.m.gridInit[j][i].isHide()) {
            button.paintComponents(button.getGraphics());
            switch (controleur.m.gridInit[j][i].getCache()) {
                case FLAG:
                    addIcone(button, "assets/bat2.png");
                    button.setString(" ");
                    break;
                case UNKNOW:
                    addIcone(button, "assets/enigma.png");
                    button.setString(" ");
                    break;
                case INTACTE:
                    addIcone(button, "assets/intacte.png");
                    button.setEnabled(true);
                    button.setString(" ");
                    break;
                default:
                    switch (controleur.m.gridInit[j][i].getEtat()) {
                        case NUMBER:
                            button.setIcon(null);
                            button.setString(String.valueOf(controleur.m.gridInit[j][i].getBombes()));
                            button.setEnabled(false);
                            button.setColor(controleur.m.gridInit[j][i].getColor());
                            break;
                        case ALONE:
                            button.setIcon(null);
                            button.setString(" ");
                            button.setEnabled(false);
                            break;
                        default:
                            button.setString(" ");
                            addIcone(button, "assets/boooom.png");
                            button.setEnabled(false);
                            break;
                    }
            }
        } else {
            button.setIcon(null);
            button.setString(" ");
        }
        if (controleur.m.gridInit[j][i].isHide()) {
            button.setEnabled(true);
        }
    }

    // Donne une image a chaque élément l'utilisant ( flag/mine/unknow)
    private void addIcone(Button button, String srcImage) {
        ImageIcon icon = new ImageIcon(srcImage);
        Image image = icon.getImage(); // l'image transforme
        Image newimg = image.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back 
        button.setText(null);
        button.setIcon(icon);
    }

    // Récuperer la position d'un bouton du tableau
    private Button getButton(int i, int j) {
        return (Button) grid.getComponent(j * controleur.m.getWidth() + i);
    }
    public String  giveName(){
        String nom = JOptionPane.showInputDialog(null, "Entrez votre pseudo : ", "Gendarmerie nationale !", JOptionPane.INFORMATION_MESSAGE);
        return nom;
    }
}

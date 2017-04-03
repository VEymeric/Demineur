/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controleur.GameController;
import Modele.CaseHide;
import Modele.CaseInit;
import Modele.Matrice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author gaetane
 */
public class Play implements Observer {
    public JFrame game = new JFrame(); // nouvelle fenetre
    private JPanel grid;
    private JButton countOfMine = new JButton();
    private final JMenuBar gameMenu;
    private final GameController controleur;

    public Play(GameController controleur) {
        this.controleur = controleur;
        game.setTitle(" Let's go ! ");
        game.setSize(controleur.m.getWidth() * 80, controleur.m.getWidth() * 80);
        game.setMinimumSize(new Dimension(controleur.m.getWidth() * 70, controleur.m.getHeight() * 70));
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new BorderLayout(5, 5));
        game.add(debugger(), BorderLayout.WEST);
        game.add(withCase(controleur.m.getWidth(), controleur.m.getHeight(), controleur), BorderLayout.CENTER);
        gameMenu = new GameMenu(controleur);
        game.setJMenuBar(gameMenu);
        countOfMine.setEnabled(false);
        game.add(countOfMine, BorderLayout.SOUTH);
        game.setVisible(true);
    }
    
  // récupère le nombre de mine pour la mise a jour
    private String countMine() {
        String ok;
    if(controleur.m.getCountMine()==0 ){
        ok = "Partie pas encore commencé";
    }
    else{
        ok = "Nombre de mine restantes:" + String.valueOf(controleur.m.getCountMine());
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
            if (controleur.m.gridInit[j][i].getCache() == CaseHide.FLAG || controleur.m.gridInit[j][i].getCache() == CaseHide.UNKNOW) { // c'est un drapeau ou on sait pas 
                if (controleur.m.gridInit[j][i].getCache() == CaseHide.FLAG) {
                    addIcone(button,"assets/flag.png");
                    button.setString(" ");
                } else if (controleur.m.gridInit[j][i].getCache() == CaseHide.UNKNOW) {
                    addIcone(button,"assets/interr.png");
                    button.setString(" ");
                }
            } else if (controleur.m.gridInit[j][i].getEtat() == CaseInit.NUMBER) { // c'est un nombre
                button.setIcon(null);
                button.setString(String.valueOf(controleur.m.gridInit[j][i].getBombes()));
                button.setEnabled(false);
                button.setColor(controleur.m.gridInit[j][i].getColor());
                //button.setForeground((Color) controleur.m.gridInit[j][i].getColor());
            } else if (controleur.m.gridInit[j][i].getEtat() == CaseInit.ALONE) {    // c'est un point 
                button.setIcon(null);
                button.setString(" ");
                button.setEnabled(false);
            } else {
                button.setString(" ");
                addIcone(button,"assets/mine.png");
                button.setEnabled(false);
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
        Image image = icon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(button.getWidth(), button.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        icon = new ImageIcon(newimg);  // transform it back 
        button.setText(null);
        button.setIcon(icon);
    }

    private Button getButton(int i, int j) {
        return (Button) grid.getComponent(j * controleur.m.getWidth() + i);
    }
}

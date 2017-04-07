package View;

import Controleur.GameController;
import Modele.Serialization;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GameMenu extends JMenuBar {

    private final JMenu menu_game = new JMenu("Game");
    private final JMenu menu_new = new JMenu("New");
    private final JMenuItem item_beginner = new JMenuItem("Beginner");
    private final JMenuItem item_inter = new JMenuItem("Intermediate");
    private final JMenuItem item_expert = new JMenuItem("Expert");
    private final JMenuItem item_custom = new JMenuItem("Custom");
    private final JMenuItem item_quit = new JMenuItem("Quit");

    private final JMenu menu_option = new JMenu("Options");
    private final JMenuItem item_debug = new JMenuItem("CheatMode");
    private final JMenuItem item_save = new JMenuItem("Save");
    private final JMenuItem item_score = new JMenuItem("Scores");
    private final JMenuItem item_load = new JMenuItem("Load");

    private final JFrame custom;

    public GameMenu(GameController controleur) {
        this.custom = new CustomGamePanel(controleur); //custom créer et cacher, on le garde en mémoire !
        this.menu_game.setMnemonic('g');

        this.menu_new.setMnemonic('n');

        this.item_beginner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
        this.item_beginner.addActionListener((ActionEvent arg0) -> {
            controleur.restart(9, 9, 12);
        });
        this.item_inter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
        this.item_inter.addActionListener((ActionEvent arg0) -> {
            controleur.restart(16, 16, 15.7);
        });
        this.item_expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        this.item_expert.addActionListener((ActionEvent arg0) -> {
            controleur.restart(16, 30, 20.8);
        });
        this.item_custom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        this.item_custom.addActionListener((ActionEvent arg0) -> {
            this.custom.setVisible(true);
        });
        this.item_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        this.item_quit.addActionListener((ActionEvent arg0) -> {
            controleur.exit();
        });

        this.menu_option.setMnemonic('o');
        this.item_debug.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        this.item_debug.addActionListener((ActionEvent arg0) -> {
            controleur.debug();
        });

        this.item_save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        this.item_save.addActionListener((ActionEvent arg0) -> {
            try {
                controleur.save();
            }   catch (IOException ex) {
                Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
        this.item_load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
        this.item_load.addActionListener((ActionEvent arg1) -> {
            try {
                controleur.load();
            } catch (IOException | ClassNotFoundException e) {
                Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, e);
            }

        });
        this.item_score.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK));
        this.item_score.addActionListener((ActionEvent arg1) -> {
            JFrame frame = new JFrame("Best scores");
                Serialization s = new Serialization();
                try{
                    s.loadScore();
                }catch(IOException | ClassNotFoundException e){
                }
                s.updateScore();
                ScoresPanel sp = new ScoresPanel();
                sp.setScores(s.scoresBeginner, s.scoresIntermediate, s.scoresExpert);
                frame.add(sp);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        });
        this.menu_new.add(this.item_beginner);
        this.menu_new.add(this.item_inter);
        this.menu_new.add(this.item_expert);
        this.menu_new.add(this.item_custom);

        this.menu_game.add(this.menu_new);
        this.menu_game.addSeparator();
        this.menu_game.add(this.item_quit);

        this.menu_option.add(this.item_debug);
        this.menu_option.add(this.item_save);
        this.menu_option.add(this.item_load);
        this.menu_option.add(this.item_score);
        

        this.add(this.menu_game);
        this.add(this.menu_option);
        this.setVisible(true);
    }
}

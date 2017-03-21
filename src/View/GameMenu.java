package View;

import Controleur.GameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class GameMenu extends JMenuBar {
  private JMenu menu_game = new JMenu("Game");
  private JMenu menu_new = new JMenu("New");
  private JMenuItem item_quit = new JMenuItem("Quit");
  private JMenuItem item_beginner = new JMenuItem("Beginner");
  private JMenuItem item_inter = new JMenuItem("Intermediate");
  private JMenuItem item_expert = new JMenuItem("Expert");
  private JMenuItem item_custom = new JMenuItem("Custom");

  public GameMenu(GameController controleur){
   // this.menu_game.setAccelerator(KeyStroke.getKeyStroke('c'));
   this.menu_game.setMnemonic('g');
   this.menu_new.setMnemonic('n');

   this.item_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
   this.item_beginner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
   this.item_inter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
   this.item_expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
   this.item_custom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));

   this.item_quit.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent arg0) {
          controleur.exit();
      }        
    });
   
    this.item_beginner.addActionListener(new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent arg0) {
            controleur.restart(5, 5, 10);
        }
    });
    this.item_inter.addActionListener(new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent arg0) {
            controleur.restart(10, 10, 20);
        }
    });
    this.menu_new.add(this.item_beginner);
    this.menu_new.add(this.item_inter);
    this.menu_new.add(this.item_expert);
    this.menu_new.add(this.item_custom);

    this.menu_game.add(this.menu_new);
    this.menu_game.add(this.item_quit);

    this.add(this.menu_game);
    //this.add(test2);
    //this.setJMenuBar(menuBar);
    this.setVisible(true);
  }
}
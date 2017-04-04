package View;

import Controleur.GameController;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GameMenu extends JMenuBar {
  private final JMenu menu_game = new JMenu("Game");
  private final JMenu menu_new = new JMenu("New");
  private final JMenuItem item_quit = new JMenuItem("Quit");
  private final JMenuItem item_beginner = new JMenuItem("Beginner");
  private final JMenuItem item_inter = new JMenuItem("Intermediate");
  private final JMenuItem item_expert = new JMenuItem("Expert");
  private final JMenuItem item_custom = new JMenuItem("Custom");

  public GameMenu(GameController controleur){
   //raccourci sur nos menus 
   this.menu_game.setMnemonic('g');
   this.menu_new.setMnemonic('n');
   this.item_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
   this.item_beginner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
   this.item_inter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
   this.item_expert.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
   this.item_custom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));

   //actions
   this.item_quit.addActionListener((ActionEvent arg0) -> {
       controleur.exit();
   });
   
    this.item_beginner.addActionListener((ActionEvent arg0) -> {
        controleur.restart(9, 9, 12);
   });
    this.item_inter.addActionListener((ActionEvent arg0) -> {
        controleur.restart(16, 16, 15.7);
   });
    this.item_expert.addActionListener((ActionEvent arg0) -> {
        controleur.restart(16, 30, 20.8);
   });
    this.item_custom.addActionListener((ActionEvent arg0) -> {
        CustomGamePanel koukou = new CustomGamePanel(controleur);
   });
    this.menu_new.add(this.item_beginner);
    this.menu_new.add(this.item_inter);
    this.menu_new.add(this.item_expert);
    this.menu_new.add(this.item_custom);

    this.menu_game.add(this.menu_new);
    this.menu_game.add(this.item_quit);

    this.add(this.menu_game);
    this.setVisible(true);
  }
}

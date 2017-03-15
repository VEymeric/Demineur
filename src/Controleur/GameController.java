package Controleur;

import Modele.CaseHide;
import Modele.Matrice;
import View.Button;
import View.Play;
import View.Print;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class GameController implements ActionListener {
    public Matrice m;
    Print gameViewConsole;
    Play gameViewWindow;
    private String action;
    Scanner sc;
    
    
            
    public GameController(int x, int y, int percentMine){ // Constructeur
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x,y,(x * y) * percentMine/ 100);
        gameViewConsole = new Print();
        gameViewWindow = new Play(this);
        m.addObserver(gameViewWindow);
        m.addObserver(gameViewConsole);
        m.update();
    }
    
    public void startGame(){ 
        System.out.println("Chuuuuuttt ! Silence ! Le jeu commence :");
        sc = new Scanner(System.in);
        
        while (m.isInGame()) {
            action = sc.nextLine();
            order(action);
        }
        if(m.getCountCase()>0){
            System.out.println("Une belle défaite ! Il restait "+m.getCountCase()+" cases à deminer.");
        }else{
            System.out.println("La foule est en délire devant notre demineur pro !");
        }
        System.out.println("Merci d'avoir joué !");
    }
  
    //Recupère les ordre donnés par la console et les traites, si inconnu affiche help;
    public void order(String go) {
        String[] cr = go.split(" ");
        switch (cr[0]) {
            case "debug" : 
                debug();
                break;
            case "d":
                if (cr.length == 3) {
                    if(m.getCountMine() == 0){//Cas possible que si le jeu n'pas commence
                        m.generateMatrice(Integer.parseInt(cr[1]),Integer.parseInt(cr[2]));
                    }
                    m.reveal(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
                    m.update();
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
                    m.mark(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]), cr[3]);
                    break;
                } else {
                    help();
                    break;
                }
            default:
                help();
        }
    }
    
    private void stop(String oh) {
        System.out.println("____________End of the game : you " + oh + "__________ ");
    }  

    //affiche les diffèrentes commandes possibles
    private void help() {
        System.out.println(" Not complete order ");
        System.out.println(" d x y -> show this case ");
        System.out.println(" m x y ?/! -> mark case with ?/!");
        System.out.println(" q -> quit ");
    }
    
    public void debug(){
            for(int j= 0; j<m.getHeight();j++ ){
                for(int i = 0; i < m.getWidth();i++){
                    if(m.gridInit[j][i].isMine() && m.gridInit[j][i].isHide()){
                        m.gridInit[j][i].setCache(CaseHide.SHOW);
                    }
                    else if(m.gridInit[j][i].isMine() && !m.gridInit[j][i].isHide()){
                        m.gridInit[j][i].setCache(CaseHide.HIDE);                        
                    }
                }
            }
            m.update();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Button) {
            Button source = (Button) e.getSource();
            action= String.valueOf( "d " + source.x +" "+ source.y );
            order(action);
        }
        else{
            order("debug");
        }
    }

}
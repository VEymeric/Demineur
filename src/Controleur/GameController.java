package Controleur;

import Modele.Matrice;
import Modele.CaseInit;
import View.Print;
import java.util.Scanner;

public class GameController{
    Matrice m;
    private ?
    Print gameView;
            
    public GameController(int x, int y, int percentMine){
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x,y,(x * y) * percentMine/ 100);
        gameView = new Print();
        m.addObserver(gameView);
        m.update();
        startGame();

    }
    public void startGame(){
        System.out.println("Chuuuuuttt ! Silence ! Le jeu commence :");
        Scanner sc = new Scanner(System.in);
        String action;
        while (m.isInGame()) {
            action = sc.nextLine();
            order(action);
            m.update();
        }
        if(m.getCountCase()>0){
            System.out.println("Une belle défaite ! Il restait "+m.getCountCase()+" cases à deminer.");
        }else{
            System.out.println("La foule est en délire devant notre demineur pro !");
        }
        System.out.println("Merci d'avoir joué !");
 
    }
    //Recupère les ordre donnés par la console et les traites, si inconnu affiche l'help;
    public void order(String go) {
        boolean end;
        String[] cr = go.split(" ");
        switch (cr[0]) {
            case "d":
                if (cr.length == 3) {
                    if(m.getCountMine() == 0){//Cas possible que si le jeu n'pas commence
                        m.generateMatrice(Integer.parseInt(cr[1]),Integer.parseInt(cr[2]));
                    }
                    m.reveal(Integer.parseInt(cr[2]), Integer.parseInt(cr[1]));
                    break;
                } else {
                    help();
                    break;
                }
            case "q":
                //stop(" give up ");
                break;
            case "m":
                if (cr.length == 4) {
                    m.mark(Integer.parseInt(cr[2]), Integer.parseInt(cr[1]), cr[3]);
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
    
    public void test(){
        System.out.println("fonction test");

    }
}
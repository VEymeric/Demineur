package Controleur;

import Modele.Matrice;
import Modele.CaseInit;
import View.Print;

public class GameController{
    Matrice m;
    private Print gameView;
            
    public GameController(int x, int y, int percentMine){
        System.out.println("Votre controller a été créé");
        m = new Modele.Matrice(x,y,(x * y) * percentMine/ 100);
    }
    
    //Recupère les ordre donnés par la console et les traites, si inconnu affiche l'help;
    public void order(String go,boolean ok) {
        String[] cr = go.split(" ");
        switch (cr[0]) {
            case "d":
                if (cr.length == 3) {
                    //m.print(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]));
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
                    //mark(Integer.parseInt(cr[1]), Integer.parseInt(cr[2]), cr[3]);
                    break;
                } else {
                    help();
                    break;
                }
            default:
                help();
        }
    }
    
public void affichage(){
        for(int j=0;j< m.getHeight();j++){
            for(int i = 0;i < m.getWidth(); i++){
                if(m.getGridInitCase(j,i) == CaseInit.NUMBER ){
                    System.out.print("  " +m.getGridInitCase(j,i).getValue()+ "  ");                   
                }
                else{
                    System.out.print("  "+m.getGridInitCase(j,i)+ "  ");                    
                }
            }
            System.out.println(" ");
        }   
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
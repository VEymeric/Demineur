package View;

import java.util.Observable;
import java.util.Observer;
import Modele.Matrice;

public class Print implements Observer {
    // argument pour l'affichage : tableau a afficher Case[][]
    
    /*private void view(Case[][] grid){
    }*/
    
    public void printHello(){
        System.out.println("Koukou toi !");
    }
    
    public void affichage(Matrice m){
        for(int j=0;j< m.getHeight();j++){
            for(int i = 0;i < m.getWidth(); i++){
                if(m.gridInit[j][i].isShow()){
                    if(m.gridInit[j][i].isNumber()){
                        System.out.print(" " +m.gridInit[j][i].getBombes() );                   
                    }
                    else{
                        System.out.print(" "+m.gridInit[j][i].getEtat().getString());                    
                    }
                }else if(m.gridInit[j][i].isHide()){
                    System.out.print(" "+m.gridInit[j][i].getCache().getString());                    
                }
            }
            System.out.println(" ");
        }   
    }       
    @Override
    public void update(Observable obs, Object arg) { // a implementer
        System.out.println("Vous avez notifié");
        if(obs instanceof Matrice){
            affichage((Matrice)obs);
        }
    }

}

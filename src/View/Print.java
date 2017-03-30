package View;

import java.util.Observable;
import java.util.Observer;
import Modele.Matrice;

public class Print implements Observer { // a renommer en PrintConsole !!
    public void affichage(Matrice m){
        System.out.println("### PLATEAU DE JEU ###");
        for(int j=0;j< m.getHeight();j++){
            for(int i = 0;i < m.getWidth(); i++){
                if(m.gridInit[j][i].isShow()){
                    if(m.gridInit[j][i].isNumber()){
                        System.out.print(" " +m.gridInit[j][i].getBombes() );                   
                    }
                    else{
                        System.out.print(" "+m.gridInit[j][i].getEtat().getString());                    
                    }
                }else{
                    System.out.print(" "+m.gridInit[j][i].getCache().getString());                    
                }
            }
            System.out.println(" ");
        }   
    } 
    
    @Override
    public void update(Observable obs, Object arg) {
        if(obs instanceof Matrice){
            affichage((Matrice)obs);
            
        }
    }

}

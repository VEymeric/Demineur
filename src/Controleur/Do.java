/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author gaetane
 */
public class Do {
    Modele.Matrice m;
    
    public Do(int i, int j, int percentMine){
        m = new Modele.Matrice(i,j,(i * j) * percentMine/ 100);
    }
    
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
                System.out.println(" Not understand your order");
                help();
        }
    }
    
    private void help() {
        System.out.println(" Not complete order ");
        System.out.println(" d x y -> show this case ");
        System.out.println(" m x y ?/! -> mark case with ?/!");
        System.out.println(" q -> quit ");
    } 

    // stop()
    

    
    
    
    
    
    
}

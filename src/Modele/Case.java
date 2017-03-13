
package Modele;

public class Case {
    private int x,y;
    private int bombes = 0;
    private CaseInit etat;
    private CaseHide cache;
    
    public Case(int x,int y){
        this.x = x;
        this.y = y;
        this.etat = CaseInit.NUMBER ;
        this.cache = CaseHide.HIDE;
    }

    public Case(int x,int y, CaseHide cache){
        this.x = x;
        this.y = y;
        this.cache = cache ;
    }    

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
  
    public int getBombes() {
        return bombes;
    }

    public void setBombes(int bombes) {
        this.bombes = bombes;
    }

    public CaseInit getEtat() {
        return etat;
    }

    public void setEtat(CaseInit etat) {
        this.etat = etat;
    }
    
    
    
}

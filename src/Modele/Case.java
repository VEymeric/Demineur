
package Modele;

public class Case {
    private int x,y;
    private int bombes;
    private CaseInit etat;
    private CaseHide cache;
    
    public Case(int x,int y){
        this.x = x;
        this.y = y;
        this.etat = CaseInit.NUMBER ;
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


package Modele;

public class Case {
    private int x,y;
    private int bombes;
    private CaseInit etat; //mine ; alone ; number
    private CaseHide cache;// unknow , flag , hide, show
    
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
    
    public boolean isMine(){
        return this.etat==CaseInit.MINE;
    }
    public boolean isAlone(){
        return this.etat==CaseInit.ALONE;
    }
    public boolean isNumber(){
        return this.etat==CaseInit.NUMBER;
    }
    public boolean isHide(){
        return this.cache==CaseHide.HIDE;
    }
    public boolean isUnknow(){
        return this.cache==CaseHide.UNKNOW;
    }
    public boolean isFlag(){
        return this.cache==CaseHide.FLAG;
    }
    public boolean isShow(){
        return this.cache==CaseHide.SHOW;
    }
    
    
}

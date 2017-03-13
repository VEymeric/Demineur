package Modele;

public enum CaseHide {
    UNKNOW("?"),
    FLAG("!"),
    HIDE("#");
    
    private String etat;

    private CaseHide(String etat){
        this.etat = etat; 
    }
    
    public String getString() {
        return etat;
    }
    
    public void setString(String s) {
        this.etat = s;
    }    
}

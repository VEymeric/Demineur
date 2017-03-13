package Modele;

public enum CaseHide {
    UNKNOW("?"),
    FLAG("!"),
    HIDE("#"),
    SHOW(" ");
    
    private String etat;
    private int number;

    private CaseHide(String etat){
        this.etat = etat; 
    }
    private CaseHide(int value){
        this.number = value;
    }
    
    public String getString() {
        return etat;
    }
    
    public void setString(String s) {
        this.etat = s;
    } 
    
    public int getValue() {
        return number;
    }
    
    public void setValue(int value) {
        this.number = value;
    }
}

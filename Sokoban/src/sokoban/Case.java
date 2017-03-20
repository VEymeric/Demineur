/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author gaetane
 */
public enum Case {
    MUR("#"),
    RIEN("  "),
    CIBLE("."),
    GARDIEN("@"),
    CAISSE("$"),
    END("*"),
    SUR("+");
    
    private String valueS;
    
    
    private Case(String x) {
        valueS = x;
    }

    public String getValueS() {
        return valueS;
    }

    public void setValueS(String valueS) {
        this.valueS = valueS;
    }
    
}

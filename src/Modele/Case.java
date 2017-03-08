/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public enum Case {
    MINE("x"),
    UNKNOW("?"),
    FLAG("!"),
    HIDE("#"),
    ALONE("."),
    NUMBER(0);

    private String valueS;
    private int valueI;

    private Case(String x) {
        valueS = x;
    }

    private Case(int x) {
        valueI = x;
    }

    public String getValueS() {
        return valueS;
    }

    public void setValueS(String valueS) {
        this.valueS = valueS;
    }

    public int getValueI() {
        return valueI;
    }

    public void setValueI(int valueI) {
        this.valueI = valueI;
    }

}

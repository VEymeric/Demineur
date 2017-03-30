package Modele;

public enum CaseInit {
    MINE("x"),
    ALONE("."),
    NUMBER(0);

    private int value;
    private String string;

    private CaseInit(String s) {
        string = s;
    }

    private CaseInit(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getString() {
        return string;
    }

    public void setString(String s) {
        this.string = s;
    }

}

package Modele;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Case implements Serializable {
    
    // méthode readObject, utilisé pour reconstituer un object sérialiser
    // car la déserialisation basique n'arrive pas à reconstruire l'objet 
    // donc il faut faire une surcharge
    public void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        // Lecture en esperant que c'est le bon ordre 
        this.x = ois.readInt();
        this.y = ois.readInt();
        this.bombes = ois.readInt();
        this.etat = (CaseInit) ois.readObject();
        this.cache = (CaseHide) ois.readObject();
        this.color = (Color)ois.readObject();
        
    }
    
    public void writeObject(ObjectOutputStream oos)throws IOException{
        oos.writeInt(x);
        oos.writeInt(y);
        oos.writeInt(bombes);
        oos.writeObject(etat);
        oos.writeObject(cache);
        oos.writeObject(color);        
    }
    

    private int x, y;
    private int bombes = 0;
    private Color color;
    private CaseInit etat; //mine ; alone ; number
    private CaseHide cache;// unknow , flag , hide, show

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        this.etat = CaseInit.NUMBER;
        this.cache = CaseHide.HIDE;
        this.color = Color.BLACK;
    }

    public Case(int x, int y, CaseHide cache) {
        this.x = x;
        this.y = y;
        this.cache = cache;
    }

    // Etat que peu avoir une case  

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public CaseHide getCache() {
        return cache;
    }

    public void setCache(CaseHide cache) {
        this.cache = cache;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public boolean isMine() {
        return this.etat == CaseInit.MINE;
    }

    public boolean isAlone() {
        return this.etat == CaseInit.ALONE;
    }

    public boolean isNumber() {
        return this.etat == CaseInit.NUMBER;
    }

    public boolean isHide() {
        return this.cache == CaseHide.HIDE;
    }

    public boolean isUnknow() {
        return this.cache == CaseHide.UNKNOW;
    }

    public boolean isFlag() {
        return this.cache == CaseHide.FLAG;
    }

    public boolean isShow() {
        return this.cache == CaseHide.SHOW;
    }

}

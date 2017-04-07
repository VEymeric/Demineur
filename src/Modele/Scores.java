package Modele;

import java.io.Serializable;

public class Scores implements Serializable{
    public int level;
    public int timer;
    public String pseudo;
    public Scores(int level, String pseudo, int timer){
        this.level = level;
        this.pseudo = pseudo;
        this.timer = timer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
     
    
    
    
}

package Modele;

import java.io.Serializable;

public class Scores implements Serializable{
    int level;
    int timer;
    String pseudo;
    public Scores(int level, String pseudo, int timer){
        this.level = level;
        this.pseudo = pseudo;
        this.timer = timer;
    }
}

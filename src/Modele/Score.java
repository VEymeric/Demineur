package Modele;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
    public int r,c,timer;
    public Score(int rows, int colums, int timer){
        this.r = rows;
        this.c = colums;
        this.timer = timer;
    }
    
    @Override
    public String toString(){
        return r + "x" + c + ":" + timer;
    }

    @Override
    public int compareTo(Score s) {
        if(this.timer < s.timer)
            return -1;
        else if(this.timer == s.timer)
            return 0;
        else return 1;
    }
}
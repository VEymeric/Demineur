/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Observable;

/**
 *
 * @author gaetane
 */
public class Matrice implements Observable {
    int i,j;
    Case[][] gridHide;
    Case[][] gridInit;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
        
    private void generateInit(){ // tableau de valeur initialisé
        for(int x = 0; x < getI(); x++){
            for(int y = 0 ; y < getJ() ; y++){
                gridInit[y][x] = Case.NUMBER;
            }
        }
    }
    
    private void generatHide(){ // tableau caché initialisé
        for(int x = 0; x < getI(); x++){
            for(int y = 0 ; y < getJ() ; y++){
                gridHide[y][x] = Case.UNKNOW;
            }
        }        
    }
    
    
    
    @Override
    private void changement(Observable o){
        Case c;
        if( o instenceof Case){
        
    }
    }
    
    
}

package javaonetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetane
 */
public class Ville { // création de la classe 
    private String nomVille;
    private String nomPays ;
    private int nbreHabitants ;
    
    public Ville() { // constructeur par defaut;
        System.out.println("Création d'une Ville ! ");
        nomVille = " Inconnu";
        nomPays = " Inconnu";
        nbreHabitants = 0 ;
    }
    public Ville(String pNomVille,String pNomPays,int pNbrHabitants){ // constructeur avec paramettre 
        nomVille = pNomVille;
        nomPays = pNomPays;
        nbreHabitants = pNbrHabitants;
        System.out.println("Création de la ville : "+ nomVille);
    }

    /**
     * @return the nomVille
     */
    public String getNomVille() {
        return nomVille;
    }

    /**
     * @param nomVille the nomVille to set
     */
    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    /**
     * @return the nomPays
     */
    public String getNomPays() {
        return nomPays;
    }

    /**
     * @param nomPays the nomPays to set
     */
    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }

    /**
     * @return the nbreHabitants
     */
    public int getNbreHabitants() {
        return nbreHabitants;
    }

    /**
     * @param nbreHabitants the nbreHabitants to set
     */
    public void setNbreHabitants(int nbreHabitants) {
        this.nbreHabitants = nbreHabitants;
    }
    
    public void compareTwoCity(Ville v1,Ville v2){
        if(v1.getNbreHabitants() < v2.getNbreHabitants()){
            System.out.println(v2.getNomVille()+ " à plus d'habitant que "+ v1.getNomVille());
        }else{
            System.out.println(v2.getNomVille()+ " à plus d'habitant que "+ v1.getNomVille());
        }
    }
    
    
}

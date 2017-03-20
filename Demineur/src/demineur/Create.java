/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

/**
 *
 * @author gaetane
 */
public class Create {
    private int i;
    private int j;
    private int percentage;
    private String[][] wireRack;
    private int numberMine;
    
    public Create (int i){
        this(i,i,50);
    }
    
    public Create(int i,int j,String percentage){
        this.i = i;
        this.j = j;
        String[] cr = percentage.split("%");
        this.percentage = Integer.parseInt(cr[0]);
    }
    
    public Create(int i, int j,int percentage){
        this.i = i;
        this.j = j;
        this.percentage = percentage;
    }
    
    public int getI() {
        return i;
    }

    public void setI(int i) {
        if( i <= 0 ){
            throw new java.lang.IllegalArgumentException(" Row too low ");
        }
        else{
            this.i = i;
        }   
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        if( j <= 0 ){
            throw new java.lang.IllegalArgumentException(" Line too low ");
        }
        else{
            this.j = j;  
        }
    }

    public int getPercentage() {
        return percentage;
    }

    public int getNumberMine() {
        return numberMine;
    }

    public void setNumberMine(int numberMine) {
        this.numberMine = numberMine;
        
    }
    
    public void setPercentage(int percentage) {       
        if(percentage < 0 || percentage > 100){
            throw new java.lang.IllegalArgumentException(" Percentage is not consistent ");
        }
        else{
            this.percentage = percentage;
        }
    }

    public String[][] getWireRack() {
        return wireRack;
    }

    public void setWireRack(int i, int j, String values) {
        wireRack[i][j] = values;
    }
        
    public void generateWireRack(){
        String[][] wireRackS = new String[getI()][getJ()];
        for(int x=0; x<wireRackS.length; x++){
            for(int y=0; y<wireRackS[x].length; y++){
                 wireRackS[x][y] = "0";  
	   }
	}
        wireRack = wireRackS;
    }
    
    public void createDemineur(){ // Affectation des valeurs dans le tableau
        int counter = 0;
        int mine = 0;
        generateWireRack();
        numberOfMine();
        System.out.println(getNumberMine());
        while (counter != getNumberMine()){
            int randI = (int)(Math.random()*getI());
            int randJ = (int)(Math.random()*getJ());
            if( "0".equals(wireRack[randI][randJ])){
                setWireRack(randI,randJ,"x");
                counter++;
            }
        }
        for( int x = 0; x < getI() ; x++){
            for( int y = 0; y< getJ(); y++){
                if("0".equals(wireRack[x][y])){
                    mine = searchMine(x,y);
                    if( mine == 0){
                        wireRack[x][y]=".";   
                    }else{
                        wireRack[x][y]= String.valueOf(mine);                        
                    }
                }
            }
        }
        affichage();
    }
    
    private void affichage(){// à déplacer ! 
        for( int x = 0; x < getI() ; x++){
            for( int y = 0; y< getJ(); y++){
                System.out.print(wireRack[x][y]+"  ");
            }
            System.out.println("  ");
        }    
    } 
    
    
    
    
    private int searchMine(int x,int y){ // Parcour des voisins pour voir si il y a une 
        int mine = 0;
        // on vérifie si la case existe et sinon on regarde ce qu'il y a dedans
        if(x+1 <= getI()-1){
            mine +=("x".equals(wireRack[x+1][y]))? 1 : 0;
        }         // coté droit
        if(x+1 <= getI()-1 && y+1 <= getJ()-1){
            mine +=("x".equals(wireRack[x+1][y+1]))? 1 : 0;
        }    // diagonal haute droite
        if(x+1 <= getI()-1 && y-1 >=0){
            mine +=("x".equals(wireRack[x+1][y-1]))? 1 : 0; 
        }// diagonale basse droite
        if(x-1 >= 0){
            mine +=("x".equals(wireRack[x-1][y]))? 1 : 0;
        }     // coté gauche
        if(x-1 >= 0 && y+1 <= getJ()-1){
            mine +=("x".equals(wireRack[x-1][y+1]))? 1 : 0;
        }     //diagonale haute gauche
        if(x-1 >= 0 && y-1 >=0){
            mine +=("x".equals(wireRack[x-1][y-1]))? 1 : 0;
        }    // diagonale basse gauche
        if(y-1 >= 0){
            mine +=("x".equals(wireRack[x][y-1]))? 1 : 0;
        }    // coté haut
        if(y+1 <= getJ()-1){
            mine +=("x".equals(wireRack[x][y+1]))? 1 : 0;
        }    // coté bas
        return mine;
    }
    
    public void numberOfMine(){ // Calcule du nombre de mine
        setNumberMine((getI()*getJ())*getPercentage()/100);
    }
}
package minesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetane
 */
public class LetsGo {
    private final Generate minus;
    private Case[][] ticTac;
    public boolean end = true;
    
    public LetsGo(Generate minus){
        this.minus = minus;
    }
    
    public Case[][] getTicTac() {
        return ticTac;
    }     

    public void setTicTac(Case[][] ticTac) {
        this.ticTac = ticTac;
    }
    
    private void print(int i,int j){
        if(minus.getWireRack(i,j) == Case.MINE){
            stop(" loose... ");
        }
        ticTac[i][j] = minus.getWireRack(i,j);
        showTicTac();
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
    
    
    private void stop(String oh){
        System.out.println("____________End of the game : you "+oh+"__________ ");
        setEnd(false);
    }
    
    public void hideTicTac(){
        Case[][] wireRackS = new Case[minus.getI()][minus.getJ()];
        for (Case[] wireRackS1 : wireRackS) {
            for (int y = 0; y < wireRackS1.length; y++) {
                wireRackS1[y] = Case.HIDE;
            }
        }
        setTicTac(wireRackS);  
        showTicTac();
    }
    
    private void mark(int i, int j, String mark){
        switch(mark){
            case "#":
                ticTac[i][j]= Case.HIDE;
                break;
            case "?":
                ticTac[i][j]= Case.UNKNOW;
                break;
            case "!":
                ticTac[i][j]= Case.FLAG;
                break;                
        }
        showTicTac();
    }
    
    private boolean casHide(){
        for(int x=0; x < minus.getI()-1 ;x++){
            for(int y=0 ; y < minus.getJ()-1 ; y++){
                if(ticTac[x][y] == Case.HIDE){
                    return false;
                }
            }
        }
        return true;
    }
    
    
    public void showTicTac(){
        for(int x=0; x < minus.getI(); x++){
            for(int y=0; y<minus.getJ();y++){
                if(Case.NUMBER.equals(ticTac[x][y])){
                    System.out.print(ticTac[x][y].getValueI()+"  ");
                }
                else{
                    if(Case.ALONE.equals(ticTac[x][y])){
                        propagation(x,y);
                    }
                    System.out.print(ticTac[x][y].getValueS()+"  ");
                }
            }
            System.out.println(" ");
        }
        if(casHide() == true){
            stop(" WIN !!!!! ");
        }
    }   
    
    private boolean checkOrNot(int x, int y){
        return ticTac[x][y] != Case.HIDE;
    }
    
    private void propagation(int x,int y){
        if(x+1 <= minus.getI()-1){
            if( checkOrNot(x+1,y) == false ){
                if( minus.getWireRack(x+1, y) == Case.ALONE ){
                    ticTac[x+1][y]=Case.ALONE;
                    propagation(x+1,y);
                    
                }
                if( minus.getWireRack(x+1, y) == Case.NUMBER){
                    ticTac[x+1][y] = minus.getWireRack(x+1, y);
                }                
            }
        }
        if(x+1 <= minus.getI()-1 && y+1 <= minus.getJ()-1){
            if(checkOrNot(x+1,y+1) == false){
            if( minus.getWireRack(x+1, y+1) == Case.ALONE ){
                ticTac[x+1][y+1]=Case.ALONE;
                propagation(x+1,y+1);
                
            }
            if( minus.getWireRack(x+1, y+1) == Case.NUMBER){
                ticTac[x+1][y+1] = minus.getWireRack(x+1, y+1);
            }
            }            
        }
        if(y+1 <= minus.getJ()-1){
            if( checkOrNot(x,y+1) == false){
            if( minus.getWireRack(x, y+1) == Case.ALONE ){
                ticTac[x][y+1]=Case.ALONE;
                propagation(x,y+1);
            }
            if( minus.getWireRack(x, y+1) == Case.NUMBER){
                ticTac[x][y+1] = minus.getWireRack(x, y+1);
            }   
            }
        }
        if(y-1 >= 0){
            if( checkOrNot(x,y-1) == false){
            if( minus.getWireRack(x, y-1) == Case.ALONE ){
                ticTac[x][y-1]=Case.ALONE;
                propagation(x,y-1);
            }
            if( minus.getWireRack(x, y-1) == Case.NUMBER){
                ticTac[x][y-1] = minus.getWireRack(x, y-1);
            }
            }            
        }
        if(x-1 >= 0 ){
            if(checkOrNot(x-1,y) == false){
            if( minus.getWireRack(x-1,y) == Case.ALONE ){
                ticTac[x-1][y]=Case.ALONE;
                propagation(x-1,y);
            }
            if( minus.getWireRack(x-1, y) == Case.NUMBER){
                ticTac[x-1][y] = minus.getWireRack(x-1, y);
            } 
            }
        } 
        if(y-1 >= 0 && x-1 >= 0 ){
            if(checkOrNot(x-1,y-1)== false){
            if( minus.getWireRack(x-1, y-1) == Case.ALONE ){
                ticTac[x-1][y-1]=Case.ALONE;
                propagation(x-1,y-1);
            }
            if( minus.getWireRack(x-1, y-1) == Case.NUMBER){
                ticTac[x-1][y-1] = minus.getWireRack(x-1, y-1);
            }            
            }
        }
        if(y+1 <= minus.getJ()-1 && x-1 >= 0 ){
            if(checkOrNot(x-1,y+1) == false){
            if( minus.getWireRack(x-1, y+1) == Case.ALONE ){
                ticTac[x-1][y+1]=Case.ALONE;
                propagation(x-1,y+1);
            }
            if( minus.getWireRack(x-1, y+1) == Case.NUMBER){
                ticTac[x-1][y+1] = minus.getWireRack(x-1, y+1);
            }
            }            
        }
        if(y-1 >= 0 && x+1 <= minus.getI()-1){
            if(checkOrNot(x+1,y-1)==false){
                if( minus.getWireRack(x+1, y-1) == Case.ALONE ){
                    ticTac[x+1][y-1]=Case.ALONE;
                    propagation(x+1,y-1);
                }
                if( minus.getWireRack(x+1, y-1) == Case.NUMBER){
                    ticTac[x+1][y-1] = minus.getWireRack(x+1, y-1);
                }
            }
        }        
    }
    
    // victoire, compte le nombre de case  
    
    public void order(String go){
        String[] cr = go.split(" ");
        switch(cr[0]){
            case "d":
                print(Integer.parseInt(cr[1]),Integer.parseInt(cr[2]));
                break;
            case "q":
                stop(" give up ");
                break;
            case "m":
                mark(Integer.parseInt(cr[1]),Integer.parseInt(cr[2]),cr[3]);
                System.out.print(cr[3]);
                break;
            default :
                System.out.println(" Not understand your order");
        }             
    }
    
}

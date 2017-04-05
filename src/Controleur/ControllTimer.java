package Controleur;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class ControllTimer extends JPanel{
    static long chrono = 0, chrono2 = 0; 
    JLabel affichage;
    static boolean run;
    static Timer SimpleTimer;
    public ControllTimer(JLabel label){
        run = false;
        affichage = label;
        SimpleTimer = new Timer(1000, (ActionEvent e) -> {
            chrono2 = java.lang.System.currentTimeMillis() ;
            String min = "";
            if(((int)((chrono2-chrono)/1000))>=60){
                min = Integer.toString((int)((chrono2-chrono)/1000)/60)+"m ";
            }
            label.setText(min + Integer.toString((int)((chrono2-chrono)/1000)%60));
        });

    }
    public int value(){
        return (int)((chrono2-chrono)/1000);
    }
    
    public void initChrono(){
        affichage.setText("0");
        SimpleTimer.stop();
    }
    public void startChrono() {
        initChrono();
        SimpleTimer.start();
        chrono = java.lang.System.currentTimeMillis() ; 
    } 

    public long stopChrono() { 
        SimpleTimer.stop();
        long temps = (chrono2 - chrono)/1000 ; 
        return temps;
    } 
}

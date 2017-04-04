/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author gaetane
 */
public class TimerPlay {
    
    private Timer t = new Timer();
    private Duration duration = Duration.ZERO;  // initialisation de PT0s
    
    TimerTask timerT = new TimerTask() { // Periodicité du code 
        @Override
        public void run() {
            duration = duration.plusSeconds(1); // rajout une par une les secondes
        }
    };
    
    public void start(){
        t.schedule(timerT, 0, 1000); // planification de l'éxecution : 0ms on commence à 1s, on évolue de 1000ms = 1s  
    }

    public void stop(){ // annule la planification des prochaines éxecution
        t.cancel();
    }

    public Duration getDuration() {
        return duration;
    }
}  


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author �mile
 */

public class Stopwatch {
    static int interval;
    static Timer timer;
    int minutes;
    String seconds;
    String time; //String that will be displayed
    
    public Stopwatch(String secs){
        int delay = 1000;
        int period = 1000;
        
        timer = new Timer();
        interval = Integer.parseInt(secs);
        if (interval >= 60){
            minutes = interval / 60;
            time = minutes + ": ";
        }
        else {
            minutes = 0;
            time = minutes + ": ";
        }
        if (interval % 60 < 10 && interval % 60 > 0){
            seconds = "0" + String.valueOf(interval % 60);
            time += seconds;
        }
        else {
            seconds = String.valueOf(interval % 60);
            time += seconds;
        }
        if (interval % 60 == 0){
            seconds = "0";
            time += seconds;
        }
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run(){
                int temp = setInterval();  

                if (temp >= 60){
                    minutes = temp / 60;
                    time = minutes + ": ";
                    }
                else {
                    minutes = 0;
                    time = minutes + ": ";
                }
                if (temp % 60 < 10 && temp % 60 > 0){
                    seconds = "0" + String.valueOf(temp % 60);
                    time += seconds;
                }
                else {
                    seconds = String.valueOf(temp % 60);
                    time += seconds;
                }
                if (temp % 60 == 0){
                    seconds = "0";
                        time += seconds;
                }

                if (temp == 0){
                    try {
                        time = "Game Over";
                } catch (Exception ex) {
                    Logger.getLogger(Stopwatch.class.getName()).log(Level.SEVERE, null, ex);
                }}
            }
        }, delay, period);
    }
    
    private static int setInterval() {
    if (interval == 1)
        timer.cancel();
    return --interval;
    }
    
    public String getTime(){
        return this.time;
    }
    
}      


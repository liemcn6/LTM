/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreading;

import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author DELL
 */
public class TimeFrame extends Frame{
    private Label lblTime;
    private int hour=0,minute=0,second=0;
    public TimeFrame(){
        super("Time Counter");
        this.setSize(250,150);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocation(400, 400);
        lblTime = new Label(hour+":"+minute+":"+second);
        lblTime.setAlignment(2);
        lblTime.setSize(50, 20);
        lblTime.setLocation(25, 55);
        this.add(lblTime);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    public void setHour(int hour) {
        this.hour = hour;
        lblTime.setText(hour+":"+minute+":"+second);
    }

    public void setMinute(int minute) {
        this.minute = minute;
        lblTime.setText(hour+":"+minute+":"+second);
    }

    public void setSecond(int second) {
        this.second = second;
        lblTime.setText(hour+":"+minute+":"+second);
    }
    
}

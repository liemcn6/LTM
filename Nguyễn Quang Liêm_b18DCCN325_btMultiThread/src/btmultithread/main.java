/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btmultithread;

/**
 *
 * @author DELL
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TimeFrame tf = new TimeFrame();
        tf.setVisible(true);
        HourThread htd = new HourThread(tf);
        MinuteThread mtd = new MinuteThread(tf, htd);
        SecondThread std = new SecondThread(tf, mtd);
        htd.start();
        mtd.start();
        std.start();
    }
    
}

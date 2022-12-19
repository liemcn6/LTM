/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.CalculatorModel;
import view.CalculatorView;

/**
 *
 * @author DELL
 */
public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    private long res;
    private int i=0;
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        res=0;
        this.view.addCongListener(new listener1());
        this.view.addTruListener(new listener2());
        this.view.addNhanListener(new listener3());
        this.view.addChiaListener(new listener4());
        this.view.addBangListener(new listiner5());
    }
    class listener1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(res == 0){
                    i=1;
                    res=view.gettext();
                    view.settext("");
                }
                else{
                    long tmp=view.gettext();
                    res=tinh(i,res,tmp);
                    view.settext("");
                    i=1;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                view.showdMessege();
            }
            
        }
        
    }
    class listener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(res == 0){
                    i=2;
                    res=view.gettext();
                    view.settext("");
                }
                else{
                    long tmp=view.gettext();
                    res=tinh(i,res,tmp);
                    view.settext("");
                    i=2;
                }
            } 
            catch (Exception ex) {
                System.out.println(ex);
                view.showdMessege();
            }
            
        }
        
    }
    class listener3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(res == 0){
                    i=3;
                    res=view.gettext();
                    view.settext("");
                }
                else{
                    long tmp=view.gettext();
                    res=tinh(i,res,tmp);

                    view.settext("");
                    i=3;
                }
            } 
            catch (Exception ex) {
                System.out.println(ex);
                view.showdMessege();
            }
            
        }
        
    }
    class listener4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(res == 0){
                    i=4;
                    res=view.gettext();
                    view.settext("");
                }
                else{
                    long tmp=view.gettext();
                    res=tinh(i,res,tmp);
                    view.settext("");
                    i=4;
                }
            }
            catch (Exception ex) {
                System.out.println(ex);
                view.showdMessege();
            }
            
        }
        
    }
    class listiner5 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                long tmp=view.gettext();
                res=tinh(i,res,tmp);
                view.settext(String.valueOf(res));
                i=0;
            } 
            catch (Exception ex) {
                System.out.println(ex);
                view.showdMessege();
            }
            
        }
    }
    public long tinh(int dem,long a,long b){
        long sum = 0;
        if(dem == 1){
            model.phepcong(a, b);
            sum=model.getCong();
        }
        if(dem == 2){
            model.pheptru(a, b);
            sum=model.getTru();
        }
        if(dem == 3){
            model.phepnhan(a, b);
            sum=model.getNhan();
        }
        if(dem == 4){
                model.phepchia(a, b);
                sum=model.getChia();
        }
        if(dem==0){
            sum=a;
        }
        return sum;
    }
}

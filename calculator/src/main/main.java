/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.CalculatorController;
import model.CalculatorModel;
import view.CalculatorView;

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
        CalculatorView view= new CalculatorView();
        CalculatorModel model =new CalculatorModel();
        CalculatorController controller =new CalculatorController(model, view);
        view.setVisible(true);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calservice;

import javax.jws.WebService;

/**
 *
 * @author DELL
 */
@WebService(serviceName = "Calculator", portName = "CalculatorSoap", endpointInterface = "org.tempuri.CalculatorSoap", targetNamespace = "http://tempuri.org/", wsdlLocation = "WEB-INF/wsdl/calculator/www.dneonline.com/calculator.asmx.wsdl")
public class calculator {

    public int add(int intA, int intB) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int subtract(int intA, int intB) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int multiply(int intA, int intB) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public int divide(int intA, int intB) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}

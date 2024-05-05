package com.example.demo.utility;

import com.example.demo.entities.Calculator;

public class UtilityCalculator {

    public static Calculator createCalculator(int parameter1, int parameter2, String operation) throws ArithmeticException {
        Calculator calculator = new Calculator();
        calculator.setParameter1(parameter1);
        calculator.setParameter2(parameter2);
        switch (operation) {
            case "ADD":
                calculator.setResult(parameter1 + parameter2);
                calculator.setOperation(operation);
                break;
            case "SUBTRACT":
                calculator.setResult(parameter1 - parameter2);
                calculator.setOperation(operation);
                break;
            case "MULTIPLY":
                calculator.setResult(parameter1 * parameter2);
                calculator.setOperation(operation);
                break;
            case "DIVIDE":
                calculator.setResult(parameter1 / parameter2);
                calculator.setOperation(operation);
        }
        return calculator;
    }
}

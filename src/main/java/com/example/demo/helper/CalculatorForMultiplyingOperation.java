package com.example.demo.helper;

import com.example.demo.entities.Calculator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculatorForMultiplyingOperation implements CalculatorCreation {
    @Override
    public Optional<Calculator> processCalculator(int parameter1, int parameter2, String operation) {
        Calculator calculator = new Calculator();
        calculator.setParameter1(parameter1);
        calculator.setParameter2(parameter2);
        calculator.setResult(parameter1 * parameter2);
        calculator.setOperation(operation);
        return Optional.of(calculator);
    }
}

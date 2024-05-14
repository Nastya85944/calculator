package com.example.demo.helper;

import com.example.demo.entities.Calculator;

import java.util.Optional;

public interface CalculatorCreation {

    Optional<Calculator> processCalculator(int parameter1, int parameter2, String operation);
}

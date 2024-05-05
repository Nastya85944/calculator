package com.example.demo.services;

import com.example.demo.entities.Calculator;
import com.example.demo.enums.OperationsEnum;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CalculatorRepository;
import com.example.demo.utility.UtilityCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationsServiceImpl implements OperationsService {

    private final CalculatorRepository calculatorRepository;

    @Autowired
    public OperationsServiceImpl(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveAddOperation(int parameter1, int parameter2) {
        Calculator existingRecord = findExistingData(OperationsEnum.ADD.name(), parameter1, parameter2);
        if (existingRecord != null) {
            return existingRecord;
        }
        Calculator calculator = UtilityCalculator.createCalculator(parameter1, parameter2, OperationsEnum.ADD.name());
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveSubtractOperation(int parameter1, int parameter2){
        Calculator existingRecord = findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2);
        if (existingRecord != null) {
            return existingRecord;
        }
        Calculator calculator = UtilityCalculator.createCalculator(parameter1, parameter2, OperationsEnum.SUBTRACT.name());
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveMultiplyOperation(int parameter1, int parameter2) {
        Calculator existingRecord = findExistingData(OperationsEnum.MULTIPLY.name(), parameter1, parameter2);
        if (existingRecord != null) {
            return existingRecord;
        }
        Calculator calculator = UtilityCalculator.createCalculator(parameter1, parameter2, OperationsEnum.MULTIPLY.name());
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveDivideOperation(int parameter1, int parameter2) throws ArithmeticException{
        Calculator existingRecord = findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2);
        if (existingRecord != null) {
            return existingRecord;
        }
        Calculator calculator = Optional.of(UtilityCalculator.createCalculator(parameter1, parameter2, OperationsEnum.DIVIDE.name()))
                .orElseThrow(ArithmeticException::new);
        return calculatorRepository.save(calculator);
    }

    @Override
    public Page<Calculator> getAllData(Pageable pageable) {
        return calculatorRepository.findAll(pageable);
    }

    @Override
    public void deleteRecord(String operation, int parameter1, int parameter2) throws ResourceNotFoundException {
        Calculator existingRecord = Optional.of(findExistingData(operation, parameter1, parameter2))
                .orElseThrow(() -> new ArithmeticException("The record doesn't exist for opertion " + operation));
        calculatorRepository.delete(existingRecord);
    }

    @Override
    public Page<Calculator> findRecordsByOperation(String operation, Pageable pageable) {
        return calculatorRepository.findRecordsByOperation(operation, pageable);
    }

    @Cacheable("operations")
    @Override
    public Calculator findExistingData(String operation, int parameter1, int parameter2){
        return calculatorRepository.findExistingData(operation, parameter1, parameter2);
    }
}

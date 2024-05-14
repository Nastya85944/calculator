package com.example.demo.services;

import com.example.demo.entities.Calculator;
import com.example.demo.enums.OperationsEnum;
import com.example.demo.exceptions.OperationNotFoundException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.helper.*;
import com.example.demo.repositories.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationsServiceImpl implements OperationsService {

    private  CalculatorRepository calculatorRepository;
    private List<CalculatorCreation> calculatorCreations;

    @Autowired
    public OperationsServiceImpl(CalculatorRepository calculatorRepository, List<CalculatorCreation>  calculatorCreations) {
        this.calculatorRepository = calculatorRepository;
        this.calculatorCreations = calculatorCreations;
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveAddOperation(int parameter1, int parameter2) throws OperationNotFoundException {
        Calculator existingRecord = findExistingData(OperationsEnum.ADD.name(), parameter1, parameter2);
        if (existingRecord != null) {
            return existingRecord;
        }
        Calculator calculator = createCalculator(parameter1, parameter2, OperationsEnum.ADD.name())
                .orElseThrow(() ->  new OperationNotFoundException("Operation is not correct"));
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveSubtractOperation(int parameter1, int parameter2) throws OperationNotFoundException{
        Optional<Calculator> existingRecord = Optional.of(findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2));
        if (existingRecord .isPresent()) {
            return existingRecord.get();
        }
        Calculator calculator = createCalculator(parameter1, parameter2, OperationsEnum.SUBTRACT.name())
                .orElseThrow(() ->  new OperationNotFoundException("Operation is not correct"));
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveMultiplyOperation(int parameter1, int parameter2) throws OperationNotFoundException{
        Optional<Calculator> existingRecord = Optional.of(findExistingData(OperationsEnum.MULTIPLY.name(), parameter1, parameter2));
        if (existingRecord.isPresent()) {
            return existingRecord.get();
        }
        Calculator calculator = createCalculator(parameter1, parameter2, OperationsEnum.MULTIPLY.name())
                .orElseThrow(() ->  new OperationNotFoundException("Operation is not correct"));
        return calculatorRepository.save(calculator);
    }

    @Cacheable("operations")
    @Override
    public Calculator checkAndSaveDivideOperation(int parameter1, int parameter2) throws ArithmeticException, OperationNotFoundException{
        Optional<Calculator> existingRecord = Optional.of(findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2));
        if (existingRecord .isPresent()) {
            return existingRecord.get();
        }
        Calculator calculator = createCalculator(parameter1, parameter2, OperationsEnum.DIVIDE.name())
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
                .orElseThrow(() -> new ArithmeticException("The record doesn't exist for operation " + operation));
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

    private Optional<Calculator> createCalculator(int parameter1, int parameter2, String operation) throws ArithmeticException {
        switch (operation) {
            case "ADD" -> {
                CalculatorCreation calculatorCreation = calculatorCreations.stream()
                        .filter(impl -> impl instanceof CalculatorForAddingOperation)
                        .findFirst()
                        .get();
                return calculatorCreation.processCalculator(parameter1, parameter2, operation);
            }
            case "SUBTRACT" -> {
                CalculatorCreation calculatorCreation = calculatorCreations.stream()
                        .filter(impl -> impl instanceof CalculatorForSubtractionOperation)
                        .findFirst().get();
                return calculatorCreation.processCalculator(parameter1, parameter2, operation);
            }
            case "MULTIPLY" -> {
                CalculatorCreation calculatorCreation = calculatorCreations.stream()
                        .filter(impl -> impl instanceof CalculatorForMultiplyingOperation)
                        .findFirst().get();
                return calculatorCreation.processCalculator(parameter1, parameter2, operation);
            }
            case "DIVIDE" -> {
                CalculatorCreation calculatorCreation = calculatorCreations.stream()
                        .filter(impl -> impl instanceof CalculatorForDividingOperation)
                        .findFirst().get();
                return calculatorCreation.processCalculator(parameter1, parameter2, operation);
            }
        }
        return Optional.empty();
    }
}

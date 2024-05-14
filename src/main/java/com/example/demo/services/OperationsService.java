package com.example.demo.services;

import com.example.demo.entities.Calculator;
import com.example.demo.exceptions.OperationNotFoundException;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationsService {
    Calculator checkAndSaveAddOperation(int parameter1, int parameter2) throws OperationNotFoundException;

    Calculator checkAndSaveSubtractOperation(int parameter1, int parameter2) throws OperationNotFoundException;

    Calculator checkAndSaveMultiplyOperation(int parameter1, int parameter2) throws OperationNotFoundException;

    Calculator checkAndSaveDivideOperation(int parameter1, int parameter2) throws ResourceNotFoundException, OperationNotFoundException;

    Page<Calculator> getAllData(Pageable pageable);

    void deleteRecord(String operation, int parameter1, int parameter2) throws ResourceNotFoundException;

    Page<Calculator> findRecordsByOperation(String operation, Pageable pageable);

    Calculator findExistingData(String operationName, int parameter1, int parameter2) throws ResourceNotFoundException;
}

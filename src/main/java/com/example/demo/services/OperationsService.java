package com.example.demo.services;

import com.example.demo.entities.Calculator;
import com.example.demo.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationsService {
    Calculator checkAndSaveAddOperation(long parameter1, long parameter2);

    Calculator checkAndSaveSubtractOperation(long parameter1, long parameter2);

    Calculator checkAndSaveMultiplyOperation(long parameter1, long parameter2);

    Calculator checkAndSaveDivideOperation(long parameter1, long parameter2) throws ResourceNotFoundException;

    Page<Calculator> getAllData(Pageable pageable);

    void deleteRecord(String operation, long parameter1, long parameter2) throws ResourceNotFoundException;

    Page<Calculator> findRecordsByOperation(String operation, Pageable pageable);

    Calculator findExistingData(String operationName, long parameter1, long parameter2) throws ResourceNotFoundException;
}

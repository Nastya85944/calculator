package com.example.demo.controllers;


import com.example.demo.entities.Calculator;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.OperationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/calculator")
public class OperationsController {

    private final OperationsService operationsService;

    @Autowired
    public OperationsController(OperationsService addOperationsService) {
        this.operationsService = addOperationsService;
    }

    @PostMapping("/add")
    public Calculator add(@Valid @RequestParam("param1") long parameter1, @Valid @RequestParam("param2") long parameter2){
        return operationsService.checkAndSaveAddOperation(parameter1, parameter2);
    }

    @PostMapping("/subtract")
    public Calculator subtract(@Valid @RequestParam("param1") long parameter1, @Valid @RequestParam("param2") long parameter2) {
        return operationsService.checkAndSaveSubtractOperation(parameter1, parameter2);
    }

    @PostMapping("/multiply")
    public Calculator multiply(@Valid @RequestParam("param1") long parameter1, @Valid @RequestParam("param2") long parameter2){
        return operationsService.checkAndSaveMultiplyOperation(parameter1, parameter2);
    }

    @PostMapping("/divide")
    public Calculator divide(@Valid @RequestParam("param1") long parameter1,
                             @Valid @RequestParam("param2") long parameter2) throws ArithmeticException, ResourceNotFoundException {
        return operationsService.checkAndSaveDivideOperation(parameter1, parameter2);
    }

    @GetMapping
    public Page<Calculator> getAllRecords(@RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "size", defaultValue = "0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return operationsService.getAllData(pageable);
    }

    @GetMapping("/operations")
    public Page<Calculator> findRecordByOperation(@RequestParam ("action") String operation,
                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return operationsService.findRecordsByOperation(operation.toUpperCase(), pageable);
    }

    @GetMapping("/record")
    public Calculator findRecord(@RequestParam String operation, @RequestParam("param1") long parameter1,
                                       @RequestParam("param2") long parameter2) throws ResourceNotFoundException {
        return operationsService.findExistingData(operation.toUpperCase(), parameter1, parameter2);
    }

    @DeleteMapping
    public void deleteRecord(@RequestParam String operation, @RequestParam("param1") long parameter1,
                           @RequestParam("param2") long parameter2) throws ResourceNotFoundException {
        operationsService.deleteRecord(operation, parameter1, parameter2);
    }
}

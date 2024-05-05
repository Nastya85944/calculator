package com.example.demo.repositories;

import com.example.demo.entities.Calculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculatorRepository extends JpaRepository<Calculator, Long> {

    @Query("select c from Calculator c where c.operation = ?1")
    Page<Calculator> findRecordsByOperation(String operationName, Pageable pageable);

    @Query("select c from Calculator c where c.operation = ?1 AND c.parameter1 = ?2 AND c.parameter2 = ?3")
    Calculator findExistingData(String operationName, long parameter1, long parameter2);
}

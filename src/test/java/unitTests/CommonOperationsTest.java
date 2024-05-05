package unitTests;

import com.example.demo.entities.Calculator;
import com.example.demo.enums.OperationsEnum;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repositories.CalculatorRepository;
import com.example.demo.services.OperationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CommonOperationsTest {

    @Mock
    private CalculatorRepository calculatorRepository;

    @InjectMocks
    private OperationsServiceImpl operationsService;

    @Test
    public void deleteRecord_existingRecord() throws ResourceNotFoundException {
        int parameter1 = 1;
        int parameter2 = 2;
        Calculator existingRecord = new Calculator();

        when(calculatorRepository.findExistingData(OperationsEnum.ADD.name(), parameter1, parameter2))
                .thenReturn(existingRecord);

        operationsService.deleteRecord(OperationsEnum.ADD.name(), parameter1, parameter2);

        verify(calculatorRepository).findExistingData(OperationsEnum.ADD.name(), parameter1, parameter2);
        verify(calculatorRepository).delete(existingRecord);
    }

    @Test
    public void findRecordsByOperation_recordsExist(){
        Pageable pageable = Pageable.ofSize(10).withPage(0);
        List<Calculator> records = new ArrayList<>();
        records.add(new Calculator());
        records.add(new Calculator());

        Page<Calculator> page = new PageImpl<>(records);
        when(calculatorRepository.findRecordsByOperation(OperationsEnum.ADD.name(), pageable))
                .thenReturn(page);

        Page<Calculator> result = operationsService.findRecordsByOperation(OperationsEnum.ADD.name(), pageable);
        verify(calculatorRepository).findRecordsByOperation(OperationsEnum.ADD.name(), pageable);
        assertEquals(result, page);


    }
}

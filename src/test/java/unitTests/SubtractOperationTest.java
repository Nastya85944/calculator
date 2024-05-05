package unitTests;

import com.example.demo.entities.Calculator;
import com.example.demo.enums.OperationsEnum;
import com.example.demo.repositories.CalculatorRepository;
import com.example.demo.services.OperationsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SubtractOperationTest {

    @Mock
    private CalculatorRepository calculatorRepository;

    @InjectMocks
    private OperationsServiceImpl operationsService;

    @Test
    public void testCheckAndSaveSubtractOperation_newRecord() {
        long parameter1 = 10L;
        long parameter2 = 2L;

        when(calculatorRepository.findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2))
                .thenReturn(null);

        Calculator newRecord = new Calculator();
        when(calculatorRepository.save(any(Calculator.class))).thenReturn(newRecord);

        Calculator result = operationsService.checkAndSaveSubtractOperation(parameter1, parameter2);
        verify(calculatorRepository).findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2);
        verify(calculatorRepository).save(any(Calculator.class));
        assertEquals(result, newRecord);
    }

    @Test
    public void checkAndSaveSubtractOperation_existingRecord(){
        long parameter1 = 10L;
        long parameter2 = 2L;

        Calculator existingRecord = new Calculator();
        when(calculatorRepository.findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2))
                .thenReturn(existingRecord);

        Calculator result = operationsService.checkAndSaveSubtractOperation(parameter1, parameter2);
        verify(calculatorRepository).findExistingData(OperationsEnum.SUBTRACT.name(), parameter1, parameter2);
        assertEquals(result, existingRecord);

    }
}

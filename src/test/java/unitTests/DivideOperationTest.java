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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DivideOperationTest {
    @Mock
    private CalculatorRepository calculatorRepository;

    @InjectMocks
    private OperationsServiceImpl operationsService;

    @Test
    public void checkAndSaveDivideOperation_newRecord() throws ArithmeticException {
        int parameter1 = 10;
        int parameter2 = 2;

        when(calculatorRepository.findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2))
                .thenReturn(null);

        Calculator newRecord = new Calculator();
        when(calculatorRepository.save(any(Calculator.class))).thenReturn(newRecord);

        Calculator result = operationsService.checkAndSaveDivideOperation(parameter1, parameter2);
        verify(calculatorRepository).findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2);
        verify(calculatorRepository).save(any(Calculator.class));
        assertEquals(newRecord, result);
    }

    @Test
    public void checkAndSaveDivideOperation_existingRecord() throws ArithmeticException {
        int parameter1 = 1;
        int parameter2 = 2;

        Calculator existingRecord = new Calculator();
        when(calculatorRepository.findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2))
                .thenReturn(existingRecord);

        Calculator result = operationsService.checkAndSaveDivideOperation(parameter1, parameter2);
        verify(calculatorRepository).findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2);
        assertEquals(existingRecord, result);
    }


    @Test
    public void checkAndSaveDivideOperation_throwArithmeticException() throws ArithmeticException{
        int parameter1 = 1;
        int parameter2 = 0;
        when(calculatorRepository.findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2))
                .thenReturn(null);

        assertThrows(ArithmeticException.class, () -> {
            operationsService.checkAndSaveDivideOperation(parameter1, parameter2);
        });

        verify(calculatorRepository).findExistingData(OperationsEnum.DIVIDE.name(), parameter1, parameter2);
        verify(calculatorRepository, never()).delete(any());
    }
}

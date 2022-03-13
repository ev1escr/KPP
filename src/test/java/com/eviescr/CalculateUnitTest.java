package com.eviescr;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.service.CalculateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CalculateUnitTest {

    @InjectMocks
    private CalculateService service;

    @Test
    public void Given_Num1AndNum2_When_CalculateSumAndMultiply_Then_ResultIsReturned() {
        Double num1 = 4D;
        Double num2 = 3D;
        CalculateResultDto calculateDto = new CalculateResultDto(num1, num2);

        assertEquals(service.getCalculation(num1, num2).getMultiply(), calculateDto.getMultiply());
        assertEquals(service.getCalculation(num1, num2).getSum(), calculateDto.getSum());
    }

}

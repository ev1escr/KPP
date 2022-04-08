package com.eviescr.converter;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.entity.CalculateResult;
import org.springframework.stereotype.Component;

@Component
public class CalculateResultConverter {

    public CalculateResult toEntity(CalculateResultDto calculateResultDto) {
        Double num1 = calculateResultDto.getNum1();
        Double num2 = calculateResultDto.getNum2();
        Long id = calculateResultDto.getId();
        return new CalculateResult(id, num1, num2);
    }

    public CalculateResultDto toDto(CalculateResult calculateResult) {
        Double num1 = calculateResult.getNum1();
        Double num2 = calculateResult.getNum2();
        Long id = calculateResult.getId();
        return new CalculateResultDto(id, num1, num2);
    }
}

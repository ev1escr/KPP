package com.eviescr.service;

import com.eviescr.dto.CalculateResultDto;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {
    public CalculateResultDto getCalculation(Double num1, Double num2) {
        return new CalculateResultDto(num1, num2);
    }
}

package com.eviescr.service;

import com.eviescr.dto.CalculateResultDto;
import com.eviescr.dto.CalculateResultListDto;

import java.util.List;

public interface CalculateService {
    CalculateResultDto getCalculation(Double num1, Double num2);

    CalculateResultDto save(CalculateResultDto calculateResultDto);

    CalculateResultDto update(CalculateResultDto calculateResultDto);

    CalculateResultDto getById(Long id);

    void deleteById(Long id);

    List<CalculateResultDto> getAll();

    CalculateResultListDto saveAll(List<CalculateResultDto> calculateResultDtos);
}

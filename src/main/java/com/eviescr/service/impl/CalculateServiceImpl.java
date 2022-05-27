package com.eviescr.service.impl;

import com.eviescr.converter.CalculateResultConverter;
import com.eviescr.dao.impl.CalculateResultDaoImpl;
import com.eviescr.dto.CalculateResultDto;
import com.eviescr.dto.CalculateResultListDto;
import com.eviescr.entity.CalculateResult;
import com.eviescr.exception.DuplicateRecordException;
import com.eviescr.exception.NoSuchRecordException;
import com.eviescr.service.CalculateService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final CalculateResultDaoImpl calculateResultDao;
    private final CalculateResultConverter converter;

    public CalculateServiceImpl(CalculateResultDaoImpl calculateResultDao, CalculateResultConverter converter) {
        this.calculateResultDao = calculateResultDao;
        this.converter = converter;
    }

    @Override
    public CalculateResultDto getCalculation(Double num1, Double num2) {
        return new CalculateResultDto(null, num1, num2);
    }

    @Override
    public CalculateResultDto save(CalculateResultDto calculateResultDto) {
        if (calculateResultDao.existsByKey(calculateResultDto.getId())) {
            throw new DuplicateRecordException("CalculateResult by key " + calculateResultDto.getId() + " duplicate");
        }
        CalculateResult calculateResult = calculateResultDao.save(converter.toEntity(calculateResultDto));
        return converter.toDto(calculateResult);
    }

    @Override
    public CalculateResultDto update(CalculateResultDto calculateResultDto) {
        if (!calculateResultDao.existsByKey(calculateResultDto.getId())) {
            throw new NoSuchRecordException("CalculateResult by key " + calculateResultDto.getId() + " not found");
        }
        return converter.toDto(calculateResultDao.save(converter.toEntity(calculateResultDto)));
    }

    @Override
    public CalculateResultDto getById(Long id) {
        if (!calculateResultDao.existsByKey(id)) {
            throw new NoSuchRecordException("CalculateResult by key " + id + " not found");
        }
        return converter.toDto(calculateResultDao.findByKey(id));
    }

    @Override
    public void deleteById(Long id) {
        if (!calculateResultDao.existsByKey(id)) {
            throw new NoSuchRecordException("CalculateResult by key " + id + " not found");
        }
        calculateResultDao.deleteByKey(id);
    }

    @Override
    public List<CalculateResultDto> getAll() {
        return calculateResultDao.findAll().parallelStream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CalculateResultListDto saveAll(List<CalculateResultDto> calculateResultDtos) {
        CalculateResultListDto calculateResultListDto = new CalculateResultListDto();
        List<CalculateResultDto> calculateResultDtoList = calculateResultDtos.parallelStream()
                .map(converter::toEntity)
                .peek(calculateResultDao::save)
                .collect(Collectors.toList()).stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
        calculateResultListDto.setCalculateResultDtoList(calculateResultDtoList);
        CalculateResultDto min = calculateResultDtoList.stream().min(Comparator.comparingDouble(CalculateResultDto::getSum))
                .orElseThrow(() -> new NoSuchRecordException("There is nothing to compare"));
        CalculateResultDto max = calculateResultDtoList.stream().max(Comparator.comparingDouble(CalculateResultDto::getSum))
                .orElseThrow(() -> new NoSuchRecordException("There is nothing to compare"));
        Double avg = calculateResultDtoList.stream().mapToDouble(CalculateResultDto::getSum).sum() /
                ((long) calculateResultDtoList.size() * 2);
        calculateResultListDto.setAvg(avg);
        calculateResultListDto.setMinSum(min);
        calculateResultListDto.setMaxSum(max);
        return calculateResultListDto;
    }
}

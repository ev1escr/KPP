package com.eviescr.dao.impl;

import com.eviescr.dao.CalculateResultDao;
import com.eviescr.exception.NoSuchRecordException;
import com.eviescr.dto.CalculateResultDto;
import com.eviescr.entity.CalculateResult;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CalculateResultDaoImpl implements CalculateResultDao<CalculateResult, Long> {

    private final Map<Long, CalculateResult> calculateResults = new LinkedHashMap<>();

    @Override
    public CalculateResult findByKey(Long key) {
        return calculateResults.get(key);
    }

    @Override
    public List<CalculateResult> findAll() {
        return new ArrayList<>(calculateResults.values());
    }

    @Override
    public CalculateResult save(CalculateResult calculateResult) {
        calculateResults.put(calculateResult.getId(), calculateResult);
        return calculateResult;
    }

    @Override
    public CalculateResult deleteByKey(Long key) {
        return calculateResults.remove(key);
    }

    @Override
    public boolean existsByKey(Long key) {
        return calculateResults.containsKey(key);
    }
}

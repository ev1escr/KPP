package com.eviescr.dao.impl;

import com.eviescr.dao.CalculateResultDao;
import com.eviescr.entity.CalculateResult;
import com.eviescr.entity.RequestParams;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CalculateResultDaoImpl implements CalculateResultDao<RequestParams, CalculateResult> {

    private final Map<RequestParams, CalculateResult> calculateResults = new LinkedHashMap<>();

    @Override
    public CalculateResult findByKey(RequestParams key) {
        return calculateResults.get(key);
    }

    @Override
    public List<CalculateResult> findAll() {
        return new ArrayList<>(calculateResults.values());
    }

    @Override
    public CalculateResult save(RequestParams requestParams, CalculateResult calculateResult) {
        calculateResults.put(requestParams, calculateResult);
        return calculateResult;
    }

    @Override
    public CalculateResult deleteByKey(RequestParams requestParams) {
        return calculateResults.remove(requestParams);
    }

    @Override
    public boolean existsByKey(RequestParams requestParams) {
        return calculateResults.containsKey(requestParams);
    }
}
